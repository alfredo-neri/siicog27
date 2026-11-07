package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.ArrayList;
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
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm5011;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.predicates.Pm5011Predicate;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm5011Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte48MB.
 */
@ManagedBean(name = "reporte48MB")
@ViewScoped
public class Reporte48MB extends BaseDirectReport {

	/** The Constant MESSAGE. */
	private static final String MESSAGE = FrontProperty.getPropertyValue("reporte.message.save.succes");
	
	/** The Constant MESSAGE_INFO. */
	private static final String MESSAGE_INFO = FrontProperty.getPropertyValue("reporte.message.save.info");
	
	/** The Constant INACTIVO. */
	private static final Integer INACTIVO = 1;
	
	/** The Constant ACTIVO. */
	private static final Integer ACTIVO = 2;
	
	/** The pm 5011. */
	private Pm5011 pm5011;
	
	/** The mes. */
	private Integer mes;
	
	/** The lis mes. */
	private List<TcMes> lisMes;
	
	/** The list pm 5011. */
	private List<Pm5011> listPm5011;
	
	/** The tc mes. */
	private TcMes tcMes;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The b lblmes. */
	private boolean bLblmes = Boolean.TRUE;
	
	/** The b combo mes. */
	private boolean bComboMes = Boolean.FALSE;
	
	/** The btxts. */
	private boolean btxts = Boolean.TRUE;
	
	/** The b habilita. */
	private boolean bHabilita = Boolean.TRUE;
	
	/** The b desabilita. */
	private boolean bDesabilita = Boolean.FALSE;
	
	/** The b visible. */
	private boolean bVisible = Boolean.FALSE;
	
	/** The b modificar. */
	private boolean bModificar = Boolean.FALSE;
	
	/** The b btn modificar. */
	private boolean bBtnModificar = Boolean.FALSE;
	
	/** The b borrar. */
	private boolean bBorrar = Boolean.TRUE;
	
	/** The b mensual. */
	private boolean bMensual = Boolean.TRUE;
	
	/** The conctb. */
	private Conctb conctb;

	/** The pm 5011 repository. */
	@ManagedProperty("#{pm5011Repository}")
	private Pm5011Repository pm5011Repository;

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
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	/**
	 * Checks if is b lblmes.
	 *
	 * @return true, if is b lblmes
	 */
	public boolean isbLblmes() {
		return bLblmes;
	}

	/**
	 * Sets the b lblmes.
	 *
	 * @param bLblmes the new b lblmes
	 */
	public void setbLblmes(boolean bLblmes) {
		this.bLblmes = bLblmes;
	}

	/**
	 * Checks if is b combo mes.
	 *
	 * @return true, if is b combo mes
	 */
	public boolean isbComboMes() {
		return bComboMes;
	}

	/**
	 * Sets the b combo mes.
	 *
	 * @param bComboMes the new b combo mes
	 */
	public void setbComboMes(boolean bComboMes) {
		this.bComboMes = bComboMes;
	}

	/**
	 * Checks if is btxts.
	 *
	 * @return true, if is btxts
	 */
	public boolean isBtxts() {
		return btxts;
	}

	/**
	 * Sets the btxts.
	 *
	 * @param btxts the new btxts
	 */
	public void setBtxts(boolean btxts) {
		this.btxts = btxts;
	}

	/**
	 * Checks if is b visible.
	 *
	 * @return true, if is b visible
	 */
	public boolean isbVisible() {
		return bVisible;
	}

	/**
	 * Sets the b visible.
	 *
	 * @param bVisible the new b visible
	 */
	public void setbVisible(boolean bVisible) {
		this.bVisible = bVisible;
	}

	/**
	 * Checks if is b habilita.
	 *
	 * @return true, if is b habilita
	 */
	public boolean isbHabilita() {
		return bHabilita;
	}

	/**
	 * Sets the b habilita.
	 *
	 * @param bHabilita the new b habilita
	 */
	public void setbHabilita(boolean bHabilita) {
		this.bHabilita = bHabilita;
	}

	/**
	 * Checks if is b desabilita.
	 *
	 * @return true, if is b desabilita
	 */
	public boolean isbDesabilita() {
		return bDesabilita;
	}

	/**
	 * Sets the b desabilita.
	 *
	 * @param bDesabilita the new b desabilita
	 */
	public void setbDesabilita(boolean bDesabilita) {
		this.bDesabilita = bDesabilita;
	}

	/** The list pm 50. */
	private List<Pm5011> listPm50;

	/**
	 * Gets the list pm 50.
	 *
	 * @return the list pm 50
	 */
	public List<Pm5011> getListPm50() {
		return listPm50;
	}

	/**
	 * Sets the list pm 50.
	 *
	 * @param listPm50 the new list pm 50
	 */
	public void setListPm50(List<Pm5011> listPm50) {
		this.listPm50 = listPm50;
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
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
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
	 * Gets the pm 5011 repository.
	 *
	 * @return the pm 5011 repository
	 */
	public Pm5011Repository getPm5011Repository() {
		return pm5011Repository;
	}

	/**
	 * Sets the pm 5011 repository.
	 *
	 * @param pm5011Repository the new pm 5011 repository
	 */
	public void setPm5011Repository(Pm5011Repository pm5011Repository) {
		this.pm5011Repository = pm5011Repository;
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
	 * Checks if is b modificar.
	 *
	 * @return true, if is b modificar
	 */
	public boolean isbModificar() {
		return bModificar;
	}

	/**
	 * Sets the b modificar.
	 *
	 * @param bModificar the new b modificar
	 */
	public void setbModificar(boolean bModificar) {
		this.bModificar = bModificar;
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
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.isbDesabilita();
		this.isbHabilita();
		this.isbVisible();
		this.isBtxts();
		pm5011 = new Pm5011();
		listPm50 = new ArrayList<Pm5011>();

		bBorrar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bBtnModificar = Boolean.FALSE;
		// listPm50 =
		// pm5011Repository.findByidsector(this.getUserDetails().getIdSector());
		listPm50 = orderByMensual();
		if (CollectionUtils.isEmpty(listPm50)) {
			pm5011.setObstcc("");
			pm5011.setObstcr("");
			listPm50.add(pm5011);
			bBorrar = Boolean.TRUE;
			bBtnModificar = Boolean.TRUE;
			this.isbModificar();
		}

		if (listPm50.size() == 12)
			bDesabilita = Boolean.TRUE;
		else
			bDesabilita = Boolean.FALSE;

		lisMes = tcMesRepository.findAll();

		tcMes = new TcMes();
		tcMes.setMes(lisMes.get(0).getMes());

		jasperReporteName = "reporte48";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		parameters.put("SECTOR", this.getUserDetails().getIdSector());
		parameters.put("MES", mes);
		parameters.put("imagen", this.getUserDetails().getPathImgCab1());
		parameters.put("N4", firmas.getN4());
		parameters.put("L4", firmas.getL4());
		parameters.put("N5", firmas.getN5());
		parameters.put("L5", firmas.getL5());
		parameters.put("N3", firmas.getN3());
		parameters.put("L3", firmas.getL3());
		parameters.put("CAMPO1", firmas.getCampo1());
		parameters.put("CLAVE", conctb.getClave());
		parameters.put("ANO_EMP", conctb.getAnoemp());
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
	 * Modify.
	 *
	 * @param index the index
	 */
	public void modify(Integer index) {
		this.isbMensual();
		this.desableButtons(1);
		bLblmes = Boolean.TRUE;
		bComboMes = Boolean.FALSE;
		bVisible = Boolean.FALSE;
		bBtnModificar = Boolean.TRUE;
		bModificar = Boolean.TRUE;
		btxts = Boolean.FALSE;
		bHabilita = Boolean.FALSE;
		bDesabilita = Boolean.TRUE;
		bVisible = Boolean.TRUE;
	}

	/**
	 * Adds the.
	 *
	 * @param index the index
	 */
	public void add(Integer index) {
		// Integer lastIndex = pm5011Repository.findAll().size() ;
		bLblmes = Boolean.FALSE;
		bComboMes = Boolean.TRUE;
		btxts = Boolean.FALSE;
		bHabilita = Boolean.FALSE;
		bDesabilita = Boolean.TRUE;
		bVisible = Boolean.TRUE;
		pm5011 = new Pm5011();
		bBorrar = Boolean.TRUE;
		bModificar = Boolean.FALSE;
		bBtnModificar = Boolean.TRUE;
		listPm50.add(index, pm5011);

	}

	/** The suma acumulado. */
	Integer sumaAcumulado;

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm5011 = new Pm5011();
		pm5011 = listPm50.get(index);
		pm5011 = notEmpty(pm5011);

		if (!bModificar)
			pm5011.setMensual(Integer.valueOf(tcMes.getMes()));

		boolean bandera = validateTxt(index);
		pm5011.setIdsector(this.getUserDetails().getIdSector());
		pm5011.setFeccap(Calendar.getInstance().getTime());
		pm5011.setCapturo(this.getUserDetails().getUsername());
		pm5011.setUserid(this.getUserDetails().getUsername());
		pm5011.setIdRef(0L);
		if (bandera) {
			Integer existe = pm5011Repository.existMonth(pm5011.getMensual(), this.getUserDetails().getIdSector());
			if (bModificar) {
				pm5011Repository.save(pm5011);
				acumular();
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se modificaron correctamente los datos");
				RequestContext.getCurrentInstance().execute("PF('pm5011Wdg').paginator.setPage("
						+ (this.pm5011Repository.findByIdSector(this.getUserDetails().getIdSector()).size() - 1) + ")");
				bLblmes = Boolean.TRUE;
				bComboMes = Boolean.FALSE;
				btxts = Boolean.TRUE;
				bHabilita = Boolean.TRUE;
				bDesabilita = Boolean.FALSE;
				bVisible = Boolean.FALSE;
				listPm50 = orderByMensual();
				bBtnModificar = Boolean.FALSE;
				bBorrar = Boolean.FALSE;

				if (pm5011Repository.count() == Long.valueOf(12).longValue())
					bDesabilita = Boolean.TRUE;
				else
					bDesabilita = Boolean.FALSE;

			} else if (existe.intValue() == 0) {
				pm5011Repository.save(pm5011);
				acumular();
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"La informacion se guardo correctamente");
				RequestContext.getCurrentInstance().execute("PF('pm5011Wdg').paginator.setPage("
						+ (this.pm5011Repository.findByIdSector(this.getUserDetails().getIdSector()).size() - 1) + ")");
				bLblmes = Boolean.TRUE;
				bComboMes = Boolean.FALSE;
				btxts = Boolean.TRUE;
				bHabilita = Boolean.TRUE;
				bVisible = Boolean.FALSE;
				listPm50 = orderByMensual();
				bBtnModificar = Boolean.FALSE;
				bBorrar = Boolean.FALSE;
				if (pm5011Repository.count() == Long.valueOf(12).longValue())
					bDesabilita = Boolean.TRUE;
				else
					bDesabilita = Boolean.FALSE;

			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El mes ya fue registrado");
			}

		}
	}

	/**
	 * Desable buttons.
	 *
	 * @param opt the opt
	 */
	public void desableButtons(Integer opt) {
		switch (opt) {
		case 1:
			this.isbDesabilita();
			this.isbHabilita();
			this.isbVisible();
			break;
		case 2:
			bHabilita = Boolean.FALSE;
			bDesabilita = Boolean.TRUE;
			break;

		default:
			break;
		}
	}

	/**
	 * Cancel.
	 *
	 * @param index the index
	 */
	public void cancel(Integer index) {

		bLblmes = Boolean.TRUE;
		bComboMes = Boolean.FALSE;
		listPm50.remove(index);
		listPm50 = orderByMensual();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edición cancelada");
		btxts = Boolean.TRUE;
		bHabilita = Boolean.TRUE;
		bVisible = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		if (CollectionUtils.isEmpty(listPm50)) {
			pm5011.setObstcc("");
			pm5011.setObstcr("");
			listPm50.add(pm5011);
			bBorrar = Boolean.TRUE;
			bBtnModificar = Boolean.TRUE;
			this.isbModificar();
		} else {
			bBtnModificar = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
		}

		if (listPm50.size() == 12)
			bDesabilita = Boolean.TRUE;
		else
			bDesabilita = Boolean.FALSE;

	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {

		if (bModificar) {
			pm5011 = pm5011Repository.getByIdSectorAndMensual(this.getUserDetails().getIdSector(),
					listPm50.get(index).getMensual());
			listPm50.add(index, pm5011);
		} else {
			listPm50.add(index, new Pm5011());
		}

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		pm5011Repository.delete(listPm50.get(index).getId());
		acumular();
		listPm50 = orderByMensual();
		if (CollectionUtils.isEmpty(listPm50)) {
			Pm5011 pm = new Pm5011();
			pm.setObstcc("");
			listPm50.add(pm);
		}
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El registro se borro correctamente");

		bLblmes = Boolean.TRUE;
		bComboMes = Boolean.FALSE;
		btxts = Boolean.TRUE;
		bHabilita = Boolean.TRUE;
		bDesabilita = Boolean.FALSE;
		bVisible = Boolean.FALSE;
		bBorrar = Boolean.FALSE;

		if (CollectionUtils.isEmpty(pm5011Repository.findAll())) {
			bBorrar = Boolean.TRUE;
			bBtnModificar = Boolean.TRUE;

		}

	}

	/**
	 * Validate txt.
	 *
	 * @param index the index
	 * @return true, if successful
	 */
	public boolean validateTxt(Integer index) {
		boolean bandera = Boolean.TRUE;
		if (null == listPm50.get(index).getMensual()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo mensual es obliga torio");
			bandera = Boolean.FALSE;
		} else if (listPm50.get(index).getMensual().intValue() > 12) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo mensual debe de ser menor o igual a 12");
			bandera = Boolean.FALSE;
		} else if (null == listPm50.get(index).getMensual()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo mensual es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == listPm50.get(index).getTcc()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Número de contribuyentes que han cumplido con los derechos de agua potable es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == listPm50.get(index).getTcr()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Total de contribuyentes registrados en el padrón al cierre del ejercicio anterior es obligatorio");
			bandera = Boolean.FALSE;
		}
		return bandera;
	}

	/**
	 * Sort by id asc.
	 *
	 * @return the sort
	 */
	private Sort sortByIdAsc() {
		return new Sort(Sort.Direction.ASC, "mensual");
	}

	/**
	 * Order by mensual.
	 *
	 * @return the list
	 */
	public List<Pm5011> orderByMensual() {

		return (List<Pm5011>) pm5011Repository.findAll(sortByIdAsc());

	}

	/**
	 * Not empty.
	 *
	 * @param entity the entity
	 * @return the pm 5011
	 */
	public Pm5011 notEmpty(Pm5011 entity) {
		entity.setMensual(entity.getMensual() == null ? 0 : entity.getMensual());
		entity.setTcc(entity.getTcc() == null ? 0 : entity.getTcc());
		entity.setTcr(entity.getTcr() == null ? 0 : entity.getTcr());
		entity.setObstcc(entity.getObstcc() == null ? "" : entity.getObstcc());
		entity.setObstcr(entity.getObstcr() == null ? "" : entity.getObstcr());
		return entity;
	}

	/**
	 * Acumular.
	 */
	public void acumular() {
		List<Pm5011> lis = orderByMensual();
		sumaAcumulado = 0;
		for (Pm5011 sum : lis) {
			sumaAcumulado = sumaAcumulado + sum.getTcc();
			sum.setAcumtcc(sumaAcumulado);
			pm5011Repository.save(sum);
		}

	}

	/**
	 * View pdf.
	 */
	public void viewPdf() {
		if (null != mes) {
			createFilePdfInline();
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Mes a Consulta es obligatorio.");
			
		}
	}
}
