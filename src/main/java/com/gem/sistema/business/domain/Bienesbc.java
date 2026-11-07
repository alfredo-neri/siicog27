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
 * The Class Bienesbc.
 */
@Entity
public class Bienesbc implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8458515011206406045L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The area. */
	private String area;

	/** The campo 1. */
	private Integer campo1;

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

	/** The costo. */
	private BigDecimal costo;

	/** The cuenta. */
	private String cuenta;

	/** The depreacum. */
	private BigDecimal depreacum;

	/** The depreperiodo. */
	private BigDecimal depreperiodo;

	/** The estado. */
	private String estado;

	/** The factura. */
	private String factura;

	/** The fecalta. */
	@Temporal(TemporalType.DATE)
	private Date fecalta;

	/** The fecbaja. */
	@Temporal(TemporalType.DATE)
	private Date fecbaja;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The fecfactura. */
	@Temporal(TemporalType.DATE)
	private Date fecfactura;

	/** The fecpol. */
	@Temporal(TemporalType.DATE)
	private Date fecpol;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The inventario. */
	private String inventario;

	/** The localidad. */
	private String localidad;

	/** The marca. */
	private String marca;

	/** The mes. */
	private Integer mes;

	/** The modelo. */
	private String modelo;

	/** The nomcta. */
	private String nomcta;

	/** The nommueble. */
	private String nommueble;

	/** The obs. */
	private String obs;

	/** The proveedor. */
	private String proveedor;

	/** The recurso. */
	private String recurso;

	/** The resguardatario. */
	private String resguardatario;

	/** The resguardo. */
	private String resguardo;

	/** The scta. */
	private String scta;

	/** The serie. */
	private String serie;

	/** The sscta. */
	private String sscta;

	/** The ssscta. */
	private String ssscta;

	/** The sssscta. */
	private String sssscta;

	/** The tippol. */
	private String tippol;

	/** The tvidautil. */
	private String tvidautil;

	/** The userid. */
	@Column(name="\"USERID\"")
	private String userid;

	/**
	 * Instantiates a new bienesbc.
	 */
	public Bienesbc() {
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
	 * Gets the area.
	 *
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * Sets the area.
	 *
	 * @param area the new area
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * Gets the campo 1.
	 *
	 * @return the campo 1
	 */
	public Integer getCampo1() {
		return campo1;
	}

	/**
	 * Sets the campo 1.
	 *
	 * @param campo1 the new campo 1
	 */
	public void setCampo1(Integer campo1) {
		this.campo1 = campo1;
	}

	/**
	 * Gets the campo 2.
	 *
	 * @return the campo 2
	 */
	public String getCampo2() {
		return campo2;
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
		return campo3;
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
		return capturo;
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
		return conpol;
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
		return consec;
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
	 * Gets the costo.
	 *
	 * @return the costo
	 */
	public BigDecimal getCosto() {
		return costo;
	}

	/**
	 * Sets the costo.
	 *
	 * @param costo the new costo
	 */
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
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
	 * Gets the depreacum.
	 *
	 * @return the depreacum
	 */
	public BigDecimal getDepreacum() {
		return depreacum;
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
		return depreperiodo;
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
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the factura.
	 *
	 * @return the factura
	 */
	public String getFactura() {
		return factura;
	}

	/**
	 * Sets the factura.
	 *
	 * @param factura the new factura
	 */
	public void setFactura(String factura) {
		this.factura = factura;
	}

	/**
	 * Gets the fecalta.
	 *
	 * @return the fecalta
	 */
	public Date getFecalta() {
		return fecalta;
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
		return fecbaja;
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
		return feccap;
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
	 * Gets the fecfactura.
	 *
	 * @return the fecfactura
	 */
	public Date getFecfactura() {
		return fecfactura;
	}

	/**
	 * Sets the fecfactura.
	 *
	 * @param fecfactura the new fecfactura
	 */
	public void setFecfactura(Date fecfactura) {
		this.fecfactura = fecfactura;
	}

	/**
	 * Gets the fecpol.
	 *
	 * @return the fecpol
	 */
	public Date getFecpol() {
		return fecpol;
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
	 * Gets the inventario.
	 *
	 * @return the inventario
	 */
	public String getInventario() {
		return inventario;
	}

	/**
	 * Sets the inventario.
	 *
	 * @param inventario the new inventario
	 */
	public void setInventario(String inventario) {
		this.inventario = inventario;
	}

	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
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
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
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
	 * Gets the modelo.
	 *
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Sets the modelo.
	 *
	 * @param modelo the new modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * Gets the nomcta.
	 *
	 * @return the nomcta
	 */
	public String getNomcta() {
		return nomcta;
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
		return nommueble;
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
	 * Gets the obs.
	 *
	 * @return the obs
	 */
	public String getObs() {
		return obs;
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
	 * Gets the proveedor.
	 *
	 * @return the proveedor
	 */
	public String getProveedor() {
		return proveedor;
	}

	/**
	 * Sets the proveedor.
	 *
	 * @param proveedor the new proveedor
	 */
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * Gets the recurso.
	 *
	 * @return the recurso
	 */
	public String getRecurso() {
		return recurso;
	}

	/**
	 * Sets the recurso.
	 *
	 * @param recurso the new recurso
	 */
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	/**
	 * Gets the resguardatario.
	 *
	 * @return the resguardatario
	 */
	public String getResguardatario() {
		return resguardatario;
	}

	/**
	 * Sets the resguardatario.
	 *
	 * @param resguardatario the new resguardatario
	 */
	public void setResguardatario(String resguardatario) {
		this.resguardatario = resguardatario;
	}

	/**
	 * Gets the resguardo.
	 *
	 * @return the resguardo
	 */
	public String getResguardo() {
		return resguardo;
	}

	/**
	 * Sets the resguardo.
	 *
	 * @param resguardo the new resguardo
	 */
	public void setResguardo(String resguardo) {
		this.resguardo = resguardo;
	}

	/**
	 * Gets the scta.
	 *
	 * @return the scta
	 */
	public String getScta() {
		return scta;
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
	 * Gets the serie.
	 *
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * Sets the serie.
	 *
	 * @param serie the new serie
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * Gets the sscta.
	 *
	 * @return the sscta
	 */
	public String getSscta() {
		return sscta;
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
		return ssscta;
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
		return sssscta;
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
	 * Gets the tippol.
	 *
	 * @return the tippol
	 */
	public String getTippol() {
		return tippol;
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
		return tvidautil;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Bienesbc other = (Bienesbc) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}