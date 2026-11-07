package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.GeneraTxtFilesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.Constants;
import com.gem.sistema.util.ConstantsClaveEnnum;

@ManagedBean(name = "clasificacionFunMB")
@ViewScoped
public class ClasificacionFunMB extends BaseDirectReport {

	private List<TcPeriodo> listTrimestres;
	private StreamedContent file;
	private Integer trimestre;
	private String fileName;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{generaTxtFilesService}")
	private GeneraTxtFilesService txtFilesService;

	@PostConstruct
	public void init() {
		listTrimestres = tcPeriodoRepositoy.findByTipoPeriodo(3);
		jasperReporteName = "EdoAnaEjerPreEgreDet";
		endFilename = jasperReporteName + ".pdf";

		if (!listTrimestres.isEmpty()) {
			trimestre = listTrimestres.get(0).getPeriodo();
		}

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Object[] meses = getMonthsByTrimestre(trimestre);
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = null;

		parameters.put("municipioName", conctb.getNomDep());
		parameters.put("pImagen1", conctb.getImagePathLeft());
		parameters.put("pImagen2", conctb.getImagePathRigth());
		parameters.put("mesInicial", meses[0]);
		parameters.put("mesFinal", meses[1]);
		parameters.put("lastDay", meses[2]);
		parameters.put("anio", conctb.getAnoemp());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F07.getValue());
		parameters.put("pL1", firma.getPuesto().getPuesto());
		parameters.put("pN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F08.getValue());
		parameters.put("pL2", firma.getPuesto().getPuesto());
		parameters.put("pN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F09.getValue());
		parameters.put("pL3", firma.getPuesto().getPuesto());
		parameters.put("pN3", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F10.getValue());
		parameters.put("pL4", firma.getPuesto().getPuesto());
		parameters.put("pN4", firma.getNombre());
		

		parameters.put("sql", this.generateSQL(trimestre, this.getUserDetails().getIdSector()));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSQL(Integer trimestre, Integer sector) {

		StringBuilder sql = new StringBuilder();
		int mesInicial = (trimestre * 3) - 2;
		int mesFinal = trimestre * 3;

		String auto = "(";
		String ejpa = "(";
		String redu = "(";
		String ejxpa = "(";
		String ampli = "(";

		for (int y = mesInicial; y <= mesFinal; y++) {
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}
		
		for (int y = 1; y <= 12; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) AUTO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) EJPA, ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDU ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) EJXPA, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLI, ";

		sql.append("SELECT GRUP, GRUPO_GASTO, NOMBRE, GASTO, NAME, ").append(" AUTO, AMPLI, REDU, ")
				.append(" (T2.AMPLI -T2.REDU) AMPLI_REDU, ").append(" (T2.AUTO + T2.AMPLI -T2.REDU) MODIFICADO, ")
				.append(" T2.EJXPA, T2.EJPA, ")
				.append(" (T2.AUTO + T2.AMPLI -T2.REDU) - T2.EJXPA SUBEJERCICIO  FROM (	SELECT  1 GRUP, GRUPO_GASTO, NOMBRE, GASTO, NAME, ")
				.append("SUM(AUTO) AUTO, SUM(EJPA) EJPA, SUM(EJXPA) EJXPA, SUM(AMPLI) AMPLI, SUM(REDU) REDU ")
				.append("FROM ( SELECT  SUBSTR(PA.PROGRAMA,1,2) GRUPO_GASTO, MU.CAMPO6 NOMBRE, SUBSTR(PA.PROGRAMA,1,4) GASTO, MP.CAMPO6 NAME, ")
				.append(auto).append(ejpa).append(ejxpa).append(ampli).append(redu)
				.append(" FROM PASO PA, MUNINEP MU, MUNINEP MP ")
				.append("WHERE 	MP.IDSECTOR = MU.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,2) = MU.CAMPO7 ")
				.append("AND PA.IDSECTOR = MU.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,4) = MP.CAMPO7 AND ")
				.append("PA.IDSECTOR = MU.IDSECTOR AND PA.IDSECTOR = ").append(sector)
				.append(" AND SUBSTR(PA.PARTIDA,4,1) != '0' ")
				.append("AND (SUBSTR(PA.PROGRAMA,13,3) BETWEEN 261 AND 262 OR SUBSTR(PA.PROGRAMA,13,3) BETWEEN 264 AND 265) )T1 ")
				.append("GROUP BY GRUPO_GASTO, NOMBRE, GASTO, NAME )T2 UNION ALL SELECT GRUP, GRUPO_GASTO, NOMBRE, GASTO, NAME, ")
				.append(" AUTO, AMPLI, REDU, ").append(" (T2.AMPLI -T2.REDU) AMPLI_REDU, ")
				.append(" (T2.AUTO + T2.AMPLI -T2.REDU) MODIFICADO, ").append(" T2.EJXPA, T2.EJPA, ")
				.append(" (T2.AUTO + T2.AMPLI -T2.REDU) - T2.EJXPA SUBEJERCICIO  FROM ( ")
				.append("SELECT  2 GRUP, GRUPO_GASTO, NOMBRE, GASTO, NAME, SUM(AUTO) AUTO, SUM(EJPA) EJPA, SUM(EJXPA) EJXPA, SUM(AMPLI) AMPLI, SUM(REDU) REDU ")
				.append("FROM (SELECT  SUBSTR(PA.PROGRAMA,1,2) GRUPO_GASTO, MU.CAMPO6 NOMBRE, SUBSTR(PA.PROGRAMA,1,4) GASTO, MP.CAMPO6 NAME, ")
				.append(auto).append(ejpa).append(ejxpa).append(ampli).append(redu)
				.append(" FROM PASO PA, MUNINEP MU, MUNINEP MP ")
				.append("WHERE 	MP.IDSECTOR = MU.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,2) = MU.CAMPO7 ")
				.append("AND PA.IDSECTOR = MU.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,4) = MP.CAMPO7 AND ")
				.append("PA.IDSECTOR = MU.IDSECTOR AND PA.IDSECTOR = ").append(sector)
				.append(" AND SUBSTR(PA.PARTIDA,4,1) != '0' ")
				.append("AND (SUBSTR(PA.PROGRAMA,13,3) BETWEEN 261 AND 263 OR SUBSTR(PA.PROGRAMA,13,3) BETWEEN 264 AND 265) )T1 ")
				.append("GROUP BY GRUPO_GASTO, NOMBRE, GASTO, NAME )T2 ORDER BY GRUP, GRUPO_GASTO, GASTO");

		System.out.println("Query-Z>" + sql.toString());
		return sql.toString();
	}

	public Object[] getMonthsByTrimestre(Integer trimestre) {
		Integer mesFinal = trimestre * 3;
		Integer mesInicial = mesFinal - 2;
		Object[] meses = {
				tcMesRepository.findByMes(org.apache.commons.lang3.StringUtils.leftPad(mesInicial.toString(), 2, "0"))
						.getDescripcion(),
				tcMesRepository.findByMes(org.apache.commons.lang3.StringUtils.leftPad(mesFinal.toString(), 2, "0"))
						.getDescripcion(),
				getLastDay(mesFinal) };

		return meses;
	}

	public StreamedContent getFile() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		fileName = "EAEPECFLDF" + conctb.getClave() + conctb.getAnoemp() + "0" + trimestre + ".txt";
		InputStream stream = null;
		try {
			stream = new FileInputStream(this.txtFilesService.generaArtivoTxtLDFClasifFuncional(
					this.generateSQL(trimestre, this.getUserDetails().getIdSector()), fileName));
		} catch (FileNotFoundException ex) {

		}
		file = new DefaultStreamedContent(stream, Constants.APPLICCTION_TXT, fileName);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se Generó el Archivo: " + fileName);
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<TcPeriodo> getListTrimestres() {
		return listTrimestres;
	}

	public void setListTrimestres(List<TcPeriodo> listTrimestres) {
		this.listTrimestres = listTrimestres;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
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

	public GeneraTxtFilesService getTxtFilesService() {
		return txtFilesService;
	}

	public void setTxtFilesService(GeneraTxtFilesService txtFilesService) {
		this.txtFilesService = txtFilesService;
	}

}
