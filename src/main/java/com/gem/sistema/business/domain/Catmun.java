package com.gem.sistema.business.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.csv.CSVRecord;

// TODO: Auto-generated Javadoc
/**
 * The Class Catmun.
 */
@Entity
@Table(name = "catmun")
public class Catmun extends AbstractEntity{

	/** The clvmun. */
	@Column(name = "clvmun")
    private Integer clvmun;
	
	/** The nommun. */
	@Column(name = "nommun")
    private String nommun;
	
	/** The c 323. */
	@Column(name = "c323")
	private BigDecimal c323;
	
	/** The c 311. */
	@Column(name = "c311")
	private BigDecimal c311;
	
	/** The a 323. */
	@Column(name = "a323")
	private BigDecimal a323;
	
	/** The a 311. */
	@Column(name = "a311")
	private BigDecimal a311;

	
	
	/**
	 * Instantiates a new catmun.
	 *
	 * @param clvmun the clvmun
	 * @param nommun the nommun
	 * @param c323 the c 323
	 * @param c311 the c 311
	 * @param a323 the a 323
	 * @param a311 the a 311
	 */
	public Catmun(Integer clvmun, String nommun, BigDecimal c323, BigDecimal c311, BigDecimal a323, BigDecimal a311) {
		this.clvmun = clvmun;
		this.nommun = nommun;
		this.c323 = c323;
		this.c311 = c311;
		this.a323 = a323;
		this.a311 = a311;
	}
	
	/**
	 * Instantiates a new catmun.
	 */
	public Catmun(){}
	
	/**
	 * Instantiates a new catmun.
	 *
	 * @param record the record
	 */
	public Catmun(CSVRecord record){
		this.clvmun = record.get(0).isEmpty()?0:new Integer(record.get(0));;
		this.nommun = record.get(1);
	}

	/**
	 * Gets the clvmun.
	 *
	 * @return the clvmun
	 */
	public Integer getClvmun() {
		return clvmun;
	}

	/**
	 * Sets the clvmun.
	 *
	 * @param clvmun the new clvmun
	 */
	public void setClvmun(Integer clvmun) {
		this.clvmun = clvmun;
	}

	/**
	 * Gets the nommun.
	 *
	 * @return the nommun
	 */
	public String getNommun() {
		return nommun;
	}

	/**
	 * Sets the nommun.
	 *
	 * @param nommun the new nommun
	 */
	public void setNommun(String nommun) {
		this.nommun = nommun;
	}

	/**
	 * Gets the c323.
	 *
	 * @return the c323
	 */
	public BigDecimal getC323() {
		return c323;
	}

	/**
	 * Sets the c323.
	 *
	 * @param c323 the new c323
	 */
	public void setC323(BigDecimal c323) {
		this.c323 = c323;
	}

	/**
	 * Gets the c311.
	 *
	 * @return the c311
	 */
	public BigDecimal getC311() {
		return c311;
	}

	/**
	 * Sets the c311.
	 *
	 * @param c311 the new c311
	 */
	public void setC311(BigDecimal c311) {
		this.c311 = c311;
	}

	/**
	 * Gets the a323.
	 *
	 * @return the a323
	 */
	public BigDecimal getA323() {
		return a323;
	}

	/**
	 * Sets the a323.
	 *
	 * @param a323 the new a323
	 */
	public void setA323(BigDecimal a323) {
		this.a323 = a323;
	}

	/**
	 * Gets the a311.
	 *
	 * @return the a311
	 */
	public BigDecimal getA311() {
		return a311;
	}

	/**
	 * Sets the a311.
	 *
	 * @param a311 the new a311
	 */
	public void setA311(BigDecimal a311) {
		this.a311 = a311;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		return "Catmun [clvmun=" + clvmun + ", nommun=" + nommun + "]";
	}
	
}
