package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ClasificacionObjGastoService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.GeneraTxtFilesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.Constants;
import com.gem.sistema.util.ConstantsClaveEnnum;

/**
 * @author Alfredo
 * Date: 08/04/2021
 * Changes: Se agrega opcion a pantalla para generar el reporte Trimestral y Acumulado
 * 			Se ajusta metodo del armado de la consulta
 */
@Component("clasificacionObjGastoMB2")
@Scope("view")
public class ClasificacionObjGastoMB2 extends BaseDirectReport {

	private List<TcPeriodo> listTrimestres;
	private Integer trimestre;
	private String reporte;
	private String pathName;
	InputStream stream = null;

	private String fileName;
	private StreamedContent file;

	@Value("#{clasificacionObjGastoService}")
	private ClasificacionObjGastoService clasificacionObjGastoService;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepository;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{generaTxtFilesService}")
	private GeneraTxtFilesService txtFilesService;

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

	@PostConstruct
	public void init() {
		listTrimestres = periodoRepository.findByTipoPeriodo(3);
		jasperReporteName = "ClasificacionObjGasto2";
		endFilename = jasperReporteName + ".pdf";

		if (!listTrimestres.isEmpty()) {
			trimestre = listTrimestres.get(0).getPeriodo();
		}

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Integer idSector = this.getUserDetails().getIdSector();
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		Object[] meses = getMonthsByTrimestre(trimestre);

		parameters.put("municipio", conctb.getNomDep());
		parameters.put("imagen", idSector == 1 ? conctb.getImagePathRigth() : conctb.getImagePathLeft());
		parameters.put("firstMonth", meses[0]);
		parameters.put("lastMonth", meses[1]);
		parameters.put("lastDay", meses[2]);
		parameters.put("anio", conctb.getAnoemp());

		switch (conctb.getClave().substring(0, 1)) {
		case "0":
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F01.getValue());
			parameters.put("firmaL1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F03.getValue());
			parameters.put("firmaL3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
			break;
		case "2":
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F10.getValue());
			parameters.put("firmaL1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F11.getValue());
			parameters.put("firmaL3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
			break;
		case "3":
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F07.getValue());
			parameters.put("firmaL1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F08.getValue());
			parameters.put("firmaL2", firma.getPuesto().getPuesto());
			parameters.put("firmaN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F09.getValue());
			parameters.put("firmaL3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
			break;
		case "4":
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F20.getValue());
			parameters.put("firmaL1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F21.getValue());
			parameters.put("firmaL3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());

			break;
		default:
			break;

		}

		parameters.put("sql", this.generateSQL(trimestre, this.getUserDetails().getIdSector()));

		return parameters;
	}

	public StreamedContent getFile() {

		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		fileName = "EAEPECOGLDF" + conctb.getClave() + conctb.getAnoemp() + "0" + trimestre + ".txt";
		InputStream stream = null;
		try {
			stream = new FileInputStream(this.txtFilesService.generaArtivoTxtLDFObjGasto(
					this.generateSQL(trimestre, this.getUserDetails().getIdSector()), fileName));
		} catch (FileNotFoundException ex) {

		}
		file = StreamedContents.of(stream, Constants.APPLICCTION_TXT, fileName, null);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se Generó el Archivo: " + fileName);
		return file;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSQL(Integer trimestre, Integer sector) {
		StringBuilder sql = new StringBuilder();
		int mesInicial = 0;
		int mesFinal = trimestre * 3;

		if (reporte.equals("T")) {
			mesInicial = mesFinal - 2;
		} else {
			mesInicial = 1;
		}


		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";

		for (int y = mesInicial; y <= mesFinal; y++) {
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}

		for (int y = 1; y <= 12; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION, ";

		sql.append("SELECT 1 GRUP, T1.CLVGAS, T1.NOMGAS, T1.APROBADO, (T1.AMPLIACION -T1.REDUCCIONES) AMPL_REDU, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) MODIFICADO, T1.DEVENGADO, T1.PAGADO, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO SUBEJERCICIO ")
				.append("FROM (SELECT NAT.CLVGAS, NAT.NOMGAS, ").append(auto).append(ampli).append(redu).append(ejxpa)
				.append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR AND SUBSTR(PA.PARTIDA, 3, 2) = '00' ")
				.append("WHERE  PA.IDSECTOR = ").append(sector)
				// .append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append(" AND SUBSTR(PA.PROGRAMA,13,3)>='101' AND SUBSTR(PA.PROGRAMA,13,3)<='113' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='201' AND SUBSTR(PA.PROGRAMA,13,3)<='202' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC ) T1 UNION ALL ")
				.append("SELECT 2 GRUP, T1.CLVGAS, T1.NOMGAS, T1.APROBADO, (T1.AMPLIACION -T1.REDUCCIONES) AMPL_REDU, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) MODIFICADO, T1.DEVENGADO, T1.PAGADO, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO SUBEJERCICIO "
						+ "FROM (SELECT NAT.CLVGAS, NAT.NOMGAS, ")
				.append(auto).append(ampli).append(redu).append(ejxpa).append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR AND SUBSTR(PA.PARTIDA, 3, 2) = '00' ")
				.append("WHERE  PA.IDSECTOR = ").append(sector)
				// .append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append(" AND SUBSTR(PA.PROGRAMA,13,3)>='203' AND SUBSTR(PA.PROGRAMA,13,3)<='225' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='114' AND SUBSTR(PA.PROGRAMA,13,3)<='116' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC) T1 ORDER BY GRUP, CLVGAS");

		return sql.toString();
	}

	public Object[] getMonthsByTrimestre(Integer trimestre) {
		int mesInicial = 0;
		int mesFinal = trimestre * 3;

		if (reporte.equals("T")) {
			mesInicial = mesFinal - 2;
		} else {
			mesInicial = 1;
		}

		Object[] meses = { periodoRepository.findByTipoPeriodoAndPeriodo(1, mesInicial).getDescripcion(),
				periodoRepository.findByTipoPeriodoAndPeriodo(1, mesFinal).getDescripcion(), getLastDay(mesFinal) };

		return meses;
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

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}

	public TcPeriodoRepositoy getPeriodoRepository() {
		return periodoRepository;
	}

	public void setPeriodoRepository(TcPeriodoRepositoy periodoRepository) {
		this.periodoRepository = periodoRepository;
	}

	public ClasificacionObjGastoService getClasificacionObjGastoService() {
		return clasificacionObjGastoService;
	}

	public void setClasificacionObjGastoService(ClasificacionObjGastoService clasificacionObjGastoService) {
		this.clasificacionObjGastoService = clasificacionObjGastoService;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public GeneraTxtFilesService getTxtFilesService() {
		return txtFilesService;
	}

	public void setTxtFilesService(GeneraTxtFilesService txtFilesService) {
		this.txtFilesService = txtFilesService;
	}

}
