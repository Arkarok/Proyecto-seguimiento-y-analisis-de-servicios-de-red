package Rastreador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;
import org.jnetpcap.protocol.voip.Rtp;
import org.w3c.dom.ls.LSOutput;

import com.dao.CapturaDAO;
import com.dao.PaqueteDAO;
import com.dao.ProtocoloDAO;
import com.dao.Protocolo_has_PaqueteDAO;
import com.model.Captura;
import com.model.Paquete;
import com.model.Protocolo;
import com.model.Protocolo_has_Paquete;

import org.jnetpcap.protocol.*;
import java.util.concurrent.TimeUnit;

public class PacketHandler<T> implements PcapPacketHandler<T> {

	private Ethernet eth = new Ethernet();
	private Ip4 ip = new Ip4();
	private Ip6 ip6 = new Ip6();
	private Tcp tcp = new Tcp();
	private Udp udp = new Udp();
	Sniffer s = new Sniffer();
	Date fecha = new Date();
	long t1 = System.currentTimeMillis();

	List<Captura> cp = new ArrayList<Captura>();

	public List<Captura> getCp() {
		return cp;
	}

	public void setCp(List<Captura> cp) {
		this.cp = cp;
	}

	public void guardar(Paquete paq, int Enlace, int Red, int Transporte) throws SQLException {

		PaqueteDAO pacDao = new PaqueteDAO();

		try {
			pacDao.guardar(paq);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Protocolo_has_Paquete Pro = new Protocolo_has_Paquete();
		int idpaquete = pacDao.getLastID();

		Pro.setIdPaquete(idpaquete);
		Pro.setIdProtocolo(Enlace);

		guardarProtocolo(Pro);

		Pro.setIdPaquete(idpaquete);
		Pro.setIdProtocolo(Red);

		guardarProtocolo(Pro);

		Pro.setIdPaquete(idpaquete);
		Pro.setIdProtocolo(Transporte);

		guardarProtocolo(Pro);

	}

	public void guardarProtocolo(Protocolo_has_Paquete cp) {

		Protocolo_has_PaqueteDAO cpDao = new Protocolo_has_PaqueteDAO();

		try {
			cpDao.guardar(cp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void nextPacket(PcapPacket packet, T user) {

		StringBuilder errbuf = new StringBuilder();
         
		/*
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		*/

		/*if (packet != null)
			return;

		/*if (!packet.hasHeader(tcp)) {
			return;
		}*/

		System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s\n",
				new Date(packet.getCaptureHeader().timestampInMillis()), packet.getCaptureHeader().caplen(),
				packet.getCaptureHeader().wirelen(), user);

		String contend = packet.toString();
		if (contend.contains("DDDDD") && contend.contains("upass")) {
			System.out.println(contend);
		}

		// System.out.println(contend);
		// SACAR LOS OBJETOS

		int longitud = packet.getCaptureHeader().caplen();

		System.out.println("Longitud " + longitud);

		int ProtocoloEnlace = 0;
		int ProtocoloRed = 0;
		int ProtocoloTransporte = 0;
		int puertoOrigen = 0;
		String direccionFisica = "";
		int puertoDestino = 0;
		int id_Servicio = 0;
		String direccion_source = "";
		String direccion_destino = "";

		if (packet.hasHeader(eth)) {
			ProtocoloEnlace = 1;
			System.out.println("source MAC " + FormatUtils.mac(packet.getHeader(eth).destination()));
			direccionFisica = FormatUtils.mac(packet.getHeader(eth).source());
		}

		if (packet.hasHeader(ip)) {
			ProtocoloRed = 2;
			System.out.println("source ip4: " + FormatUtils.ip(packet.getHeader(ip).source()));
			System.out.println("destino ip4: " + FormatUtils.ip(packet.getHeader(ip).destination()));
			direccion_source = FormatUtils.ip(packet.getHeader(ip).source());
			direccion_destino = FormatUtils.ip(packet.getHeader(ip).destination());
		}

		if (packet.hasHeader(ip6)) {
			ProtocoloRed = 2;
			System.out.println("source ip6: " + FormatUtils.ip(packet.getHeader(ip6).source()));
			System.out.println("destino ip46: " + FormatUtils.ip(packet.getHeader(ip6).destination()));

			direccion_source = FormatUtils.ip(packet.getHeader(ip6).source());
			direccion_destino = FormatUtils.ip(packet.getHeader(ip6).destination());
		}

		if (packet.hasHeader(tcp)) {
			ProtocoloTransporte = 4;
			System.out.println("source tcp: " + packet.getHeader(tcp).source());
			System.out.println("destino tcp: " + packet.getHeader(tcp).destination());
			puertoDestino = packet.getHeader(tcp).destination();
			puertoOrigen =packet.getHeader(tcp).source();
		}

		if (packet.hasHeader(udp)) {
			ProtocoloTransporte = 3;
			System.out.println("source udp: " + packet.getHeader(udp).source());
			System.out.println("destino udp " + packet.getHeader(udp).destination());
			puertoDestino = packet.getHeader(udp).destination();
			puertoOrigen =packet.getHeader(udp).source();
		}

		 String servicio = Sniffer.encontarServicio(puertoOrigen, puertoDestino);
	        System.out.println("Servicio : "  + servicio);
	  
	        switch(servicio) {
	        
		        case "HTTP": id_Servicio = 1;break;
		        case "FTP": id_Servicio = 2;break;
		        case "DNS": id_Servicio = 3;break;
		        case "SMTP": id_Servicio = 4;break;
		        case "POP3": id_Servicio = 5;break;
		        case "DHCP": id_Servicio = 6;break;
		        case "HTTPS": id_Servicio = 7;break;
		        case "FTPS": id_Servicio = 8;break;
		        case "Otro": id_Servicio = 9;break; 
		        
	        }

		System.out.println("id_servicio = " + id_Servicio);

		CapturaDAO capDao = new CapturaDAO();
		Paquete paq = new Paquete();

		paq.setIpOrigen(direccion_source);
		paq.setIpDestino(direccion_destino);
		paq.setPuertoDestino(puertoDestino);
		paq.setIdServicios(id_Servicio);
		paq.setTamaño(longitud);
		paq.setMac(direccionFisica);
		try {
			paq.setCodigo_captura(capDao.getLastID());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(paq);

		try {
			guardar(paq, ProtocoloEnlace, ProtocoloRed, ProtocoloTransporte);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Captura_has_Protocolo capP1 = new Captura_has_Protocolo();

	}

}
