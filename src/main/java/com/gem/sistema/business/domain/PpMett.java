package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PP_METT database table.
 * 
 */
@Entity
@Table(name = "PP_METT")
@NamedQuery(name = "PpMett.findAll", query = "SELECT p FROM PpMett p")
public class PpMett implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -248130307418457762L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID\"")
	private Long id;

	/** The ampli 1. */
	@Column(name = "AMPLI_1")
	private BigDecimal ampli1;

	/** The ampli 2. */
	@Column(name = "AMPLI_2")
	private BigDecimal ampli2;

	/** The ampli 3. */
	@Column(name = "AMPLI_3")
	private BigDecimal ampli3;

	/** The ampli 4. */
	@Column(name = "AMPLI_4")
	private BigDecimal ampli4;

	/** The ampli 5. */
	@Column(name = "AMPLI_5")
	private BigDecimal ampli5;

	/** The anio cap. */
	@Column(name = "ANIO_CAP")
	private Integer anioCap;

	/** The campo 0. */
	private String campo0;

	/** The campo 1. */
	private String campo1;

	/** The campo 2. */
	private String campo2;

	/** The campo 3. */
	private String campo3;

	/** The campo 4. */
	private String campo4;

	/** The campo 5. */
	private String campo5;

	/** The can met. */
	@Column(name = "CAN_MET")
	private BigDecimal canMet;

	/** The can meta. */
	@Column(name = "CAN_META")
	private BigDecimal canMeta;

	/** The can meti. */
	@Column(name = "CAN_METI")
	private BigDecimal canMeti;

	/** The can metia. */
	@Column(name = "CAN_METIA")
	private BigDecimal canMetia;

	/** The can metic 1. */
	@Column(name = "CAN_METIC_1")
	private BigDecimal canMetic1;

	/** The can metic 10. */
	@Column(name = "CAN_METIC_10")
	private BigDecimal canMetic10;

	/** The can metic 11. */
	@Column(name = "CAN_METIC_11")
	private BigDecimal canMetic11;

	/** The can metic 12. */
	@Column(name = "CAN_METIC_12")
	private BigDecimal canMetic12;

	/** The can metic 13. */
	@Column(name = "CAN_METIC_13")
	private BigDecimal canMetic13;

	/** The can metic 2. */
	@Column(name = "CAN_METIC_2")
	private BigDecimal canMetic2;

	/** The can metic 3. */
	@Column(name = "CAN_METIC_3")
	private BigDecimal canMetic3;

	/** The can metic 4. */
	@Column(name = "CAN_METIC_4")
	private BigDecimal canMetic4;

	/** The can metic 5. */
	@Column(name = "CAN_METIC_5")
	private BigDecimal canMetic5;

	/** The can metic 6. */
	@Column(name = "CAN_METIC_6")
	private BigDecimal canMetic6;

	/** The can metic 7. */
	@Column(name = "CAN_METIC_7")
	private BigDecimal canMetic7;

	/** The can metic 8. */
	@Column(name = "CAN_METIC_8")
	private BigDecimal canMetic8;

	/** The can metic 9. */
	@Column(name = "CAN_METIC_9")
	private BigDecimal canMetic9;

	/** The cant avan 1. */
	@Column(name = "CANT_AVAN_1")
	private BigDecimal cantAvan1;

	/** The cant avan 10. */
	@Column(name = "CANT_AVAN_10")
	private BigDecimal cantAvan10;

	/** The cant avan 11. */
	@Column(name = "CANT_AVAN_11")
	private BigDecimal cantAvan11;

	/** The cant avan 12. */
	@Column(name = "CANT_AVAN_12")
	private BigDecimal cantAvan12;

	/** The cant avan 13. */
	@Column(name = "CANT_AVAN_13")
	private BigDecimal cantAvan13;

	/** The cant avan 2. */
	@Column(name = "CANT_AVAN_2")
	private BigDecimal cantAvan2;

	/** The cant avan 3. */
	@Column(name = "CANT_AVAN_3")
	private BigDecimal cantAvan3;

	/** The cant avan 4. */
	@Column(name = "CANT_AVAN_4")
	private BigDecimal cantAvan4;

	/** The cant avan 5. */
	@Column(name = "CANT_AVAN_5")
	private BigDecimal cantAvan5;

	/** The cant avan 6. */
	@Column(name = "CANT_AVAN_6")
	private BigDecimal cantAvan6;

	/** The cant avan 7. */
	@Column(name = "CANT_AVAN_7")
	private BigDecimal cantAvan7;

	/** The cant avan 8. */
	@Column(name = "CANT_AVAN_8")
	private BigDecimal cantAvan8;

	/** The cant avan 9. */
	@Column(name = "CANT_AVAN_9")
	private BigDecimal cantAvan9;

	/** The clavedep. */
	private Integer clavedep;

	/** The clvdep. */
	private String clvdep;

	/** The clvfuen. */
	private String clvfuen;

	/** The clvmet. */
	private Integer clvmet;

	/** The clvnep. */
	private String clvnep;

	/** The clvreg. */
	private String clvreg;

	/** The costo. */
	private BigDecimal costo;

	/** The formula. */
	private String formula;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The nom ind. */
	@Column(name = "NOM_IND")
	private String nomInd;

	/** The nom met. */
	@Column(name = "NOM_MET")
	private String nomMet;

	/** The num ver. */
	@Column(name = "NUM_VER")
	private Integer numVer;

	/** The progante. */
	private BigDecimal progante;

	/** The redu 1. */
	@Column(name = "REDU_1")
	private BigDecimal redu1;

	/** The redu 2. */
	@Column(name = "REDU_2")
	private BigDecimal redu2;

	/** The redu 3. */
	@Column(name = "REDU_3")
	private BigDecimal redu3;

	/** The redu 4. */
	@Column(name = "REDU_4")
	private BigDecimal redu4;

	/** The redu 5. */
	@Column(name = "REDU_5")
	private BigDecimal redu5;

	/** The uni med. */
	@Column(name = "UNI_MED")
	private String uniMed;

	/** The userid. */
	@Column(name = "\"USERID\"")
	private String userid;

	/** The varfin 1. */
	@Column(name = "VARFIN_1")
	private String varfin1;

	/** The varfin 2. */
	@Column(name = "VARFIN_2")
	private String varfin2;

	/** The varfin 3. */
	@Column(name = "VARFIN_3")
	private String varfin3;

	/** The varfin 4. */
	@Column(name = "VARFIN_4")
	private String varfin4;

	/** The varfis 1. */
	@Column(name = "VARFIS_1")
	private String varfis1;

	/** The varfis 2. */
	@Column(name = "VARFIS_2")
	private String varfis2;

	/** The varfis 3. */
	@Column(name = "VARFIS_3")
	private String varfis3;

	/** The varfis 4. */
	@Column(name = "VARFIS_4")
	private String varfis4;

	/**
	 * Instantiates a new pp mett.
	 */
	public PpMett() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
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
	 * Gets the ampli 1.
	 *
	 * @return the ampli 1
	 */
	public BigDecimal getAmpli1() {
		return this.ampli1;
	}

	/**
	 * Sets the ampli 1.
	 *
	 * @param ampli1 the new ampli 1
	 */
	public void setAmpli1(BigDecimal ampli1) {
		this.ampli1 = ampli1;
	}

	/**
	 * Gets the ampli 2.
	 *
	 * @return the ampli 2
	 */
	public BigDecimal getAmpli2() {
		return this.ampli2;
	}

	/**
	 * Sets the ampli 2.
	 *
	 * @param ampli2 the new ampli 2
	 */
	public void setAmpli2(BigDecimal ampli2) {
		this.ampli2 = ampli2;
	}

	/**
	 * Gets the ampli 3.
	 *
	 * @return the ampli 3
	 */
	public BigDecimal getAmpli3() {
		return this.ampli3;
	}

	/**
	 * Sets the ampli 3.
	 *
	 * @param ampli3 the new ampli 3
	 */
	public void setAmpli3(BigDecimal ampli3) {
		this.ampli3 = ampli3;
	}

	/**
	 * Gets the ampli 4.
	 *
	 * @return the ampli 4
	 */
	public BigDecimal getAmpli4() {
		return this.ampli4;
	}

	/**
	 * Sets the ampli 4.
	 *
	 * @param ampli4 the new ampli 4
	 */
	public void setAmpli4(BigDecimal ampli4) {
		this.ampli4 = ampli4;
	}

	/**
	 * Gets the ampli 5.
	 *
	 * @return the ampli 5
	 */
	public BigDecimal getAmpli5() {
		return this.ampli5;
	}

	/**
	 * Sets the ampli 5.
	 *
	 * @param ampli5 the new ampli 5
	 */
	public void setAmpli5(BigDecimal ampli5) {
		this.ampli5 = ampli5;
	}

	/**
	 * Gets the anio cap.
	 *
	 * @return the anio cap
	 */
	public Integer getAnioCap() {
		return this.anioCap;
	}

	/**
	 * Sets the anio cap.
	 *
	 * @param anioCap the new anio cap
	 */
	public void setAnioCap(Integer anioCap) {
		this.anioCap = anioCap;
	}

	/**
	 * Gets the campo 0.
	 *
	 * @return the campo 0
	 */
	public String getCampo0() {
		return this.campo0;
	}

	/**
	 * Sets the campo 0.
	 *
	 * @param campo0 the new campo 0
	 */
	public void setCampo0(String campo0) {
		this.campo0 = campo0;
	}

	/**
	 * Gets the campo 1.
	 *
	 * @return the campo 1
	 */
	public String getCampo1() {
		return this.campo1;
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
	public String getCampo2() {
		return this.campo2;
	}

	/**
	 * Sets the campo 2.
	 *
	 * @param campo2 the new campo 2
	 */
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	/**
	 * Gets the campo 3.
	 *
	 * @return the campo 3
	 */
	public String getCampo3() {
		return this.campo3;
	}

	/**
	 * Sets the campo 3.
	 *
	 * @param campo3 the new campo 3
	 */
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	/**
	 * Gets the campo 4.
	 *
	 * @return the campo 4
	 */
	public String getCampo4() {
		return this.campo4;
	}

	/**
	 * Sets the campo 4.
	 *
	 * @param campo4 the new campo 4
	 */
	public void setCampo4(String campo4) {
		this.campo4 = campo4;
	}

	/**
	 * Gets the campo 5.
	 *
	 * @return the campo 5
	 */
	public String getCampo5() {
		return this.campo5;
	}

	/**
	 * Sets the campo 5.
	 *
	 * @param campo5 the new campo 5
	 */
	public void setCampo5(String campo5) {
		this.campo5 = campo5;
	}

	/**
	 * Gets the can met.
	 *
	 * @return the can met
	 */
	public BigDecimal getCanMet() {
		return this.canMet;
	}

	/**
	 * Sets the can met.
	 *
	 * @param canMet the new can met
	 */
	public void setCanMet(BigDecimal canMet) {
		this.canMet = canMet;
	}

	/**
	 * Gets the can meta.
	 *
	 * @return the can meta
	 */
	public BigDecimal getCanMeta() {
		return this.canMeta;
	}

	/**
	 * Sets the can meta.
	 *
	 * @param canMeta the new can meta
	 */
	public void setCanMeta(BigDecimal canMeta) {
		this.canMeta = canMeta;
	}

	/**
	 * Gets the can meti.
	 *
	 * @return the can meti
	 */
	public BigDecimal getCanMeti() {
		return this.canMeti;
	}

	/**
	 * Sets the can meti.
	 *
	 * @param canMeti the new can meti
	 */
	public void setCanMeti(BigDecimal canMeti) {
		this.canMeti = canMeti;
	}

	/**
	 * Gets the can metia.
	 *
	 * @return the can metia
	 */
	public BigDecimal getCanMetia() {
		return this.canMetia;
	}

	/**
	 * Sets the can metia.
	 *
	 * @param canMetia the new can metia
	 */
	public void setCanMetia(BigDecimal canMetia) {
		this.canMetia = canMetia;
	}

	/**
	 * Gets the can metic 1.
	 *
	 * @return the can metic 1
	 */
	public BigDecimal getCanMetic1() {
		return this.canMetic1;
	}

	/**
	 * Sets the can metic 1.
	 *
	 * @param canMetic1 the new can metic 1
	 */
	public void setCanMetic1(BigDecimal canMetic1) {
		this.canMetic1 = canMetic1;
	}

	/**
	 * Gets the can metic 10.
	 *
	 * @return the can metic 10
	 */
	public BigDecimal getCanMetic10() {
		return this.canMetic10;
	}

	/**
	 * Sets the can metic 10.
	 *
	 * @param canMetic10 the new can metic 10
	 */
	public void setCanMetic10(BigDecimal canMetic10) {
		this.canMetic10 = canMetic10;
	}

	/**
	 * Gets the can metic 11.
	 *
	 * @return the can metic 11
	 */
	public BigDecimal getCanMetic11() {
		return this.canMetic11;
	}

	/**
	 * Sets the can metic 11.
	 *
	 * @param canMetic11 the new can metic 11
	 */
	public void setCanMetic11(BigDecimal canMetic11) {
		this.canMetic11 = canMetic11;
	}

	/**
	 * Gets the can metic 12.
	 *
	 * @return the can metic 12
	 */
	public BigDecimal getCanMetic12() {
		return this.canMetic12;
	}

	/**
	 * Sets the can metic 12.
	 *
	 * @param canMetic12 the new can metic 12
	 */
	public void setCanMetic12(BigDecimal canMetic12) {
		this.canMetic12 = canMetic12;
	}

	/**
	 * Gets the can metic 13.
	 *
	 * @return the can metic 13
	 */
	public BigDecimal getCanMetic13() {
		return this.canMetic13;
	}

	/**
	 * Sets the can metic 13.
	 *
	 * @param canMetic13 the new can metic 13
	 */
	public void setCanMetic13(BigDecimal canMetic13) {
		this.canMetic13 = canMetic13;
	}

	/**
	 * Gets the can metic 2.
	 *
	 * @return the can metic 2
	 */
	public BigDecimal getCanMetic2() {
		return this.canMetic2;
	}

	/**
	 * Sets the can metic 2.
	 *
	 * @param canMetic2 the new can metic 2
	 */
	public void setCanMetic2(BigDecimal canMetic2) {
		this.canMetic2 = canMetic2;
	}

	/**
	 * Gets the can metic 3.
	 *
	 * @return the can metic 3
	 */
	public BigDecimal getCanMetic3() {
		return this.canMetic3;
	}

	/**
	 * Sets the can metic 3.
	 *
	 * @param canMetic3 the new can metic 3
	 */
	public void setCanMetic3(BigDecimal canMetic3) {
		this.canMetic3 = canMetic3;
	}

	/**
	 * Gets the can metic 4.
	 *
	 * @return the can metic 4
	 */
	public BigDecimal getCanMetic4() {
		return this.canMetic4;
	}

	/**
	 * Sets the can metic 4.
	 *
	 * @param canMetic4 the new can metic 4
	 */
	public void setCanMetic4(BigDecimal canMetic4) {
		this.canMetic4 = canMetic4;
	}

	/**
	 * Gets the can metic 5.
	 *
	 * @return the can metic 5
	 */
	public BigDecimal getCanMetic5() {
		return this.canMetic5;
	}

	/**
	 * Sets the can metic 5.
	 *
	 * @param canMetic5 the new can metic 5
	 */
	public void setCanMetic5(BigDecimal canMetic5) {
		this.canMetic5 = canMetic5;
	}

	/**
	 * Gets the can metic 6.
	 *
	 * @return the can metic 6
	 */
	public BigDecimal getCanMetic6() {
		return this.canMetic6;
	}

	/**
	 * Sets the can metic 6.
	 *
	 * @param canMetic6 the new can metic 6
	 */
	public void setCanMetic6(BigDecimal canMetic6) {
		this.canMetic6 = canMetic6;
	}

	/**
	 * Gets the can metic 7.
	 *
	 * @return the can metic 7
	 */
	public BigDecimal getCanMetic7() {
		return this.canMetic7;
	}

	/**
	 * Sets the can metic 7.
	 *
	 * @param canMetic7 the new can metic 7
	 */
	public void setCanMetic7(BigDecimal canMetic7) {
		this.canMetic7 = canMetic7;
	}

	/**
	 * Gets the can metic 8.
	 *
	 * @return the can metic 8
	 */
	public BigDecimal getCanMetic8() {
		return this.canMetic8;
	}

	/**
	 * Sets the can metic 8.
	 *
	 * @param canMetic8 the new can metic 8
	 */
	public void setCanMetic8(BigDecimal canMetic8) {
		this.canMetic8 = canMetic8;
	}

	/**
	 * Gets the can metic 9.
	 *
	 * @return the can metic 9
	 */
	public BigDecimal getCanMetic9() {
		return this.canMetic9;
	}

	/**
	 * Sets the can metic 9.
	 *
	 * @param canMetic9 the new can metic 9
	 */
	public void setCanMetic9(BigDecimal canMetic9) {
		this.canMetic9 = canMetic9;
	}

	/**
	 * Gets the cant avan 1.
	 *
	 * @return the cant avan 1
	 */
	public BigDecimal getCantAvan1() {
		return this.cantAvan1;
	}

	/**
	 * Sets the cant avan 1.
	 *
	 * @param cantAvan1 the new cant avan 1
	 */
	public void setCantAvan1(BigDecimal cantAvan1) {
		this.cantAvan1 = cantAvan1;
	}

	/**
	 * Gets the cant avan 10.
	 *
	 * @return the cant avan 10
	 */
	public BigDecimal getCantAvan10() {
		return this.cantAvan10;
	}

	/**
	 * Sets the cant avan 10.
	 *
	 * @param cantAvan10 the new cant avan 10
	 */
	public void setCantAvan10(BigDecimal cantAvan10) {
		this.cantAvan10 = cantAvan10;
	}

	/**
	 * Gets the cant avan 11.
	 *
	 * @return the cant avan 11
	 */
	public BigDecimal getCantAvan11() {
		return this.cantAvan11;
	}

	/**
	 * Sets the cant avan 11.
	 *
	 * @param cantAvan11 the new cant avan 11
	 */
	public void setCantAvan11(BigDecimal cantAvan11) {
		this.cantAvan11 = cantAvan11;
	}

	/**
	 * Gets the cant avan 12.
	 *
	 * @return the cant avan 12
	 */
	public BigDecimal getCantAvan12() {
		return this.cantAvan12;
	}

	/**
	 * Sets the cant avan 12.
	 *
	 * @param cantAvan12 the new cant avan 12
	 */
	public void setCantAvan12(BigDecimal cantAvan12) {
		this.cantAvan12 = cantAvan12;
	}

	/**
	 * Gets the cant avan 13.
	 *
	 * @return the cant avan 13
	 */
	public BigDecimal getCantAvan13() {
		return this.cantAvan13;
	}

	/**
	 * Sets the cant avan 13.
	 *
	 * @param cantAvan13 the new cant avan 13
	 */
	public void setCantAvan13(BigDecimal cantAvan13) {
		this.cantAvan13 = cantAvan13;
	}

	/**
	 * Gets the cant avan 2.
	 *
	 * @return the cant avan 2
	 */
	public BigDecimal getCantAvan2() {
		return this.cantAvan2;
	}

	/**
	 * Sets the cant avan 2.
	 *
	 * @param cantAvan2 the new cant avan 2
	 */
	public void setCantAvan2(BigDecimal cantAvan2) {
		this.cantAvan2 = cantAvan2;
	}

	/**
	 * Gets the cant avan 3.
	 *
	 * @return the cant avan 3
	 */
	public BigDecimal getCantAvan3() {
		return this.cantAvan3;
	}

	/**
	 * Sets the cant avan 3.
	 *
	 * @param cantAvan3 the new cant avan 3
	 */
	public void setCantAvan3(BigDecimal cantAvan3) {
		this.cantAvan3 = cantAvan3;
	}

	/**
	 * Gets the cant avan 4.
	 *
	 * @return the cant avan 4
	 */
	public BigDecimal getCantAvan4() {
		return this.cantAvan4;
	}

	/**
	 * Sets the cant avan 4.
	 *
	 * @param cantAvan4 the new cant avan 4
	 */
	public void setCantAvan4(BigDecimal cantAvan4) {
		this.cantAvan4 = cantAvan4;
	}

	/**
	 * Gets the cant avan 5.
	 *
	 * @return the cant avan 5
	 */
	public BigDecimal getCantAvan5() {
		return this.cantAvan5;
	}

	/**
	 * Sets the cant avan 5.
	 *
	 * @param cantAvan5 the new cant avan 5
	 */
	public void setCantAvan5(BigDecimal cantAvan5) {
		this.cantAvan5 = cantAvan5;
	}

	/**
	 * Gets the cant avan 6.
	 *
	 * @return the cant avan 6
	 */
	public BigDecimal getCantAvan6() {
		return this.cantAvan6;
	}

	/**
	 * Sets the cant avan 6.
	 *
	 * @param cantAvan6 the new cant avan 6
	 */
	public void setCantAvan6(BigDecimal cantAvan6) {
		this.cantAvan6 = cantAvan6;
	}

	/**
	 * Gets the cant avan 7.
	 *
	 * @return the cant avan 7
	 */
	public BigDecimal getCantAvan7() {
		return this.cantAvan7;
	}

	/**
	 * Sets the cant avan 7.
	 *
	 * @param cantAvan7 the new cant avan 7
	 */
	public void setCantAvan7(BigDecimal cantAvan7) {
		this.cantAvan7 = cantAvan7;
	}

	/**
	 * Gets the cant avan 8.
	 *
	 * @return the cant avan 8
	 */
	public BigDecimal getCantAvan8() {
		return this.cantAvan8;
	}

	/**
	 * Sets the cant avan 8.
	 *
	 * @param cantAvan8 the new cant avan 8
	 */
	public void setCantAvan8(BigDecimal cantAvan8) {
		this.cantAvan8 = cantAvan8;
	}

	/**
	 * Gets the cant avan 9.
	 *
	 * @return the cant avan 9
	 */
	public BigDecimal getCantAvan9() {
		return this.cantAvan9;
	}

	/**
	 * Sets the cant avan 9.
	 *
	 * @param cantAvan9 the new cant avan 9
	 */
	public void setCantAvan9(BigDecimal cantAvan9) {
		this.cantAvan9 = cantAvan9;
	}

	/**
	 * Gets the clavedep.
	 *
	 * @return the clavedep
	 */
	public Integer getClavedep() {
		return this.clavedep;
	}

	/**
	 * Sets the clavedep.
	 *
	 * @param clavedep the new clavedep
	 */
	public void setClavedep(Integer clavedep) {
		this.clavedep = clavedep;
	}

	/**
	 * Gets the clvdep.
	 *
	 * @return the clvdep
	 */
	public String getClvdep() {
		return this.clvdep;
	}

	/**
	 * Sets the clvdep.
	 *
	 * @param clvdep the new clvdep
	 */
	public void setClvdep(String clvdep) {
		this.clvdep = clvdep;
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
	 * Gets the clvmet.
	 *
	 * @return the clvmet
	 */
	public Integer getClvmet() {
		return this.clvmet;
	}

	/**
	 * Sets the clvmet.
	 *
	 * @param clvmet the new clvmet
	 */
	public void setClvmet(Integer clvmet) {
		this.clvmet = clvmet;
	}

	/**
	 * Gets the clvnep.
	 *
	 * @return the clvnep
	 */
	public String getClvnep() {
		return this.clvnep;
	}

	/**
	 * Sets the clvnep.
	 *
	 * @param clvnep the new clvnep
	 */
	public void setClvnep(String clvnep) {
		this.clvnep = clvnep;
	}

	/**
	 * Gets the clvreg.
	 *
	 * @return the clvreg
	 */
	public String getClvreg() {
		return this.clvreg;
	}

	/**
	 * Sets the clvreg.
	 *
	 * @param clvreg the new clvreg
	 */
	public void setClvreg(String clvreg) {
		this.clvreg = clvreg;
	}

	/**
	 * Gets the costo.
	 *
	 * @return the costo
	 */
	public BigDecimal getCosto() {
		return this.costo;
	}

	/**
	 * Sets the costo.
	 *
	 * @param costo the new costo
	 */
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	/**
	 * Gets the formula.
	 *
	 * @return the formula
	 */
	public String getFormula() {
		return this.formula;
	}

	/**
	 * Sets the formula.
	 *
	 * @param formula the new formula
	 */
	public void setFormula(String formula) {
		this.formula = formula;
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
	 * Gets the nom ind.
	 *
	 * @return the nom ind
	 */
	public String getNomInd() {
		return this.nomInd;
	}

	/**
	 * Sets the nom ind.
	 *
	 * @param nomInd the new nom ind
	 */
	public void setNomInd(String nomInd) {
		this.nomInd = nomInd;
	}

	/**
	 * Gets the nom met.
	 *
	 * @return the nom met
	 */
	public String getNomMet() {
		return this.nomMet;
	}

	/**
	 * Sets the nom met.
	 *
	 * @param nomMet the new nom met
	 */
	public void setNomMet(String nomMet) {
		this.nomMet = nomMet;
	}

	/**
	 * Gets the num ver.
	 *
	 * @return the num ver
	 */
	public Integer getNumVer() {
		return this.numVer;
	}

	/**
	 * Sets the num ver.
	 *
	 * @param numVer the new num ver
	 */
	public void setNumVer(Integer numVer) {
		this.numVer = numVer;
	}

	/**
	 * Gets the progante.
	 *
	 * @return the progante
	 */
	public BigDecimal getProgante() {
		return this.progante;
	}

	/**
	 * Sets the progante.
	 *
	 * @param progante the new progante
	 */
	public void setProgante(BigDecimal progante) {
		this.progante = progante;
	}

	/**
	 * Gets the redu 1.
	 *
	 * @return the redu 1
	 */
	public BigDecimal getRedu1() {
		return this.redu1;
	}

	/**
	 * Sets the redu 1.
	 *
	 * @param redu1 the new redu 1
	 */
	public void setRedu1(BigDecimal redu1) {
		this.redu1 = redu1;
	}

	/**
	 * Gets the redu 2.
	 *
	 * @return the redu 2
	 */
	public BigDecimal getRedu2() {
		return this.redu2;
	}

	/**
	 * Sets the redu 2.
	 *
	 * @param redu2 the new redu 2
	 */
	public void setRedu2(BigDecimal redu2) {
		this.redu2 = redu2;
	}

	/**
	 * Gets the redu 3.
	 *
	 * @return the redu 3
	 */
	public BigDecimal getRedu3() {
		return this.redu3;
	}

	/**
	 * Sets the redu 3.
	 *
	 * @param redu3 the new redu 3
	 */
	public void setRedu3(BigDecimal redu3) {
		this.redu3 = redu3;
	}

	/**
	 * Gets the redu 4.
	 *
	 * @return the redu 4
	 */
	public BigDecimal getRedu4() {
		return this.redu4;
	}

	/**
	 * Sets the redu 4.
	 *
	 * @param redu4 the new redu 4
	 */
	public void setRedu4(BigDecimal redu4) {
		this.redu4 = redu4;
	}

	/**
	 * Gets the redu 5.
	 *
	 * @return the redu 5
	 */
	public BigDecimal getRedu5() {
		return this.redu5;
	}

	/**
	 * Sets the redu 5.
	 *
	 * @param redu5 the new redu 5
	 */
	public void setRedu5(BigDecimal redu5) {
		this.redu5 = redu5;
	}

	/**
	 * Gets the uni med.
	 *
	 * @return the uni med
	 */
	public String getUniMed() {
		return this.uniMed;
	}

	/**
	 * Sets the uni med.
	 *
	 * @param uniMed the new uni med
	 */
	public void setUniMed(String uniMed) {
		this.uniMed = uniMed;
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
	 * Gets the varfin 1.
	 *
	 * @return the varfin 1
	 */
	public String getVarfin1() {
		return this.varfin1;
	}

	/**
	 * Sets the varfin 1.
	 *
	 * @param varfin1 the new varfin 1
	 */
	public void setVarfin1(String varfin1) {
		this.varfin1 = varfin1;
	}

	/**
	 * Gets the varfin 2.
	 *
	 * @return the varfin 2
	 */
	public String getVarfin2() {
		return this.varfin2;
	}

	/**
	 * Sets the varfin 2.
	 *
	 * @param varfin2 the new varfin 2
	 */
	public void setVarfin2(String varfin2) {
		this.varfin2 = varfin2;
	}

	/**
	 * Gets the varfin 3.
	 *
	 * @return the varfin 3
	 */
	public String getVarfin3() {
		return this.varfin3;
	}

	/**
	 * Sets the varfin 3.
	 *
	 * @param varfin3 the new varfin 3
	 */
	public void setVarfin3(String varfin3) {
		this.varfin3 = varfin3;
	}

	/**
	 * Gets the varfin 4.
	 *
	 * @return the varfin 4
	 */
	public String getVarfin4() {
		return this.varfin4;
	}

	/**
	 * Sets the varfin 4.
	 *
	 * @param varfin4 the new varfin 4
	 */
	public void setVarfin4(String varfin4) {
		this.varfin4 = varfin4;
	}

	/**
	 * Gets the varfis 1.
	 *
	 * @return the varfis 1
	 */
	public String getVarfis1() {
		return this.varfis1;
	}

	/**
	 * Sets the varfis 1.
	 *
	 * @param varfis1 the new varfis 1
	 */
	public void setVarfis1(String varfis1) {
		this.varfis1 = varfis1;
	}

	/**
	 * Gets the varfis 2.
	 *
	 * @return the varfis 2
	 */
	public String getVarfis2() {
		return this.varfis2;
	}

	/**
	 * Sets the varfis 2.
	 *
	 * @param varfis2 the new varfis 2
	 */
	public void setVarfis2(String varfis2) {
		this.varfis2 = varfis2;
	}

	/**
	 * Gets the varfis 3.
	 *
	 * @return the varfis 3
	 */
	public String getVarfis3() {
		return this.varfis3;
	}

	/**
	 * Sets the varfis 3.
	 *
	 * @param varfis3 the new varfis 3
	 */
	public void setVarfis3(String varfis3) {
		this.varfis3 = varfis3;
	}

	/**
	 * Gets the varfis 4.
	 *
	 * @return the varfis 4
	 */
	public String getVarfis4() {
		return this.varfis4;
	}

	/**
	 * Sets the varfis 4.
	 *
	 * @param varfis4 the new varfis 4
	 */
	public void setVarfis4(String varfis4) {
		this.varfis4 = varfis4;
	}

}