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
 * The Class Agua.
 */
@Entity
public class Agua implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8663185957395345367L;

	/** The id. */
	@Id
	@Column(name = "\"ID\"")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The alcant. */
	private BigDecimal alcant;

	/** The alcanta. */
	private BigDecimal alcanta;

	/** The campo 1. */
	private String campo1;

	/** The campo 2. */
	private Integer campo2;

	/** The conexr. */
	private BigDecimal conexr;

	/** The conexra. */
	private BigDecimal conexra;

	/** The drenaje. */
	private BigDecimal drenaje;

	/** The drenajea. */
	private BigDecimal drenajea;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The gtosejec. */
	private BigDecimal gtosejec;

	/** The gtosejeca. */
	private BigDecimal gtosejeca;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

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

	/** The ntomasmen. */
	private Integer ntomasmen;

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

	/** The sagua. */
	private BigDecimal sagua;

	/** The saguaa. */
	private BigDecimal saguaa;

	/** The tomasantes. */
	private Integer tomasantes;

	/** The userid. */
	@Column(name = "\"USERID\"")
	private String userid;

	/** The usuario. */
	private String usuario;

	/**
	 * Instantiates a new agua.
	 */
	public Agua() {
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
	 * Gets the alcant.
	 *
	 * @return the alcant
	 */
	public BigDecimal getAlcant() {
		return alcant;
	}

	/**
	 * Sets the alcant.
	 *
	 * @param alcant the new alcant
	 */
	public void setAlcant(BigDecimal alcant) {
		this.alcant = alcant;
	}

	/**
	 * Gets the alcanta.
	 *
	 * @return the alcanta
	 */
	public BigDecimal getAlcanta() {
		return alcanta;
	}

	/**
	 * Sets the alcanta.
	 *
	 * @param alcanta the new alcanta
	 */
	public void setAlcanta(BigDecimal alcanta) {
		this.alcanta = alcanta;
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
	 * Gets the conexr.
	 *
	 * @return the conexr
	 */
	public BigDecimal getConexr() {
		return conexr;
	}

	/**
	 * Sets the conexr.
	 *
	 * @param conexr the new conexr
	 */
	public void setConexr(BigDecimal conexr) {
		this.conexr = conexr;
	}

	/**
	 * Gets the conexra.
	 *
	 * @return the conexra
	 */
	public BigDecimal getConexra() {
		return conexra;
	}

	/**
	 * Sets the conexra.
	 *
	 * @param conexra the new conexra
	 */
	public void setConexra(BigDecimal conexra) {
		this.conexra = conexra;
	}

	/**
	 * Gets the drenaje.
	 *
	 * @return the drenaje
	 */
	public BigDecimal getDrenaje() {
		return drenaje;
	}

	/**
	 * Sets the drenaje.
	 *
	 * @param drenaje the new drenaje
	 */
	public void setDrenaje(BigDecimal drenaje) {
		this.drenaje = drenaje;
	}

	/**
	 * Gets the drenajea.
	 *
	 * @return the drenajea
	 */
	public BigDecimal getDrenajea() {
		return drenajea;
	}

	/**
	 * Sets the drenajea.
	 *
	 * @param drenajea the new drenajea
	 */
	public void setDrenajea(BigDecimal drenajea) {
		this.drenajea = drenajea;
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
	 * Gets the gtosejec.
	 *
	 * @return the gtosejec
	 */
	public BigDecimal getGtosejec() {
		return gtosejec;
	}

	/**
	 * Sets the gtosejec.
	 *
	 * @param gtosejec the new gtosejec
	 */
	public void setGtosejec(BigDecimal gtosejec) {
		this.gtosejec = gtosejec;
	}

	/**
	 * Gets the gtosejeca.
	 *
	 * @return the gtosejeca
	 */
	public BigDecimal getGtosejeca() {
		return gtosejeca;
	}

	/**
	 * Sets the gtosejeca.
	 *
	 * @param gtosejeca the new gtosejeca
	 */
	public void setGtosejeca(BigDecimal gtosejeca) {
		this.gtosejeca = gtosejeca;
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
	 * Gets the ntomasmen.
	 *
	 * @return the ntomasmen
	 */
	public Integer getNtomasmen() {
		return ntomasmen;
	}

	/**
	 * Sets the ntomasmen.
	 *
	 * @param ntomasmen the new ntomasmen
	 */
	public void setNtomasmen(Integer ntomasmen) {
		this.ntomasmen = ntomasmen;
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
	 * Gets the sagua.
	 *
	 * @return the sagua
	 */
	public BigDecimal getSagua() {
		return sagua;
	}

	/**
	 * Sets the sagua.
	 *
	 * @param sagua the new sagua
	 */
	public void setSagua(BigDecimal sagua) {
		this.sagua = sagua;
	}

	/**
	 * Gets the saguaa.
	 *
	 * @return the saguaa
	 */
	public BigDecimal getSaguaa() {
		return saguaa;
	}

	/**
	 * Sets the saguaa.
	 *
	 * @param saguaa the new saguaa
	 */
	public void setSaguaa(BigDecimal saguaa) {
		this.saguaa = saguaa;
	}

	/**
	 * Gets the tomasantes.
	 *
	 * @return the tomasantes
	 */
	public Integer getTomasantes() {
		return tomasantes;
	}

	/**
	 * Sets the tomasantes.
	 *
	 * @param tomasantes the new tomasantes
	 */
	public void setTomasantes(Integer tomasantes) {
		this.tomasantes = tomasantes;
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
		Agua other = (Agua) obj;
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
		return "Agua [id=" + id + ", alcant=" + alcant + ", alcanta=" + alcanta + ", campo1=" + campo1 + ", campo2="
				+ campo2 + ", conexr=" + conexr + ", conexra=" + conexra + ", drenaje=" + drenaje + ", drenajea="
				+ drenajea + ", feccap=" + feccap + ", gtosejec=" + gtosejec + ", gtosejeca=" + gtosejeca + ", idRef="
				+ idRef + ", idsector=" + idsector + ", indemni=" + indemni + ", indemnia=" + indemnia + ", mes=" + mes
				+ ", multas=" + multas + ", multasa=" + multasa + ", ntomasmen=" + ntomasmen + ", otros=" + otros
				+ ", otrosa=" + otrosa + ", racumaa=" + racumaa + ", ralmes=" + ralmes + ", rdeaa=" + rdeaa
				+ ", rdelmes=" + rdelmes + ", recargos=" + recargos + ", recargosa=" + recargosa + ", sagua=" + sagua
				+ ", saguaa=" + saguaa + ", tomasantes=" + tomasantes + ", userid=" + userid + ", usuario=" + usuario
				+ "]";
	}

}