package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the POLIEN database table.
 * 
 */
@Entity
@NamedQuery(name="Polien.findAll", query="SELECT p FROM Polien p")
public class Polien extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	

	/** The anopol. */
	private Integer anopol;

	/** The cappol. */
	private String cappol;

	/** The ccondu. */
	private Integer ccondu;

	/** The cconfl. */
	private Integer cconfl;

	/** The cconii. */
	private Integer cconii;

	/** The cconin. */
	private Integer cconin;

	/** The ccontrol. */
	private BigDecimal ccontrol;

	/** The ccuenta. */
	private BigDecimal ccuenta;

	/** The cdebe. */
	private BigDecimal cdebe;

	/** The chaber. */
	private BigDecimal chaber;

	/** The clvpol. */
	private String clvpol;

	/** The conpol. */
	private Integer conpol;

	/** The cscta. */
	private BigDecimal cscta;

	/** The csscta. */
	private BigDecimal csscta;

	/** The cssscta. */
	private BigDecimal cssscta;

	/** The csssscta. */
	private BigDecimal csssscta;

	/** The cta 600. */
	private BigDecimal cta600;

	/** The ctc 600. */
	private BigDecimal ctc600;

	/** The ctcuenta. */
	private BigDecimal ctcuenta;

	/** The ctscta. */
	private BigDecimal ctscta;

	/** The ctsscta. */
	private BigDecimal ctsscta;

	/** The ctssscta. */
	private BigDecimal ctssscta;

	/** The ctsssscta. */
	private BigDecimal ctsssscta;

	/** The esppol. */
	private String esppol;

	/** The estatus. */
	private String estatus;

	/** The fecafec. */
	@Temporal(TemporalType.DATE)
	private Date fecafec;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The fecpol. */
	@Temporal(TemporalType.DATE)
	private Date fecpol = Calendar.getInstance().getTime();

	/** The idsector. */
	private Integer idsector;

	/** The mespol. */
	private Integer mespol;

	/** The nivaut. */
	private Integer nivaut;

	/** The p 1000. */
	private BigDecimal p1000;

	/** The p 2000. */
	private BigDecimal p2000;

	/** The p 3000. */
	private BigDecimal p3000;

	/** The p 4000. */
	private BigDecimal p4000;

	/** The p 5000. */
	private BigDecimal p5000;

	/** The p 6000. */
	private BigDecimal p6000;

	/** The p 7000. */
	private BigDecimal p7000;

	/** The p 8000. */
	private BigDecimal p8000;

	/** The p 9000. */
	private BigDecimal p9000;

	/** The polclv 11. */
	@Column(name="POLCLV_1")
	private String polclv11;

	/** The polclv 12. */
	@Column(name="POLCLV_2")
	private String polclv12;

	/** The polclv 13. */
	@Column(name="POLCLV_3")
	private String polclv13;

	/** The polclv 1. */
	private String polclv1;

	/** The polclv 2. */
	private String polclv2;

	/** The polclv 3. */
	private String polclv3;

	/** The renpol. */
	private BigDecimal renpol;

	/** The staafe. */
	private String staafe;

	/** The staaut. */
	private String staaut;

	/** The stades. */
	private String stades;

	/** The stapol. */
	private String stapol;

	/** The tippol. */
	private String tippol;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new polien.
	 */
	public Polien() {
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
	 * Gets the cappol.
	 *
	 * @return the cappol
	 */
	public String getCappol() {
		return this.cappol;
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
	 * Gets the ccondu.
	 *
	 * @return the ccondu
	 */
	public Integer getCcondu() {
		return this.ccondu;
	}

	/**
	 * Sets the ccondu.
	 *
	 * @param ccondu the new ccondu
	 */
	public void setCcondu(Integer ccondu) {
		this.ccondu = ccondu;
	}

	/**
	 * Gets the cconfl.
	 *
	 * @return the cconfl
	 */
	public Integer getCconfl() {
		return this.cconfl;
	}

	/**
	 * Sets the cconfl.
	 *
	 * @param cconfl the new cconfl
	 */
	public void setCconfl(Integer cconfl) {
		this.cconfl = cconfl;
	}

	/**
	 * Gets the cconii.
	 *
	 * @return the cconii
	 */
	public Integer getCconii() {
		return this.cconii;
	}

	/**
	 * Sets the cconii.
	 *
	 * @param cconii the new cconii
	 */
	public void setCconii(Integer cconii) {
		this.cconii = cconii;
	}

	/**
	 * Gets the cconin.
	 *
	 * @return the cconin
	 */
	public Integer getCconin() {
		return this.cconin;
	}

	/**
	 * Sets the cconin.
	 *
	 * @param cconin the new cconin
	 */
	public void setCconin(Integer cconin) {
		this.cconin = cconin;
	}

	/**
	 * Gets the ccontrol.
	 *
	 * @return the ccontrol
	 */
	public BigDecimal getCcontrol() {
		return this.ccontrol;
	}

	/**
	 * Sets the ccontrol.
	 *
	 * @param ccontrol the new ccontrol
	 */
	public void setCcontrol(BigDecimal ccontrol) {
		this.ccontrol = ccontrol;
	}

	/**
	 * Gets the ccuenta.
	 *
	 * @return the ccuenta
	 */
	public BigDecimal getCcuenta() {
		return this.ccuenta;
	}

	/**
	 * Sets the ccuenta.
	 *
	 * @param ccuenta the new ccuenta
	 */
	public void setCcuenta(BigDecimal ccuenta) {
		this.ccuenta = ccuenta;
	}

	/**
	 * Gets the cdebe.
	 *
	 * @return the cdebe
	 */
	public BigDecimal getCdebe() {
		return this.cdebe;
	}

	/**
	 * Sets the cdebe.
	 *
	 * @param cdebe the new cdebe
	 */
	public void setCdebe(BigDecimal cdebe) {
		this.cdebe = cdebe;
	}

	/**
	 * Gets the chaber.
	 *
	 * @return the chaber
	 */
	public BigDecimal getChaber() {
		return this.chaber;
	}

	/**
	 * Sets the chaber.
	 *
	 * @param chaber the new chaber
	 */
	public void setChaber(BigDecimal chaber) {
		this.chaber = chaber;
	}

	/**
	 * Gets the clvpol.
	 *
	 * @return the clvpol
	 */
	public String getClvpol() {
		return this.clvpol;
	}

	/**
	 * Sets the clvpol.
	 *
	 * @param clvpol the new clvpol
	 */
	public void setClvpol(String clvpol) {
		this.clvpol = clvpol;
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
	 * Gets the cscta.
	 *
	 * @return the cscta
	 */
	public BigDecimal getCscta() {
		return this.cscta;
	}

	/**
	 * Sets the cscta.
	 *
	 * @param cscta the new cscta
	 */
	public void setCscta(BigDecimal cscta) {
		this.cscta = cscta;
	}

	/**
	 * Gets the csscta.
	 *
	 * @return the csscta
	 */
	public BigDecimal getCsscta() {
		return this.csscta;
	}

	/**
	 * Sets the csscta.
	 *
	 * @param csscta the new csscta
	 */
	public void setCsscta(BigDecimal csscta) {
		this.csscta = csscta;
	}

	/**
	 * Gets the cssscta.
	 *
	 * @return the cssscta
	 */
	public BigDecimal getCssscta() {
		return this.cssscta;
	}

	/**
	 * Sets the cssscta.
	 *
	 * @param cssscta the new cssscta
	 */
	public void setCssscta(BigDecimal cssscta) {
		this.cssscta = cssscta;
	}

	/**
	 * Gets the csssscta.
	 *
	 * @return the csssscta
	 */
	public BigDecimal getCsssscta() {
		return this.csssscta;
	}

	/**
	 * Sets the csssscta.
	 *
	 * @param csssscta the new csssscta
	 */
	public void setCsssscta(BigDecimal csssscta) {
		this.csssscta = csssscta;
	}

	/**
	 * Gets the cta 600.
	 *
	 * @return the cta 600
	 */
	public BigDecimal getCta600() {
		return this.cta600;
	}

	/**
	 * Sets the cta 600.
	 *
	 * @param cta600 the new cta 600
	 */
	public void setCta600(BigDecimal cta600) {
		this.cta600 = cta600;
	}

	/**
	 * Gets the ctc 600.
	 *
	 * @return the ctc 600
	 */
	public BigDecimal getCtc600() {
		return this.ctc600;
	}

	/**
	 * Sets the ctc 600.
	 *
	 * @param ctc600 the new ctc 600
	 */
	public void setCtc600(BigDecimal ctc600) {
		this.ctc600 = ctc600;
	}

	/**
	 * Gets the ctcuenta.
	 *
	 * @return the ctcuenta
	 */
	public BigDecimal getCtcuenta() {
		return this.ctcuenta;
	}

	/**
	 * Sets the ctcuenta.
	 *
	 * @param ctcuenta the new ctcuenta
	 */
	public void setCtcuenta(BigDecimal ctcuenta) {
		this.ctcuenta = ctcuenta;
	}

	/**
	 * Gets the ctscta.
	 *
	 * @return the ctscta
	 */
	public BigDecimal getCtscta() {
		return this.ctscta;
	}

	/**
	 * Sets the ctscta.
	 *
	 * @param ctscta the new ctscta
	 */
	public void setCtscta(BigDecimal ctscta) {
		this.ctscta = ctscta;
	}

	/**
	 * Gets the ctsscta.
	 *
	 * @return the ctsscta
	 */
	public BigDecimal getCtsscta() {
		return this.ctsscta;
	}

	/**
	 * Sets the ctsscta.
	 *
	 * @param ctsscta the new ctsscta
	 */
	public void setCtsscta(BigDecimal ctsscta) {
		this.ctsscta = ctsscta;
	}

	/**
	 * Gets the ctssscta.
	 *
	 * @return the ctssscta
	 */
	public BigDecimal getCtssscta() {
		return this.ctssscta;
	}

	/**
	 * Sets the ctssscta.
	 *
	 * @param ctssscta the new ctssscta
	 */
	public void setCtssscta(BigDecimal ctssscta) {
		this.ctssscta = ctssscta;
	}

	/**
	 * Gets the ctsssscta.
	 *
	 * @return the ctsssscta
	 */
	public BigDecimal getCtsssscta() {
		return this.ctsssscta;
	}

	/**
	 * Sets the ctsssscta.
	 *
	 * @param ctsssscta the new ctsssscta
	 */
	public void setCtsssscta(BigDecimal ctsssscta) {
		this.ctsssscta = ctsssscta;
	}

	/**
	 * Gets the esppol.
	 *
	 * @return the esppol
	 */
	public String getEsppol() {
		return this.esppol;
	}

	/**
	 * Sets the esppol.
	 *
	 * @param esppol the new esppol
	 */
	public void setEsppol(String esppol) {
		this.esppol = esppol;
	}

	/**
	 * Gets the estatus.
	 *
	 * @return the estatus
	 */
	public String getEstatus() {
		return this.estatus;
	}

	/**
	 * Sets the estatus.
	 *
	 * @param estatus the new estatus
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * Gets the fecafec.
	 *
	 * @return the fecafec
	 */
	public Date getFecafec() {
		return this.fecafec;
	}

	/**
	 * Sets the fecafec.
	 *
	 * @param fecafec the new fecafec
	 */
	public void setFecafec(Date fecafec) {
		this.fecafec = fecafec;
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
	 * Gets the nivaut.
	 *
	 * @return the nivaut
	 */
	public Integer getNivaut() {
		return this.nivaut;
	}

	/**
	 * Sets the nivaut.
	 *
	 * @param nivaut the new nivaut
	 */
	public void setNivaut(Integer nivaut) {
		this.nivaut = nivaut;
	}

	/**
	 * Gets the p1000.
	 *
	 * @return the p1000
	 */
	public BigDecimal getP1000() {
		return this.p1000;
	}

	/**
	 * Sets the p1000.
	 *
	 * @param p1000 the new p1000
	 */
	public void setP1000(BigDecimal p1000) {
		this.p1000 = p1000;
	}

	/**
	 * Gets the p2000.
	 *
	 * @return the p2000
	 */
	public BigDecimal getP2000() {
		return this.p2000;
	}

	/**
	 * Sets the p2000.
	 *
	 * @param p2000 the new p2000
	 */
	public void setP2000(BigDecimal p2000) {
		this.p2000 = p2000;
	}

	/**
	 * Gets the p3000.
	 *
	 * @return the p3000
	 */
	public BigDecimal getP3000() {
		return this.p3000;
	}

	/**
	 * Sets the p3000.
	 *
	 * @param p3000 the new p3000
	 */
	public void setP3000(BigDecimal p3000) {
		this.p3000 = p3000;
	}

	/**
	 * Gets the p4000.
	 *
	 * @return the p4000
	 */
	public BigDecimal getP4000() {
		return this.p4000;
	}

	/**
	 * Sets the p4000.
	 *
	 * @param p4000 the new p4000
	 */
	public void setP4000(BigDecimal p4000) {
		this.p4000 = p4000;
	}

	/**
	 * Gets the p5000.
	 *
	 * @return the p5000
	 */
	public BigDecimal getP5000() {
		return this.p5000;
	}

	/**
	 * Sets the p5000.
	 *
	 * @param p5000 the new p5000
	 */
	public void setP5000(BigDecimal p5000) {
		this.p5000 = p5000;
	}

	/**
	 * Gets the p6000.
	 *
	 * @return the p6000
	 */
	public BigDecimal getP6000() {
		return this.p6000;
	}

	/**
	 * Sets the p6000.
	 *
	 * @param p6000 the new p6000
	 */
	public void setP6000(BigDecimal p6000) {
		this.p6000 = p6000;
	}

	/**
	 * Gets the p7000.
	 *
	 * @return the p7000
	 */
	public BigDecimal getP7000() {
		return this.p7000;
	}

	/**
	 * Sets the p7000.
	 *
	 * @param p7000 the new p7000
	 */
	public void setP7000(BigDecimal p7000) {
		this.p7000 = p7000;
	}

	/**
	 * Gets the p8000.
	 *
	 * @return the p8000
	 */
	public BigDecimal getP8000() {
		return this.p8000;
	}

	/**
	 * Sets the p8000.
	 *
	 * @param p8000 the new p8000
	 */
	public void setP8000(BigDecimal p8000) {
		this.p8000 = p8000;
	}

	/**
	 * Gets the p9000.
	 *
	 * @return the p9000
	 */
	public BigDecimal getP9000() {
		return this.p9000;
	}

	/**
	 * Sets the p9000.
	 *
	 * @param p9000 the new p9000
	 */
	public void setP9000(BigDecimal p9000) {
		this.p9000 = p9000;
	}

	/**
	 * Gets the polclv 11.
	 *
	 * @return the polclv 11
	 */
	public String getPolclv11() {
		return this.polclv11;
	}

	/**
	 * Sets the polclv 11.
	 *
	 * @param polclv11 the new polclv 11
	 */
	public void setPolclv11(String polclv11) {
		this.polclv11 = polclv11;
	}

	/**
	 * Gets the polclv 12.
	 *
	 * @return the polclv 12
	 */
	public String getPolclv12() {
		return this.polclv2;
	}

	/**
	 * Sets the polclv 12.
	 *
	 * @param polclv12 the new polclv 12
	 */
	public void setPolclv12(String polclv12) {
		this.polclv12 = polclv12;
	}

	/**
	 * Gets the polclv 13.
	 *
	 * @return the polclv 13
	 */
	public String getPolclv13() {
		return this.polclv13;
	}

	/**
	 * Sets the polclv 13.
	 *
	 * @param polclv13 the new polclv 13
	 */
	public void setPolclv13(String polclv13) {
		this.polclv13 = polclv13;
	}

	/**
	 * Gets the polclv 1.
	 *
	 * @return the polclv 1
	 */
	public String getPolclv1() {
		return this.polclv1;
	}

	/**
	 * Sets the polclv 1.
	 *
	 * @param polclv1 the new polclv 1
	 */
	public void setPolclv1(String polclv1) {
		this.polclv1 = polclv1;
	}

	/**
	 * Gets the polclv 2.
	 *
	 * @return the polclv 2
	 */
	public String getPolclv2() {
		return this.polclv2;
	}

	/**
	 * Sets the polclv 2.
	 *
	 * @param polclv2 the new polclv 2
	 */
	public void setPolclv2(String polclv2) {
		this.polclv2 = polclv2;
	}

	/**
	 * Gets the polclv 3.
	 *
	 * @return the polclv 3
	 */
	public String getPolclv3() {
		return this.polclv3;
	}

	/**
	 * Sets the polclv 3.
	 *
	 * @param polclv3 the new polclv 3
	 */
	public void setPolclv3(String polclv3) {
		this.polclv3 = polclv3;
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
	 * Gets the staafe.
	 *
	 * @return the staafe
	 */
	public String getStaafe() {
		return this.staafe;
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
	 * Gets the staaut.
	 *
	 * @return the staaut
	 */
	public String getStaaut() {
		return this.staaut;
	}

	/**
	 * Sets the staaut.
	 *
	 * @param staaut the new staaut
	 */
	public void setStaaut(String staaut) {
		this.staaut = staaut;
	}

	/**
	 * Gets the stades.
	 *
	 * @return the stades
	 */
	public String getStades() {
		return this.stades;
	}

	/**
	 * Sets the stades.
	 *
	 * @param stades the new stades
	 */
	public void setStades(String stades) {
		this.stades = stades;
	}

	/**
	 * Gets the stapol.
	 *
	 * @return the stapol
	 */
	public String getStapol() {
		return this.stapol;
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

}