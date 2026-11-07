package com.gem.sistema.business.service.presupuesto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dto.IngresoDTO;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;
import com.gem.sistema.business.repository.load.IngresoRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PresIngresoCalendarizadoServiceImpl.
 */
@Service
public class PresIngresoCalendarizadoServiceImpl implements PresIngresoCalendarizadoService {

	/** Ruta donde se encuentra el archivo jasper del reporte de cuentas. */
	@Value("${view.report.path.resIngresoCalendarizado}")
	private String REPORT_PATH;
	
	/** The ingreso repository. */
	@Autowired
	IngresoRepository ingresoRepository;

	/** The tc usuario repository. */
	@Autowired
	TcUsuarioRepository tcUsuarioRepository;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.presupuesto.PresIngresoCalendarizadoService#generarArchivoIngresos8110(java.lang.String)
	 */
	@Override
	public String generarArchivoIngresos8110(String nombreUsuario) {
		List<Object> list = ingresoRepository.getCuentas8110();
		String pattern = "\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|%s|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"|\"%s\"\n";

		File tmpDir = new File(REPORT_PATH);
		if(!tmpDir.exists()){
			tmpDir.mkdirs();
		}
		
		String fileName = REPORT_PATH.concat(getNombreArchivo(nombreUsuario).concat(".txt"));
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName, true);
			for (Object o : list) {
				IngresoDTO i = new IngresoDTO();
				Object obj[] = (Object[]) o;

				i.setNomcta((String) obj[0]);
				i.setCuenta((String) obj[1]);
				i.setCvetxt((String) obj[2]);
				i.setScta((String) obj[3]);
				i.setSscta((String) obj[4]);
				i.setSsscta((String) obj[5]);
				i.setSssscta((String) obj[6]);
				i.setCampo1((int) obj[7]);
				i.setClvfuen((String) obj[8]);
				i.setAutoi1((BigDecimal) obj[9]);
				i.setAutoi2((BigDecimal) obj[10]);
				i.setAutoi3((BigDecimal) obj[11]);
				i.setAutoi4((BigDecimal) obj[12]);
				i.setAutoi5((BigDecimal) obj[13]);
				i.setAutoi6((BigDecimal) obj[14]);
				i.setAutoi7((BigDecimal) obj[15]);
				i.setAutoi8((BigDecimal) obj[16]);
				i.setAutoi9((BigDecimal) obj[17]);
				i.setAutoi10((BigDecimal) obj[18]);
				i.setAutoi11((BigDecimal) obj[19]);
				i.setAutoi12((BigDecimal) obj[20]);
				i.setAutoi13((BigDecimal) obj[21]);

				fw.append(String.format(pattern, i.getCuenta(), i.getScta(), getLast2Chars(i.getSscta()),
						getLast2Chars(i.getSsscta()), getLast2Chars(i.getSssscta()), i.getNomcta(),
						formatIntOrZero(i.getAutoi1()), formatIntOrZero(i.getAutoi2()), formatIntOrZero(i.getAutoi3()),
						formatIntOrZero(i.getAutoi4()), formatIntOrZero(i.getAutoi5()), formatIntOrZero(i.getAutoi6()),
						formatIntOrZero(i.getAutoi7()), formatIntOrZero(i.getAutoi8()), formatIntOrZero(i.getAutoi9()),
						formatIntOrZero(i.getAutoi10()), formatIntOrZero(i.getAutoi11()),
						formatIntOrZero(i.getAutoi12()), formatIntOrZero(i.getAutoi13()), obj[22]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return fileName;
	}

	/**
	 * Format int or zero.
	 *
	 * @param num the num
	 * @return the string
	 */
	private String formatIntOrZero(BigDecimal num) {
		DecimalFormat df = new DecimalFormat(".##");
		String cantidad = df.format(num);
		String decimales = cantidad.substring(cantidad.indexOf("."), cantidad.length());
		if (num.doubleValue() == 0.0) {
			cantidad = "0";
			return cantidad;
		} else if (decimales.equals(".0")) {
			return cantidad.substring(0, cantidad.indexOf("."));
		}
		return cantidad;
	}

	/**
	 * Gets the last 2 chars.
	 *
	 * @param str the str
	 * @return the last 2 chars
	 */
	private String getLast2Chars(String str) {
		if (str != null && str.length() >= 2) {
			return str.substring(str.length() - 2, str.length());
		} else if (str != null && str.length() < 2) {
			return str;
		} else
			return "";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.presupuesto.PresIngresoCalendarizadoService#getNombreArchivo(java.lang.String)
	 */
	@Override
	public String getNombreArchivo(String username) {
		Object obj = tcUsuarioRepository.getNomArchivoIngCaledarizado(username);
		Object[] arrObj = (Object[]) obj;
		String PI = "PI"; // identifica el Presupuesto de Ingresos
		String claveEntidad = (String) arrObj[0];
		String numEntidadMunicipal = (String) arrObj[1];
		String anio = "2016";
		return PI + claveEntidad + numEntidadMunicipal + anio;
	}

	/**
	 * Gets the ingreso repository.
	 *
	 * @return the ingreso repository
	 */
	public IngresoRepository getIngresoRepository() {
		return ingresoRepository;
	}

	/**
	 * Sets the ingreso repository.
	 *
	 * @param ingresoRepository the new ingreso repository
	 */
	public void setIngresoRepository(IngresoRepository ingresoRepository) {
		this.ingresoRepository = ingresoRepository;
	}

	/**
	 * Gets the tc usuario repository.
	 *
	 * @return the tc usuario repository
	 */
	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	/**
	 * Sets the tc usuario repository.
	 *
	 * @param tcUsuarioRepository the new tc usuario repository
	 */
	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

}
