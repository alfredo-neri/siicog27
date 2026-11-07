package com.gem.sistema.business.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the MUNINEP database table.
 * 
 */
@Entity
@NamedQuery(name="Muninep.findAll", query="SELECT m FROM Muninep m")
public class Muninep extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	@IgnoredQuery
	private static final long serialVersionUID = 1L;

	/** The campo 0. */
	@Column(name="CAMPO0")
	private String campo0;

	/**
	 * Gets the campo 0.
	 *
	 * @return the campo 0
	 */
	@ColumnFile(indexColumn=1)
	public String getCampo0() {
		return campo0;
	}

	/** The campo 1. */
	@Column(name="CAMPO1")
	private String campo1;

	/** The campo 2. */
	@Column(name="CAMPO2")
	private String campo2;

	/** The campo 3. */
	@Column(name="CAMPO3")
	private String campo3;

	/** The campo 4. */
	@Column(name="CAMPO4")
	private String campo4;

	/** The campo 5. */
	@Column(name="CAMPO5")
	private String campo5;

	/** The campo 6. */
	@Column(name="CAMPO6")
	private String campo6;

	/** The campo 7. */
	@Column(name="CAMPO7")
	private String campo7;

	/** The campo 8. */
	@Column(name="CAMPO8")
	private String campo8;

	/** The campo 9. */
	@Column(name="CAMPO9")
	private String campo9;
	
	/** The idsector. */
	@Column(name="IDSECTOR")
	private Integer idsector;
	
	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;
	
	/** The editable. */
	@IgnoredQuery
	@Transient
	private Boolean editable;

	/**
	 * Instantiates a new muninep.
	 */
	public Muninep() {
	}

	/**
	 * Sets the campo 0.
	 *
	 * @param campo0 the new campo 0
	 */
	public void setCampo0(String campo0) {
		this.campo0 = campo0;
	}

	/**
	 * Gets the campo 1.
	 *
	 * @return the campo 1
	 */
	@ColumnFile(indexColumn=2)
	public String getCampo1() {
		return this.campo1;
	}

	/**
	 * Sets the campo 1.
	 *
	 * @param campo1 the new campo 1
	 */
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	/**
	 * Gets the campo 2.
	 *
	 * @return the campo 2
	 */
	@ColumnFile(indexColumn=3)
	public String getCampo2() {
		return this.campo2;
	}

	/**
	 * Sets the campo 2.
	 *
	 * @param campo2 the new campo 2
	 */
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	/**
	 * Gets the campo 3.
	 *
	 * @return the campo 3
	 */
	@ColumnFile(indexColumn=4)
	public String getCampo3() {
		return this.campo3;
	}
	
	/**
	 * Sets the campo 3.
	 *
	 * @param campo3 the new campo 3
	 */
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	/**
	 * Gets the campo 4.
	 *
	 * @return the campo 4
	 */
	@ColumnFile(indexColumn=5)
	public String getCampo4() {
		return this.campo4;
	}

	/**
	 * Sets the campo 4.
	 *
	 * @param campo4 the new campo 4
	 */
	public void setCampo4(String campo4) {
		this.campo4 = campo4;
	}

	/**
	 * Gets the campo 5.
	 *
	 * @return the campo 5
	 */
	@ColumnFile(indexColumn=6)
	public String getCampo5() {
		return this.campo5;
	}

	/**
	 * Sets the campo 5.
	 *
	 * @param campo5 the new campo 5
	 */
	public void setCampo5(String campo5) {
		this.campo5 = campo5;
	}
	
	/**
	 * Gets the campo 6.
	 *
	 * @return the campo 6
	 */
	@ColumnFile(indexColumn=7)
	public String getCampo6() {
		return this.campo6;
	}

	/**
	 * Sets the campo 6.
	 *
	 * @param campo6 the new campo 6
	 */
	public void setCampo6(String campo6) {
		this.campo6 = campo6;
	}

	/**
	 * Gets the campo 7.
	 *
	 * @return the campo 7
	 */
	@ColumnFile(indexColumn=8)
	public String getCampo7() {
		return this.campo7;
	}

	/**
	 * Sets the campo 7.
	 *
	 * @param campo7 the new campo 7
	 */
	public void setCampo7(String campo7) {
		this.campo7 = campo7;
	}

	
	/**
	 * Gets the campo 8.
	 *
	 * @return the campo 8
	 */
	public String getCampo8() {
		return this.campo8;
	}

	/**
	 * Sets the campo 8.
	 *
	 * @param campo8 the new campo 8
	 */
	public void setCampo8(String campo8) {
		this.campo8 = campo8;
	}

	/**
	 * Gets the campo 9.
	 *
	 * @return the campo 9
	 */
	public String getCampo9() {
		return this.campo9;
	}

	/**
	 * Sets the campo 9.
	 *
	 * @param campo9 the new campo 9
	 */
	public void setCampo9(String campo9) {
		this.campo9 = campo9;
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
	 * Gets the editable.
	 *
	 * @return the editable
	 */
	public Boolean getEditable() {
		return this.userid == null || this.userid.equals("");
	}

//	public String getVisibility() {
//		return this.userid == null || this.userid.equals("") ? "visible" : "hidden";
//	}

	/* (non-Javadoc)
 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
 */
@Override
	public String toString() {
		return "Muninep [campo0=" + campo0 + ", campo1=" + campo1 + ", campo2=" + campo2 + ", campo3=" + campo3
				+ ", campo4=" + campo4 + ", campo5=" + campo5 + ", campo6=" + campo6 + ", campo7=" + campo7
				+ ", campo8=" + campo8 + ", campo9=" + campo9 + ", idsector=" + idsector + ", userid=" + userid
				+ ", idRef=" + idRef + "]";
	}
}