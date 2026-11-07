
package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.List;
import java.util.Map;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009126MB.
 */
@ManagedBean(name = "rF009126MB")
@ViewScoped
public class RF009126MB extends GenericExecuteProcedure {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	/** Constante para utilizar el log de la aplicacion. */

	private static final String MESSAGE_ERROR = "El Mes Inical debe ser menor o igual al Mes Final";

	/** The Constant NAME_PROCEDURE. */
	private static final String NAME_PROCEDURE = "SP_REPORTE_RF009126_DEPPROG";

	/** The mes. */
	private String mes;

	/** The mes final. */
	private String mesFinal;

	/** The list mes. */
	private List<TcMes> listMes;
	// private List<String> listClave;
	/** The lis mes fin. */
	// private List<Xcatpro> listPrograma;
	private List<TcMes> lisMesFin;

	/** The conctb. */
	private Conctb conctb;

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The bandera. */
	private Boolean bandera = Boolean.FALSE;

	// @ManagedProperty("#{xcatproRepository}") // aqui trae el ABC
	// private XcatproRepository xcatproRepository;

	/** The tcmes repository. */
	@ManagedProperty("#{tcMesRepository}") // aqui trae el ABC
	private TcMesRepository tcmesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	// Generamos Getters and Seters

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes
	 *            the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the mes final.
	 *
	 * @return the mes final
	 */
	public String getMesFinal() {
		return mesFinal;
	}

	/**
	 * Sets the mes final.
	 *
	 * @param mesFinal
	 *            the new mes final
	 */
	public void setMesFinal(String mesFinal) {
		this.mesFinal = mesFinal;
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes
	 *            the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	/**
	 * Gets the lis mes fin.
	 *
	 * @return the lis mes fin
	 */
	public List<TcMes> getLisMesFin() {
		return lisMesFin;
	}

	/**
	 * Sets the lis mes fin.
	 *
	 * @param lisMesFin
	 *            the new lis mes fin
	 */
	public void setLisMesFin(List<TcMes> lisMesFin) {
		this.lisMesFin = lisMesFin;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb
	 *            the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the file txt.
	 *
	 * @return the file txt
	 */
	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	/**
	 * Sets the file txt.
	 *
	 * @param fileTxt
	 *            the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/**
	 * Gets the tcmes repository.
	 *
	 * @return the tcmes repository
	 */
	public TcMesRepository getTcmesRepository() {
		return tcmesRepository;
	}

	/**
	 * Sets the tcmes repository.
	 *
	 * @param tcmesRepository
	 *            the new tcmes repository
	 */
	public void setTcmesRepository(TcMesRepository tcmesRepository) {
		this.tcmesRepository = tcmesRepository;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository
	 *            the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository
	 *            the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the bandera.
	 *
	 * @return the bandera
	 */
	public Boolean getBandera() {
		return bandera;
	}

	/**
	 * Sets the bandera.
	 *
	 * @param bandera
	 *            the new bandera
	 */
	public void setBandera(Boolean bandera) {
		this.bandera = bandera;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		procedureName = NAME_PROCEDURE;

		// llenar la lista
		listMes = tcmesRepository.findAll();
		// listClave =
		// xcatproRepository.getClaveById(this.getUserDetails().getIdSector());
		// listPrograma = xcatproRepository.getClave(listClave.get(0),
		// this.getUserDetails().getIdSector());

		// se inicializa las listas
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

		// if (!CollectionUtils.isEmpty(listClave)) {
		// clave = listClave.get(0);
		// programa = listPrograma.get(0).getClvfun() +
		// listPrograma.get(0).getClvfin();
		// }

		lisMesFin = tcmesRepository.findAll();
		if (!CollectionUtils.isEmpty(lisMesFin))
			mesFinal = lisMesFin.get(0).getMes();

	}

	/**
	 * Checks if is bandera.
	 *
	 * @return true, if is bandera
	 */
	public boolean isBandera() {
		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		parameters.addValue("i_mes_ini", Integer.valueOf(mes));
		parameters.addValue("i_mes_fin", Integer.valueOf(mesFinal));
		parameters.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameters.addValue("i_sql", this.generateQueryTxt(Integer.valueOf(mes), Integer.valueOf(mesFinal),
				this.getUserDetails().getIdSector()));
		parameters.addValue("i_nombreMun", this.getUserDetails().getMunicipio());

		return parameters;
	}

	/** The out parameters. */
	Map<String, Object> outParameters;

	/** The stream. */
	InputStream stream = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());

		if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(new File(outParameters.get("O_RUTA_FILE").toString() + "/"
						+ outParameters.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt",
					outParameters.get("O_NAME_FILE").toString());

		} else {
			generateNotificationFront(SEVERITY_INFO, "Info!", outParameters.get("O_MSG_ERROR").toString());

		}

	}

	// Se crea metodo que retornara el query al SP

	/**
	 * Generate query txt.
	 *
	 * @param mesIni
	 *            the mes ini
	 * @param mesfin
	 *            the mesfin
	 * @param sector
	 *            the sector
	 * @return the string
	 */
	public String generateQueryTxt(Integer mesIni, Integer mesfin, Integer sector) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		StringBuilder c = new StringBuilder();
		StringBuilder d = new StringBuilder();
		StringBuilder f = new StringBuilder();
		StringBuilder g = new StringBuilder();
		StringBuilder cpt = new StringBuilder();
		a.append(" P.AUTO1_1 ");
		b.append(" P.EJPA1_" + mesIni + " ");
		c.append(" P.EJXPA1_" + mesIni + " ");
		d.append(" P.TOEJE1_" + mesIni + " ");
		f.append(" P.AMPLI1_" + mesIni + " ");
		g.append(" P.REDU1_" + mesIni + " ");
		cpt.append(" P.COMPRO1_" + mesIni + " ");

		for (int i = 2; i < 13; i++) {
			a.append("+ P.AUTO1_" + i + " ");
		}

		for (int i = mesIni + 1; i <= mesfin; i++) {
			b.append("+ P.EJPA1_" + i + " ");
			c.append("+ P.EJXPA1_" + i + " ");
			d.append("+ P.TOEJE1_" + i + " ");
			f.append("+ P.AMPLI1_" + i + " ");
			g.append("+ P.REDU1_" + i + " ");
			cpt.append("+ P.COMPRO1_" + i + " ");
		}

		sSql.append(" SELECT T1.CLAVE,").append(" T1.PROGRAMA, ").append(" T1.PARTIDA,").append(" T1.DESCRIPCION,")
				.append(" T1.A APROBADO,").append(" T1.F AMPLIACION,").append(" T1.G REDUCCION,")
				.append(" (T1.A+T1.F-T1.G) MODIFICADO,").append(" (T1.CPT)COMPROMETIDO,").append(" T1.C DEVENGADO,")
				.append(" T1.B PAGADO,").append(" T1.D EJERCIDO,").append(" ((T1.A +T1.F-T1.G)-T1.D) POR_EJERCER")
				.append(" FROM (SELECT  P.CLAVE CLAVE,").append(" P.PROGRAMA,").append(" P.PARTIDA PARTIDA, ")
				.append(" N.NOMGAS DESCRIPCION,").append(" SUM(" + a + ")A,").append(" SUM(" + b + " )B,")
				.append(" SUM( " + c + " )C,").append(" SUM(" + d + " )D,").append(" SUM(" + f + ")F, ")
				.append(" SUM( " + g + " )G,").append(" SUM( " + cpt + ")CPT").append(" FROM PASO P  ")
				.append(" JOIN NATGAS N ON N.CLVGAS= P.PARTIDA AND N.IDSECTOR=P.IDSECTOR ")
				.append(" JOIN XCATPRO X ON   X.CLVDEP = P.CLAVE  AND  X.CLVFUN||X.CLVFIN= P.PROGRAMA AND X.SECTORID = P.IDSECTOR")
				.append(" WHERE P.IDSECTOR = " + sector).append(" AND SUBSTR(P.PARTIDA,4,1)<>'0'")
				.append(" GROUP BY P.CLAVE ,").append(" P.PROGRAMA,").append(" P.PARTIDA ,").append(" N.NOMGAS ")
				.append(" ORDER BY P.CLAVE,").append(" P.PROGRAMA,").append(" P.PARTIDA )T1");

		return sSql.toString();
	}

	// Metodo para validacion de meses mes inicial no sea mayor a mes final

	/**
	 * On change valida meses.
	 *
	 * @throws ReportValidationException
	 *             the report validation exception
	 */
	public void onChangeValidaMeses() throws ReportValidationException {
		fileTxt = null;
		if (Integer.valueOf(mes) > Integer.valueOf(mesFinal)) {
			generateNotificationFront(SEVERITY_INFO, "Info", MESSAGE_ERROR);

		} else {

			outParameters = this.executePlService.callProcedure(procedureName, this.getParametersReports());

			if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) > 0) {
				try {
					stream = new FileInputStream(new File(outParameters.get("O_RUTA_FILE").toString() + "/"
							+ outParameters.get("O_NAME_FILE").toString()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileTxt = new DefaultStreamedContent(stream, "application/txt",
						outParameters.get("O_NAME_FILE").toString());

			} else {
				generateNotificationFront(SEVERITY_INFO, "Info!", outParameters.get("O_MSG_ERROR").toString());

			}

		}

	}

	public void downloadTxt() throws ReportValidationException {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mesFinal), null,
					this.getUserDetails().getIdSector()) == Boolean.TRUE) {
				this.downLoadFile();
				RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
			}
		}
	}

	public void validateMonth() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
	}

	public void validateMonthEnd() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mesFinal), null, this.getUserDetails().getIdSector());
	}

}
