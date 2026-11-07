package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TcTabularSueldosTrimestral;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.TcTabuladorSueldosTrimestralService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.datamodel.DataModelGeneric;

@Component("tabuladorSueldosTrimestralMB")
@Scope("view")
public class TabuladorSueldosTrimestralMB extends BaseDirectReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2809561152644535426L;
	private static final String CLAVE_REPORT = "RPT_ATBULADOR_TRIMESTRAL_SUELDOS";
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-pencil').eq(";
	/** Habilitar el modo de edicion. */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:objects:%0$s:pustoFuncional');";
	private String puesto;
	private String nivelSalarial;
	private String noPlaza;
	private String categoira;
	private String catSindicalizada;
	private String cateEventual;
	private Integer trimestre;
	private List<Integer> listTrimestre;
	private List<TcTabularSueldosTrimestral> listTabulador;

	@Value("#{tcTabuladorSueldosTrimestralService}")
	private TcTabuladorSueldosTrimestralService tabuladorService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	private DataModelGeneric<TcTabularSueldosTrimestral> dataModeler;

	private TcTabularSueldosTrimestral selected;

	@PostConstruct
	public void init() {
		listTrimestre = Arrays.asList(1, 2, 3, 4);
		trimestre = listTrimestre.get(0);
		this.loadTabulador();
		dataModeler = new DataModelGeneric<TcTabularSueldosTrimestral>(listTabulador);

		jasperReporteName = "tabulador-sueldos-trimestral";
		endFilename = jasperReporteName + ".pdf";
	}

	public void loadTabulador() {
		listTabulador = this.tabuladorService.findAll();
		this.dataModeler = new DataModelGeneric<TcTabularSueldosTrimestral>(listTabulador);
	}

	public void limpiar() {
		this.loadTabulador();
		this.puesto = StringUtils.EMPTY;
		this.noPlaza = null;
		this.nivelSalarial = null;
		this.categoira = null;
		this.catSindicalizada = null;
	}

	public void predicateFilters() {
		if (StringUtils.isNoneBlank(puesto))
			listTabulador = listTabulador.stream().filter(tab -> tab.getPuestoFuncional().startsWith(puesto))
					.map(tab -> tab).collect(Collectors.toList());
		else if (StringUtils.isNoneBlank(nivelSalarial))
			listTabulador = listTabulador = listTabulador.stream()
					.filter(tab -> tab.getNivelSalarial().equals(Integer.valueOf(nivelSalarial))).map(tab -> tab)
					.collect(Collectors.toList());
		else if (StringUtils.isNoneBlank(noPlaza))
			listTabulador = listTabulador = listTabulador.stream()
					.filter(tab -> tab.getNoPlaza().equals(Integer.valueOf(noPlaza))).map(tab -> tab)
					.collect(Collectors.toList());
		else if (StringUtils.isNoneBlank(categoira))
			listTabulador = listTabulador = listTabulador.stream()
					.filter(tab -> tab.getCategoriaConfianza().equals(Integer.valueOf(categoira))).map(tab -> tab)
					.collect(Collectors.toList());
		else if (StringUtils.isNoneBlank(catSindicalizada))
			listTabulador = listTabulador = listTabulador.stream()
					.filter(tab -> tab.getCategoriaSindicalizado().equals(Integer.valueOf(catSindicalizada)))
					.map(tab -> tab).collect(Collectors.toList());

		dataModeler = new DataModelGeneric<TcTabularSueldosTrimestral>(listTabulador);
	}

	public void addNewElement() {
		listTabulador.add(0, new TcTabularSueldosTrimestral());
		dataModeler.setListT(listTabulador);
		activateRowEdition(0);
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			this.tabuladorService.saveFile(event.getFile().getFileName(), event.getFile().getInputStream());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Infor",
					"La información se catrgo correctamente");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		this.tabuladorService.delteAll();
	}

	public void deleteById(Integer id) {
		this.tabuladorService.deleteById(Long.valueOf(id));
	}

	public void onRowEdit(RowEditEvent event) {
		TcTabularSueldosTrimestral tabulador = (TcTabularSueldosTrimestral) event.getObject();
		this.tabuladorService.saveObj(tabulador);

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public List<Integer> getListTrimestre() {
		return listTrimestre;
	}

	public void setListTrimestre(List<Integer> listTrimestre) {
		this.listTrimestre = listTrimestre;
	}

	public TcTabuladorSueldosTrimestralService getTabuladorService() {
		return tabuladorService;
	}

	public void setTabuladorService(TcTabuladorSueldosTrimestralService tabuladorService) {
		this.tabuladorService = tabuladorService;
	}

	public List<TcTabularSueldosTrimestral> getListTabulador() {
		return listTabulador;
	}

	public void setListTabulador(List<TcTabularSueldosTrimestral> listTabulador) {
		this.listTabulador = listTabulador;
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Integer mes = trimestre == 1 ? 3 : trimestre == 2 ? 6 : trimestre == 3 ? 9 : 12;
		String mesInicial = mes == 3 ? "ENERO" : mes == 6 ? "ABRIL" : mes == 9 ? "JULIO" : "OCTUBRE";
		String mesFinal = mes == 3 ? "MARZO" : mes == 6 ? "JUNIO" : mes == 9 ? "SEPTIEMBRE" : "DICIEMBRE";
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		TcMes descriMes = tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0"));
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		parameters.put("lastDay", getLastDayByAnoEmp(Integer.valueOf(mes), conctb.getAnoemp()));
		parameters.put("pInicial", descriMes.getDescripcion());
		parameters.put("anio", conctb.getAnoemp().toString());
		parameters.put("organismo", conctb.getNomDep());
		parameters.put("mInicial", mesInicial);
		parameters.put("mFinal", mesFinal);
		parameters.put("mFinal", mesFinal);
		parameters.put("pImg1", conctb.getImagePathLeft());
		parameters.put("pImg2", conctb.getImagePathRigth());
		parameters.put("pClave", conctb.getClave().substring(1));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));
		endFilename = getNameFileReport(mes, conctb, reporte);

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
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

	public DataModelGeneric<TcTabularSueldosTrimestral> getDataModeler() {
		return dataModeler;
	}

	public void setDataModeler(DataModelGeneric<TcTabularSueldosTrimestral> dataModeler) {
		this.dataModeler = dataModeler;
	}

	public TcTabularSueldosTrimestral getSelected() {
		return selected;
	}

	public void setSelected(TcTabularSueldosTrimestral selected) {
		this.selected = selected;
	}

	public void activateRowEdition(final int index) {
		LOGGER.info(":: Cerrar cuadro de dialogo ");
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		PrimeFaces.current().executeScript(text.toString());
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getNivelSalarial() {
		return nivelSalarial;
	}

	public void setNivelSalarial(String nivelSalarial) {
		this.nivelSalarial = nivelSalarial;
	}

	public String getNoPlaza() {
		return noPlaza;
	}

	public void setNoPlaza(String noPlaza) {
		this.noPlaza = noPlaza;
	}

	public String getCategoira() {
		return categoira;
	}

	public void setCategoira(String categoira) {
		this.categoira = categoira;
	}

	public String getCatSindicalizada() {
		return catSindicalizada;
	}

	public void setCatSindicalizada(String catSindicalizada) {
		this.catSindicalizada = catSindicalizada;
	}

	public String getCateEventual() {
		return cateEventual;
	}

	public void setCateEventual(String cateEventual) {
		this.cateEventual = cateEventual;
	}

}
