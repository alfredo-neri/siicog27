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
 * The Class Predial.
 */
@Entity
public class Predial implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4432109489151088038L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	/** The campo 1. */
	private String campo1;

	/** The campo 2. */
	private Integer campo2;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The gastosejec. */
	private BigDecimal gastosejec;

	/** The gastosejeca. */
	private BigDecimal gastosejeca;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The impuestos. */
	private BigDecimal impuestos;

	/** The impuestosa. */
	private BigDecimal impuestosa;

	/** The indemni. */
	private BigDecimal indemni;

	/** The indemnia. */
	private BigDecimal indemnia;

	/** The mes. */
	private String mes;

	/** The multas. */
	private BigDecimal multas;

	/** The multasa. */
	private BigDecimal multasa;

	/** The nctasanterior. */
	private Integer nctasanterior;

	/** The nctasdmes. */
	private Integer nctasdmes;

	/** The otros. */
	private BigDecimal otros;

	/** The otrosa. */
	private BigDecimal otrosa;

	/** The racumaa. */
	private BigDecimal racumaa;

	/** The ralmes. */
	private BigDecimal ralmes;

	/** The rdeaa. */
	private BigDecimal rdeaa;

	/** The rdelmes. */
	private BigDecimal rdelmes;

	/** The recargos. */
	private BigDecimal recargos;

	/** The recargosa. */
	private BigDecimal recargosa;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/** The usuario. */
	private String usuario;

	/**
	 * Instantiates a new predial.
	 */
	public Predial() {
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
	public String getCampo1() {
		return campo1;
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
	public Integer getCampo2() {
		return campo2;
	}

	/**
	 * Sets the campo 2.
	 *
	 * @param campo2 the new campo 2
	 */
	public void setCampo2(Integer campo2) {
		this.campo2 = campo2;
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
	 * Gets the gastosejec.
	 *
	 * @return the gastosejec
	 */
	public BigDecimal getGastosejec() {
		return gastosejec;
	}

	/**
	 * Sets the gastosejec.
	 *
	 * @param gastosejec the new gastosejec
	 */
	public void setGastosejec(BigDecimal gastosejec) {
		this.gastosejec = gastosejec;
	}

	/**
	 * Gets the gastosejeca.
	 *
	 * @return the gastosejeca
	 */
	public BigDecimal getGastosejeca() {
		return gastosejeca;
	}

	/**
	 * Sets the gastosejeca.
	 *
	 * @param gastosejeca the new gastosejeca
	 */
	public void setGastosejeca(BigDecimal gastosejeca) {
		this.gastosejeca = gastosejeca;
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
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public BigDecimal getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos the new impuestos
	 */
	public void setImpuestos(BigDecimal impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the impuestosa.
	 *
	 * @return the impuestosa
	 */
	public BigDecimal getImpuestosa() {
		return impuestosa;
	}

	/**
	 * Sets the impuestosa.
	 *
	 * @param impuestosa the new impuestosa
	 */
	public void setImpuestosa(BigDecimal impuestosa) {
		this.impuestosa = impuestosa;
	}

	/**
	 * Gets the indemni.
	 *
	 * @return the indemni
	 */
	public BigDecimal getIndemni() {
		return indemni;
	}

	/**
	 * Sets the indemni.
	 *
	 * @param indemni the new indemni
	 */
	public void setIndemni(BigDecimal indemni) {
		this.indemni = indemni;
	}

	/**
	 * Gets the indemnia.
	 *
	 * @return the indemnia
	 */
	public BigDecimal getIndemnia() {
		return indemnia;
	}

	/**
	 * Sets the indemnia.
	 *
	 * @param indemnia the new indemnia
	 */
	public void setIndemnia(BigDecimal indemnia) {
		this.indemnia = indemnia;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the multas.
	 *
	 * @return the multas
	 */
	public BigDecimal getMultas() {
		return multas;
	}

	/**
	 * Sets the multas.
	 *
	 * @param multas the new multas
	 */
	public void setMultas(BigDecimal multas) {
		this.multas = multas;
	}

	/**
	 * Gets the multasa.
	 *
	 * @return the multasa
	 */
	public BigDecimal getMultasa() {
		return multasa;
	}

	/**
	 * Sets the multasa.
	 *
	 * @param multasa the new multasa
	 */
	public void setMultasa(BigDecimal multasa) {
		this.multasa = multasa;
	}

	/**
	 * Gets the nctasanterior.
	 *
	 * @return the nctasanterior
	 */
	public Integer getNctasanterior() {
		return nctasanterior;
	}

	/**
	 * Sets the nctasanterior.
	 *
	 * @param nctasanterior the new nctasanterior
	 */
	public void setNctasanterior(Integer nctasanterior) {
		this.nctasanterior = nctasanterior;
	}

	/**
	 * Gets the nctasdmes.
	 *
	 * @return the nctasdmes
	 */
	public Integer getNctasdmes() {
		return nctasdmes;
	}

	/**
	 * Sets the nctasdmes.
	 *
	 * @param nctasdmes the new nctasdmes
	 */
	public void setNctasdmes(Integer nctasdmes) {
		this.nctasdmes = nctasdmes;
	}

	/**
	 * Gets the otros.
	 *
	 * @return the otros
	 */
	public BigDecimal getOtros() {
		return otros;
	}

	/**
	 * Sets the otros.
	 *
	 * @param otros the new otros
	 */
	public void setOtros(BigDecimal otros) {
		this.otros = otros;
	}

	/**
	 * Gets the otrosa.
	 *
	 * @return the otrosa
	 */
	public BigDecimal getOtrosa() {
		return otrosa;
	}

	/**
	 * Sets the otrosa.
	 *
	 * @param otrosa the new otrosa
	 */
	public void setOtrosa(BigDecimal otrosa) {
		this.otrosa = otrosa;
	}

	/**
	 * Gets the racumaa.
	 *
	 * @return the racumaa
	 */
	public BigDecimal getRacumaa() {
		return racumaa;
	}

	/**
	 * Sets the racumaa.
	 *
	 * @param racumaa the new racumaa
	 */
	public void setRacumaa(BigDecimal racumaa) {
		this.racumaa = racumaa;
	}

	/**
	 * Gets the ralmes.
	 *
	 * @return the ralmes
	 */
	public BigDecimal getRalmes() {
		return ralmes;
	}

	/**
	 * Sets the ralmes.
	 *
	 * @param ralmes the new ralmes
	 */
	public void setRalmes(BigDecimal ralmes) {
		this.ralmes = ralmes;
	}

	/**
	 * Gets the rdeaa.
	 *
	 * @return the rdeaa
	 */
	public BigDecimal getRdeaa() {
		return rdeaa;
	}

	/**
	 * Sets the rdeaa.
	 *
	 * @param rdeaa the new rdeaa
	 */
	public void setRdeaa(BigDecimal rdeaa) {
		this.rdeaa = rdeaa;
	}

	/**
	 * Gets the rdelmes.
	 *
	 * @return the rdelmes
	 */
	public BigDecimal getRdelmes() {
		return rdelmes;
	}

	/**
	 * Sets the rdelmes.
	 *
	 * @param rdelmes the new rdelmes
	 */
	public void setRdelmes(BigDecimal rdelmes) {
		this.rdelmes = rdelmes;
	}

	/**
	 * Gets the recargos.
	 *
	 * @return the recargos
	 */
	public BigDecimal getRecargos() {
		return recargos;
	}

	/**
	 * Sets the recargos.
	 *
	 * @param recargos the new recargos
	 */
	public void setRecargos(BigDecimal recargos) {
		this.recargos = recargos;
	}

	/**
	 * Gets the recargosa.
	 *
	 * @return the recargosa
	 */
	public BigDecimal getRecargosa() {
		return recargosa;
	}

	/**
	 * Sets the recargosa.
	 *
	 * @param recargosa the new recargosa
	 */
	public void setRecargosa(BigDecimal recargosa) {
		this.recargosa = recargosa;
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
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
		Predial other = (Predial) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Predial [id=" + id + ", campo1=" + campo1 + ", campo2=" + campo2 + ", feccap=" + feccap
				+ ", gastosejec=" + gastosejec + ", gastosejeca=" + gastosejeca + ", idRef=" + idRef + ", idsector="
				+ idsector + ", impuestos=" + impuestos + ", impuestosa=" + impuestosa + ", indemni=" + indemni
				+ ", indemnia=" + indemnia + ", mes=" + mes + ", multas=" + multas + ", multasa=" + multasa
				+ ", nctasanterior=" + nctasanterior + ", nctasdmes=" + nctasdmes + ", otros=" + otros + ", otrosa="
				+ otrosa + ", racumaa=" + racumaa + ", ralmes=" + ralmes + ", rdeaa=" + rdeaa + ", rdelmes=" + rdelmes
				+ ", recargos=" + recargos + ", recargosa=" + recargosa + ", userid=" + userid + ", usuario=" + usuario
				+ ", getId()=" + getId() + ", getCampo1()=" + getCampo1() + ", getCampo2()=" + getCampo2()
				+ ", getFeccap()=" + getFeccap() + ", getGastosejec()=" + getGastosejec() + ", getGastosejeca()="
				+ getGastosejeca() + ", getIdRef()=" + getIdRef() + ", getIdsector()=" + getIdsector()
				+ ", getImpuestos()=" + getImpuestos() + ", getImpuestosa()=" + getImpuestosa() + ", getIndemni()="
				+ getIndemni() + ", getIndemnia()=" + getIndemnia() + ", getMes()=" + getMes() + ", getMultas()="
				+ getMultas() + ", getMultasa()=" + getMultasa() + ", getNctasanterior()=" + getNctasanterior()
				+ ", getNctasdmes()=" + getNctasdmes() + ", getOtros()=" + getOtros() + ", getOtrosa()=" + getOtrosa()
				+ ", getRacumaa()=" + getRacumaa() + ", getRalmes()=" + getRalmes() + ", getRdeaa()=" + getRdeaa()
				+ ", getRdelmes()=" + getRdelmes() + ", getRecargos()=" + getRecargos() + ", getRecargosa()="
				+ getRecargosa() + ", getUserid()=" + getUserid() + ", getUsuario()=" + getUsuario() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}

	
}