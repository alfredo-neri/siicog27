package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TwCheque;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TwChequeRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.NumberToLetter;
import com.gem.sistema.util.UtilFront;

@Scope("view")
@Component("impresionChequeMB")
public class ImpresionChequeMB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String CLAVE_REPORT = "MOD_IMPRESION_CHEQUE";

	private Integer mes;
	private String tipo;
	private Integer numero;
	private Integer numero2;
	private Integer cheque;
	private Integer tipoGasto;
	private BigDecimal monto = BigDecimal.ZERO; // new BigDecimal();
	private String gastoText;
	private String beneficiario;
	private TwCheque twCheque;
	private Map<String, Object> parameters = null;
	private Boolean searchByFecha = Boolean.TRUE;
	private Boolean searchByPoliza = Boolean.FALSE;
	private Boolean searchByCheque = Boolean.FALSE;
	private Boolean searchByMes = Boolean.FALSE;
	private Boolean searchByRango = Boolean.FALSE;
	private Boolean pdf = Boolean.FALSE;

	private Integer searchBy;
	private Date date;
	private Date newFecpol;

	private static final String QUERY_LIST = " SELECT D.TIPPOL, D.MESPOL, D.CONPOL, C.NUMERO_CHEQUE, C.MONTO_CHEQUE, "
			+ " C.USERID, C.BENEFICIARIO,C.FECHA_ELABORO "
			+ "	FROM TW_CHEQUES C INNER JOIN POLIDE D ON C.ID_POLIDE = D.ID "
			+ " WHERE D.IDSECTOR = 2 	AND D.CANPOL+D.CANPOLH<>0 ";

	private List<TcPeriodo> listMeses;
	private List<Cattpo> listCattpo;

	@Value("#{cattpoRepository}")
	private CattpoRepository cattpoRepositry;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{polideRepository}")
	private PolideRepository polideRepository;

	@Value("#{polienRepository}")
	private PolienRepository polienRepository;

	@Value("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	@Value("#{twChequeRepository}")
	private TwChequeRepository chequeRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@PostConstruct
	public void init() {
		listMeses = periodoRepositoy.findByTipoPeriodo(1);
		listCattpo = cattpoRepositry.findAll();

		if (!listMeses.isEmpty())
			mes = listMeses.get(0).getPeriodo();

		if (!listCattpo.isEmpty())
			tipo = listCattpo.get(0).getTippol();

		searchBy = 1;
		searchByFecha = Boolean.TRUE;
		searchByPoliza = Boolean.FALSE;
		searchByCheque = Boolean.FALSE;

		jasperReporteName = "cheque";
		endFilename = jasperReporteName + ".pdf";
	}

	public void changeBeneficiario() {
		if (numero != null) {
			Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
			Polide polide = this
					.selectPolide(polideRepository.findAllByIdsectorAndTippolAndConpolAndMespolAndAnopolAndCuenta(
							this.getUserDetails().getIdSector(), tipo, numero, mes, conctb.getAnoemp(), "1112"));
			if (null != polide) {
				beneficiario = polide.getConcep();
			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
						"La póliza no existe, o no tiene cuentas de banco.");
			}
		}
	}

	public void changeText() {
		switch (tipoGasto) {
		case 0:
			gastoText = StringUtils.EMPTY;
			break;
		case 1:
			gastoText = "CONFORME A LA DAP-047 DEL ACUERDO CON EL QUE SE ESTABLECEN LAS NORMAS ADMINISTRATIVAS PARA LA ASIGNACIÓN Y USO DE BIENES Y SERVICIOS DE LAS DEPENDENCIAS Y ORGANISMOS AUXILIARES DEL PODER EJECUTIVO ESTATAL VIGENTE ME COMPROMETO A COMPROBAR A MAS TARDAR EL DÍA";
			break;
		case 2:
			gastoText = "CONFORME AL ARTICULO 83 DEL MANUAL DE NORMAS Y POLÍTICAS PARA EL GASTO PUBLICO DEL GOBIERNO DEL ESTADO DE MÉXICO VIGENTE, ME COMPROMETO A COMPROBAR A MAS TARDAR EL DÍA ";
			break;
		default:
			gastoText = StringUtils.EMPTY;
			break;
		}
	}

	public void preview() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		Polide polide = this
				.selectPolide(polideRepository.findAllByIdsectorAndTippolAndConpolAndMespolAndAnopolAndCuenta(
						this.getUserDetails().getIdSector(), tipo, numero, mes, conctb.getAnoemp(), "1112"));
		if (null != polide) {
			parameters = new java.util.HashMap<String, Object>();
			parameters.put("preview", Boolean.TRUE);

			this.twCheque = new TwCheque();
			this.twCheque.setNumeroCheque(cheque);
			this.twCheque.setMontoCheque(
					polide.getCanpolh().compareTo(BigDecimal.ZERO) == 1 ? polide.getCanpolh() : polide.getCanpol());
			this.twCheque.setFechaElaboro(polide.getFecpol());
			this.twCheque.setBeneficiario(beneficiario);
			this.twCheque.setObservaciones(gastoText);
			this.twCheque.setUserid(this.getUserDetails().getUsername());
			this.twCheque.setPolide(polide);
			this.armadoReporte();
			this.createFilePdfInline();
		} else {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"La póliza no existe, o no tiene cuentas de banco.");
		}

	}

	public void save() {
		Boolean continuar = Boolean.TRUE;

		if (numero == null || numero < 1) {
			continuar = Boolean.FALSE;
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Favor de Ingresar el Número de Póliza.");
		}

		if (cheque == null || cheque < 1) {
			continuar = Boolean.FALSE;
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Favor de Ingresar el Número de Cheque.");
		}

		if (tipoGasto == null) {
			continuar = Boolean.FALSE;
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Favor de selecionar algún Tipo de Gasto.");
		}

		if (continuar) {
			parameters = new java.util.HashMap<String, Object>();
			TwCheque twCheque = chequeRepository.findByNumeroCheque(cheque);
			Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

			if (null == twCheque) {
				twCheque = chequeRepository.findByPolideTippolAndPolideMespolAndPolideConpolAndPolideIdsector(tipo, mes,
						numero, this.getUserDetails().getIdSector());

				if (null == twCheque) {

					Polide polide = this.selectPolide(
							polideRepository.findAllByIdsectorAndTippolAndConpolAndMespolAndAnopolAndCuenta(
									this.getUserDetails().getIdSector(), tipo, numero, mes, conctb.getAnoemp(),
									"1112"));
					if (null != polide) {
						TwCheque cheque = new TwCheque();
						cheque.setMontoCheque(polide.getCanpolh().compareTo(BigDecimal.ZERO) == 1 ? polide.getCanpolh()
								: polide.getCanpol());
						cheque.setFechaElaboro(polide.getFecpol());
						cheque.setNumeroCheque(this.cheque);
						cheque.setObservaciones(gastoText);
						cheque.setBeneficiario(beneficiario);
						cheque.setUserid(this.getUserDetails().getUsername());
						cheque.setPolide(polide);
						this.twCheque = chequeRepository.save(cheque);

						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Success",
								"Cheque generado correctamente");

						parameters.put("preview", Boolean.FALSE);
						this.asignar();
						this.armadoReporte();
						this.createFilePdfInline();
					} else {
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
								"La póliza no existe o no tiene cuentas de banco.");
					}

				} else {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
							"Ya se ha generado con anterioridad un cheque para esta póliza");
				}
			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
						"El número de cheque ya fue previamente capturado");
			}
		}

	}

	public void changeSearch() {
		switch (searchBy) {
		case 1: // Fecha
			searchBy = 1;
			searchByFecha = Boolean.TRUE;
			searchByPoliza = Boolean.FALSE;
			searchByCheque = Boolean.FALSE;
			searchByMes = Boolean.FALSE;
			searchByRango = Boolean.FALSE;
			break;
		case 2: // Poliza
			searchBy = 2;
			searchByFecha = Boolean.FALSE;
			searchByPoliza = Boolean.TRUE;
			searchByCheque = Boolean.FALSE;
			searchByMes = Boolean.FALSE;
			searchByRango = Boolean.FALSE;
			break;
		case 3: // Numero Cheque
			searchBy = 3;
			searchByFecha = Boolean.FALSE;
			searchByPoliza = Boolean.FALSE;
			searchByCheque = Boolean.TRUE;
			searchByMes = Boolean.FALSE;
			searchByRango = Boolean.FALSE;
			break;
		case 4: // Mes
			searchBy = 4;
			searchByFecha = Boolean.FALSE;
			searchByPoliza = Boolean.FALSE;
			searchByCheque = Boolean.FALSE;
			searchByMes = Boolean.TRUE;
			searchByRango = Boolean.FALSE;
			break;

		case 5: // Rango
			searchBy = 5;
			searchByFecha = Boolean.FALSE;
			searchByPoliza = Boolean.FALSE;
			searchByCheque = Boolean.FALSE;
			searchByMes = Boolean.FALSE;
			searchByRango = Boolean.TRUE;
			break;
		}
	}

	public void onDateSelect(SelectEvent event) {
		this.newFecpol = (Date) event.getObject();
		if (null == this.date) {
			generateNotificationFront(FacesMessage.SEVERITY_FATAL, MESSAGE_ERROR, "Favor de capturar una fecha valida");
			PrimeFaces.current().executeScript("errorChangeDate();");
		}
	}

	private Polide selectPolide(List<Polide> polides) {

		Polide polide = null;

		if (polides.size() == 1)
			polide = polides.get(0);

		if (polides.size() >= 2) {
			for (Polide detalle : polides) {
				if (detalle.getCanpolh().compareTo(BigDecimal.ZERO) == 1)
					polide = detalle;
			}
		}

		return polide;
	}

	public void search() {
		pdf = Boolean.TRUE;
		parameters = new java.util.HashMap<String, Object>();
		switch (searchBy) {
		case 1:// Fecha
		case 4:// Mes
		case 5:// Rango de polizas
			if (searchBy == 5) {
				if (numero2 < numero) {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
							"el segundo consecutido no puede ser menor al primero");
					break;
				}
			}
			this.reportList();
			break;
		case 2: // Poliza
			twCheque = chequeRepository.findByPolideTippolAndPolideMespolAndPolideConpolAndPolideIdsector(tipo, mes,
					numero, this.getUserDetails().getIdSector());

			if (null != twCheque) {
				this.armadoReporte();
				this.createFilePdfInline();
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Success",
						"Cheque generado correctamente");
			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
						"No se ha generado ningún cheque para esta póliza");
			}
			break;

		case 3: // Cheque
			twCheque = chequeRepository.findByNumeroCheque(cheque);

			if (null != twCheque) {
				this.armadoReporte();
				this.createFilePdfInline();
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Success",
						"Cheque generado correctamente");
			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
						"No se ha generado ningún cheque con ese número");
			}
			break;
		}

	}

	private void reportList() {
		this.jasperReporteName = "chequeList";
//		this.endFilename = this.jasperReporteName +  ".pdf";

		parameters = new java.util.HashMap<String, Object>();
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		switch (searchBy) {
		case 1:
			parameters.put("query",
					QUERY_LIST + " AND C.FECHA_ELABORO = '" + new SimpleDateFormat("dd/MM/yyyy").format(date) + "'");
			break;
		case 4:
			parameters.put("query", QUERY_LIST + " AND MONTH(C.FECHA_ELABORO) =" + mes);
			break;
		case 5:
			parameters.put("query", QUERY_LIST + " 	AND D.TIPPOL='" + tipo + "' AND D.MESPOL=" + mes
					+ " AND  D.CONPOL BETWEEN " + numero + " AND " + numero2);
			break;
		}

		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("sector", this.getUserDetails().getIdSector());
		if (pdf)
			this.createFilePdfInline();
		UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Success", "Reporte generado correctamente");
	}

	private void armadoReporte() {
		this.jasperReporteName = "cheque";
		Integer idSector = this.getUserDetails().getIdSector();
		NumberToLetter letter = new NumberToLetter();
		Polide polide = this.twCheque.getPolide();
		String cuentano = StringUtils.EMPTY;
		String banconame = StringUtils.EMPTY;

		if (polide != null) {
//			beneficiario = polide.getConcep();

			Cuenta cuenta = cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(
					polide.getCuenta(), polide.getScta(), polide.getSscta(), polide.getSsscta(), polide.getSssscta(),
					Long.valueOf(idSector));
			Cuenta banco = cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(
					polide.getCuenta(), polide.getScta(), "", "", "", Long.valueOf(idSector));

			cuentano = cuenta.getNomcta().split("\\.")[1].trim().split("\\(")[0].split(" ")[0] + " "
					+ banco.getNomcta();
			beneficiario = beneficiario == null ? this.twCheque.getBeneficiario() : beneficiario;
			banconame = beneficiario + " " + cuenta.getNomcta();
		}

		parameters.put("tipo", this.twCheque.getPolide().getTippol());
		parameters.put("mes", this.twCheque.getPolide().getMespol());
		parameters.put("monto", this.twCheque.getMontoCheque());
		parameters.put("montoLetter",
				letter.Convertir(this.twCheque.getMontoCheque().setScale(2).toPlainString(), true));
		parameters.put("numero", this.twCheque.getPolide().getConpol());
		parameters.put("cheque", this.twCheque.getNumeroCheque());
		parameters.put("observaciones", this.twCheque.getObservaciones());
		parameters.put("beneficiario", this.twCheque.getBeneficiario());
		parameters.put("cuentaNo", cuentano);
		parameters.put("banco", banconame);
		parameters.put("fechaElaboracion", polide != null ? polide.getFecpol() : this.twCheque.getFechaElaboro());

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);

		parameters.put("imagePath1", conctb.getImagePathLeft());
		parameters.put("imagePath2", conctb.getImagePathRigth());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	public void asignar() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		Boolean continuar = Boolean.TRUE;

		if (numero == null || numero < 1) {
			continuar = Boolean.FALSE;
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Favor de Ingresar el Número de Póliza.");
		}

		if (cheque == null || cheque < 1) {
			continuar = Boolean.FALSE;
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Favor de Ingresar el Número de Cheque.");
		}

		if (continuar) {
			Polide polide = this
					.selectPolide(polideRepository.findAllByIdsectorAndTippolAndConpolAndMespolAndAnopolAndCuenta(
							this.getUserDetails().getIdSector(), tipo, numero, mes, conctb.getAnoemp(), "1112"));
			if (null != polide) {
				polide.setRefpol(cheque.toString());
				polideRepository.save(polide);
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info",
						"Se ha asignado el numero de cheque correctamente");
			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
						"La póliza no existe, o no tiene cuentas de banco.");
			}
		}
	}

	public void downloadXls() {
		pdf = Boolean.FALSE;
		parameters = new java.util.HashMap<String, Object>();
		switch (searchBy) {
		case 1: // Fecha
		case 4:
		case 5:
			this.reportList();
			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
			break;
		case 2: // Poliza
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Excel no disponible para la opción actual");
			break;
		case 3: // Cheque
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
					"Excel no disponible para la opción actual");
			break;
		}

	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public List<Cattpo> getListCattpo() {
		return listCattpo;
	}

	public void setListCattpo(List<Cattpo> listCattpo) {
		this.listCattpo = listCattpo;
	}

	public CattpoRepository getCattpoRepositry() {
		return cattpoRepositry;
	}

	public void setCattpoRepositry(CattpoRepository cattpoRepositry) {
		this.cattpoRepositry = cattpoRepositry;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public Integer getCheque() {
		return cheque;
	}

	public void setCheque(Integer cheque) {
		this.cheque = cheque;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Integer getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(Integer tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public String getGastoText() {
		return gastoText;
	}

	public void setGastoText(String gastoText) {
		this.gastoText = gastoText;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public TwChequeRepository getChequeRepository() {
		return chequeRepository;
	}

	public TwCheque getTwCheque() {
		return twCheque;
	}

	public void setTwCheque(TwCheque twCheque) {
		this.twCheque = twCheque;
	}

	public Boolean getSearchByFecha() {
		return searchByFecha;
	}

	public void setSearchByFecha(Boolean searchByFecha) {
		this.searchByFecha = searchByFecha;
	}

	public Boolean getSearchByPoliza() {
		return searchByPoliza;
	}

	public void setSearchByPoliza(Boolean searchByPoliza) {
		this.searchByPoliza = searchByPoliza;
	}

	public Boolean getSearchByCheque() {
		return searchByCheque;
	}

	public void setSearchByCheque(Boolean searchByCheque) {
		this.searchByCheque = searchByCheque;
	}

	public Boolean getSearchByMes() {
		return searchByMes;
	}

	public void setSearchByMes(Boolean searchByMes) {
		this.searchByMes = searchByMes;
	}

	public Integer getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(Integer searchBy) {
		this.searchBy = searchBy;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getNewFecpol() {
		return newFecpol;
	}

	public void setNewFecpol(Date newFecpol) {
		this.newFecpol = newFecpol;
	}

	public void setChequeRepository(TwChequeRepository chequeRepository) {
		this.chequeRepository = chequeRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public Boolean getSearchByRango() {
		return searchByRango;
	}

	public void setSearchByRango(Boolean searchByRango) {
		this.searchByRango = searchByRango;
	}

	public Integer getNumero2() {
		return numero2;
	}

	public void setNumero2(Integer numero2) {
		this.numero2 = numero2;
	}

}
