package Rastreador;

import com.dao.CapturaDAO;
import com.model.Captura;

public class prueba {

	public static void main(String[] args) {

		long t = System.currentTimeMillis();
		Captura cap = new Captura();
		CapturaDAO capDao = new CapturaDAO();

		try {
			java.sql.Timestamp z = new java.sql.Timestamp(t);

			cap.setTiempoInicio(z);
			;
			cap.setDuracion(5);
			cap.setId_usuario(404669);

			capDao.guardar(cap);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Sniffer s = new Sniffer();

		// CapturaDAO cp = new CapturaDAO();
		// System.out.println(cp.getLastID());

		
		s.sniff(40000);
		

	}

}
