package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM5811 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm5811.findAll", query="SELECT p FROM Pm5811 p")
public class Pm5811 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The docu. */
	private String docu;

	/** The espest. */
	private String espest;

	/** The estsup. */
	private String estsup;

	/** The experi. */
	private String experi;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The fecing. */
	@Temporal(TemporalType.DATE)
	private Date fecing;

	/** The grado. */
	private String grado;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The nombre. */
	private String nombre;

	/** The pubfecfin. */
	@Temporal(TemporalType.DATE)
	private Date pubfecfin;

	/** The pubfecini. */
	@Temporal(TemporalType.DATE)
	private Date pubfecini;

	/** The publugar. */
	private String publugar;

	/** The pubpuesto. */
	private String pubpuesto;

	/** The secpub. */
	private String secpub;

	/** The semes. */
	private Integer semes;

	/** The ultfecfin. */
	@Temporal(TemporalType.DATE)
	private Date ultfecfin;

	/** The ultfecini. */
	@Temporal(TemporalType.DATE)
	private Date ultfecini;

	/** The ultlugar. */
	private String ultlugar;

	/** The ultpuesto. */
	private String ultpuesto;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;
	

	/**
	 * Instantiates a new pm 5811.
	 */
	public Pm5811() {
	}

	/**
	 * Gets the docu.
	 *
	 * @return the docu
	 */
	public String getDocu() {
		return this.docu;
	}

	/**
	 * Sets the docu.
	 *
	 * @param docu the new docu
	 */
	public void setDocu(String docu) {
		this.docu = docu;
	}

	/**
	 * Gets the espest.
	 *
	 * @return the espest
	 */
	public String getEspest() {
		return this.espest;
	}

	/**
	 * Sets the espest.
	 *
	 * @param espest the new espest
	 */
	public void setEspest(String espest) {
		this.espest = espest;
	}

	/**
	 * Gets the estsup.
	 *
	 * @return the estsup
	 */
	public String getEstsup() {
		return this.estsup;
	}

	/**
	 * Sets the estsup.
	 *
	 * @param estsup the new estsup
	 */
	public void setEstsup(String estsup) {
		this.estsup = estsup;
	}

	/**
	 * Gets the experi.
	 *
	 * @return the experi
	 */
	public String getExperi() {
		return this.experi;
	}

	/**
	 * Sets the experi.
	 *
	 * @param experi the new experi
	 */
	public void setExperi(String experi) {
		this.experi = experi;
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
	 * Gets the fecing.
	 *
	 * @return the fecing
	 */
	public Date getFecing() {
		return this.fecing;
	}

	/**
	 * Sets the fecing.
	 *
	 * @param fecing the new fecing
	 */
	public void setFecing(Date fecing) {
		this.fecing = fecing;
	}

	/**
	 * Gets the grado.
	 *
	 * @return the grado
	 */
	public String getGrado() {
		return this.grado;
	}

	/**
	 * Sets the grado.
	 *
	 * @param grado the new grado
	 */
	public void setGrado(String grado) {
		this.grado = grado;
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
	 * Gets the pubfecfin.
	 *
	 * @return the pubfecfin
	 */
	public Date getPubfecfin() {
		return this.pubfecfin;
	}

	/**
	 * Sets the pubfecfin.
	 *
	 * @param pubfecfin the new pubfecfin
	 */
	public void setPubfecfin(Date pubfecfin) {
		this.pubfecfin = pubfecfin;
	}

	/**
	 * Gets the pubfecini.
	 *
	 * @return the pubfecini
	 */
	public Date getPubfecini() {
		return this.pubfecini;
	}

	/**
	 * Sets the pubfecini.
	 *
	 * @param pubfecini the new pubfecini
	 */
	public void setPubfecini(Date pubfecini) {
		this.pubfecini = pubfecini;
	}

	/**
	 * Gets the publugar.
	 *
	 * @return the publugar
	 */
	public String getPublugar() {
		return this.publugar;
	}

	/**
	 * Sets the publugar.
	 *
	 * @param publugar the new publugar
	 */
	public void setPublugar(String publugar) {
		this.publugar = publugar;
	}

	/**
	 * Gets the pubpuesto.
	 *
	 * @return the pubpuesto
	 */
	public String getPubpuesto() {
		return this.pubpuesto;
	}

	/**
	 * Sets the pubpuesto.
	 *
	 * @param pubpuesto the new pubpuesto
	 */
	public void setPubpuesto(String pubpuesto) {
		this.pubpuesto = pubpuesto;
	}

	/**
	 * Gets the secpub.
	 *
	 * @return the secpub
	 */
	public String getSecpub() {
		return this.secpub;
	}

	/**
	 * Sets the secpub.
	 *
	 * @param secpub the new secpub
	 */
	public void setSecpub(String secpub) {
		this.secpub = secpub;
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
	 * Gets the ultfecfin.
	 *
	 * @return the ultfecfin
	 */
	public Date getUltfecfin() {
		return this.ultfecfin;
	}

	/**
	 * Sets the ultfecfin.
	 *
	 * @param ultfecfin the new ultfecfin
	 */
	public void setUltfecfin(Date ultfecfin) {
		this.ultfecfin = ultfecfin;
	}

	/**
	 * Gets the ultfecini.
	 *
	 * @return the ultfecini
	 */
	public Date getUltfecini() {
		return this.ultfecini;
	}

	/**
	 * Sets the ultfecini.
	 *
	 * @param ultfecini the new ultfecini
	 */
	public void setUltfecini(Date ultfecini) {
		this.ultfecini = ultfecini;
	}

	/**
	 * Gets the ultlugar.
	 *
	 * @return the ultlugar
	 */
	public String getUltlugar() {
		return this.ultlugar;
	}

	/**
	 * Sets the ultlugar.
	 *
	 * @param ultlugar the new ultlugar
	 */
	public void setUltlugar(String ultlugar) {
		this.ultlugar = ultlugar;
	}

	/**
	 * Gets the ultpuesto.
	 *
	 * @return the ultpuesto
	 */
	public String getUltpuesto() {
		return this.ultpuesto;
	}

	/**
	 * Sets the ultpuesto.
	 *
	 * @param ultpuesto the new ultpuesto
	 */
	public void setUltpuesto(String ultpuesto) {
		this.ultpuesto = ultpuesto;
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