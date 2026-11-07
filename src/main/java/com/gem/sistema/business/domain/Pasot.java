package com.gem.sistema.business.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PASOT database table.
 *
 */
@Entity
@NamedQuery(name="Pasot.findAll", query="SELECT p FROM Pasot p")
public class Pasot extends AbstractEntity  {

	/** The ampli 11. */
	@Column(name="AMPLI1_1")
	private BigDecimal ampli11;

	/** The ampli 110. */
	@Column(name="AMPLI1_10")
	private BigDecimal ampli110;

	/** The ampli 111. */
	@Column(name="AMPLI1_11")
	private BigDecimal ampli111;

	/** The ampli 112. */
	@Column(name="AMPLI1_12")
	private BigDecimal ampli112;

	/** The ampli 113. */
	@Column(name="AMPLI1_13")
	private BigDecimal ampli113;

	/** The ampli 12. */
	@Column(name="AMPLI1_2")
	private BigDecimal ampli12;

	/** The ampli 13. */
	@Column(name="AMPLI1_3")
	private BigDecimal ampli13;

	/** The ampli 14. */
	@Column(name="AMPLI1_4")
	private BigDecimal ampli14;

	/** The ampli 15. */
	@Column(name="AMPLI1_5")
	private BigDecimal ampli15;

	/** The ampli 16. */
	@Column(name="AMPLI1_6")
	private BigDecimal ampli16;

	/** The ampli 17. */
	@Column(name="AMPLI1_7")
	private BigDecimal ampli17;

	/** The ampli 18. */
	@Column(name="AMPLI1_8")
	private BigDecimal ampli18;

	/** The ampli 19. */
	@Column(name="AMPLI1_9")
	private BigDecimal ampli19;

	/** The auto. */
	private BigDecimal auto;

	/** The auto 11. */
	@Column(name="AUTO1_1")
	private BigDecimal auto11;

	/** The auto 110. */
	@Column(name="AUTO1_10")
	private BigDecimal auto110;

	/** The auto 111. */
	@Column(name="AUTO1_11")
	private BigDecimal auto111;

	/** The auto 112. */
	@Column(name="AUTO1_12")
	private BigDecimal auto112;

	/** The auto 113. */
	@Column(name="AUTO1_13")
	private BigDecimal auto113;

	/** The auto 12. */
	@Column(name="AUTO1_2")
	private BigDecimal auto12;

	/** The auto 13. */
	@Column(name="AUTO1_3")
	private BigDecimal auto13;

	/** The auto 14. */
	@Column(name="AUTO1_4")
	private BigDecimal auto14;

	/** The auto 15. */
	@Column(name="AUTO1_5")
	private BigDecimal auto15;

	/** The auto 16. */
	@Column(name="AUTO1_6")
	private BigDecimal auto16;

	/** The auto 17. */
	@Column(name="AUTO1_7")
	private BigDecimal auto17;

	/** The auto 18. */
	@Column(name="AUTO1_8")
	private BigDecimal auto18;

	/** The auto 19. */
	@Column(name="AUTO1_9")
	private BigDecimal auto19;

	/** The ejpa. */
	private BigDecimal ejpa;

	/** The ejpa 11. */
	@Column(name="EJPA1_1")
	private BigDecimal ejpa11;

	/** The ejpa 110. */
	@Column(name="EJPA1_10")
	private BigDecimal ejpa110;

	/** The ejpa 111. */
	@Column(name="EJPA1_11")
	private BigDecimal ejpa111;

	/** The ejpa 112. */
	@Column(name="EJPA1_12")
	private BigDecimal ejpa112;

	/** The ejpa 113. */
	@Column(name="EJPA1_13")
	private BigDecimal ejpa113;

	/** The ejpa 12. */
	@Column(name="EJPA1_2")
	private BigDecimal ejpa12;

	/** The ejpa 13. */
	@Column(name="EJPA1_3")
	private BigDecimal ejpa13;

	/** The ejpa 14. */
	@Column(name="EJPA1_4")
	private BigDecimal ejpa14;

	/** The ejpa 15. */
	@Column(name="EJPA1_5")
	private BigDecimal ejpa15;

	/** The ejpa 16. */
	@Column(name="EJPA1_6")
	private BigDecimal ejpa16;

	/** The ejpa 17. */
	@Column(name="EJPA1_7")
	private BigDecimal ejpa17;

	/** The ejpa 18. */
	@Column(name="EJPA1_8")
	private BigDecimal ejpa18;

	/** The ejpa 19. */
	@Column(name="EJPA1_9")
	private BigDecimal ejpa19;

	/** The ejxpa. */
	private BigDecimal ejxpa;

	/** The ejxpa 11. */
	@Column(name="EJXPA1_1")
	private BigDecimal ejxpa11;

	/** The ejxpa 110. */
	@Column(name="EJXPA1_10")
	private BigDecimal ejxpa110;

	/** The ejxpa 111. */
	@Column(name="EJXPA1_11")
	private BigDecimal ejxpa111;

	/** The ejxpa 112. */
	@Column(name="EJXPA1_12")
	private BigDecimal ejxpa112;

	/** The ejxpa 113. */
	@Column(name="EJXPA1_13")
	private BigDecimal ejxpa113;

	/** The ejxpa 12. */
	@Column(name="EJXPA1_2")
	private BigDecimal ejxpa12;

	/** The ejxpa 13. */
	@Column(name="EJXPA1_3")
	private BigDecimal ejxpa13;

	/** The ejxpa 14. */
	@Column(name="EJXPA1_4")
	private BigDecimal ejxpa14;

	/** The ejxpa 15. */
	@Column(name="EJXPA1_5")
	private BigDecimal ejxpa15;

	/** The ejxpa 16. */
	@Column(name="EJXPA1_6")
	private BigDecimal ejxpa16;

	/** The ejxpa 17. */
	@Column(name="EJXPA1_7")
	private BigDecimal ejxpa17;

	/** The ejxpa 18. */
	@Column(name="EJXPA1_8")
	private BigDecimal ejxpa18;

	/** The ejxpa 19. */
	@Column(name="EJXPA1_9")
	private BigDecimal ejxpa19;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

  /** The idsector. */
  @Column(name = "IDSECTOR")
	private Integer idsector;

  /** The partida. */
  @Column(name = "PARTIDA")
	private String partida;

	/** The porpa. */
	private BigDecimal porpa;

	/** The porpa 11. */
	@Column(name="PORPA1_1")
	private BigDecimal porpa11;

	/** The porpa 110. */
	@Column(name="PORPA1_10")
	private BigDecimal porpa110;

	/** The porpa 111. */
	@Column(name="PORPA1_11")
	private BigDecimal porpa111;

	/** The porpa 112. */
	@Column(name="PORPA1_12")
	private BigDecimal porpa112;

	/** The porpa 113. */
	@Column(name="PORPA1_13")
	private BigDecimal porpa113;

	/** The porpa 12. */
	@Column(name="PORPA1_2")
	private BigDecimal porpa12;

	/** The porpa 13. */
	@Column(name="PORPA1_3")
	private BigDecimal porpa13;

	/** The porpa 14. */
	@Column(name="PORPA1_4")
	private BigDecimal porpa14;

	/** The porpa 15. */
	@Column(name="PORPA1_5")
	private BigDecimal porpa15;

	/** The porpa 16. */
	@Column(name="PORPA1_6")
	private BigDecimal porpa16;

	/** The porpa 17. */
	@Column(name="PORPA1_7")
	private BigDecimal porpa17;

	/** The porpa 18. */
	@Column(name="PORPA1_8")
	private BigDecimal porpa18;

	/** The porpa 19. */
	@Column(name="PORPA1_9")
	private BigDecimal porpa19;

	/** The redu 11. */
	@Column(name="REDU1_1")
	private BigDecimal redu11;

	/** The redu 110. */
	@Column(name="REDU1_10")
	private BigDecimal redu110;

	/** The redu 111. */
	@Column(name="REDU1_11")
	private BigDecimal redu111;

	/** The redu 112. */
	@Column(name="REDU1_12")
	private BigDecimal redu112;

	/** The redu 113. */
	@Column(name="REDU1_13")
	private BigDecimal redu113;

	/** The redu 12. */
	@Column(name="REDU1_2")
	private BigDecimal redu12;

	/** The redu 13. */
	@Column(name="REDU1_3")
	private BigDecimal redu13;

	/** The redu 14. */
	@Column(name="REDU1_4")
	private BigDecimal redu14;

	/** The redu 15. */
	@Column(name="REDU1_5")
	private BigDecimal redu15;

	/** The redu 16. */
	@Column(name="REDU1_6")
	private BigDecimal redu16;

	/** The redu 17. */
	@Column(name="REDU1_7")
	private BigDecimal redu17;

	/** The redu 18. */
	@Column(name="REDU1_8")
	private BigDecimal redu18;

	/** The redu 19. */
	@Column(name="REDU1_9")
	private BigDecimal redu19;

	/** The toeje. */
	private BigDecimal toeje;

	/** The toeje 11. */
	@Column(name="TOEJE1_1")
	private BigDecimal toeje11;

	/** The toeje 110. */
	@Column(name="TOEJE1_10")
	private BigDecimal toeje110;

	/** The toeje 111. */
	@Column(name="TOEJE1_11")
	private BigDecimal toeje111;

	/** The toeje 112. */
	@Column(name="TOEJE1_12")
	private BigDecimal toeje112;

	/** The toeje 113. */
	@Column(name="TOEJE1_13")
	private BigDecimal toeje113;

	/** The toeje 12. */
	@Column(name="TOEJE1_2")
	private BigDecimal toeje12;

	/** The toeje 13. */
	@Column(name="TOEJE1_3")
	private BigDecimal toeje13;

	/** The toeje 14. */
	@Column(name="TOEJE1_4")
	private BigDecimal toeje14;

	/** The toeje 15. */
	@Column(name="TOEJE1_5")
	private BigDecimal toeje15;

	/** The toeje 16. */
	@Column(name="TOEJE1_6")
	private BigDecimal toeje16;

	/** The toeje 17. */
	@Column(name="TOEJE1_7")
	private BigDecimal toeje17;

	/** The toeje 18. */
	@Column(name="TOEJE1_8")
	private BigDecimal toeje18;

	/** The toeje 19. */
	@Column(name="TOEJE1_9")
	private BigDecimal toeje19;

	/** The userid. */
	@Column(name="\"USERID\"")
	private String userid;

	/**
	 * Instantiates a new pasot.
	 */
	public Pasot() {
	}

	/**
	 * Gets the ampli 11.
	 *
	 * @return the ampli 11
	 */
	public BigDecimal getAmpli11() {
		return this.ampli11;
	}

	/**
	 * Sets the ampli 11.
	 *
	 * @param ampli11 the new ampli 11
	 */
	public void setAmpli11(BigDecimal ampli11) {
		this.ampli11 = ampli11;
	}

	/**
	 * Gets the ampli 110.
	 *
	 * @return the ampli 110
	 */
	public BigDecimal getAmpli110() {
		return this.ampli110;
	}

	/**
	 * Sets the ampli 110.
	 *
	 * @param ampli110 the new ampli 110
	 */
	public void setAmpli110(BigDecimal ampli110) {
		this.ampli110 = ampli110;
	}

	/**
	 * Gets the ampli 111.
	 *
	 * @return the ampli 111
	 */
	public BigDecimal getAmpli111() {
		return this.ampli111;
	}

	/**
	 * Sets the ampli 111.
	 *
	 * @param ampli111 the new ampli 111
	 */
	public void setAmpli111(BigDecimal ampli111) {
		this.ampli111 = ampli111;
	}

	/**
	 * Gets the ampli 112.
	 *
	 * @return the ampli 112
	 */
	public BigDecimal getAmpli112() {
		return this.ampli112;
	}

	/**
	 * Sets the ampli 112.
	 *
	 * @param ampli112 the new ampli 112
	 */
	public void setAmpli112(BigDecimal ampli112) {
		this.ampli112 = ampli112;
	}

	/**
	 * Gets the ampli 113.
	 *
	 * @return the ampli 113
	 */
	public BigDecimal getAmpli113() {
		return this.ampli113;
	}

	/**
	 * Sets the ampli 113.
	 *
	 * @param ampli113 the new ampli 113
	 */
	public void setAmpli113(BigDecimal ampli113) {
		this.ampli113 = ampli113;
	}

	/**
	 * Gets the ampli 12.
	 *
	 * @return the ampli 12
	 */
	public BigDecimal getAmpli12() {
		return this.ampli12;
	}

	/**
	 * Sets the ampli 12.
	 *
	 * @param ampli12 the new ampli 12
	 */
	public void setAmpli12(BigDecimal ampli12) {
		this.ampli12 = ampli12;
	}

	/**
	 * Gets the ampli 13.
	 *
	 * @return the ampli 13
	 */
	public BigDecimal getAmpli13() {
		return this.ampli13;
	}

	/**
	 * Sets the ampli 13.
	 *
	 * @param ampli13 the new ampli 13
	 */
	public void setAmpli13(BigDecimal ampli13) {
		this.ampli13 = ampli13;
	}

	/**
	 * Gets the ampli 14.
	 *
	 * @return the ampli 14
	 */
	public BigDecimal getAmpli14() {
		return this.ampli14;
	}

	/**
	 * Sets the ampli 14.
	 *
	 * @param ampli14 the new ampli 14
	 */
	public void setAmpli14(BigDecimal ampli14) {
		this.ampli14 = ampli14;
	}

	/**
	 * Gets the ampli 15.
	 *
	 * @return the ampli 15
	 */
	public BigDecimal getAmpli15() {
		return this.ampli15;
	}

	/**
	 * Sets the ampli 15.
	 *
	 * @param ampli15 the new ampli 15
	 */
	public void setAmpli15(BigDecimal ampli15) {
		this.ampli15 = ampli15;
	}

	/**
	 * Gets the ampli 16.
	 *
	 * @return the ampli 16
	 */
	public BigDecimal getAmpli16() {
		return this.ampli16;
	}

	/**
	 * Sets the ampli 16.
	 *
	 * @param ampli16 the new ampli 16
	 */
	public void setAmpli16(BigDecimal ampli16) {
		this.ampli16 = ampli16;
	}

	/**
	 * Gets the ampli 17.
	 *
	 * @return the ampli 17
	 */
	public BigDecimal getAmpli17() {
		return this.ampli17;
	}

	/**
	 * Sets the ampli 17.
	 *
	 * @param ampli17 the new ampli 17
	 */
	public void setAmpli17(BigDecimal ampli17) {
		this.ampli17 = ampli17;
	}

	/**
	 * Gets the ampli 18.
	 *
	 * @return the ampli 18
	 */
	public BigDecimal getAmpli18() {
		return this.ampli18;
	}

	/**
	 * Sets the ampli 18.
	 *
	 * @param ampli18 the new ampli 18
	 */
	public void setAmpli18(BigDecimal ampli18) {
		this.ampli18 = ampli18;
	}

	/**
	 * Gets the ampli 19.
	 *
	 * @return the ampli 19
	 */
	public BigDecimal getAmpli19() {
		return this.ampli19;
	}

	/**
	 * Sets the ampli 19.
	 *
	 * @param ampli19 the new ampli 19
	 */
	public void setAmpli19(BigDecimal ampli19) {
		this.ampli19 = ampli19;
	}

	/**
	 * Gets the auto.
	 *
	 * @return the auto
	 */
	public BigDecimal getAuto() {
		return this.auto;
	}

	/**
	 * Sets the auto.
	 *
	 * @param auto the new auto
	 */
	public void setAuto(BigDecimal auto) {
		this.auto = auto;
	}

	/**
	 * Gets the auto 11.
	 *
	 * @return the auto 11
	 */
	public BigDecimal getAuto11() {
		return this.auto11;
	}

	/**
	 * Sets the auto 11.
	 *
	 * @param auto11 the new auto 11
	 */
	public void setAuto11(BigDecimal auto11) {
		this.auto11 = auto11;
	}

	/**
	 * Gets the auto 110.
	 *
	 * @return the auto 110
	 */
	public BigDecimal getAuto110() {
		return this.auto110;
	}

	/**
	 * Sets the auto 110.
	 *
	 * @param auto110 the new auto 110
	 */
	public void setAuto110(BigDecimal auto110) {
		this.auto110 = auto110;
	}

	/**
	 * Gets the auto 111.
	 *
	 * @return the auto 111
	 */
	public BigDecimal getAuto111() {
		return this.auto111;
	}

	/**
	 * Sets the auto 111.
	 *
	 * @param auto111 the new auto 111
	 */
	public void setAuto111(BigDecimal auto111) {
		this.auto111 = auto111;
	}

	/**
	 * Gets the auto 112.
	 *
	 * @return the auto 112
	 */
	public BigDecimal getAuto112() {
		return this.auto112;
	}

	/**
	 * Sets the auto 112.
	 *
	 * @param auto112 the new auto 112
	 */
	public void setAuto112(BigDecimal auto112) {
		this.auto112 = auto112;
	}

	/**
	 * Gets the auto 113.
	 *
	 * @return the auto 113
	 */
	public BigDecimal getAuto113() {
		return this.auto113;
	}

	/**
	 * Sets the auto 113.
	 *
	 * @param auto113 the new auto 113
	 */
	public void setAuto113(BigDecimal auto113) {
		this.auto113 = auto113;
	}

	/**
	 * Gets the auto 12.
	 *
	 * @return the auto 12
	 */
	public BigDecimal getAuto12() {
		return this.auto12;
	}

	/**
	 * Sets the auto 12.
	 *
	 * @param auto12 the new auto 12
	 */
	public void setAuto12(BigDecimal auto12) {
		this.auto12 = auto12;
	}

	/**
	 * Gets the auto 13.
	 *
	 * @return the auto 13
	 */
	public BigDecimal getAuto13() {
		return this.auto13;
	}

	/**
	 * Sets the auto 13.
	 *
	 * @param auto13 the new auto 13
	 */
	public void setAuto13(BigDecimal auto13) {
		this.auto13 = auto13;
	}

	/**
	 * Gets the auto 14.
	 *
	 * @return the auto 14
	 */
	public BigDecimal getAuto14() {
		return this.auto14;
	}

	/**
	 * Sets the auto 14.
	 *
	 * @param auto14 the new auto 14
	 */
	public void setAuto14(BigDecimal auto14) {
		this.auto14 = auto14;
	}

	/**
	 * Gets the auto 15.
	 *
	 * @return the auto 15
	 */
	public BigDecimal getAuto15() {
		return this.auto15;
	}

	/**
	 * Sets the auto 15.
	 *
	 * @param auto15 the new auto 15
	 */
	public void setAuto15(BigDecimal auto15) {
		this.auto15 = auto15;
	}

	/**
	 * Gets the auto 16.
	 *
	 * @return the auto 16
	 */
	public BigDecimal getAuto16() {
		return this.auto16;
	}

	/**
	 * Sets the auto 16.
	 *
	 * @param auto16 the new auto 16
	 */
	public void setAuto16(BigDecimal auto16) {
		this.auto16 = auto16;
	}

	/**
	 * Gets the auto 17.
	 *
	 * @return the auto 17
	 */
	public BigDecimal getAuto17() {
		return this.auto17;
	}

	/**
	 * Sets the auto 17.
	 *
	 * @param auto17 the new auto 17
	 */
	public void setAuto17(BigDecimal auto17) {
		this.auto17 = auto17;
	}

	/**
	 * Gets the auto 18.
	 *
	 * @return the auto 18
	 */
	public BigDecimal getAuto18() {
		return this.auto18;
	}

	/**
	 * Sets the auto 18.
	 *
	 * @param auto18 the new auto 18
	 */
	public void setAuto18(BigDecimal auto18) {
		this.auto18 = auto18;
	}

	/**
	 * Gets the auto 19.
	 *
	 * @return the auto 19
	 */
	public BigDecimal getAuto19() {
		return this.auto19;
	}

	/**
	 * Sets the auto 19.
	 *
	 * @param auto19 the new auto 19
	 */
	public void setAuto19(BigDecimal auto19) {
		this.auto19 = auto19;
	}

	/**
	 * Gets the ejpa.
	 *
	 * @return the ejpa
	 */
	public BigDecimal getEjpa() {
		return this.ejpa;
	}

	/**
	 * Sets the ejpa.
	 *
	 * @param ejpa the new ejpa
	 */
	public void setEjpa(BigDecimal ejpa) {
		this.ejpa = ejpa;
	}

	/**
	 * Gets the ejpa 11.
	 *
	 * @return the ejpa 11
	 */
	public BigDecimal getEjpa11() {
		return this.ejpa11;
	}

	/**
	 * Sets the ejpa 11.
	 *
	 * @param ejpa11 the new ejpa 11
	 */
	public void setEjpa11(BigDecimal ejpa11) {
		this.ejpa11 = ejpa11;
	}

	/**
	 * Gets the ejpa 110.
	 *
	 * @return the ejpa 110
	 */
	public BigDecimal getEjpa110() {
		return this.ejpa110;
	}

	/**
	 * Sets the ejpa 110.
	 *
	 * @param ejpa110 the new ejpa 110
	 */
	public void setEjpa110(BigDecimal ejpa110) {
		this.ejpa110 = ejpa110;
	}

	/**
	 * Gets the ejpa 111.
	 *
	 * @return the ejpa 111
	 */
	public BigDecimal getEjpa111() {
		return this.ejpa111;
	}

	/**
	 * Sets the ejpa 111.
	 *
	 * @param ejpa111 the new ejpa 111
	 */
	public void setEjpa111(BigDecimal ejpa111) {
		this.ejpa111 = ejpa111;
	}

	/**
	 * Gets the ejpa 112.
	 *
	 * @return the ejpa 112
	 */
	public BigDecimal getEjpa112() {
		return this.ejpa112;
	}

	/**
	 * Sets the ejpa 112.
	 *
	 * @param ejpa112 the new ejpa 112
	 */
	public void setEjpa112(BigDecimal ejpa112) {
		this.ejpa112 = ejpa112;
	}

	/**
	 * Gets the ejpa 113.
	 *
	 * @return the ejpa 113
	 */
	public BigDecimal getEjpa113() {
		return this.ejpa113;
	}

	/**
	 * Sets the ejpa 113.
	 *
	 * @param ejpa113 the new ejpa 113
	 */
	public void setEjpa113(BigDecimal ejpa113) {
		this.ejpa113 = ejpa113;
	}

	/**
	 * Gets the ejpa 12.
	 *
	 * @return the ejpa 12
	 */
	public BigDecimal getEjpa12() {
		return this.ejpa12;
	}

	/**
	 * Sets the ejpa 12.
	 *
	 * @param ejpa12 the new ejpa 12
	 */
	public void setEjpa12(BigDecimal ejpa12) {
		this.ejpa12 = ejpa12;
	}

	/**
	 * Gets the ejpa 13.
	 *
	 * @return the ejpa 13
	 */
	public BigDecimal getEjpa13() {
		return this.ejpa13;
	}

	/**
	 * Sets the ejpa 13.
	 *
	 * @param ejpa13 the new ejpa 13
	 */
	public void setEjpa13(BigDecimal ejpa13) {
		this.ejpa13 = ejpa13;
	}

	/**
	 * Gets the ejpa 14.
	 *
	 * @return the ejpa 14
	 */
	public BigDecimal getEjpa14() {
		return this.ejpa14;
	}

	/**
	 * Sets the ejpa 14.
	 *
	 * @param ejpa14 the new ejpa 14
	 */
	public void setEjpa14(BigDecimal ejpa14) {
		this.ejpa14 = ejpa14;
	}

	/**
	 * Gets the ejpa 15.
	 *
	 * @return the ejpa 15
	 */
	public BigDecimal getEjpa15() {
		return this.ejpa15;
	}

	/**
	 * Sets the ejpa 15.
	 *
	 * @param ejpa15 the new ejpa 15
	 */
	public void setEjpa15(BigDecimal ejpa15) {
		this.ejpa15 = ejpa15;
	}

	/**
	 * Gets the ejpa 16.
	 *
	 * @return the ejpa 16
	 */
	public BigDecimal getEjpa16() {
		return this.ejpa16;
	}

	/**
	 * Sets the ejpa 16.
	 *
	 * @param ejpa16 the new ejpa 16
	 */
	public void setEjpa16(BigDecimal ejpa16) {
		this.ejpa16 = ejpa16;
	}

	/**
	 * Gets the ejpa 17.
	 *
	 * @return the ejpa 17
	 */
	public BigDecimal getEjpa17() {
		return this.ejpa17;
	}

	/**
	 * Sets the ejpa 17.
	 *
	 * @param ejpa17 the new ejpa 17
	 */
	public void setEjpa17(BigDecimal ejpa17) {
		this.ejpa17 = ejpa17;
	}

	/**
	 * Gets the ejpa 18.
	 *
	 * @return the ejpa 18
	 */
	public BigDecimal getEjpa18() {
		return this.ejpa18;
	}

	/**
	 * Sets the ejpa 18.
	 *
	 * @param ejpa18 the new ejpa 18
	 */
	public void setEjpa18(BigDecimal ejpa18) {
		this.ejpa18 = ejpa18;
	}

	/**
	 * Gets the ejpa 19.
	 *
	 * @return the ejpa 19
	 */
	public BigDecimal getEjpa19() {
		return this.ejpa19;
	}

	/**
	 * Sets the ejpa 19.
	 *
	 * @param ejpa19 the new ejpa 19
	 */
	public void setEjpa19(BigDecimal ejpa19) {
		this.ejpa19 = ejpa19;
	}

	/**
	 * Gets the ejxpa.
	 *
	 * @return the ejxpa
	 */
	public BigDecimal getEjxpa() {
		return this.ejxpa;
	}

	/**
	 * Sets the ejxpa.
	 *
	 * @param ejxpa the new ejxpa
	 */
	public void setEjxpa(BigDecimal ejxpa) {
		this.ejxpa = ejxpa;
	}

	/**
	 * Gets the ejxpa 11.
	 *
	 * @return the ejxpa 11
	 */
	public BigDecimal getEjxpa11() {
		return this.ejxpa11;
	}

	/**
	 * Sets the ejxpa 11.
	 *
	 * @param ejxpa11 the new ejxpa 11
	 */
	public void setEjxpa11(BigDecimal ejxpa11) {
		this.ejxpa11 = ejxpa11;
	}

	/**
	 * Gets the ejxpa 110.
	 *
	 * @return the ejxpa 110
	 */
	public BigDecimal getEjxpa110() {
		return this.ejxpa110;
	}

	/**
	 * Sets the ejxpa 110.
	 *
	 * @param ejxpa110 the new ejxpa 110
	 */
	public void setEjxpa110(BigDecimal ejxpa110) {
		this.ejxpa110 = ejxpa110;
	}

	/**
	 * Gets the ejxpa 111.
	 *
	 * @return the ejxpa 111
	 */
	public BigDecimal getEjxpa111() {
		return this.ejxpa111;
	}

	/**
	 * Sets the ejxpa 111.
	 *
	 * @param ejxpa111 the new ejxpa 111
	 */
	public void setEjxpa111(BigDecimal ejxpa111) {
		this.ejxpa111 = ejxpa111;
	}

	/**
	 * Gets the ejxpa 112.
	 *
	 * @return the ejxpa 112
	 */
	public BigDecimal getEjxpa112() {
		return this.ejxpa112;
	}

	/**
	 * Sets the ejxpa 112.
	 *
	 * @param ejxpa112 the new ejxpa 112
	 */
	public void setEjxpa112(BigDecimal ejxpa112) {
		this.ejxpa112 = ejxpa112;
	}

	/**
	 * Gets the ejxpa 113.
	 *
	 * @return the ejxpa 113
	 */
	public BigDecimal getEjxpa113() {
		return this.ejxpa113;
	}

	/**
	 * Sets the ejxpa 113.
	 *
	 * @param ejxpa113 the new ejxpa 113
	 */
	public void setEjxpa113(BigDecimal ejxpa113) {
		this.ejxpa113 = ejxpa113;
	}

	/**
	 * Gets the ejxpa 12.
	 *
	 * @return the ejxpa 12
	 */
	public BigDecimal getEjxpa12() {
		return this.ejxpa12;
	}

	/**
	 * Sets the ejxpa 12.
	 *
	 * @param ejxpa12 the new ejxpa 12
	 */
	public void setEjxpa12(BigDecimal ejxpa12) {
		this.ejxpa12 = ejxpa12;
	}

	/**
	 * Gets the ejxpa 13.
	 *
	 * @return the ejxpa 13
	 */
	public BigDecimal getEjxpa13() {
		return this.ejxpa13;
	}

	/**
	 * Sets the ejxpa 13.
	 *
	 * @param ejxpa13 the new ejxpa 13
	 */
	public void setEjxpa13(BigDecimal ejxpa13) {
		this.ejxpa13 = ejxpa13;
	}

	/**
	 * Gets the ejxpa 14.
	 *
	 * @return the ejxpa 14
	 */
	public BigDecimal getEjxpa14() {
		return this.ejxpa14;
	}

	/**
	 * Sets the ejxpa 14.
	 *
	 * @param ejxpa14 the new ejxpa 14
	 */
	public void setEjxpa14(BigDecimal ejxpa14) {
		this.ejxpa14 = ejxpa14;
	}

	/**
	 * Gets the ejxpa 15.
	 *
	 * @return the ejxpa 15
	 */
	public BigDecimal getEjxpa15() {
		return this.ejxpa15;
	}

	/**
	 * Sets the ejxpa 15.
	 *
	 * @param ejxpa15 the new ejxpa 15
	 */
	public void setEjxpa15(BigDecimal ejxpa15) {
		this.ejxpa15 = ejxpa15;
	}

	/**
	 * Gets the ejxpa 16.
	 *
	 * @return the ejxpa 16
	 */
	public BigDecimal getEjxpa16() {
		return this.ejxpa16;
	}

	/**
	 * Sets the ejxpa 16.
	 *
	 * @param ejxpa16 the new ejxpa 16
	 */
	public void setEjxpa16(BigDecimal ejxpa16) {
		this.ejxpa16 = ejxpa16;
	}

	/**
	 * Gets the ejxpa 17.
	 *
	 * @return the ejxpa 17
	 */
	public BigDecimal getEjxpa17() {
		return this.ejxpa17;
	}

	/**
	 * Sets the ejxpa 17.
	 *
	 * @param ejxpa17 the new ejxpa 17
	 */
	public void setEjxpa17(BigDecimal ejxpa17) {
		this.ejxpa17 = ejxpa17;
	}

	/**
	 * Gets the ejxpa 18.
	 *
	 * @return the ejxpa 18
	 */
	public BigDecimal getEjxpa18() {
		return this.ejxpa18;
	}

	/**
	 * Sets the ejxpa 18.
	 *
	 * @param ejxpa18 the new ejxpa 18
	 */
	public void setEjxpa18(BigDecimal ejxpa18) {
		this.ejxpa18 = ejxpa18;
	}

	/**
	 * Gets the ejxpa 19.
	 *
	 * @return the ejxpa 19
	 */
	public BigDecimal getEjxpa19() {
		return this.ejxpa19;
	}

	/**
	 * Sets the ejxpa 19.
	 *
	 * @param ejxpa19 the new ejxpa 19
	 */
	public void setEjxpa19(BigDecimal ejxpa19) {
		this.ejxpa19 = ejxpa19;
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
	 * Gets the partida.
	 *
	 * @return the partida
	 */
	public String getPartida() {
		return this.partida;
	}

	/**
	 * Sets the partida.
	 *
	 * @param partida the new partida
	 */
	public void setPartida(String partida) {
		this.partida = partida;
	}

	/**
	 * Gets the porpa.
	 *
	 * @return the porpa
	 */
	public BigDecimal getPorpa() {
		return this.porpa;
	}

	/**
	 * Sets the porpa.
	 *
	 * @param porpa the new porpa
	 */
	public void setPorpa(BigDecimal porpa) {
		this.porpa = porpa;
	}

	/**
	 * Gets the porpa 11.
	 *
	 * @return the porpa 11
	 */
	public BigDecimal getPorpa11() {
		return this.porpa11;
	}

	/**
	 * Sets the porpa 11.
	 *
	 * @param porpa11 the new porpa 11
	 */
	public void setPorpa11(BigDecimal porpa11) {
		this.porpa11 = porpa11;
	}

	/**
	 * Gets the porpa 110.
	 *
	 * @return the porpa 110
	 */
	public BigDecimal getPorpa110() {
		return this.porpa110;
	}

	/**
	 * Sets the porpa 110.
	 *
	 * @param porpa110 the new porpa 110
	 */
	public void setPorpa110(BigDecimal porpa110) {
		this.porpa110 = porpa110;
	}

	/**
	 * Gets the porpa 111.
	 *
	 * @return the porpa 111
	 */
	public BigDecimal getPorpa111() {
		return this.porpa111;
	}

	/**
	 * Sets the porpa 111.
	 *
	 * @param porpa111 the new porpa 111
	 */
	public void setPorpa111(BigDecimal porpa111) {
		this.porpa111 = porpa111;
	}

	/**
	 * Gets the porpa 112.
	 *
	 * @return the porpa 112
	 */
	public BigDecimal getPorpa112() {
		return this.porpa112;
	}

	/**
	 * Sets the porpa 112.
	 *
	 * @param porpa112 the new porpa 112
	 */
	public void setPorpa112(BigDecimal porpa112) {
		this.porpa112 = porpa112;
	}

	/**
	 * Gets the porpa 113.
	 *
	 * @return the porpa 113
	 */
	public BigDecimal getPorpa113() {
		return this.porpa113;
	}

	/**
	 * Sets the porpa 113.
	 *
	 * @param porpa113 the new porpa 113
	 */
	public void setPorpa113(BigDecimal porpa113) {
		this.porpa113 = porpa113;
	}

	/**
	 * Gets the porpa 12.
	 *
	 * @return the porpa 12
	 */
	public BigDecimal getPorpa12() {
		return this.porpa12;
	}

	/**
	 * Sets the porpa 12.
	 *
	 * @param porpa12 the new porpa 12
	 */
	public void setPorpa12(BigDecimal porpa12) {
		this.porpa12 = porpa12;
	}

	/**
	 * Gets the porpa 13.
	 *
	 * @return the porpa 13
	 */
	public BigDecimal getPorpa13() {
		return this.porpa13;
	}

	/**
	 * Sets the porpa 13.
	 *
	 * @param porpa13 the new porpa 13
	 */
	public void setPorpa13(BigDecimal porpa13) {
		this.porpa13 = porpa13;
	}

	/**
	 * Gets the porpa 14.
	 *
	 * @return the porpa 14
	 */
	public BigDecimal getPorpa14() {
		return this.porpa14;
	}

	/**
	 * Sets the porpa 14.
	 *
	 * @param porpa14 the new porpa 14
	 */
	public void setPorpa14(BigDecimal porpa14) {
		this.porpa14 = porpa14;
	}

	/**
	 * Gets the porpa 15.
	 *
	 * @return the porpa 15
	 */
	public BigDecimal getPorpa15() {
		return this.porpa15;
	}

	/**
	 * Sets the porpa 15.
	 *
	 * @param porpa15 the new porpa 15
	 */
	public void setPorpa15(BigDecimal porpa15) {
		this.porpa15 = porpa15;
	}

	/**
	 * Gets the porpa 16.
	 *
	 * @return the porpa 16
	 */
	public BigDecimal getPorpa16() {
		return this.porpa16;
	}

	/**
	 * Sets the porpa 16.
	 *
	 * @param porpa16 the new porpa 16
	 */
	public void setPorpa16(BigDecimal porpa16) {
		this.porpa16 = porpa16;
	}

	/**
	 * Gets the porpa 17.
	 *
	 * @return the porpa 17
	 */
	public BigDecimal getPorpa17() {
		return this.porpa17;
	}

	/**
	 * Sets the porpa 17.
	 *
	 * @param porpa17 the new porpa 17
	 */
	public void setPorpa17(BigDecimal porpa17) {
		this.porpa17 = porpa17;
	}

	/**
	 * Gets the porpa 18.
	 *
	 * @return the porpa 18
	 */
	public BigDecimal getPorpa18() {
		return this.porpa18;
	}

	/**
	 * Sets the porpa 18.
	 *
	 * @param porpa18 the new porpa 18
	 */
	public void setPorpa18(BigDecimal porpa18) {
		this.porpa18 = porpa18;
	}

	/**
	 * Gets the porpa 19.
	 *
	 * @return the porpa 19
	 */
	public BigDecimal getPorpa19() {
		return this.porpa19;
	}

	/**
	 * Sets the porpa 19.
	 *
	 * @param porpa19 the new porpa 19
	 */
	public void setPorpa19(BigDecimal porpa19) {
		this.porpa19 = porpa19;
	}

	/**
	 * Gets the redu 11.
	 *
	 * @return the redu 11
	 */
	public BigDecimal getRedu11() {
		return this.redu11;
	}

	/**
	 * Sets the redu 11.
	 *
	 * @param redu11 the new redu 11
	 */
	public void setRedu11(BigDecimal redu11) {
		this.redu11 = redu11;
	}

	/**
	 * Gets the redu 110.
	 *
	 * @return the redu 110
	 */
	public BigDecimal getRedu110() {
		return this.redu110;
	}

	/**
	 * Sets the redu 110.
	 *
	 * @param redu110 the new redu 110
	 */
	public void setRedu110(BigDecimal redu110) {
		this.redu110 = redu110;
	}

	/**
	 * Gets the redu 111.
	 *
	 * @return the redu 111
	 */
	public BigDecimal getRedu111() {
		return this.redu111;
	}

	/**
	 * Sets the redu 111.
	 *
	 * @param redu111 the new redu 111
	 */
	public void setRedu111(BigDecimal redu111) {
		this.redu111 = redu111;
	}

	/**
	 * Gets the redu 112.
	 *
	 * @return the redu 112
	 */
	public BigDecimal getRedu112() {
		return this.redu112;
	}

	/**
	 * Sets the redu 112.
	 *
	 * @param redu112 the new redu 112
	 */
	public void setRedu112(BigDecimal redu112) {
		this.redu112 = redu112;
	}

	/**
	 * Gets the redu 113.
	 *
	 * @return the redu 113
	 */
	public BigDecimal getRedu113() {
		return this.redu113;
	}

	/**
	 * Sets the redu 113.
	 *
	 * @param redu113 the new redu 113
	 */
	public void setRedu113(BigDecimal redu113) {
		this.redu113 = redu113;
	}

	/**
	 * Gets the redu 12.
	 *
	 * @return the redu 12
	 */
	public BigDecimal getRedu12() {
		return this.redu12;
	}

	/**
	 * Sets the redu 12.
	 *
	 * @param redu12 the new redu 12
	 */
	public void setRedu12(BigDecimal redu12) {
		this.redu12 = redu12;
	}

	/**
	 * Gets the redu 13.
	 *
	 * @return the redu 13
	 */
	public BigDecimal getRedu13() {
		return this.redu13;
	}

	/**
	 * Sets the redu 13.
	 *
	 * @param redu13 the new redu 13
	 */
	public void setRedu13(BigDecimal redu13) {
		this.redu13 = redu13;
	}

	/**
	 * Gets the redu 14.
	 *
	 * @return the redu 14
	 */
	public BigDecimal getRedu14() {
		return this.redu14;
	}

	/**
	 * Sets the redu 14.
	 *
	 * @param redu14 the new redu 14
	 */
	public void setRedu14(BigDecimal redu14) {
		this.redu14 = redu14;
	}

	/**
	 * Gets the redu 15.
	 *
	 * @return the redu 15
	 */
	public BigDecimal getRedu15() {
		return this.redu15;
	}

	/**
	 * Sets the redu 15.
	 *
	 * @param redu15 the new redu 15
	 */
	public void setRedu15(BigDecimal redu15) {
		this.redu15 = redu15;
	}

	/**
	 * Gets the redu 16.
	 *
	 * @return the redu 16
	 */
	public BigDecimal getRedu16() {
		return this.redu16;
	}

	/**
	 * Sets the redu 16.
	 *
	 * @param redu16 the new redu 16
	 */
	public void setRedu16(BigDecimal redu16) {
		this.redu16 = redu16;
	}

	/**
	 * Gets the redu 17.
	 *
	 * @return the redu 17
	 */
	public BigDecimal getRedu17() {
		return this.redu17;
	}

	/**
	 * Sets the redu 17.
	 *
	 * @param redu17 the new redu 17
	 */
	public void setRedu17(BigDecimal redu17) {
		this.redu17 = redu17;
	}

	/**
	 * Gets the redu 18.
	 *
	 * @return the redu 18
	 */
	public BigDecimal getRedu18() {
		return this.redu18;
	}

	/**
	 * Sets the redu 18.
	 *
	 * @param redu18 the new redu 18
	 */
	public void setRedu18(BigDecimal redu18) {
		this.redu18 = redu18;
	}

	/**
	 * Gets the redu 19.
	 *
	 * @return the redu 19
	 */
	public BigDecimal getRedu19() {
		return this.redu19;
	}

	/**
	 * Sets the redu 19.
	 *
	 * @param redu19 the new redu 19
	 */
	public void setRedu19(BigDecimal redu19) {
		this.redu19 = redu19;
	}

	/**
	 * Gets the toeje.
	 *
	 * @return the toeje
	 */
	public BigDecimal getToeje() {
		return this.toeje;
	}

	/**
	 * Sets the toeje.
	 *
	 * @param toeje the new toeje
	 */
	public void setToeje(BigDecimal toeje) {
		this.toeje = toeje;
	}

	/**
	 * Gets the toeje 11.
	 *
	 * @return the toeje 11
	 */
	public BigDecimal getToeje11() {
		return this.toeje11;
	}

	/**
	 * Sets the toeje 11.
	 *
	 * @param toeje11 the new toeje 11
	 */
	public void setToeje11(BigDecimal toeje11) {
		this.toeje11 = toeje11;
	}

	/**
	 * Gets the toeje 110.
	 *
	 * @return the toeje 110
	 */
	public BigDecimal getToeje110() {
		return this.toeje110;
	}

	/**
	 * Sets the toeje 110.
	 *
	 * @param toeje110 the new toeje 110
	 */
	public void setToeje110(BigDecimal toeje110) {
		this.toeje110 = toeje110;
	}

	/**
	 * Gets the toeje 111.
	 *
	 * @return the toeje 111
	 */
	public BigDecimal getToeje111() {
		return this.toeje111;
	}

	/**
	 * Sets the toeje 111.
	 *
	 * @param toeje111 the new toeje 111
	 */
	public void setToeje111(BigDecimal toeje111) {
		this.toeje111 = toeje111;
	}

	/**
	 * Gets the toeje 112.
	 *
	 * @return the toeje 112
	 */
	public BigDecimal getToeje112() {
		return this.toeje112;
	}

	/**
	 * Sets the toeje 112.
	 *
	 * @param toeje112 the new toeje 112
	 */
	public void setToeje112(BigDecimal toeje112) {
		this.toeje112 = toeje112;
	}

	/**
	 * Gets the toeje 113.
	 *
	 * @return the toeje 113
	 */
	public BigDecimal getToeje113() {
		return this.toeje113;
	}

	/**
	 * Sets the toeje 113.
	 *
	 * @param toeje113 the new toeje 113
	 */
	public void setToeje113(BigDecimal toeje113) {
		this.toeje113 = toeje113;
	}

	/**
	 * Gets the toeje 12.
	 *
	 * @return the toeje 12
	 */
	public BigDecimal getToeje12() {
		return this.toeje12;
	}

	/**
	 * Sets the toeje 12.
	 *
	 * @param toeje12 the new toeje 12
	 */
	public void setToeje12(BigDecimal toeje12) {
		this.toeje12 = toeje12;
	}

	/**
	 * Gets the toeje 13.
	 *
	 * @return the toeje 13
	 */
	public BigDecimal getToeje13() {
		return this.toeje13;
	}

	/**
	 * Sets the toeje 13.
	 *
	 * @param toeje13 the new toeje 13
	 */
	public void setToeje13(BigDecimal toeje13) {
		this.toeje13 = toeje13;
	}

	/**
	 * Gets the toeje 14.
	 *
	 * @return the toeje 14
	 */
	public BigDecimal getToeje14() {
		return this.toeje14;
	}

	/**
	 * Sets the toeje 14.
	 *
	 * @param toeje14 the new toeje 14
	 */
	public void setToeje14(BigDecimal toeje14) {
		this.toeje14 = toeje14;
	}

	/**
	 * Gets the toeje 15.
	 *
	 * @return the toeje 15
	 */
	public BigDecimal getToeje15() {
		return this.toeje15;
	}

	/**
	 * Sets the toeje 15.
	 *
	 * @param toeje15 the new toeje 15
	 */
	public void setToeje15(BigDecimal toeje15) {
		this.toeje15 = toeje15;
	}

	/**
	 * Gets the toeje 16.
	 *
	 * @return the toeje 16
	 */
	public BigDecimal getToeje16() {
		return this.toeje16;
	}

	/**
	 * Sets the toeje 16.
	 *
	 * @param toeje16 the new toeje 16
	 */
	public void setToeje16(BigDecimal toeje16) {
		this.toeje16 = toeje16;
	}

	/**
	 * Gets the toeje 17.
	 *
	 * @return the toeje 17
	 */
	public BigDecimal getToeje17() {
		return this.toeje17;
	}

	/**
	 * Sets the toeje 17.
	 *
	 * @param toeje17 the new toeje 17
	 */
	public void setToeje17(BigDecimal toeje17) {
		this.toeje17 = toeje17;
	}

	/**
	 * Gets the toeje 18.
	 *
	 * @return the toeje 18
	 */
	public BigDecimal getToeje18() {
		return this.toeje18;
	}

	/**
	 * Sets the toeje 18.
	 *
	 * @param toeje18 the new toeje 18
	 */
	public void setToeje18(BigDecimal toeje18) {
		this.toeje18 = toeje18;
	}

	/**
	 * Gets the toeje 19.
	 *
	 * @return the toeje 19
	 */
	public BigDecimal getToeje19() {
		return this.toeje19;
	}

	/**
	 * Sets the toeje 19.
	 *
	 * @param toeje19 the new toeje 19
	 */
	public void setToeje19(BigDecimal toeje19) {
		this.toeje19 = toeje19;
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
