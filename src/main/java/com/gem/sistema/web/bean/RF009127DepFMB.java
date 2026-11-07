package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.FileInputStream;
import java.io.File;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Fuentef;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009127DepFMB.
 */
@ManagedBean(name = "rF009127DepFMB")
@ViewScoped

public class RF009127DepFMB extends GenericExecuteProcedure {

	/** The Constant NAME_PROCEDURE. */
	private static final String NAME_PROCEDURE = "SP_REPORTE_RF009127_DEPFUENTE";
	
	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";
	
	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";
	
	/** The Constant FOCUS_BY_ROWID. */
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:preViewTxt');";
	
	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";
	
	/** The file txt. */
	private StreamedContent fileTxt;

	/** The mes. */
	private String mes;
	
	/** The xdepen. */
	private String xdepen;
	
	/** The xdire. */
	private String xdire;
	
	/** The conctb. */
	private Conctb conctb;
	
	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;
	
	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The list catdep. */
	private List<Catdep> listCatdep;
	
	/** The list fuentef. */
	private List<Fuentef> listFuentef;
	
	/** The list tc mes. */
	private List<TcMes> listTcMes;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The fuentef repository. */
	@ManagedProperty("#{fuentefRepository}")
	private FuentefRepository fuentefRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
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
	 * @param fileTxt the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

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
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the xdepen.
	 *
	 * @return the xdepen
	 */
	public String getXdepen() {
		return xdepen;
	}

	/**
	 * Sets the xdepen.
	 *
	 * @param xdepen the new xdepen
	 */
	public void setXdepen(String xdepen) {
		this.xdepen = xdepen;
	}

	/**
	 * Gets the xdire.
	 *
	 * @return the xdire
	 */
	public String getXdire() {
		return xdire;
	}

	/**
	 * Sets the xdire.
	 *
	 * @param xdire the new xdire
	 */
	public void setXdire(String xdire) {
		this.xdire = xdire;
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
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the list catdep.
	 *
	 * @return the list catdep
	 */
	public List<Catdep> getListCatdep() {
		return listCatdep;
	}

	/**
	 * Sets the list catdep.
	 *
	 * @param listCatdep the new list catdep
	 */
	public void setListCatdep(List<Catdep> listCatdep) {
		this.listCatdep = listCatdep;
	}

	/**
	 * Gets the list fuentef.
	 *
	 * @return the list fuentef
	 */
	public List<Fuentef> getListFuentef() {
		return listFuentef;
	}

	/**
	 * Sets the list fuentef.
	 *
	 * @param listFuentef the new list fuentef
	 */
	public void setListFuentef(List<Fuentef> listFuentef) {
		this.listFuentef = listFuentef;
	}

	/**
	 * Gets the list tc mes.
	 *
	 * @return the list tc mes
	 */
	public List<TcMes> getListTcMes() {
		return listTcMes;
	}

	/**
	 * Sets the list tc mes.
	 *
	 * @param listTcMes the new list tc mes
	 */
	public void setListTcMes(List<TcMes> listTcMes) {
		this.listTcMes = listTcMes;
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

	/**
	 * Gets the fuentef repository.
	 *
	 * @return the fuentef repository
	 */
	public FuentefRepository getFuentefRepository() {
		return fuentefRepository;
	}

	/**
	 * Sets the fuentef repository.
	 *
	 * @param fuentefRepository the new fuentef repository
	 */
	public void setFuentefRepository(FuentefRepository fuentefRepository) {
		this.fuentefRepository = fuentefRepository;
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
	 * @param firmasRepository the new firmas repository
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
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the b pre view.
	 *
	 * @return the b pre view
	 */
	public Boolean getbPreView() {
		return bPreView;
	}

	/**
	 * Sets the b pre view.
	 *
	 * @param bPreView the new b pre view
	 */
	public void setbPreView(Boolean bPreView) {
		this.bPreView = bPreView;
	}

	/**
	 * Gets the txt preview.
	 *
	 * @return the txt preview
	 */
	public StringBuilder getTxtPreview() {
		return txtPreview;
	}

	/**
	 * Sets the txt preview.
	 *
	 * @param txtPreview the new txt preview
	 */
	public void setTxtPreview(StringBuilder txtPreview) {
		this.txtPreview = txtPreview;
	}

	/**
	 * Gets the generate preview service.
	 *
	 * @return the generate preview service
	 */
	public GeneratePreviewService getGeneratePreviewService() {
		return generatePreviewService;
	}

	/**
	 * Sets the generate preview service.
	 *
	 * @param generatePreviewService the new generate preview service
	 */
	public void setGeneratePreviewService(GeneratePreviewService generatePreviewService) {
		this.generatePreviewService = generatePreviewService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		procedureName = NAME_PROCEDURE;

		listTcMes = tcMesRepository.findAll();
		listCatdep = catdepRepository.findAllByIdsectorOrderByClvdep(this.getUserDetails().getIdSector());
		listFuentef = fuentefRepository.getByIdSector(this.getUserDetails().getIdSector());

		if (!CollectionUtils.isEmpty(listTcMes)) {
			mes = listTcMes.get(0).getMes();
		}

		if (!CollectionUtils.isEmpty(listCatdep)) {
			xdepen = listCatdep.get(0).getClvdep();
		}

		if (!CollectionUtils.isEmpty(listFuentef)) {
			xdire = listFuentef.get(0).getLiga();
		}

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		parameters.addValue("i_mes", Integer.valueOf(mes));
		parameters.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameters.addValue("i_sql", this.generaQuery(this.getUserDetails().getIdSector(), Integer.valueOf(mes), xdepen,
				xdire.substring(0, 3)));
		parameters.addValue("i_xdepen", xdepen);
		parameters.addValue("i_xdire", xdire);
		parameters.addValue("i_nombreMun", this.getUserDetails().getMunicipio());

		return parameters;
	}

	/** The out parameters. */
	Map<String, Object> outParameters;
	
	/** The stream. */
	InputStream stream = null;

	/* (non-Javadoc)
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

	/**
	 * Genera query.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @param xdepen the xdepen
	 * @param xdire the xdire
	 * @return the string
	 */
	public String generaQuery(Integer idsector, Integer mes, String xdepen, String xdire) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder ejempa = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder toeje = new StringBuilder();
		StringBuilder ampli = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder compro = new StringBuilder();

		sSql.append("SELECT T1.PARTIDA,").append("T1.DESCRIPCION,").append("T1.A APROBADO,").append("T1.F AMPLIACION,")
				.append("T1.G REDUCCION,").append("(T1.A+T1.F-T1.G) MODIFICADO,").append("(T1.CPT)COMPROMETIDO,")
				.append("T1.C DEVENGADO,").append("T1.B PAGADO,").append("T1.D EJERCIDO,")
				.append("((T1.A +T1.F-T1.G)-T1.D) POR_EJERCER").append(" FROM (SELECT P.PARTIDA PARTIDA,")
				.append("N.NOMGAS DESCRIPCION,")
				.append("SUM(P.AUTO1_1 + P.AUTO1_2 + P.AUTO1_3 + P.AUTO1_4 + P.AUTO1_5 + P.AUTO1_6 + P.AUTO1_7 + P.AUTO1_8 + P.AUTO1_9 + P.AUTO1_10 + P.AUTO1_11 + P.AUTO1_12)A,");

		for (int i = 1; i <= mes; i++) {
			ejempa.append(" P.EJPA1_" + i + "+");
			ejxpa.append(" P.EJXPA1_" + i + "+");
			toeje.append(" P.TOEJE1_" + i + "+");
			ampli.append(" P.AMPLI1_" + i + "+");
			redu.append(" P.REDU1_" + i + "+");
			compro.append(" P.COMPRO1_" + i + "+");
		}
		sSql.append(" SUM( ").append(ejempa.substring(1, ejempa.length() - 1)).append(")B,").append(" SUM( ")
				.append(ejxpa.substring(1, ejxpa.length() - 1)).append(")C,").append(" SUM( ")
				.append(toeje.substring(1, toeje.length() - 1)).append(")D,").append(" SUM( ")
				.append(ampli.substring(1, ampli.length() - 1)).append(")F,").append(" SUM( ")
				.append(redu.substring(1, redu.length() - 1)).append(")G,").append(" SUM( ")
				.append(compro.substring(1, compro.length() - 1)).append(")CPT ");

		sSql.append(" FROM PASO P JOIN NATGAS N ON N.CLVGAS= P.PARTIDA").append(" AND N.IDSECTOR=P.IDSECTOR")
				.append(" WHERE P.IDSECTOR = " + idsector).append(" AND P.CLAVE = '" + xdepen + "'")
				.append(" AND SUBSTR(P.PROGRAMA,13,3) ='" + xdire + "'").append(" GROUP BY P.PARTIDA,")
				.append(" N.NOMGAS)T1");

		return sSql.toString();

	}

	/** The out. */
	Map<String, Object> out;
	
	/** The input file. */
	InputStream inputFile = null;
	
	/** The input file 2. */
	InputStream inputFile2 = null;
	
	/** The new file. */
	File newFile = null;
	
	/** The target. */
	File target = null;
	
	/** The file name out. */
	String fileNameOut = "";

	/**
	 * Pre view.
	 *
	 * @throws ReportValidationException the report validation exception
	 */
	public void preView() throws ReportValidationException {
		this.setbPreView(Boolean.TRUE);
		out = new HashMap<String, Object>();
		out = executePlService.callProcedure(procedureName, getParametersReports());

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			fileNameOut = "/gem/reportes/" + UUID.randomUUID() + ".txt";
			newFile = new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString());
			target = new File(fileNameOut);
			try {
				ConvertCharsetUtils.transform(newFile, charSetUFT, target, charSetISO);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			txtPreview = this.generatePreviewService.generatePreview(fileNameOut);
			RequestContext.getCurrentInstance().execute(FOCUS_BY_ROWID);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		}
	}
	
	public void downloadTxt() throws ReportValidationException {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.downLoadFile();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}
	}

	public void validateMonth() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
	}

	public void viewTxt() throws ReportValidationException {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.preView();

		}
	}
	

}
