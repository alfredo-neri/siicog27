package com.gem.sistema.business.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0092164DTO.
 */
public class RF0092164DTO {
	
	/** The feccap. */
	private Date feccap;

	/** The fecdep. */
	private Date fecdep;

	/** The fecdepfor. */
	private Date fecdepfor;

	/** The id ref. */
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The mensual. */
	private Integer mensual;

	/** The mes. */
	private String mes;

	/** The mminfism. */
	private BigDecimal mminfism;

	/** The mminfor. */
	private BigDecimal mminfor;

	/** The ncbfism. */
	private Integer ncbfism;

	/** The ncbfor. */
	private Integer ncbfor;

	/** The obsfism. */
	private String obsfism;

	/** The obsfor. */
	private String obsfor;

	
	/** The userid. */
	private String userid;
	
	/**
	 * Instantiates a new rf0092164dto.
	 */
	public RF0092164DTO(){
		
		this.mminfism = BigDecimal.ZERO;
		this.mminfor = BigDecimal.ZERO;
		this.feccap = Calendar.getInstance().getTime();
		this.mes = "";
		
		this.obsfism = "";
		this.obsfor = "";
		
	}

	/**
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	public Date getFeccap() {
		return feccap;
	}

	/**
	 * Sets the feccap.
	 *
	 * @param feccap the new feccap
	 */
	public void setFeccap(Date feccap) {
		this.feccap = feccap;
	}

	/**
	 * Gets the fecdep.
	 *
	 * @return the fecdep
	 */
	public Date getFecdep() {
		return fecdep;
	}

	/**
	 * Sets the fecdep.
	 *
	 * @param fecdep the new fecdep
	 */
	public void setFecdep(Date fecdep) {
		this.fecdep = fecdep;
	}

	/**
	 * Gets the fecdepfor.
	 *
	 * @return the fecdepfor
	 */
	public Date getFecdepfor() {
		return fecdepfor;
	}

	/**
	 * Sets the fecdepfor.
	 *
	 * @param fecdepfor the new fecdepfor
	 */
	public void setFecdepfor(Date fecdepfor) {
		this.fecdepfor = fecdepfor;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	/**
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public Integer getIdsector() {
		return idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(Integer idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the mensual.
	 *
	 * @return the mensual
	 */
	public Integer getMensual() {
		return mensual;
	}

	/**
	 * Sets the mensual.
	 *
	 * @param mensual the new mensual
	 */
	public void setMensual(Integer mensual) {
		this.mensual = mensual;
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
	 * Gets the mminfism.
	 *
	 * @return the mminfism
	 */
	public BigDecimal getMminfism() {
		return mminfism;
	}

	/**
	 * Sets the mminfism.
	 *
	 * @param mminfism the new mminfism
	 */
	public void setMminfism(BigDecimal mminfism) {
		this.mminfism = mminfism;
	}

	/**
	 * Gets the mminfor.
	 *
	 * @return the mminfor
	 */
	public BigDecimal getMminfor() {
		return mminfor;
	}

	/**
	 * Sets the mminfor.
	 *
	 * @param mminfor the new mminfor
	 */
	public void setMminfor(BigDecimal mminfor) {
		this.mminfor = mminfor;
	}

	/**
	 * Gets the ncbfism.
	 *
	 * @return the ncbfism
	 */
	public Integer getNcbfism() {
		return ncbfism;
	}

	/**
	 * Sets the ncbfism.
	 *
	 * @param ncbfism the new ncbfism
	 */
	public void setNcbfism(Integer ncbfism) {
		this.ncbfism = ncbfism;
	}

	/**
	 * Gets the ncbfor.
	 *
	 * @return the ncbfor
	 */
	public Integer getNcbfor() {
		return ncbfor;
	}

	/**
	 * Sets the ncbfor.
	 *
	 * @param ncbfor the new ncbfor
	 */
	public void setNcbfor(Integer ncbfor) {
		this.ncbfor = ncbfor;
	}

	/**
	 * Gets the obsfism.
	 *
	 * @return the obsfism
	 */
	public String getObsfism() {
		return obsfism;
	}

	/**
	 * Sets the obsfism.
	 *
	 * @param obsfism the new obsfism
	 */
	public void setObsfism(String obsfism) {
		this.obsfism = obsfism;
	}

	/**
	 * Gets the obsfor.
	 *
	 * @return the obsfor
	 */
	public String getObsfor() {
		return obsfor;
	}

	/**
	 * Sets the obsfor.
	 *
	 * @param obsfor the new obsfor
	 */
	public void setObsfor(String obsfor) {
		this.obsfor = obsfor;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	


}
