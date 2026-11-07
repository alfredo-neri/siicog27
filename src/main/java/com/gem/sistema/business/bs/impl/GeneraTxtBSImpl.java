package com.gem.sistema.business.bs.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.GeneraTxtBS;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.business.dto.EaepecaldfDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.ReportesRepository;

@Repository
public class GeneraTxtBSImpl implements GeneraTxtBS {

	private static final String CONTENEDOR = "\"";
	private static final String SEPARADOR = "|";
	private static final String NO_ETEQUITADO = "GASTO NO ETIQUETADO";
	private static final String ETIQUETADO = "GASTO ETIQUETADO";
	private static final String FILE_NAE = "EAEPECALDF";
	private static final String TXT = ".txt";

	@Autowired
	private ReportesRepository reportesRepository;

	@Autowired
	ConctbRepository conctbRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String generatArchivoTxt(long idReporte, Integer idSector, Integer trimestre) {

		TcReporte tcReporte = this.reportesRepository.findById(idReporte).orElse(null);
		Conctb conctb = this.conctbRepository.findByIdsector(idSector);
		String fileSystem = tcReporte.getRutaArchivo() + "/" + tcReporte.getNombreArchivo() + conctb.getClave()
				+ conctb.getAnoemp() + StringUtils.leftPad(trimestre.toString(), 2, "0") + TXT;
		String sSql = String.format(tcReporte.getQry1(), idSector, trimestre);
		List<String> listDatos = this.jdbcTemplate.query(sSql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				return rs.getString(1);
			}
		});

		return generaArchivo(listDatos, fileSystem);
	}

	public String generaArchivo(List<String> listdatos, String fileSystem) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileSystem);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			listdatos.forEach(datos -> out.print(datos + "\r\n"));

			out.close();
			return fileSystem;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;

	}
	
	

	public ReportesRepository getReportesRepository() {
		return reportesRepository;
	}

	public void setReportesRepository(ReportesRepository reportesRepository) {
		this.reportesRepository = reportesRepository;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	@Override
	public String generaArchivoEaepecaldf(Integer idSector, Integer trimestre) {
		List<EaepecaldfDTO> listNoEtiq = new ArrayList<EaepecaldfDTO>();
		List<EaepecaldfDTO> listEtiq = new ArrayList<EaepecaldfDTO>();
		EaepecaldfDTO noEtiq = new EaepecaldfDTO();
		EaepecaldfDTO etiq = new EaepecaldfDTO();
		Conctb conctb = this.conctbRepository.findByIdsector(idSector);

		String sSql = this.generaQueryTotalNoEtiquetado(idSector, trimestre);
		System.out.println(sSql);
		List<EaepecaldfDTO> lis = this.jdbcTemplate.query(sSql, new Object[] { idSector },
				new RowMapper<EaepecaldfDTO>() {

					@Override
					public EaepecaldfDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						EaepecaldfDTO eaepecaldfDTO = new EaepecaldfDTO();
						eaepecaldfDTO.setClave(rs.getString("CLAVE"));
						eaepecaldfDTO.setTitulo(rs.getString("NOMBRE_CUENTA"));
						eaepecaldfDTO.setAprobado(rs.getBigDecimal("APROBADO"));
						eaepecaldfDTO.setPagado(rs.getBigDecimal("PAGADO"));
						eaepecaldfDTO.setDevengado(rs.getBigDecimal("DEVENGADO"));
						eaepecaldfDTO.setAmpliacion(rs.getBigDecimal("AMPLIACION"));
						eaepecaldfDTO.setReduccion(rs.getBigDecimal("REDUCCION"));
						eaepecaldfDTO.setPorEjercer(rs.getBigDecimal("POR_EJERCER"));
						return eaepecaldfDTO;
					}
				});
		for (EaepecaldfDTO dto : lis) {
			if ((dto.getClave().compareTo("101") >= 0 && dto.getClave().compareTo("113") <= 0)
					|| dto.getClave().compareTo("201") >= 0 && dto.getClave().compareTo("202") >= 202) {
				noEtiq.setTitulo(NO_ETEQUITADO);
				noEtiq.setAprobado(noEtiq.getAprobado().add(dto.getAprobado()));
				noEtiq.setPagado(noEtiq.getPagado().add(dto.getPagado()));
				noEtiq.setDevengado(noEtiq.getDevengado().add(dto.getDevengado()));
				noEtiq.setAmpliacion(noEtiq.getAmpliacion().add(dto.getAmpliacion()));
				noEtiq.setReduccion(noEtiq.getReduccion().add(dto.getReduccion()));
				noEtiq.setPorEjercer(noEtiq.getPorEjercer().add(dto.getPorEjercer()));
				listNoEtiq.add(dto);
			}
			if ((dto.getClave().compareTo("203") >= 0 && dto.getClave().compareTo("225") <= 0)
					|| (dto.getClave().compareTo("114") >= 0 && dto.getClave().compareTo("115") <= 0)) {
				etiq.setTitulo(ETIQUETADO);
				etiq.setTitulo(dto.getClave() + " " + dto.getTitulo());
				etiq.setAprobado(etiq.getAprobado().add(dto.getAprobado()));
				etiq.setPagado(etiq.getPagado().add(dto.getPagado()));
				etiq.setDevengado(etiq.getDevengado().add(dto.getDevengado()));
				etiq.setAmpliacion(etiq.getAmpliacion().add(dto.getAmpliacion()));
				etiq.setReduccion(etiq.getReduccion().add(dto.getReduccion()));
				etiq.setPorEjercer(etiq.getPorEjercer().add(dto.getPorEjercer()));
				listNoEtiq.add(dto);
			}
		}
		EaepecaldfDTO totales = new EaepecaldfDTO();
		totales = generarTotales(noEtiq);
		totales.setTitulo(NO_ETEQUITADO);
		String hNoEtiq = generarEncabezados(totales);
		totales = new EaepecaldfDTO();

		totales = generarTotales(etiq);
		totales.setTitulo(ETIQUETADO);
		String hEtiq = generarEncabezados(totales);
		String datos;
		FileWriter fw = null;
		try {
			String fileSystem = "/gem/reportes/" + "EAEPECFLDF" + conctb.getClave() + conctb.getAnoemp()
					+ StringUtils.leftPad(trimestre.toString(), 2, "0") + TXT;
			fw = new FileWriter(fileSystem);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			/***
			 * GASTO NO ETIQUETADO
			 */
			out.print(hNoEtiq + "\n");
			for (EaepecaldfDTO nEtiq : listNoEtiq) {
				totales = generarTotales(nEtiq);
				datos = generarEncabezados(totales);
				out.print(datos + "\n");

			}

			/***
			 * GASTO ETIQUETADO
			 */
			out.print(hEtiq + "\n");
			for (EaepecaldfDTO etique : listEtiq) {
				totales = generarTotales(etique);
				datos = generarEncabezados(totales);
				out.print(etique + "\n");

			}

			out.close();
			return fileSystem;
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	private EaepecaldfDTO generarTotales(EaepecaldfDTO eaepecaldfDTO) {
		EaepecaldfDTO total = new EaepecaldfDTO();
		total.setAprobado(eaepecaldfDTO.getAprobado());
		total.setAmpreduccion(eaepecaldfDTO.getAmpliacion().subtract(eaepecaldfDTO.getReduccion()));
		total.setModificado(
				eaepecaldfDTO.getAprobado().add(eaepecaldfDTO.getAmpliacion()).subtract(eaepecaldfDTO.getReduccion()));
		total.setDevengado(eaepecaldfDTO.getDevengado());
		total.setPagado(eaepecaldfDTO.getPagado());
		total.setPorEjercer(eaepecaldfDTO.getAprobado().add(eaepecaldfDTO.getAmpreduccion())
				.subtract(eaepecaldfDTO.getDevengado()));
		return eaepecaldfDTO;
	}

	private String generarEncabezados(EaepecaldfDTO eaepecaldfDTO) {
		StringBuilder datos = new StringBuilder();
		datos.append(CONTENEDOR).append(eaepecaldfDTO.getClave()).append(" " + eaepecaldfDTO.getTitulo())
				.append(CONTENEDOR.concat(SEPARADOR)).append(CONTENEDOR + eaepecaldfDTO.getAprobado())
				.append(CONTENEDOR.concat(SEPARADOR)).append(CONTENEDOR + eaepecaldfDTO.getAmpreduccion())
				.append(CONTENEDOR.concat(SEPARADOR)).append(CONTENEDOR).append(eaepecaldfDTO.getAmpreduccion())
				.append(CONTENEDOR.concat(SEPARADOR)).append(CONTENEDOR).append(eaepecaldfDTO.getModificado())
				.append(CONTENEDOR.concat(SEPARADOR)).append(CONTENEDOR).append(eaepecaldfDTO.getDevengado())
				.append(CONTENEDOR.concat(SEPARADOR)).append(CONTENEDOR).append(eaepecaldfDTO.getPagado())
				.append(CONTENEDOR.concat(SEPARADOR)).append(CONTENEDOR).append(eaepecaldfDTO.getPorEjercer());
		return datos.toString();
	}

	private String generaQueryTotalNoEtiquetado(Integer idSector, Integer trimestre) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder sumAprobado = new StringBuilder();
		StringBuilder sumPagado = new StringBuilder();
		StringBuilder sumDevenagdo = new StringBuilder();
		StringBuilder sumAmpliacion = new StringBuilder();
		StringBuilder sumReduccion = new StringBuilder();
		StringBuilder sumPorEjerer = new StringBuilder();
		sSql.append("SELECT SUBSTR(PA.CLAVE,1,3) CLAVE,  NA.NOMGAS NOMBRE_CUENTA,");
		sumAprobado.append(" SUM(");
		sumPagado.append("SUM( ");
		sumDevenagdo.append("SUM(");
		sumAmpliacion.append("SUM(");
		sumReduccion.append("SUM(");
		sumPorEjerer.append("SUM(");
		int indexMax = trimestre * 3;
		int indexmin = indexMax - 2;

		for (int i = indexmin; i <= indexMax; i++) {
			sumAprobado.append("PA.AUTO1_" + i + " + ");
			sumPagado.append("PA.EJPA1_" + i + " + ");
			sumDevenagdo.append("PA.EJXPA1_" + i + " + ");
			sumAmpliacion.append("PA.AMPLI1_" + i + " + ");
			sumReduccion.append("PA.REDU1_" + i + " + ");
			sumPorEjerer.append("PA.PORPA1_" + i + " + ");
		}

		sSql.append(sumAprobado.substring(0, sumAprobado.length() - 2)).append(")APROBADO, ").append("\n")
				.append(sumPagado.substring(0, sumPagado.length() - 2)).append(") PAGADO,").append("\n")
				.append(sumDevenagdo.substring(0, sumDevenagdo.length() - 2)).append(") DEVENGADO,").append("\n")
				.append(sumAmpliacion.substring(0, sumAmpliacion.length() - 2)).append(") AMPLIACION,").append("\n")
				.append(sumReduccion.substring(0, sumReduccion.length() - 2)).append(") REDUCCION,").append("\n")
				.append(sumPorEjerer.substring(0, sumPorEjerer.length() - 2)).append(") POR_EJERCER").append("\n");
		sSql.append("FROM  PASO PA\n").append(",\n" + "      NATGAS NA\n").append("WHERE PA.IDSECTOR = NA.IDSECTOR\n")
				.append("  AND NA.CLVGAS = PA.PARTIDA\n").append("  AND SUBSTR(PA.PARTIDA,4,1) <>  '0'\n")
				.append("  AND PA.IDSECTOR = ?\n").append("GROUP BY SUBSTR(PA.CLAVE,1,3) ,  NA.NOMGAS ");
		return sSql.toString();
	}

	private String generaQueryTotalEaepecfldf(Integer idSector, Integer trimestre) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder sumAprobado = new StringBuilder();
		StringBuilder sumPagado = new StringBuilder();
		StringBuilder sumDevenagdo = new StringBuilder();
		StringBuilder sumAmpliacion = new StringBuilder();
		StringBuilder sumReduccion = new StringBuilder();
		StringBuilder sumPorEjerer = new StringBuilder();
		sSql.append("SELECT SUBSTR(PA.PROGRAMA,1,2) PROGRAMA,  NA.NOMGAS NOMBRE_CUENTA,");
		sumAprobado.append(" SUM(");
		sumPagado.append("SUM( ");
		sumDevenagdo.append("SUM(");
		sumAmpliacion.append("SUM(");
		sumReduccion.append("SUM(");
		sumPorEjerer.append("SUM(");
		int indexMax = trimestre * 3;
		int indexmin = indexMax - 2;

		for (int i = indexmin; i <= indexMax; i++) {
			sumAprobado.append("PA.AUTO1_" + i + " + ");
			sumPagado.append("PA.EJPA1_" + i + " + ");
			sumDevenagdo.append("PA.EJXPA1_" + i + " + ");
			sumAmpliacion.append("PA.AMPLI1_" + i + " + ");
			sumReduccion.append("PA.REDU1_" + i + " + ");
			sumPorEjerer.append("PA.PORPA1_" + i + " + ");
		}

		sSql.append(sumAprobado.substring(0, sumAprobado.length() - 2)).append(")APROBADO, ").append("\n")
				.append(sumPagado.substring(0, sumPagado.length() - 2)).append(") PAGADO,").append("\n")
				.append(sumDevenagdo.substring(0, sumDevenagdo.length() - 2)).append(") DEVENGADO,").append("\n")
				.append(sumAmpliacion.substring(0, sumAmpliacion.length() - 2)).append(") AMPLIACION,").append("\n")
				.append(sumReduccion.substring(0, sumReduccion.length() - 2)).append(") REDUCCION,").append("\n")
				.append(sumPorEjerer.substring(0, sumPorEjerer.length() - 2)).append(") POR_EJERCER").append("\n");
		sSql.append("FROM  PASO PA\n").append(",\n" + "      NATGAS NA\n").append("WHERE PA.IDSECTOR = NA.IDSECTOR\n")
				.append("  AND NA.CLVGAS = PA.PARTIDA\n").append("  AND SUBSTR(PA.PARTIDA,4,1) <>  '0'\n")
				.append("  AND PA.IDSECTOR = ?\n").append("GROUP BY SELECT SUBSTR(PA.PROGRAMA,1,2) ,  NA.NOMGAS ");
		return sSql.toString();
	}

	@Override
	public String generaArchivoEaepecfldf(Integer idSector, Integer trimestre) {
		List<EaepecaldfDTO> listNoEtiq = new ArrayList<EaepecaldfDTO>();
		List<EaepecaldfDTO> listEtiq = new ArrayList<EaepecaldfDTO>();
		EaepecaldfDTO noEtiq = new EaepecaldfDTO();
		EaepecaldfDTO etiq = new EaepecaldfDTO();
		Conctb conctb = this.conctbRepository.findByIdsector(idSector);

		String sSql = this.generaQueryTotalNoEtiquetado(idSector, trimestre);
		System.out.println(sSql);
		List<EaepecaldfDTO> lis = this.jdbcTemplate.query(sSql, new Object[] { idSector },
				new RowMapper<EaepecaldfDTO>() {

					@Override
					public EaepecaldfDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						EaepecaldfDTO eaepecaldfDTO = new EaepecaldfDTO();
						eaepecaldfDTO.setClave(rs.getString("CLAVE"));
						eaepecaldfDTO.setTitulo(rs.getString("NOMBRE_CUENTA"));
						eaepecaldfDTO.setAprobado(rs.getBigDecimal("APROBADO"));
						eaepecaldfDTO.setPagado(rs.getBigDecimal("PAGADO"));
						eaepecaldfDTO.setDevengado(rs.getBigDecimal("DEVENGADO"));
						eaepecaldfDTO.setAmpliacion(rs.getBigDecimal("AMPLIACION"));
						eaepecaldfDTO.setReduccion(rs.getBigDecimal("REDUCCION"));
						eaepecaldfDTO.setPorEjercer(rs.getBigDecimal("POR_EJERCER"));
						return eaepecaldfDTO;
					}
				});
		for (EaepecaldfDTO dto : lis) {
			if ((dto.getClave().compareTo("101") >= 0 && dto.getClave().compareTo("113") <= 0)
					|| dto.getClave().compareTo("201") >= 0 && dto.getClave().compareTo("202") >= 202) {
				noEtiq.setTitulo(NO_ETEQUITADO);
				noEtiq.setAprobado(noEtiq.getAprobado().add(dto.getAprobado()));
				noEtiq.setPagado(noEtiq.getPagado().add(dto.getPagado()));
				noEtiq.setDevengado(noEtiq.getDevengado().add(dto.getDevengado()));
				noEtiq.setAmpliacion(noEtiq.getAmpliacion().add(dto.getAmpliacion()));
				noEtiq.setReduccion(noEtiq.getReduccion().add(dto.getReduccion()));
				noEtiq.setPorEjercer(noEtiq.getPorEjercer().add(dto.getPorEjercer()));
				listNoEtiq.add(dto);
			}
			if ((dto.getClave().compareTo("203") >= 0 && dto.getClave().compareTo("225") <= 0)
					|| (dto.getClave().compareTo("114") >= 0 && dto.getClave().compareTo("115") <= 0)) {
				etiq.setTitulo(ETIQUETADO);
				etiq.setTitulo(dto.getClave() + " " + dto.getTitulo());
				etiq.setAprobado(etiq.getAprobado().add(dto.getAprobado()));
				etiq.setPagado(etiq.getPagado().add(dto.getPagado()));
				etiq.setDevengado(etiq.getDevengado().add(dto.getDevengado()));
				etiq.setAmpliacion(etiq.getAmpliacion().add(dto.getAmpliacion()));
				etiq.setReduccion(etiq.getReduccion().add(dto.getReduccion()));
				etiq.setPorEjercer(etiq.getPorEjercer().add(dto.getPorEjercer()));
				listNoEtiq.add(dto);
			}
		}
		EaepecaldfDTO totales = new EaepecaldfDTO();
		totales = generarTotales(noEtiq);
		totales.setTitulo(NO_ETEQUITADO);
		String hNoEtiq = generarEncabezados(totales);
		totales = new EaepecaldfDTO();

		totales = generarTotales(etiq);
		totales.setTitulo(ETIQUETADO);
		String hEtiq = generarEncabezados(totales);
		String datos;
		FileWriter fw = null;
		try {
			String fileSystem = "/gem/reportes/" + FILE_NAE + conctb.getClave() + conctb.getAnoemp()
					+ StringUtils.leftPad(trimestre.toString(), 2, "0") + TXT;
			fw = new FileWriter(fileSystem);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			/***
			 * GASTO NO ETIQUETADO
			 */
			out.print(hNoEtiq + "\n");
			for (EaepecaldfDTO nEtiq : listNoEtiq) {
				totales = generarTotales(nEtiq);
				datos = generarEncabezados(totales);
				out.print(datos + "\n");

			}

			/***
			 * GASTO ETIQUETADO
			 */
			out.print(hEtiq + "\n");
			for (EaepecaldfDTO etique : listEtiq) {
				totales = generarTotales(etique);
				datos = generarEncabezados(totales);
				out.print(etique + "\n");

			}

			out.close();
			return fileSystem;
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;
	}

}