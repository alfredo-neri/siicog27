package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notNull;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the CATFLU database table.
 * 
 */
@Entity
@NamedQuery(name = "Catflu.findAll", query = "SELECT c FROM Catflu c")
public class Catflu extends AbstractEntity {

	/** The Constant MAX_LENGTH_NOMBRE. */
	@IgnoredQuery
	static final int MAX_LENGTH_NOMBRE = 160;
	
	/** The Constant MAX_LENGTH_CTA. */
	@IgnoredQuery
	static final int MAX_LENGTH_CTA = 4;

	/** The clvflu. */
	@Column(name = "clvflu", nullable = false)
	private Double clvflu;

	/** The gruflu. */
	private String gruflu = StringUtils.EMPTY;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	@Column(name = "IDSECTOR")
	private Integer idsector;

	/** The mesflu 1. */
	@Column(name = "MESFLU_1")
	private BigDecimal mesflu1;

	/** The mesflu 10. */
	@Column(name = "MESFLU_10")
	private BigDecimal mesflu10;

	/** The mesflu 11. */
	@Column(name = "MESFLU_11")
	private BigDecimal mesflu11;

	/** The mesflu 12. */
	@Column(name = "MESFLU_12")
	private BigDecimal mesflu12;

	/** The mesflu 13. */
	@Column(name = "MESFLU_13")
	private BigDecimal mesflu13;

	/** The mesflu 14. */
	@Column(name = "MESFLU_14")
	private BigDecimal mesflu14;

	/** The mesflu 15. */
	@Column(name = "MESFLU_15")
	private BigDecimal mesflu15;

	/** The mesflu 16. */
	@Column(name = "MESFLU_16")
	private BigDecimal mesflu16;

	/** The mesflu 2. */
	@Column(name = "MESFLU_2")
	private BigDecimal mesflu2;

	/** The mesflu 3. */
	@Column(name = "MESFLU_3")
	private BigDecimal mesflu3;

	/** The mesflu 4. */
	@Column(name = "MESFLU_4")
	private BigDecimal mesflu4;

	/** The mesflu 5. */
	@Column(name = "MESFLU_5")
	private BigDecimal mesflu5;

	/** The mesflu 6. */
	@Column(name = "MESFLU_6")
	private BigDecimal mesflu6;

	/** The mesflu 7. */
	@Column(name = "MESFLU_7")
	private BigDecimal mesflu7;

	/** The mesflu 8. */
	@Column(name = "MESFLU_8")
	private BigDecimal mesflu8;

	/** The mesflu 9. */
	@Column(name = "MESFLU_9")
	private BigDecimal mesflu9;

	/** The nomflu. */
	@Column(name = "nomflu", nullable = false, length = MAX_LENGTH_NOMBRE)
	private String nomflu = StringUtils.EMPTY;

	/** The sguflu. */
	private String sguflu = StringUtils.EMPTY;

	/** The ssgflu. */
	private String ssgflu = StringUtils.EMPTY;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new catflu.
	 */
	public Catflu() {
	}

	/**
	 * Instantiates a new catflu.
	 *
	 * @param clvflu the clvflu
	 * @param nomflu the nomflu
	 * @param gruflu the gruflu
	 * @param sguflu the sguflu
	 * @param ssgflu the ssgflu
	 * @param mesflu the mesflu
	 */
	public Catflu(Double clvflu, String nomflu, String gruflu, String sguflu, String ssgflu, BigDecimal mesflu) {
		this.clvflu = clvflu;
		this.nomflu = nomflu;
		this.gruflu = gruflu;
		this.sguflu = sguflu;
		this.ssgflu = ssgflu;
		this.mesflu1 = mesflu;
	}

	/**
	 * Gets the clvflu.
	 *
	 * @return the clvflu
	 */
	@ColumnFile(indexColumn = 1)
	public Double getClvflu() {
		return this.clvflu;
	}

	/**
	 * Sets the clvflu.
	 *
	 * @param clvflu the new clvflu
	 */
	public void setClvflu(Double clvflu) {
		this.clvflu = clvflu;
	}

	/**
	 * Gets the gruflu.
	 *
	 * @return the gruflu
	 */
	@ColumnFile(indexColumn = 3)
	public String getGruflu() {
		return this.gruflu;
	}

	/**
	 * Sets the gruflu.
	 *
	 * @param gruflu the new gruflu
	 */
	public void setGruflu(String gruflu) {
		this.gruflu = gruflu;
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
	 * Gets the mesflu 1.
	 *
	 * @return the mesflu 1
	 */
	public BigDecimal getMesflu1() {
		return this.mesflu1;
	}

	/**
	 * Sets the mesflu 1.
	 *
	 * @param mesflu1 the new mesflu 1
	 */
	public void setMesflu1(BigDecimal mesflu1) {
		this.mesflu1 = mesflu1;
	}

	/**
	 * Gets the mesflu 10.
	 *
	 * @return the mesflu 10
	 */
	public BigDecimal getMesflu10() {
		return this.mesflu10;
	}

	/**
	 * Sets the mesflu 10.
	 *
	 * @param mesflu10 the new mesflu 10
	 */
	public void setMesflu10(BigDecimal mesflu10) {
		this.mesflu10 = mesflu10;
	}

	/**
	 * Gets the mesflu 11.
	 *
	 * @return the mesflu 11
	 */
	public BigDecimal getMesflu11() {
		return this.mesflu11;
	}

	/**
	 * Sets the mesflu 11.
	 *
	 * @param mesflu11 the new mesflu 11
	 */
	public void setMesflu11(BigDecimal mesflu11) {
		this.mesflu11 = mesflu11;
	}

	/**
	 * Gets the mesflu 12.
	 *
	 * @return the mesflu 12
	 */
	public BigDecimal getMesflu12() {
		return this.mesflu12;
	}

	/**
	 * Sets the mesflu 12.
	 *
	 * @param mesflu12 the new mesflu 12
	 */
	public void setMesflu12(BigDecimal mesflu12) {
		this.mesflu12 = mesflu12;
	}

	/**
	 * Gets the mesflu 13.
	 *
	 * @return the mesflu 13
	 */
	public BigDecimal getMesflu13() {
		return this.mesflu13;
	}

	/**
	 * Sets the mesflu 13.
	 *
	 * @param mesflu13 the new mesflu 13
	 */
	public void setMesflu13(BigDecimal mesflu13) {
		this.mesflu13 = mesflu13;
	}

	/**
	 * Gets the mesflu 14.
	 *
	 * @return the mesflu 14
	 */
	public BigDecimal getMesflu14() {
		return this.mesflu14;
	}

	/**
	 * Sets the mesflu 14.
	 *
	 * @param mesflu14 the new mesflu 14
	 */
	public void setMesflu14(BigDecimal mesflu14) {
		this.mesflu14 = mesflu14;
	}

	/**
	 * Gets the mesflu 15.
	 *
	 * @return the mesflu 15
	 */
	public BigDecimal getMesflu15() {
		return this.mesflu15;
	}

	/**
	 * Sets the mesflu 15.
	 *
	 * @param mesflu15 the new mesflu 15
	 */
	public void setMesflu15(BigDecimal mesflu15) {
		this.mesflu15 = mesflu15;
	}

	/**
	 * Gets the mesflu 16.
	 *
	 * @return the mesflu 16
	 */
	public BigDecimal getMesflu16() {
		return this.mesflu16;
	}

	/**
	 * Sets the mesflu 16.
	 *
	 * @param mesflu16 the new mesflu 16
	 */
	public void setMesflu16(BigDecimal mesflu16) {
		this.mesflu16 = mesflu16;
	}

	/**
	 * Gets the mesflu 2.
	 *
	 * @return the mesflu 2
	 */
	public BigDecimal getMesflu2() {
		return this.mesflu2;
	}

	/**
	 * Sets the mesflu 2.
	 *
	 * @param mesflu2 the new mesflu 2
	 */
	public void setMesflu2(BigDecimal mesflu2) {
		this.mesflu2 = mesflu2;
	}

	/**
	 * Gets the mesflu 3.
	 *
	 * @return the mesflu 3
	 */
	public BigDecimal getMesflu3() {
		return this.mesflu3;
	}

	/**
	 * Sets the mesflu 3.
	 *
	 * @param mesflu3 the new mesflu 3
	 */
	public void setMesflu3(BigDecimal mesflu3) {
		this.mesflu3 = mesflu3;
	}

	/**
	 * Gets the mesflu 4.
	 *
	 * @return the mesflu 4
	 */
	public BigDecimal getMesflu4() {
		return this.mesflu4;
	}

	/**
	 * Sets the mesflu 4.
	 *
	 * @param mesflu4 the new mesflu 4
	 */
	public void setMesflu4(BigDecimal mesflu4) {
		this.mesflu4 = mesflu4;
	}

	/**
	 * Gets the mesflu 5.
	 *
	 * @return the mesflu 5
	 */
	public BigDecimal getMesflu5() {
		return this.mesflu5;
	}

	/**
	 * Sets the mesflu 5.
	 *
	 * @param mesflu5 the new mesflu 5
	 */
	public void setMesflu5(BigDecimal mesflu5) {
		this.mesflu5 = mesflu5;
	}

	/**
	 * Gets the mesflu 6.
	 *
	 * @return the mesflu 6
	 */
	public BigDecimal getMesflu6() {
		return this.mesflu6;
	}

	/**
	 * Sets the mesflu 6.
	 *
	 * @param mesflu6 the new mesflu 6
	 */
	public void setMesflu6(BigDecimal mesflu6) {
		this.mesflu6 = mesflu6;
	}

	/**
	 * Gets the mesflu 7.
	 *
	 * @return the mesflu 7
	 */
	public BigDecimal getMesflu7() {
		return this.mesflu7;
	}

	/**
	 * Sets the mesflu 7.
	 *
	 * @param mesflu7 the new mesflu 7
	 */
	public void setMesflu7(BigDecimal mesflu7) {
		this.mesflu7 = mesflu7;
	}

	/**
	 * Gets the mesflu 8.
	 *
	 * @return the mesflu 8
	 */
	public BigDecimal getMesflu8() {
		return this.mesflu8;
	}

	/**
	 * Sets the mesflu 8.
	 *
	 * @param mesflu8 the new mesflu 8
	 */
	public void setMesflu8(BigDecimal mesflu8) {
		this.mesflu8 = mesflu8;
	}

	/**
	 * Gets the mesflu 9.
	 *
	 * @return the mesflu 9
	 */
	public BigDecimal getMesflu9() {
		return this.mesflu9;
	}

	/**
	 * Sets the mesflu 9.
	 *
	 * @param mesflu9 the new mesflu 9
	 */
	public void setMesflu9(BigDecimal mesflu9) {
		this.mesflu9 = mesflu9;
	}

	/**
	 * Gets the nomflu.
	 *
	 * @return the nomflu
	 */
	@ColumnFile(indexColumn = 2)
	public String getNomflu() {
		return this.nomflu;
	}

	/**
	 * Sets the nomflu.
	 *
	 * @param nomflu the new nomflu
	 */
	public void setNomflu(String nomflu) {
		this.nomflu = nomflu;
	}

	/**
	 * Gets the sguflu.
	 *
	 * @return the sguflu
	 */
	@ColumnFile(indexColumn = 4)
	public String getSguflu() {
		return this.sguflu;
	}

	/**
	 * Sets the sguflu.
	 *
	 * @param sguflu the new sguflu
	 */
	public void setSguflu(String sguflu) {
		this.sguflu = sguflu;
	}

	/**
	 * Gets the ssgflu.
	 *
	 * @return the ssgflu
	 */
	public String getSsgflu() {
		return this.ssgflu;
	}

	/**
	 * Sets the ssgflu.
	 *
	 * @param ssgflu the new ssgflu
	 */
	public void setSsgflu(String ssgflu) {
		this.ssgflu = ssgflu;
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
	 * Require valid nomflu.
	 *
	 * @param nomflu the nomflu
	 */
	public void requireValidNomflu(String nomflu) {
		notNull(nomflu, "Nombre no puede ser nulo.");
		isTrue(nomflu.length() <= MAX_LENGTH_NOMBRE, "La m�xima longitud del nombre es de <%d> caracteres.",
				MAX_LENGTH_NOMBRE);

	}

	/**
	 * Require valid gruflu.
	 *
	 * @param gruflu the gruflu
	 */
	public void requireValidGruflu(String gruflu) {
		isTrue(gruflu.length() <= MAX_LENGTH_CTA, "La m�xima longitud del nombre es de <%d> caracteres.",
				MAX_LENGTH_CTA);

	}

	/**
	 * Require valid sguflu.
	 *
	 * @param sguflu the sguflu
	 */
	public void requireValidSguflu(String sguflu) {
		isTrue(sguflu.length() <= MAX_LENGTH_CTA, "La m�xima longitud del nombre es de <%d> caracteres.",
				MAX_LENGTH_CTA);

	}

	/**
	 * Require valid ssgflu.
	 *
	 * @param ssgflu the ssgflu
	 */
	public void requireValidSsgflu(String ssgflu) {
		isTrue(ssgflu.length() <= MAX_LENGTH_CTA, "La m�xima longitud del nombre es de <%d> caracteres.",
				MAX_LENGTH_CTA);

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		// return new ToStringBuilder(this).append("ID",
		// getId()).append("Index", getIndex()).append("clvflu", this.clvflu)
		// .append("nomflu", this.nomflu).append("gruflu",
		// this.gruflu).append("sguflu", this.sguflu)
		// .append("ssgflu", this.ssgflu).append("mesflu",
		// this.mesflu1).toString();
		return ToStringBuilder.reflectionToString(this);
	}

}