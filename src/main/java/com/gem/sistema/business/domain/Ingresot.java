package com.gem.sistema.business.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the INGRESOT database table.
 *
 */
@Entity@NamedStoredProcedureQueries({

  @NamedStoredProcedureQuery(name = "acumula_ingresot",
                             procedureName="SP_ACUMULA_INGRESOT",
                             parameters = {
                               @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_SECTOR", type = Integer.class),
                               @StoredProcedureParameter(mode = ParameterMode.IN, name = "USER_ID", type = String.class)
                             })
})
public class Ingresot extends AbstractEntity {

	/** The auto. */
	private BigDecimal auto;

	/** The autoi 1. */
	@Column(name = "AUTOI_1")
	private BigDecimal autoi1;

	/** The autoi 2. */
	@Column(name = "AUTOI_2")
	private BigDecimal autoi2;

	/** The autoi 3. */
	@Column(name = "AUTOI_3")
	private BigDecimal autoi3;

	/** The autoi 4. */
	@Column(name = "AUTOI_4")
	private BigDecimal autoi4;

	/** The autoi 5. */
	@Column(name = "AUTOI_5")
	private BigDecimal autoi5;

	/** The cuenta. */
	private String cuenta;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The nombre. */
	private String nombre;

	/** The scta. */
	private String scta;

	/** The sectorid. */
	private Integer sectorid;

	/** The sscta. */
	private String sscta;

	/** The ssscta. */
	private String ssscta;

	/** The sssscta. */
	private String sssscta;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new ingresot.
	 */
	public Ingresot() {
	}

	/**
	 * Gets the auto.
	 *
	 * @return the auto
	 */
	public BigDecimal getAuto() {
		return this.auto;
	}

	/**
	 * Sets the auto.
	 *
	 * @param auto the new auto
	 */
	public void setAuto(BigDecimal auto) {
		this.auto = auto;
	}

	/**
	 * Gets the autoi 1.
	 *
	 * @return the autoi 1
	 */
	public BigDecimal getAutoi1() {
		return this.autoi1;
	}

	/**
	 * Sets the autoi 1.
	 *
	 * @param autoi1 the new autoi 1
	 */
	public void setAutoi1(BigDecimal autoi1) {
		this.autoi1 = autoi1;
	}

	/**
	 * Gets the autoi 2.
	 *
	 * @return the autoi 2
	 */
	public BigDecimal getAutoi2() {
		return this.autoi2;
	}

	/**
	 * Sets the autoi 2.
	 *
	 * @param autoi2 the new autoi 2
	 */
	public void setAutoi2(BigDecimal autoi2) {
		this.autoi2 = autoi2;
	}

	/**
	 * Gets the autoi 3.
	 *
	 * @return the autoi 3
	 */
	public BigDecimal getAutoi3() {
		return this.autoi3;
	}

	/**
	 * Sets the autoi 3.
	 *
	 * @param autoi3 the new autoi 3
	 */
	public void setAutoi3(BigDecimal autoi3) {
		this.autoi3 = autoi3;
	}

	/**
	 * Gets the autoi 4.
	 *
	 * @return the autoi 4
	 */
	public BigDecimal getAutoi4() {
		return this.autoi4;
	}

	/**
	 * Sets the autoi 4.
	 *
	 * @param autoi4 the new autoi 4
	 */
	public void setAutoi4(BigDecimal autoi4) {
		this.autoi4 = autoi4;
	}

	/**
	 * Gets the autoi 5.
	 *
	 * @return the autoi 5
	 */
	public BigDecimal getAutoi5() {
		return this.autoi5;
	}

	/**
	 * Sets the autoi 5.
	 *
	 * @param autoi5 the new autoi 5
	 */
	public void setAutoi5(BigDecimal autoi5) {
		this.autoi5 = autoi5;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return this.cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return this.idRef;
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
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the scta.
	 *
	 * @return the scta
	 */
	public String getScta() {
		return this.scta;
	}

	/**
	 * Sets the scta.
	 *
	 * @param scta the new scta
	 */
	public void setScta(String scta) {
		this.scta = scta;
	}

	/**
	 * Gets the sectorid.
	 *
	 * @return the sectorid
	 */
	public Integer getSectorid() {
		return this.sectorid;
	}

	/**
	 * Sets the sectorid.
	 *
	 * @param sectorid the new sectorid
	 */
	public void setSectorid(Integer sectorid) {
		this.sectorid = sectorid;
	}

	/**
	 * Gets the sscta.
	 *
	 * @return the sscta
	 */
	public String getSscta() {
		return this.sscta;
	}

	/**
	 * Sets the sscta.
	 *
	 * @param sscta the new sscta
	 */
	public void setSscta(String sscta) {
		this.sscta = sscta;
	}

	/**
	 * Gets the ssscta.
	 *
	 * @return the ssscta
	 */
	public String getSsscta() {
		return this.ssscta;
	}

	/**
	 * Sets the ssscta.
	 *
	 * @param ssscta the new ssscta
	 */
	public void setSsscta(String ssscta) {
		this.ssscta = ssscta;
	}

	/**
	 * Gets the sssscta.
	 *
	 * @return the sssscta
	 */
	public String getSssscta() {
		return this.sssscta;
	}

	/**
	 * Sets the sssscta.
	 *
	 * @param sssscta the new sssscta
	 */
	public void setSssscta(String sssscta) {
		this.sssscta = sssscta;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return this.userid;
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
