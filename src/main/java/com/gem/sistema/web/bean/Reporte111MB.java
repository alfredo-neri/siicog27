package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.gem.sistema.business.domain.Pm2511;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm2511Repository;
import com.gem.sistema.business.service.catalogos.Pm2511Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte111MB.
 */
@ManagedBean(name = "reporte111MB")
@ViewScoped
public class Reporte111MB extends BaseDirectReport {

	/** The trimestre. */
	private String trimestre;
	
	/** The list trimestre. */
	private List<String> listTrimestre;

	/** The pm 2511. */
	private Pm2511 pm2511;
	
	/** The list pm 2511. */
	private List<Pm2511> listPm2511;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm2511> comboTri;

	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The b lbl trimestre. */
	private boolean bLblTrimestre = Boolean.TRUE;
	
	/** The b combo tri. */
	private boolean bComboTri = Boolean.FALSE;
	
	/** The b lbl. */
	private boolean bLbl = Boolean.TRUE;
	
	/** The b txt. */
	private boolean bTxt = Boolean.FALSE;
	
	/** The b V save. */
	private boolean bVSave = Boolean.FALSE;
	
	/** The b V modificar. */
	private boolean bVModificar = Boolean.TRUE;
	
	/** The b btn moficar. */
	private boolean bBtnMoficar = Boolean.TRUE;
	
	/** The b modificar. */
	private boolean bModificar = Boolean.FALSE;
	
	/** The b borrar. */
	private boolean bBorrar = Boolean.TRUE;
	
	/** The b add. */
	private boolean bAdd = Boolean.FALSE;

	/** The pm 2511 service. */
	@ManagedProperty("#{pm2511Service}")
	private Pm2511Service pm2511Service;

	/** The pm 2511 repository. */
	@ManagedProperty("#{pm2511Repository}")
	private Pm2511Repository pm2511Repository;

	/**
	 * Gets the trimestre.
	 *
	 * @return the trimestre
	 */
	public String getTrimestre() {
		return trimestre;
	}

	/**
	 * Sets the trimestre.
	 *
	 * @param trimestre the new trimestre
	 */
	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	/**
	 * Gets the list trimestre.
	 *
	 * @return the list trimestre
	 */
	public List<String> getListTrimestre() {
		return listTrimestre;
	}

	/**
	 * Sets the list trimestre.
	 *
	 * @param listTrimestre the new list trimestre
	 */
	public void setListTrimestre(List<String> listTrimestre) {
		this.listTrimestre = listTrimestre;
	}

	/**
	 * Gets the pm 2511.
	 *
	 * @return the pm 2511
	 */
	public Pm2511 getPm2511() {
		return pm2511;
	}

	/**
	 * Sets the pm 2511.
	 *
	 * @param pm2511 the new pm 2511
	 */
	public void setPm2511(Pm2511 pm2511) {
		this.pm2511 = pm2511;
	}

	/**
	 * Gets the list pm 2511.
	 *
	 * @return the list pm 2511
	 */
	public List<Pm2511> getListPm2511() {
		return listPm2511;
	}

	/**
	 * Sets the list pm 2511.
	 *
	 * @param listPm2511 the new list pm 2511
	 */
	public void setListPm2511(List<Pm2511> listPm2511) {
		this.listPm2511 = listPm2511;
	}

	/**
	 * Gets the pm 2511 service.
	 *
	 * @return the pm 2511 service
	 */
	public Pm2511Service getPm2511Service() {
		return pm2511Service;
	}

	/**
	 * Sets the pm 2511 service.
	 *
	 * @param pm2511Service the new pm 2511 service
	 */
	public void setPm2511Service(Pm2511Service pm2511Service) {
		this.pm2511Service = pm2511Service;
	}

	/**
	 * Checks if is b lbl trimestre.
	 *
	 * @return true, if is b lbl trimestre
	 */
	public boolean isbLblTrimestre() {
		return bLblTrimestre;
	}

	/**
	 * Sets the b lbl trimestre.
	 *
	 * @param bLblTrimestre the new b lbl trimestre
	 */
	public void setbLblTrimestre(boolean bLblTrimestre) {
		this.bLblTrimestre = bLblTrimestre;
	}

	/**
	 * Checks if is b combo tri.
	 *
	 * @return true, if is b combo tri
	 */
	public boolean isbComboTri() {
		return bComboTri;
	}

	/**
	 * Sets the b combo tri.
	 *
	 * @param bComboTri the new b combo tri
	 */
	public void setbComboTri(boolean bComboTri) {
		this.bComboTri = bComboTri;
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
	 * Checks if is b V save.
	 *
	 * @return true, if is b V save
	 */
	public boolean isbVSave() {
		return bVSave;
	}

	/**
	 * Sets the b V save.
	 *
	 * @param bVSave the new b V save
	 */
	public void setbVSave(boolean bVSave) {
		this.bVSave = bVSave;
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
	 * Checks if is b btn moficar.
	 *
	 * @return true, if is b btn moficar
	 */
	public boolean isbBtnMoficar() {
		return bBtnMoficar;
	}

	/**
	 * Sets the b btn moficar.
	 *
	 * @param bBtnMoficar the new b btn moficar
	 */
	public void setbBtnMoficar(boolean bBtnMoficar) {
		this.bBtnMoficar = bBtnMoficar;
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
	 * Gets the pm 2511 repository.
	 *
	 * @return the pm 2511 repository
	 */
	public Pm2511Repository getPm2511Repository() {
		return pm2511Repository;
	}

	/**
	 * Sets the pm 2511 repository.
	 *
	 * @param pm2511Repository the new pm 2511 repository
	 */
	public void setPm2511Repository(Pm2511Repository pm2511Repository) {
		this.pm2511Repository = pm2511Repository;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte111";
		endFilename = jasperReporteName + ".pdf";

		bLblTrimestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bAdd = Boolean.FALSE;
		listTrimestre = new ArrayList<String>();
		for (int i = 1; i <= 4; i++)
			listTrimestre.add("0" + i);
		listPm2511 = pm2511Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm2511.get(0).getUserid()) {
			bLblTrimestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.TRUE;
			bAdd = Boolean.FALSE;
		}

		for (Pm2511 p : listPm2511) {
			listTrimestre.remove(p.getTrimestre());
		}
		trimestre = listTrimestre.get(0);

		comboTri = pm2511Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getTrimestre().toString();
		if (listPm2511.size() == 4) {
			this.setbAdd(Boolean.TRUE);
		}

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		parameters.put("TRIMESTRE", Integer.valueOf(conTrim));
		parameters.put("SECTOR", this.getUserDetails().getIdSector());
		parameters.put("imagen", this.getUserDetails().getPathImgCab1());
		parameters.put("nombreMunicipio", firmas.getCampo1());
		parameters.put("clave", conctb.getClave());
		parameters.put("N4", firmas.getL4());
		parameters.put("L4", firmas.getN4());
		parameters.put("N5", "");
		parameters.put("L5", "");
		parameters.put("N3", firmas.getL5());
		parameters.put("L3", firmas.getN5());
		parameters.put("ANO_EMP", firmas.getCampo3());

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
	 * Gets the con trim.
	 *
	 * @return the con trim
	 */
	public String getConTrim() {
		return conTrim;
	}

	/**
	 * Sets the con trim.
	 *
	 * @param conTrim the new con trim
	 */
	public void setConTrim(String conTrim) {
		this.conTrim = conTrim;
	}

	/**
	 * Gets the combo tri.
	 *
	 * @return the combo tri
	 */
	public List<Pm2511> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm2511> comboTri) {
		this.comboTri = comboTri;
	}

	/**
	 * Adds the.
	 */
	public void add() {
		bLblTrimestre = Boolean.FALSE;
		bComboTri = Boolean.TRUE;
		bLbl = Boolean.FALSE;
		bTxt = Boolean.TRUE;
		bVSave = Boolean.TRUE;
		bVModificar = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bBorrar = Boolean.TRUE;
		bAdd = Boolean.TRUE;
		listPm2511 = this.getPm2511Repository().findAllByIdsector(this.getUserDetails().getIdSector());
		if (CollectionUtils.isNotEmpty(listPm2511))
			listPm2511.add(new Pm2511());
		else
			listPm2511.add(this.pm2511Service.add());
		RequestContext.getCurrentInstance()
				.execute("PF('pm2511Wdg').paginator.setPage(" + (listPm2511.size() - 1) + ");");
		// if (null == listPm2511.get(1).getUserid())
		// listPm2511.remove(1);

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm2511 = this.entitySave(index);
		if (bModificar) {
			pm2511Repository.save(pm2511);
			this.calcularAcumulado(pm2511);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se modificaron los datos correctamente");

			bLblTrimestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.FALSE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
			bAdd = Boolean.FALSE;
		} else {
			listPm2511.add(index, pm2511);
			listPm2511 = pm2511Service.save(index, listPm2511);
			if (listPm2511.get(index).isbValidar()) {
				this.calcularAcumulado(pm2511);
				bLblTrimestre = Boolean.TRUE;
				bComboTri = Boolean.FALSE;
				bLbl = Boolean.TRUE;
				bTxt = Boolean.FALSE;
				bVSave = Boolean.FALSE;
				bVModificar = Boolean.TRUE;
				bBtnMoficar = Boolean.FALSE;
				bModificar = Boolean.FALSE;
				bBorrar = Boolean.TRUE;
				bAdd = Boolean.FALSE;
			}
		}
		if (null == listPm2511.get(index).getUserid()) {
			bBorrar = Boolean.TRUE;
		} else {
			bBorrar = Boolean.FALSE;
		}
		comboTri = pm2511Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());
		if (listPm2511.size() == 4) {
			bAdd = Boolean.TRUE;
		}

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm2511 = pm2511Service.delete(index, listPm2511);
		// for (Pm2511 p : listPm2511) {
		// listTrimestre.remove(p.getTrimestre());
		// }
		listTrimestre.remove(index);

		if (CollectionUtils.isEmpty(this.pm2511Repository.findAllByIdsector(this.getUserDetails().getIdSector()))) {
			bLblTrimestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.TRUE;
			bAdd = Boolean.FALSE;
		} else {
			bLblTrimestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
			bAdd = Boolean.FALSE;
		}
		comboTri = pm2511Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());
	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm2511 = pm2511Repository.findAllBytrimestreAndIdsector(listPm2511.get(index).getTrimestre(),
					this.getUserDetails().getIdSector());
			listPm2511.get(index).setTrimestre(pm2511.getTrimestre());
			listPm2511.get(index).setAgua(pm2511.getAgua());
			listPm2511.get(index).setObsagua(pm2511.getObsagua());
			listPm2511.get(index).setPoblacion(pm2511.getPoblacion());
			listPm2511.get(index).setObspob(pm2511.getObspob());
		} else
			listPm2511 = pm2511Service.clean(index, listPm2511);
	}

	/**
	 * Modify.
	 *
	 * @param index the index
	 */
	public void modify(Integer index) {

		bLblTrimestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.FALSE;
		bTxt = Boolean.TRUE;
		bVSave = Boolean.TRUE;
		bVModificar = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.TRUE;
		bBorrar = Boolean.TRUE;
		bAdd = Boolean.TRUE;

	}

	/**
	 * Cancel.
	 *
	 * @param index the index
	 */
	public void cancel(Integer index) {
		bLblTrimestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;
		listPm2511 = pm2511Repository.findAllByIdsector(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm2511)) {

			bBorrar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;

		} else {
			bBtnMoficar = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
			bVModificar = Boolean.FALSE;
		}
		if (listPm2511.size() == 4) {
			bAdd = Boolean.TRUE;
		}

		bModificar = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bAdd = Boolean.FALSE;
		listPm2511 = pm2511Service.orderByTrimestreAsc(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 2511
	 */
	public Pm2511 entitySave(Integer index) {
		pm2511 = listPm2511.get(index);
		pm2511.setUserid(this.getUserDetails().getUsername());
		pm2511.setIdRef(0L);
		pm2511.setIdsector(this.getUserDetails().getIdSector());
		pm2511.setFeccap(Calendar.getInstance().getTime());
		pm2511.setCapturo(this.getUserDetails().getUsername());
		pm2511.setTrimestre(bModificar == true ? pm2511.getTrimestre() : Integer.valueOf(trimestre));
		return pm2511;
	}

	/**
	 * Calcular acumulado.
	 *
	 * @param entity the entity
	 */
	public void calcularAcumulado(Pm2511 entity) {
		// Integer contar =
		// pm2511Repository.count(this.getUserDetails().getIdSector());

		// if (contar.intValue() < 2) {
		// entity.setAcumagua(BigDecimal.ZERO);
		// } else {
		entity.setAcumagua(pm2511Repository.sumAcumByIdSector(this.getUserDetails().getIdSector()));
		// }
		pm2511Repository.save(entity);
	}

}
