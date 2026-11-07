package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.StringUtils.validateStringWithRegex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcClave;
import com.gem.sistema.business.domain.TcPuesto;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcClaveRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.util.CopyFileUtil;
import com.gem.sistema.web.security.model.GemUser;

/**
 * @author Alfredo Neri
 *
 */
@Component("datosImpresionMB")
@Scope("view")
public class DatosImpresionMB extends AbstractMB {
	private static final String PATH_IMG_FILE = "/gem/img/";
	private static final String REGEX_PUESTO = "^\\s*([a-zA-Z. áÁéÉíÍóÓúÚñÑ]*)\\s*$";
	private static final String EDIT_PUESTO_ROW_JQUERY = "jQuery('#datosimpresion\\\\:tabs\\\\:singleDT span.ui-icon-pencil').eq(%1$s).each(function(){jQuery(this).click()});";
	private static final String EDIT_FIRMA_ROW_JQUERY = "jQuery('#datosimpresion\\\\:tabs\\\\:tableFirmas span.ui-icon-pencil').eq(%1$s).each(function(){jQuery(this).click()});";
	private static final String FOCUS_FLOW_PUESTO_ROW_JQUERY = "PrimeFaces.focus('datosimpresion:tabs:tableFirmas:%1$s:firma-puesto');";
	private static final String FOCUS_FLOW_FIRMA_ROW_JQUERY = "PrimeFaces.focus('datosimpresion:tabs:tableFirmas:%1$s:firma-name');";

	private List<TcClave> listClaves;
	private List<TcPuesto> listPuestos;
	private List<TrPuestoFirma> listPuestosFirma;

	private List<TcReportesFirma> listReportes;
	private List<TcClave> listClavesFirma;
	private List<TcClave> listClaveSelected = new ArrayList<TcClave>();
	private List<TcClave> listClaveFiltered = new ArrayList<TcClave>();
	private TcReportesFirma reporteSelected;
	private Boolean rowSelected = Boolean.FALSE;

	private TcPuesto puesto;
	private TcClave clave;
	private Conctb conctb;
	private GemUser gemUser;
	private String organismo;
	private String claveOrganimo;
	private Long test;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService firmasService;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reportesFirmasRepository;

	@Value("#{tcClaveRepository}")
	private TcClaveRepository claveRepository;

	@PostConstruct
	public void init() {

		this.loadList();
		puesto = new TcPuesto();
		clave = new TcClave();
		gemUser = this.getUserDetails();
		conctb = conctbRepository.findByIdsectorAndIdRef(this.getUserDetails().getIdSector(), 0L);
		claveOrganimo = conctb.getClave().substring(0, 1);
		this.chooseOrganismo();

		listReportes = (List<TcReportesFirma>) reportesFirmasRepository.findAll();
		listClavesFirma = (List<TcClave>) claveRepository.findAll();
	}

	private void loadList() {
		listClaves = firmasService.listClaves(this.getUserDetails().getIdSector());
		listPuestos = firmasService.listPuestos(this.getUserDetails().getIdSector(), 0L);
		listPuestosFirma = firmasService.listPuestosFirmas(this.getUserDetails().getIdSector(), 0L);

		if (!listClaves.isEmpty())
			test = listClaves.get(0).getId();
	}

	public void update(TcPuesto puesto) {

		if (puesto.getFirmas().isEmpty()) {
			firmasService.createRelationshipPuesto(puesto, 0L);
		} else {
			firmasService.deleteRelationshipPuesto(puesto.getFirmas());
		}

		this.loadList();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se han actualizado correctamente la lista de Firmas");
	}

	public void newPuesto() {
		if (!StringUtils.isEmpty(puesto.getPuesto())) {

			if (validateStringWithRegex(REGEX_PUESTO, puesto.getPuesto())) {
				puesto.setPuesto(puesto.getPuesto().toUpperCase());
				puesto.setIdSector(this.getUserDetails().getIdSector());
				puesto.setClave(clave);
				puesto.setEstatus(1);
				puesto.setIdRef(0);

				firmasService.createPuesto(puesto, 0L);
				puesto = new TcPuesto();
				this.loadList();
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se ha Registrado correctamente el Puesto");

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Error!", "El Puesto es incorrecto, solo se permite valores alfabeticos."));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"El Puesto es obligatorio.", "Favor de capturar el puesto."));
		}
	}

	public void updateConctb() {
		conctbRepository.save(conctb);
		Firmas firmas = firmasRepository.findFirstByIdsector(this.getUserDetails().getIdSector());
		firmas.setCampo1(conctb.getNomDep());
		firmas.setCampo2(conctb.getRfc());
		firmasRepository.save(firmas);

		this.chooseOrganismo();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
				"La información se ha actualizado correctamente."));
	}

	public void updateOrganismo() {
		conctb.setClave(claveOrganimo + conctb.getClave().substring(1));
		conctbRepository.save(conctb);
		this.chooseOrganismo();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
				"La información se ha actualizado correctamente."));
	}

	public void handleFileUpload(FileUploadEvent event) {

		try {
			CopyFileUtil.copyFile(event.getFile().getFileName(), event.getFile().getInputStream(), PATH_IMG_FILE);
			conctb.setImagePathLeft(PATH_IMG_FILE + event.getFile().getFileName());
			conctbRepository.save(conctb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
				"La información se ha actualizado correctamente."));
	}

	public void uploadImageRigth(FileUploadEvent event) {

		try {
			CopyFileUtil.copyFile(event.getFile().getFileName(), event.getFile().getInputStream(), PATH_IMG_FILE);
			conctb.setImagePathRigth(PATH_IMG_FILE + event.getFile().getFileName());
			conctbRepository.save(conctb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
				"La información se ha actualizado correctamente."));
	}

	public void onRowEditPuesto(RowEditEvent event) {
		TcPuesto puesto = (TcPuesto) event.getObject();
		DataTable objDataTable = (DataTable) event.getSource();

		if (!StringUtils.isEmpty(puesto.getPuesto())) {
			if (validateStringWithRegex(REGEX_PUESTO, puesto.getPuesto())) {
				puesto.setPuesto(puesto.getPuesto().toUpperCase());

				this.firmasService.updateNamePuesto(puesto);
				this.loadList();
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se ha Actualizado correctamente el Puesto");
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Error!", "El Puesto es incorrecto, solo se permite valores alfabeticos."));
				PrimeFaces.current()
						.executeScript(String.format(EDIT_PUESTO_ROW_JQUERY, objDataTable.getRowIndex())
								+ String.format(FOCUS_FLOW_PUESTO_ROW_JQUERY, objDataTable.getRowIndex()));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"El Puesto es obligatorio.", "Favor de capturar el puesto."));
			PrimeFaces.current()
					.executeScript(String.format(EDIT_PUESTO_ROW_JQUERY, objDataTable.getRowIndex())
							+ String.format(FOCUS_FLOW_PUESTO_ROW_JQUERY, objDataTable.getRowIndex()));
		}
	}

	public void onRowEdit(RowEditEvent event) {
		TrPuestoFirma firma = (TrPuestoFirma) event.getObject();
		DataTable objDataTable = (DataTable) event.getSource();

		if (!StringUtils.isEmpty(firma.getNombre()) && !StringUtils.isEmpty(firma.getPuesto().getPuesto())) {
			if (validateStringWithRegex(REGEX_PUESTO, firma.getNombre())
					&& validateStringWithRegex(REGEX_PUESTO, firma.getPuesto().getPuesto())) {
				firma.setNombre(firma.getNombre().toUpperCase());
				firma.getPuesto().setPuesto(firma.getPuesto().getPuesto().toUpperCase());

				firmasService.updateNameFirma(firma);
				firmasService.updateNamePuesto(firma.getPuesto());
				this.loadList();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
						"Se ha actualizado correctamente los datos."));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Error!", "El Puesto y/o Nombre son incorrectos, solo se permite valores alfabeticos."));
				PrimeFaces.current()
						.executeScript(String.format(EDIT_FIRMA_ROW_JQUERY, objDataTable.getRowIndex())
								+ String.format(FOCUS_FLOW_FIRMA_ROW_JQUERY, objDataTable.getRowIndex()));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"El Puesto y Nombre es obligatorio.", "Favor de capturar el nombre y/o Puesto."));
			PrimeFaces.current()
					.executeScript(String.format(EDIT_FIRMA_ROW_JQUERY, objDataTable.getRowIndex())
							+ String.format((StringUtils.isEmpty(firma.getNombre()) ? FOCUS_FLOW_FIRMA_ROW_JQUERY
									: FOCUS_FLOW_PUESTO_ROW_JQUERY), objDataTable.getRowIndex()));
		}
	}

	public void onRowCancel(RowEditEvent event) {
		this.loadList();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Edición Cancelada."));
	}

	private void chooseOrganismo() {
		switch (conctb.getClave().substring(0, 1)) {
		case "0":
			this.organismo = "Ayuntamiento";
			break;
		case "2":
			this.organismo = "Organismo de Agua";
			break;
		case "3":
			this.organismo = "DIF";
			break;
		case "4":
			this.organismo = "Organismo del Deporte";
			break;
		default:
			this.organismo = StringUtils.EMPTY;
			break;
		}
	}

	public void onRowSelect(SelectEvent event) {

		TcReportesFirma firma = (TcReportesFirma) event.getObject();
		rowSelected = Boolean.TRUE;

		listClaveFiltered.clear();
		listClaveSelected.clear();

		String firmas = firma.getFirmas();
		if (firmas != null && !firmas.isEmpty()) {
			String[] localidades = StringUtils.splitByWholeSeparator(firmas, ",");
			for (String i : localidades) {
				for (TcClave locs : listClavesFirma) {
					if (i.trim().equals(locs.getClave().toString())) {
						listClaveSelected.add(locs);
						break;
					}
				}
			}
		}
	}

	public void changeFirmas() {

		int index = 0;
		for (TcReportesFirma reporte : listReportes) {
			if (reporteSelected.getId() == reporte.getId()) {

				if (listClaveSelected.size() > 5) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Solo se permite un máximo de 5 firmas");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					listReportes.get(index).setFirmas(printFirmas());
					reportesFirmasRepository.save(listReportes.get(index));
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Se ha actualizado el registro");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}

				break;
			}
			index++;
		}

	}

	private String printFirmas() {
		StringBuilder firmasText = new StringBuilder();
		int i = 1;
		for (TcClave clave : listClaveSelected) {
			firmasText.append(clave.getClave());
			if (listClaveSelected.size() != i)
				firmasText.append(",");
			i++;
		}
		return firmasText.toString();
	}

	public List<TcClave> getListClaves() {
		return listClaves;
	}

	public void setListClaves(List<TcClave> listClaves) {
		this.listClaves = listClaves;
	}

	public List<TcPuesto> getListPuestos() {
		return listPuestos;
	}

	public void setListPuestos(List<TcPuesto> listPuestos) {
		this.listPuestos = listPuestos;
	}

	public List<TrPuestoFirma> getListPuestosFirma() {
		return listPuestosFirma;
	}

	public void setListPuestosFirma(List<TrPuestoFirma> listPuestosFirma) {
		this.listPuestosFirma = listPuestosFirma;
	}

	public TcPuesto getPuesto() {
		return puesto;
	}

	public void setPuesto(TcPuesto puesto) {
		this.puesto = puesto;
	}

	public TcClave getClave() {
		return clave;
	}

	public void setClave(TcClave clave) {
		this.clave = clave;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public GemUser getGemUser() {
		return gemUser;
	}

	public void setGemUser(GemUser gemUser) {
		this.gemUser = gemUser;
	}

	public String getOrganismo() {
		return organismo;
	}

	public void setOrganismo(String organismo) {
		this.organismo = organismo;
	}

	public PuestosFirmasService getFirmasService() {
		return firmasService;
	}

	public void setFirmasService(PuestosFirmasService firmasService) {
		this.firmasService = firmasService;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	public String getClaveOrganimo() {
		return claveOrganimo;
	}

	public void setClaveOrganimo(String claveOrganimo) {
		this.claveOrganimo = claveOrganimo;
	}

	public Long getTest() {
		return test;
	}

	public void setTest(Long test) {
		this.test = test;
	}

	public List<TcReportesFirma> getListReportes() {
		return listReportes;
	}

	public void setListReportes(List<TcReportesFirma> listReportes) {
		this.listReportes = listReportes;
	}

	public List<TcClave> getListClavesFirma() {
		return listClavesFirma;
	}

	public void setListClavesFirma(List<TcClave> listClavesFirma) {
		this.listClavesFirma = listClavesFirma;
	}

	public List<TcClave> getListClaveSelected() {
		return listClaveSelected;
	}

	public void setListClaveSelected(List<TcClave> listClaveSelected) {
		this.listClaveSelected = listClaveSelected;
	}

	public List<TcClave> getListClaveFiltered() {
		return listClaveFiltered;
	}

	public void setListClaveFiltered(List<TcClave> listClaveFiltered) {
		this.listClaveFiltered = listClaveFiltered;
	}

	public TcReportesFirma getReporteSelected() {
		return reporteSelected;
	}

	public void setReporteSelected(TcReportesFirma reporteSelected) {
		this.reporteSelected = reporteSelected;
	}

	public Boolean getRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(Boolean rowSelected) {
		this.rowSelected = rowSelected;
	}

	public TcReportesFirmasRepository getReportesFirmasRepository() {
		return reportesFirmasRepository;
	}

	public void setReportesFirmasRepository(TcReportesFirmasRepository reportesFirmasRepository) {
		this.reportesFirmasRepository = reportesFirmasRepository;
	}

	public TcClaveRepository getClaveRepository() {
		return claveRepository;
	}

	public void setClaveRepository(TcClaveRepository claveRepository) {
		this.claveRepository = claveRepository;
	}

}
