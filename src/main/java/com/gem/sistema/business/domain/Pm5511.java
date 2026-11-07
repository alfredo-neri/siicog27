package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM5511 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm5511.findAll", query="SELECT p FROM Pm5511 p")
public class Pm5511 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The adu. */
	private BigDecimal adu;

	/** The adv. */
	private BigDecimal adv;

	/** The aeb. */
	private BigDecimal aeb;

	/** The aep. */
	private BigDecimal aep;

	/** The aes. */
	private BigDecimal aes;

	/** The amer. */
	private BigDecimal amer;

	/** The aocr. */
	private BigDecimal aocr;

	/** The apis. */
	private BigDecimal apis;

	/** The cap. */
	private BigDecimal cap;

	/** The capturo. */
	private String capturo;

	/** The daj. */
	private BigDecimal daj;

	/** The ean. */
	private BigDecimal ean;

	/** The el. */
	private BigDecimal el;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The maoa. */
	private BigDecimal maoa;

	/** The mensual. */
	private Integer mensual;

	/** The mmme. */
	private BigDecimal mmme;

	/** The mnpob. */
	private BigDecimal mnpob;

	/** The mnpop. */
	private BigDecimal mnpop;

	/** The mnpos. */
	private BigDecimal mnpos;

	/** The mpv. */
	private BigDecimal mpv;

	/** The mt. */
	private BigDecimal mt;

	/** The mtam. */
	private BigDecimal mtam;

	/** The obf. */
	private BigDecimal obf;

	/** The obs. */
	private String obs;

	/** The obsgral. */
	private String obsgral;

	/** The otros. */
	private BigDecimal otros;

	/** The pac. */
	private BigDecimal pac;

	/** The padu. */
	private BigDecimal padu;

	/** The padv. */
	private BigDecimal padv;

	/** The paeb. */
	private BigDecimal paeb;

	/** The paep. */
	private BigDecimal paep;

	/** The paes. */
	private BigDecimal paes;

	/** The pamer. */
	private BigDecimal pamer;

	/** The paocr. */
	private BigDecimal paocr;

	/** The papis. */
	private BigDecimal papis;

	/** The pcap. */
	private BigDecimal pcap;

	/** The pdaj. */
	private BigDecimal pdaj;

	/** The pean. */
	private BigDecimal pean;

	/** The pel. */
	private BigDecimal pel;

	/** The pmnpob. */
	private BigDecimal pmnpob;

	/** The pmnpop. */
	private BigDecimal pmnpop;

	/** The pmnpos. */
	private BigDecimal pmnpos;

	/** The pmpv. */
	private BigDecimal pmpv;

	/** The pmt. */
	private BigDecimal pmt;

	/** The pobf. */
	private BigDecimal pobf;

	/** The podc. */
	private BigDecimal podc;

	/** The podcs. */
	private BigDecimal podcs;

	/** The potros. */
	private BigDecimal potros;

	/** The ppac. */
	private BigDecimal ppac;

	/** The ppea. */
	private BigDecimal ppea;

	/** The ppodc. */
	private BigDecimal ppodc;

	/** The ppodcs. */
	private BigDecimal ppodcs;

	/** The pppea. */
	private BigDecimal pppea;

	/** The psppc. */
	private BigDecimal psppc;

	/** The psubb. */
	private BigDecimal psubb;

	/** The psubinf. */
	private BigDecimal psubinf;

	/** The psubof. */
	private BigDecimal psubof;

	/** The psubot. */
	private BigDecimal psubot;

	/** The psubsp. */
	private BigDecimal psubsp;

	/** The sppc. */
	private BigDecimal sppc;

	/** The subb. */
	private BigDecimal subb;

	/** The subinf. */
	private BigDecimal subinf;

	/** The subof. */
	private BigDecimal subof;

	/** The subot. */
	private BigDecimal subot;

	/** The subsp. */
	private BigDecimal subsp;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 5511.
	 */
	public Pm5511() {
	}


	/**
	 * Gets the adu.
	 *
	 * @return the adu
	 */
	public BigDecimal getAdu() {
		return this.adu;
	}

	/**
	 * Sets the adu.
	 *
	 * @param adu the new adu
	 */
	public void setAdu(BigDecimal adu) {
		this.adu = adu;
	}

	/**
	 * Gets the adv.
	 *
	 * @return the adv
	 */
	public BigDecimal getAdv() {
		return this.adv;
	}

	/**
	 * Sets the adv.
	 *
	 * @param adv the new adv
	 */
	public void setAdv(BigDecimal adv) {
		this.adv = adv;
	}

	/**
	 * Gets the aeb.
	 *
	 * @return the aeb
	 */
	public BigDecimal getAeb() {
		return this.aeb;
	}

	/**
	 * Sets the aeb.
	 *
	 * @param aeb the new aeb
	 */
	public void setAeb(BigDecimal aeb) {
		this.aeb = aeb;
	}

	/**
	 * Gets the aep.
	 *
	 * @return the aep
	 */
	public BigDecimal getAep() {
		return this.aep;
	}

	/**
	 * Sets the aep.
	 *
	 * @param aep the new aep
	 */
	public void setAep(BigDecimal aep) {
		this.aep = aep;
	}

	/**
	 * Gets the aes.
	 *
	 * @return the aes
	 */
	public BigDecimal getAes() {
		return this.aes;
	}

	/**
	 * Sets the aes.
	 *
	 * @param aes the new aes
	 */
	public void setAes(BigDecimal aes) {
		this.aes = aes;
	}

	/**
	 * Gets the amer.
	 *
	 * @return the amer
	 */
	public BigDecimal getAmer() {
		return this.amer;
	}

	/**
	 * Sets the amer.
	 *
	 * @param amer the new amer
	 */
	public void setAmer(BigDecimal amer) {
		this.amer = amer;
	}

	/**
	 * Gets the aocr.
	 *
	 * @return the aocr
	 */
	public BigDecimal getAocr() {
		return this.aocr;
	}

	/**
	 * Sets the aocr.
	 *
	 * @param aocr the new aocr
	 */
	public void setAocr(BigDecimal aocr) {
		this.aocr = aocr;
	}

	/**
	 * Gets the apis.
	 *
	 * @return the apis
	 */
	public BigDecimal getApis() {
		return this.apis;
	}

	/**
	 * Sets the apis.
	 *
	 * @param apis the new apis
	 */
	public void setApis(BigDecimal apis) {
		this.apis = apis;
	}

	/**
	 * Gets the cap.
	 *
	 * @return the cap
	 */
	public BigDecimal getCap() {
		return this.cap;
	}

	/**
	 * Sets the cap.
	 *
	 * @param cap the new cap
	 */
	public void setCap(BigDecimal cap) {
		this.cap = cap;
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
	 * Gets the daj.
	 *
	 * @return the daj
	 */
	public BigDecimal getDaj() {
		return this.daj;
	}

	/**
	 * Sets the daj.
	 *
	 * @param daj the new daj
	 */
	public void setDaj(BigDecimal daj) {
		this.daj = daj;
	}

	/**
	 * Gets the ean.
	 *
	 * @return the ean
	 */
	public BigDecimal getEan() {
		return this.ean;
	}

	/**
	 * Sets the ean.
	 *
	 * @param ean the new ean
	 */
	public void setEan(BigDecimal ean) {
		this.ean = ean;
	}

	/**
	 * Gets the el.
	 *
	 * @return the el
	 */
	public BigDecimal getEl() {
		return this.el;
	}

	/**
	 * Sets the el.
	 *
	 * @param el the new el
	 */
	public void setEl(BigDecimal el) {
		this.el = el;
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
	 * Gets the maoa.
	 *
	 * @return the maoa
	 */
	public BigDecimal getMaoa() {
		return this.maoa;
	}

	/**
	 * Sets the maoa.
	 *
	 * @param maoa the new maoa
	 */
	public void setMaoa(BigDecimal maoa) {
		this.maoa = maoa;
	}

	/**
	 * Gets the mensual.
	 *
	 * @return the mensual
	 */
	public Integer getMensual() {
		return this.mensual;
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
	 * Gets the mmme.
	 *
	 * @return the mmme
	 */
	public BigDecimal getMmme() {
		return this.mmme;
	}

	/**
	 * Sets the mmme.
	 *
	 * @param mmme the new mmme
	 */
	public void setMmme(BigDecimal mmme) {
		this.mmme = mmme;
	}

	/**
	 * Gets the mnpob.
	 *
	 * @return the mnpob
	 */
	public BigDecimal getMnpob() {
		return this.mnpob;
	}

	/**
	 * Sets the mnpob.
	 *
	 * @param mnpob the new mnpob
	 */
	public void setMnpob(BigDecimal mnpob) {
		this.mnpob = mnpob;
	}

	/**
	 * Gets the mnpop.
	 *
	 * @return the mnpop
	 */
	public BigDecimal getMnpop() {
		return this.mnpop;
	}

	/**
	 * Sets the mnpop.
	 *
	 * @param mnpop the new mnpop
	 */
	public void setMnpop(BigDecimal mnpop) {
		this.mnpop = mnpop;
	}

	/**
	 * Gets the mnpos.
	 *
	 * @return the mnpos
	 */
	public BigDecimal getMnpos() {
		return this.mnpos;
	}

	/**
	 * Sets the mnpos.
	 *
	 * @param mnpos the new mnpos
	 */
	public void setMnpos(BigDecimal mnpos) {
		this.mnpos = mnpos;
	}

	/**
	 * Gets the mpv.
	 *
	 * @return the mpv
	 */
	public BigDecimal getMpv() {
		return this.mpv;
	}

	/**
	 * Sets the mpv.
	 *
	 * @param mpv the new mpv
	 */
	public void setMpv(BigDecimal mpv) {
		this.mpv = mpv;
	}

	/**
	 * Gets the mt.
	 *
	 * @return the mt
	 */
	public BigDecimal getMt() {
		return this.mt;
	}

	/**
	 * Sets the mt.
	 *
	 * @param mt the new mt
	 */
	public void setMt(BigDecimal mt) {
		this.mt = mt;
	}

	/**
	 * Gets the mtam.
	 *
	 * @return the mtam
	 */
	public BigDecimal getMtam() {
		return this.mtam;
	}

	/**
	 * Sets the mtam.
	 *
	 * @param mtam the new mtam
	 */
	public void setMtam(BigDecimal mtam) {
		this.mtam = mtam;
	}

	/**
	 * Gets the obf.
	 *
	 * @return the obf
	 */
	public BigDecimal getObf() {
		return this.obf;
	}

	/**
	 * Sets the obf.
	 *
	 * @param obf the new obf
	 */
	public void setObf(BigDecimal obf) {
		this.obf = obf;
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
	 * Gets the obsgral.
	 *
	 * @return the obsgral
	 */
	public String getObsgral() {
		return this.obsgral;
	}

	/**
	 * Sets the obsgral.
	 *
	 * @param obsgral the new obsgral
	 */
	public void setObsgral(String obsgral) {
		this.obsgral = obsgral;
	}

	/**
	 * Gets the otros.
	 *
	 * @return the otros
	 */
	public BigDecimal getOtros() {
		return this.otros;
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
	 * Gets the pac.
	 *
	 * @return the pac
	 */
	public BigDecimal getPac() {
		return this.pac;
	}

	/**
	 * Sets the pac.
	 *
	 * @param pac the new pac
	 */
	public void setPac(BigDecimal pac) {
		this.pac = pac;
	}

	/**
	 * Gets the padu.
	 *
	 * @return the padu
	 */
	public BigDecimal getPadu() {
		return this.padu;
	}

	/**
	 * Sets the padu.
	 *
	 * @param padu the new padu
	 */
	public void setPadu(BigDecimal padu) {
		this.padu = padu;
	}

	/**
	 * Gets the padv.
	 *
	 * @return the padv
	 */
	public BigDecimal getPadv() {
		return this.padv;
	}

	/**
	 * Sets the padv.
	 *
	 * @param padv the new padv
	 */
	public void setPadv(BigDecimal padv) {
		this.padv = padv;
	}

	/**
	 * Gets the paeb.
	 *
	 * @return the paeb
	 */
	public BigDecimal getPaeb() {
		return this.paeb;
	}

	/**
	 * Sets the paeb.
	 *
	 * @param paeb the new paeb
	 */
	public void setPaeb(BigDecimal paeb) {
		this.paeb = paeb;
	}

	/**
	 * Gets the paep.
	 *
	 * @return the paep
	 */
	public BigDecimal getPaep() {
		return this.paep;
	}

	/**
	 * Sets the paep.
	 *
	 * @param paep the new paep
	 */
	public void setPaep(BigDecimal paep) {
		this.paep = paep;
	}

	/**
	 * Gets the paes.
	 *
	 * @return the paes
	 */
	public BigDecimal getPaes() {
		return this.paes;
	}

	/**
	 * Sets the paes.
	 *
	 * @param paes the new paes
	 */
	public void setPaes(BigDecimal paes) {
		this.paes = paes;
	}

	/**
	 * Gets the pamer.
	 *
	 * @return the pamer
	 */
	public BigDecimal getPamer() {
		return this.pamer;
	}

	/**
	 * Sets the pamer.
	 *
	 * @param pamer the new pamer
	 */
	public void setPamer(BigDecimal pamer) {
		this.pamer = pamer;
	}

	/**
	 * Gets the paocr.
	 *
	 * @return the paocr
	 */
	public BigDecimal getPaocr() {
		return this.paocr;
	}

	/**
	 * Sets the paocr.
	 *
	 * @param paocr the new paocr
	 */
	public void setPaocr(BigDecimal paocr) {
		this.paocr = paocr;
	}

	/**
	 * Gets the papis.
	 *
	 * @return the papis
	 */
	public BigDecimal getPapis() {
		return this.papis;
	}

	/**
	 * Sets the papis.
	 *
	 * @param papis the new papis
	 */
	public void setPapis(BigDecimal papis) {
		this.papis = papis;
	}

	/**
	 * Gets the pcap.
	 *
	 * @return the pcap
	 */
	public BigDecimal getPcap() {
		return this.pcap;
	}

	/**
	 * Sets the pcap.
	 *
	 * @param pcap the new pcap
	 */
	public void setPcap(BigDecimal pcap) {
		this.pcap = pcap;
	}

	/**
	 * Gets the pdaj.
	 *
	 * @return the pdaj
	 */
	public BigDecimal getPdaj() {
		return this.pdaj;
	}

	/**
	 * Sets the pdaj.
	 *
	 * @param pdaj the new pdaj
	 */
	public void setPdaj(BigDecimal pdaj) {
		this.pdaj = pdaj;
	}

	/**
	 * Gets the pean.
	 *
	 * @return the pean
	 */
	public BigDecimal getPean() {
		return this.pean;
	}

	/**
	 * Sets the pean.
	 *
	 * @param pean the new pean
	 */
	public void setPean(BigDecimal pean) {
		this.pean = pean;
	}

	/**
	 * Gets the pel.
	 *
	 * @return the pel
	 */
	public BigDecimal getPel() {
		return this.pel;
	}

	/**
	 * Sets the pel.
	 *
	 * @param pel the new pel
	 */
	public void setPel(BigDecimal pel) {
		this.pel = pel;
	}

	/**
	 * Gets the pmnpob.
	 *
	 * @return the pmnpob
	 */
	public BigDecimal getPmnpob() {
		return this.pmnpob;
	}

	/**
	 * Sets the pmnpob.
	 *
	 * @param pmnpob the new pmnpob
	 */
	public void setPmnpob(BigDecimal pmnpob) {
		this.pmnpob = pmnpob;
	}

	/**
	 * Gets the pmnpop.
	 *
	 * @return the pmnpop
	 */
	public BigDecimal getPmnpop() {
		return this.pmnpop;
	}

	/**
	 * Sets the pmnpop.
	 *
	 * @param pmnpop the new pmnpop
	 */
	public void setPmnpop(BigDecimal pmnpop) {
		this.pmnpop = pmnpop;
	}

	/**
	 * Gets the pmnpos.
	 *
	 * @return the pmnpos
	 */
	public BigDecimal getPmnpos() {
		return this.pmnpos;
	}

	/**
	 * Sets the pmnpos.
	 *
	 * @param pmnpos the new pmnpos
	 */
	public void setPmnpos(BigDecimal pmnpos) {
		this.pmnpos = pmnpos;
	}

	/**
	 * Gets the pmpv.
	 *
	 * @return the pmpv
	 */
	public BigDecimal getPmpv() {
		return this.pmpv;
	}

	/**
	 * Sets the pmpv.
	 *
	 * @param pmpv the new pmpv
	 */
	public void setPmpv(BigDecimal pmpv) {
		this.pmpv = pmpv;
	}

	/**
	 * Gets the pmt.
	 *
	 * @return the pmt
	 */
	public BigDecimal getPmt() {
		return this.pmt;
	}

	/**
	 * Sets the pmt.
	 *
	 * @param pmt the new pmt
	 */
	public void setPmt(BigDecimal pmt) {
		this.pmt = pmt;
	}

	/**
	 * Gets the pobf.
	 *
	 * @return the pobf
	 */
	public BigDecimal getPobf() {
		return this.pobf;
	}

	/**
	 * Sets the pobf.
	 *
	 * @param pobf the new pobf
	 */
	public void setPobf(BigDecimal pobf) {
		this.pobf = pobf;
	}

	/**
	 * Gets the podc.
	 *
	 * @return the podc
	 */
	public BigDecimal getPodc() {
		return this.podc;
	}

	/**
	 * Sets the podc.
	 *
	 * @param podc the new podc
	 */
	public void setPodc(BigDecimal podc) {
		this.podc = podc;
	}

	/**
	 * Gets the podcs.
	 *
	 * @return the podcs
	 */
	public BigDecimal getPodcs() {
		return this.podcs;
	}

	/**
	 * Sets the podcs.
	 *
	 * @param podcs the new podcs
	 */
	public void setPodcs(BigDecimal podcs) {
		this.podcs = podcs;
	}

	/**
	 * Gets the potros.
	 *
	 * @return the potros
	 */
	public BigDecimal getPotros() {
		return this.potros;
	}

	/**
	 * Sets the potros.
	 *
	 * @param potros the new potros
	 */
	public void setPotros(BigDecimal potros) {
		this.potros = potros;
	}

	/**
	 * Gets the ppac.
	 *
	 * @return the ppac
	 */
	public BigDecimal getPpac() {
		return this.ppac;
	}

	/**
	 * Sets the ppac.
	 *
	 * @param ppac the new ppac
	 */
	public void setPpac(BigDecimal ppac) {
		this.ppac = ppac;
	}

	/**
	 * Gets the ppea.
	 *
	 * @return the ppea
	 */
	public BigDecimal getPpea() {
		return this.ppea;
	}

	/**
	 * Sets the ppea.
	 *
	 * @param ppea the new ppea
	 */
	public void setPpea(BigDecimal ppea) {
		this.ppea = ppea;
	}

	/**
	 * Gets the ppodc.
	 *
	 * @return the ppodc
	 */
	public BigDecimal getPpodc() {
		return this.ppodc;
	}

	/**
	 * Sets the ppodc.
	 *
	 * @param ppodc the new ppodc
	 */
	public void setPpodc(BigDecimal ppodc) {
		this.ppodc = ppodc;
	}

	/**
	 * Gets the ppodcs.
	 *
	 * @return the ppodcs
	 */
	public BigDecimal getPpodcs() {
		return this.ppodcs;
	}

	/**
	 * Sets the ppodcs.
	 *
	 * @param ppodcs the new ppodcs
	 */
	public void setPpodcs(BigDecimal ppodcs) {
		this.ppodcs = ppodcs;
	}

	/**
	 * Gets the pppea.
	 *
	 * @return the pppea
	 */
	public BigDecimal getPppea() {
		return this.pppea;
	}

	/**
	 * Sets the pppea.
	 *
	 * @param pppea the new pppea
	 */
	public void setPppea(BigDecimal pppea) {
		this.pppea = pppea;
	}

	/**
	 * Gets the psppc.
	 *
	 * @return the psppc
	 */
	public BigDecimal getPsppc() {
		return this.psppc;
	}

	/**
	 * Sets the psppc.
	 *
	 * @param psppc the new psppc
	 */
	public void setPsppc(BigDecimal psppc) {
		this.psppc = psppc;
	}

	/**
	 * Gets the psubb.
	 *
	 * @return the psubb
	 */
	public BigDecimal getPsubb() {
		return this.psubb;
	}

	/**
	 * Sets the psubb.
	 *
	 * @param psubb the new psubb
	 */
	public void setPsubb(BigDecimal psubb) {
		this.psubb = psubb;
	}

	/**
	 * Gets the psubinf.
	 *
	 * @return the psubinf
	 */
	public BigDecimal getPsubinf() {
		return this.psubinf;
	}

	/**
	 * Sets the psubinf.
	 *
	 * @param psubinf the new psubinf
	 */
	public void setPsubinf(BigDecimal psubinf) {
		this.psubinf = psubinf;
	}

	/**
	 * Gets the psubof.
	 *
	 * @return the psubof
	 */
	public BigDecimal getPsubof() {
		return this.psubof;
	}

	/**
	 * Sets the psubof.
	 *
	 * @param psubof the new psubof
	 */
	public void setPsubof(BigDecimal psubof) {
		this.psubof = psubof;
	}

	/**
	 * Gets the psubot.
	 *
	 * @return the psubot
	 */
	public BigDecimal getPsubot() {
		return this.psubot;
	}

	/**
	 * Sets the psubot.
	 *
	 * @param psubot the new psubot
	 */
	public void setPsubot(BigDecimal psubot) {
		this.psubot = psubot;
	}

	/**
	 * Gets the psubsp.
	 *
	 * @return the psubsp
	 */
	public BigDecimal getPsubsp() {
		return this.psubsp;
	}

	/**
	 * Sets the psubsp.
	 *
	 * @param psubsp the new psubsp
	 */
	public void setPsubsp(BigDecimal psubsp) {
		this.psubsp = psubsp;
	}

	/**
	 * Gets the sppc.
	 *
	 * @return the sppc
	 */
	public BigDecimal getSppc() {
		return this.sppc;
	}

	/**
	 * Sets the sppc.
	 *
	 * @param sppc the new sppc
	 */
	public void setSppc(BigDecimal sppc) {
		this.sppc = sppc;
	}

	/**
	 * Gets the subb.
	 *
	 * @return the subb
	 */
	public BigDecimal getSubb() {
		return this.subb;
	}

	/**
	 * Sets the subb.
	 *
	 * @param subb the new subb
	 */
	public void setSubb(BigDecimal subb) {
		this.subb = subb;
	}

	/**
	 * Gets the subinf.
	 *
	 * @return the subinf
	 */
	public BigDecimal getSubinf() {
		return this.subinf;
	}

	/**
	 * Sets the subinf.
	 *
	 * @param subinf the new subinf
	 */
	public void setSubinf(BigDecimal subinf) {
		this.subinf = subinf;
	}

	/**
	 * Gets the subof.
	 *
	 * @return the subof
	 */
	public BigDecimal getSubof() {
		return this.subof;
	}

	/**
	 * Sets the subof.
	 *
	 * @param subof the new subof
	 */
	public void setSubof(BigDecimal subof) {
		this.subof = subof;
	}

	/**
	 * Gets the subot.
	 *
	 * @return the subot
	 */
	public BigDecimal getSubot() {
		return this.subot;
	}

	/**
	 * Sets the subot.
	 *
	 * @param subot the new subot
	 */
	public void setSubot(BigDecimal subot) {
		this.subot = subot;
	}

	/**
	 * Gets the subsp.
	 *
	 * @return the subsp
	 */
	public BigDecimal getSubsp() {
		return this.subsp;
	}

	/**
	 * Sets the subsp.
	 *
	 * @param subsp the new subsp
	 */
	public void setSubsp(BigDecimal subsp) {
		this.subsp = subsp;
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