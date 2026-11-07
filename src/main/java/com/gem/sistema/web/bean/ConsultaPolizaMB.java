package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliend;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcRegistraArchivoDigital;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcRegistraArchivoDigitalRepository;
import com.gem.sistema.business.service.catalogos.ConsultaPolizaSirveImpl;
import com.gem.sistema.business.service.catalogos.PolizaService;
import com.gem.sistema.business.service.catalogos.PolizaServiceImpl;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaPolizaMB.
 */
@ManagedBean(name = "consultaPolizaMB")
@ViewScoped
public class ConsultaPolizaMB extends AbstractMB implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaPolizaMB.class);

	/** The list polide. */
	private List<Polide> listPolide;
	
	/** The list meses. */
	private List<TcMes> listMeses;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The tc mes. */
	private TcMes tcMes;

	/** The lis cattpo. */
	private List<Cattpo> lisCattpo;
	
	/** The cattpo. */
	private Cattpo cattpo;
	
	/** The img policy. */
	private List<String> imgPolicy;
	
	/** The list numero. */
	private List<String> listNumero;
	
	/** The filter polide. */
	private List<Polide> filterPolide;
	
	/** The year. */
	private Integer year;
	
	/** The numero. */
	private Integer numero;
	
	/** The list polien. */
	private List<Polien> listPolien;
	
	/** The polien. */
	private Polien polien;
	
	/** The meses value. */
	private String mesesValue;
	
	/** The id user. */
	private String idUser;
	
	/** The me. */
	private Integer me;
	
	/** The ti. */
	private String ti;
	
	/** The poliend. */
	private Poliend poliend;
	
	/** The cuenta. */
	private Cuenta cuenta;
	
	/** The renglo. */
	private BigDecimal renglo;
	
	/** The list cuenta. */
	private List<Cuenta> listCuenta;

	/** The exis digital. */
	@ManagedProperty("#{tcRegistraArchivoDigitalRepository}")
	private TcRegistraArchivoDigitalRepository exisDigital;

	/**
	 * Gets the exis digital.
	 *
	 * @return the exis digital
	 */
	public TcRegistraArchivoDigitalRepository getExisDigital() {
		return exisDigital;
	}

	/**
	 * Sets the exis digital.
	 *
	 * @param exisDigital the new exis digital
	 */
	public void setExisDigital(TcRegistraArchivoDigitalRepository exisDigital) {
		this.exisDigital = exisDigital;
	}

	/**
	 * Gets the list cuenta.
	 *
	 * @return the list cuenta
	 */
	public List<Cuenta> getListCuenta() {
		return listCuenta;
	}

	/**
	 * Sets the list cuenta.
	 *
	 * @param listCuenta the new list cuenta
	 */
	public void setListCuenta(List<Cuenta> listCuenta) {
		this.listCuenta = listCuenta;
	}

	/**
	 * Gets the renglo.
	 *
	 * @return the renglo
	 */
	public BigDecimal getRenglo() {
		return renglo;
	}

	/**
	 * Sets the renglo.
	 *
	 * @param renglo the new renglo
	 */
	public void setRenglo(BigDecimal renglo) {
		this.renglo = renglo;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/** The graphic image. */
	private StreamedContent graphicImage;

	/** The streamed content. */
	private StreamedContent streamedContent;

	/**
	 * Gets the streamed content.
	 *
	 * @return the streamed content
	 */
	public StreamedContent getStreamedContent() {
		if (streamedContent != null)
			try {
				streamedContent.getStream().reset();
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		return streamedContent;
	}

	/**
	 * Sets the streamed content.
	 *
	 * @param streamedContent the new streamed content
	 */
	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

	/**
	 * Gets the graphic image.
	 *
	 * @return the graphic image
	 */
	public StreamedContent getGraphicImage() {
		return graphicImage;
	}

	/**
	 * Sets the graphic image.
	 *
	 * @param graphicImage the new graphic image
	 */
	public void setGraphicImage(StreamedContent graphicImage) {
		this.graphicImage = graphicImage;
	}

	/**
	 * Gets the poliend.
	 *
	 * @return the poliend
	 */
	public Poliend getPoliend() {
		return poliend;
	}

	/**
	 * Sets the poliend.
	 *
	 * @param poliend the new poliend
	 */
	public void setPoliend(Poliend poliend) {
		this.poliend = poliend;
	}

	/** The list incorrectas. */
	private List<Poliend> listIncorrectas;

	/**
	 * Gets the list incorrectas.
	 *
	 * @return the list incorrectas
	 */
	public List<Poliend> getListIncorrectas() {
		return listIncorrectas;
	}

	/**
	 * Sets the list incorrectas.
	 *
	 * @param listIncorrectas the new list incorrectas
	 */
	public void setListIncorrectas(List<Poliend> listIncorrectas) {
		this.listIncorrectas = listIncorrectas;
	}

	/** The consulta poliza sirve impl. */
	@ManagedProperty("#{consultaPolizaSirveImpl}")
	private ConsultaPolizaSirveImpl consultaPolizaSirveImpl;

	/** The poliza service. */
	@ManagedProperty("#{polizaService}")
	private PolizaServiceImpl polizaService;

	/**
	 * Gets the poliza service.
	 *
	 * @return the poliza service
	 */
	public PolizaServiceImpl getPolizaService() {
		return polizaService;
	}

	/**
	 * Sets the poliza service.
	 *
	 * @param polizaService the new poliza service
	 */
	public void setPolizaService(PolizaServiceImpl polizaService) {
		this.polizaService = polizaService;
	}

	/**
	 * Gets the consulta poliza sirve impl.
	 *
	 * @return the consulta poliza sirve impl
	 */
	public ConsultaPolizaSirveImpl getConsultaPolizaSirveImpl() {
		return consultaPolizaSirveImpl;
	}

	/**
	 * Sets the consulta poliza sirve impl.
	 *
	 * @param consultaPolizaSirveImpl the new consulta poliza sirve impl
	 */
	public void setConsultaPolizaSirveImpl(ConsultaPolizaSirveImpl consultaPolizaSirveImpl) {
		this.consultaPolizaSirveImpl = consultaPolizaSirveImpl;
	}

	/**
	 * Gets the me.
	 *
	 * @return the me
	 */
	public Integer getMe() {
		return me;
	}

	/**
	 * Sets the me.
	 *
	 * @param me the new me
	 */
	public void setMe(Integer me) {
		this.me = me;
	}

	/**
	 * Gets the ti.
	 *
	 * @return the ti
	 */
	public String getTi() {
		return ti;
	}

	/**
	 * Sets the ti.
	 *
	 * @param ti the new ti
	 */
	public void setTi(String ti) {
		this.ti = ti;
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the meses value.
	 *
	 * @return the meses value
	 */
	public String getMesesValue() {
		return mesesValue;
	}

	/**
	 * Sets the meses value.
	 *
	 * @param mesesValue the new meses value
	 */
	public void setMesesValue(String mesesValue) {
		this.mesesValue = mesesValue;
	}

	/**
	 * Gets the polien.
	 *
	 * @return the polien
	 */
	public Polien getPolien() {
		return polien;
	}

	/**
	 * Sets the polien.
	 *
	 * @param polien the new polien
	 */
	public void setPolien(Polien polien) {
		this.polien = polien;
	}

	/**
	 * Gets the list polien.
	 *
	 * @return the list polien
	 */
	public List<Polien> getListPolien() {
		return listPolien;
	}

	/**
	 * Sets the list polien.
	 *
	 * @param listPolien the new list polien
	 */
	public void setListPolien(List<Polien> listPolien) {
		this.listPolien = listPolien;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Gets the filter polide.
	 *
	 * @return the filter polide
	 */
	public List<Polide> getFilterPolide() {
		return filterPolide;
	}

	/**
	 * Sets the filter polide.
	 *
	 * @param filterPolide the new filter polide
	 */
	public void setFilterPolide(List<Polide> filterPolide) {
		this.filterPolide = filterPolide;
	}

	/**
	 * Gets the list numero.
	 *
	 * @return the list numero
	 */
	public List<String> getListNumero() {
		return listNumero;
	}

	/**
	 * Sets the list numero.
	 *
	 * @param listNumero the new list numero
	 */
	public void setListNumero(List<String> listNumero) {
		this.listNumero = listNumero;
	}

	/** The cattpo repositry. */
	@ManagedProperty("#{cattpoRepository}")
	private CattpoRepository cattpoRepositry;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The cuenta repository. */
	@ManagedProperty("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/** The archivo repository. */
	@ManagedProperty("#{tcRegistraArchivoDigitalRepository}")
	private TcRegistraArchivoDigitalRepository archivoRepository;

	/** The polide repository. */
	@ManagedProperty("#{polideRepository}")
	private PolideRepository polideRepository;

	/** The polien repository. */
	@ManagedProperty("#{polienRepository}")
	private PolienRepository polienRepository;

	/**
	 * Gets the polien repository.
	 *
	 * @return the polien repository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository the new polien repository
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	/**
	 * Gets the polide repository.
	 *
	 * @return the polide repository
	 */
	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	/**
	 * Sets the polide repository.
	 *
	 * @param polideRepository the new polide repository
	 */
	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	/**
	 * Gets the list polide.
	 *
	 * @return the list polide
	 */
	public List<Polide> getListPolide() {
		return listPolide;
	}

	/**
	 * Sets the list polide.
	 *
	 * @param listPolide the new list polide
	 */
	public void setListPolide(List<Polide> listPolide) {
		this.listPolide = listPolide;
	}

	/**
	 * Gets the list meses.
	 *
	 * @return the list meses
	 */
	public List<TcMes> getListMeses() {
		return listMeses;
	}

	/**
	 * Sets the list meses.
	 *
	 * @param listMeses the new list meses
	 */
	public void setListMeses(List<TcMes> listMeses) {
		this.listMeses = listMeses;
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	/**
	 * Gets the tc mes.
	 *
	 * @return the tc mes
	 */
	public TcMes getTcMes() {
		return tcMes;
	}

	/**
	 * Sets the tc mes.
	 *
	 * @param tcMes the new tc mes
	 */
	public void setTcMes(TcMes tcMes) {
		this.tcMes = tcMes;
	}

	/**
	 * Gets the lis cattpo.
	 *
	 * @return the lis cattpo
	 */
	public List<Cattpo> getLisCattpo() {
		return lisCattpo;
	}

	/**
	 * Sets the lis cattpo.
	 *
	 * @param lisCattpo the new lis cattpo
	 */
	public void setLisCattpo(List<Cattpo> lisCattpo) {
		this.lisCattpo = lisCattpo;
	}

	/**
	 * Gets the cattpo.
	 *
	 * @return the cattpo
	 */
	public Cattpo getCattpo() {
		return cattpo;
	}

	/**
	 * Sets the cattpo.
	 *
	 * @param cattpo the new cattpo
	 */
	public void setCattpo(Cattpo cattpo) {
		this.cattpo = cattpo;
	}

	/**
	 * Gets the img policy.
	 *
	 * @return the img policy
	 */
	public List<String> getImgPolicy() {
		return imgPolicy;
	}

	/**
	 * Sets the img policy.
	 *
	 * @param imgPolicy the new img policy
	 */
	public void setImgPolicy(List<String> imgPolicy) {
		this.imgPolicy = imgPolicy;
	}

	/**
	 * Gets the cattpo repositry.
	 *
	 * @return the cattpo repositry
	 */
	public CattpoRepository getCattpoRepositry() {
		return cattpoRepositry;
	}

	/**
	 * Sets the cattpo repositry.
	 *
	 * @param cattpoRepositry the new cattpo repositry
	 */
	public void setCattpoRepositry(CattpoRepository cattpoRepositry) {
		this.cattpoRepositry = cattpoRepositry;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Gets the cuenta repository.
	 *
	 * @return the cuenta repository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository the new cuenta repository
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Gets the archivo repository.
	 *
	 * @return the archivo repository
	 */
	public TcRegistraArchivoDigitalRepository getArchivoRepository() {
		return archivoRepository;
	}

	/**
	 * Sets the archivo repository.
	 *
	 * @param archivoRepository the new archivo repository
	 */
	public void setArchivoRepository(TcRegistraArchivoDigitalRepository archivoRepository) {
		this.archivoRepository = archivoRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		listMes = tcMesRepository.findAll();
		lisCattpo = cattpoRepositry.findAll();
		polien = polienRepository.getByFirstMonth(this.getUserDetails().getIdSector());
		listPolide = polideRepository.getByAllDetail(polien.getMespol(), polien.getConpol(), polien.getTippol(),
				this.getUserDetails().getIdSector());
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);

	}

	/**
	 * On pega load.
	 */
	public void onPegaLoad() {
		idUser = this.getUserDetails().getUsername();
		LOGGER.info("id Users:::: " + idUser);
	}

	/** The image id. */
	private Long imageId = 0l;

	/**
	 * Gets the image id.
	 *
	 * @return the imageId
	 */
	public Long getImageId() {
		return imageId;
	}

	/**
	 * Sets the image id.
	 *
	 * @param imageId            the imageId to set
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/**
	 * Find police.
	 */
	public void findPolice() {
		LOGGER.info("entra a consulta el detalle");

		LOGGER.info("tipo::::::: " + polien.getTippol());
		LOGGER.info("mes::::::: " + polien.getMespol());
		LOGGER.info("tnumero:::: " + polien.getConpol());
		try {
			listPolide = polideRepository.getByAllDetail(polien.getMespol(), polien.getConpol(), polien.getTippol(),
					this.getUserDetails().getIdSector());
			Polien encabezado = polienRepository.getPoliza(polien.getMespol(), polien.getConpol(), polien.getTippol(),
					this.getUserDetails().getIdSector());

			if (encabezado.getTippol() == null) {
				try {

					init();
				} catch (NullPointerException e) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"No existe informacion para esta Poliza");
					FacesContext.getCurrentInstance().addMessage(null, message);

				}
			} else {
				polien = encabezado;
				graphicImage = null;
				try {

					TcRegistraArchivoDigital archivoDigital = exisDigital.getPoliBby(
							String.valueOf(encabezado.getMespol()), encabezado.getConpol(), encabezado.getTippol(),
							this.getUserDetails().getIdSector());
					if (null != archivoDigital) {
						this.imageId = archivoDigital.getId();

					} else {
						this.imageId = 0l;
					}

				} catch (Exception e) {
					e.printStackTrace();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							" La poliza no se encuentra");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			}
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"La poiliza no se encuentra en la base de datos");
			FacesContext.getCurrentInstance().addMessage(null, message);
			polien.setCdebe(null);
			polien.setChaber(null);
			polien.setCtc600(null);
			polien.setCta600(null);
			polien.setCcontrol(null);
			polien.setFeccap(null);
			polien.setStaafe("");
			polien.setStapol("");
			polien.setPolclv1("");
		}

	}

	/**
	 * Find digital poli.
	 */
	public void findDigitalPoli() {
		if (this.imageId > 0) {
			RequestContext.getCurrentInstance().execute("PF('digitalPoliza').show();");

		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					" La poliza aun no esta digitalizada");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * Find wrong policy.
	 */
	public void findWrongPolicy() {
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("Consulta Polizas incorrectas" + me);
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		LOGGER.info("::::::::::::::::::::::::::::::::.");
		if (me == null) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe informacion para esta Poliza"));

		} else {
			listIncorrectas = consultaPolizaSirveImpl.wrongPolicy(me, this.getUserDetails().getIdSector());
			if (listIncorrectas.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error!", "No existe informacion para esta Poliza"));
			}

		}

	}

	/**
	 * Gets the report poli.
	 *
	 * @return the report poli
	 */
	public void getReportPoli() {
		LOGGER.info("ENtra a leer el reporte del PDF");
		try {
			streamedContent = new DefaultStreamedContent(new FileInputStream("/tmp/Poliza.pdf"), "application/pdf");
			LOGGER.info("ST::: " + streamedContent.getContentEncoding());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Find cuentas polizas.
	 *
	 * @param cuenta the cuenta
	 * @param sCta the s cta
	 * @param ssCta the ss cta
	 * @param sssCta the sss cta
	 * @param ssssCta the ssss cta
	 */
	public void findCuentasPolizas(String cuenta, String sCta, String ssCta, String sssCta, String ssssCta) {
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("Eenrta a revisar el caralogo de cuentas...");
		LOGGER.info("Cuenta::: " + cuenta);
		LOGGER.info("sCuenta::::::::" + sCta);
		LOGGER.info("ssCuenta:::::::" + ssCta);
		LOGGER.info("sssCuenta::::::" + sssCta);
		LOGGER.info("ssssCuenta::::::" + ssssCta);
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		LOGGER.info("::::::::::::::::::::::::::::::::::::::::::::::::");
		listCuenta = consultaPolizaSirveImpl.findCuentas(cuenta, sCta, ssCta, sssCta, ssssCta,
				this.getUserDetails().getIdSector());
	}

	/**
	 * Find byb row.
	 */
	public void findBybRow() {
		LOGGER.info("entra a leer el renglon");
		LOGGER.info("renglon:: " + renglo);
		listPolide = consultaPolizaSirveImpl.findRow(renglo, polien.getMespol(), polien.getConpol(), polien.getTippol(),
				this.getUserDetails().getIdSector());
	}

	/** The render pdf. */
	private boolean renderPdf = Boolean.FALSE;

	/**
	 * Checks if is render pdf.
	 *
	 * @return true, if is render pdf
	 */
	public boolean isRenderPdf() {
		return renderPdf;
	}

	/**
	 * Sets the render pdf.
	 *
	 * @param renderPdf the new render pdf
	 */
	public void setRenderPdf(boolean renderPdf) {
		this.renderPdf = renderPdf;
	}

	/** The file pdf path. */
	private String filePdfPath = "/tmp/m-dpolizasd.pdf";

	/**
	 * Gets the file pdf path.
	 *
	 * @return the file pdf path
	 */
	public String getFilePdfPath() {
		return filePdfPath;
	}

	/**
	 * Sets the file pdf path.
	 *
	 * @param filePdfPath the new file pdf path
	 */
	public void setFilePdfPath(String filePdfPath) {
		this.filePdfPath = filePdfPath;
	}

	/**
	 * Validate.
	 *
	 * @return true, if successful
	 */
	private boolean validate() {
		return Boolean.TRUE;
	}

	/** Ruta donde se encuentra el archivo jasper del reporte de cuentas. */
	// @Value("${view.report.path.jasper.poliza}")
	private static final String REPORT_PATH_JASPER_POLICY = FrontProperty
			.getPropertyValue("view.report.path.jasper.poliza");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.poliza}")
	private static final String REPORT_NAME = FrontProperty.getPropertyValue("file.name.report.txt.poliza");

	/** Mensaje Error. */
	// @Value("${catalog.message.error}")
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The poliza serviceimpl. */
	@ManagedProperty("#{polizaService}")
	private PolizaService polizaServiceimpl;

	/**
	 * Button action.
	 *
	 * @param actionEvent the action event
	 */
	public void buttonAction(ActionEvent actionEvent) {

		if (validate()) {
			this.setRenderPdf(Boolean.TRUE);
			// this.streamedContent = new DefaultStreamedContent(
			// this.polizaServiceimpl.getReportByTipopolMespolConpol(REPORT_PATH_JASPER_POLICY,
			// REPORT_NAME,
			// "escudo_ecatepec.png", polien.getTippol(), polien.getMespol(),
			// polien.getConpol(), polien.getConpol(),
			// getUserDetails().getIdSector()), "application/pdf");
			try {
				this.filePdfPath = this.polizaServiceimpl.savePDFFile(REPORT_PATH_JASPER_POLICY, REPORT_NAME,
						"escudo_ecatepec.png", polien.getTippol(), polien.getMespol(), polien.getConpol(),
						polien.getConpol(), getUserDetails().getIdSector(), this.getUserDetails().getUsername());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al generar el archivo PDF.");
			}
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al capturar los parametros.");
		}
	}

	/**
	 * Gets the poliza serviceimpl.
	 *
	 * @return the polizaServiceimpl
	 */
	public PolizaService getPolizaServiceimpl() {
		return polizaServiceimpl;
	}

	/**
	 * Sets the poliza serviceimpl.
	 *
	 * @param polizaServiceimpl            the polizaServiceimpl to set
	 */
	public void setPolizaServiceimpl(PolizaService polizaServiceimpl) {
		this.polizaServiceimpl = polizaServiceimpl;
	}

}
