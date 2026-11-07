package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.File;

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

import com.gem.sistema.business.domain.Catdgm;

import com.gem.sistema.business.domain.Fuentef;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.CatdgmRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009128DepGenFMB.
 */
@ManagedBean(name = "rF009128DepGenFMB")
@ViewScoped
public class RF009128DepGenFMB extends GenericExecuteProcedure {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RF009128DepGenFMB.class);

	/** The Constant NAME_PROCEDURE. */
	private static final String NAME_PROCEDURE = "SP_REPORTE_RF009128_DGENFUENTE";

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The mes. */
	private String mes;

	/** The xdepen. */
	private String xdepen;

	/** The nombre de la Fuente de Financiamiento. */
	private String nombreFuenteF;

	/** The xdire. */
	private String xdire;

	/** The list tc mes. */
	private List<TcMes> listTcMes;

	/** The list catdgm. */
	private List<Catdgm> listCatdgm;

	/** The list fuentf. */
	private List<Fuentef> listFuentf;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant FOCUS_BY_ROWID. */
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:preViewTxt');";

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The out. */
	Map<String, Object> out;

	/**
	 * @return the txtPreview
	 */
	public StringBuilder getTxtPreview() {
		return txtPreview;
	}

	/**
	 * @param txtPreview
	 *            the txtPreview to set
	 */
	public void setTxtPreview(StringBuilder txtPreview) {
		this.txtPreview = txtPreview;
	}

	/** The catdgm repository. */
	@ManagedProperty("#{catdgmRepository}")
	private CatdgmRepository catdgmRepository;

	/** The fuentef repository. */
	@ManagedProperty("#{fuentefRepository}")
	private FuentefRepository fuentefRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/**
	 * @return the generatePreviewService
	 */
	public GeneratePreviewService getGeneratePreviewService() {
		return generatePreviewService;
	}

	/**
	 * @param generatePreviewService
	 *            the generatePreviewService to set
	 */
	public void setGeneratePreviewService(GeneratePreviewService generatePreviewService) {
		this.generatePreviewService = generatePreviewService;
	}

	/**
	 * @return the bPreView
	 */
	public Boolean getbPreView() {
		return bPreView;
	}

	/**
	 * @param bPreView
	 *            the bPreView to set
	 */
	public void setbPreView(Boolean bPreView) {
		this.bPreView = bPreView;
	}

	/**
	 * @return the out
	 */
	public Map<String, Object> getOut() {
		return out;
	}

	/**
	 * @param out
	 *            the out to set
	 */
	public void setOut(Map<String, Object> out) {
		this.out = out;
	}

	/**
	 * @return the inputFile
	 */
	public InputStream getInputFile() {
		return inputFile;
	}

	/**
	 * @param inputFile
	 *            the inputFile to set
	 */
	public void setInputFile(InputStream inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * @return the inputFile2
	 */
	public InputStream getInputFile2() {
		return inputFile2;
	}

	/**
	 * @param inputFile2
	 *            the inputFile2 to set
	 */
	public void setInputFile2(InputStream inputFile2) {
		this.inputFile2 = inputFile2;
	}

	/**
	 * @return the newFile
	 */
	public File getNewFile() {
		return newFile;
	}

	/**
	 * @param newFile
	 *            the newFile to set
	 */
	public void setNewFile(File newFile) {
		this.newFile = newFile;
	}

	/**
	 * @return the fileNameOut
	 */
	public String getFileNameOut() {
		return fileNameOut;
	}

	/**
	 * @param fileNameOut
	 *            the fileNameOut to set
	 */
	public void setFileNameOut(String fileNameOut) {
		this.fileNameOut = fileNameOut;
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
	 * @param xdepen
	 *            the new xdepen
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
	 * @param xdire
	 *            the new xdire
	 */
	public void setXdire(String xdire) {
		this.xdire = xdire;
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
	 * @param listTcMes
	 *            the new list tc mes
	 */
	public void setListTcMes(List<TcMes> listTcMes) {
		this.listTcMes = listTcMes;
	}

	/**
	 * Gets the list catdgm.
	 *
	 * @return the list catdgm
	 */
	public List<Catdgm> getListCatdgm() {
		return listCatdgm;
	}

	/**
	 * Sets the list catdgm.
	 *
	 * @param listCatdgm
	 *            the new list catdgm
	 */
	public void setListCatdgm(List<Catdgm> listCatdgm) {
		this.listCatdgm = listCatdgm;
	}

	/**
	 * Gets the nombreFuenteF
	 * 
	 * @return variable nombreFuenteF
	 */
	public String getNombreFuenteF() {
		return nombreFuenteF;
	}

	/**
	 * Set the nombreFuenteF
	 * 
	 * @param nombreFuenteF
	 */
	public void setNombreFuenteF(String nombreFuenteF) {
		this.nombreFuenteF = nombreFuenteF;
	}

	/**
	 * Gets the list fuentf.
	 *
	 * @return the list fuentf
	 */
	public List<Fuentef> getListFuentf() {
		return listFuentf;
	}

	/**
	 * Sets the list fuentf.
	 *
	 * @param listFuentf
	 *            the new list fuentf
	 */
	public void setListFuentf(List<Fuentef> listFuentf) {
		this.listFuentf = listFuentf;
	}

	/**
	 * Gets the catdgm repository.
	 *
	 * @return the catdgm repository
	 */
	public CatdgmRepository getCatdgmRepository() {
		return catdgmRepository;
	}

	/**
	 * Sets the catdgm repository.
	 *
	 * @param catdgmRepository
	 *            the new catdgm repository
	 */
	public void setCatdgmRepository(CatdgmRepository catdgmRepository) {
		this.catdgmRepository = catdgmRepository;
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
	 * @param fuentefRepository
	 *            the new fuentef repository
	 */
	public void setFuentefRepository(FuentefRepository fuentefRepository) {
		this.fuentefRepository = fuentefRepository;
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
	 * @param tcMesRepository
	 *            the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		procedureName = NAME_PROCEDURE;

		listTcMes = tcMesRepository.findAll();
		listCatdgm = catdgmRepository.findAll();
		listFuentf = fuentefRepository.getByIdSector(this.getUserDetails().getIdSector());

		if (!CollectionUtils.isEmpty(listTcMes)) {
			mes = listTcMes.get(0).getMes();
		}

		if (!CollectionUtils.isEmpty(listCatdgm)) {
			xdepen = listCatdgm.get(0).getClave();
		}

		if (!CollectionUtils.isEmpty(listFuentf)) {
			xdire = listFuentf.get(0).getLiga();
			nombreFuenteF = listFuentf.get(0).getNombref();
		}

	}

	/**
	 * (non-Javadoc)
	 * 
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

	/**
	 * Genera query.
	 *
	 * @param idsector
	 *            the idsector
	 * @param mes
	 *            the mes
	 * @param xdepen
	 *            the xdepen
	 * @param xdire
	 *            the xdire
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
				.append(" WHERE P.IDSECTOR = " + idsector).append(" AND SUBSTR(P.CLAVE,1,3)= '" + xdepen + "'")
				.append(" AND SUBSTR(P.PROGRAMA,13,3) ='" + xdire + "'").append(" GROUP BY P.PARTIDA,")
				.append(" N.NOMGAS)T1");

		return sSql.toString();

	}

	/**
	 * Union box.
	 */
	public void unionBox() {
		nombreFuenteF = xdire.substring(4);
	}

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
	 * @throws ReportValidationException
	 *             the report validation exception
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
		}
	}

}
