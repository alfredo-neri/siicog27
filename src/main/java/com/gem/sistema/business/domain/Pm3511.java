package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM3511 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm3511.findAll", query="SELECT p FROM Pm3511 p")
public class Pm3511 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The capturo. */
	private String capturo;

	/** The certif. */
	private String certif;

	/** The docu. */
	private String docu;

	/** The edad. */
	private Integer edad;

	/** The especer. */
	private String especer;

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

	/** The fipspublico. */
	@Temporal(TemporalType.DATE)
	private Date fipspublico;

	/** The fiupuesto. */
	@Temporal(TemporalType.DATE)
	private Date fiupuesto;

	/** The ftpspublico. */
	@Temporal(TemporalType.DATE)
	private Date ftpspublico;

	/** The ftupuesto. */
	@Temporal(TemporalType.DATE)
	private Date ftupuesto;

	/** The grado. */
	private String grado;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The nombre. */
	private String nombre;

	/** The publugar. */
	private String publugar;

	/** The pubperiodo. */
	private String pubperiodo;

	/** The pubpuesto. */
	private String pubpuesto;

	/** The secpub. */
	private String secpub;

	/** The semes. */
	private Integer semes;

	/** The sexo. */
	private String sexo;

	/** The ultlugar. */
	private String ultlugar;

	/** The ultperiodo. */
	private String ultperiodo;

	/** The ultpuesto. */
	private String ultpuesto;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;

	/**
	 * Instantiates a new pm 3511.
	 */
	public Pm3511() {
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
	 * Gets the certif.
	 *
	 * @return the certif
	 */
	public String getCertif() {
		return this.certif;
	}

	/**
	 * Sets the certif.
	 *
	 * @param certif the new certif
	 */
	public void setCertif(String certif) {
		this.certif = certif;
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
	 * Gets the edad.
	 *
	 * @return the edad
	 */
	public Integer getEdad() {
		return this.edad;
	}

	/**
	 * Sets the edad.
	 *
	 * @param edad the new edad
	 */
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	/**
	 * Gets the especer.
	 *
	 * @return the especer
	 */
	public String getEspecer() {
		return this.especer;
	}

	/**
	 * Sets the especer.
	 *
	 * @param especer the new especer
	 */
	public void setEspecer(String especer) {
		this.especer = especer;
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
	 * Gets the fipspublico.
	 *
	 * @return the fipspublico
	 */
	public Date getFipspublico() {
		return this.fipspublico;
	}

	/**
	 * Sets the fipspublico.
	 *
	 * @param fipspublico the new fipspublico
	 */
	public void setFipspublico(Date fipspublico) {
		this.fipspublico = fipspublico;
	}

	/**
	 * Gets the fiupuesto.
	 *
	 * @return the fiupuesto
	 */
	public Date getFiupuesto() {
		return this.fiupuesto;
	}

	/**
	 * Sets the fiupuesto.
	 *
	 * @param fiupuesto the new fiupuesto
	 */
	public void setFiupuesto(Date fiupuesto) {
		this.fiupuesto = fiupuesto;
	}

	/**
	 * Gets the ftpspublico.
	 *
	 * @return the ftpspublico
	 */
	public Date getFtpspublico() {
		return this.ftpspublico;
	}

	/**
	 * Sets the ftpspublico.
	 *
	 * @param ftpspublico the new ftpspublico
	 */
	public void setFtpspublico(Date ftpspublico) {
		this.ftpspublico = ftpspublico;
	}

	/**
	 * Gets the ftupuesto.
	 *
	 * @return the ftupuesto
	 */
	public Date getFtupuesto() {
		return this.ftupuesto;
	}

	/**
	 * Sets the ftupuesto.
	 *
	 * @param ftupuesto the new ftupuesto
	 */
	public void setFtupuesto(Date ftupuesto) {
		this.ftupuesto = ftupuesto;
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
	 * Gets the pubperiodo.
	 *
	 * @return the pubperiodo
	 */
	public String getPubperiodo() {
		return this.pubperiodo;
	}

	/**
	 * Sets the pubperiodo.
	 *
	 * @param pubperiodo the new pubperiodo
	 */
	public void setPubperiodo(String pubperiodo) {
		this.pubperiodo = pubperiodo;
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
	 * Gets the sexo.
	 *
	 * @return the sexo
	 */
	public String getSexo() {
		return this.sexo;
	}

	/**
	 * Sets the sexo.
	 *
	 * @param sexo the new sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
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
	 * Gets the ultperiodo.
	 *
	 * @return the ultperiodo
	 */
	public String getUltperiodo() {
		return this.ultperiodo;
	}

	/**
	 * Sets the ultperiodo.
	 *
	 * @param ultperiodo the new ultperiodo
	 */
	public void setUltperiodo(String ultperiodo) {
		this.ultperiodo = ultperiodo;
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