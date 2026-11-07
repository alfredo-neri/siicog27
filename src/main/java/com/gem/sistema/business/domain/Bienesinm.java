package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bienesinm.
 */
@Entity
public class Bienesinm implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6841141788853718110L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID\"")
	private Long id;

	/** The campo 1. */
	private BigDecimal campo1;

	/** The campo 2. */
	private String campo2;

	/** The campo 3. */
	private String campo3;

	/** The capturo. */
	private String capturo;

	/** The conpol. */
	private Integer conpol;

	/** The consec. */
	private Integer consec;

	/** The cuenta. */
	private String cuenta;

	/** The cvecatas. */
	private String cvecatas;

	/** The depreacum. */
	private BigDecimal depreacum;

	/** The depreperiodo. */
	private BigDecimal depreperiodo;

	/** The escritura. */
	private String escritura;

	/** The fecadq. */
	@Temporal(TemporalType.DATE)
	private Date fecadq;

	/** The fecalta. */
	@Temporal(TemporalType.DATE)
	private Date fecalta;

	/** The fecbaja. */
	@Temporal(TemporalType.DATE)
	private Date fecbaja;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The fecpol. */
	@Temporal(TemporalType.DATE)
	private Date fecpol;

	/** The id ref. */
	@Column(name = "ID_REF")
	private long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The juridica. */
	private String juridica;

	/** The localidad. */
	private String localidad;

	/** The mes. */
	private Integer mes;

	/** The modadqui. */
	private String modadqui;

	/** The nnorte. */
	private String nnorte;

	/** The nomcta. */
	private String nomcta;

	/** The nommueble. */
	private String nommueble;

	/** The noriente. */
	private String noriente;

	/** The norte. */
	private BigDecimal norte;

	/** The nponiente. */
	private String nponiente;

	/** The nsur. */
	private String nsur;

	/** The obs. */
	private String obs;

	/** The oriente. */
	private BigDecimal oriente;

	/** The poniente. */
	private BigDecimal poniente;

	/** The registro. */
	private String registro;

	/** The scta. */
	private String scta;

	/** The sscta. */
	private String sscta;

	/** The ssscta. */
	private String ssscta;

	/** The sssscta. */
	private String sssscta;

	/** The supconst. */
	private BigDecimal supconst;

	/** The superficie. */
	private BigDecimal superficie;

	/** The sur. */
	private BigDecimal sur;

	/** The tippol. */
	private String tippol;

	/** The tvidautil. */
	private String tvidautil;

	/** The ubicacion. */
	private String ubicacion;

	/** The userid. */
	@Column(name = "\"USERID\"")
	private String userid;

	/** The uso. */
	private String uso;

	/** The valorcatas. */
	private BigDecimal valorcatas;

	/** The valorinm. */
	private BigDecimal valorinm;

	/** The zona. */
	private String zona;

	@Temporal(TemporalType.DATE)
	private Date feccontrato;
	
	private BigDecimal porcentaje;
	
	private String documento;
	/**
	 * Instantiates a new bienesinm.
	 */
	public Bienesinm() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the campo 1.
	 *
	 * @return the campo 1
	 */
	public BigDecimal getCampo1() {
		return this.campo1;
	}

	/**
	 * Sets the campo 1.
	 *
	 * @param campo1 the new campo 1
	 */
	public void setCampo1(BigDecimal campo1) {
		this.campo1 = campo1;
	}

	/**
	 * Gets the campo 2.
	 *
	 * @return the campo 2
	 */
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
	 * Gets the conpol.
	 *
	 * @return the conpol
	 */
	public Integer getConpol() {
		return this.conpol;
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
	 * Gets the consec.
	 *
	 * @return the consec
	 */
	public Integer getConsec() {
		return this.consec;
	}

	/**
	 * Sets the consec.
	 *
	 * @param consec the new consec
	 */
	public void setConsec(Integer consec) {
		this.consec = consec;
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
	 * Gets the cvecatas.
	 *
	 * @return the cvecatas
	 */
	public String getCvecatas() {
		return this.cvecatas;
	}

	/**
	 * Sets the cvecatas.
	 *
	 * @param cvecatas the new cvecatas
	 */
	public void setCvecatas(String cvecatas) {
		this.cvecatas = cvecatas;
	}

	/**
	 * Gets the depreacum.
	 *
	 * @return the depreacum
	 */
	public BigDecimal getDepreacum() {
		return this.depreacum;
	}

	/**
	 * Sets the depreacum.
	 *
	 * @param depreacum the new depreacum
	 */
	public void setDepreacum(BigDecimal depreacum) {
		this.depreacum = depreacum;
	}

	/**
	 * Gets the depreperiodo.
	 *
	 * @return the depreperiodo
	 */
	public BigDecimal getDepreperiodo() {
		return this.depreperiodo;
	}

	/**
	 * Sets the depreperiodo.
	 *
	 * @param depreperiodo the new depreperiodo
	 */
	public void setDepreperiodo(BigDecimal depreperiodo) {
		this.depreperiodo = depreperiodo;
	}

	/**
	 * Gets the escritura.
	 *
	 * @return the escritura
	 */
	public String getEscritura() {
		return this.escritura;
	}

	/**
	 * Sets the escritura.
	 *
	 * @param escritura the new escritura
	 */
	public void setEscritura(String escritura) {
		this.escritura = escritura;
	}

	/**
	 * Gets the fecadq.
	 *
	 * @return the fecadq
	 */
	public Date getFecadq() {
		return this.fecadq;
	}

	/**
	 * Sets the fecadq.
	 *
	 * @param fecadq the new fecadq
	 */
	public void setFecadq(Date fecadq) {
		this.fecadq = fecadq;
	}

	/**
	 * Gets the fecalta.
	 *
	 * @return the fecalta
	 */
	public Date getFecalta() {
		return this.fecalta;
	}

	/**
	 * Sets the fecalta.
	 *
	 * @param fecalta the new fecalta
	 */
	public void setFecalta(Date fecalta) {
		this.fecalta = fecalta;
	}

	/**
	 * Gets the fecbaja.
	 *
	 * @return the fecbaja
	 */
	public Date getFecbaja() {
		return this.fecbaja;
	}

	/**
	 * Sets the fecbaja.
	 *
	 * @param fecbaja the new fecbaja
	 */
	public void setFecbaja(Date fecbaja) {
		this.fecbaja = fecbaja;
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
	 * Gets the fecpol.
	 *
	 * @return the fecpol
	 */
	public Date getFecpol() {
		return this.fecpol;
	}

	/**
	 * Sets the fecpol.
	 *
	 * @param fecpol the new fecpol
	 */
	public void setFecpol(Date fecpol) {
		this.fecpol = fecpol;
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
	 * Gets the juridica.
	 *
	 * @return the juridica
	 */
	public String getJuridica() {
		return this.juridica;
	}

	/**
	 * Sets the juridica.
	 *
	 * @param juridica the new juridica
	 */
	public void setJuridica(String juridica) {
		this.juridica = juridica;
	}

	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return this.localidad;
	}

	/**
	 * Sets the localidad.
	 *
	 * @param localidad the new localidad
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return this.mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Gets the modadqui.
	 *
	 * @return the modadqui
	 */
	public String getModadqui() {
		return this.modadqui;
	}

	/**
	 * Sets the modadqui.
	 *
	 * @param modadqui the new modadqui
	 */
	public void setModadqui(String modadqui) {
		this.modadqui = modadqui;
	}

	/**
	 * Gets the nnorte.
	 *
	 * @return the nnorte
	 */
	public String getNnorte() {
		return this.nnorte;
	}

	/**
	 * Sets the nnorte.
	 *
	 * @param nnorte the new nnorte
	 */
	public void setNnorte(String nnorte) {
		this.nnorte = nnorte;
	}

	/**
	 * Gets the nomcta.
	 *
	 * @return the nomcta
	 */
	public String getNomcta() {
		return this.nomcta;
	}

	/**
	 * Sets the nomcta.
	 *
	 * @param nomcta the new nomcta
	 */
	public void setNomcta(String nomcta) {
		this.nomcta = nomcta;
	}

	/**
	 * Gets the nommueble.
	 *
	 * @return the nommueble
	 */
	public String getNommueble() {
		return this.nommueble;
	}

	/**
	 * Sets the nommueble.
	 *
	 * @param nommueble the new nommueble
	 */
	public void setNommueble(String nommueble) {
		this.nommueble = nommueble;
	}

	/**
	 * Gets the noriente.
	 *
	 * @return the noriente
	 */
	public String getNoriente() {
		return this.noriente;
	}

	/**
	 * Sets the noriente.
	 *
	 * @param noriente the new noriente
	 */
	public void setNoriente(String noriente) {
		this.noriente = noriente;
	}

	/**
	 * Gets the norte.
	 *
	 * @return the norte
	 */
	public BigDecimal getNorte() {
		return this.norte;
	}

	/**
	 * Sets the norte.
	 *
	 * @param norte the new norte
	 */
	public void setNorte(BigDecimal norte) {
		this.norte = norte;
	}

	/**
	 * Gets the nponiente.
	 *
	 * @return the nponiente
	 */
	public String getNponiente() {
		return this.nponiente;
	}

	/**
	 * Sets the nponiente.
	 *
	 * @param nponiente the new nponiente
	 */
	public void setNponiente(String nponiente) {
		this.nponiente = nponiente;
	}

	/**
	 * Gets the nsur.
	 *
	 * @return the nsur
	 */
	public String getNsur() {
		return this.nsur;
	}

	/**
	 * Sets the nsur.
	 *
	 * @param nsur the new nsur
	 */
	public void setNsur(String nsur) {
		this.nsur = nsur;
	}

	/**
	 * Gets the obs.
	 *
	 * @return the obs
	 */
	public String getObs() {
		return this.obs;
	}

	/**
	 * Sets the obs.
	 *
	 * @param obs the new obs
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}

	/**
	 * Gets the oriente.
	 *
	 * @return the oriente
	 */
	public BigDecimal getOriente() {
		return this.oriente;
	}

	/**
	 * Sets the oriente.
	 *
	 * @param oriente the new oriente
	 */
	public void setOriente(BigDecimal oriente) {
		this.oriente = oriente;
	}

	/**
	 * Gets the poniente.
	 *
	 * @return the poniente
	 */
	public BigDecimal getPoniente() {
		return this.poniente;
	}

	/**
	 * Sets the poniente.
	 *
	 * @param poniente the new poniente
	 */
	public void setPoniente(BigDecimal poniente) {
		this.poniente = poniente;
	}

	/**
	 * Gets the registro.
	 *
	 * @return the registro
	 */
	public String getRegistro() {
		return this.registro;
	}

	/**
	 * Sets the registro.
	 *
	 * @param registro the new registro
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
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
	 * Gets the supconst.
	 *
	 * @return the supconst
	 */
	public BigDecimal getSupconst() {
		return this.supconst;
	}

	/**
	 * Sets the supconst.
	 *
	 * @param supconst the new supconst
	 */
	public void setSupconst(BigDecimal supconst) {
		this.supconst = supconst;
	}

	/**
	 * Gets the superficie.
	 *
	 * @return the superficie
	 */
	public BigDecimal getSuperficie() {
		return this.superficie;
	}

	/**
	 * Sets the superficie.
	 *
	 * @param superficie the new superficie
	 */
	public void setSuperficie(BigDecimal superficie) {
		this.superficie = superficie;
	}

	/**
	 * Gets the sur.
	 *
	 * @return the sur
	 */
	public BigDecimal getSur() {
		return this.sur;
	}

	/**
	 * Sets the sur.
	 *
	 * @param sur the new sur
	 */
	public void setSur(BigDecimal sur) {
		this.sur = sur;
	}

	/**
	 * Gets the tippol.
	 *
	 * @return the tippol
	 */
	public String getTippol() {
		return this.tippol;
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
	 * Gets the tvidautil.
	 *
	 * @return the tvidautil
	 */
	public String getTvidautil() {
		return this.tvidautil;
	}

	/**
	 * Sets the tvidautil.
	 *
	 * @param tvidautil the new tvidautil
	 */
	public void setTvidautil(String tvidautil) {
		this.tvidautil = tvidautil;
	}

	/**
	 * Gets the ubicacion.
	 *
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return this.ubicacion;
	}

	/**
	 * Sets the ubicacion.
	 *
	 * @param ubicacion the new ubicacion
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
	 * Gets the uso.
	 *
	 * @return the uso
	 */
	public String getUso() {
		return this.uso;
	}

	/**
	 * Sets the uso.
	 *
	 * @param uso the new uso
	 */
	public void setUso(String uso) {
		this.uso = uso;
	}

	/**
	 * Gets the valorcatas.
	 *
	 * @return the valorcatas
	 */
	public BigDecimal getValorcatas() {
		return this.valorcatas;
	}

	/**
	 * Sets the valorcatas.
	 *
	 * @param valorcatas the new valorcatas
	 */
	public void setValorcatas(BigDecimal valorcatas) {
		this.valorcatas = valorcatas;
	}

	/**
	 * Gets the valorinm.
	 *
	 * @return the valorinm
	 */
	public BigDecimal getValorinm() {
		return this.valorinm;
	}

	/**
	 * Sets the valorinm.
	 *
	 * @param valorinm the new valorinm
	 */
	public void setValorinm(BigDecimal valorinm) {
		this.valorinm = valorinm;
	}

	/**
	 * Gets the zona.
	 *
	 * @return the zona
	 */
	public String getZona() {
		return this.zona;
	}

	/**
	 * Sets the zona.
	 *
	 * @param zona the new zona
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	public Date getFeccontrato() {
		return feccontrato;
	}

	public void setFeccontrato(Date feccontrato) {
		this.feccontrato = feccontrato;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Bienesinm other = (Bienesinm) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bienesinm [id=" + id + ", campo1=" + campo1 + ", campo2=" + campo2 + ", campo3=" + campo3 + ", capturo="
				+ capturo + ", conpol=" + conpol + ", consec=" + consec + ", cuenta=" + cuenta + ", cvecatas="
				+ cvecatas + ", depreacum=" + depreacum + ", depreperiodo=" + depreperiodo + ", escritura=" + escritura
				+ ", fecadq=" + fecadq + ", fecalta=" + fecalta + ", fecbaja=" + fecbaja + ", feccap=" + feccap
				+ ", fecpol=" + fecpol + ", idRef=" + idRef + ", idsector=" + idsector + ", juridica=" + juridica
				+ ", localidad=" + localidad + ", mes=" + mes + ", modadqui=" + modadqui + ", nnorte=" + nnorte
				+ ", nomcta=" + nomcta + ", nommueble=" + nommueble + ", noriente=" + noriente + ", norte=" + norte
				+ ", nponiente=" + nponiente + ", nsur=" + nsur + ", obs=" + obs + ", oriente=" + oriente
				+ ", poniente=" + poniente + ", registro=" + registro + ", scta=" + scta + ", sscta=" + sscta
				+ ", ssscta=" + ssscta + ", sssscta=" + sssscta + ", supconst=" + supconst + ", superficie="
				+ superficie + ", sur=" + sur + ", tippol=" + tippol + ", tvidautil=" + tvidautil + ", ubicacion="
				+ ubicacion + ", userid=" + userid + ", uso=" + uso + ", valorcatas=" + valorcatas + ", valorinm="
				+ valorinm + ", zona=" + zona + "]";
	}

	
}