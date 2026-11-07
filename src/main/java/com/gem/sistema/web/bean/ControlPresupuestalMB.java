package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.event.ToggleEvent;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.TcAccion;
import com.gem.sistema.business.domain.TcArea;
import com.gem.sistema.business.domain.TcContrato;
import com.gem.sistema.business.domain.TcContratoPad;
import com.gem.sistema.business.domain.TcControlPresupuestario;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcStatusPago;
import com.gem.sistema.business.domain.TrAreaAccion;
import com.gem.sistema.business.domain.TrPresupuestoArea;
import com.gem.sistema.business.domain.TrPresupuestoDependencia;
import com.gem.sistema.business.domain.TwAccionContrato;
import com.gem.sistema.business.domain.TwContratoFactura;
import com.gem.sistema.business.domain.TwObra;
import com.gem.sistema.business.domain.TwObraPago;
import com.gem.sistema.business.service.catalogos.ControlPresupuestarioService;

@Component("controlPresupuestalMB")
@Scope("view")
public class ControlPresupuestalMB extends AbstractMB {

	private static final String CLAVE_FAM = "FAM";
	private static final String CLAVE_PAD = "PAD";

	private List<TrPresupuestoArea> listPresupuestoAreas;
	private List<TrPresupuestoDependencia> listPresupuestoDependencias;
	private List<TcStatusPago> liStatusPagos;
	private List<TcContratoPad> listContratoPads;
	private List<TcPeriodo> listMeses;
	private List<Cattpo> listTipos;
	private List<Catdep> listCatdeps;

	private List<TcAccion> listAccions;
	private List<TcAccion> selectedAccions;
	private List<TcArea> listAreas;

	private Polien polizaSelected;
	private TcArea areaSelected;
	private TcAccion accionSelected;
	private TrPresupuestoArea presupuestoSelected;

	private TcControlPresupuestario fam;
	private TcControlPresupuestario pad;

	private TwObra twObra;
	private TwObraPago twObraPago;
	private TwObraPago obraPagoSelected;
	private TwContratoFactura facturaSelected;
	private TcContrato tcContrato;
	private TcContratoPad tcContratoPad;
	private TwContratoFactura contratoFactura;
	private TwAccionContrato accionContratoSelected = null;
	private TrAreaAccion areaAccionSelected = null;
	private Catdep dependenciaSelected;
	private TwObra obraSelected = null;
	private TcContratoPad contratoPadSelected = null;

	private String tabchange;
	private Integer tabindex;

	private Boolean toggleFAM = Boolean.FALSE;
	private Boolean togglePAD = Boolean.FALSE;

	private String modalName = StringUtils.EMPTY;

	private Boolean renderDialogObras = Boolean.FALSE;
	private Boolean renderDialogAreas = Boolean.FALSE;
	private Boolean renderDialogAcciones = Boolean.FALSE;
	private Boolean renderDialogPagosObras = Boolean.FALSE;
	private Boolean renderDialogFacturasFAM = Boolean.FALSE;
	private Boolean renderDialogContratosFAM = Boolean.FALSE;
	private Boolean renderDialogDependencias = Boolean.FALSE;
	private Boolean renderDialogContratosPAD = Boolean.FALSE;
	private Boolean renderDialogPresupuestosFAM = Boolean.FALSE;
	private Boolean renderDialogPresupuestosPAD = Boolean.FALSE;

	public void openDialogObras() {

		TrPresupuestoDependencia presupuestoDependencia = new TrPresupuestoDependencia();
		TcContratoPad contrato = new TcContratoPad();
		Catdep catdep = new Catdep();
		contrato.setNumeroContrato(StringUtils.EMPTY);

		this.twObra = new TwObra();
		this.twObra.setNoAccion(0);
		this.twObra.setNombreObra(StringUtils.EMPTY);
		this.twObra.setAsignado(BigDecimal.ZERO);
		this.twObra.setAutorizado(BigDecimal.ZERO);

		this.twObra.setContrato(contrato);
		presupuestoDependencia.setDependencia(catdep);
		this.twObra.setPresupuestoDependencia(presupuestoDependencia);

		this.renderDialogObras = Boolean.TRUE;
		this.renderDialogAreas = Boolean.FALSE;
		this.renderDialogAcciones = Boolean.FALSE;
		this.renderDialogPagosObras = Boolean.FALSE;
		this.renderDialogFacturasFAM = Boolean.FALSE;
		this.renderDialogContratosFAM = Boolean.FALSE;
		this.renderDialogDependencias = Boolean.FALSE;
		this.renderDialogContratosPAD = Boolean.FALSE;
		this.renderDialogPresupuestosFAM = Boolean.FALSE;
		this.renderDialogPresupuestosPAD = Boolean.FALSE;

		PrimeFaces.current().executeScript("PF('obrasDlg').show();");
		PrimeFaces.current().ajax().update("thirdForm");
	}

	public void openDialogAreas() {
		this.renderDialogObras = Boolean.FALSE;
		this.renderDialogAreas = Boolean.TRUE;
		this.renderDialogAcciones = Boolean.FALSE;
		this.renderDialogPagosObras = Boolean.FALSE;
		this.renderDialogFacturasFAM = Boolean.FALSE;
		this.renderDialogContratosFAM = Boolean.FALSE;
		this.renderDialogDependencias = Boolean.FALSE;
		this.renderDialogContratosPAD = Boolean.FALSE;
		this.renderDialogPresupuestosFAM = Boolean.FALSE;
		this.renderDialogPresupuestosPAD = Boolean.FALSE;

		PrimeFaces.current().executeScript("PF('areasDlg').show();");
		PrimeFaces.current().ajax().update("secondForm");
	}

	public void openDialogAcciones() {
		this.renderDialogObras = Boolean.FALSE;
		this.renderDialogAreas = Boolean.FALSE;
		this.renderDialogAcciones = Boolean.TRUE;
		this.renderDialogPagosObras = Boolean.FALSE;
		this.renderDialogFacturasFAM = Boolean.FALSE;
		this.renderDialogContratosFAM = Boolean.FALSE;
		this.renderDialogDependencias = Boolean.FALSE;
		this.renderDialogContratosPAD = Boolean.FALSE;
		this.renderDialogPresupuestosFAM = Boolean.FALSE;
		this.renderDialogPresupuestosPAD = Boolean.FALSE;

		PrimeFaces.current().executeScript("PF('accionesDlg').show();");
		PrimeFaces.current().ajax().update("secondForm");
	}

	public void openDialogFacturasFam() {
		
		if (facturaSelected != null) {
			this.contratoFactura = facturaSelected;
			this.polizaSelected = facturaSelected.getPoliza();
			this.facturaSelected = null;
		} else {
			this.polizaSelected = new Polien();
			this.polizaSelected.setFecpol(null);
		}
		
		this.renderDialogObras = Boolean.FALSE;
		this.renderDialogAreas = Boolean.FALSE;
		this.renderDialogAcciones = Boolean.FALSE;
		this.renderDialogPagosObras = Boolean.FALSE;
		this.renderDialogFacturasFAM = Boolean.TRUE;
		this.renderDialogContratosFAM = Boolean.FALSE;
		this.renderDialogDependencias = Boolean.FALSE;
		this.renderDialogContratosPAD = Boolean.FALSE;
		this.renderDialogPresupuestosFAM = Boolean.FALSE;
		this.renderDialogPresupuestosPAD = Boolean.FALSE;

		PrimeFaces.current().executeScript("PF('facturasDlg').show();");
		PrimeFaces.current().ajax().update("secondForm");
	}

	public void openDialogContratosFam() {
		this.renderDialogObras = Boolean.FALSE;
		this.renderDialogAreas = Boolean.FALSE;
		this.renderDialogAcciones = Boolean.FALSE;
		this.renderDialogPagosObras = Boolean.FALSE;
		this.renderDialogFacturasFAM = Boolean.FALSE;
		this.renderDialogContratosFAM = Boolean.TRUE;
		this.renderDialogDependencias = Boolean.FALSE;
		this.renderDialogContratosPAD = Boolean.FALSE;
		this.renderDialogPresupuestosFAM = Boolean.FALSE;
		this.renderDialogPresupuestosPAD = Boolean.FALSE;

		PrimeFaces.current().executeScript("PF('contratosDlg').show();");
		PrimeFaces.current().ajax().update("secondForm");
	}

	public void openDialogDependencias() {
		this.renderDialogObras = Boolean.FALSE;
		this.renderDialogAreas = Boolean.FALSE;
		this.renderDialogAcciones = Boolean.FALSE;
		this.renderDialogPagosObras = Boolean.FALSE;
		this.renderDialogFacturasFAM = Boolean.FALSE;
		this.renderDialogContratosFAM = Boolean.FALSE;
		this.renderDialogDependencias = Boolean.TRUE;
		this.renderDialogContratosPAD = Boolean.FALSE;
		this.renderDialogPresupuestosFAM = Boolean.FALSE;
		this.renderDialogPresupuestosPAD = Boolean.FALSE;

		PrimeFaces.current().executeScript("PF('dependenciaDlg').show();");
		PrimeFaces.current().ajax().update("thirdForm");
	}

	public void openDialogPresupuestosFam() {
		this.renderDialogObras = Boolean.FALSE;
		this.renderDialogAreas = Boolean.FALSE;
		this.renderDialogAcciones = Boolean.FALSE;
		this.renderDialogPagosObras = Boolean.FALSE;
		this.renderDialogFacturasFAM = Boolean.FALSE;
		this.renderDialogContratosFAM = Boolean.FALSE;
		this.renderDialogDependencias = Boolean.FALSE;
		this.renderDialogContratosPAD = Boolean.FALSE;
		this.renderDialogPresupuestosFAM = Boolean.TRUE;
		this.renderDialogPresupuestosPAD = Boolean.FALSE;

		PrimeFaces.current().executeScript("PF('configuracionPresupuestoFamDlg').show();");
		PrimeFaces.current().ajax().update("secondForm");
	}

	public void openDialogPresupuestosPad() {
		this.renderDialogObras = Boolean.FALSE;
		this.renderDialogAreas = Boolean.FALSE;
		this.renderDialogAcciones = Boolean.FALSE;
		this.renderDialogPagosObras = Boolean.FALSE;
		this.renderDialogFacturasFAM = Boolean.FALSE;
		this.renderDialogContratosFAM = Boolean.FALSE;
		this.renderDialogDependencias = Boolean.FALSE;
		this.renderDialogContratosPAD = Boolean.FALSE;
		this.renderDialogPresupuestosFAM = Boolean.FALSE;
		this.renderDialogPresupuestosPAD = Boolean.TRUE;

		PrimeFaces.current().executeScript("PF('configuracionPresupuestoPadDlg').show();");
		PrimeFaces.current().ajax().update("thirdForm");
	}

	public void openDialogPagosObras() {

		if (obraPagoSelected != null) {
			this.twObraPago = obraPagoSelected;
			this.obraPagoSelected = null;
		} else {
			this.twObraPago = new TwObraPago();
		}

		this.renderDialogObras = Boolean.FALSE;
		this.renderDialogAreas = Boolean.FALSE;
		this.renderDialogAcciones = Boolean.FALSE;
		this.renderDialogPagosObras = Boolean.TRUE;
		this.renderDialogFacturasFAM = Boolean.FALSE;
		this.renderDialogContratosFAM = Boolean.FALSE;
		this.renderDialogDependencias = Boolean.FALSE;
		this.renderDialogContratosPAD = Boolean.FALSE;
		this.renderDialogPresupuestosFAM = Boolean.FALSE;
		this.renderDialogPresupuestosPAD = Boolean.FALSE;

		PrimeFaces.current().executeScript("PF('pagosObrasDlg').show();");
		PrimeFaces.current().ajax().update("thirdForm");
	}

	public void openDialogContratosPad() {

		this.tcContratoPad = new TcContratoPad();

		this.loadDataContratosPad();
		this.renderDialogObras = Boolean.FALSE;
		this.renderDialogAreas = Boolean.FALSE;
		this.renderDialogAcciones = Boolean.FALSE;
		this.renderDialogPagosObras = Boolean.FALSE;
		this.renderDialogFacturasFAM = Boolean.FALSE;
		this.renderDialogContratosFAM = Boolean.FALSE;
		this.renderDialogDependencias = Boolean.FALSE;
		this.renderDialogContratosPAD = Boolean.TRUE;
		this.renderDialogPresupuestosFAM = Boolean.FALSE;
		this.renderDialogPresupuestosPAD = Boolean.FALSE;

		PrimeFaces.current().executeScript("PF('contratosPadDlg').show();");
		PrimeFaces.current().ajax().update("thirdForm");
	}

	private void loadDataContratosPad() {
		this.listContratoPads = this.controlPresupuestarioService.getAllContratosPad();
	}

	public void changeContrato() {
		TcContrato contrato = controlPresupuestarioService.getContratos(tcContrato.getNumeroContrato());
		if (contrato != null) {

			this.tcContrato = contrato;

			PrimeFaces.current().ajax().update("secondForm:float-input1");
			PrimeFaces.current().ajax().update("secondForm:float-input2");
			PrimeFaces.current().ajax().update("secondForm:float-input3");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Warn! Contrato no encontrado, Favor de seleccionar una contrato valido.", null));

			PrimeFaces.current().ajax().update("secondForm:messagesContratosFam");
		}
	}

	public void changePoliza() {
		if (polizaSelected.getMespol() != null && polizaSelected.getConpol() != null
				&& polizaSelected.getTippol() != null) {
			polizaSelected = controlPresupuestarioService.getPolien(polizaSelected.getMespol(), 2022,
					polizaSelected.getConpol(), polizaSelected.getTippol(), this.getUserDetails().getIdSector());

			if (polizaSelected != null) {
				this.contratoFactura.setPoliza(polizaSelected);

				PrimeFaces.current().ajax().update("secondForm:fechaPoliza");

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Warn! Póliza no encontrada, Favor de seleccionar una poliza que se encuentre registrada.",
						null));
				PrimeFaces.current().ajax().update("secondForm:messagesFacturasFam");
			}
		} else {
			PrimeFaces.current().ajax().update("secondForm:messagesFacturasFam");
		}

	}

	@Value("#{controlPresupuestarioService}")
	private ControlPresupuestarioService controlPresupuestarioService;

	@PostConstruct
	public void init() {
		fam = controlPresupuestarioService.getControlPresupuestario(CLAVE_FAM);
		pad = controlPresupuestarioService.getControlPresupuestario(CLAVE_PAD);

		listPresupuestoAreas = controlPresupuestarioService.getAreasWithPresupuesto();
		if (!CollectionUtils.isEmpty(listPresupuestoAreas)) {
			this.areaAccionSelected = listPresupuestoAreas.get(0).getAccions().get(0);
		}

		listPresupuestoDependencias = controlPresupuestarioService.getDependenciasWithPresupuesto();
		if (!CollectionUtils.isEmpty(listPresupuestoDependencias)) {
			this.obraSelected = listPresupuestoDependencias.get(0).getObras().get(0);
		}

		this.tcContrato = new TcContrato();
		this.contratoFactura = new TwContratoFactura();
		this.contratoFactura.setPoliza(new Polien());

		this.liStatusPagos = controlPresupuestarioService.getStatusPagos();
		this.listMeses = controlPresupuestarioService.getActivePeriodos(this.getUserDetails().getIdSector());
		this.listTipos = controlPresupuestarioService.getAllTiposPoliza();

		togglePAD = Boolean.TRUE;
		toggleFAM = Boolean.FALSE;

		this.listAreas = controlPresupuestarioService.getAllAreas();
		this.listAccions = new ArrayList<>(); // controlPresupuestarioService.getAllAccions();
		this.listCatdeps = controlPresupuestarioService.getAllDependencias();

		this.recalculateAvailable();

	}

	private Boolean validateDisponibleAreas(List<TrPresupuestoArea> presupuestoAreas) {

		Boolean response = Boolean.FALSE;
		Boolean validAcciones = Boolean.TRUE;

		BigDecimal inicial = BigDecimal.ZERO;
		BigDecimal presupuesto = BigDecimal.ZERO;
		BigDecimal presupuestoTotal = BigDecimal.ZERO;
		for (TrPresupuestoArea trPresupuestoArea : presupuestoAreas) {

			presupuestoTotal = presupuestoTotal.add(trPresupuestoArea.getPresupuestoAutorizado());

			inicial = this.validateDisponibleAcciones(trPresupuestoArea.getAccions());

			if (trPresupuestoArea.getPresupuestoAutorizado().compareTo(inicial) < 0) {
				validAcciones = Boolean.FALSE;
				break;
			}

		}

		if (validAcciones) {
			if (this.fam.getAutorizadoAnual().compareTo(presupuesto) >= 0) {
				this.fam = controlPresupuestarioService.saveControlPresupuestario(fam);
				this.listPresupuestoAreas = controlPresupuestarioService.savePresupuestoAreas(listPresupuestoAreas);
				for (TrPresupuestoArea trPresupuestoArea : this.listPresupuestoAreas) {
					presupuesto = presupuesto.add(trPresupuestoArea.getPresupuestoAutorizado());
					inicial = this.validateDisponibleAcciones(trPresupuestoArea.getAccions());
					trPresupuestoArea.setDisponible(trPresupuestoArea.getPresupuestoAutorizado().subtract(inicial));
				}
				this.fam.setDisponible(fam.getAutorizadoAnual().subtract(presupuestoTotal));
				response = Boolean.TRUE;
			} else {
				this.fam = controlPresupuestarioService.getControlPresupuestario(CLAVE_FAM);
				this.listPresupuestoAreas = controlPresupuestarioService.getAreasWithPresupuesto();
				presupuesto = BigDecimal.ZERO;
				for (TrPresupuestoArea trPresupuestoArea : this.listPresupuestoAreas) {
					presupuesto = presupuesto.add(trPresupuestoArea.getPresupuestoAutorizado());
					inicial = this.validateDisponibleAcciones(trPresupuestoArea.getAccions());
					trPresupuestoArea.setDisponible(trPresupuestoArea.getPresupuestoAutorizado().subtract(inicial));
				}
				this.fam.setDisponible(fam.getAutorizadoAnual().subtract(presupuestoTotal));
			}
		}

		return response;
	}

	private BigDecimal validateDisponibleAcciones(List<TrAreaAccion> presupuestoAccions) {

		BigDecimal presupuesto = BigDecimal.ZERO;
		for (TrAreaAccion trAreaAccion : presupuestoAccions) {
			presupuesto = presupuesto.add(trAreaAccion.getAutorizadoInicial());
		}

		return presupuesto;
	}

	private void recalculateAvailable() {

		if (validateDisponibleAreas(listPresupuestoAreas)) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
					"La información se ha actualizado correctamente."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El Presupuesto se ha superado."));
		}

	}

	public void onSelectedArea() {

		this.listAccions = controlPresupuestarioService.getAccionsByDireccion(presupuestoSelected.getArea());

//			polizaSelected = controlPresupuestarioService.getPolien(polizaSelected.getMespol(), 2022,
//					polizaSelected.getConpol(), polizaSelected.getTippol(), this.getUserDetails().getIdSector());
//
//			if (polizaSelected != null) {
//				this.contratoFactura.setPoliza(polizaSelected);
//
//				PrimeFaces.current().ajax().update("secondForm:fechaPoliza");
//
//			} else {
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
//						"Warn! Póliza no encontrada, Favor de seleccionar una poliza que se encuentre registrada.",
//						null));
//				PrimeFaces.current().ajax().update("secondForm:messagesFacturasFam");
//			}

	}

	public void onToggleFAM(ToggleEvent event) {

		this.toggleFAM = !toggleFAM;
		this.togglePAD = !togglePAD;

	}

	public void onTogglePAD(ToggleEvent event) {

		this.toggleFAM = !toggleFAM;
		this.togglePAD = !togglePAD;

	}

	public void updateFAM() {

		this.recalculateAvailable();

//		if (fam.getDisponible().compareTo(BigDecimal.ZERO) < 0) {
//
//			fam = controlPresupuestarioService.getControlPresupuestario(CLAVE_FAM);
//			listPresupuestoAreas = controlPresupuestarioService.getAreasWithPresupuesto();
//			
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El Presupuesto asignado se ha superado."));
//
//		} else {
//
//			this.fam = controlPresupuestarioService.saveControlPresupuestario(fam);
//			this.listPresupuestoAreas = controlPresupuestarioService.savePresupuestoAreas(listPresupuestoAreas);
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
//					"La información se ha actualizado correctamente."));
//		}
//		
//		this.recalculateAvailable();
	}

	public void updatePAD() {
		this.pad = controlPresupuestarioService.saveControlPresupuestario(pad);
		this.listPresupuestoDependencias = controlPresupuestarioService
				.savePresupuestoDependencias(listPresupuestoDependencias);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
				"La información se ha actualizado correctamente."));
	}

	public void onTabChange(TabChangeEvent event) {

		this.areaAccionSelected = (TrAreaAccion) event.getData();
		System.out.println(this.areaAccionSelected.getAccion().getDescripcion());

		String msg = "Tab Changed Active Tab: " + areaAccionSelected.getId();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFOR", msg));
	}

	public void onTabChangeObra(TabChangeEvent event) {

		this.obraSelected = (TwObra) event.getData();
		System.out.println(this.obraSelected.getNombreObra());

		String msg = "Tab Changed Active Tab: " + obraSelected.getId();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFOR", msg));
	}

	public void onRowToggle(ToggleEvent event) {

		this.accionContratoSelected = ((TwAccionContrato) event.getData());
		this.contratoFactura.setAccionContrato(accionContratoSelected);
	}

	public void onToggleDependencia(ToggleEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled",
				"Status:" + event.getVisibility().name());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onTabChangeDependenciaPad(TabChangeEvent event) {
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onTabCloseDependenciaPad(TabCloseEvent event) {
		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void editFactura(TwAccionContrato accionContrato) {
		this.accionContratoSelected = accionContrato;
		this.contratoFactura = new TwContratoFactura();
		this.contratoFactura.setPoliza(new Polien());
		this.contratoFactura.setAccionContrato(accionContratoSelected);

		this.openDialogFacturasFam();
	}

	public void saveArea() {

		Boolean continuar = Boolean.TRUE;
		for (TrPresupuestoArea presupuestoArea : listPresupuestoAreas) {

			if (presupuestoArea.getArea().equals(areaSelected)) {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error! El Area ya se encuentra registrada.", null));
				continuar = Boolean.FALSE;
				PrimeFaces.current().ajax().update("secondForm:messagesAreas");
				break;
			}
		}

		if (continuar) {
			TrPresupuestoArea presupuestoArea = new TrPresupuestoArea();

			presupuestoArea.setArea(areaSelected);
			presupuestoArea.setPresupuestoAutorizado(BigDecimal.ZERO);

			listPresupuestoAreas.add(controlPresupuestarioService.savePresupuestoArea(presupuestoArea));

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "El Area se ha registrado correctamente."));

			PrimeFaces.current().executeScript("PF('areasDlg').hide();");
			PrimeFaces.current().ajax().update("form:fam");
			PrimeFaces.current().ajax().update("form:messages");
		}
	}

	public void saveAccion() {

		if (!CollectionUtils.isEmpty(this.selectedAccions)) {

			for (TcAccion accion : selectedAccions) {

				if (controlPresupuestarioService.getAreaAccion(presupuestoSelected, accion) != null) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Warn!", "La Acción: " + accion.getNombre() + " ya existe."));
				} else {

					TrAreaAccion areaAccion = new TrAreaAccion();

					areaAccion.setPresupuestoArea(presupuestoSelected);
					areaAccion.setAutorizadoInicial(BigDecimal.ZERO);
					areaAccion.setAccion(accion);

					TrAreaAccion trAreaAccion = controlPresupuestarioService.saveAreaAccion(areaAccion);

					int index = listPresupuestoAreas.indexOf(presupuestoSelected);
					listPresupuestoAreas.get(index).getAccions().add(trAreaAccion);

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Info!", "La Acción: " + accion.getNombre() + " se ha registrado correctamente."));

				}

				PrimeFaces.current().executeScript("PF('accionesDlg').hide();");
				PrimeFaces.current().ajax().update("form:messages");

			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! No se selecciono ninguna Acción.", null));
		}

		// Boolean continuar = Boolean.TRUE;
//
//		Integer index = this.getIndexOfTrPresupuestoArea(presupuestoSelected);
//
//		for (TrAreaAccion areaAccion : presupuestoSelected.getAccions()) {
//
//			if (areaAccion.getAccion().equals(accionSelected)) {
//
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//						"Error! La Acción ya se encuentra registrada.", null));
//				continuar = Boolean.FALSE;
//				break;
//			}
//		}
//
//		if (continuar) {
//			TrAreaAccion areaAccion = new TrAreaAccion();
//
//			areaAccion.setPresupuestoArea(presupuestoSelected);
//			areaAccion.setAutorizadoInicial(BigDecimal.ZERO);
//			areaAccion.setAccion(accionSelected);
//
//			TrAreaAccion trAreaAccion = controlPresupuestarioService.saveAreaAccion(areaAccion);
//
//			listPresupuestoAreas.get(index).getAccions().add(trAreaAccion);
//
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "La Acción se ha registrado correctamente."));
//
//			PrimeFaces.current().executeScript("PF('accionesDlg').hide();");
//			PrimeFaces.current().ajax().update("form:messages");
//		}
	}

	public void saveContratoPad() {
		Boolean canContinue = Boolean.TRUE;
		String message = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.tcContratoPad.getNumeroContrato().trim())) {
			canContinue = Boolean.FALSE;
			message = message + " Error. Se debe ingresar el Número de Contrato!";
		}

		if (null == this.tcContratoPad.getFechaContrato()) {
			canContinue = Boolean.FALSE;
			message = message + " Error. Se debe ingresar la Fecha del Contrato!";
		}

		if (this.tcContratoPad.getImporte().compareTo(BigDecimal.ZERO) <= 0) {
			canContinue = Boolean.FALSE;
			message = message + " Error. Se debe ingresar un importe valido!";
		}

		if (StringUtils.isEmpty(this.tcContratoPad.getNombreObra().trim())) {
			canContinue = Boolean.FALSE;
			message = message + " Error. Se debe ingresar el nombre de la obra!";
		}

		if (StringUtils.isEmpty(this.tcContratoPad.getProveedor().trim())) {
			canContinue = Boolean.FALSE;
			message = message + " Error. Se debe ingresar el Nombre del Proveedor!";
		}

		if (null == this.tcContratoPad.getFechaInicioObra()) {
			canContinue = Boolean.FALSE;
			message = message + " Error. Se debe ingresar la Fecha de Inicio!";
		}

		if (null == this.tcContratoPad.getFechaTerminoObra()) {
			canContinue = Boolean.FALSE;
			message = message + " Error. Se debe ingresar la Fecha de Termino!";
		}

		if (canContinue) {

			if (this.tcContratoPad.getId() != null) {
				this.controlPresupuestarioService.saveContratoPad(this.tcContratoPad);
			} else {
				this.listContratoPads.add(this.controlPresupuestarioService.saveContratoPad(this.tcContratoPad));
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Info! El Contrato se ha registrado correctamente.", null));

			PrimeFaces.current().executeScript("PF('contratosPadDlg').hide();");
			PrimeFaces.current().ajax().update("thirdForm");
			PrimeFaces.current().ajax().update("form:messages");

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		}

	}

	public void saveObra() {

//		if (!StringUtils.isEmpty(this.twObra.getNombreObra().trim())) {
		TcContratoPad contrato = controlPresupuestarioService
				.getContratoPad(this.twObra.getContrato().getNumeroContrato());

		if (contrato != null) {

			if (this.twObra.getPresupuestoDependencia() != null) {
				this.twObra.setContrato(contrato);
				controlPresupuestarioService.saveObra(this.twObra);

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Info! La Acción se ha registrado correctamente.", null));

				PrimeFaces.current().executeScript("PF('obrasDlg').hide();");
				PrimeFaces.current().ajax().update("thirdForm");
				PrimeFaces.current().ajax().update("form:messages");

				Integer index = listPresupuestoDependencias.indexOf(this.twObra.getPresupuestoDependencia());
				listPresupuestoDependencias.get(index).getObras().add(twObra);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! La Dependencia no es valida.", null));
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! El contrato no existe.", null));
		}

//		} else {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//					"Error! Favor de Ingresar el Nombre de la Obra.", null));
//		}

	}

	public void savePagoObra() {

		if (!StringUtils.isEmpty(this.twObraPago.getNoFactura().trim())) {

			if (this.twObraPago.getImporteFactura().compareTo(BigDecimal.ZERO) == 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error! El Importe de la Factura no puede ser 0.", null));
			} else {

//				if (this.twObraPago.getImporteAfectacionPresupuestal()
//						.compareTo(this.twObraPago.getImporteFactura()) == 1) {
//					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//							"Error! El Importe de la Afectación Presupuestal no puede ser mayor al Importe de la Factura.",
//							null));
//				} else {

				BigDecimal totalPagado = BigDecimal.ZERO;
				for (TwObraPago pagos : obraSelected.getPagos()) {
					totalPagado = totalPagado.add(pagos.getImporteFactura());
				}
				totalPagado = totalPagado.add(this.twObraPago.getImporteFactura());

				System.out.println("TotalPagado: " + totalPagado);
				if (totalPagado.compareTo(obraSelected.getContrato().getImporte()) == 1) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error! El Importe de las Factura no puede ser mayor al Monto del Contrato", null));
				} else if (twObraPago.getStatusPago().getClave().equals("PGD") && null == twObraPago.getFechaPago()) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error! La Fecha de Pago es obligatoria.", null));
				} else {
					
					Boolean isNew = Boolean.FALSE;
					if(null == this.twObraPago.getId())
						isNew = Boolean.TRUE;
					

					if (isNew) {
						if (null != twObraPago.getFechaPago() && !twObraPago.getStatusPago().getClave().equals("PGD")) {
							twObraPago.setStatusPago(getStatusPagado());
						}

						this.twObraPago.setObra(obraSelected);
					}

					if (StringUtils.isEmpty(this.twObraPago.getObservaciones())) {
						this.twObraPago.setObservaciones(StringUtils.EMPTY);
					}

					TwObraPago pago = controlPresupuestarioService.savePagoObra(this.twObraPago);

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Info! El Pago se ha registrado correctamente.", null));

					Integer indexDependencia = listPresupuestoDependencias
							.indexOf(this.obraSelected.getPresupuestoDependencia());
					Integer indexObra = listPresupuestoDependencias.get(indexDependencia).getObras()
							.indexOf(this.obraSelected);

					if (isNew) {
						this.listPresupuestoDependencias.get(indexDependencia).getObras().get(indexObra).getPagos()
								.add(pago);
					}

					PrimeFaces.current().executeScript("PF('pagosObrasDlg').hide();");
					PrimeFaces.current().ajax().update("thirdForm");
					PrimeFaces.current().ajax().update("form:messages");
					PrimeFaces.current()
							.ajax().update("form:dependencias:" + indexDependencia + ":obras:" + indexObra + ":pagos");

				}
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! Favor de Ingresar el No de Factura.", null));
		}

	}

	private TcStatusPago getStatusPagado() {
		Integer index = null;
		for (TcStatusPago tcStatusPago : this.getLiStatusPagos()) {
			if (tcStatusPago.getClave().equals("PGD")) {

				index = tcStatusPago.getIndex();
				break;
			}
		}
		return this.getLiStatusPagos().get(index);
	}

	public void saveDependencia() {
		Boolean continuar = Boolean.TRUE;
		for (TrPresupuestoDependencia presupuestoDependencia : listPresupuestoDependencias) {

			if (presupuestoDependencia.getDependencia().equals(dependenciaSelected)) {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error! La Dependencia ya se encuentra registrada.", null));
				continuar = Boolean.FALSE;
				break;
			}
		}

		if (continuar) {
			TrPresupuestoDependencia presupuestoDependencia = new TrPresupuestoDependencia();

			presupuestoDependencia.setDependencia(dependenciaSelected);
			presupuestoDependencia.setPresupuestoAsignado(BigDecimal.ZERO);

			listPresupuestoDependencias
					.add(controlPresupuestarioService.savePresupuestoDependencia(presupuestoDependencia));

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
					"La Dependencia se ha registrado correctamente."));

			PrimeFaces.current().executeScript("PF('dependenciaDlg').hide();");
			PrimeFaces.current().ajax().update("form:messages");
		}
	}

	public void showModal() {
		PrimeFaces.current().executeScript("PF('" + this.modalName + "').show();");
	}

	public void saveFactura() {

		Polien polien = controlPresupuestarioService.getPolien(contratoFactura.getPoliza().getMespol(), 2022,
				contratoFactura.getPoliza().getConpol(), contratoFactura.getPoliza().getTippol(),
				this.getUserDetails().getIdSector());

		if (polien != null) {
			this.contratoFactura.setPoliza(polien);
			this.contratoFactura.setAccionContrato(this.accionContratoSelected);
			this.controlPresupuestarioService.saveContratoFactura(contratoFactura);

			listPresupuestoAreas = controlPresupuestarioService.getAreasWithPresupuesto();
			this.contratoFactura = new TwContratoFactura();
			this.contratoFactura.setPoliza(new Polien());

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
					"La Factura se ha registrado correctamente."));
			PrimeFaces.current().executeScript("PF('facturasDlg').hide();");
			PrimeFaces.current().ajax().update("facturaForm:dialogId");
			PrimeFaces.current().ajax().update("form:tabView:areas");
			PrimeFaces.current().ajax().update("form:messages");

		} else {
			PrimeFaces.current().executeScript("PF('facturasDlg').open();");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn!",
					"Póliza no encontrada, Favor de seleccionar una poliza que se encuentre registrada."));
		}
	}

	public void saveContrato() {

		TcContrato contrato = controlPresupuestarioService.getContratos(tcContrato.getNumeroContrato());

		if (contrato != null) {
			TwAccionContrato accionContrato = new TwAccionContrato();

			accionContrato.setContrato(contrato);
			accionContrato.setAreaAccion(areaAccionSelected);

			this.controlPresupuestarioService.saveAccionConytrato(accionContrato);

			listPresupuestoAreas = controlPresupuestarioService.getAreasWithPresupuesto();
			tcContrato = new TcContrato();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Contrato registrado correctamente."));
			PrimeFaces.current().executeScript("PF('contratosDlg').hide();");
			PrimeFaces.current().ajax().update("contratoForm:dialogId");
			PrimeFaces.current().ajax().update("form:tabView:areas");
			PrimeFaces.current().ajax().update("form:messages");
		} else {
			PrimeFaces.current().executeScript("PF('contratosDlg').open();");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn!",
					"Contrato no encontrado, Favor de seleccionar una contrato valido."));
		}
	}

	public List<TrPresupuestoArea> getListPresupuestoAreas() {
		return listPresupuestoAreas;
	}

	public void setListPresupuestoAreas(List<TrPresupuestoArea> listPresupuestoAreas) {
		this.listPresupuestoAreas = listPresupuestoAreas;
	}

	public List<TrPresupuestoDependencia> getListPresupuestoDependencias() {
		return listPresupuestoDependencias;
	}

	public void setListPresupuestoDependencias(List<TrPresupuestoDependencia> listPresupuestoDependencias) {
		this.listPresupuestoDependencias = listPresupuestoDependencias;
	}

	public List<TcStatusPago> getLiStatusPagos() {
		return liStatusPagos;
	}

	public void setLiStatusPagos(List<TcStatusPago> liStatusPagos) {
		this.liStatusPagos = liStatusPagos;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public List<Cattpo> getListTipos() {
		return listTipos;
	}

	public void setListTipos(List<Cattpo> listTipos) {
		this.listTipos = listTipos;
	}

	public List<Catdep> getListCatdeps() {
		return listCatdeps;
	}

	public void setListCatdeps(List<Catdep> listCatdeps) {
		this.listCatdeps = listCatdeps;
	}

	public List<TcAccion> getListAccions() {
		return listAccions;
	}

	public void setListAccions(List<TcAccion> listAccions) {
		this.listAccions = listAccions;
	}

	public List<TcArea> getListAreas() {
		return listAreas;
	}

	public void setListAreas(List<TcArea> listAreas) {
		this.listAreas = listAreas;
	}

	public Polien getPolizaSelected() {
		return polizaSelected;
	}

	public void setPolizaSelected(Polien polizaSelected) {
		this.polizaSelected = polizaSelected;
	}

	public TcArea getAreaSelected() {
		return areaSelected;
	}

	public void setAreaSelected(TcArea areaSelected) {
		this.areaSelected = areaSelected;
	}

	public TcAccion getAccionSelected() {
		return accionSelected;
	}

	public void setAccionSelected(TcAccion accionSelected) {
		this.accionSelected = accionSelected;
	}

	public TrPresupuestoArea getPresupuestoSelected() {
		return presupuestoSelected;
	}

	public void setPresupuestoSelected(TrPresupuestoArea presupuestoSelected) {
		this.presupuestoSelected = presupuestoSelected;
	}

	public TcControlPresupuestario getFam() {
		return fam;
	}

	public void setFam(TcControlPresupuestario fam) {
		this.fam = fam;
	}

	public TcControlPresupuestario getPad() {
		return pad;
	}

	public void setPad(TcControlPresupuestario pad) {
		this.pad = pad;
	}

	public TwObra getTwObra() {
		return twObra;
	}

	public void setTwObra(TwObra twObra) {
		this.twObra = twObra;
	}

	public TwObraPago getTwObraPago() {
		return twObraPago;
	}

	public void setTwObraPago(TwObraPago twObraPago) {
		this.twObraPago = twObraPago;
	}

	public TcContrato getTcContrato() {
		return tcContrato;
	}

	public void setTcContrato(TcContrato tcContrato) {
		this.tcContrato = tcContrato;
	}

	public TwContratoFactura getContratoFactura() {
		return contratoFactura;
	}

	public void setContratoFactura(TwContratoFactura contratoFactura) {
		this.contratoFactura = contratoFactura;
	}

	public TwAccionContrato getAccionContratoSelected() {
		return accionContratoSelected;
	}

	public void setAccionContratoSelected(TwAccionContrato accionContratoSelected) {
		this.accionContratoSelected = accionContratoSelected;
	}

	public TrAreaAccion getAreaAccionSelected() {
		return areaAccionSelected;
	}

	public void setAreaAccionSelected(TrAreaAccion areaAccionSelected) {
		this.areaAccionSelected = areaAccionSelected;
	}

	public Catdep getDependenciaSelected() {
		return dependenciaSelected;
	}

	public void setDependenciaSelected(Catdep dependenciaSelected) {
		this.dependenciaSelected = dependenciaSelected;
	}

	public TwObra getObraSelected() {
		return obraSelected;
	}

	public void setObraSelected(TwObra obraSelected) {
		this.obraSelected = obraSelected;
	}

	public String getTabchange() {
		return tabchange;
	}

	public void setTabchange(String tabchange) {
		this.tabchange = tabchange;
	}

	public Integer getTabindex() {
		return tabindex;
	}

	public void setTabindex(Integer tabindex) {
		this.tabindex = tabindex;
	}

	public Boolean getToggleFAM() {
		return toggleFAM;
	}

	public void setToggleFAM(Boolean toggleFAM) {
		this.toggleFAM = toggleFAM;
	}

	public Boolean getTogglePAD() {
		return togglePAD;
	}

	public void setTogglePAD(Boolean togglePAD) {
		this.togglePAD = togglePAD;
	}

	public String getModalName() {
		return modalName;
	}

	public void setModalName(String modalName) {
		this.modalName = modalName;
	}

	public Boolean getRenderDialogObras() {
		return renderDialogObras;
	}

	public void setRenderDialogObras(Boolean renderDialogObras) {
		this.renderDialogObras = renderDialogObras;
	}

	public Boolean getRenderDialogAreas() {
		return renderDialogAreas;
	}

	public void setRenderDialogAreas(Boolean renderDialogAreas) {
		this.renderDialogAreas = renderDialogAreas;
	}

	public Boolean getRenderDialogAcciones() {
		return renderDialogAcciones;
	}

	public void setRenderDialogAcciones(Boolean renderDialogAcciones) {
		this.renderDialogAcciones = renderDialogAcciones;
	}

	public Boolean getRenderDialogPagosObras() {
		return renderDialogPagosObras;
	}

	public void setRenderDialogPagosObras(Boolean renderDialogPagosObras) {
		this.renderDialogPagosObras = renderDialogPagosObras;
	}

	public Boolean getRenderDialogFacturasFAM() {
		return renderDialogFacturasFAM;
	}

	public void setRenderDialogFacturasFAM(Boolean renderDialogFacturasFAM) {
		this.renderDialogFacturasFAM = renderDialogFacturasFAM;
	}

	public Boolean getRenderDialogContratosFAM() {
		return renderDialogContratosFAM;
	}

	public void setRenderDialogContratosFAM(Boolean renderDialogContratosFAM) {
		this.renderDialogContratosFAM = renderDialogContratosFAM;
	}

	public Boolean getRenderDialogDependencias() {
		return renderDialogDependencias;
	}

	public void setRenderDialogDependencias(Boolean renderDialogDependencias) {
		this.renderDialogDependencias = renderDialogDependencias;
	}

	public Boolean getRenderDialogPresupuestosFAM() {
		return renderDialogPresupuestosFAM;
	}

	public void setRenderDialogPresupuestosFAM(Boolean renderDialogPresupuestosFAM) {
		this.renderDialogPresupuestosFAM = renderDialogPresupuestosFAM;
	}

	public Boolean getRenderDialogPresupuestosPAD() {
		return renderDialogPresupuestosPAD;
	}

	public void setRenderDialogPresupuestosPAD(Boolean renderDialogPresupuestosPAD) {
		this.renderDialogPresupuestosPAD = renderDialogPresupuestosPAD;
	}

	public ControlPresupuestarioService getControlPresupuestarioService() {
		return controlPresupuestarioService;
	}

	public void setControlPresupuestarioService(ControlPresupuestarioService controlPresupuestarioService) {
		this.controlPresupuestarioService = controlPresupuestarioService;
	}

	public List<TcAccion> getSelectedAccions() {
		return selectedAccions;
	}

	public void setSelectedAccions(List<TcAccion> selectedAccions) {
		this.selectedAccions = selectedAccions;
	}

	public Boolean getRenderDialogContratosPAD() {
		return renderDialogContratosPAD;
	}

	public void setRenderDialogContratosPAD(Boolean renderDialogContratosPAD) {
		this.renderDialogContratosPAD = renderDialogContratosPAD;
	}

	public List<TcContratoPad> getListContratoPads() {
		return listContratoPads;
	}

	public void setListContratoPads(List<TcContratoPad> listContratoPads) {
		this.listContratoPads = listContratoPads;
	}

	public TcContratoPad getTcContratoPad() {
		return tcContratoPad;
	}

	public void setTcContratoPad(TcContratoPad tcContratoPad) {
		this.tcContratoPad = tcContratoPad;
	}

	public TcContratoPad getContratoPadSelected() {
		return contratoPadSelected;
	}

	public void setContratoPadSelected(TcContratoPad contratoPadSelected) {
		this.contratoPadSelected = contratoPadSelected;
	}

	public TwObraPago getObraPagoSelected() {
		return obraPagoSelected;
	}

	public void setObraPagoSelected(TwObraPago obraPagoSelected) {
		this.obraPagoSelected = obraPagoSelected;
	}

	public TwContratoFactura getFacturaSelected() {
		return facturaSelected;
	}

	public void setFacturaSelected(TwContratoFactura facturaSelected) {
		this.facturaSelected = facturaSelected;
	}

}
