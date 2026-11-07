package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PROGRAMAMUN database table.
 *
 */
@Entity
@NamedQuery(name="Programamun.findAll", query="SELECT p FROM Programamun p")
public class Programamun extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;



	/** The cvedepa. */
	private String cvedepa;

	/** The cvedepg. */
	private String cvedepg;

	/** The descrip. */
	private String descrip;

	/** The fecini. */
	@Temporal(TemporalType.DATE)
	private Date fecini;

	/** The fecter. */
	@Temporal(TemporalType.DATE)
	private Date fecter;

	/** The ffin. */
	private String ffin;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The locben. */
	private String locben;

	/** The observa 1. */
	@Column(name="OBSERVA_1")
	private String observa1;

	/** The observa 10. */
	@Column(name="OBSERVA_10")
	private String observa10;

	/** The observa 11. */
	@Column(name="OBSERVA_11")
	private String observa11;

	/** The observa 12. */
	@Column(name="OBSERVA_12")
	private String observa12;

	/** The observa 2. */
	@Column(name="OBSERVA_2")
	private String observa2;

	/** The observa 3. */
	@Column(name="OBSERVA_3")
	private String observa3;

	/** The observa 4. */
	@Column(name="OBSERVA_4")
	private String observa4;

	/** The observa 5. */
	@Column(name="OBSERVA_5")
	private String observa5;

	/** The observa 6. */
	@Column(name="OBSERVA_6")
	private String observa6;

	/** The observa 7. */
	@Column(name="OBSERVA_7")
	private String observa7;

	/** The observa 8. */
	@Column(name="OBSERVA_8")
	private String observa8;

	/** The observa 9. */
	@Column(name="OBSERVA_9")
	private String observa9;

	/** The pobben. */
	private Integer pobben;

	/** The programa. */
	private String programa;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new programamun.
	 */
	public Programamun() {
	}


	/**
	 * Gets the cvedepa.
	 *
	 * @return the cvedepa
	 */
	public String getCvedepa() {
		return this.cvedepa;
	}

	/**
	 * Sets the cvedepa.
	 *
	 * @param cvedepa the new cvedepa
	 */
	public void setCvedepa(String cvedepa) {
		this.cvedepa = cvedepa;
	}

	/**
	 * Gets the cvedepg.
	 *
	 * @return the cvedepg
	 */
	public String getCvedepg() {
		return this.cvedepg;
	}

	/**
	 * Sets the cvedepg.
	 *
	 * @param cvedepg the new cvedepg
	 */
	public void setCvedepg(String cvedepg) {
		this.cvedepg = cvedepg;
	}

	/**
	 * Gets the descrip.
	 *
	 * @return the descrip
	 */
	public String getDescrip() {
		return this.descrip;
	}

	/**
	 * Sets the descrip.
	 *
	 * @param descrip the new descrip
	 */
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	/**
	 * Gets the fecini.
	 *
	 * @return the fecini
	 */
	public Date getFecini() {
		return this.fecini;
	}

	/**
	 * Sets the fecini.
	 *
	 * @param fecini the new fecini
	 */
	public void setFecini(Date fecini) {
		this.fecini = fecini;
	}

	/**
	 * Gets the fecter.
	 *
	 * @return the fecter
	 */
	public Date getFecter() {
		return this.fecter;
	}

	/**
	 * Sets the fecter.
	 *
	 * @param fecter the new fecter
	 */
	public void setFecter(Date fecter) {
		this.fecter = fecter;
	}

	/**
	 * Gets the ffin.
	 *
	 * @return the ffin
	 */
	public String getFfin() {
		return this.ffin;
	}

	/**
	 * Sets the ffin.
	 *
	 * @param ffin the new ffin
	 */
	public void setFfin(String ffin) {
		this.ffin = ffin;
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
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public Integer getIdsector() {
		return this.idsector;
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
	 * Gets the locben.
	 *
	 * @return the locben
	 */
	public String getLocben() {
		return this.locben;
	}

	/**
	 * Sets the locben.
	 *
	 * @param locben the new locben
	 */
	public void setLocben(String locben) {
		this.locben = locben;
	}

	/**
	 * Gets the observa 1.
	 *
	 * @return the observa 1
	 */
	public String getObserva1() {
		return this.observa1;
	}

	/**
	 * Sets the observa 1.
	 *
	 * @param observa1 the new observa 1
	 */
	public void setObserva1(String observa1) {
		this.observa1 = observa1;
	}

	/**
	 * Gets the observa 10.
	 *
	 * @return the observa 10
	 */
	public String getObserva10() {
		return this.observa10;
	}

	/**
	 * Sets the observa 10.
	 *
	 * @param observa10 the new observa 10
	 */
	public void setObserva10(String observa10) {
		this.observa10 = observa10;
	}

	/**
	 * Gets the observa 11.
	 *
	 * @return the observa 11
	 */
	public String getObserva11() {
		return this.observa11;
	}

	/**
	 * Sets the observa 11.
	 *
	 * @param observa11 the new observa 11
	 */
	public void setObserva11(String observa11) {
		this.observa11 = observa11;
	}

	/**
	 * Gets the observa 12.
	 *
	 * @return the observa 12
	 */
	public String getObserva12() {
		return this.observa12;
	}

	/**
	 * Sets the observa 12.
	 *
	 * @param observa12 the new observa 12
	 */
	public void setObserva12(String observa12) {
		this.observa12 = observa12;
	}

	/**
	 * Gets the observa 2.
	 *
	 * @return the observa 2
	 */
	public String getObserva2() {
		return this.observa2;
	}

	/**
	 * Sets the observa 2.
	 *
	 * @param observa2 the new observa 2
	 */
	public void setObserva2(String observa2) {
		this.observa2 = observa2;
	}

	/**
	 * Gets the observa 3.
	 *
	 * @return the observa 3
	 */
	public String getObserva3() {
		return this.observa3;
	}

	/**
	 * Sets the observa 3.
	 *
	 * @param observa3 the new observa 3
	 */
	public void setObserva3(String observa3) {
		this.observa3 = observa3;
	}

	/**
	 * Gets the observa 4.
	 *
	 * @return the observa 4
	 */
	public String getObserva4() {
		return this.observa4;
	}

	/**
	 * Sets the observa 4.
	 *
	 * @param observa4 the new observa 4
	 */
	public void setObserva4(String observa4) {
		this.observa4 = observa4;
	}

	/**
	 * Gets the observa 5.
	 *
	 * @return the observa 5
	 */
	public String getObserva5() {
		return this.observa5;
	}

	/**
	 * Sets the observa 5.
	 *
	 * @param observa5 the new observa 5
	 */
	public void setObserva5(String observa5) {
		this.observa5 = observa5;
	}

	/**
	 * Gets the observa 6.
	 *
	 * @return the observa 6
	 */
	public String getObserva6() {
		return this.observa6;
	}

	/**
	 * Sets the observa 6.
	 *
	 * @param observa6 the new observa 6
	 */
	public void setObserva6(String observa6) {
		this.observa6 = observa6;
	}

	/**
	 * Gets the observa 7.
	 *
	 * @return the observa 7
	 */
	public String getObserva7() {
		return this.observa7;
	}

	/**
	 * Sets the observa 7.
	 *
	 * @param observa7 the new observa 7
	 */
	public void setObserva7(String observa7) {
		this.observa7 = observa7;
	}

	/**
	 * Gets the observa 8.
	 *
	 * @return the observa 8
	 */
	public String getObserva8() {
		return this.observa8;
	}

	/**
	 * Sets the observa 8.
	 *
	 * @param observa8 the new observa 8
	 */
	public void setObserva8(String observa8) {
		this.observa8 = observa8;
	}

	/**
	 * Gets the observa 9.
	 *
	 * @return the observa 9
	 */
	public String getObserva9() {
		return this.observa9;
	}

	/**
	 * Sets the observa 9.
	 *
	 * @param observa9 the new observa 9
	 */
	public void setObserva9(String observa9) {
		this.observa9 = observa9;
	}

	/**
	 * Gets the pobben.
	 *
	 * @return the pobben
	 */
	public Integer getPobben() {
		return this.pobben;
	}

	/**
	 * Sets the pobben.
	 *
	 * @param pobben the new pobben
	 */
	public void setPobben(Integer pobben) {
		this.pobben = pobben;
	}

	/**
	 * Gets the programa.
	 *
	 * @return the programa
	 */
	public String getPrograma() {
		return this.programa;
	}

	/**
	 * Sets the programa.
	 *
	 * @param programa the new programa
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
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
