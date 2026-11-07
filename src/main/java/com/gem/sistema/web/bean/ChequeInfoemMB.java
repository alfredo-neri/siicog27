package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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


import com.gem.sistema.business.bs.ReporteChequeContratoService;
import com.gem.sistema.business.domain.*;
import com.gem.sistema.web.datamodel.DataModelGeneric;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.service.catalogos.EmpleadoInfoemService;
import com.gem.sistema.business.service.catalogos.RegistroPagoChequeService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "chequeInfoemMB")
@ViewScoped
public class ChequeInfoemMB extends BaseDirectReport {

	private static final String NOMBRE_PROVEEDOR = "INSTITUTO DE SEGURIDAD SOCIAL DEL ESTADO DE MEXICO Y MUNICIPIOS";

	@ManagedProperty("#{empleadoInfoemService}")
	private EmpleadoInfoemService empleadoInfoemService;

	@ManagedProperty("#{registroPagoChequeService}")
	private RegistroPagoChequeService registroPagoChequeService;

	@ManagedProperty("#{reporteChequeContratoService}")
	private ReporteChequeContratoService reporteChequeContratoService;

	private TwRegistroPagoCheque selectPagoCheque;
	private static final String PATH_OUT = "/gem/reportes/";

	private TcContrato contrato;
	private boolean flagActivate = true;
	
	private String notasCheques;
	

	private Character tipoRerporte = 'e';

	private Character tipoChequeConsulta = 'e';

	private boolean rederContrato = true;

	private String none = "none";

	private String nombre;

	private String etiqueta;

	private Long numeroPagados;

	private String nContrato;

	private Long numeroCancelados;

	private Integer folio = 0;

	private Integer folioConsulta;

	private Integer mesConsulta;

	private String nombreConsulta;

	private String statusCheque = "PAGADO";

	private String tipoPagoCad = "Quincena";

	private BigDecimal monto = BigDecimal.ZERO;

	private String montoLetras;

	private String concepto;

	private String currentDate;

	private String numeroPago;

	private List<TcPeriodo> meses;

	private TcPeriodo mes;

	private List<TcTipoCheque> tipoCheques;

	private TcTipoCheque tipoCheque;

	private List<TcEstatusCheque> estatusCheques;

	private TcEstatusCheque estatusCheque;

	private String message;

	private List<String> selctOptions;

	private List<TwRegistroPagoCheque> registroPagoCheques;

	private static final Map<Object, Object> mapPagos = new HashMap<>();

	private DataModelGeneric<TwRegistroPagoCheque> modelGeneric;
	
	private List<String> listCuentaBancaria;

	private boolean editableTable = false;
	
	private Date date;

	private Integer idAux;

	static {
		mapPagos.put(1, "1ra Quincena");
		mapPagos.put(2, "2da Quincena");
	}

	private TwRegistroPagoCheque cancelTregistroCheque;
	
	private String cuentaBancaria;

	public void cancelCheque(Integer id) {
		idAux = id;
		cancelTregistroCheque = this.registroPagoChequeService.findByIdCheque(idAux);
		RequestContext.getCurrentInstance().execute("PF('dlgChequesCan').show()");

	}

	public void confirmarCalcelCheque() {
		cancelTregistroCheque = this.registroPagoChequeService.findByIdCheque(idAux);
		TcEstatusCheque auxEstatus = this.registroPagoChequeService.findAllEstatusCheque().stream()
				.filter(c -> c.getNombre().equals("CANCELADO")).findFirst().get();
		cancelTregistroCheque.setTcEstatusCheque(auxEstatus);
		this.registroPagoChequeService.update(cancelTregistroCheque.getFolio(), cancelTregistroCheque);

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info",
				"El Cheque con Folio : " + cancelTregistroCheque.getFolio() + " se cancelo correctamente");
		RequestContext.getCurrentInstance().execute("PF('dlgChequesCan').hide()");
		consultarCheques();

	}

	@PostConstruct
	public void init() {
		jasperReporteName = "cheque_full";
		endFilename = jasperReporteName + ".pdf";
		etiqueta = "Empleado";
		tipoCheques = this.registroPagoChequeService.findAllTipoCheque();
		tipoCheque = tipoCheques.get(0);
		estatusCheques = this.registroPagoChequeService.findAllEstatusCheque();
		estatusCheque = estatusCheques.get(0);
		meses = this.registroPagoChequeService.findByMonthPeriodo();
		mes = meses.get(0);

		numeroPagados = this.registroPagoChequeService.findAll().stream()
				.filter(p -> p.getTcEstatusCheque().getNombre().equals("PAGADO")).count();
		numeroCancelados = this.registroPagoChequeService.findAll().stream()
				.filter(p -> p.getTcEstatusCheque().getNombre().equals("CANCELADO")).count();
		rederContrato = false;
		none = "none";
		monto = BigDecimal.ZERO;
		listCuentaBancaria = new ArrayList<>();
		listCuentaBancaria.add("--Seleccione--");
		listCuentaBancaria.add("18000230559");
		listCuentaBancaria.add("18000230545");

	}

	public void selectedOptionsChanged() {
		String message = "selectedOptions changed to: ";
		if (!selctOptions.isEmpty()) {
			for (int i = 0; i < selctOptions.size(); i++) {
				if (i > 0) {
					message += ", ";
				}
				message += selctOptions.get(i);
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public void findByCheque() {
		if (folioConsulta != null && mesConsulta == null && tipoChequeConsulta != ' '
				&& StringUtils.isEmpty(statusCheque) && StringUtils.isEmpty(nombreConsulta)) {
			registroPagoCheques = registroPagoCheques.stream().filter(cheque -> cheque.getFolio().equals(folioConsulta))
					.collect(Collectors.toList());
		} else if (folioConsulta == null && mesConsulta == null && tipoChequeConsulta != ' '
				&& StringUtils.isEmpty(statusCheque) && StringUtils.isNotEmpty(nombreConsulta)) {
			registroPagoCheques = registroPagoCheques.stream()
					.filter(cheque -> cheque.getDestinario().equals(nombreConsulta)).collect(Collectors.toList());
		} else if (folioConsulta == null && mesConsulta == null && tipoChequeConsulta != ' '
				&& StringUtils.isNotEmpty(statusCheque) && StringUtils.isEmpty(nombreConsulta)) {
			registroPagoCheques = registroPagoCheques.stream()
					.filter(cheque -> cheque.getTcEstatusCheque().getNombre().equals("PAGADO")
							|| cheque.getTcEstatusCheque().getNombre().equals("CANCELADO"))
					.collect(Collectors.toList());
		} else if (folioConsulta == null && mesConsulta != null && tipoChequeConsulta != ' '
				&& StringUtils.isEmpty(statusCheque) && StringUtils.isEmpty(nombreConsulta)) {
			registroPagoCheques = registroPagoCheques.stream()
					.filter(cheque -> cheque.getTcPeriodo().getPeriodo().equals(mesConsulta))
					.collect(Collectors.toList());
		} else {
			registroPagoCheques = registroPagoCheques.stream().filter(cheque -> cheque.getFolio().equals(folioConsulta))
					.filter(cheque -> cheque.getTcEstatusCheque().getNombre().equals("PAGADO")
							|| cheque.getTcEstatusCheque().getNombre().equals("CANCELADO"))
					.filter(cheque -> cheque.getTcPeriodo().getPeriodo().equals(mesConsulta))
					.filter(cheque -> cheque.getDestinario().equals(nombreConsulta))
					.filter(cheque -> cheque.getTcTipoCheque().getClaveCheque() == 'e'
							|| cheque.getTcTipoCheque().getClaveCheque() == 'g'
							|| cheque.getTcTipoCheque().getClaveCheque() == 'p')
					.collect(Collectors.toList());
		}
		modelGeneric.setListT(registroPagoCheques);
	}

	public void cleanConsultElements() {
		folioConsulta = null;
		mesConsulta = meses.get(0).getPeriodo();
		nombreConsulta = StringUtils.EMPTY;
	}

	public void consultarCheques() {
		registroPagoCheques = this.registroPagoChequeService.findAll();
		modelGeneric = new DataModelGeneric<TwRegistroPagoCheque>(registroPagoCheques);
	}

	public List<String> findEmpleadoByFullName(String name) {
		String value = name.toUpperCase();
		return empleadoInfoemService.findAll().stream()
				.filter(empleado -> empleado.getNombreCompleto().startsWith(value))
				.map(empleado -> empleado.getNombreCompleto()).collect(Collectors.toList());

	}

	public void changeTipoCheque() {
		nombre = "";
		tipoPagoCad = "Pago";
		rederContrato = false;
		if (tipoRerporte == 'p') {
			nombre = NOMBRE_PROVEEDOR;
			etiqueta = "Proveedor";
			rederContrato = true;
			none = "inline";

		} else if (tipoRerporte == 'e') {
			etiqueta = "Empleado";
			tipoPagoCad = "Quincena";
		}
	}

	public void chageNumbersToLetters() {
		if (monto != null)
			montoLetras = this.empleadoInfoemService.covertedNumbersToLetters(monto.doubleValue());
	}

	public void cleanElements() {
		tipoRerporte = 'e';
		etiqueta = "Empleado";
		nombre = StringUtils.EMPTY;
		concepto = StringUtils.EMPTY;
		monto = null;
		montoLetras = StringUtils.EMPTY;
		folio = null;
	}

	public void validateFolio() {
		if (folio != null) {
			flagActivate = false;
			if (monto.compareTo(BigDecimal.ZERO) > 0) {
				if (contrato.getImporteAdquisicion().compareTo(monto) < 0 && tipoRerporte == 'p') {
					generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error",
							"La cantidad a pagar ya supero el monto del contrato");
				}
				return;
			}
			TwRegistroPagoCheque validate = this.registroPagoChequeService.findByFolio(folio);
			if (validate != null) {
				message = "¡El Folio ya esta Ocupado!";
				generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error", message);
				flagActivate = true;
			}

		}
	}

	public EmpleadoInfoemService getEmpleadoInfoemService() {
		return empleadoInfoemService;
	}

	public void setEmpleadoInfoemService(EmpleadoInfoemService empleadoInfoemService) {
		this.empleadoInfoemService = empleadoInfoemService;
	}

	public Character getTipoRerporte() {
		return tipoRerporte;
	}

	public void setTipoRerporte(Character tipoRerporte) {
		this.tipoRerporte = tipoRerporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getMontoLetras() {
		return montoLetras;
	}

	public void setMontoLetras(String montoLetras) {
		this.montoLetras = montoLetras;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getCurrentDate() {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat dt1 = new SimpleDateFormat("dd MMMM yyyy");
		setCurrentDate(dt1.format(date));
		String dia = separat(currentDate)[0];
		String mes = separat(currentDate)[1].toUpperCase();
		String anio = separat(currentDate)[2];
		setCurrentDate(dia + " DE " + mes + " DE  " + anio);
		return currentDate;
	}

	public String[] separat(String value) {
		String[] fecha = value.split(" ");
		return fecha;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	TwRegistroPagoCheque pago = null;

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> param = new HashMap<>();
		
		contrato = new TcContrato();
		contrato.setNumeroContrato(StringUtils.EMPTY);
		pago = new TwRegistroPagoCheque();
		pago.setDestinario(nombre);
		pago.setConcepto(concepto);
		pago.setFolio(folio);
		pago.setMonto(monto);
		pago.setMontoLetras(montoLetras);
		pago.setCuentaBancaria(cuentaBancaria);
		pago.setNotascheues(notasCheques);
		TcPeriodo auxMes = this.registroPagoChequeService.findByMonthPeriodo().stream()
				.filter(m -> m.getId() == mes.getId()).findFirst().get();
		pago.setTcPeriodo(auxMes);
		pago.setTcEstatusCheque(
				estatusCheques.stream().filter(status -> status.getNombre().equals("PAGADO")).findFirst().get());
		pago.setTcTipoCheque(tipoCheque);
		pago.setNumeroPago(numeroPago);
		contrato = this.registroPagoChequeService.findContratoByNumContrato(nContrato);

		this.registroPagoChequeService.save(pago, contrato);

		param.put("fechaCheque", currentDate);
		param.put("beneficiario", nombre);
		param.put("monto", monto);
		param.put("montoLetras", montoLetras);
		param.put("concepto", concepto);
		cleanElements();
		flagActivate = true;
		monto = BigDecimal.ZERO;
		return param;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void findContrato() {
		if (StringUtils.isNoneEmpty(nContrato))
			contrato = this.registroPagoChequeService.findContratoByNumContrato(nContrato);
	}

	public TcPeriodo getMes() {
		return mes;
	}

	public void setMes(TcPeriodo mes) {
		this.mes = mes;
	}

	public List<TcTipoCheque> getTipoCheques() {
		return tipoCheques;
	}

	public void setTipoCheques(List<TcTipoCheque> tipoCheques) {
		this.tipoCheques = tipoCheques;
	}

	public TcTipoCheque getTipoCheque() {
		return tipoCheque;
	}

	public void setTipoCheque(TcTipoCheque tipoCheque) {
		this.tipoCheque = tipoCheque;
	}

	public List<TcEstatusCheque> getEstatusCheques() {
		return estatusCheques;
	}

	public void setEstatusCheques(List<TcEstatusCheque> estatusCheques) {
		this.estatusCheques = estatusCheques;
	}

	public TcEstatusCheque getEstatusCheque() {
		return estatusCheque;
	}

	public void setEstatusCheque(TcEstatusCheque estatusCheque) {
		this.estatusCheque = estatusCheque;
	}

	public RegistroPagoChequeService getRegistroPagoChequeService() {
		return registroPagoChequeService;
	}

	public void setRegistroPagoChequeService(RegistroPagoChequeService registroPagoChequeService) {
		this.registroPagoChequeService = registroPagoChequeService;
	}

	public List<TcPeriodo> getMeses() {
		return meses;
	}

	public void setMeses(List<TcPeriodo> meses) {
		this.meses = meses;
	}

	public static Map<Object, Object> getMappagos() {
		return mapPagos;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNumeroPago() {
		return numeroPago;
	}

	public void setNumeroPago(String numeroPago) {
		this.numeroPago = numeroPago;
	}

	public boolean isFlagActivate() {
		return flagActivate;
	}

	public void setFlagActivate(boolean flagActivate) {
		this.flagActivate = flagActivate;
	}

	public List<TwRegistroPagoCheque> getRegistroPagoCheques() {
		return registroPagoCheques;
	}

	public void setRegistroPagoCheques(List<TwRegistroPagoCheque> registroPagoCheques) {
		this.registroPagoCheques = registroPagoCheques;
	}

	public Integer getFolioConsulta() {
		return folioConsulta;
	}

	public void setFolioConsulta(Integer folioConsulta) {
		this.folioConsulta = folioConsulta;
	}

	public Integer getMesConsulta() {
		return mesConsulta;
	}

	public void setMesConsulta(Integer mesConsulta) {
		this.mesConsulta = mesConsulta;
	}

	public String getNombreConsulta() {
		return nombreConsulta;
	}

	public void setNombreConsulta(String nombreConsulta) {
		this.nombreConsulta = nombreConsulta;
	}

	public String getStatusCheque() {
		return statusCheque;
	}

	public void setStatusCheque(String statusCheque) {
		this.statusCheque = statusCheque;
	}

	public Character getTipoChequeConsulta() {
		return tipoChequeConsulta;
	}

	public void setTipoChequeConsulta(Character tipoChequeConsulta) {
		this.tipoChequeConsulta = tipoChequeConsulta;
	}

	public List<String> getSelctOptions() {
		return selctOptions;
	}

	public void setSelctOptions(List<String> selctOptions) {
		this.selctOptions = selctOptions;
	}

	public DataModelGeneric<TwRegistroPagoCheque> getModelGeneric() {
		return modelGeneric;
	}

	public void setModelGeneric(DataModelGeneric<TwRegistroPagoCheque> modelGeneric) {
		this.modelGeneric = modelGeneric;
	}

	public boolean isEditableTable() {
		return editableTable;
	}

	public void setEditableTable(boolean editableTable) {
		this.editableTable = editableTable;
	}

	public TwRegistroPagoCheque getSelectPagoCheque() {
		return selectPagoCheque;
	}

	public void setSelectPagoCheque(TwRegistroPagoCheque selectPagoCheque) {
		this.selectPagoCheque = selectPagoCheque;
	}

	public TwRegistroPagoCheque getCancelTregistroCheque() {
		return cancelTregistroCheque;
	}

	public void setCancelTregistroCheque(TwRegistroPagoCheque cancelTregistroCheque) {
		this.cancelTregistroCheque = cancelTregistroCheque;
	}

	public String getTipoPagoCad() {
		return tipoPagoCad;
	}

	public void setTipoPagoCad(String tipoPagoCad) {
		this.tipoPagoCad = tipoPagoCad;
	}

	public Long getNumeroPagados() {
		return numeroPagados;
	}

	public void setNumeroPagados(Long numeroPagados) {
		this.numeroPagados = numeroPagados;
	}

	public Long getNumeroCancelados() {
		return numeroCancelados;
	}

	public void setNumeroCancelados(Long numeroCancelados) {
		this.numeroCancelados = numeroCancelados;
	}

	public TcContrato getContrato() {
		return contrato;
	}

	public void setContrato(TcContrato contrato) {
		this.contrato = contrato;
	}

	public boolean isRederContrato() {
		return rederContrato;
	}

	public void setRederContrato(boolean rederContrato) {
		this.rederContrato = rederContrato;
	}

	public String getNone() {
		return none;
	}

	public void setNone(String none) {
		this.none = none;
	}

	public String getnContrato() {
		return nContrato;
	}

	public void setnContrato(String nContrato) {
		this.nContrato = nContrato;
	}

	private StreamedContent fileCheques;
	InputStream stream3 = null;
	String nameFile = "";

	public StreamedContent getFileCheques() {
		Map<String, Object> map = new HashMap<>();

		TcPeriodo mesAux = this.registroPagoChequeService.findByMonthPeriodo().stream()
				.filter(b -> b.getId().equals(mes.getId())).findFirst().get();
		map.put("mes", mesAux);
		nameFile = this.reporteChequeContratoService.createReport(map);
		/*
		 * InputStream stream =
		 * FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(
		 * PATH_OUT + "reporte.xls"); fileCheques = new DefaultStreamedContent(stream,
		 * "application/vnd.ms-excel", "reporte.xls");
		 */
		File file = new File(nameFile);
		try {
			stream3 = new FileInputStream(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileCheques = new DefaultStreamedContent(stream3, "application/vnd.ms-excel", file.getName());
		return fileCheques;
	}

	public void setFileCheques(StreamedContent fileCheques) {
		this.fileCheques = fileCheques;
	}

	public ReporteChequeContratoService getReporteChequeContratoService() {
		return reporteChequeContratoService;
	}

	public void setReporteChequeContratoService(ReporteChequeContratoService reporteChequeContratoService) {
		this.reporteChequeContratoService = reporteChequeContratoService;
	}

	public List<String> getListCuentaBancaria() {
		return listCuentaBancaria;
	}

	public void setListCuentaBancaria(List<String> listCuentaBancaria) {
		this.listCuentaBancaria = listCuentaBancaria;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
