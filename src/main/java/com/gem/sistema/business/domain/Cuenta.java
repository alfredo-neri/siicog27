package com.gem.sistema.business.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;
import com.gem.sistema.business.service.catalogos.AccountServiceImpl;
import com.gem.sistema.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Cuenta.
 */
@Entity
@Table(name = "cuenta")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "sp_acumulacion_saldos", procedureName = "SP_ACUMULA_SALDOS", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_SECTOR", type = Integer.class) }) })
public class Cuenta extends AbstractEntity {

	/** The abonos 1. */
	@Column(name = "ABONOS_1")
	private BigDecimal abonos1 = BigDecimal.ZERO;

	/** The abonos 10. */
	@Column(name = "ABONOS_10")
	private BigDecimal abonos10 = BigDecimal.ZERO;

	/** The abonos 11. */
	@Column(name = "ABONOS_11")
	private BigDecimal abonos11 = BigDecimal.ZERO;

	/** The abonos 12. */
	@Column(name = "ABONOS_12")
	private BigDecimal abonos12 = BigDecimal.ZERO;

	/** The abonos 13. */
	@Column(name = "ABONOS_13")
	private BigDecimal abonos13 = BigDecimal.ZERO;

	/** The abonos 14. */
	@Column(name = "ABONOS_14")
	private BigDecimal abonos14 = BigDecimal.ZERO;

	/** The abonos 15. */
	@Column(name = "ABONOS_15")
	private BigDecimal abonos15 = BigDecimal.ZERO;

	/** The abonos 16. */
	@Column(name = "ABONOS_16")
	private BigDecimal abonos16 = BigDecimal.ZERO;

	/** The abonos 2. */
	@Column(name = "ABONOS_2")
	private BigDecimal abonos2 = BigDecimal.ZERO;

	/** The abonos 3. */
	@Column(name = "ABONOS_3")
	private BigDecimal abonos3 = BigDecimal.ZERO;

	/** The abonos 4. */
	@Column(name = "ABONOS_4")
	private BigDecimal abonos4 = BigDecimal.ZERO;

	/** The abonos 5. */
	@Column(name = "ABONOS_5")
	private BigDecimal abonos5 = BigDecimal.ZERO;

	/** The abonos 6. */
	@Column(name = "ABONOS_6")
	private BigDecimal abonos6 = BigDecimal.ZERO;

	/** The abonos 7. */
	@Column(name = "ABONOS_7")
	private BigDecimal abonos7 = BigDecimal.ZERO;

	/** The abonos 8. */
	@Column(name = "ABONOS_8")
	private BigDecimal abonos8 = BigDecimal.ZERO;

	/** The abonos 9. */
	@Column(name = "ABONOS_9")
	private BigDecimal abonos9 = BigDecimal.ZERO;

	/** The capcta. */
	private String capcta;

	/** The cargos 1. */
	@Column(name = "CARGOS_1")
	private BigDecimal cargos1 = BigDecimal.ZERO;

	/** The cargos 10. */
	@Column(name = "CARGOS_10")
	private BigDecimal cargos10 = BigDecimal.ZERO;

	/** The cargos 11. */
	@Column(name = "CARGOS_11")
	private BigDecimal cargos11 = BigDecimal.ZERO;

	/** The cargos 12. */
	@Column(name = "CARGOS_12")
	private BigDecimal cargos12 = BigDecimal.ZERO;

	/** The cargos 13. */
	@Column(name = "CARGOS_13")
	private BigDecimal cargos13 = BigDecimal.ZERO;

	/** The cargos 14. */
	@Column(name = "CARGOS_14")
	private BigDecimal cargos14 = BigDecimal.ZERO;

	/** The cargos 15. */
	@Column(name = "CARGOS_15")
	private BigDecimal cargos15 = BigDecimal.ZERO;

	/** The cargos 16. */
	@Column(name = "CARGOS_16")
	private BigDecimal cargos16 = BigDecimal.ZERO;

	/** The cargos 2. */
	@Column(name = "CARGOS_2")
	private BigDecimal cargos2 = BigDecimal.ZERO;

	/** The cargos 3. */
	@Column(name = "CARGOS_3")
	private BigDecimal cargos3 = BigDecimal.ZERO;

	/** The cargos 4. */
	@Column(name = "CARGOS_4")
	private BigDecimal cargos4 = BigDecimal.ZERO;

	/** The cargos 5. */
	@Column(name = "CARGOS_5")
	private BigDecimal cargos5 = BigDecimal.ZERO;

	/** The cargos 6. */
	@Column(name = "CARGOS_6")
	private BigDecimal cargos6 = BigDecimal.ZERO;

	/** The cargos 7. */
	@Column(name = "CARGOS_7")
	private BigDecimal cargos7 = BigDecimal.ZERO;

	/** The cargos 8. */
	@Column(name = "CARGOS_8")
	private BigDecimal cargos8 = BigDecimal.ZERO;

	/** The cargos 9. */
	@Column(name = "CARGOS_9")
	private BigDecimal cargos9 = BigDecimal.ZERO;

	/** The cuenta. */
	@Column(name = "cuenta")
	private String cuenta = StringUtils.EMPTY;

	/** The feccap. */
	private Date feccap = Calendar.getInstance().getTime();

	/** The nivcta. */
	@Column(name = "nivcta")
	private String nivcta = StringUtils.EMPTY;

	/** The nomcta. */
	@Column(name = "nomcta")
	private String nomcta = StringUtils.EMPTY;
	

	/** The notcta. */
	private Integer notcta;

	/** The salini. */
	private BigDecimal salini;
	
	/** The scuenta. */
	@Column(name = "scta")
	private String scuenta = StringUtils.EMPTY;

	/** The sscuenta. */
	@Column(name = "sscta")
	private String sscuenta = StringUtils.EMPTY;

	/** The ssscuenta. */
	@Column(name = "ssscta")
	private String ssscuenta = StringUtils.EMPTY;

	/** The sssscuenta. */
	@Column(name = "sssscta")
	private String sssscuenta = StringUtils.EMPTY;

	/** The stacta. */
	private String stacta = StringUtils.EMPTY;

	/** The xnicta. */
	private Integer xnicta;

	/** The ultniv. */
	@IgnoredQuery
	@Transient
	private String ultniv;

	/** The editable. */
	@IgnoredQuery
	@Transient
	private Boolean editable = Boolean.TRUE;

	/** The deleteable. */
	@IgnoredQuery
	@Transient
	private Boolean deleteable;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	@Column(name = "IDSECTOR")
	private Long idsector;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;
	
	private BigDecimal saliniant;

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name="cuenta", referencedColumnName="cuenta", insertable=false,
	 * updatable=false) private Maycta maycta;
	 */

	/**
	 * Instantiates a new cuenta.
	 */
	public Cuenta() {
	}

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param cuenta     the cuenta
	 * @param scuenta    the scuenta
	 * @param sscuenta   the sscuenta
	 * @param ssscuenta  the ssscuenta
	 * @param sssscuenta the sssscuenta
	 * @param nomcta     the nomcta
	 * @param salini     the salini
	 * @param nivcta     the nivcta
	 * @param stacta     the stacta
	 * @param notcta     the notcta
	 */
	public Cuenta(String cuenta, String scuenta, String sscuenta, String ssscuenta, String sssscuenta, String nomcta,
			 BigDecimal salini, String nivcta, String stacta, Integer notcta) {
		this.cuenta = cuenta;
		this.scuenta = scuenta;
		this.sscuenta = sscuenta;
		this.ssscuenta = ssscuenta;
		this.sssscuenta = sssscuenta;
		this.nomcta = nomcta;
		this.salini = salini;
		this.nivcta = nivcta;
		this.stacta = stacta;
		this.notcta = notcta;

	}

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param cuenta     the cuenta
	 * @param scuenta    the scuenta
	 * @param sscuenta   the sscuenta
	 * @param ssscuenta  the ssscuenta
	 * @param sssscuenta the sssscuenta
	 * @param nomcta     the nomcta
	 * @param nivcta     the nivcta
	 * @param xnicta     the xnicta
	 * @param capcta     the capcta
	 * @param notcta     the notcta
	 * @param stacta     the stacta
	 * @param idsector   the idsector
	 */
	public Cuenta(String cuenta, String scuenta, String sscuenta, String ssscuenta, String sssscuenta, String nomcta,
			      String nivcta, Integer xnicta, String capcta, Integer notcta, String stacta, Long idsector) {
		this.cuenta = cuenta;
		this.scuenta = scuenta;
		this.sscuenta = sscuenta;
		this.ssscuenta = ssscuenta;
		this.sssscuenta = sssscuenta;
		this.nomcta = nomcta;
		this.nivcta = nivcta;
		this.xnicta = xnicta;
		this.capcta = capcta;
		this.notcta = notcta;
		this.stacta = stacta;
		this.idsector = idsector;
	}

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param cuenta     the cuenta
	 * @param scuenta    the scuenta
	 * @param sscuenta   the sscuenta
	 * @param ssscuenta  the ssscuenta
	 * @param sssscuenta the sssscuenta
	 * @param nomcta     the nomcta
	 * @param nivcta     the nivcta
	 * @param xnicta     the xnicta
	 * @param capcta     the capcta
	 * @param notcta     the notcta
	 * @param stacta     the stacta
	 * @param salini     the salini
	 * @param idsector   the idsector
	 */
	public Cuenta(String cuenta, String scuenta, String sscuenta, String ssscuenta, String sssscuenta, String nomcta,
				String nivcta, Integer xnicta, String capcta, Integer notcta, String stacta, BigDecimal salini,
			Long idsector) {
		this.cuenta = cuenta;
		this.scuenta = scuenta;
		this.sscuenta = sscuenta;
		this.ssscuenta = ssscuenta;
		this.sssscuenta = sssscuenta;
		this.nomcta = nomcta;
		this.nivcta = nivcta;
		this.xnicta = xnicta;
		this.capcta = capcta;
		this.notcta = notcta;
		this.stacta = stacta;
		this.idsector = idsector;
		this.salini = salini;

	}

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param cuenta     the cuenta
	 * @param scuenta    the scuenta
	 * @param sscuenta   the sscuenta
	 * @param ssscuenta  the ssscuenta
	 * @param sssscuenta the sssscuenta
	 * @param nomcta     the nomcta
	 * @param nivcta     the nivcta
	 * @param xnicta     the xnicta
	 * @param capcta     the capcta
	 * @param idsector   the idsector
	 */
	public Cuenta(String cuenta, String scuenta, String sscuenta, String ssscuenta, String sssscuenta, String nomcta
			,String stacta, String nivcta, Integer xnicta, String capcta, Long idsector) {
		this.cuenta = cuenta;
		this.scuenta = scuenta;
		this.sscuenta = sscuenta;
		this.ssscuenta = ssscuenta;
		this.sssscuenta = sssscuenta;
		this.nomcta = nomcta;
		this.stacta = stacta;
		this.nivcta = nivcta;
		this.xnicta = xnicta;
		this.capcta = capcta;
		this.idsector = idsector;
		this.salini = BigDecimal.ZERO;
		this.abonos1 = BigDecimal.ZERO;
		this.abonos2 = BigDecimal.ZERO;
		this.abonos3 = BigDecimal.ZERO;
		this.abonos4 = BigDecimal.ZERO;
		this.abonos5 = BigDecimal.ZERO;
		this.abonos6 = BigDecimal.ZERO;
		this.abonos7 = BigDecimal.ZERO;
		this.abonos8 = BigDecimal.ZERO;
		this.abonos9 = BigDecimal.ZERO;
		this.abonos10 = BigDecimal.ZERO;
		this.abonos11 = BigDecimal.ZERO;
		this.abonos12 = BigDecimal.ZERO;
		this.abonos13 = BigDecimal.ZERO;
		this.abonos14 = BigDecimal.ZERO;
		this.abonos15 = BigDecimal.ZERO;
		this.abonos16 = BigDecimal.ZERO;
		this.cargos1 = BigDecimal.ZERO;
		this.cargos2 = BigDecimal.ZERO;
		this.cargos3 = BigDecimal.ZERO;
		this.cargos4 = BigDecimal.ZERO;
		this.cargos5 = BigDecimal.ZERO;
		this.cargos6 = BigDecimal.ZERO;
		this.cargos7 = BigDecimal.ZERO;
		this.cargos8 = BigDecimal.ZERO;
		this.cargos9 = BigDecimal.ZERO;
		this.cargos10 = BigDecimal.ZERO;
		this.cargos11 = BigDecimal.ZERO;
		this.cargos12 = BigDecimal.ZERO;
		this.cargos13 = BigDecimal.ZERO;
		this.cargos14 = BigDecimal.ZERO;
		this.cargos15 = BigDecimal.ZERO;
		this.cargos16 = BigDecimal.ZERO;
		this.userid = this.capcta;
		if (this.idsector == 1) {
			this.idRef = 0l;
		} else {
			this.idRef = 1l;
		}
		this.notcta = 0;
	}

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param cuenta    the cuenta
	 * @param scuenta   the scuenta
	 * @param sscuenta  the sscuenta
	 * @param ssscuenta the ssscuenta
	 */
	public Cuenta(String cuenta, String scuenta, String sscuenta, String ssscuenta) {
		this.setId(new Long(0));
		this.cuenta = cuenta;
		this.scuenta = scuenta;
		this.sscuenta = sscuenta;
		this.ssscuenta = ssscuenta;
	}

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param cuenta     the cuenta
	 * @param scuenta    the scuenta
	 * @param sscuenta   the sscuenta
	 * @param ssscuenta  the ssscuenta
	 * @param sssscuenta the sssscuenta
	 */
	public Cuenta(String cuenta, String scuenta, String sscuenta, String ssscuenta, String sssscuenta) {
		this.setId(new Long(0));
		this.cuenta = cuenta;
		this.scuenta = scuenta;
		this.sscuenta = sscuenta;
		this.ssscuenta = ssscuenta;
		this.sssscuenta = sssscuenta;
	}

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param cuenta the cuenta
	 */
	public Cuenta(String cuenta) {
		this.setId(new Long(0));
		this.cuenta = cuenta;
	}

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param cuenta   the cuenta
	 * @param titulo   the titulo
	 * @param saldoIni the saldo ini
	 */
	public Cuenta(String cuenta, String titulo, BigDecimal saldoIni) {
		this.setId(new Long(0));
		this.cuenta = cuenta;
		this.nomcta = titulo;
		this.salini = saldoIni;
	}

	/**
	 * Gets the abonos 1.
	 *
	 * @return the abonos 1
	 */
	public BigDecimal getAbonos1() {
		return this.abonos1;
	}

	/**
	 * Sets the abonos 1.
	 *
	 * @param abonos1 the new abonos 1
	 */
	public void setAbonos1(BigDecimal abonos1) {
		this.abonos1 = abonos1;
	}

	/**
	 * Gets the abonos 10.
	 *
	 * @return the abonos 10
	 */
	public BigDecimal getAbonos10() {
		return this.abonos10;
	}

	/**
	 * Sets the abonos 10.
	 *
	 * @param abonos10 the new abonos 10
	 */
	public void setAbonos10(BigDecimal abonos10) {
		this.abonos10 = abonos10;
	}

	/**
	 * Gets the abonos 11.
	 *
	 * @return the abonos 11
	 */
	public BigDecimal getAbonos11() {
		return this.abonos11;
	}

	/**
	 * Sets the abonos 11.
	 *
	 * @param abonos11 the new abonos 11
	 */
	public void setAbonos11(BigDecimal abonos11) {
		this.abonos11 = abonos11;
	}

	/**
	 * Gets the abonos 12.
	 *
	 * @return the abonos 12
	 */
	public BigDecimal getAbonos12() {
		return this.abonos12;
	}

	/**
	 * Sets the abonos 12.
	 *
	 * @param abonos12 the new abonos 12
	 */
	public void setAbonos12(BigDecimal abonos12) {
		this.abonos12 = abonos12;
	}

	/**
	 * Gets the abonos 13.
	 *
	 * @return the abonos 13
	 */
	public BigDecimal getAbonos13() {
		return this.abonos13;
	}

	/**
	 * Sets the abonos 13.
	 *
	 * @param abonos13 the new abonos 13
	 */
	public void setAbonos13(BigDecimal abonos13) {
		this.abonos13 = abonos13;
	}

	/**
	 * Gets the abonos 14.
	 *
	 * @return the abonos 14
	 */
	public BigDecimal getAbonos14() {
		return this.abonos14;
	}

	/**
	 * Sets the abonos 14.
	 *
	 * @param abonos14 the new abonos 14
	 */
	public void setAbonos14(BigDecimal abonos14) {
		this.abonos14 = abonos14;
	}

	/**
	 * Gets the abonos 15.
	 *
	 * @return the abonos 15
	 */
	public BigDecimal getAbonos15() {
		return this.abonos15;
	}

	/**
	 * Sets the abonos 15.
	 *
	 * @param abonos15 the new abonos 15
	 */
	public void setAbonos15(BigDecimal abonos15) {
		this.abonos15 = abonos15;
	}

	/**
	 * Gets the abonos 16.
	 *
	 * @return the abonos 16
	 */
	public BigDecimal getAbonos16() {
		return this.abonos16;
	}

	/**
	 * Sets the abonos 16.
	 *
	 * @param abonos16 the new abonos 16
	 */
	public void setAbonos16(BigDecimal abonos16) {
		this.abonos16 = abonos16;
	}

	/**
	 * Gets the abonos 2.
	 *
	 * @return the abonos 2
	 */
	public BigDecimal getAbonos2() {
		return this.abonos2;
	}

	/**
	 * Sets the abonos 2.
	 *
	 * @param abonos2 the new abonos 2
	 */
	public void setAbonos2(BigDecimal abonos2) {
		this.abonos2 = abonos2;
	}

	/**
	 * Gets the abonos 3.
	 *
	 * @return the abonos 3
	 */
	public BigDecimal getAbonos3() {
		return this.abonos3;
	}

	/**
	 * Sets the abonos 3.
	 *
	 * @param abonos3 the new abonos 3
	 */
	public void setAbonos3(BigDecimal abonos3) {
		this.abonos3 = abonos3;
	}

	/**
	 * Gets the abonos 4.
	 *
	 * @return the abonos 4
	 */
	public BigDecimal getAbonos4() {
		return this.abonos4;
	}

	/**
	 * Sets the abonos 4.
	 *
	 * @param abonos4 the new abonos 4
	 */
	public void setAbonos4(BigDecimal abonos4) {
		this.abonos4 = abonos4;
	}

	/**
	 * Gets the abonos 5.
	 *
	 * @return the abonos 5
	 */
	public BigDecimal getAbonos5() {
		return this.abonos5;
	}

	/**
	 * Sets the abonos 5.
	 *
	 * @param abonos5 the new abonos 5
	 */
	public void setAbonos5(BigDecimal abonos5) {
		this.abonos5 = abonos5;
	}

	/**
	 * Gets the abonos 6.
	 *
	 * @return the abonos 6
	 */
	public BigDecimal getAbonos6() {
		return this.abonos6;
	}

	/**
	 * Sets the abonos 6.
	 *
	 * @param abonos6 the new abonos 6
	 */
	public void setAbonos6(BigDecimal abonos6) {
		this.abonos6 = abonos6;
	}

	/**
	 * Gets the abonos 7.
	 *
	 * @return the abonos 7
	 */
	public BigDecimal getAbonos7() {
		return this.abonos7;
	}

	/**
	 * Sets the abonos 7.
	 *
	 * @param abonos7 the new abonos 7
	 */
	public void setAbonos7(BigDecimal abonos7) {
		this.abonos7 = abonos7;
	}

	/**
	 * Gets the abonos 8.
	 *
	 * @return the abonos 8
	 */
	public BigDecimal getAbonos8() {
		return this.abonos8;
	}

	/**
	 * Sets the abonos 8.
	 *
	 * @param abonos8 the new abonos 8
	 */
	public void setAbonos8(BigDecimal abonos8) {
		this.abonos8 = abonos8;
	}

	/**
	 * Gets the abonos 9.
	 *
	 * @return the abonos 9
	 */
	public BigDecimal getAbonos9() {
		return this.abonos9;
	}

	/**
	 * Sets the abonos 9.
	 *
	 * @param abonos9 the new abonos 9
	 */
	public void setAbonos9(BigDecimal abonos9) {
		this.abonos9 = abonos9;
	}

	/**
	 * Gets the capcta.
	 *
	 * @return the capcta
	 */
	public String getCapcta() {
		return this.capcta;
	}

	/**
	 * Sets the capcta.
	 *
	 * @param capcta the new capcta
	 */
	public void setCapcta(String capcta) {
		this.capcta = capcta;
	}

	/**
	 * Gets the cargos 1.
	 *
	 * @return the cargos 1
	 */
	public BigDecimal getCargos1() {
		return this.cargos1;
	}

	/**
	 * Sets the cargos 1.
	 *
	 * @param cargos1 the new cargos 1
	 */
	public void setCargos1(BigDecimal cargos1) {
		this.cargos1 = cargos1;
	}

	/**
	 * Gets the cargos 10.
	 *
	 * @return the cargos 10
	 */
	public BigDecimal getCargos10() {
		return this.cargos10;
	}

	/**
	 * Sets the cargos 10.
	 *
	 * @param cargos10 the new cargos 10
	 */
	public void setCargos10(BigDecimal cargos10) {
		this.cargos10 = cargos10;
	}

	/**
	 * Gets the cargos 11.
	 *
	 * @return the cargos 11
	 */
	public BigDecimal getCargos11() {
		return this.cargos11;
	}

	/**
	 * Sets the cargos 11.
	 *
	 * @param cargos11 the new cargos 11
	 */
	public void setCargos11(BigDecimal cargos11) {
		this.cargos11 = cargos11;
	}

	/**
	 * Gets the cargos 12.
	 *
	 * @return the cargos 12
	 */
	public BigDecimal getCargos12() {
		return this.cargos12;
	}

	/**
	 * Sets the cargos 12.
	 *
	 * @param cargos12 the new cargos 12
	 */
	public void setCargos12(BigDecimal cargos12) {
		this.cargos12 = cargos12;
	}

	/**
	 * Gets the cargos 13.
	 *
	 * @return the cargos 13
	 */
	public BigDecimal getCargos13() {
		return this.cargos13;
	}

	/**
	 * Sets the cargos 13.
	 *
	 * @param cargos13 the new cargos 13
	 */
	public void setCargos13(BigDecimal cargos13) {
		this.cargos13 = cargos13;
	}

	/**
	 * Gets the cargos 14.
	 *
	 * @return the cargos 14
	 */
	public BigDecimal getCargos14() {
		return this.cargos14;
	}

	/**
	 * Sets the cargos 14.
	 *
	 * @param cargos14 the new cargos 14
	 */
	public void setCargos14(BigDecimal cargos14) {
		this.cargos14 = cargos14;
	}

	/**
	 * Gets the cargos 15.
	 *
	 * @return the cargos 15
	 */
	public BigDecimal getCargos15() {
		return this.cargos15;
	}

	/**
	 * Sets the cargos 15.
	 *
	 * @param cargos15 the new cargos 15
	 */
	public void setCargos15(BigDecimal cargos15) {
		this.cargos15 = cargos15;
	}

	/**
	 * Gets the cargos 16.
	 *
	 * @return the cargos 16
	 */
	public BigDecimal getCargos16() {
		return this.cargos16;
	}

	/**
	 * Sets the cargos 16.
	 *
	 * @param cargos16 the new cargos 16
	 */
	public void setCargos16(BigDecimal cargos16) {
		this.cargos16 = cargos16;
	}

	/**
	 * Gets the cargos 2.
	 *
	 * @return the cargos 2
	 */
	public BigDecimal getCargos2() {
		return this.cargos2;
	}

	/**
	 * Sets the cargos 2.
	 *
	 * @param cargos2 the new cargos 2
	 */
	public void setCargos2(BigDecimal cargos2) {
		this.cargos2 = cargos2;
	}

	/**
	 * Gets the cargos 3.
	 *
	 * @return the cargos 3
	 */
	public BigDecimal getCargos3() {
		return this.cargos3;
	}

	/**
	 * Sets the cargos 3.
	 *
	 * @param cargos3 the new cargos 3
	 */
	public void setCargos3(BigDecimal cargos3) {
		this.cargos3 = cargos3;
	}

	/**
	 * Gets the cargos 4.
	 *
	 * @return the cargos 4
	 */
	public BigDecimal getCargos4() {
		return this.cargos4;
	}

	/**
	 * Sets the cargos 4.
	 *
	 * @param cargos4 the new cargos 4
	 */
	public void setCargos4(BigDecimal cargos4) {
		this.cargos4 = cargos4;
	}

	/**
	 * Gets the cargos 5.
	 *
	 * @return the cargos 5
	 */
	public BigDecimal getCargos5() {
		return this.cargos5;
	}

	/**
	 * Sets the cargos 5.
	 *
	 * @param cargos5 the new cargos 5
	 */
	public void setCargos5(BigDecimal cargos5) {
		this.cargos5 = cargos5;
	}

	/**
	 * Gets the cargos 6.
	 *
	 * @return the cargos 6
	 */
	public BigDecimal getCargos6() {
		return this.cargos6;
	}

	/**
	 * Sets the cargos 6.
	 *
	 * @param cargos6 the new cargos 6
	 */
	public void setCargos6(BigDecimal cargos6) {
		this.cargos6 = cargos6;
	}

	/**
	 * Gets the cargos 7.
	 *
	 * @return the cargos 7
	 */
	public BigDecimal getCargos7() {
		return this.cargos7;
	}

	/**
	 * Sets the cargos 7.
	 *
	 * @param cargos7 the new cargos 7
	 */
	public void setCargos7(BigDecimal cargos7) {
		this.cargos7 = cargos7;
	}

	/**
	 * Gets the cargos 8.
	 *
	 * @return the cargos 8
	 */
	public BigDecimal getCargos8() {
		return this.cargos8;
	}

	/**
	 * Sets the cargos 8.
	 *
	 * @param cargos8 the new cargos 8
	 */
	public void setCargos8(BigDecimal cargos8) {
		this.cargos8 = cargos8;
	}

	/**
	 * Gets the cargos 9.
	 *
	 * @return the cargos 9
	 */
	public BigDecimal getCargos9() {
		return this.cargos9;
	}

	/**
	 * Sets the cargos 9.
	 *
	 * @param cargos9 the new cargos 9
	 */
	public void setCargos9(BigDecimal cargos9) {
		this.cargos9 = cargos9;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	@ColumnFile(indexColumn = 1)
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
	 * Gets the nivcta.
	 *
	 * @return the nivcta
	 */
	@ColumnFile(indexColumn = 8)
	public String getNivcta() {
		return this.nivcta;
	}

	/**
	 * Sets the nivcta.
	 *
	 * @param nivcta the new nivcta
	 */
	public void setNivcta(String nivcta) {
		this.nivcta = nivcta;
	}

	/**
	 * Gets the nomcta.
	 *
	 * @return the nomcta
	 */
	@ColumnFile(indexColumn = 6)
	public String getNomcta() {
		return this.nomcta;
	}

	/**
	 * Sets the nomcta.
	 *
	 * @param nomcta the new nomcta
	 */
	public void setNomcta(String nomcta) {
		this.nomcta = nomcta;
	}
	

	/**
	 * Gets the notcta.
	 *
	 * @return the notcta
	 */
	@ColumnFile(indexColumn = 11)
	public Integer getNotcta() {
		return this.notcta;
	}

	/**
	 * Sets the notcta.
	 *
	 * @param notcta the new notcta
	 */
	public void setNotcta(Integer notcta) {
		this.notcta = notcta;
	}

	/**
	 * Gets the salini.
	 *
	 * @return the salini
	 */
	@ColumnFile(indexColumn = 7)
	public BigDecimal getSalini() {
		return this.salini;
	}

	/**
	 * Sets the salini.
	 *
	 * @param salini the new salini
	 */
	public void setSalini(BigDecimal salini) {
		this.salini = salini;
	}

	/**
	 * Gets the scuenta.
	 *
	 * @return the scuenta
	 */
	@ColumnFile(indexColumn = 2)
	public String getScuenta() {
		return scuenta;
	}

	/**
	 * Sets the scuenta.
	 *
	 * @param scuenta the new scuenta
	 */
	public void setScuenta(String scuenta) {
		this.scuenta = scuenta;
	}

	/**
	 * Gets the sscuenta.
	 *
	 * @return the sscuenta
	 */
	@ColumnFile(indexColumn = 3)
	public String getSscuenta() {
		return sscuenta;
	}

	/**
	 * Sets the sscuenta.
	 *
	 * @param sscuenta the new sscuenta
	 */
	public void setSscuenta(String sscuenta) {
		this.sscuenta = sscuenta;
	}

	/**
	 * Gets the ssscuenta.
	 *
	 * @return the ssscuenta
	 */
	@ColumnFile(indexColumn = 4)
	public String getSsscuenta() {
		return ssscuenta;
	}

	/**
	 * Sets the ssscuenta.
	 *
	 * @param ssscuenta the new ssscuenta
	 */
	public void setSsscuenta(String ssscuenta) {
		this.ssscuenta = ssscuenta;
	}

	/**
	 * Gets the sssscuenta.
	 *
	 * @return the sssscuenta
	 */
	@ColumnFile(indexColumn = 5)
	public String getSssscuenta() {
		return sssscuenta;
	}

	/**
	 * Sets the sssscuenta.
	 *
	 * @param sssscuenta the new sssscuenta
	 */
	public void setSssscuenta(String sssscuenta) {
		this.sssscuenta = sssscuenta;
	}

	/**
	 * Gets the ultniv.
	 *
	 * @return the ultniv
	 */
	public String getUltniv() {
		return ultniv;
	}

	/**
	 * Sets the ultniv.
	 *
	 * @param ultniv the new ultniv
	 */
	public void setUltniv(String ultniv) {
		this.ultniv = ultniv;
	}

	/**
	 * Gets the editable.
	 *
	 * @return the editable
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable the new editable
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	/**
	 * Gets the stacta.
	 *
	 * @return the stacta
	 */
	@ColumnFile(indexColumn = 9)
	public String getStacta() {
		return this.stacta;
	}

	/**
	 * Sets the stacta.
	 *
	 * @param stacta the new stacta
	 */
	public void setStacta(String stacta) {
		this.stacta = stacta;
	}

	/**
	 * Gets the xnicta.
	 *
	 * @return the xnicta
	 */
	@ColumnFile(indexColumn = 10)
	public Integer getXnicta() {
		return this.xnicta;
	}

	/**
	 * Sets the xnicta.
	 *
	 * @param xnicta the new xnicta
	 */
	public void setXnicta(Integer xnicta) {
		this.xnicta = xnicta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cuenta [id=" + getId() + ", cuenta=" + cuenta + ", scuenta=" + scuenta + ", sscuenta=" + sscuenta
				+ ", ssscuenta=" + ssscuenta + ", sssscuenta=" + sssscuenta + ", nomcta=" + nomcta + ", salini="
				+ salini + ", nivcta=" + nivcta + ", stacta=" + stacta + ", notcta=" + notcta + ", xnicta=" + xnicta
				+ ", ultniv=" + ultniv + "]";
	}

	/**
	 * Gets the deleteable.
	 *
	 * @return the deleteable
	 */
	public Boolean getDeleteable() {
		if (null == deleteable) {
			deleteable = AccountServiceImpl.getCuentaLevel(this) > 1
					&& !this.cuenta.matches(Constants.CTA_NO_DELETE_REGEXP);
		}
		return deleteable;
	}

	/**
	 * Sets the deleteable.
	 *
	 * @param deleteable the deleteable to set
	 */
	public void setDeleteable(Boolean deleteable) {
		this.deleteable = deleteable;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the idRef
	 */
	public Long getIdRef() {
		return idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the idRef to set
	 */
	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	/**
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public Long getIdsector() {
		return idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the idsector to set
	 */
	public void setIdsector(Long idsector) {
		this.idsector = idsector;
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
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/*
	 * public Maycta getMaycta(){ return maycta; }
	 * 
	 * public void setMaycta(Maycta maycta){ this.maycta = maycta; }
	 */


	/**
	 * Gets the abonos as list.
	 *
	 * @return the abonos as list
	 */
	public List<BigDecimal> getAbonosAsList() {
		return new ArrayList<BigDecimal>() {
			{
				add(getAbonos1());
				add(getAbonos2());
				add(getAbonos3());
				add(getAbonos4());
				add(getAbonos5());
				add(getAbonos6());
				add(getAbonos7());
				add(getAbonos8());
				add(getAbonos9());
				add(getAbonos10());
				add(getAbonos11());
				add(getAbonos12());
				add(getAbonos13());
				add(getAbonos14());
				add(getAbonos15());
				add(getAbonos16());
			}
		};
	}

	/**
	 * Gets the cargos as list.
	 *
	 * @return the cargos as list
	 */
	public List<BigDecimal> getCargosAsList() {
		return new ArrayList<BigDecimal>() {
			{
				add(getCargos1());
				add(getCargos2());
				add(getCargos3());
				add(getCargos4());
				add(getCargos5());
				add(getCargos6());
				add(getCargos7());
				add(getCargos8());
				add(getCargos9());
				add(getCargos10());
				add(getCargos11());
				add(getCargos12());
				add(getCargos13());
				add(getCargos14());
				add(getCargos15());
				add(getCargos16());
			}
		};
	}

	public BigDecimal getSaliniant() {
		return saliniant;
	}

	public void setSaliniant(BigDecimal saliniant) {
		this.saliniant = saliniant;
	}
	
	
}
