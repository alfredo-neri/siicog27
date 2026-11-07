package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the CONCTB database table.
 * 
 */
@Entity
@NamedQuery(name = "Conctb.findAll", query = "SELECT c FROM Conctb c")
public class Conctb extends AbstractEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The anoemp. */
	@Column(name = "ANOEMP")
	private Integer anoemp;

	/** The clave. */
	private String clave;

	/** The clave 1. */
	private String clave1;

	/** The clvemp. */
	private String clvemp;

	/** The id ref. */
	@Column(name = "ID_REF")
	private long idRef;

	/** The idsector. */
	@Column(name = "IDSECTOR")
	private Integer idsector;

	/** The mescie. */
	private Integer mescie;

	/** The mesemp. */
	private Integer mesemp;

	/** The nivel 1. */
	private Integer nivel1;

	/** The nivel 2. */
	private Integer nivel2;

	/** The nivel 3. */
	private Integer nivel3;

	/** The nivel 4. */
	private Integer nivel4;

	/** The nivel 5. */
	private Integer nivel5;

	/** The nivel 6. */
	private Integer nivel6;

	/** The numdet. */
	private BigDecimal numdet;

	/** The numvac. */
	private Integer numvac;

	/** The ordpol. */
	private BigDecimal ordpol;

	private String rfc;

	@Column(name = "NOM_DEP")
	private String nomDep;

	@Column(name = "IMAGE_PATH_RIGTH")
	private String imagePathRigth;

	@Column(name = "IMAGE_PATH_LEFT")
	private String imagePathLeft;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new conctb.
	 */
	public Conctb() {
	}

	/**
	 * Gets the anoemp.
	 *
	 * @return the anoemp
	 */
	public Integer puestosFirmasService() {
		return this.anoemp;
	}

	/**
	 * Sets the anoemp.
	 *
	 * @param anoemp the new anoemp
	 */
	public void setAnoemp(Integer anoemp) {
		this.anoemp = anoemp;
	}

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return this.clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the clave 1.
	 *
	 * @return the clave 1
	 */
	public String getClave1() {
		return this.clave1;
	}

	/**
	 * Sets the clave 1.
	 *
	 * @param clave1 the new clave 1
	 */
	public void setClave1(String clave1) {
		this.clave1 = clave1;
	}

	/**
	 * Gets the clvemp.
	 *
	 * @return the clvemp
	 */
	public String getClvemp() {
		return this.clvemp;
	}

	/**
	 * Sets the clvemp.
	 *
	 * @param clvemp the new clvemp
	 */
	public void setClvemp(String clvemp) {
		this.clvemp = clvemp;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public long getIdRef() {
		return this.idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(long idRef) {
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
	 * Gets the mescie.
	 *
	 * @return the mescie
	 */
	public Integer getMescie() {
		return this.mescie;
	}

	/**
	 * Sets the mescie.
	 *
	 * @param mescie the new mescie
	 */
	public void setMescie(Integer mescie) {
		this.mescie = mescie;
	}

	/**
	 * Gets the mesemp.
	 *
	 * @return the mesemp
	 */
	public Integer getMesemp() {
		return this.mesemp;
	}

	/**
	 * Sets the mesemp.
	 *
	 * @param mesemp the new mesemp
	 */
	public void setMesemp(Integer mesemp) {
		this.mesemp = mesemp;
	}

	/**
	 * Gets the nivel 1.
	 *
	 * @return the nivel 1
	 */
	public Integer getNivel1() {
		return this.nivel1;
	}

	/**
	 * Sets the nivel 1.
	 *
	 * @param nivel1 the new nivel 1
	 */
	public void setNivel1(Integer nivel1) {
		this.nivel1 = nivel1;
	}

	/**
	 * Gets the nivel 2.
	 *
	 * @return the nivel 2
	 */
	public Integer getNivel2() {
		return this.nivel2;
	}

	/**
	 * Sets the nivel 2.
	 *
	 * @param nivel2 the new nivel 2
	 */
	public void setNivel2(Integer nivel2) {
		this.nivel2 = nivel2;
	}

	/**
	 * Gets the nivel 3.
	 *
	 * @return the nivel 3
	 */
	public Integer getNivel3() {
		return this.nivel3;
	}

	/**
	 * Sets the nivel 3.
	 *
	 * @param nivel3 the new nivel 3
	 */
	public void setNivel3(Integer nivel3) {
		this.nivel3 = nivel3;
	}

	/**
	 * Gets the nivel 4.
	 *
	 * @return the nivel 4
	 */
	public Integer getNivel4() {
		return this.nivel4;
	}

	/**
	 * Sets the nivel 4.
	 *
	 * @param nivel4 the new nivel 4
	 */
	public void setNivel4(Integer nivel4) {
		this.nivel4 = nivel4;
	}

	/**
	 * Gets the nivel 5.
	 *
	 * @return the nivel 5
	 */
	public Integer getNivel5() {
		return this.nivel5;
	}

	/**
	 * Sets the nivel 5.
	 *
	 * @param nivel5 the new nivel 5
	 */
	public void setNivel5(Integer nivel5) {
		this.nivel5 = nivel5;
	}

	/**
	 * Gets the nivel 6.
	 *
	 * @return the nivel 6
	 */
	public Integer getNivel6() {
		return this.nivel6;
	}

	/**
	 * Sets the nivel 6.
	 *
	 * @param nivel6 the new nivel 6
	 */
	public void setNivel6(Integer nivel6) {
		this.nivel6 = nivel6;
	}

	/**
	 * Gets the numdet.
	 *
	 * @return the numdet
	 */
	public BigDecimal getNumdet() {
		return this.numdet;
	}

	/**
	 * Sets the numdet.
	 *
	 * @param numdet the new numdet
	 */
	public void setNumdet(BigDecimal numdet) {
		this.numdet = numdet;
	}

	/**
	 * Gets the numvac.
	 *
	 * @return the numvac
	 */
	public Integer getNumvac() {
		return this.numvac;
	}

	/**
	 * Sets the numvac.
	 *
	 * @param numvac the new numvac
	 */
	public void setNumvac(Integer numvac) {
		this.numvac = numvac;
	}

	/**
	 * Gets the ordpol.
	 *
	 * @return the ordpol
	 */
	public BigDecimal getOrdpol() {
		return this.ordpol;
	}

	/**
	 * Sets the ordpol.
	 *
	 * @param ordpol the new ordpol
	 */
	public void setOrdpol(BigDecimal ordpol) {
		this.ordpol = ordpol;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNomDep() {
		return nomDep;
	}

	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
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

	public String getImagePathRigth() {
		return imagePathRigth;
	}

	public void setImagePathRigth(String imagePathRigth) {
		this.imagePathRigth = imagePathRigth;
	}

	public String getImagePathLeft() {
		return imagePathLeft;
	}

	public void setImagePathLeft(String imagePathLeft) {
		this.imagePathLeft = imagePathLeft;
	}

	public Integer getAnoemp() {
		return anoemp;
	}

}