package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import org.apache.commons.lang3.StringUtils;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.util.ConstantsClaveEnnum;
import com.gem.sistema.web.security.model.GemUser;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF0091205MB.
 */
@Component("reporteDependeciasTresMB")
@Scope("view")
public class ReporteDependeciasTresMB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_DEPENDENCIA_TRES";

	/** The mes. */
	private Integer mes;

	/** The clv dep. */
	/** Aviso cuando el sector no tiene dependencias de tres digitos en CATDEP. */
	private static final String SIN_DEPENDENCIA = "No hay dependencias registradas para el sector.";

	private String clvDep;

	private String nomDep;

	/** The list catdep. */
	private List<String> listCatdep;

	private List<TcPeriodo> listMeses;

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The catdep repository. */
	@Value("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The firmas repository. */
	@Value("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The execute pl service. */
	@Value("#{executePlService}")
	protected ExecutePlService executePlService;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct reporteDependeciasTresMB");
		jasperReporteName = "rf009_1_20_5";
		endFilename = jasperReporteName + ".pdf";

		listCatdep = catdepRepository.findByIdSectorAndclvdep(this.getUserDetails().getIdSector());
		listMeses = periodoRepositoy.findByTipoPeriodo(1);

		if (!CollectionUtils.isEmpty(listCatdep)) {
			clvDep = listCatdep.get(0);
		}
		if (!listMeses.isEmpty()) {
			mes = listMeses.get(0).getPeriodo();
			this.changeDependecia();
		}
	}

	public void changeDependecia() {
		Catdep dependencia = clvDep == null ? null
				: catdepRepository.getCatdep(this.getUserDetails().getIdSector(), clvDep);
		// sin dependencias en CATDEP la pantalla abre vacia en lugar de fallar
		nomDep = dependencia == null ? StringUtils.EMPTY : dependencia.getNomdep();
		if (dependencia == null) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!", SIN_DEPENDENCIA);
		}
	}

	/**
	 * Indica si hay una dependencia valida para generar el reporte; si no, avisa al usuario.
	 *
	 * @return true si se puede generar el reporte
	 */
	private boolean hayDependencia() {
		if (clvDep == null || catdepRepository.getCatdep(this.getUserDetails().getIdSector(), clvDep) == null) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!", SIN_DEPENDENCIA);
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		GemUser user = this.getUserDetails();
		TrPuestoFirma firma = null;
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		Catdep catdep = catdepRepository.getCatdep(user.getIdSector(), clvDep);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);

		paramsReport.put("p_mes", mes);
		paramsReport.put("p_an", conctb.getAnoemp());
		paramsReport.put("p_sector", user.getIdSector());
		paramsReport.put("pClaveDependencia", clvDep);
		paramsReport.put("pNombreDependencia", catdep.getNomdep());
		if (user.getIdSector() == 1) {
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(user.getIdSector(), 0L,
					ConstantsClaveEnnum.CLAVE_F01.getValue());
			paramsReport.put("p_l1", firma.getPuesto().getPuesto());
			paramsReport.put("p_n1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(user.getIdSector(), 0L,
					ConstantsClaveEnnum.CLAVE_F02.getValue());
			paramsReport.put("p_l2", firma.getPuesto().getPuesto());
			paramsReport.put("p_n2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(user.getIdSector(), 0L,
					ConstantsClaveEnnum.CLAVE_F03.getValue());
			paramsReport.put("p_l3", firma.getPuesto().getPuesto());
			paramsReport.put("p_n3", firma.getNombre());
		} else if (user.getIdSector() == 2) {
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(user.getIdSector(), 0L,
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("p_l1", firma.getPuesto().getPuesto());
			paramsReport.put("p_n1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(user.getIdSector(), 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			paramsReport.put("p_l2", firma.getPuesto().getPuesto());
			paramsReport.put("p_n2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(user.getIdSector(), 0L,
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			paramsReport.put("p_l3", firma.getPuesto().getPuesto());
			paramsReport.put("p_n3", firma.getNombre());
		}

		paramsReport.put("p_sql", this.generateQuery(mes, user.getIdSector(), clvDep));
		paramsReport.put("p_img", user.getPathImgCab1());
		paramsReport.put("entidadName", this.getUserDetails().getMunicipio());
		paramsReport.put("mesName", periodo.getDescripcion().toUpperCase());
		paramsReport.put("lastDayOfMonth", getLastDayByAnoEmp(mes, conctb.getAnoemp()));

		return paramsReport;
	}

	public void downloadXls() {
		if (hayDependencia() && this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (hayDependencia() && this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			this.createFilePdfInline();
			;
		}
	}

	/** The out. */
	Map<String, Object> out;

	/** The stream. */
	InputStream stream = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getFileTxt()
	 */
	public StreamedContent getFileTxt() {
		addToBitacora(true);
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_mes", mes);
		parameter.addValue("i_sector", this.getUserDetails().getIdSector());
		parameter.addValue("i_dependencia", clvDep);
		parameter.addValue("i_municipio", this.getUserDetails().getMunicipio());
		parameter.addValue("i_query", this.generateQuery(mes, this.getUserDetails().getIdSector(), clvDep));

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			fileTxt = StreamedContents.of(stream, "application/txt", out.get("O_NAME_FILE").toString(), null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		addToBitacora(false);
		return fileTxt;
	}

	/**
	 * Sets the file txt.
	 *
	 * @param fileTxt the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/**
	 * Gets the execute pl service.
	 *
	 * @return the execute pl service
	 */
	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	/**
	 * Sets the execute pl service.
	 *
	 * @param executePlService the new execute pl service
	 */
	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	public List<String> getListCatdep() {
		return listCatdep;
	}

	public void setListCatdep(List<String> listCatdep) {
		this.listCatdep = listCatdep;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	/**
	 * Gets the clv dep.
	 *
	 * @return the clv dep
	 */
	public String getClvDep() {
		return clvDep;
	}

	/**
	 * Sets the clv dep.
	 *
	 * @param clvDep the new clv dep
	 */
	public void setClvDep(String clvDep) {
		this.clvDep = clvDep;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	/**
	 * Generate query.
	 *
	 * @param mes    the mes
	 * @param sector the sector
	 * @param clvdep the clvdep
	 * @return the string
	 */
	public String generateQuery(Integer mes, Integer sector, String clvdep) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		StringBuilder c = new StringBuilder();
		StringBuilder d = new StringBuilder();
		StringBuilder f = new StringBuilder();
		StringBuilder g = new StringBuilder();
		StringBuilder cpt = new StringBuilder();
		a.append(" P.AUTO1_1 ");
		b.append(" P.EJPA1_1 ");
		c.append(" P.EJXPA1_1 ");
		d.append(" P.TOEJE1_1 ");
		f.append(" P.AMPLI1_1 ");
		g.append(" P.REDU1_1 ");
		cpt.append(" P.COMPRO1_1 ");

		for (int i = 2; i < 13; i++) {
			a.append("+ P.AUTO1_" + i + " ");
		}
		for (int i = 2; i <= mes; i++) {
			b.append("+ P.EJPA1_" + i + " ");
			c.append("+ P.EJXPA1_" + i + " ");
			d.append("+ P.TOEJE1_" + i + " ");
			f.append("+ P.AMPLI1_" + i + " ");
			g.append("+ P.REDU1_" + i + " ");
			cpt.append("+ P.COMPRO1_" + i + " ");
		}
		sSql.append(
				" SELECT PARTIDA,  NOMGAS , A , B , C , D , F , G , CPT , ( ( A + F - G ) -D ) E , A1 , B1 , C1 , D1 , F1 , G1 , CPT1  , ( ( A1 + F1 - G1 ) -D1 ) E1 ");
		sSql.append(" FROM( ");
		sSql.append("  SELECT  ");
		sSql.append("  P.PARTIDA ");
		sSql.append("  , NOMGAS ");
		sSql.append("  , SUM( " + a + " ) A ");
		sSql.append("  , SUM( " + b + " ) B ");
		sSql.append("  , SUM( " + c + " ) C ");
		sSql.append("  , SUM( " + d + " ) D ");
		sSql.append("  , SUM( " + f + " ) F ");
		sSql.append("  , SUM( " + g + " ) G ");
		sSql.append("  , SUM( " + cpt + " ) CPT ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + a + " ), 0) ) A1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + b + " ),0 )) B1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + c + " ),0 )) C1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + d + " ),0 )) D1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + f + " ),0 )) F1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + g + " ),0 )) G1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + cpt + " ),0 )) CPT1 ");
		sSql.append("    FROM PASO P, NATGAS N ");
		sSql.append("    WHERE P.PARTIDA=N.CLVGAS  ");
		sSql.append("      AND P.IDSECTOR= N.IDSECTOR  ");
		sSql.append("      AND N.IDSECTOR=" + sector + " ");
		sSql.append("      AND SUBSTR(P.CLAVE,1,3)  = '" + clvdep + "' ");
		sSql.append("  GROUP BY PARTIDA , NOMGAS 	 ");
		sSql.append("  ) ORDER BY PARTIDA ");
		System.out.println(sSql.toString());

		return sSql.toString();
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public String getNomDep() {
		return nomDep;
	}

	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}
}
