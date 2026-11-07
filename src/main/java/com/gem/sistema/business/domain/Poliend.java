package com.gem.sistema.business.domain;

// TODO: Auto-generated Javadoc
/**
 * The Class Poliend.
 */
public class Poliend {

	/** The tippol. */
	private String tippol;

	/** The mespol. */
	private Integer mespol;

	/** The conpol. */
	private Integer conpol;

	/** The ccontro. */
	private Double ccontro;

	/** The sdebe. */
	private Double sdebe;

	/** The shaber. */
	private Double shaber;

	/** The ctc 6000. */
	private Double ctc6000;

	/** The cta 6000. */
	private Double cta6000;

	private String cappol;

	/**
	 * Instantiates a new poliend.
	 */
	public Poliend() {
	}

	/**
	 * Instantiates a new poliend.
	 *
	 * @param tippol  the tippol
	 * @param mespol  the mespol
	 * @param conpol  the conpol
	 * @param ccontro the ccontro
	 * @param sdebe   the sdebe
	 * @param shaber  the shaber
	 * @param ctc6000 the ctc 6000
	 * @param cta6000 the cta 6000
	 */
	public Poliend(String tippol, Integer mespol, Integer conpol, Double ccontro, Double sdebe, Double shaber,
			Double ctc6000, Double cta6000) {
		this.tippol = tippol;
		this.mespol = mespol;
		this.conpol = conpol;
		this.ccontro = ccontro;
		this.sdebe = sdebe;
		this.shaber = shaber;
		this.ctc6000 = ctc6000;
		this.cta6000 = cta6000;
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
	 * Gets the mespol.
	 *
	 * @return the mespol
	 */
	public Integer getMespol() {
		return mespol;
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
	 * Gets the ccontro.
	 *
	 * @return the ccontro
	 */
	public Double getCcontro() {
		return ccontro;
	}

	/**
	 * Sets the ccontro.
	 *
	 * @param ccontro the new ccontro
	 */
	public void setCcontro(Double ccontro) {
		this.ccontro = ccontro;
	}

	/**
	 * Gets the sdebe.
	 *
	 * @return the sdebe
	 */
	public Double getSdebe() {
		return sdebe;
	}

	/**
	 * Sets the sdebe.
	 *
	 * @param sdebe the new sdebe
	 */
	public void setSdebe(Double sdebe) {
		this.sdebe = sdebe;
	}

	/**
	 * Gets the shaber.
	 *
	 * @return the shaber
	 */
	public Double getShaber() {
		return shaber;
	}

	/**
	 * Sets the shaber.
	 *
	 * @param shaber the new shaber
	 */
	public void setShaber(Double shaber) {
		this.shaber = shaber;
	}

	/**
	 * Gets the ctc 6000.
	 *
	 * @return the ctc 6000
	 */
	public Double getCtc6000() {
		return ctc6000;
	}

	/**
	 * Sets the ctc 6000.
	 *
	 * @param ctc6000 the new ctc 6000
	 */
	public void setCtc6000(Double ctc6000) {
		this.ctc6000 = ctc6000;
	}

	/**
	 * Gets the cta 6000.
	 *
	 * @return the cta 6000
	 */
	public Double getCta6000() {
		return cta6000;
	}

	/**
	 * Sets the cta 6000.
	 *
	 * @param cta6000 the new cta 6000
	 */
	public void setCta6000(Double cta6000) {
		this.cta6000 = cta6000;
	}

	public String getCappol() {
		return cappol;
	}

	public void setCappol(String cappol) {
		this.cappol = cappol;
	}

}
