package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM3911 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm3911.findAll", query="SELECT p FROM Pm3911 p")
public class Pm3911 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The manorgno. */
	private String manorgno;

	/** The manorgpro. */
	private String manorgpro;

	/** The manorgsi. */
	private String manorgsi;

	/** The manorgvig. */
	private String manorgvig;

	/** The manprono. */
	private String manprono;

	/** The manpropro. */
	private String manpropro;

	/** The manprosi. */
	private String manprosi;

	/** The manprovig. */
	private String manprovig;

	/** The observaciones. */
	private String observaciones;

	/** The obsmanorg. */
	private String obsmanorg;

	/** The obsmanpro. */
	private String obsmanpro;

	/** The obsorg. */
	private String obsorg;

	/** The obsreg. */
	private String obsreg;

	/** The orgno. */
	private String orgno;

	/** The orgpro. */
	private String orgpro;

	/** The orgsi. */
	private String orgsi;

	/** The orgvig. */
	private String orgvig;

	/** The perfinman. */
	@Temporal(TemporalType.DATE)
	private Date perfinman;

	/** The perfinmo. */
	@Temporal(TemporalType.DATE)
	private Date perfinmo;

	/** The perfino. */
	@Temporal(TemporalType.DATE)
	private Date perfino;

	/** The perfinreg. */
	@Temporal(TemporalType.DATE)
	private Date perfinreg;

	/** The periniman. */
	@Temporal(TemporalType.DATE)
	private Date periniman;

	/** The perinimo. */
	@Temporal(TemporalType.DATE)
	private Date perinimo;

	/** The perinio. */
	@Temporal(TemporalType.DATE)
	private Date perinio;

	/** The preinireg. */
	@Temporal(TemporalType.DATE)
	private Date preinireg;

	/** The regno. */
	private String regno;

	/** The regpro. */
	private String regpro;

	/** The regsi. */
	private String regsi;

	/** The regvig. */
	private String regvig;

	/** The semes. */
	private Integer semes;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 3911.
	 */
	public Pm3911() {
	}

	/**
	 * Gets the capturo.
	 *
	 * @return the capturo
	 */
	public String getCapturo() {
		return this.capturo;
	}

	/**
	 * Sets the capturo.
	 *
	 * @param capturo the new capturo
	 */
	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	/**
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	public Date getFeccap() {
		return this.feccap;
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
	public int getIdsector() {
		return this.idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(int idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the manorgno.
	 *
	 * @return the manorgno
	 */
	public String getManorgno() {
		return this.manorgno;
	}

	/**
	 * Sets the manorgno.
	 *
	 * @param manorgno the new manorgno
	 */
	public void setManorgno(String manorgno) {
		this.manorgno = manorgno;
	}

	/**
	 * Gets the manorgpro.
	 *
	 * @return the manorgpro
	 */
	public String getManorgpro() {
		return this.manorgpro;
	}

	/**
	 * Sets the manorgpro.
	 *
	 * @param manorgpro the new manorgpro
	 */
	public void setManorgpro(String manorgpro) {
		this.manorgpro = manorgpro;
	}

	/**
	 * Gets the manorgsi.
	 *
	 * @return the manorgsi
	 */
	public String getManorgsi() {
		return this.manorgsi;
	}

	/**
	 * Sets the manorgsi.
	 *
	 * @param manorgsi the new manorgsi
	 */
	public void setManorgsi(String manorgsi) {
		this.manorgsi = manorgsi;
	}

	/**
	 * Gets the manorgvig.
	 *
	 * @return the manorgvig
	 */
	public String getManorgvig() {
		return this.manorgvig;
	}

	/**
	 * Sets the manorgvig.
	 *
	 * @param manorgvig the new manorgvig
	 */
	public void setManorgvig(String manorgvig) {
		this.manorgvig = manorgvig;
	}

	/**
	 * Gets the manprono.
	 *
	 * @return the manprono
	 */
	public String getManprono() {
		return this.manprono;
	}

	/**
	 * Sets the manprono.
	 *
	 * @param manprono the new manprono
	 */
	public void setManprono(String manprono) {
		this.manprono = manprono;
	}

	/**
	 * Gets the manpropro.
	 *
	 * @return the manpropro
	 */
	public String getManpropro() {
		return this.manpropro;
	}

	/**
	 * Sets the manpropro.
	 *
	 * @param manpropro the new manpropro
	 */
	public void setManpropro(String manpropro) {
		this.manpropro = manpropro;
	}

	/**
	 * Gets the manprosi.
	 *
	 * @return the manprosi
	 */
	public String getManprosi() {
		return this.manprosi;
	}

	/**
	 * Sets the manprosi.
	 *
	 * @param manprosi the new manprosi
	 */
	public void setManprosi(String manprosi) {
		this.manprosi = manprosi;
	}

	/**
	 * Gets the manprovig.
	 *
	 * @return the manprovig
	 */
	public String getManprovig() {
		return this.manprovig;
	}

	/**
	 * Sets the manprovig.
	 *
	 * @param manprovig the new manprovig
	 */
	public void setManprovig(String manprovig) {
		this.manprovig = manprovig;
	}

	/**
	 * Gets the observaciones.
	 *
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * Sets the observaciones.
	 *
	 * @param observaciones the new observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Gets the obsmanorg.
	 *
	 * @return the obsmanorg
	 */
	public String getObsmanorg() {
		return this.obsmanorg;
	}

	/**
	 * Sets the obsmanorg.
	 *
	 * @param obsmanorg the new obsmanorg
	 */
	public void setObsmanorg(String obsmanorg) {
		this.obsmanorg = obsmanorg;
	}

	/**
	 * Gets the obsmanpro.
	 *
	 * @return the obsmanpro
	 */
	public String getObsmanpro() {
		return this.obsmanpro;
	}

	/**
	 * Sets the obsmanpro.
	 *
	 * @param obsmanpro the new obsmanpro
	 */
	public void setObsmanpro(String obsmanpro) {
		this.obsmanpro = obsmanpro;
	}

	/**
	 * Gets the obsorg.
	 *
	 * @return the obsorg
	 */
	public String getObsorg() {
		return this.obsorg;
	}

	/**
	 * Sets the obsorg.
	 *
	 * @param obsorg the new obsorg
	 */
	public void setObsorg(String obsorg) {
		this.obsorg = obsorg;
	}

	/**
	 * Gets the obsreg.
	 *
	 * @return the obsreg
	 */
	public String getObsreg() {
		return this.obsreg;
	}

	/**
	 * Sets the obsreg.
	 *
	 * @param obsreg the new obsreg
	 */
	public void setObsreg(String obsreg) {
		this.obsreg = obsreg;
	}

	/**
	 * Gets the orgno.
	 *
	 * @return the orgno
	 */
	public String getOrgno() {
		return this.orgno;
	}

	/**
	 * Sets the orgno.
	 *
	 * @param orgno the new orgno
	 */
	public void setOrgno(String orgno) {
		this.orgno = orgno;
	}

	/**
	 * Gets the orgpro.
	 *
	 * @return the orgpro
	 */
	public String getOrgpro() {
		return this.orgpro;
	}

	/**
	 * Sets the orgpro.
	 *
	 * @param orgpro the new orgpro
	 */
	public void setOrgpro(String orgpro) {
		this.orgpro = orgpro;
	}

	/**
	 * Gets the orgsi.
	 *
	 * @return the orgsi
	 */
	public String getOrgsi() {
		return this.orgsi;
	}

	/**
	 * Sets the orgsi.
	 *
	 * @param orgsi the new orgsi
	 */
	public void setOrgsi(String orgsi) {
		this.orgsi = orgsi;
	}

	/**
	 * Gets the orgvig.
	 *
	 * @return the orgvig
	 */
	public String getOrgvig() {
		return this.orgvig;
	}

	/**
	 * Sets the orgvig.
	 *
	 * @param orgvig the new orgvig
	 */
	public void setOrgvig(String orgvig) {
		this.orgvig = orgvig;
	}

	/**
	 * Gets the perfinman.
	 *
	 * @return the perfinman
	 */
	public Date getPerfinman() {
		return this.perfinman;
	}

	/**
	 * Sets the perfinman.
	 *
	 * @param perfinman the new perfinman
	 */
	public void setPerfinman(Date perfinman) {
		this.perfinman = perfinman;
	}

	/**
	 * Gets the perfinmo.
	 *
	 * @return the perfinmo
	 */
	public Date getPerfinmo() {
		return this.perfinmo;
	}

	/**
	 * Sets the perfinmo.
	 *
	 * @param perfinmo the new perfinmo
	 */
	public void setPerfinmo(Date perfinmo) {
		this.perfinmo = perfinmo;
	}

	/**
	 * Gets the perfino.
	 *
	 * @return the perfino
	 */
	public Date getPerfino() {
		return this.perfino;
	}

	/**
	 * Sets the perfino.
	 *
	 * @param perfino the new perfino
	 */
	public void setPerfino(Date perfino) {
		this.perfino = perfino;
	}

	/**
	 * Gets the perfinreg.
	 *
	 * @return the perfinreg
	 */
	public Date getPerfinreg() {
		return this.perfinreg;
	}

	/**
	 * Sets the perfinreg.
	 *
	 * @param perfinreg the new perfinreg
	 */
	public void setPerfinreg(Date perfinreg) {
		this.perfinreg = perfinreg;
	}

	/**
	 * Gets the periniman.
	 *
	 * @return the periniman
	 */
	public Date getPeriniman() {
		return this.periniman;
	}

	/**
	 * Sets the periniman.
	 *
	 * @param periniman the new periniman
	 */
	public void setPeriniman(Date periniman) {
		this.periniman = periniman;
	}

	/**
	 * Gets the perinimo.
	 *
	 * @return the perinimo
	 */
	public Date getPerinimo() {
		return this.perinimo;
	}

	/**
	 * Sets the perinimo.
	 *
	 * @param perinimo the new perinimo
	 */
	public void setPerinimo(Date perinimo) {
		this.perinimo = perinimo;
	}

	/**
	 * Gets the perinio.
	 *
	 * @return the perinio
	 */
	public Date getPerinio() {
		return this.perinio;
	}

	/**
	 * Sets the perinio.
	 *
	 * @param perinio the new perinio
	 */
	public void setPerinio(Date perinio) {
		this.perinio = perinio;
	}

	/**
	 * Gets the preinireg.
	 *
	 * @return the preinireg
	 */
	public Date getPreinireg() {
		return this.preinireg;
	}

	/**
	 * Sets the preinireg.
	 *
	 * @param preinireg the new preinireg
	 */
	public void setPreinireg(Date preinireg) {
		this.preinireg = preinireg;
	}

	/**
	 * Gets the regno.
	 *
	 * @return the regno
	 */
	public String getRegno() {
		return this.regno;
	}

	/**
	 * Sets the regno.
	 *
	 * @param regno the new regno
	 */
	public void setRegno(String regno) {
		this.regno = regno;
	}

	/**
	 * Gets the regpro.
	 *
	 * @return the regpro
	 */
	public String getRegpro() {
		return this.regpro;
	}

	/**
	 * Sets the regpro.
	 *
	 * @param regpro the new regpro
	 */
	public void setRegpro(String regpro) {
		this.regpro = regpro;
	}

	/**
	 * Gets the regsi.
	 *
	 * @return the regsi
	 */
	public String getRegsi() {
		return this.regsi;
	}

	/**
	 * Sets the regsi.
	 *
	 * @param regsi the new regsi
	 */
	public void setRegsi(String regsi) {
		this.regsi = regsi;
	}

	/**
	 * Gets the regvig.
	 *
	 * @return the regvig
	 */
	public String getRegvig() {
		return this.regvig;
	}

	/**
	 * Sets the regvig.
	 *
	 * @param regvig the new regvig
	 */
	public void setRegvig(String regvig) {
		this.regvig = regvig;
	}

	/**
	 * Gets the semes.
	 *
	 * @return the semes
	 */
	public Integer getSemes() {
		return this.semes;
	}

	/**
	 * Sets the semes.
	 *
	 * @param semes the new semes
	 */
	public void setSemes(Integer semes) {
		this.semes = semes;
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

	/**
	 * Checks if is b guardar.
	 *
	 * @return true, if is b guardar
	 */
	public boolean isbGuardar() {
		return bGuardar;
	}

	/**
	 * Sets the b guardar.
	 *
	 * @param bGuardar the new b guardar
	 */
	public void setbGuardar(boolean bGuardar) {
		this.bGuardar = bGuardar;
	}
	
	

}