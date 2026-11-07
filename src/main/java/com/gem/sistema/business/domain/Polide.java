package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the POLIDE database table.
 * 
 */
@SqlResultSetMapping(name = "consultaMovimientosMapping", classes = {
        @ConstructorResult(targetClass = Polide.class, columns = {
        		@ColumnResult(name = "canpol", type = BigDecimal.class),
        		@ColumnResult(name = "canpolh", type = BigDecimal.class),
        		@ColumnResult(name = "cappol", type = String.class),
                @ColumnResult(name = "conpol", type = Integer.class),
                @ColumnResult(name = "concep", type = String.class),
                @ColumnResult(name = "renpol", type = BigDecimal.class),
                @ColumnResult(name = "stapol", type = String.class),
                @ColumnResult(name = "staafe", type = String.class),
                @ColumnResult(name = "tippol", type = String.class),
        }) 
})
@Entity
@NamedQuery(name="Polide.findAll", query="SELECT p FROM Polide p")
public class Polide extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The anopol. */
	private Integer anopol;

	/** The antpag. */
	private BigDecimal antpag;

	/** The canpol. */
	private BigDecimal canpol;

	/** The canpolh. */
	private BigDecimal canpolh;

	/** The caopol. */
	private String caopol;

	/** The clvban. */
	private Integer clvban;

	/** The clvcto. */
	private String clvcto;

	/** The clvfuen. */
	private String clvfuen;

	/** The concep. */
	private String concep;

	/** The conpol. */
	private Integer conpol;

	/** The cuenta. */
	private String cuenta;

	/** The cuenta 1. */
	private String cuenta1;

	/** The dn. */
	private String dn;

	/** The fecpol. */
	@Temporal(TemporalType.DATE)
	private Date fecpol;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;
	
	/** The idsector. */
	@Column(name="IDSECTOR")
	private Integer idsector;

	/** The mespol. */
	private Integer mespol;

	/** The numdet. */
	private BigDecimal numdet;

	/** The recpol. */
	private String recpol;

	/** The refpol. */
	private String refpol;

	/** The rela. */
	private Integer rela;

	/** The renpol. */
	private BigDecimal renpol;

	/** The renpolr. */
	private BigDecimal renpolr;

	/** The scta. */
	private String scta;

	/** The sscta. */
	private String sscta;

	/** The ssscta. */
	private String ssscta;

	/** The sssscta. */
	private String sssscta;

	/** The stacon. */
	private Integer stacon;

	/** The tipcon. */
	private Integer tipcon;

	/** The tippol. */
	private String tippol;

	/** The tipr. */
	private String tipr;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The bmin. */
	@Transient
	private Integer bmin;
	
	/** The stapol. */
	@Transient
	private String stapol;
	
	/** The staafe. */
	@Transient
	private String staafe;
	
	/** The cappol. */
	@Transient
	private String cappol;
	
	/** Numero de contrato */
	private String numcto;
	
	/** Numero de factura */
	private String numfac;
	
	/** Numero del proveedor */
	private String numprov;
	
	/** RFC */
	private String rfc;
	
	/**
	 * Gets the bmin.
	 *
	 * @return the bmin
	 */
	public Integer getBmin() {
		return bmin;
	}
	
	/**
	 * Sets the bmin.
	 *
	 * @param bmin the new bmin
	 */
	public void setBmin(Integer bmin) {
		this.bmin = bmin;
	}
	
	/** The nomcta. */
	@Transient
	private String nomcta;
	
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
	
	/** The composite key. */
	@Transient
	private String compositeKey;
	
	/**
	 * Gets the composite key.
	 *
	 * @return the composite key
	 */
	public String getCompositeKey() {
		return cuenta +  scta + sscta + ssscta + sssscta + conpol;
	}

	/**
	 * Sets the composite key.
	 *
	 * @param compositeKey the new composite key
	 */
	public void setCompositeKey(String compositeKey) {
		this.compositeKey = compositeKey;
	}

	/**
	 * Instantiates a new polide.
	 */
	public Polide() {
	}

	/**
	 * Instantiates a new polide.
	 *
	 * @param canpol the canpol
	 * @param canpolh the canpolh
	 * @param cappol the cappol
	 * @param conpol the conpol
	 * @param concep the concep
	 * @param renpol the renpol
	 * @param stapol the stapol
	 * @param staafe the staafe
	 * @param tippol the tippol
	 * @param numcto the numcto;
	 * @param numfac the numfac;
	 * @param numprov the numprov;
	 * @param rfc the rfc;
	 */
	public Polide(BigDecimal canpol, BigDecimal canpolh, String cappol, Integer conpol, String concep, BigDecimal renpol,
			String stapol, String staafe, String tippol, String numcto, String numfac, String numprov, String rfc) {
		super();
		this.canpol = canpol;
		this.canpolh = canpolh;
		this.cappol = cappol;
		this.conpol = conpol;
		this.concep = concep;
		this.renpol = renpol;
		this.stapol = stapol;
		this.staafe = staafe;
		this.tippol = tippol;
		this.numcto = numcto;
		this.numfac = numfac;
		this.numprov = numprov;
		this.rfc = rfc;
	}
	
	/**
	 * Gets the anopol.
	 *
	 * @return the anopol
	 */
	public Integer getAnopol() {
		return this.anopol;
	}

	/**
	 * Sets the anopol.
	 *
	 * @param anopol the new anopol
	 */
	public void setAnopol(Integer anopol) {
		this.anopol = anopol;
	}

	/**
	 * Gets the antpag.
	 *
	 * @return the antpag
	 */
	public BigDecimal getAntpag() {
		return this.antpag;
	}

	/**
	 * Sets the antpag.
	 *
	 * @param antpag the new antpag
	 */
	public void setAntpag(BigDecimal antpag) {
		this.antpag = antpag;
	}

	/**
	 * Gets the canpol.
	 *
	 * @return the canpol
	 */
	public BigDecimal getCanpol() {
		return this.canpol;
	}

	/**
	 * Sets the canpol.
	 *
	 * @param canpol the new canpol
	 */
	public void setCanpol(BigDecimal canpol) {
		this.canpol = canpol;
	}

	/**
	 * Gets the canpolh.
	 *
	 * @return the canpolh
	 */
	public BigDecimal getCanpolh() {
		return this.canpolh;
	}

	/**
	 * Sets the canpolh.
	 *
	 * @param canpolh the new canpolh
	 */
	public void setCanpolh(BigDecimal canpolh) {
		this.canpolh = canpolh;
	}

	/**
	 * Gets the caopol.
	 *
	 * @return the caopol
	 */
	public String getCaopol() {
		return this.caopol;
	}

	/**
	 * Sets the caopol.
	 *
	 * @param caopol the new caopol
	 */
	public void setCaopol(String caopol) {
		this.caopol = caopol;
	}

	/**
	 * Gets the clvban.
	 *
	 * @return the clvban
	 */
	public Integer getClvban() {
		return this.clvban;
	}

	/**
	 * Sets the clvban.
	 *
	 * @param clvban the new clvban
	 */
	public void setClvban(Integer clvban) {
		this.clvban = clvban;
	}

	/**
	 * Gets the clvcto.
	 *
	 * @return the clvcto
	 */
	public String getClvcto() {
		return this.clvcto;
	}

	/**
	 * Sets the clvcto.
	 *
	 * @param clvcto the new clvcto
	 */
	public void setClvcto(String clvcto) {
		this.clvcto = clvcto;
	}

	/**
	 * Gets the clvfuen.
	 *
	 * @return the clvfuen
	 */
	public String getClvfuen() {
		return this.clvfuen;
	}

	/**
	 * Sets the clvfuen.
	 *
	 * @param clvfuen the new clvfuen
	 */
	public void setClvfuen(String clvfuen) {
		this.clvfuen = clvfuen;
	}

	/**
	 * Gets the concep.
	 *
	 * @return the concep
	 */
	public String getConcep() {
		return this.concep;
	}

	/**
	 * Sets the concep.
	 *
	 * @param concep the new concep
	 */
	public void setConcep(String concep) {
		this.concep = concep;
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
	 * Gets the cuenta 1.
	 *
	 * @return the cuenta 1
	 */
	public String getCuenta1() {
		return this.cuenta1;
	}

	/**
	 * Sets the cuenta 1.
	 *
	 * @param cuenta1 the new cuenta 1
	 */
	public void setCuenta1(String cuenta1) {
		this.cuenta1 = cuenta1;
	}

	/**
	 * Gets the dn.
	 *
	 * @return the dn
	 */
	public String getDn() {
		return this.dn;
	}

	/**
	 * Sets the dn.
	 *
	 * @param dn the new dn
	 */
	public void setDn(String dn) {
		this.dn = dn;
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
	 * Gets the mespol.
	 *
	 * @return the mespol
	 */
	public Integer getMespol() {
		return this.mespol;
	}

	/**
	 * Sets the mespol.
	 *
	 * @param mespol the new mespol
	 */
	public void setMespol(Integer mespol) {
		this.mespol = mespol;
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
	 * Gets the recpol.
	 *
	 * @return the recpol
	 */
	public String getRecpol() {
		return this.recpol;
	}

	/**
	 * Sets the recpol.
	 *
	 * @param recpol the new recpol
	 */
	public void setRecpol(String recpol) {
		this.recpol = recpol;
	}

	/**
	 * Gets the refpol.
	 *
	 * @return the refpol
	 */
	public String getRefpol() {
		return this.refpol;
	}

	/**
	 * Sets the refpol.
	 *
	 * @param refpol the new refpol
	 */
	public void setRefpol(String refpol) {
		this.refpol = refpol;
	}

	/**
	 * Gets the rela.
	 *
	 * @return the rela
	 */
	public Integer getRela() {
		return this.rela;
	}

	/**
	 * Sets the rela.
	 *
	 * @param rela the new rela
	 */
	public void setRela(Integer rela) {
		this.rela = rela;
	}

	/**
	 * Gets the renpol.
	 *
	 * @return the renpol
	 */
	public BigDecimal getRenpol() {
		return this.renpol;
	}

	/**
	 * Sets the renpol.
	 *
	 * @param renpol the new renpol
	 */
	public void setRenpol(BigDecimal renpol) {
		this.renpol = renpol;
	}

	/**
	 * Gets the renpolr.
	 *
	 * @return the renpolr
	 */
	public BigDecimal getRenpolr() {
		return this.renpolr;
	}

	/**
	 * Sets the renpolr.
	 *
	 * @param renpolr the new renpolr
	 */
	public void setRenpolr(BigDecimal renpolr) {
		this.renpolr = renpolr;
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
	 * Gets the stacon.
	 *
	 * @return the stacon
	 */
	public Integer getStacon() {
		return this.stacon;
	}

	/**
	 * Sets the stacon.
	 *
	 * @param stacon the new stacon
	 */
	public void setStacon(Integer stacon) {
		this.stacon = stacon;
	}

	/**
	 * Gets the tipcon.
	 *
	 * @return the tipcon
	 */
	public Integer getTipcon() {
		return this.tipcon;
	}

	/**
	 * Sets the tipcon.
	 *
	 * @param tipcon the new tipcon
	 */
	public void setTipcon(Integer tipcon) {
		this.tipcon = tipcon;
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
	 * Gets the tipr.
	 *
	 * @return the tipr
	 */
	public String getTipr() {
		return this.tipr;
	}

	/**
	 * Sets the tipr.
	 *
	 * @param tipr the new tipr
	 */
	public void setTipr(String tipr) {
		this.tipr = tipr;
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
	 * Gets the stapol.
	 *
	 * @return the stapol
	 */
	public String getStapol() {
		return stapol;
	}

	/**
	 * Sets the stapol.
	 *
	 * @param stapol the new stapol
	 */
	public void setStapol(String stapol) {
		this.stapol = stapol;
	}

	/**
	 * Gets the staafe.
	 *
	 * @return the staafe
	 */
	public String getStaafe() {
		return staafe;
	}

	/**
	 * Sets the staafe.
	 *
	 * @param staafe the new staafe
	 */
	public void setStaafe(String staafe) {
		this.staafe = staafe;
	}

	/**
	 * Gets the cappol.
	 *
	 * @return the cappol
	 */
	public String getCappol() {
		return cappol;
	}

	/**
	 * Sets the cappol.
	 *
	 * @param cappol the new cappol
	 */
	public void setCappol(String cappol) {
		this.cappol = cappol;
	}

	/**
	 * @return the numcto
	 */
	public String getNumcto() {
		return numcto;
	}

	/**
	 * @param numcto the numcto to set
	 */
	public void setNumcto(String numcto) {
		this.numcto = numcto;
	}

	/**
	 * @return the numfac
	 */
	public String getNumfac() {
		return numfac;
	}

	/**
	 * @param numfac the numfac to set
	 */
	public void setNumfac(String numfac) {
		this.numfac = numfac;
	}

	/**
	 * @return the numprov
	 */
	public String getNumprov() {
		return numprov;
	}

	/**
	 * @param numprov the numprov to set
	 */
	public void setNumprov(String numprov) {
		this.numprov = numprov;
	}

	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}


	@Override
	public String toString() {
		return "Polide [anopol=" + anopol + ", antpag=" + antpag + ", canpol=" + canpol + ", canpolh=" + canpolh
				+ ", caopol=" + caopol + ", clvban=" + clvban + ", clvcto=" + clvcto + ", clvfuen=" + clvfuen
				+ ", concep=" + concep + ", conpol=" + conpol + ", cuenta=" + cuenta + ", cuenta1=" + cuenta1 + ", dn="
				+ dn + ", fecpol=" + fecpol + ", idRef=" + idRef + ", idsector=" + idsector + ", mespol=" + mespol
				+ ", numdet=" + numdet + ", recpol=" + recpol + ", refpol=" + refpol + ", rela=" + rela + ", renpol="
				+ renpol + ", renpolr=" + renpolr + ", scta=" + scta + ", sscta=" + sscta + ", ssscta=" + ssscta
				+ ", sssscta=" + sssscta + ", stacon=" + stacon + ", tipcon=" + tipcon + ", tippol=" + tippol
				+ ", tipr=" + tipr + ", userid=" + userid + ", bmin=" + bmin + ", numcto=" + numcto + ", numfac="
				+ numfac + ", numprov=" + numprov + ", rfc=" + rfc + ", nomcta=" + nomcta + "]";
	}

}