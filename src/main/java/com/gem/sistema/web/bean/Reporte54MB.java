package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm5511;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm5511Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm5511Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte54MB.
 */
@ManagedBean(name = "reporte54MB")
@ViewScoped
public class Reporte54MB extends BaseDirectReport {

	/** The Constant INITIAL. */
	private static final Integer INITIAL = 1;
	
	/** The Constant CASE_NO_DATA. */
	private static final Integer CASE_NO_DATA = 2;
	
	/** The Constant CASE_ADD. */
	private static final Integer CASE_ADD = 4;
	
	/** The Constant ZERO. */
	private static final BigDecimal ZERO = BigDecimal.ZERO;
	
	/** The Constant TRUE. */
	private static final Boolean TRUE = Boolean.TRUE;
	
	/** The Constant FALSE. */
	private static final Boolean FALSE = Boolean.FALSE;

	/** The Constant CIEN_PORCIENTO. */
	private static final BigDecimal CIEN_PORCIENTO = new BigDecimal("100.00");

	/** The mes. */
	private String mes;
	
	/** The mensual. */
	private String mensual;
	
	/** The pm 5511. */
	private Pm5511 pm5511;
	
	/** The pm 5511 buffer. */
	private Pm5511 pm5511Buffer = new Pm5511();
	
	/** The list pm 5511. */
	private List<Pm5511> listPm5511;
	
	/** The lis mes. */
	private List<TcMes> lisMes;
	
	/** The combo pm 511. */
	private List<Pm5511> comboPm511;
	
	/** The txt origen. */
	String txtOrigen;
	
	/** The txt destino. */
	String txtDestino;
	
	/** The porcentaje. */
	Double porcentaje;
	
	/** The d origen. */
	Double dOrigen = 0.00;
	
	/** The d origen 2. */
	Double dOrigen2 = 0.00;
	
	/** The d origen 3. */
	Double dOrigen3 = 0.00;
	
	/** The d O destino. */
	Double dODestino = 0.00;
	
	/** The d O destino 2. */
	Double dODestino2 = 0.00;
	
	/** The d O destino 3. */
	Double dODestino3 = 0.00;
	
	/** The mme. */
	Double mme;
	
	/** The sum sub T 1. */
	Double sumSubT1 = 0.00;
	
	/** The sum sub tp 1. */
	Double sumSubTp1 = 0.00;
	
	/** The sum sub T 2. */
	Double sumSubT2 = 0.00;
	
	/** The sum sub tp 2. */
	Double sumSubTp2 = 0.00;
	
	/** The sum sub T 3. */
	Double sumSubT3 = 0.00;
	
	/** The sum sub tp 3. */
	Double sumSubTp3 = 0.00;
	
	/** The sum total. */
	Double sumTotal = 0.00;
	
	/** The sum total P. */
	Double sumTotalP = 0.00;
	
	/** The s st 1. */
	Double sSt1;
	
	/** The s st 2. */
	Double sSt2;
	
	/** The s st 3. */
	Double sSt3;

	/** The txt. */
	private StreamedContent txt;

	/** The b lbl. */
	private boolean bLbl = Boolean.TRUE;
	
	/** The b txt. */
	private boolean bTxt = Boolean.FALSE;
	
	/** The b add. */
	private boolean bAdd = Boolean.TRUE;
	
	/** The b V modificar. */
	private boolean bVModificar = Boolean.TRUE;
	
	/** The b V salvar. */
	private boolean bVSalvar = Boolean.FALSE;
	
	/** The b btn modificar. */
	private boolean bBtnModificar = Boolean.TRUE;
	
	/** The b borrar. */
	private boolean bBorrar = Boolean.TRUE;
	
	/** The b otros. */
	private boolean bOtros = Boolean.TRUE;
	
	/** The b mensual. */
	private boolean bMensual = Boolean.TRUE;
	
	/** The b combo. */
	private boolean bCombo = Boolean.FALSE;
	
	/** The b bloque 1. */
	private Boolean bBloque1;
	
	/** The b bloque 2. */
	private Boolean bBloque2;
	
	/** The b bloque 3. */
	private Boolean bBloque3;
	
	/** The b update. */
	private Boolean bUpdate;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The pm 5511 repository. */
	@ManagedProperty("#{pm5511Repository}")
	private Pm5511Repository pm5511Repository;

	/** The pm 5511 service. */
	@ManagedProperty("#{pm5511Service}")
	private Pm5511Service pm5511service;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/**
	 * Gets the pm 5511.
	 *
	 * @return the pm 5511
	 */
	public Pm5511 getPm5511() {
		return pm5511;
	}

	/**
	 * Sets the pm 5511.
	 *
	 * @param pm5511 the new pm 5511
	 */
	public void setPm5511(Pm5511 pm5511) {
		this.pm5511 = pm5511;
	}

	/**
	 * Gets the list pm 5511.
	 *
	 * @return the list pm 5511
	 */
	public List<Pm5511> getListPm5511() {
		return listPm5511;
	}

	/**
	 * Sets the list pm 5511.
	 *
	 * @param listPm5511 the new list pm 5511
	 */
	public void setListPm5511(List<Pm5511> listPm5511) {
		this.listPm5511 = listPm5511;
	}

	/**
	 * Gets the pm 5511 repository.
	 *
	 * @return the pm 5511 repository
	 */
	public Pm5511Repository getPm5511Repository() {
		return pm5511Repository;
	}

	/**
	 * Sets the pm 5511 repository.
	 *
	 * @param pm5511Repository the new pm 5511 repository
	 */
	public void setPm5511Repository(Pm5511Repository pm5511Repository) {
		this.pm5511Repository = pm5511Repository;
	}

	/**
	 * Gets the pm 5511 service.
	 *
	 * @return the pm 5511 service
	 */
	public Pm5511Service getPm5511service() {
		return pm5511service;
	}

	/**
	 * Sets the pm 5511 service.
	 *
	 * @param pm5511service the new pm 5511 service
	 */
	public void setPm5511service(Pm5511Service pm5511service) {
		this.pm5511service = pm5511service;
	}

	/**
	 * Checks if is b lbl.
	 *
	 * @return true, if is b lbl
	 */
	public boolean isbLbl() {
		return bLbl;
	}

	/**
	 * Sets the b lbl.
	 *
	 * @param bLbl the new b lbl
	 */
	public void setbLbl(boolean bLbl) {
		this.bLbl = bLbl;
	}

	/**
	 * Checks if is b txt.
	 *
	 * @return true, if is b txt
	 */
	public boolean isbTxt() {
		return bTxt;
	}

	/**
	 * Sets the b txt.
	 *
	 * @param bTxt the new b txt
	 */
	public void setbTxt(boolean bTxt) {
		this.bTxt = bTxt;
	}

	/**
	 * Checks if is b V modificar.
	 *
	 * @return true, if is b V modificar
	 */
	public boolean isbVModificar() {
		return bVModificar;
	}

	/**
	 * Sets the b V modificar.
	 *
	 * @param bVModificar the new b V modificar
	 */
	public void setbVModificar(boolean bVModificar) {
		this.bVModificar = bVModificar;
	}

	/**
	 * Checks if is b V salvar.
	 *
	 * @return true, if is b V salvar
	 */
	public boolean isbVSalvar() {
		return bVSalvar;
	}

	/**
	 * Sets the b V salvar.
	 *
	 * @param bVSalvar the new b V salvar
	 */
	public void setbVSalvar(boolean bVSalvar) {
		this.bVSalvar = bVSalvar;
	}

	/**
	 * Checks if is b btn modificar.
	 *
	 * @return true, if is b btn modificar
	 */
	public boolean isbBtnModificar() {
		return bBtnModificar;
	}

	/**
	 * Sets the b btn modificar.
	 *
	 * @param bBtnModificar the new b btn modificar
	 */
	public void setbBtnModificar(boolean bBtnModificar) {
		this.bBtnModificar = bBtnModificar;
	}

	/**
	 * Checks if is b borrar.
	 *
	 * @return true, if is b borrar
	 */
	public boolean isbBorrar() {
		return bBorrar;
	}

	/**
	 * Sets the b borrar.
	 *
	 * @param bBorrar the new b borrar
	 */
	public void setbBorrar(boolean bBorrar) {
		this.bBorrar = bBorrar;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the lis mes.
	 *
	 * @return the lis mes
	 */
	public List<TcMes> getLisMes() {
		return lisMes;
	}

	/**
	 * Sets the lis mes.
	 *
	 * @param lisMes the new lis mes
	 */
	public void setLisMes(List<TcMes> lisMes) {
		this.lisMes = lisMes;
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
	 * Checks if is b otros.
	 *
	 * @return true, if is b otros
	 */
	public boolean isbOtros() {
		return bOtros;
	}

	/**
	 * Sets the b otros.
	 *
	 * @param bOtros the new b otros
	 */
	public void setbOtros(boolean bOtros) {
		this.bOtros = bOtros;
	}

	/**
	 * Gets the pm 5511 buffer.
	 *
	 * @return the pm 5511 buffer
	 */
	public Pm5511 getPm5511Buffer() {
		return pm5511Buffer;
	}

	/**
	 * Sets the pm 5511 buffer.
	 *
	 * @param pm5511Buffer the new pm 5511 buffer
	 */
	public void setPm5511Buffer(Pm5511 pm5511Buffer) {
		this.pm5511Buffer = pm5511Buffer;
	}

	/**
	 * Checks if is b mensual.
	 *
	 * @return true, if is b mensual
	 */
	public boolean isbMensual() {
		return bMensual;
	}

	/**
	 * Sets the b mensual.
	 *
	 * @param bMensual the new b mensual
	 */
	public void setbMensual(boolean bMensual) {
		this.bMensual = bMensual;
	}

	/**
	 * Checks if is b combo.
	 *
	 * @return true, if is b combo
	 */
	public boolean isbCombo() {
		return bCombo;
	}

	/**
	 * Sets the b combo.
	 *
	 * @param bCombo the new b combo
	 */
	public void setbCombo(boolean bCombo) {
		this.bCombo = bCombo;
	}

	/**
	 * Gets the txt.
	 *
	 * @return the txt
	 */
	public StreamedContent getTxt() {
		return txt;
	}

	/**
	 * Sets the txt.
	 *
	 * @param txt the new txt
	 */
	public void setTxt(StreamedContent txt) {
		this.txt = txt;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the mensual.
	 *
	 * @return the mensual
	 */
	public String getMensual() {
		return mensual;
	}

	/**
	 * Sets the mensual.
	 *
	 * @param mensual the new mensual
	 */
	public void setMensual(String mensual) {
		this.mensual = mensual;
	}

	/**
	 * Gets the combo pm 511.
	 *
	 * @return the combo pm 511
	 */
	public List<Pm5511> getComboPm511() {
		return comboPm511;
	}

	/**
	 * Sets the combo pm 511.
	 *
	 * @param comboPm511 the new combo pm 511
	 */
	public void setComboPm511(List<Pm5511> comboPm511) {
		this.comboPm511 = comboPm511;
	}

	/**
	 * Checks if is b add.
	 *
	 * @return true, if is b add
	 */
	public boolean isbAdd() {
		return bAdd;
	}

	/**
	 * Sets the b add.
	 *
	 * @param bAdd the new b add
	 */
	public void setbAdd(boolean bAdd) {
		this.bAdd = bAdd;
	}

	/**
	 * Gets the b update.
	 *
	 * @return the b update
	 */
	public Boolean getbUpdate() {
		return bUpdate;
	}

	/**
	 * Sets the b update.
	 *
	 * @param bUpdate the new b update
	 */
	public void setbUpdate(Boolean bUpdate) {
		this.bUpdate = bUpdate;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		// actividBand(INITIAL);

		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bVSalvar = Boolean.FALSE;
		bBtnModificar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bOtros = Boolean.TRUE;
		bAdd = Boolean.FALSE;
		isbCombo();
		isbMensual();
		listPm5511 = this.pm5511service.findAllOrderByMensual();
		if (CollectionUtils.isEmpty(listPm5511)) {
			pm5511 = this.pm5511service.beginVariables();
			listPm5511.add(pm5511);
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bVSalvar = Boolean.FALSE;
			bBtnModificar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			bOtros = Boolean.TRUE;
		}

		comboPm511 = pm5511Repository.findAll();
		if (CollectionUtils.isEmpty(comboPm511)) {
			comboPm511.add(0, pm5511);
			mensual = comboPm511.get(0).getMensual().toString();
		}
		lisMes = tcMesRepository.findAll();

		if (CollectionUtils.isEmpty(lisMes)) {

			mes = lisMes.get(0).getMes();
		}

		if (listPm5511.size() == 12) {
			bAdd = Boolean.TRUE;
		}
		this.setbUpdate(FALSE);
	}

	/**
	 * Modify.
	 *
	 * @param index the index
	 */
	public void modify(Integer index) {
		isbCombo();
		isbMensual();
		bBtnModificar = Boolean.TRUE;
		bTxt = Boolean.TRUE;
		bLbl = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bVModificar = Boolean.FALSE;
		bVSalvar = Boolean.TRUE;
		bOtros = Boolean.FALSE;
		bAdd = Boolean.TRUE;
		isbBtnModificar();
		this.setbUpdate(TRUE);
	}

	/**
	 * Adds the.
	 *
	 * @param index the index
	 */
	public void add(Integer index) {
		bCombo = Boolean.TRUE;
		bMensual = Boolean.FALSE;
		bTxt = Boolean.TRUE;
		bLbl = Boolean.FALSE;
		bBorrar = Boolean.TRUE;
		bVModificar = Boolean.FALSE;
		bVSalvar = Boolean.TRUE;
		bBtnModificar = Boolean.FALSE;
		bAdd = Boolean.TRUE;
		bOtros = Boolean.FALSE;

		listPm5511.add(index, this.pm5511service.add());
		// if (null == listPm5511.get(1).getUserid()) {
		// listPm5511.remove(1);
		// }

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		if (bUpdate == FALSE) {
			mensual = mes;
		} else {
			mes = mensual;
		}
		if (!mes.equals("00")) {
			boolean bandera = existMonth(
					bBtnModificar == false ? Integer.valueOf(mes) : listPm5511.get(index).getMensual());

			if (bUpdate) {

				pm5511 = listPm5511.get(index);
				this.pm5511Repository.save(pm5511);
				bCombo = Boolean.FALSE;
				bMensual = Boolean.TRUE;
				bTxt = Boolean.FALSE;
				bLbl = Boolean.TRUE;
				bBorrar = Boolean.FALSE;
				bVModificar = Boolean.TRUE;
				bVSalvar = Boolean.FALSE;
				bBtnModificar = Boolean.FALSE;
				bOtros = Boolean.TRUE;
				bAdd = Boolean.FALSE;
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"La información se modifico correctamente");
				this.setbUpdate(FALSE);
				listPm5511 = pm5511Repository.findAllByidsector(this.getUserDetails().getIdSector());
			} else if (bandera) {
				// if (this.validateObs(index)) {
				saveData(index);
				bCombo = Boolean.FALSE;
				bMensual = Boolean.TRUE;
				bTxt = Boolean.FALSE;
				bLbl = Boolean.TRUE;
				bBorrar = Boolean.FALSE;
				bVModificar = Boolean.TRUE;
				bVSalvar = Boolean.FALSE;
				bBtnModificar = Boolean.FALSE;
				bAdd = Boolean.FALSE;
				bOtros = Boolean.TRUE;
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo correctamente");
				this.setbUpdate(FALSE);
				listPm5511 = pm5511Repository.findAllByidsector(this.getUserDetails().getIdSector());
				// }

			} else if (bandera == false) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El mes ya fue registrado");
			}

			if (listPm5511.size() == 12) {
				bAdd = Boolean.TRUE;
			}
			comboPm511 = pm5511Repository.findAll();
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione mes mayor a cero");
		}

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm5511 = pm5511service.delete(listPm5511, index);

		comboPm511 = pm5511Repository.findAllByidsector(this.getUserDetails().getIdSector());
		bBtnModificar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bAdd = Boolean.FALSE;
		this.setbUpdate(FALSE);
		if (CollectionUtils.isEmpty(comboPm511)) {
			bBtnModificar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;

		}
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se eliminio correctamente la información");

	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {

		if (bBtnModificar) {
			pm5511 = pm5511Repository.getByIdSectorAndMensual(this.getUserDetails().getIdSector(),
					listPm5511.get(index).getMensual());
			listPm5511.set(index, pm5511);
			bBtnModificar = Boolean.FALSE;
		} else {
			pm5511 = pm5511service.beginVariables();
			listPm5511.set(index, pm5511);
		}

	}

	/**
	 * Cancel.
	 *
	 * @param index the index
	 */
	public void cancel(Integer index) {
		bCombo = Boolean.FALSE;
		bMensual = Boolean.TRUE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bVSalvar = Boolean.FALSE;
		bBtnModificar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bOtros = Boolean.TRUE;
		bAdd = Boolean.FALSE;
		dOrigen = 0.00;
		dOrigen2 = 0.00;
		dOrigen3 = 0.00;
		dODestino = 0.00;
		dODestino2 = 0.00;
		dODestino3 = 0.00;
		sumSubT1 = 0.00;
		sumSubTp1 = 0.00;
		sumSubT2 = 0.00;
		sumSubTp2 = 0.00;
		sumSubT3 = 0.00;
		sumSubTp3 = 0.00;
		sumTotal = 0.00;
		sumTotalP = 0.00;
		this.setbUpdate(FALSE);
		listPm5511 = pm5511Repository.findAllByidsector(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm5511)) {

			bVModificar = Boolean.TRUE;
			bBtnModificar = Boolean.FALSE;
			bVSalvar = Boolean.FALSE;
			bBtnModificar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			bAdd = Boolean.FALSE;
			listPm5511.add(this.pm5511service.add());

		}

		if (listPm5511.size() == 12) {
			bAdd = Boolean.TRUE;
		}
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edición cancelada");
	}

	/**
	 * Activid band.
	 *
	 * @param opt the opt
	 */
	public void actividBand(int opt) {
		switch (opt) {
		case 1:
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bVSalvar = Boolean.FALSE;
			bBtnModificar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			bOtros = Boolean.TRUE;
			break;
		case 2:
			bLbl = Boolean.FALSE;
			bTxt = Boolean.TRUE;
			bVModificar = Boolean.TRUE;
			bVSalvar = Boolean.FALSE;
			bBtnModificar = Boolean.FALSE;
			bBorrar = Boolean.TRUE;
			bOtros = Boolean.FALSE;
		case 3:
			bBorrar = Boolean.TRUE;
			bBtnModificar = Boolean.TRUE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSalvar = Boolean.FALSE;
			bOtros = Boolean.FALSE;
		case 4:
			bTxt = Boolean.TRUE;
			bLbl = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
			bVModificar = Boolean.FALSE;
			bVSalvar = Boolean.TRUE;
			bBtnModificar = Boolean.FALSE;
			bOtros = Boolean.FALSE;
		default:
			break;
		}

	}

	/**
	 * Save data.
	 *
	 * @param index the index
	 */
	public void saveData(Integer index) {
		pm5511 = listPm5511.get(index);
		pm5511.setMensual(Integer.valueOf(mes));
		pm5511.setUserid(this.getUserDetails().getUsername());
		pm5511.setIdRef(0L);
		pm5511.setIdsector(this.getUserDetails().getIdSector());
		pm5511.setCapturo(this.getUserDetails().getUsername());
		pm5511.setFeccap(Calendar.getInstance().getTime());
		pm5511.setObsgral(null == pm5511.getObsgral() ? "" : pm5511.getObsgral());
		pm5511.setObs("");
		pm5511Repository.save(pm5511);
	}

	/** The parameters. */
	Map<String, Object> parameters;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		parameters.put("Mensual", Integer.valueOf(mensual));
		parameters.put("SECTOR", this.getUserDetails().getIdSector());
		parameters.put("CAMPO1", firmas.getCampo1());
		parameters.put("CLAVE", conctb.getClave());
		parameters.put("L4", firmas.getL4());
		parameters.put("L5", firmas.getL5());
		parameters.put("N4", firmas.getN4());
		parameters.put("N5", firmas.getN5());
		parameters.put("ANO_EMP", firmas.getCampo3());
		parameters.put("imagen", this.getUserDetails().getPathImgCab1());

		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Exist month.
	 *
	 * @param mensual the mensual
	 * @return true, if successful
	 */
	public boolean existMonth(Integer mensual) {
		return pm5511Repository.findByMonth(this.getUserDetails().getIdSector(), mensual) == 1 ? false : true;
	}

	/**
	 * Calcular obligaciones financieras.
	 *
	 * @param index the index
	 */
	public void calcularObligacionesFinancieras(Integer index) {
		pm5511 = listPm5511.get(index);

		if (pm5511.getMmme().compareTo(BigDecimal.ZERO) == 0)
			pm5511.setPobf(BigDecimal.ZERO);
		else {

			Double obf = null;
			obf = (pm5511.getObf().doubleValue() * 100) / pm5511.getMmme().doubleValue();
			pm5511.setPobf(new BigDecimal(obf));
		}
		listPm5511.add(index, pm5511);
	}

	/**
	 * Creates the pdf.
	 */
	public void createPdf() {
		jasperReporteName = "reporte54";
		endFilename = jasperReporteName + ".pdf";
		this.createFilePdfInline();
	}

	/**
	 * Creates the txt.
	 */
	public void createTxt() {
		jasperReporteName = "reporte54_txt";
		endFilename = jasperReporteName + ".pdf";
		txt = getFileTxt();
	}

	/**
	 * Calcular porcentaje.
	 *
	 * @param index the index
	 * @param origen the origen
	 */
	public void calcularPorcentaje(Integer index, String origen) {
		pm5511 = listPm5511.get(index);
		mme = pm5511.getMmme().doubleValue();
		bBloque1 = Boolean.FALSE;
		bBloque2 = Boolean.FALSE;
		bBloque3 = Boolean.FALSE;
		if (mme != 0) {
			if (origen.equals("txtObf")) {
				dOrigen = pm5511.getObf().doubleValue();
				pm5511.setPobf(this.calcular(dOrigen, mme));
				dODestino = pm5511.getPobf().doubleValue();
				bBloque1 = TRUE;

			} else if (origen.equals("txtPpea")) {
				dOrigen = pm5511.getPpea().doubleValue();
				pm5511.setPppea(this.calcular(dOrigen, mme));
				dODestino = pm5511.getPppea().doubleValue();
				bBloque1 = TRUE;

			} else if (origen.equals("txtAeb")) {
				dOrigen = pm5511.getAeb().doubleValue();
				pm5511.setPaeb(this.calcular(dOrigen, mme));
				dODestino = pm5511.getPaeb().doubleValue();
				bBloque1 = TRUE;

			} else if (origen.equals("txtEl")) {
				dOrigen = pm5511.getEl().doubleValue();
				pm5511.setPel(this.calcular(dOrigen, mme));
				dODestino = pm5511.getPel().doubleValue();
				bBloque1 = TRUE;

			} else if (origen.equals("txtApis")) {
				dOrigen = pm5511.getApis().doubleValue();
				pm5511.setPapis(this.calcular(dOrigen, mme));
				dODestino = pm5511.getPapis().doubleValue();
				bBloque1 = TRUE;

			} else if (origen.equals("txtPac")) {
				dOrigen = pm5511.getPac().doubleValue();
				pm5511.setPpac(this.calcular(dOrigen, mme));
				dODestino = pm5511.getPpac().doubleValue();
				bBloque1 = TRUE;

			} else if (origen.equals("txtDag")) {
				dOrigen = pm5511.getDaj().doubleValue();
				pm5511.setPdaj(this.calcular(dOrigen, mme));
				dODestino = pm5511.getPdaj().doubleValue();
				bBloque1 = TRUE;
				/* primer segundo */
			} else if (origen.equals("txtSppc")) {
				dOrigen2 = pm5511.getSppc().doubleValue();
				pm5511.setPsppc(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPsppc().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtMnpos")) {
				dOrigen2 = pm5511.getMnpos().doubleValue();
				pm5511.setPmnpos(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPmnpos().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtMnpob")) {
				dOrigen2 = pm5511.getMnpob().doubleValue();
				pm5511.setPmnpob(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPmnpob().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtMnpop")) {
				dOrigen2 = pm5511.getMnpop().doubleValue();
				pm5511.setPmnpop(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPmnpop().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtCap")) {
				dOrigen2 = pm5511.getCap().doubleValue();
				pm5511.setPcap(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPcap().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAdu")) {
				dOrigen2 = pm5511.getAdu().doubleValue();
				pm5511.setPadu(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPadu().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtEan")) {
				dOrigen2 = pm5511.getEan().doubleValue();
				pm5511.setPean(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPean().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAmer")) {
				dOrigen2 = pm5511.getAmer().doubleValue();
				pm5511.setPamer(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPamer().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAes")) {
				dOrigen2 = pm5511.getAes().doubleValue();
				pm5511.setPaes(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPaes().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAep")) {
				dOrigen2 = pm5511.getAep().doubleValue();
				pm5511.setPaep(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPaep().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAdv")) {
				dOrigen2 = pm5511.getAdv().doubleValue();
				pm5511.setPadv(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPadv().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtMpv")) {
				dOrigen2 = pm5511.getMpv().doubleValue();
				pm5511.setPmpv(this.calcular(dOrigen2, mme));
				dODestino2 = pm5511.getPmpv().doubleValue();
				bBloque2 = TRUE;
				/* tercer bloque */
			} else if (origen.equals("txtInfr")) {
				dOrigen3 = pm5511.getAocr().doubleValue();
				pm5511.setPaocr(this.calcular(dOrigen3, mme));
				dODestino3 = pm5511.getPaocr().doubleValue();
				bBloque3 = TRUE;
			} else if (origen.equals("txtaocr")) {
				dOrigen3 = pm5511.getPodc().doubleValue();
				pm5511.setPpodc(this.calcular(dOrigen3, mme));
				dODestino3 = pm5511.getPpodc().doubleValue();
				bBloque3 = TRUE;
			} else if (origen.equals("txtpodc")) {
				dOrigen3 = pm5511.getSubinf().doubleValue();
				pm5511.setPsubinf(this.calcular(dOrigen3, mme));
				dODestino3 = pm5511.getPsubinf().doubleValue();
				bBloque3 = TRUE;
			} else if (origen.equals("txtBienes")) {
				dOrigen3 = pm5511.getPodcs().doubleValue();
				pm5511.setPpodcs(this.calcular(dOrigen3, mme));
				dODestino3 = pm5511.getPpodcs().doubleValue();
				bBloque3 = TRUE;
			} else if (origen.equals("txtSubb")) {
				dOrigen3 = pm5511.getSubb().doubleValue();
				pm5511.setPsubb(this.calcular(dOrigen3, mme));
				dODestino3 = pm5511.getPsubb().doubleValue();
				bBloque3 = TRUE;
			} else if (origen.equals("txtotros")) {
				dOrigen3 = pm5511.getOtros().doubleValue();
				pm5511.setPotros(this.calcular(dOrigen3, mme));
				dODestino3 = pm5511.getPotros().doubleValue();
				bBloque3 = TRUE;
			}

			if (bBloque1 == TRUE) {
				if (bUpdate == FALSE) {
					sumSubTp1 = pm5511.getPsubof().doubleValue();
					sumSubTp1 = sumSubTp1 + dODestino;
					pm5511.setPsubof(this.conbertToBigDecimal(sumSubTp1));

				} else if (bUpdate == TRUE) {
					pm5511.setSubof(BigDecimal.ZERO);
					sSt1 = pm5511.getObf().doubleValue() + pm5511.getPpea().doubleValue()
							+ pm5511.getAeb().doubleValue() + pm5511.getEl().doubleValue()
							+ pm5511.getApis().doubleValue() + pm5511.getPac().doubleValue()
							+ pm5511.getDaj().doubleValue();
					pm5511.setSubof(BigDecimal.valueOf(sSt1));
					sumSubT1 = pm5511.getPobf().doubleValue() + pm5511.getPppea().doubleValue()
							+ pm5511.getPaeb().doubleValue() + pm5511.getPel().doubleValue()
							+ pm5511.getPapis().doubleValue() + pm5511.getPpac().doubleValue()
							+ pm5511.getPdaj().doubleValue();
					pm5511.setPsubof(BigDecimal.valueOf(sumSubT1));

				}
				sumTotalP = sumSubTp1;
			}
			if (bBloque2 == TRUE) {
				if (bUpdate == FALSE) {
					sumSubTp2 = pm5511.getPsubsp().doubleValue();
					sumSubTp2 = sumSubTp2 + dODestino2;
					pm5511.setPsubsp(this.conbertToBigDecimal(sumSubTp2));
				} else if (bUpdate == TRUE) {
					sSt2 = pm5511.getSppc().doubleValue() + pm5511.getMnpos().doubleValue()
							+ pm5511.getMnpob().doubleValue() + pm5511.getMnpop().doubleValue()
							+ pm5511.getCap().doubleValue() + pm5511.getAdu().doubleValue()
							+ pm5511.getEan().doubleValue() + pm5511.getAmer().doubleValue()
							+ pm5511.getAes().doubleValue() + pm5511.getAep().doubleValue()
							+ pm5511.getAdv().doubleValue() + pm5511.getMpv().doubleValue();
					pm5511.setSubsp(BigDecimal.valueOf(sSt2));
					sumSubTp2 = pm5511.getPsppc().doubleValue() + pm5511.getPmnpos().doubleValue()
							+ pm5511.getPmnpob().doubleValue() + pm5511.getPmnpop().doubleValue()
							+ pm5511.getPcap().doubleValue() + pm5511.getPadu().doubleValue()
							+ pm5511.getPean().doubleValue() + pm5511.getPamer().doubleValue()
							+ pm5511.getPaes().doubleValue() + pm5511.getPaep().doubleValue()
							+ pm5511.getPadv().doubleValue() + pm5511.getPmpv().doubleValue();
					pm5511.setPsubsp(BigDecimal.valueOf(sumSubTp2));
				}

				sumTotalP = sumSubTp1 + sumSubTp2;

			}
			if (bBloque3 == TRUE) {
				if (bUpdate == FALSE) {
					sumSubTp3 = pm5511.getPsubot().doubleValue();
					sumSubTp3 = sumSubTp3 + dODestino3;
					pm5511.setPsubot(this.conbertToBigDecimal(sumSubTp3));
				} else if (bUpdate == TRUE) {
					sSt3 = pm5511.getAocr().doubleValue() + pm5511.getPodc().doubleValue()
							+ pm5511.getSubinf().doubleValue() + pm5511.getPodcs().doubleValue()
							+ pm5511.getSubb().doubleValue() + pm5511.getOtros().doubleValue();
					pm5511.setSubot(BigDecimal.valueOf(sSt3));
					sumSubTp3 = pm5511.getPaocr().doubleValue() + pm5511.getPpodc().doubleValue()
							+ pm5511.getPsubinf().doubleValue() + pm5511.getPpodcs().doubleValue()
							+ pm5511.getPsubb().doubleValue() + pm5511.getPotros().doubleValue();
					pm5511.setPsubot(BigDecimal.valueOf(sumSubTp3));
				}

				sumTotalP = sumSubTp1 + sumSubTp2 + sumSubTp3;
			}

			pm5511.setPmt(this.conbertToBigDecimal(sumTotalP));

		} else {
			if (origen.equals("txtObf")) {
				dOrigen = pm5511.getObf().doubleValue();
				bBloque1 = TRUE;
			} else if (origen.equals("txtPpea")) {
				dOrigen = pm5511.getPpea().doubleValue();
				bBloque1 = TRUE;
			} else if (origen.equals("txtAeb")) {
				dOrigen = pm5511.getAeb().doubleValue();
				bBloque1 = TRUE;
			} else if (origen.equals("txtEl")) {
				dOrigen = pm5511.getEl().doubleValue();
				bBloque1 = TRUE;
			} else if (origen.equals("txtApis")) {
				dOrigen = pm5511.getApis().doubleValue();
				bBloque1 = TRUE;
			} else if (origen.equals("txtPac")) {
				dOrigen = pm5511.getPac().doubleValue();
				bBloque1 = TRUE;
			} else if (origen.equals("txtDag")) {
				dOrigen = pm5511.getDaj().doubleValue();
				bBloque1 = TRUE;
				/* primer segundo */
			} else if (origen.equals("txtSppc")) {
				dOrigen2 = pm5511.getSppc().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtMnpos")) {
				dOrigen2 = pm5511.getMnpos().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtMnpob")) {
				dOrigen2 = pm5511.getMnpob().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtMnpop")) {
				dOrigen2 = pm5511.getMnpop().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtCap")) {
				dOrigen2 = pm5511.getCap().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAdu")) {
				dOrigen2 = pm5511.getAdu().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtEan")) {
				dOrigen2 = pm5511.getEan().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAmer")) {
				dOrigen2 = pm5511.getAmer().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAes")) {
				dOrigen2 = pm5511.getAes().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAep")) {
				dOrigen2 = pm5511.getAep().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtAdv")) {
				dOrigen2 = pm5511.getAdv().doubleValue();
				bBloque2 = TRUE;
			} else if (origen.equals("txtMpv")) {
				dOrigen2 = pm5511.getMpv().doubleValue();
				bBloque2 = TRUE;
				/* tercer bloque */
			} else if (origen.equals("txtInfr")) {
				dOrigen3 = pm5511.getAocr().doubleValue();
				bBloque3 = TRUE;
			} else if (origen.equals("txtaocr")) {
				dOrigen3 = pm5511.getPodc().doubleValue();
				bBloque3 = TRUE;
			} else if (origen.equals("txtpodc")) {
				dOrigen3 = pm5511.getSubinf().doubleValue();
				bBloque3 = TRUE;

			} else if (origen.equals("txtBienes")) {
				dOrigen3 = pm5511.getPodcs().doubleValue();
				bBloque3 = TRUE;

			} else if (origen.equals("txtSubb")) {
				dOrigen3 = pm5511.getSubb().doubleValue();
				bBloque3 = TRUE;

			} else if (origen.equals("txtotros")) {
				dOrigen3 = pm5511.getOtros().doubleValue();
				bBloque3 = TRUE;

			}
		}

		if (bBloque1 == TRUE) {
			if (bUpdate == FALSE) {
				sumSubT1 = pm5511.getSubof().doubleValue();
				sumSubT1 = sumSubT1 + dOrigen;
				pm5511.setSubof(this.conbertToBigDecimal(sumSubT1));
				sumTotal = sumSubT1;
			}
		}

		// subsp
		if (bBloque2 == TRUE) {
			if (bUpdate == FALSE) {
				sumSubT2 = pm5511.getSubsp().doubleValue();
				sumSubT2 = sumSubT2 + dOrigen2;
				pm5511.setSubsp(this.conbertToBigDecimal(sumSubT2));
				sumTotal = sumSubT1 + sumSubT2;
			}

		}
		if (bBloque3 == TRUE) {
			if (bUpdate == FALSE) {
				sumSubT3 = pm5511.getSubot().doubleValue();
				sumSubT3 = sumSubT3 + dOrigen3;
				pm5511.setSubot(this.conbertToBigDecimal(sumSubT3));
				sumTotal = sumSubT1 + sumSubT2 + sumSubT3;
			}

		}

		if (bUpdate == FALSE) {
			pm5511.setMt(this.conbertToBigDecimal(sumTotal));
		} else if (bUpdate == TRUE) {
			sumTotal = 0.00;
			sumTotal = pm5511.getSubof().doubleValue() + pm5511.getSubsp().doubleValue()
					+ pm5511.getSubot().doubleValue();
			pm5511.setMt(BigDecimal.valueOf(sumTotal));
			sumTotalP = pm5511.getPsubof().doubleValue() + pm5511.getPsubsp().doubleValue()
					+ pm5511.getPsubot().doubleValue();
			pm5511.setPmt(BigDecimal.valueOf(sumTotalP));
		}

		// sumTotalP = sumSubTp1 + sumSubTp2 + sumSubTp3;
		listPm5511.set(index, pm5511);

	}

	/**
	 * Calcular.
	 *
	 * @param origen the origen
	 * @param destino the destino
	 * @return the big decimal
	 */
	public BigDecimal calcular(Double origen, Double destino) {
		Double r = (origen * 100) / destino;
		return new BigDecimal(r);
	}

	/**
	 * Conbert to big decimal.
	 *
	 * @param value the value
	 * @return the big decimal
	 */
	public BigDecimal conbertToBigDecimal(Double value) {
		return BigDecimal.valueOf(value);
	}

	/**
	 * Validate obs.
	 *
	 * @param index the index
	 * @return true, if successful
	 */
	public boolean validateObs(Integer index) {
		Pm5511 pm = listPm5511.get(index);
		boolean bandera = Boolean.TRUE;
		if (null == pm.getObsgral()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo observaciones es obligatorio");
			bandera = Boolean.FALSE;
		}
		return bandera;
	}

	/**
	 * Order by.
	 */
	public void orderBy() {
		listPm5511 = pm5511Repository.findAllByidsector(this.getUserDetails().getIdSector());
	}

}
