package com.gem.sistema.web.semaphore;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;



// TODO: Auto-generated Javadoc
/**
 * The Class PolienUK.
 */
public class PolienUK{

	/** The anopol. */
	private Integer anopol;
	
	/** The tippol. */
	private String tippol;
	
	/** The mespol. */
	private Integer mespol;
	
	/** The conpol. */
	private Integer conpol;
	
	/** The idsector. */
	private Integer idsector;
	
	

	/**
	 * Instantiates a new polien UK.
	 *
	 * @param anopol the anopol
	 * @param tippol the tippol
	 * @param mespol the mespol
	 * @param conpol the conpol
	 * @param idsector the idsector
	 */
	public PolienUK(Integer anopol, String tippol, Integer mespol, Integer conpol, Integer idsector) {
		super();
		if(anopol == null || tippol == null || mespol == null || conpol == null || idsector == null){
			throw new RuntimeException("Los parametros de PolienUK no pueden ser nulos.");
		}
		
		this.anopol = anopol;
		this.tippol = tippol;
		this.mespol = mespol;
		this.conpol = conpol;
		this.idsector = idsector;
	}



	/**
	 * Gets the anopol.
	 *
	 * @return the anopol
	 */
	public Integer getAnopol() {
		return anopol;
	}



	/**
	 * Sets the anopol.
	 *
	 * @param anopol the new anopol
	 */
	public void setAnopol(Integer anopol) {
		this.anopol = anopol;
	}



	/**
	 * Gets the tippol.
	 *
	 * @return the tippol
	 */
	public String getTippol() {
		return tippol;
	}



	/**
	 * Sets the tippol.
	 *
	 * @param tippol the new tippol
	 */
	public void setTippol(String tippol) {
		this.tippol = tippol;
	}



	/**
	 * Gets the mespol.
	 *
	 * @return the mespol
	 */
	public Integer getMespol() {
		return mespol;
	}



	/**
	 * Sets the mespol.
	 *
	 * @param mespol the new mespol
	 */
	public void setMespol(Integer mespol) {
		this.mespol = mespol;
	}



	/**
	 * Gets the conpol.
	 *
	 * @return the conpol
	 */
	public Integer getConpol() {
		return conpol;
	}



	/**
	 * Sets the conpol.
	 *
	 * @param conpol the new conpol
	 */
	public void setConpol(Integer conpol) {
		this.conpol = conpol;
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



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cuenta [anopol=" + anopol + ", tippol=" + tippol + ", mespol=" + mespol + ", conpol="
				+ conpol  + ", idsector=" + idsector + "]";
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anopol == null) ? 0 : anopol.hashCode());
		result = prime * result + ((conpol == null) ? 0 : conpol.hashCode());
		result = prime * result + ((idsector == null) ? 0 : idsector.hashCode());
		result = prime * result + ((mespol == null) ? 0 : mespol.hashCode());
		result = prime * result + ((tippol == null) ? 0 : tippol.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PolienUK other = (PolienUK) obj;
		if (anopol == null) {
			if (other.anopol != null)
				return false;
		} else if (!anopol.equals(other.anopol))
			return false;
		if (conpol == null) {
			if (other.conpol != null)
				return false;
		} else if (!conpol.equals(other.conpol))
			return false;
		if (idsector == null) {
			if (other.idsector != null)
				return false;
		} else if (!idsector.equals(other.idsector))
			return false;
		if (mespol == null) {
			if (other.mespol != null)
				return false;
		} else if (!mespol.equals(other.mespol))
			return false;
		if (tippol == null) {
			if (other.tippol != null)
				return false;
		} else if (!tippol.equals(other.tippol))
			return false;
		return true;
	}

	
 

}
