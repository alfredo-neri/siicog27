package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.TwIngresoPropiosDetalle;
import com.gem.sistema.business.dto.IngresoPropioDTO;
import com.gem.sistema.business.dto.IngresosPropiosPolizaDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.TcAfectacionIngresoRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.TwIngresoPropioDetalleService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.CopyFileUtil;
import com.gem.sistema.util.UtilFront;

@ManagedBean(name = "ingresoPropioCargaAutomaticaMB")
@ViewScoped
public class IngresoPropioCargaAutomaticaMB extends BaseDirectReport {

	private static final String PATH = "/gem/upfiles/";

	private static final String TIPO = "I";
	private Integer numero;
	private Integer mes;
	private Conctb conctb = null;
	private String fileError = null;
	private List<Integer> listMes;
	private BigDecimal cargos = BigDecimal.ZERO;
	private BigDecimal abonos = BigDecimal.ZERO;
	private BigDecimal totalIngreso = BigDecimal.ZERO;
	private BigDecimal montoArchivo = BigDecimal.ZERO;
	private List<TwIngresoPropiosDetalle> cargados = new ArrayList<TwIngresoPropiosDetalle>();
	private List<IngresosPropiosPolizaDTO> polizas = new ArrayList<IngresosPropiosPolizaDTO>();
	private IngresosPropiosPolizaDTO polideSelected;
	@ManagedProperty(value = "#{tcAfectacionIngresoRepository}")
	private TcAfectacionIngresoRepository afectacionRepository;

	@ManagedProperty("#{twIngresoPropioDetalleService}")
	private TwIngresoPropioDetalleService twIngresoPropioDetalleService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty(value = "#{polideRepository}")
	private PolideRepository polideRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct polizaDetallada ");
		jasperReporteName = "ingresosPropios";
		endFilename = jasperReporteName + ".xls";

		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		listMes = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++)
			listMes.add(i);
		mes = listMes.get(0);
		cargarPolizas();
	}

	/**
	 * Carga las polizas que contengas cuenta de ingresos propios de acuerdo al mes,
	 * año, sector
	 */
	public void cargarPolizas() {
		polizas = twIngresoPropioDetalleService.getPolizasWithTotalIngreso(mes.toString(), conctb.getAnoemp(),
				this.getUserDetails().getIdSector());
		if (polizas.isEmpty()) {
			this.outMsn("No se encontraron detalles de polizas ");
		}
	}

	public void rowSelected(SelectEvent event) {
		polideSelected = (IngresosPropiosPolizaDTO) event.getObject();
		if (polideSelected != null) {
			this.numero = polideSelected.getConpol();
		} else {
			this.outMsn("Selecione una detalle");
		}
		// Se resetean valores en cada carga
		cargos = BigDecimal.ZERO;
		abonos = BigDecimal.ZERO;
		totalIngreso = BigDecimal.ZERO;
		montoArchivo = BigDecimal.ZERO;
		cargados = new ArrayList<TwIngresoPropiosDetalle>();
		fileError = "";

	}

	public void handleFileUpload(FileUploadEvent event) {
		// Se resetean valores en cada carga
		cargos = BigDecimal.ZERO;
		abonos = BigDecimal.ZERO;
		totalIngreso = BigDecimal.ZERO;
		montoArchivo = BigDecimal.ZERO;
		cargados = new ArrayList<TwIngresoPropiosDetalle>();
		fileError = "";
		String newName = UUID.randomUUID() + "" + event.getFile().getFileName();
		List<Polide> polide = new ArrayList<Polide>();
		if (numero == null) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "¡El campo numero no puede ser vacio!", "");

		} else {
			// Valida si el mes ya ha sido afectado para poder agrear registros
			if (this.afectacionRepository.getByMes(mes).getStatus().equals("N")) {
				// optine los renglones de con las cuenta de ingreos porpios
				polide = polideRepository.getAllByIdsectorAndTippolAndMespolAndAnopolAndConpol(
						Integer.valueOf(this.getUserDetails().getIdSector()), TIPO, mes, conctb.getAnoemp(), numero);
				if (CollectionUtils.isEmpty(polide)) {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
							"No se encontraron detalles de poliza con cuenta de ingresos porpios", "");
				} else if (polide.size() > 2) {// no deben de existir mas de dos renglones por poliza 0001 y 0002
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
							"La poliza contiene mas de dos cuentas de ingreos porpios", "");
				} else {
					List<Polide> cuenta0001 = polide.stream().filter(p -> p.getSsscta().equals("0001"))
							.collect(Collectors.toList());
					if (cuenta0001.isEmpty()) {
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
								"La poliza no contiene la cuenta de ingresos porpios", "");
					} else if (cuenta0001.size() > 1) {// solo debe de existir una cuneta 0001 de ingresos propios
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
								"La poliza contiene mas de dos cuentas de ingreos porpios", "");
					} else {
						// Si solo existe la cuenta 0001 se le suma 0
						BigDecimal depositos = polide.size() == 1 ? BigDecimal.ZERO : polide.get(1).getCanpolh();
						// Renglon al que se asosia el ingreso detallado
						Polide detallePoliza = cuenta0001.get(0);
						this.setCargos(detallePoliza.getCanpol());
						this.setAbonos(detallePoliza.getCanpolh());
						this.setTotalIngreso(polide.get(0).getCanpolh().add(depositos));
						if (null != detallePoliza) {
							if (event.getFile() != null) {
								if (StringUtils.isNotEmpty(newName)) {
									try {
										String path = CopyFileUtil.copyFile(newName, event.getFile().getInputstream(),
												PATH);
										if (event.getFile().getSize() <= 0) {
											UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
													"El archivo no puede ser vacio", "");
										} else {
											File file = new File(path);
											IngresoPropioDTO salida = this.twIngresoPropioDetalleService
													.cargaIngresoPropio(file, detallePoliza, this.getTotalIngreso());
											this.setMontoArchivo(salida.getTotal());
											fileError = salida.getCodError() == 2 ? "/gem/errores/" + newName : null;
											cargados = salida.getCargados();
											salida.getMsgsError().forEach(this::outMsn);
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								} else {
									UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
											"Seleciona un archivo", "");
								}
							} else {
								UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Seleciona un archivo",
										"");
							}
						}
					}
				}
			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
						"El mes ya ha sido afectado, no es posible realizar la carga ", "");
			}
		}

	}

	public Map<String, Object> getParametersReports() {
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		paramsReport.put("pAnio", conctb.getAnoemp());
		paramsReport.put("pDia", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("USUARIO", getUserDetails().getUsername());
		paramsReport.put("img1", conctb.getImagePathLeft());
		paramsReport.put("img2", conctb.getImagePathRigth());
		paramsReport.put("mes", mes);
		paramsReport.put("pMesLetra",
				tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes).getDescripcion().toUpperCase());
		paramsReport.put("idSector", idSector);
		paramsReport.put("nameDep", conctb.getNomDep());

		return paramsReport;
	}

	public StreamedContent getFile() {
		StreamedContent st = null;
		try {
			File file = new File(this.getFileError());
			st = new DefaultStreamedContent(new FileInputStream(file), "text/csv", file.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return st;
	}

	public void outMsn(String msn) {
		UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, msn, "");
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public String getFileError() {
		return fileError;
	}

	public void setFileError(String fileError) {
		this.fileError = fileError;
	}

	public List<Integer> getListMes() {
		return listMes;
	}

	public void setListMes(List<Integer> listMes) {
		this.listMes = listMes;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public TwIngresoPropioDetalleService getTwIngresoPropioDetalleService() {
		return twIngresoPropioDetalleService;
	}

	public void setTwIngresoPropioDetalleService(TwIngresoPropioDetalleService twIngresoPropioDetalleService) {
		this.twIngresoPropioDetalleService = twIngresoPropioDetalleService;
	}

	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	public BigDecimal getCargos() {
		return cargos;
	}

	public void setCargos(BigDecimal cargos) {
		this.cargos = cargos;
	}

	public BigDecimal getAbonos() {
		return abonos;
	}

	public void setAbonos(BigDecimal abonos) {
		this.abonos = abonos;
	}

	public BigDecimal getTotalIngreso() {
		return totalIngreso;
	}

	public void setTotalIngreso(BigDecimal totalIngreso) {
		this.totalIngreso = totalIngreso;
	}

	public BigDecimal getMontoArchivo() {
		return montoArchivo;
	}

	public void setMontoArchivo(BigDecimal montoArchivo) {
		this.montoArchivo = montoArchivo;
	}

	public List<TwIngresoPropiosDetalle> getCargados() {
		return cargados;
	}

	public void setCargados(List<TwIngresoPropiosDetalle> cargados) {
		this.cargados = cargados;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TcAfectacionIngresoRepository getAfectacionRepository() {
		return afectacionRepository;
	}

	public void setAfectacionRepository(TcAfectacionIngresoRepository afectacionRepository) {
		this.afectacionRepository = afectacionRepository;
	}

	public List<IngresosPropiosPolizaDTO> getPolizas() {
		return polizas;
	}

	public void setPolizas(List<IngresosPropiosPolizaDTO> polizas) {
		this.polizas = polizas;
	}

	public IngresosPropiosPolizaDTO getPolideSelected() {
		return polideSelected;
	}

	public void setPolideSelected(IngresosPropiosPolizaDTO polideSelected) {
		this.polideSelected = polideSelected;
	}

}
