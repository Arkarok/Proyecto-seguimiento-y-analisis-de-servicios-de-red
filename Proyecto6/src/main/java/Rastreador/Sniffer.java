package Rastreador;

import java.util.ArrayList;
import java.util.List;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;


public class Sniffer {

	public void sniff(int tiempo) {

		// EL DISPOSITIVO SE BUSCA EN MODO PROMISCUO
		List<PcapIf> alldevs = new ArrayList<PcapIf>(); // ALMACENAR DISPOSITIVOS
		StringBuilder errbuf = new StringBuilder(); // PARA CUALQUIER MENSAJE DE ERROR

		int r = Pcap.findAllDevs(alldevs, errbuf);
		if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
			System.err.printf("Can't read list of devices, error is %s", errbuf.toString());
			return;
		}

		System.out.println("Network devices found:");

		// ITERAR TODAS LAS TARJETA DE RED ENCONTRADA
		int i = 0;
		for (PcapIf device : alldevs) {
			String description = (device.getDescription() != null) ? device.getDescription()
					: "No description available";
			System.out.printf("#%d: %s [%s]\n", i++, device.getName(), description);
		}

		/*----------------------------------------------*/
		PcapIf device = alldevs.get(1);

		System.out.printf("\nChoosing '%s' on your behalf:\n",
				(device.getDescription() != null) ? device.getDescription() : device.getName());

		int snaplen = 64 * 1024; // CAPTURA TODOS LOS PAQUETES, SIN TRUNCACION -- BYTES CAPTURADOR POR PAQUETES
		int flags = Pcap.MODE_PROMISCUOUS;
		int timeout = 10 * 1000; // TIEMPO EN MILISEGUNDOS

		Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

		if (pcap == null) {
			System.err.printf("Error while opening device for capture: " + errbuf.toString());
			return;
		} else {
			System.out.println("Puedo capturar");
		}

		long t1 = System.currentTimeMillis();
		do {

			PacketHandler<String> tc = new PacketHandler<String>();

			// System.out.println(System.currentTimeMillis()- t1);
			pcap.loop(1, tc, "Snnifer");

		} while (System.currentTimeMillis() - t1 < tiempo);

	}

	public static String encontarServicio(int puertoO, int puertoD){
		
		String variable = "";
		
		
		if(puertoO == 20 || puertoD==20) {
		        variable = "FTP";        
		}else if(puertoO == 21 || puertoD==21) {
			variable = "FTP";
		}else if(puertoO == 80 || puertoD==80) {
			variable = "HTTP";
		}else if(puertoO == 53 || puertoD==53) {
			variable = "DNS";
		}else if(puertoO == 25 || puertoD==25) {
			variable = "SMTP";
		}else if(puertoO == 465 || puertoD==465) {
			variable = "SMTP";
		}else if(puertoO == 587 || puertoD==587) {
			variable = "SMTP";
		}else if(puertoO == 2525 || puertoD==2525) {
			 variable = "SMTP";
		}else if(puertoO == 110 || puertoD==110) {
			variable = "POP3";
		}else if(puertoO == 995 || puertoD==995) {
			variable ="POP3";
		}else if(puertoO == 67 || puertoD==67) {
			variable = "DHCP";
		}else if(puertoO == 68 || puertoD==68) {
			variable = "DHCP";
		}else if(puertoO == 443 || puertoD==443) {
			variable = "HTTPS";
		}else if(puertoO == 990 || puertoD==990) {
			variable = "FTPS";
		}else if(puertoO == 44994|| puertoD==44994) {
			variable = "SMTP";
		}else if(puertoO == 35004|| puertoD==35004) {
			variable = "SMTP";
		}else if(puertoO == 38328|| puertoD== 38328) {
			variable = "SMTP";
		}else if(puertoO == 35006|| puertoD== 35006) {
			variable = "SMTP";
		}else if(puertoO == 38330|| puertoD== 38330) {
			variable = "SMTP";
		}else if(puertoO == 39616|| puertoD== 39616) {
			variable = "SMTP";
		}else if(puertoO == 57224|| puertoD== 57224) {
			variable = "SMTP";
		}else if(puertoO == 53244|| puertoD== 53244) {
			variable = "SMTP";
		}else if(puertoO == 51298|| puertoD== 51298) {
			variable = "SMTP";
		}else {
			variable = "Otro";
		}
	  
		return variable;

    }
	

}
