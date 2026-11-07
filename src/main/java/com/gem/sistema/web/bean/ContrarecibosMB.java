package com.gem.sistema.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.ContraRecibo;
import com.gem.sistema.business.domain.FolioContrarecibo;
import com.gem.sistema.business.domain.Fuentef;
import com.gem.sistema.business.domain.ProveedorNuevo;
import com.gem.sistema.business.domain.TcFacturaContraRecibo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.ContraReciboRepository;
import com.gem.sistema.business.repository.catalogs.FoliosContrareciboRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.catalogs.ProveedoresNuevoRepository;
import com.gem.sistema.business.repository.catalogs.TcFacturasContraReciboRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "contrarecibosMB")
@ViewScoped
public class ContrarecibosMB extends BaseDirectReport {

	private static final String SHOW_PDF = " jQuery('#form\\\\:showPdf').click();";
	private Boolean whitePdf = false;
	private ContraRecibo recibo;
	private TcFacturaContraRecibo facturaContraRecibo = new TcFacturaContraRecibo();
	private String busqueda = "";
	private Long idTemporal;
	private Conctb conctb = null;
	private FolioContrarecibo folio;
	private Date dia;
	/** Array principal de recibos */
	private List<ContraRecibo> recibos = new ArrayList<ContraRecibo>();
	private List<TcFacturaContraRecibo> facturasByRecibo = new ArrayList<TcFacturaContraRecibo>();
	/** Arrays para combo de proveedores */
	private List<ProveedorNuevo> proveedores = new ArrayList<>();
	/** Arrays para combo de fuentes */
	private List<Fuentef> fuentes = new ArrayList<>();

	Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();

	@ManagedProperty("#{contraReciboRepository}")
	private ContraReciboRepository contraReciboRepository;

	@ManagedProperty("#{tcFacturaContraReciboRepository}")
	private TcFacturasContraReciboRepository facturasContraReciboRepository;

	@ManagedProperty("#{proveedoresNuevoRepository}")
	private ProveedoresNuevoRepository proveedoresRepository;

	@ManagedProperty("#{foliosContrareciboRepository}")
	private FoliosContrareciboRepository foliosContrareciboRepository;

	@ManagedProperty("#{fuentefRepository}")
	private FuentefRepository fuentefRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@PostConstruct
	public void init() {
		dia = new Date();
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		jasperReporteName = "contraRecibo";
		endFilename = jasperReporteName + ".pdf";
		buscar();
		proveedores = proveedoresRepository.findAll();
		fuentes = fuentefRepository.getByIdSector(this.getUserDetails().getIdSector());
		folio = foliosContrareciboRepository.findTopByAnio(conctb.getAnoemp());
		recibo = getDefaultRecibo();
	}

	/** Instancia por defaulth de un contrato Pedido */
	private ContraRecibo getDefaultRecibo() {
		ContraRecibo init = new ContraRecibo();
		init.setConcepto("");
		init.setElaboro("");
		init.setFechaEmicion(new Date());
		init.setFechaPago(add45DaysToDate(init.getFechaEmicion()));
		init.setFolio(folio == null ? 0 : folio.getFolioActual());
		init.setObs("");
		init.setFolioIdentificacion(folio);
		return init;
	}

	/** Guarda los cambios en un contrato pedido */
	public void guardarRecibo() {

		List<String> errors = recibo.isValid();
		if (errors.isEmpty()) {
			try {
				ContraRecibo recibofolio = contraReciboRepository.findOneByFolio(recibo.getFolio());
				boolean modificado = recibo.getId() != null && recibo.getId() > 0;
				if (recibofolio == null || recibofolio.getId().equals(recibo.getId())) {
					if (recibo.getObs() == null) {
						recibo.setObs("");
					}
					if (!modificado) {
						recibo.setCancelado("");
					}
					ContraRecibo nuevo = contraReciboRepository.save(recibo);
					if (modificado) {
						recibos = recibos.stream().map(p -> p.getId().equals(nuevo.getId()) ? nuevo : p)
								.collect(Collectors.toList());
					} else {
						recibos.add(nuevo);
						folio.setFolioActual(folio.getFolioActual() + 1);
						folio = foliosContrareciboRepository.save(folio);
					}
					recibos = recibos.stream().sorted(Comparator.comparing(ContraRecibo::getFolio))
							.collect(Collectors.toList());
					recibo = nuevo;
					displayInfoMessage(
							"Se ha " + (!modificado ? "agregado" : "modificado ") + " correctamente el contra-recibo");
				} else {
					displayInfoMessage("El numero de folio ya ha sido capturado");
				}
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrrido un error al guardar el contra-recibo");
			}
		} else {
			errors.forEach(e -> displayInfoMessage(e));
		}
	}

	public void onEditRecibo() {
		facturasByRecibo = facturasContraReciboRepository.findAllByContraRecibo(recibo);
	}

	/** Suma 45 dias habiles a la fecha dada */
	public Date add45DaysToDate(Date fecha) {
		if (fecha != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			Integer contador = 0;
			while (contador <= 45) {
				c.add(Calendar.DATE, 1);
				Integer dia = c.get(Calendar.DAY_OF_WEEK);
				if (dia > 1 && dia < 7)
					contador++;
			}
			fecha = c.getTime();
		}
		return fecha;
	}

	public void updateFechaPago() {
		if (recibo != null) {
			recibo.setFechaPago(add45DaysToDate(recibo.getFechaEmicion()));
		}
	}

	public void cacelarRecibo() {
		recibos = recibos.stream().map(r -> {
			if (idTemporal.equals(r.getId())) {
				r.setCancelado(r.getCancelado() == null || r.getCancelado().equals("") ? "Cancelado" : "");
				r = contraReciboRepository.save(r);
			}
			return r;
		}).collect(Collectors.toList());
	}

	public void resetContrato() {
		if (recibo.getId() != null) {
			displayInfoMessage("Se cancelo la edicion");
		}
		recibo = getDefaultRecibo();
	}

	/** Elimina un contrato */
	public void deleteContrato() {
		if (idTemporal != null) {
			try {
				ContraRecibo reciboDelete = recibos.stream().filter(p -> p.getId().equals(idTemporal)).findFirst()
						.orElse(new ContraRecibo());
				recibos = recibos.stream().filter(p -> !p.getId().equals(idTemporal)).collect(Collectors.toList());
				facturasByRecibo = facturasContraReciboRepository.findAllByContraRecibo(reciboDelete);
				facturasContraReciboRepository.delete(facturasByRecibo);
				contraReciboRepository.delete(reciboDelete);
				displayInfoMessage(
						"El contra-recibo con folio: " + reciboDelete.getFolio() + ", se ha eliminado correctamente");
				displayInfoMessage("Se han eliminado: " + facturasByRecibo.size()
						+ " facturas del contrarecibo con folio " + reciboDelete.getFolio());
				if (recibo != null && recibo.getId() != null && recibo.getId().equals(idTemporal)) {
					recibo = getDefaultRecibo();
					facturasByRecibo = new ArrayList<TcFacturaContraRecibo>();
				}
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error eliminando el contra-recibo");
			}
		}
	}

	public Date getMaxDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		return c.getTime();
	}

	public Map<String, Object> getParametersReports() {
		paramsReport.put("imagePath", conctb.getImagePathLeft());
		paramsReport.put("imagePath2", conctb.getImagePathRigth());
		paramsReport.put("entidadName", conctb.getNomDep());
		return paramsReport;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

	public void displayInfoMessage(String str) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", str);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void showPdf() {
		RequestContext.getCurrentInstance().execute(SHOW_PDF);
		whitePdf = false;
	}

	public void showPdfWhite() {
		RequestContext.getCurrentInstance().execute(SHOW_PDF);
		whitePdf = true;
	}

	public void pdfContrarecibo() {
		jasperReporteName = "contraRecibo";
		endFilename = jasperReporteName + ".pdf";
		paramsReport = new HashMap<String, Object>();
		paramsReport.put("white", whitePdf);
		paramsReport.put("id", idTemporal);
		this.createFilePdfInline();
	}

	public void pdfContrareciboList() {
		if (dia == null || dia.toString().equals("")) {
			displayInfoMessage("Seleccione una fecha");
		} else {
			jasperReporteName = "contraRecibosList";
			endFilename = jasperReporteName + ".pdf";
			paramsReport = new HashMap<String, Object>();
			paramsReport.put("mes", "'" + new SimpleDateFormat("dd/MM/yyyy").format(dia) + "'");
			this.createFilePdfInline();
		}
	}

	public void buscar() {
		if (busqueda == null || busqueda.equals("")) {
			recibos = contraReciboRepository.findAllByOrderByFolio();
		} else {
			recibos = contraReciboRepository.findAllByOrderByFolio().stream()
					.filter(p -> p.busquedaSimple().toLowerCase().contains(busqueda.toLowerCase()))
					.collect(Collectors.toList());
		}

	}

	/** Guarda una factura */
	public void guardarFactura() {
		if (recibo == null || recibo.getId() == null || recibo.getId() == 0) {
			displayInfoMessage("Debe de guardar el contra recibo para capturar facturas");
		} else {
			List<String> errors = facturaContraRecibo.isValid();
			if (errors.isEmpty()) {
				try {
					boolean modificado = facturaContraRecibo.getId() != null && facturaContraRecibo.getId() > 0;
					facturaContraRecibo.setContraRecibo(recibo);
					facturaContraRecibo = facturasContraReciboRepository.save(facturaContraRecibo);
					if (modificado) {
						facturasByRecibo = facturasByRecibo.stream()
								.map(f -> f.getId().equals(facturaContraRecibo.getId()) ? facturaContraRecibo : f)
								.collect(Collectors.toList());
					} else {
						facturasByRecibo.add(facturaContraRecibo);
					}
					displayInfoMessage(
							"Se ha " + (!modificado ? "agregado" : "modificado ") + " correctamente la factura");
					facturaContraRecibo = new TcFacturaContraRecibo();
				} catch (Exception e) {
					e.printStackTrace();
					displayInfoMessage("Ha ocurrrido un error al guardar la factura");
				}
			} else {
				errors.forEach(this::displayInfoMessage);
			}
		}
	}

	/** Elimina un Factura */
	public void deleteFactura() {
		if (facturaContraRecibo != null) {
			try {
				facturasByRecibo.remove(facturaContraRecibo);
				recibo.deleteFactura(facturaContraRecibo);
				facturasContraReciboRepository.delete(facturasByRecibo);
				displayInfoMessage("La factura se ha eliminado correctamnte");
				facturaContraRecibo = new TcFacturaContraRecibo();
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error eliminando el articulo");
			}
		}
	}

	public void resetFactura() {
		if (facturaContraRecibo.getId() != null) {
			displayInfoMessage("Se cancelo la edicion");
		}
		facturaContraRecibo = new TcFacturaContraRecibo();
	}

	public ContraRecibo getRecibo() {
		return recibo;
	}

	public void setRecibo(ContraRecibo recibo) {
		this.recibo = recibo;
	}

	public List<ContraRecibo> getRecibos() {
		return recibos;
	}

	public void setRecibos(List<ContraRecibo> recibos) {
		this.recibos = recibos;
	}

	

	public List<ProveedorNuevo> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorNuevo> proveedores) {
		this.proveedores = proveedores;
	}

	public List<Fuentef> getFuentes() {
		return fuentes;
	}

	public void setFuentes(List<Fuentef> fuentes) {
		this.fuentes = fuentes;
	}

	public ContraReciboRepository getContraReciboRepository() {
		return contraReciboRepository;
	}

	public void setContraReciboRepository(ContraReciboRepository contraReciboRepository) {
		this.contraReciboRepository = contraReciboRepository;
	}

	

	public ProveedoresNuevoRepository getProveedoresRepository() {
		return proveedoresRepository;
	}

	public void setProveedoresRepository(ProveedoresNuevoRepository proveedoresRepository) {
		this.proveedoresRepository = proveedoresRepository;
	}

	public FoliosContrareciboRepository getFoliosContrareciboRepository() {
		return foliosContrareciboRepository;
	}

	public void setFoliosContrareciboRepository(FoliosContrareciboRepository foliosContrareciboRepository) {
		this.foliosContrareciboRepository = foliosContrareciboRepository;
	}

	public FuentefRepository getFuentefRepository() {
		return fuentefRepository;
	}

	public void setFuentefRepository(FuentefRepository fuentefRepository) {
		this.fuentefRepository = fuentefRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public List<TcFacturaContraRecibo> getFacturasByRecibo() {
		return facturasByRecibo;
	}

	public void setFacturasByRecibo(List<TcFacturaContraRecibo> facturasByRecibo) {
		this.facturasByRecibo = facturasByRecibo;
	}

	public TcFacturasContraReciboRepository getFacturasContraReciboRepository() {
		return facturasContraReciboRepository;
	}

	public void setFacturasContraReciboRepository(TcFacturasContraReciboRepository facturasContraReciboRepository) {
		this.facturasContraReciboRepository = facturasContraReciboRepository;
	}

	public TcFacturaContraRecibo getFacturaContraRecibo() {
		return facturaContraRecibo;
	}

	public void setFacturaContraRecibo(TcFacturaContraRecibo facturaContraRecibo) {
		this.facturaContraRecibo = facturaContraRecibo;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public Long getIdTemporal() {
		return idTemporal;
	}

	public void setIdTemporal(Long idTemporal) {
		this.idTemporal = idTemporal;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

}
