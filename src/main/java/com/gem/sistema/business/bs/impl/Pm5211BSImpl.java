package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm5211BS;
import com.gem.sistema.business.domain.Pm2511;
import com.gem.sistema.business.domain.Pm5211;
import com.gem.sistema.business.repository.catalogs.Pm2511Repository;
import com.gem.sistema.business.repository.catalogs.Pm5211Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5211BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm5211BS")
public class Pm5211BSImpl implements Pm5211BS {
	
	/** The t acum ipr. */
	private BigDecimal tAcumIpr;
	
	/** The t acum ipm. */
	private BigDecimal tAcumIpm;
	
	/** The t acum der. */
	private BigDecimal tAcumDer;
	
	/** The t acum prod. */
	private BigDecimal tAcumProd;
	
	/** The t acum aprov. */
	private BigDecimal tAcumAprov;
	
	/** The t acum apme. */
	private BigDecimal tAcumApme;
	
	/** The t acum acce. */
	private BigDecimal tAcumAcce;
	
	/** The t acumipf. */
	private BigDecimal tAcumipf;
	
	/** The t acum ting. */
	private BigDecimal tAcumTing;
	
	/** The list. */
	private List<Pm5211> list;
	
	/** The pm 5211 repository. */
	@Autowired
	private Pm5211Repository pm5211Repository;

	/**
	 * Gets the pm 5211 repository.
	 *
	 * @return the pm 5211 repository
	 */
	public Pm5211Repository getPm5211Repository() {
		return pm5211Repository;
	}

	/**
	 * Sets the pm 5211 repository.
	 *
	 * @param pm5211Repository the new pm 5211 repository
	 */
	public void setPm5211Repository(Pm5211Repository pm5211Repository) {
		this.pm5211Repository = pm5211Repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5211BS#orderByMendsualAsc()
	 */
	@Override
	public List<Pm5211> orderByMendsualAsc(Integer idSector) {
		return (List<Pm5211>) pm5211Repository.findAll(this.orderByMensual());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm5211BS#validateTxt(com.gem.sistema.business
	 * .domain.Pm5211)
	 */
	@Override
	public boolean validateTxt(Pm5211 pm5211) {
		boolean bandera = Boolean.TRUE;
		if (null == pm5211.getMensual()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El mes es obligatorio");
			bandera = Boolean.FALSE;
		} else if (pm5211.getMensual() > 12) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El mes debe de ser menor a 13");
			bandera = Boolean.FALSE;
		} else if (pm5211.getMensual() == 0) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El mes debe de ser mayor a cero");
			bandera = Boolean.FALSE;
		} else if (null == pm5211.getIpr()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El monto del campo Ingresos Propios Recudados es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5211.getImp()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El monto del campo Impuestos es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5211.getDer()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El monto del campo Derechos es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5211.getProd()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El monto del campo Productos es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5211.getAprov()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El monto del campo Aprovechamiento es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5211.getApmej()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El monto del campo Aplicación de Mejoras es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5211.getAcc()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El monto del campo Accesorios es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5211.getIpf()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El monto del campo Ingresos Propios Financiamiento es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5211.getTing()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El monto del campo Total de Ingresos es obligatorio");
			bandera = Boolean.FALSE;
		}
		return bandera;
	}

	/** The pm 5211. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5211BS#save(java.util.List,
	 * java.lang.Integer)
	 */
	Pm5211 pm5211 = null;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5211BS#save(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5211> save(List<Pm5211> listPm5211, Integer index) {
		pm5211 = new Pm5211();
		list = new ArrayList<Pm5211>();
		pm5211 = listPm5211.get(index);
		boolean bandera = this.validateTxt(pm5211);

		if (bandera) {

			if (this.findByMensual(pm5211.getMensual(), pm5211.getIdsector())) {

				pm5211.setFeccap(Calendar.getInstance().getTime());
				pm5211Repository.save(pm5211);
				
				list = this.aculation(pm5211.getIdsector());
				list.get(index).setbGuardra(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"La información se guardo correctamente");

			} else {
				
				list = pm5211Repository.findByIdSectorOderByMensual(pm5211.getIdsector());
				list.add(index, pm5211);
				list.get(index).setbGuardra(Boolean.FALSE);
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El mes ya fue registrado");
			}
		} else {

			pm5211.setbGuardra(Boolean.FALSE);
			pm5211.setObsgral("");
			list.add(pm5211);
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5211BS#delete(java.util.List,
	 * java.lang.Integer)
	 */
	@Override
	public List<Pm5211> delete(List<Pm5211> listPm5211, Integer index) {
		pm5211Repository.delete(listPm5211.get(index).getId());
		listPm5211.remove(index);
		listPm5211 = this.orderByMendsualAsc(listPm5211.get(index).getIdsector());
		Pm5211 pm5211 = new Pm5211();
		if (CollectionUtils.isEmpty(listPm5211)) {
			pm5211.setIpr(BigDecimal.ZERO);
			pm5211.setImp(BigDecimal.ZERO);
			pm5211.setDer(BigDecimal.ZERO);
			pm5211.setProd(BigDecimal.ZERO);
			pm5211.setAprov(BigDecimal.ZERO);
			pm5211.setApmej(BigDecimal.ZERO);
			pm5211.setAcc(BigDecimal.ZERO);
			pm5211.setIpf(BigDecimal.ZERO);
			pm5211.setTing(BigDecimal.ZERO);
			pm5211.setMensual(0);
			listPm5211.add(pm5211);

		}
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Registro eliminado correctamente");
		return listPm5211;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5211BS#add(java.util.List,
	 * java.lang.Integer)
	 */
	@Override
	public List<Pm5211> add(List<Pm5211> listPm5211, Integer index) {
		Pm5211 pm5211Entity = new Pm5211();
		listPm5211.add(index, pm5211Entity);
		if (null == listPm5211.get(1).getId())
			listPm5211.remove(1);
		return listPm5211;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5211BS#clean(java.util.List,
	 * java.lang.Integer)
	 */
	@Override
	public List<Pm5211> clean(List<Pm5211> listPm5211, Integer index) {
		listPm5211.get(index).setIpr(BigDecimal.ZERO);
		listPm5211.get(index).setImp(BigDecimal.ZERO);
		listPm5211.get(index).setDer(BigDecimal.ZERO);
		listPm5211.get(index).setProd(BigDecimal.ZERO);
		listPm5211.get(index).setAprov(BigDecimal.ZERO);
		listPm5211.get(index).setApmej(BigDecimal.ZERO);
		listPm5211.get(index).setAcc(BigDecimal.ZERO);
		listPm5211.get(index).setIpf(BigDecimal.ZERO);
		listPm5211.get(index).setTing(BigDecimal.ZERO);
		listPm5211.get(index).setMensual(null);
		listPm5211.get(index).setObsgral("");
		return listPm5211;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5211BS#cancel(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5211> cancel(List<Pm5211> listPm5211, Integer index) {
		listPm5211 = this.orderByMendsualAsc(listPm5211.get(index).getIdsector());
		Pm5211 pm5211 = new Pm5211();
		if (CollectionUtils.isEmpty(listPm5211)) {
			pm5211.setIpr(BigDecimal.ZERO);
			pm5211.setImp(BigDecimal.ZERO);
			pm5211.setDer(BigDecimal.ZERO);
			pm5211.setProd(BigDecimal.ZERO);
			pm5211.setAprov(BigDecimal.ZERO);
			pm5211.setApmej(BigDecimal.ZERO);
			pm5211.setAcc(BigDecimal.ZERO);
			pm5211.setIpf(BigDecimal.ZERO);
			pm5211.setTing(BigDecimal.ZERO);
			pm5211.setMensual(0);
			listPm5211.add(pm5211);

		}
		return listPm5211;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5211BS#findByMensual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByMensual(Integer mensual, Integer idSector) {
		Pm5211 exist = pm5211Repository.getMensual(idSector, mensual);
		boolean bandera = Boolean.FALSE;
		if (null == exist)
			bandera = Boolean.TRUE;

		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5211BS#orderByMensual()
	 */
	public Sort orderByMensual() {
		return new Sort(Sort.Direction.ASC, "mensual");
	}

	

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5211BS#aculation(java.lang.Integer)
	 */
	@Override
	public List<Pm5211> aculation(Integer idSector) {
		Double ipr = 0.0;
		Double imp = 0.0;
		Double der = 0.0;
		Double prod = 0.0;
		Double aprov = 0.0;
		Double apmej = 0.0;
		Double acc = 0.0;
		Double ipf = 0.0;
		Double ting = 0.0;
		List<Pm5211> list = pm5211Repository.findByIdSectorOderByMensual(idSector);
		List<Pm5211> listRetur = new ArrayList<Pm5211>();
		Pm5211 saveData = new Pm5211();
		for (Pm5211 calcular : list) {
			ipr = ipr + calcular.getIpr().doubleValue();
			imp = imp + calcular.getImp().doubleValue();
			der = der + calcular.getDer().doubleValue();
			prod = prod + calcular.getProd().doubleValue();
			aprov = aprov + calcular.getAprov().doubleValue();
			apmej = apmej + calcular.getApmej().doubleValue();
			acc = acc + calcular.getAcc().doubleValue();
			ipf = ipf + calcular.getIpf().doubleValue();
			ting = ting + calcular.getTing().doubleValue();
			saveData = calcular;
			saveData.setAcuipr(BigDecimal.valueOf(ipr));
			saveData.setAcuimp(BigDecimal.valueOf(imp));
			saveData.setAcuder(BigDecimal.valueOf(der));
			saveData.setAcuprod(BigDecimal.valueOf(prod));
			saveData.setAcuaprov(BigDecimal.valueOf(aprov));
			saveData.setAcuapmej(BigDecimal.valueOf(apmej));
			saveData.setAcuacc(BigDecimal.valueOf(acc));
			saveData.setAcuipf(BigDecimal.valueOf(ipf));
			saveData.setAcuting(BigDecimal.valueOf(ting));
			this.getPm5211Repository().save(saveData);
			listRetur.add(saveData);
		}
		return listRetur;
	}

}
