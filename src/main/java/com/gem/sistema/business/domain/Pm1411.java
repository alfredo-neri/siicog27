package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM1411 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm1411.findAll", query = "SELECT p FROM Pm1411 p")
public class Pm1411 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The mensual. */
	private Integer mensual = 0;

	/** The te. */
	private BigDecimal te;
	
	/** The sp. */
	private BigDecimal sp;
	
	/** The spspa. */
	private BigDecimal spspa;
	
	/** The acute. */
	private BigDecimal acute;
	
	/** The acusp. */
	private BigDecimal acusp;
	
	/** The acuspspa. */
	private BigDecimal acuspspa;
	
	/** The obste. */
	private String obste;
	
	/** The obssp. */
	private String obssp;
	
	/** The obsspspa. */
	private String obsspspa;
	
	/** The dif. */
	private BigDecimal dif;
	
	/** The pdif. */
	private BigDecimal pdif;
	
	/** The sjaf. */
	private BigDecimal sjaf;
	
	/** The psjaf. */
	private BigDecimal psjaf;
	
	/** The oapf. */
	private BigDecimal oapf;
	
	/** The poapf. */
	private BigDecimal poapf;
	
	/** The als. */
	private BigDecimal als;
	
	/** The pals. */
	private BigDecimal pals;
	
	/** The ape. */
	private BigDecimal ape;
	
	/** The pape. */
	private BigDecimal pape;
	
	/** The aam. */
	private BigDecimal aam;
	
	/** The paam. */
	private BigDecimal paam;
	
	/** The aj. */
	private BigDecimal aj;
	
	/** The paj. */
	private BigDecimal paj;
	
	/** The ied. */
	private BigDecimal ied;
	
	/** The pied. */
	private BigDecimal pied;
	
	/** The ei. */
	private BigDecimal ei;
	
	/** The pei. */
	private BigDecimal pei;
	
	/** The avma. */
	private BigDecimal avma;
	
	/** The pavma. */
	private BigDecimal pavma;
	
	/** The pafd. */
	private BigDecimal pafd;
	
	/** The ppafd. */
	private BigDecimal ppafd;
	
	/** The spdc. */
	private BigDecimal spdc;
	
	/** The pspdc. */
	private BigDecimal pspdc;
	
	/** The amse. */
	private BigDecimal amse;
	
	/** The pamse. */
	private BigDecimal pamse;
	
	/** The apcd. */
	private BigDecimal apcd;
	
	/** The papcd. */
	private BigDecimal papcd;
	
	/** The ampg. */
	private BigDecimal ampg;
	
	/** The pampg. */
	private BigDecimal pampg;
	
	/** The oana. */
	private BigDecimal oana;
	
	/** The poana. */
	private BigDecimal poana;
	
	/** The aid. */
	private BigDecimal aid;
	
	/** The paid. */
	private BigDecimal paid;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/** The idsector. */
	private int idsector;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/**
	 * Instantiates a new pm 1411.
	 */
	public Pm1411() {
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
	 * Gets the mensual.
	 *
	 * @return the mensual
	 */
	public Integer getMensual() {
		return mensual;
	}

	/**
	 * Sets the mensual.
	 *
	 * @param mensual the new mensual
	 */
	public void setMensual(Integer mensual) {
		this.mensual = mensual;
	}

	/**
	 * Gets the te.
	 *
	 * @return the te
	 */
	public BigDecimal getTe() {
		return te;
	}

	/**
	 * Sets the te.
	 *
	 * @param te the new te
	 */
	public void setTe(BigDecimal te) {
		this.te = te;
	}

	/**
	 * Gets the sp.
	 *
	 * @return the sp
	 */
	public BigDecimal getSp() {
		return sp;
	}

	/**
	 * Sets the sp.
	 *
	 * @param sp the new sp
	 */
	public void setSp(BigDecimal sp) {
		this.sp = sp;
	}

	/**
	 * Gets the spspa.
	 *
	 * @return the spspa
	 */
	public BigDecimal getSpspa() {
		return spspa;
	}

	/**
	 * Sets the spspa.
	 *
	 * @param spspa the new spspa
	 */
	public void setSpspa(BigDecimal spspa) {
		this.spspa = spspa;
	}

	/**
	 * Gets the acute.
	 *
	 * @return the acute
	 */
	public BigDecimal getAcute() {
		return acute;
	}

	/**
	 * Sets the acute.
	 *
	 * @param acute the new acute
	 */
	public void setAcute(BigDecimal acute) {
		this.acute = acute;
	}

	/**
	 * Gets the acusp.
	 *
	 * @return the acusp
	 */
	public BigDecimal getAcusp() {
		return acusp;
	}

	/**
	 * Sets the acusp.
	 *
	 * @param acusp the new acusp
	 */
	public void setAcusp(BigDecimal acusp) {
		this.acusp = acusp;
	}

	/**
	 * Gets the acuspspa.
	 *
	 * @return the acuspspa
	 */
	public BigDecimal getAcuspspa() {
		return acuspspa;
	}

	/**
	 * Sets the acuspspa.
	 *
	 * @param acuspspa the new acuspspa
	 */
	public void setAcuspspa(BigDecimal acuspspa) {
		this.acuspspa = acuspspa;
	}

	/**
	 * Gets the obste.
	 *
	 * @return the obste
	 */
	public String getObste() {
		return obste;
	}

	/**
	 * Sets the obste.
	 *
	 * @param obste the new obste
	 */
	public void setObste(String obste) {
		this.obste = obste;
	}

	/**
	 * Gets the obssp.
	 *
	 * @return the obssp
	 */
	public String getObssp() {
		return obssp;
	}

	/**
	 * Sets the obssp.
	 *
	 * @param obssp the new obssp
	 */
	public void setObssp(String obssp) {
		this.obssp = obssp;
	}

	/**
	 * Gets the obsspspa.
	 *
	 * @return the obsspspa
	 */
	public String getObsspspa() {
		return obsspspa;
	}

	/**
	 * Sets the obsspspa.
	 *
	 * @param obsspspa the new obsspspa
	 */
	public void setObsspspa(String obsspspa) {
		this.obsspspa = obsspspa;
	}

	/**
	 * Gets the dif.
	 *
	 * @return the dif
	 */
	public BigDecimal getDif() {
		return dif;
	}

	/**
	 * Sets the dif.
	 *
	 * @param dif the new dif
	 */
	public void setDif(BigDecimal dif) {
		this.dif = dif;
	}

	/**
	 * Gets the pdif.
	 *
	 * @return the pdif
	 */
	public BigDecimal getPdif() {
		return pdif;
	}

	/**
	 * Sets the pdif.
	 *
	 * @param pdif the new pdif
	 */
	public void setPdif(BigDecimal pdif) {
		this.pdif = pdif;
	}

	/**
	 * Gets the sjaf.
	 *
	 * @return the sjaf
	 */
	public BigDecimal getSjaf() {
		return sjaf;
	}

	/**
	 * Sets the sjaf.
	 *
	 * @param sjaf the new sjaf
	 */
	public void setSjaf(BigDecimal sjaf) {
		this.sjaf = sjaf;
	}

	/**
	 * Gets the psjaf.
	 *
	 * @return the psjaf
	 */
	public BigDecimal getPsjaf() {
		return psjaf;
	}

	/**
	 * Sets the psjaf.
	 *
	 * @param psjaf the new psjaf
	 */
	public void setPsjaf(BigDecimal psjaf) {
		this.psjaf = psjaf;
	}

	/**
	 * Gets the oapf.
	 *
	 * @return the oapf
	 */
	public BigDecimal getOapf() {
		return oapf;
	}

	/**
	 * Sets the oapf.
	 *
	 * @param oapf the new oapf
	 */
	public void setOapf(BigDecimal oapf) {
		this.oapf = oapf;
	}

	/**
	 * Gets the poapf.
	 *
	 * @return the poapf
	 */
	public BigDecimal getPoapf() {
		return poapf;
	}

	/**
	 * Sets the poapf.
	 *
	 * @param poapf the new poapf
	 */
	public void setPoapf(BigDecimal poapf) {
		this.poapf = poapf;
	}

	/**
	 * Gets the als.
	 *
	 * @return the als
	 */
	public BigDecimal getAls() {
		return als;
	}

	/**
	 * Sets the als.
	 *
	 * @param als the new als
	 */
	public void setAls(BigDecimal als) {
		this.als = als;
	}

	/**
	 * Gets the pals.
	 *
	 * @return the pals
	 */
	public BigDecimal getPals() {
		return pals;
	}

	/**
	 * Sets the pals.
	 *
	 * @param pals the new pals
	 */
	public void setPals(BigDecimal pals) {
		this.pals = pals;
	}

	/**
	 * Gets the ape.
	 *
	 * @return the ape
	 */
	public BigDecimal getApe() {
		return ape;
	}

	/**
	 * Sets the ape.
	 *
	 * @param ape the new ape
	 */
	public void setApe(BigDecimal ape) {
		this.ape = ape;
	}

	/**
	 * Gets the pape.
	 *
	 * @return the pape
	 */
	public BigDecimal getPape() {
		return pape;
	}

	/**
	 * Sets the pape.
	 *
	 * @param pape the new pape
	 */
	public void setPape(BigDecimal pape) {
		this.pape = pape;
	}

	/**
	 * Gets the aam.
	 *
	 * @return the aam
	 */
	public BigDecimal getAam() {
		return aam;
	}

	/**
	 * Sets the aam.
	 *
	 * @param aam the new aam
	 */
	public void setAam(BigDecimal aam) {
		this.aam = aam;
	}

	/**
	 * Gets the paam.
	 *
	 * @return the paam
	 */
	public BigDecimal getPaam() {
		return paam;
	}

	/**
	 * Sets the paam.
	 *
	 * @param paam the new paam
	 */
	public void setPaam(BigDecimal paam) {
		this.paam = paam;
	}

	/**
	 * Gets the aj.
	 *
	 * @return the aj
	 */
	public BigDecimal getAj() {
		return aj;
	}

	/**
	 * Sets the aj.
	 *
	 * @param aj the new aj
	 */
	public void setAj(BigDecimal aj) {
		this.aj = aj;
	}

	/**
	 * Gets the paj.
	 *
	 * @return the paj
	 */
	public BigDecimal getPaj() {
		return paj;
	}

	/**
	 * Sets the paj.
	 *
	 * @param paj the new paj
	 */
	public void setPaj(BigDecimal paj) {
		this.paj = paj;
	}

	/**
	 * Gets the ied.
	 *
	 * @return the ied
	 */
	public BigDecimal getIed() {
		return ied;
	}

	/**
	 * Sets the ied.
	 *
	 * @param ied the new ied
	 */
	public void setIed(BigDecimal ied) {
		this.ied = ied;
	}

	/**
	 * Gets the pied.
	 *
	 * @return the pied
	 */
	public BigDecimal getPied() {
		return pied;
	}

	/**
	 * Sets the pied.
	 *
	 * @param pied the new pied
	 */
	public void setPied(BigDecimal pied) {
		this.pied = pied;
	}

	/**
	 * Gets the ei.
	 *
	 * @return the ei
	 */
	public BigDecimal getEi() {
		return ei;
	}

	/**
	 * Sets the ei.
	 *
	 * @param ei the new ei
	 */
	public void setEi(BigDecimal ei) {
		this.ei = ei;
	}

	/**
	 * Gets the pei.
	 *
	 * @return the pei
	 */
	public BigDecimal getPei() {
		return pei;
	}

	/**
	 * Sets the pei.
	 *
	 * @param pei the new pei
	 */
	public void setPei(BigDecimal pei) {
		this.pei = pei;
	}

	/**
	 * Gets the avma.
	 *
	 * @return the avma
	 */
	public BigDecimal getAvma() {
		return avma;
	}

	/**
	 * Sets the avma.
	 *
	 * @param avma the new avma
	 */
	public void setAvma(BigDecimal avma) {
		this.avma = avma;
	}

	/**
	 * Gets the pavma.
	 *
	 * @return the pavma
	 */
	public BigDecimal getPavma() {
		return pavma;
	}

	/**
	 * Sets the pavma.
	 *
	 * @param pavma the new pavma
	 */
	public void setPavma(BigDecimal pavma) {
		this.pavma = pavma;
	}

	/**
	 * Gets the pafd.
	 *
	 * @return the pafd
	 */
	public BigDecimal getPafd() {
		return pafd;
	}

	/**
	 * Sets the pafd.
	 *
	 * @param pafd the new pafd
	 */
	public void setPafd(BigDecimal pafd) {
		this.pafd = pafd;
	}

	/**
	 * Gets the ppafd.
	 *
	 * @return the ppafd
	 */
	public BigDecimal getPpafd() {
		return ppafd;
	}

	/**
	 * Sets the ppafd.
	 *
	 * @param ppafd the new ppafd
	 */
	public void setPpafd(BigDecimal ppafd) {
		this.ppafd = ppafd;
	}

	/**
	 * Gets the spdc.
	 *
	 * @return the spdc
	 */
	public BigDecimal getSpdc() {
		return spdc;
	}

	/**
	 * Sets the spdc.
	 *
	 * @param spdc the new spdc
	 */
	public void setSpdc(BigDecimal spdc) {
		this.spdc = spdc;
	}

	/**
	 * Gets the pspdc.
	 *
	 * @return the pspdc
	 */
	public BigDecimal getPspdc() {
		return pspdc;
	}

	/**
	 * Sets the pspdc.
	 *
	 * @param pspdc the new pspdc
	 */
	public void setPspdc(BigDecimal pspdc) {
		this.pspdc = pspdc;
	}

	/**
	 * Gets the amse.
	 *
	 * @return the amse
	 */
	public BigDecimal getAmse() {
		return amse;
	}

	/**
	 * Sets the amse.
	 *
	 * @param amse the new amse
	 */
	public void setAmse(BigDecimal amse) {
		this.amse = amse;
	}

	/**
	 * Gets the pamse.
	 *
	 * @return the pamse
	 */
	public BigDecimal getPamse() {
		return pamse;
	}

	/**
	 * Sets the pamse.
	 *
	 * @param pamse the new pamse
	 */
	public void setPamse(BigDecimal pamse) {
		this.pamse = pamse;
	}

	/**
	 * Gets the apcd.
	 *
	 * @return the apcd
	 */
	public BigDecimal getApcd() {
		return apcd;
	}

	/**
	 * Sets the apcd.
	 *
	 * @param apcd the new apcd
	 */
	public void setApcd(BigDecimal apcd) {
		this.apcd = apcd;
	}

	/**
	 * Gets the papcd.
	 *
	 * @return the papcd
	 */
	public BigDecimal getPapcd() {
		return papcd;
	}

	/**
	 * Sets the papcd.
	 *
	 * @param papcd the new papcd
	 */
	public void setPapcd(BigDecimal papcd) {
		this.papcd = papcd;
	}

	/**
	 * Gets the ampg.
	 *
	 * @return the ampg
	 */
	public BigDecimal getAmpg() {
		return ampg;
	}

	/**
	 * Sets the ampg.
	 *
	 * @param ampg the new ampg
	 */
	public void setAmpg(BigDecimal ampg) {
		this.ampg = ampg;
	}

	/**
	 * Gets the pampg.
	 *
	 * @return the pampg
	 */
	public BigDecimal getPampg() {
		return pampg;
	}

	/**
	 * Sets the pampg.
	 *
	 * @param pampg the new pampg
	 */
	public void setPampg(BigDecimal pampg) {
		this.pampg = pampg;
	}

	/**
	 * Gets the oana.
	 *
	 * @return the oana
	 */
	public BigDecimal getOana() {
		return oana;
	}

	/**
	 * Sets the oana.
	 *
	 * @param oana the new oana
	 */
	public void setOana(BigDecimal oana) {
		this.oana = oana;
	}

	/**
	 * Gets the poana.
	 *
	 * @return the poana
	 */
	public BigDecimal getPoana() {
		return poana;
	}

	/**
	 * Sets the poana.
	 *
	 * @param poana the new poana
	 */
	public void setPoana(BigDecimal poana) {
		this.poana = poana;
	}

	/**
	 * Gets the aid.
	 *
	 * @return the aid
	 */
	public BigDecimal getAid() {
		return aid;
	}

	/**
	 * Sets the aid.
	 *
	 * @param aid the new aid
	 */
	public void setAid(BigDecimal aid) {
		this.aid = aid;
	}

	/**
	 * Gets the paid.
	 *
	 * @return the paid
	 */
	public BigDecimal getPaid() {
		return paid;
	}

	/**
	 * Sets the paid.
	 *
	 * @param paid the new paid
	 */
	public void setPaid(BigDecimal paid) {
		this.paid = paid;
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
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public int getIdsector() {
		return idsector;
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
		Pm1411 other = (Pm1411) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}