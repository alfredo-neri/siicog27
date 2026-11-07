package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM3811 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm3811.findAll", query = "SELECT p FROM Pm3811 p")
public class Pm3811 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The areaafin. */
	private String areaafin;

	/** The capturo. */
	private String capturo;

	/** The cert. */
	private String cert = "SI";

	/** The certh. */
	private String certh;

	/** The doc. */
	private String doc;

	/** The edad. */
	private Integer edad = 0;

	/** The espcert. */
	private String espcert;

	/** The espcerth. */
	private String espcerth;

	/** The espest. */
	private String espest;

	/** The estsup. */
	private String  estsup = "SI";

	/** The explab. */
	private String explab;

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
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The nombre. */
	private String nombre;

	/** The publugar. */
	private String publugar;

	/** The pubper. */
	private String pubper;

	/** The pubpuesto. */
	private String pubpuesto;

	/** The secpub. */
	private String secpub = "SI";

	/** The semestral. */
	private Integer semestral;

	/** The sexo. */
	private String sexo;

	/** The titingoarq. */
	private String titingoarq = "SI";

	/** The ultlugar. */
	private String ultlugar;

	/** The ultpuesto. */
	private String ultpuesto;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/** The b guardar. */
	@Transient
	private boolean bGuardar;

	/**
	 * Instantiates a new pm 3811.
	 */
	public Pm3811() {
	}

	/**
	 * Gets the areaafin.
	 *
	 * @return the areaafin
	 */
	public String getAreaafin() {
		return this.areaafin;
	}

	/**
	 * Sets the areaafin.
	 *
	 * @param areaafin the new areaafin
	 */
	public void setAreaafin(String areaafin) {
		this.areaafin = areaafin;
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
	 * Gets the cert.
	 *
	 * @return the cert
	 */
	public String getCert() {
		return this.cert;
	}

	/**
	 * Sets the cert.
	 *
	 * @param cert the new cert
	 */
	public void setCert(String cert) {
		this.cert = cert;
	}

	/**
	 * Gets the certh.
	 *
	 * @return the certh
	 */
	public String getCerth() {
		return this.certh;
	}

	/**
	 * Sets the certh.
	 *
	 * @param certh the new certh
	 */
	public void setCerth(String certh) {
		this.certh = certh;
	}

	/**
	 * Gets the doc.
	 *
	 * @return the doc
	 */
	public String getDoc() {
		return this.doc;
	}

	/**
	 * Sets the doc.
	 *
	 * @param doc the new doc
	 */
	public void setDoc(String doc) {
		this.doc = doc;
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
	 * Gets the espcert.
	 *
	 * @return the espcert
	 */
	public String getEspcert() {
		return this.espcert;
	}

	/**
	 * Sets the espcert.
	 *
	 * @param espcert the new espcert
	 */
	public void setEspcert(String espcert) {
		this.espcert = espcert;
	}

	/**
	 * Gets the espcerth.
	 *
	 * @return the espcerth
	 */
	public String getEspcerth() {
		return this.espcerth;
	}

	/**
	 * Sets the espcerth.
	 *
	 * @param espcerth the new espcerth
	 */
	public void setEspcerth(String espcerth) {
		this.espcerth = espcerth;
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
	 * Gets the explab.
	 *
	 * @return the explab
	 */
	public String getExplab() {
		return this.explab;
	}

	/**
	 * Sets the explab.
	 *
	 * @param explab the new explab
	 */
	public void setExplab(String explab) {
		this.explab = explab;
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
	 * Gets the pubper.
	 *
	 * @return the pubper
	 */
	public String getPubper() {
		return this.pubper;
	}

	/**
	 * Sets the pubper.
	 *
	 * @param pubper the new pubper
	 */
	public void setPubper(String pubper) {
		this.pubper = pubper;
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
	 * Gets the semestral.
	 *
	 * @return the semestral
	 */
	public Integer getSemestral() {
		return this.semestral;
	}

	/**
	 * Sets the semestral.
	 *
	 * @param semestral the new semestral
	 */
	public void setSemestral(Integer semestral) {
		this.semestral = semestral;
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
	 * Gets the titingoarq.
	 *
	 * @return the titingoarq
	 */
	public String getTitingoarq() {
		return this.titingoarq;
	}

	/**
	 * Sets the titingoarq.
	 *
	 * @param titingoarq the new titingoarq
	 */
	public void setTitingoarq(String titingoarq) {
		this.titingoarq = titingoarq;
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