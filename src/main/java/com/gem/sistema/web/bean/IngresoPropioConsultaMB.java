package com.gem.sistema.web.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.dto.IngresosPropiosDetalleDTO;
import com.gem.sistema.business.dto.IngresosPropiosPolizaDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.TwIngresoPropioDetalleService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "ingresoPropioConsultaMB")
@ViewScoped
public class IngresoPropioConsultaMB extends BaseDirectReport {
	private String mes;
	private String numero = "";
	private Conctb conctb = null;
	private List<TcMes> listMes;
	private BigDecimal totalIngreso = BigDecimal.ZERO;
	private List<IngresosPropiosPolizaDTO> polizas = new ArrayList<IngresosPropiosPolizaDTO>();
	List<IngresosPropiosDetalleDTO> ingresos = new ArrayList<IngresosPropiosDetalleDTO>();;
	private IngresosPropiosPolizaDTO polideSelected;
	private String status = "";

	@ManagedProperty("#{twIngresoPropioDetalleService}")
	private TwIngresoPropioDetalleService twIngresoPropioDetalleService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty(value = "#{polideRepository}")
	private PolideRepository polideRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "CargaIngresoDetallado";
		endFilename = jasperReporteName + ".pdf";
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			this.mes = listMes.get(0).getMes();
		}
		cargarPolizas();
	}

	public void cargarPolizas() {
		polizas = twIngresoPropioDetalleService.getPolizasWithTotalIngreso(mes, conctb.getAnoemp(),
				this.getUserDetails().getIdSector());
		if (polizas.isEmpty()) {
			this.displayInfoMessage("No se encontraron detalles de polizas ");
		}
		this.setIngresos(new ArrayList<IngresosPropiosDetalleDTO>());
	}

	public void cargarIngreso(SelectEvent event) {
		polideSelected = (IngresosPropiosPolizaDTO) event.getObject();
		if (polideSelected != null) {
			this.ingresos = twIngresoPropioDetalleService.getIngresosWithNombre(polideSelected);
			upTotalIngresosAndStatus();
		} else {
			this.displayInfoMessage("Selecione una detalle");
		}

	}

	public void upTotalIngresosAndStatus() {
		BigDecimal sum = new BigDecimal(0);
		if (!ingresos.isEmpty()) {
			for (IngresosPropiosDetalleDTO ingr : ingresos) {
				sum = sum.add(ingr.getMonto());
			}
			status = sum.compareTo(polideSelected.getIngreso()) == 0 ? "C" : "I";
			totalIngreso = sum;
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		parameters.put("query", this.getQuery());
		parameters.put("pMun", conctb.getNomDep());
		parameters.put("pImagen1", conctb.getImagePathLeft());
		parameters.put("pImagen2", conctb.getImagePathRigth());
		parameters.put("mes", polideSelected.getMespol());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("conpol", polideSelected.getConpol());
		return parameters;
	}

	private String getQuery() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT C.CLAVE,C.NOMBRE, I.MONTO FROM ")
				.append(" TC_INGRESOS_PROPIOS C ,TW_INGRESO_PROPIOS_DETALLE I")
				.append(" WHERE I.MESPOL= " + polideSelected.getMespol())
				.append(" AND I.CONPOL=" + polideSelected.getConpol() + " AND I.TIPPOL='" + polideSelected.getTippol()
						+ "' AND	RENPOL= ")
				.append(polideSelected.getRenpol() + " AND 	I.ID_INGRESO=C.ID ");
		return sql.toString();
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {

		return null;
	}

	public void displayInfoMessage(String str) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", str);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void createPDF() {
		if (polideSelected != null)
			this.createFilePdfInline();
		else
			this.displayInfoMessage("No se ha seleccionado ninguna poliza");

	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<TcMes> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	public BigDecimal getTotalIngreso() {
		return totalIngreso;
	}

	public void setTotalIngreso(BigDecimal totalIngreso) {
		this.totalIngreso = totalIngreso;
	}

	public TwIngresoPropioDetalleService getTwIngresoPropioDetalleService() {
		return twIngresoPropioDetalleService;
	}

	public void setTwIngresoPropioDetalleService(TwIngresoPropioDetalleService twIngresoPropioDetalleService) {
		this.twIngresoPropioDetalleService = twIngresoPropioDetalleService;
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

	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	public List<IngresosPropiosPolizaDTO> getPolizas() {
		return polizas;
	}

	public void setPolizas(List<IngresosPropiosPolizaDTO> polizas) {
		this.polizas = polizas;
	}

	public List<IngresosPropiosDetalleDTO> getIngresos() {
		return ingresos;
	}

	public void setIngresos(List<IngresosPropiosDetalleDTO> ingresos) {
		this.ingresos = ingresos;
	}

	public IngresosPropiosPolizaDTO getPolideSelected() {
		return polideSelected;
	}

	public void setPolideSelected(IngresosPropiosPolizaDTO polideSelected) {
		this.polideSelected = polideSelected;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
