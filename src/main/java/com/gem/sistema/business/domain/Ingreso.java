package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the INGRESO database table.
 *
 */
@Entity
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "acumula_ingreso", procedureName = "SP_ACUMULA_INGRESO", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_SECTOR", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "USER_ID", type = String.class) }) })
@NamedQuery(name = "Ingreso.findAll", query = "SELECT i FROM Ingreso i")
public class Ingreso extends MonthlyAbstractEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// @Id
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	// @Column(name="\"ID\"")
	// private long id;

	/** The autoi 1. */
	@Column(name = "AUTOI_1")
	private BigDecimal autoi1;

	/** The autoi 10. */
	@Column(name = "AUTOI_10")
	private BigDecimal autoi10;

	/** The autoi 11. */
	@Column(name = "AUTOI_11")
	private BigDecimal autoi11;

	/** The autoi 12. */
	@Column(name = "AUTOI_12")
	private BigDecimal autoi12;

	/** The autoi 13. */
	@Column(name = "AUTOI_13")
	private BigDecimal autoi13;

	/** The autoi 2. */
	@Column(name = "AUTOI_2")
	private BigDecimal autoi2;

	/** The autoi 3. */
	@Column(name = "AUTOI_3")
	private BigDecimal autoi3;

	/** The autoi 4. */
	@Column(name = "AUTOI_4")
	private BigDecimal autoi4;

	/** The autoi 5. */
	@Column(name = "AUTOI_5")
	private BigDecimal autoi5;

	/** The autoi 6. */
	@Column(name = "AUTOI_6")
	private BigDecimal autoi6;

	/** The autoi 7. */
	@Column(name = "AUTOI_7")
	private BigDecimal autoi7;

	/** The autoi 8. */
	@Column(name = "AUTOI_8")
	private BigDecimal autoi8;

	/** The autoi 9. */
	@Column(name = "AUTOI_9")
	private BigDecimal autoi9;

	/** The campo 1. */
	private int campo1;

	/** The clvfuen. */
	private String clvfuen;

	/** The cuenta. */
	private String cuenta = StringUtils.EMPTY;

	/** The cvetxt. */
	private String cvetxt = StringUtils.EMPTY;

	/** The scta. */
	private String scta = StringUtils.EMPTY;

	/** The sscta. */
	private String sscta = StringUtils.EMPTY;

	/** The ssscta. */
	private String ssscta = StringUtils.EMPTY;

	/** The sssscta. */
	private String sssscta = StringUtils.EMPTY;

	/** The idsector. */
	private Long idsector;

	private String userId;

	@Column(name = "ID_REF")
	private Integer ref;

	/**
	 * Instantiates a new ingreso.
	 */
	public Ingreso() {
	}

	// public long getId() {
	// return this.id;
	// }
	//
	// public void setId(long id) {
	// this.id = id;
	// }

	/**
	 * Gets the autoi 1.
	 *
	 * @return the autoi 1
	 */
	public BigDecimal getAutoi1() {
		return this.autoi1;
	}

	/**
	 * Sets the autoi 1.
	 *
	 * @param autoi1 the new autoi 1
	 */
	public void setAutoi1(BigDecimal autoi1) {
		this.autoi1 = autoi1;
	}

	/**
	 * Gets the autoi 10.
	 *
	 * @return the autoi 10
	 */
	public BigDecimal getAutoi10() {
		return this.autoi10;
	}

	/**
	 * Sets the autoi 10.
	 *
	 * @param autoi10 the new autoi 10
	 */
	public void setAutoi10(BigDecimal autoi10) {
		this.autoi10 = autoi10;
	}

	/**
	 * Gets the autoi 11.
	 *
	 * @return the autoi 11
	 */
	public BigDecimal getAutoi11() {
		return this.autoi11;
	}

	/**
	 * Sets the autoi 11.
	 *
	 * @param autoi11 the new autoi 11
	 */
	public void setAutoi11(BigDecimal autoi11) {
		this.autoi11 = autoi11;
	}

	/**
	 * Gets the autoi 12.
	 *
	 * @return the autoi 12
	 */
	public BigDecimal getAutoi12() {
		return this.autoi12;
	}

	/**
	 * Sets the autoi 12.
	 *
	 * @param autoi12 the new autoi 12
	 */
	public void setAutoi12(BigDecimal autoi12) {
		this.autoi12 = autoi12;
	}

	/**
	 * Gets the autoi 13.
	 *
	 * @return the autoi 13
	 */
	public BigDecimal getAutoi13() {
		return this.autoi13;
	}

	/**
	 * Sets the autoi 13.
	 *
	 * @param autoi13 the new autoi 13
	 */
	public void setAutoi13(BigDecimal autoi13) {
		this.autoi13 = autoi13;
	}

	/**
	 * Gets the autoi 2.
	 *
	 * @return the autoi 2
	 */
	public BigDecimal getAutoi2() {
		return this.autoi2;
	}

	/**
	 * Sets the autoi 2.
	 *
	 * @param autoi2 the new autoi 2
	 */
	public void setAutoi2(BigDecimal autoi2) {
		this.autoi2 = autoi2;
	}

	/**
	 * Gets the autoi 3.
	 *
	 * @return the autoi 3
	 */
	public BigDecimal getAutoi3() {
		return this.autoi3;
	}

	/**
	 * Sets the autoi 3.
	 *
	 * @param autoi3 the new autoi 3
	 */
	public void setAutoi3(BigDecimal autoi3) {
		this.autoi3 = autoi3;
	}

	/**
	 * Gets the autoi 4.
	 *
	 * @return the autoi 4
	 */
	public BigDecimal getAutoi4() {
		return this.autoi4;
	}

	/**
	 * Sets the autoi 4.
	 *
	 * @param autoi4 the new autoi 4
	 */
	public void setAutoi4(BigDecimal autoi4) {
		this.autoi4 = autoi4;
	}

	/**
	 * Gets the autoi 5.
	 *
	 * @return the autoi 5
	 */
	public BigDecimal getAutoi5() {
		return this.autoi5;
	}

	/**
	 * Sets the autoi 5.
	 *
	 * @param autoi5 the new autoi 5
	 */
	public void setAutoi5(BigDecimal autoi5) {
		this.autoi5 = autoi5;
	}

	/**
	 * Gets the autoi 6.
	 *
	 * @return the autoi 6
	 */
	public BigDecimal getAutoi6() {
		return this.autoi6;
	}

	/**
	 * Sets the autoi 6.
	 *
	 * @param autoi6 the new autoi 6
	 */
	public void setAutoi6(BigDecimal autoi6) {
		this.autoi6 = autoi6;
	}

	/**
	 * Gets the autoi 7.
	 *
	 * @return the autoi 7
	 */
	public BigDecimal getAutoi7() {
		return this.autoi7;
	}

	/**
	 * Sets the autoi 7.
	 *
	 * @param autoi7 the new autoi 7
	 */
	public void setAutoi7(BigDecimal autoi7) {
		this.autoi7 = autoi7;
	}

	/**
	 * Gets the autoi 8.
	 *
	 * @return the autoi 8
	 */
	public BigDecimal getAutoi8() {
		return this.autoi8;
	}

	/**
	 * Sets the autoi 8.
	 *
	 * @param autoi8 the new autoi 8
	 */
	public void setAutoi8(BigDecimal autoi8) {
		this.autoi8 = autoi8;
	}

	/**
	 * Gets the autoi 9.
	 *
	 * @return the autoi 9
	 */
	public BigDecimal getAutoi9() {
		return this.autoi9;
	}

	/**
	 * Sets the autoi 9.
	 *
	 * @param autoi9 the new autoi 9
	 */
	public void setAutoi9(BigDecimal autoi9) {
		this.autoi9 = autoi9;
	}

	/**
	 * Gets the campo 1.
	 *
	 * @return the campo 1
	 */
	public int getCampo1() {
		return this.campo1;
	}

	/**
	 * Sets the campo 1.
	 *
	 * @param campo1 the new campo 1
	 */
	public void setCampo1(int campo1) {
		this.campo1 = campo1;
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
	 * Gets the cvetxt.
	 *
	 * @return the cvetxt
	 */
	public String getCvetxt() {
		return this.cvetxt;
	}

	/**
	 * Sets the cvetxt.
	 *
	 * @param cvetxt the new cvetxt
	 */
	public void setCvetxt(String cvetxt) {
		this.cvetxt = cvetxt;
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
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(Long idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public Long getIdsector() {
		return idsector;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRef() {
		return ref;
	}

	public void setRef(Integer ref) {
		this.ref = ref;
	}

	/**
	 * Gets the autoi as list.
	 *
	 * @return the autoi as list
	 */
	public List<BigDecimal> getAutoiAsList() {
		return Arrays.asList(new BigDecimal[] { getAutoi1(), getAutoi2(), getAutoi3(), getAutoi4(), getAutoi5(),
				getAutoi6(), getAutoi7(), getAutoi8(), getAutoi9(), getAutoi10(), getAutoi11(), getAutoi12() });
	}

	/**
	 * Sum autoi to 13.
	 */
	public void sumAutoiTo13() {
		BigDecimal sum = new BigDecimal(0.0);
		for (BigDecimal i : getAutoiAsList()) {
			if (i != null) {
				System.out.println("ADDING " + i);
				sum = sum.add(i);
			}
		}
		setAutoi13(sum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getEne()
	 */
	@Transient
	@Override
	public BigDecimal getEne() {
		return getAutoi1();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setEne(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setEne(BigDecimal val) {
		setAutoi1(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getFeb()
	 */
	@Transient
	@Override
	public BigDecimal getFeb() {
		return getAutoi2();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setFeb(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setFeb(BigDecimal val) {
		setAutoi2(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getMar()
	 */
	@Transient
	@Override
	public BigDecimal getMar() {
		return getAutoi3();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setMar(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setMar(BigDecimal val) {
		setAutoi3(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getAbr()
	 */
	@Transient
	@Override
	public BigDecimal getAbr() {
		return getAutoi4();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setAbr(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setAbr(BigDecimal val) {
		setAutoi4(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getMay()
	 */
	@Transient
	@Override
	public BigDecimal getMay() {
		return getAutoi5();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setMay(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setMay(BigDecimal val) {
		setAutoi5(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getJun()
	 */
	@Transient
	@Override
	public BigDecimal getJun() {
		return getAutoi6();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setJun(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setJun(BigDecimal val) {
		setAutoi6(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getJul()
	 */
	@Transient
	@Override
	public BigDecimal getJul() {
		return getAutoi7();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setJul(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setJul(BigDecimal val) {
		setAutoi7(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getAgo()
	 */
	@Transient
	@Override
	public BigDecimal getAgo() {
		return getAutoi8();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setAgo(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setAgo(BigDecimal val) {
		setAutoi8(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getSep()
	 */
	@Transient
	@Override
	public BigDecimal getSep() {
		return getAutoi9();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setSep(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setSep(BigDecimal val) {
		setAutoi9(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getOct()
	 */
	@Transient
	@Override
	public BigDecimal getOct() {
		return getAutoi10();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setOct(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setOct(BigDecimal val) {
		setAutoi10(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getNov()
	 */
	@Transient
	@Override
	public BigDecimal getNov() {
		return getAutoi11();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setNov(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setNov(BigDecimal val) {
		setAutoi11(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#getDic()
	 */
	@Transient
	@Override
	public BigDecimal getDic() {
		return getAutoi12();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.MonthlyAbstractEntity#setDic(java.math.
	 * BigDecimal)
	 */
	@Transient
	@Override
	public void setDic(BigDecimal val) {
		setAutoi12(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	public String toString() {
		return new StringBuilder("Ingreso[").append(" id:").append(getId()).append(", cuenta:").append(cuenta)
				.append(", scta:").append(scta).append(", sscta:").append(sscta).append(", ssscta:").append(ssscta)
				.append(", sssscta:").append(sssscta).append(", autoi1:").append(autoi1).append(", autoi2:")
				.append(autoi2).append(", autoi3:").append(autoi3).append(", autoi4:").append(autoi4)
				.append(", autoi5:").append(autoi5).append(", autoi6:").append(autoi6).append(", autoi7:")
				.append(autoi7).append(", autoi8:").append(autoi8).append(", autoi9:").append(autoi9)
				.append(", autoi10:").append(autoi10).append(", autoi11:").append(autoi11).append(", autoi12:")
				.append(autoi12).append(", autoi13:").append(autoi13).append(", campo1:").append(campo1)
				.append(", clvfuen:").append(clvfuen).append(", cvetxt:").append(cvetxt).append(", idsector:")
				.append(idsector).append("]").toString();

	}
}
