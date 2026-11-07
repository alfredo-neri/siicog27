package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDay;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.DataYearSystemService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte262MB.
 */
@ManagedBean
@ViewScoped
public class Reporte262MB extends BaseDirectReport {

	/** The fecha ini. */
	private Date fechaIni;
	
	/** The fecha fin. */
	private Date fechaFin;
	
	/** The mes ini. */
	private Integer mesIni;
	
	/** The mes fin. */
	private Integer mesFin;

	/** The conctb. */
	private Conctb conctb;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	/** The data year system service. */
	@ManagedProperty(value = "#{dataYearSystemService}")
    private DataYearSystemService dataYearSystemService;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "reporte262";
		endFilename = jasperReporteName + ".pdf";
		Calendar  calendar = Calendar.getInstance();
		
		this.setFechaFin(this.getYeraEmp(calendar.get(Calendar.MONTH)+1).getTime());
		this.setFechaIni(this.getYeraEmp(calendar.get(Calendar.MONTH)+1).getTime());

	}

	/** The query. */
	String query;
	
	/** The parametros. */
	Map<String, Object> parametros;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		parametros = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = null;
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaIni);
		mesIni = calendar.get(Calendar.MONTH) + 1;
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(fechaFin);
		mesFin = calendar2.get(Calendar.MONTH) + 1;

		query = generateQuery(mesIni, mesFin, this.getUserDetails().getIdSector());
		parametros.put("imagePath", this.getUserDetails().getPathImgCab1());
		parametros.put("entidadName", this.getUserDetails().getMunicipio());
		parametros.put("mesNameInicio",
				tcMesRepository.findByMes(StringUtils.leftPad(mesIni.toString(), 2, "0")).getDescripcion());
		parametros.put("mesNameFin",
				tcMesRepository.findByMes(StringUtils.leftPad(mesFin.toString(), 2, "0")).getDescripcion());
		parametros.put("lastDayOfMonth", getLastDay(mesFin));
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
		parametros.put("firmaP1", firma.getPuesto().getPuesto());
	    parametros.put("firmaN1", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
	    parametros.put("firmaP2", firma.getPuesto().getPuesto());
	    parametros.put("firmaN2", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
	    parametros.put("firmaP3", firma.getPuesto().getPuesto());
	    parametros.put("firmaN3", firma.getNombre());
	    firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F28.getValue());
	    parametros.put("firmaP4", firma.getPuesto().getPuesto());
	    parametros.put("firmaN4", firma.getNombre());
		parametros.put("year", conctb.getAnoemp());
		parametros.put("query", query);
		parametros.put("claveEntidad", conctb.getClave());

		return parametros;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Generate query.
	 *
	 * @param mesInicial the mes inicial
	 * @param mesFinal the mes final
	 * @param idSector the id sector
	 * @return the string
	 */
	public String generateQuery(Integer mesInicial, Integer mesFinal, Integer idSector) {

		StringBuilder sQuery = new StringBuilder();
		StringBuilder sAmpli = new StringBuilder();
		StringBuilder sRedu = new StringBuilder();
		StringBuilder sEjpa = new StringBuilder();
		StringBuilder sEjxpa = new StringBuilder();
		StringBuilder sToeje = new StringBuilder();
		StringBuilder sCompro = new StringBuilder();
		StringBuilder sFoot = new StringBuilder();

		String sAuto = "SUM(P.AUTO1_1+P.AUTO1_2+P.AUTO1_3+P.AUTO1_4+P.AUTO1_5+P.AUTO1_6+P.AUTO1_7+P.AUTO1_8+P.AUTO1_9+P.AUTO1_10+P.AUTO1_11+P.AUTO1_12) AUTO1, ";
		sFoot.append("FROM PASO P, NATGAS N")
				.append(" WHERE P.IDSECTOR = N.IDSECTOR AND P.PARTIDA  = N.CLVGAS AND P.IDSECTOR = " + idSector
						+ " AND SUBSTR(P.PARTIDA,4,1)<>'0'")
				.append(" GROUP BY P.PARTIDA, N.NOMGAS ORDER BY P.PARTIDA");
		sQuery.append("SELECT P.PARTIDA, N.NOMGAS, ");

		for (int i = mesInicial; i <= mesFinal; i++) {
			sAmpli.append(" P.AMPLI1_" + i + " +");
			sRedu.append(" P.REDU1_" + i + " +");
			sEjpa.append(" P.EJPA1_" + i + " +");
			sEjxpa.append(" P.EJXPA1_" + i + " +");
			sToeje.append(" P.TOEJE1_" + i + " +");
			sCompro.append(" P.COMPRO1_" + i + " +");
		}

		sAmpli.insert(0, "SUM(");
		sRedu.insert(0, "SUM(");
		sEjpa.insert(0, "SUM(");
		sEjxpa.insert(0, "SUM(");
		sToeje.insert(0, "SUM(");
		sCompro.insert(0, "SUM(");

		sQuery.append(sAuto).append(sAmpli.substring(0, sAmpli.length() - 1)).append(") AMPLI, ")
				.append(sRedu.substring(0, sRedu.length() - 1)).append(") REDU, ")
				.append(sEjpa.substring(0, sEjpa.length() - 1)).append(") EJPA, ")
				.append(sEjxpa.substring(0, sEjxpa.length() - 1)).append(") EJXPA, ")
				.append(sToeje.substring(0, sToeje.length() - 1)).append(") TOEJE, ")
				.append(sCompro.substring(0, sCompro.length() - 1)).append(") COMPRO ").append(sFoot);

		return sQuery.toString();
	}

	/**
	 * Gets the mes ini.
	 *
	 * @return the mes ini
	 */
	public Integer getMesIni() {
		return mesIni;
	}

	/**
	 * Sets the mes ini.
	 *
	 * @param mesIni the new mes ini
	 */
	public void setMesIni(Integer mesIni) {
		this.mesIni = mesIni;
	}

	/**
	 * Gets the mes fin.
	 *
	 * @return the mes fin
	 */
	public Integer getMesFin() {
		return mesFin;
	}

	/**
	 * Sets the mes fin.
	 *
	 * @param mesFin the new mes fin
	 */
	public void setMesFin(Integer mesFin) {
		this.mesFin = mesFin;
	}

	/**
	 * Gets the fecha ini.
	 *
	 * @return the fecha ini
	 */
	public Date getFechaIni() {
		return fechaIni;
	}

	/**
	 * Sets the fecha ini.
	 *
	 * @param fechaIni the new fecha ini
	 */
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin the new fecha fin
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	/**
	 * Gets the data year system service.
	 *
	 * @return the data year system service
	 */
	public DataYearSystemService getDataYearSystemService() {
		return dataYearSystemService;
	}

	/**
	 * Sets the data year system service.
	 *
	 * @param dataYearSystemService the new data year system service
	 */
	public void setDataYearSystemService(DataYearSystemService dataYearSystemService) {
		this.dataYearSystemService = dataYearSystemService;
	}

	/**
	 * Gets the yera emp.
	 *
	 * @param month the month
	 * @return the yera emp
	 */
	public Calendar getYeraEmp(Integer month) {
        Calendar calendar = new GregorianCalendar(
                this.getDataYearSystemService().getYear(this.getUserDetails().getIdSector()), (month - 1), 1);
        return calendar;
    }
	
	/**
	 * Gets the conctb fin.
	 *
	 * @param conctb the new conctb 
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb fin.
	 *
	 * @param conctb the new conctb 
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}
	/**
	 * Gets the conctbRepository fin.
	 *
	 * @param conctb repository the new fecha fin
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}
	
	/**
	 * Sets the conctbRepository fin.
	 *
	 * @param conctb repository the new fecha fin
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
	
}
