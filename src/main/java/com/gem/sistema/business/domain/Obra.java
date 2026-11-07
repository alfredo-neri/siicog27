package com.gem.sistema.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the OBRAS database table.
 * 
 */
@Entity
@Table(name="OBRAS")
@NamedQuery(name="Obra.findAll", query="SELECT o FROM Obra o")
public class Obra extends AbstractEntity  {

	/** The auto 1. */
	@Column(name="AUTO_1")
	private BigDecimal auto1;

	/** The auto 10. */
	@Column(name="AUTO_10")
	private BigDecimal auto10;

	/** The auto 11. */
	@Column(name="AUTO_11")
	private BigDecimal auto11;

	/** The auto 12. */
	@Column(name="AUTO_12")
	private BigDecimal auto12;

	/** The auto 13. */
	@Column(name="AUTO_13")
	private BigDecimal auto13;

	/** The auto 2. */
	@Column(name="AUTO_2")
	private BigDecimal auto2;

	/** The auto 3. */
	@Column(name="AUTO_3")
	private BigDecimal auto3;

	/** The auto 4. */
	@Column(name="AUTO_4")
	private BigDecimal auto4;

	/** The auto 5. */
	@Column(name="AUTO_5")
	private BigDecimal auto5;

	/** The auto 6. */
	@Column(name="AUTO_6")
	private BigDecimal auto6;

	/** The auto 7. */
	@Column(name="AUTO_7")
	private BigDecimal auto7;

	/** The auto 8. */
	@Column(name="AUTO_8")
	private BigDecimal auto8;

	/** The auto 9. */
	@Column(name="AUTO_9")
	private BigDecimal auto9;

	/** The avancef. */
	private BigDecimal avancef;

	/** The avancefa. */
	private BigDecimal avancefa;

	/** The capturo. */
	private String capturo;

	/** The clvdep. */
	private String clvdep;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The ffin. */
	private String ffin;

	/** The fn. */
	private String fn;

	/** The fun. */
	private String fun;

	/** The id ref. */
	@Column(name="ID_REF")
	private long idRef;

	/** The justificacion. */
	private String justificacion;

	/** The ncontrol. */
	private int ncontrol;

	/** The nomobra. */
	private String nomobra;

	/** The poblacion. */
	private int poblacion;

	/** The prog. */
	private String prog;

	/** The proy. */
	private String proy;

	/** The subfun. */
	private String subfun;

	/** The subprog. */
	private String subprog;

	/** The tipoasig. */
	private String tipoasig;

	/** The tipoejec. */
	private String tipoejec;

	/** The toeje 1. */
	@Column(name="TOEJE_1")
	private BigDecimal toeje1;

	/** The toeje 10. */
	@Column(name="TOEJE_10")
	private BigDecimal toeje10;

	/** The toeje 11. */
	@Column(name="TOEJE_11")
	private BigDecimal toeje11;

	/** The toeje 12. */
	@Column(name="TOEJE_12")
	private BigDecimal toeje12;

	/** The toeje 13. */
	@Column(name="TOEJE_13")
	private BigDecimal toeje13;

	/** The toeje 2. */
	@Column(name="TOEJE_2")
	private BigDecimal toeje2;

	/** The toeje 3. */
	@Column(name="TOEJE_3")
	private BigDecimal toeje3;

	/** The toeje 4. */
	@Column(name="TOEJE_4")
	private BigDecimal toeje4;

	/** The toeje 5. */
	@Column(name="TOEJE_5")
	private BigDecimal toeje5;

	/** The toeje 6. */
	@Column(name="TOEJE_6")
	private BigDecimal toeje6;

	/** The toeje 7. */
	@Column(name="TOEJE_7")
	private BigDecimal toeje7;

	/** The toeje 8. */
	@Column(name="TOEJE_8")
	private BigDecimal toeje8;

	/** The toeje 9. */
	@Column(name="TOEJE_9")
	private BigDecimal toeje9;

	/** The ubicacion. */
	private String ubicacion;

    /** The userid. */
    @Column(name="USERID")
	private String userid;

	/** The catsector. */
	//bi-directional many-to-one association to Catsector
	@ManyToOne
	@JoinColumn(name="IDSECTOR")
	private Catsector catsector;

	
	
	/**
	 * Instantiates a new obra.
	 */
	public Obra() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new obra.
	 *
	 * @param id the id
	 * @param fn the fn
	 * @param fun the fun
	 * @param subfun the subfun
	 * @param prog the prog
	 * @param subprog the subprog
	 * @param proy the proy
	 * @param ffin the ffin
	 * @param ncontrol the ncontrol
	 * @param nomobra the nomobra
	 * @param tipoejec the tipoejec
	 * @param ubicacion the ubicacion
	 * @param justificacion the justificacion
	 * @param poblacion the poblacion
	 * @param tipoasig the tipoasig
	 * @param auto1 the auto 1
	 * @param auto2 the auto 2
	 * @param auto3 the auto 3
	 * @param auto4 the auto 4
	 * @param auto5 the auto 5
	 * @param auto6 the auto 6
	 * @param auto7 the auto 7
	 * @param auto8 the auto 8
	 * @param auto9 the auto 9
	 * @param auto10 the auto 10
	 * @param auto11 the auto 11
	 * @param auto12 the auto 12
	 * @param auto13 the auto 13
	 * @param toeje1 the toeje 1
	 * @param toeje2 the toeje 2
	 * @param toeje3 the toeje 3
	 * @param toeje4 the toeje 4
	 * @param toeje5 the toeje 5
	 * @param toeje6 the toeje 6
	 * @param toeje7 the toeje 7
	 * @param toeje8 the toeje 8
	 * @param toeje9 the toeje 9
	 * @param toeje10 the toeje 10
	 * @param toeje11 the toeje 11
	 * @param toeje12 the toeje 12
	 * @param toeje13 the toeje 13
	 * @param capturo the capturo
	 * @param feccap the feccap
	 * @param clvdep the clvdep
	 * @param avancef the avancef
	 */
	public Obra(long id, String fn, String fun, String subfun, String prog, String subprog, String proy, String ffin,
			int ncontrol, String nomobra, String tipoejec, String ubicacion, String justificacion, int poblacion,
			String tipoasig, BigDecimal auto1, BigDecimal auto2, BigDecimal auto3, BigDecimal auto4, BigDecimal auto5, BigDecimal auto6,
			BigDecimal auto7, BigDecimal auto8, BigDecimal auto9, BigDecimal auto10, BigDecimal auto11, BigDecimal auto12, BigDecimal auto13,
			BigDecimal toeje1, BigDecimal toeje2, BigDecimal toeje3, BigDecimal toeje4, BigDecimal toeje5, BigDecimal toeje6, BigDecimal toeje7,
			BigDecimal toeje8, BigDecimal toeje9, BigDecimal toeje10, BigDecimal toeje11, BigDecimal toeje12, BigDecimal toeje13,
			String capturo, Date feccap, String clvdep, BigDecimal avancef) {
		super();
		this.setId(id);
		this.fn = fn;
		this.fun = fun;
		this.subfun = subfun;
		this.prog = prog;
		this.subprog = subprog;
		this.proy = proy;
		this.ffin = ffin;
		this.ncontrol = ncontrol;
		this.nomobra = nomobra;
		this.tipoejec = tipoejec;
		this.ubicacion = ubicacion;
		this.justificacion = justificacion;
		this.poblacion = poblacion;
		this.tipoasig = tipoasig;
		this.auto1 = auto1;
		this.auto2 = auto2;
		this.auto3 = auto3;
		this.auto4 = auto4;
		this.auto5 = auto5;
		this.auto6 = auto6;
		this.auto7 = auto7;
		this.auto8 = auto8;
		this.auto9 = auto9;
		this.auto10 = auto10;
		this.auto11 = auto11;
		this.auto12 = auto12;
		this.auto13 = auto13;
		this.toeje1 = toeje1;
		this.toeje2 = toeje2;
		this.toeje3 = toeje3;
		this.toeje4 = toeje4;
		this.toeje5 = toeje5;
		this.toeje6 = toeje6;
		this.toeje7 = toeje7;
		this.toeje8 = toeje8;
		this.toeje9 = toeje9;
		this.toeje10 = toeje10;
		this.toeje11 = toeje11;
		this.toeje12 = toeje12;
		this.toeje13 = toeje13;
		this.capturo = capturo;
		this.feccap = feccap;
		this.clvdep = clvdep;
		this.avancef = avancef;
	}

	/**
	 * Gets the auto 1.
	 *
	 * @return the auto 1
	 */
	public BigDecimal getAuto1() {
		return this.auto1;
	}

	/**
	 * Sets the auto 1.
	 *
	 * @param auto1 the new auto 1
	 */
	public void setAuto1(BigDecimal auto1) {
		this.auto1 = auto1;
	}

	/**
	 * Gets the auto 10.
	 *
	 * @return the auto 10
	 */
	public BigDecimal getAuto10() {
		return this.auto10;
	}

	/**
	 * Sets the auto 10.
	 *
	 * @param auto10 the new auto 10
	 */
	public void setAuto10(BigDecimal auto10) {
		this.auto10 = auto10;
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
	 * Gets the auto 2.
	 *
	 * @return the auto 2
	 */
	public BigDecimal getAuto2() {
		return this.auto2;
	}

	/**
	 * Sets the auto 2.
	 *
	 * @param auto2 the new auto 2
	 */
	public void setAuto2(BigDecimal auto2) {
		this.auto2 = auto2;
	}

	/**
	 * Gets the auto 3.
	 *
	 * @return the auto 3
	 */
	public BigDecimal getAuto3() {
		return this.auto3;
	}

	/**
	 * Sets the auto 3.
	 *
	 * @param auto3 the new auto 3
	 */
	public void setAuto3(BigDecimal auto3) {
		this.auto3 = auto3;
	}

	/**
	 * Gets the auto 4.
	 *
	 * @return the auto 4
	 */
	public BigDecimal getAuto4() {
		return this.auto4;
	}

	/**
	 * Sets the auto 4.
	 *
	 * @param auto4 the new auto 4
	 */
	public void setAuto4(BigDecimal auto4) {
		this.auto4 = auto4;
	}

	/**
	 * Gets the auto 5.
	 *
	 * @return the auto 5
	 */
	public BigDecimal getAuto5() {
		return this.auto5;
	}

	/**
	 * Sets the auto 5.
	 *
	 * @param auto5 the new auto 5
	 */
	public void setAuto5(BigDecimal auto5) {
		this.auto5 = auto5;
	}

	/**
	 * Gets the auto 6.
	 *
	 * @return the auto 6
	 */
	public BigDecimal getAuto6() {
		return this.auto6;
	}

	/**
	 * Sets the auto 6.
	 *
	 * @param auto6 the new auto 6
	 */
	public void setAuto6(BigDecimal auto6) {
		this.auto6 = auto6;
	}

	/**
	 * Gets the auto 7.
	 *
	 * @return the auto 7
	 */
	public BigDecimal getAuto7() {
		return this.auto7;
	}

	/**
	 * Sets the auto 7.
	 *
	 * @param auto7 the new auto 7
	 */
	public void setAuto7(BigDecimal auto7) {
		this.auto7 = auto7;
	}

	/**
	 * Gets the auto 8.
	 *
	 * @return the auto 8
	 */
	public BigDecimal getAuto8() {
		return this.auto8;
	}

	/**
	 * Sets the auto 8.
	 *
	 * @param auto8 the new auto 8
	 */
	public void setAuto8(BigDecimal auto8) {
		this.auto8 = auto8;
	}

	/**
	 * Gets the auto 9.
	 *
	 * @return the auto 9
	 */
	public BigDecimal getAuto9() {
		return this.auto9;
	}

	/**
	 * Sets the auto 9.
	 *
	 * @param auto9 the new auto 9
	 */
	public void setAuto9(BigDecimal auto9) {
		this.auto9 = auto9;
	}

	/**
	 * Gets the avancef.
	 *
	 * @return the avancef
	 */
	public BigDecimal getAvancef() {
		return this.avancef;
	}

	/**
	 * Sets the avancef.
	 *
	 * @param avancef the new avancef
	 */
	public void setAvancef(BigDecimal avancef) {
		this.avancef = avancef;
	}

	/**
	 * Gets the avancefa.
	 *
	 * @return the avancefa
	 */
	public BigDecimal getAvancefa() {
		return this.avancefa;
	}

	/**
	 * Sets the avancefa.
	 *
	 * @param avancefa the new avancefa
	 */
	public void setAvancefa(BigDecimal avancefa) {
		this.avancefa = avancefa;
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
	 * Gets the ffin.
	 *
	 * @return the ffin
	 */
	public String getFfin() {
		return this.ffin;
	}

	/**
	 * Sets the ffin.
	 *
	 * @param ffin the new ffin
	 */
	public void setFfin(String ffin) {
		this.ffin = ffin;
	}

	/**
	 * Gets the fn.
	 *
	 * @return the fn
	 */
	public String getFn() {
		return this.fn;
	}

	/**
	 * Sets the fn.
	 *
	 * @param fn the new fn
	 */
	public void setFn(String fn) {
		this.fn = fn;
	}

	/**
	 * Gets the fun.
	 *
	 * @return the fun
	 */
	public String getFun() {
		return this.fun;
	}

	/**
	 * Sets the fun.
	 *
	 * @param fun the new fun
	 */
	public void setFun(String fun) {
		this.fun = fun;
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
	 * Gets the justificacion.
	 *
	 * @return the justificacion
	 */
	public String getJustificacion() {
		return this.justificacion;
	}

	/**
	 * Sets the justificacion.
	 *
	 * @param justificacion the new justificacion
	 */
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	/**
	 * Gets the ncontrol.
	 *
	 * @return the ncontrol
	 */
	public int getNcontrol() {
		return this.ncontrol;
	}

	/**
	 * Sets the ncontrol.
	 *
	 * @param ncontrol the new ncontrol
	 */
	public void setNcontrol(int ncontrol) {
		this.ncontrol = ncontrol;
	}

	/**
	 * Gets the nomobra.
	 *
	 * @return the nomobra
	 */
	public String getNomobra() {
		return this.nomobra;
	}

	/**
	 * Sets the nomobra.
	 *
	 * @param nomobra the new nomobra
	 */
	public void setNomobra(String nomobra) {
		this.nomobra = nomobra;
	}

	/**
	 * Gets the poblacion.
	 *
	 * @return the poblacion
	 */
	public int getPoblacion() {
		return this.poblacion;
	}

	/**
	 * Sets the poblacion.
	 *
	 * @param poblacion the new poblacion
	 */
	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * Gets the prog.
	 *
	 * @return the prog
	 */
	public String getProg() {
		return this.prog;
	}

	/**
	 * Sets the prog.
	 *
	 * @param prog the new prog
	 */
	public void setProg(String prog) {
		this.prog = prog;
	}

	/**
	 * Gets the proy.
	 *
	 * @return the proy
	 */
	public String getProy() {
		return this.proy;
	}

	/**
	 * Sets the proy.
	 *
	 * @param proy the new proy
	 */
	public void setProy(String proy) {
		this.proy = proy;
	}

	/**
	 * Gets the subfun.
	 *
	 * @return the subfun
	 */
	public String getSubfun() {
		return this.subfun;
	}

	/**
	 * Sets the subfun.
	 *
	 * @param subfun the new subfun
	 */
	public void setSubfun(String subfun) {
		this.subfun = subfun;
	}

	/**
	 * Gets the subprog.
	 *
	 * @return the subprog
	 */
	public String getSubprog() {
		return this.subprog;
	}

	/**
	 * Sets the subprog.
	 *
	 * @param subprog the new subprog
	 */
	public void setSubprog(String subprog) {
		this.subprog = subprog;
	}

	/**
	 * Gets the tipoasig.
	 *
	 * @return the tipoasig
	 */
	public String getTipoasig() {
		return this.tipoasig;
	}

	/**
	 * Sets the tipoasig.
	 *
	 * @param tipoasig the new tipoasig
	 */
	public void setTipoasig(String tipoasig) {
		this.tipoasig = tipoasig;
	}

	/**
	 * Gets the tipoejec.
	 *
	 * @return the tipoejec
	 */
	public String getTipoejec() {
		return this.tipoejec;
	}

	/**
	 * Sets the tipoejec.
	 *
	 * @param tipoejec the new tipoejec
	 */
	public void setTipoejec(String tipoejec) {
		this.tipoejec = tipoejec;
	}

	/**
	 * Gets the toeje 1.
	 *
	 * @return the toeje 1
	 */
	public BigDecimal getToeje1() {
		return this.toeje1;
	}

	/**
	 * Sets the toeje 1.
	 *
	 * @param toeje1 the new toeje 1
	 */
	public void setToeje1(BigDecimal toeje1) {
		this.toeje1 = toeje1;
	}

	/**
	 * Gets the toeje 10.
	 *
	 * @return the toeje 10
	 */
	public BigDecimal getToeje10() {
		return this.toeje10;
	}

	/**
	 * Sets the toeje 10.
	 *
	 * @param toeje10 the new toeje 10
	 */
	public void setToeje10(BigDecimal toeje10) {
		this.toeje10 = toeje10;
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
	 * Gets the toeje 2.
	 *
	 * @return the toeje 2
	 */
	public BigDecimal getToeje2() {
		return this.toeje2;
	}

	/**
	 * Sets the toeje 2.
	 *
	 * @param toeje2 the new toeje 2
	 */
	public void setToeje2(BigDecimal toeje2) {
		this.toeje2 = toeje2;
	}

	/**
	 * Gets the toeje 3.
	 *
	 * @return the toeje 3
	 */
	public BigDecimal getToeje3() {
		return this.toeje3;
	}

	/**
	 * Sets the toeje 3.
	 *
	 * @param toeje3 the new toeje 3
	 */
	public void setToeje3(BigDecimal toeje3) {
		this.toeje3 = toeje3;
	}

	/**
	 * Gets the toeje 4.
	 *
	 * @return the toeje 4
	 */
	public BigDecimal getToeje4() {
		return this.toeje4;
	}

	/**
	 * Sets the toeje 4.
	 *
	 * @param toeje4 the new toeje 4
	 */
	public void setToeje4(BigDecimal toeje4) {
		this.toeje4 = toeje4;
	}

	/**
	 * Gets the toeje 5.
	 *
	 * @return the toeje 5
	 */
	public BigDecimal getToeje5() {
		return this.toeje5;
	}

	/**
	 * Sets the toeje 5.
	 *
	 * @param toeje5 the new toeje 5
	 */
	public void setToeje5(BigDecimal toeje5) {
		this.toeje5 = toeje5;
	}

	/**
	 * Gets the toeje 6.
	 *
	 * @return the toeje 6
	 */
	public BigDecimal getToeje6() {
		return this.toeje6;
	}

	/**
	 * Sets the toeje 6.
	 *
	 * @param toeje6 the new toeje 6
	 */
	public void setToeje6(BigDecimal toeje6) {
		this.toeje6 = toeje6;
	}

	/**
	 * Gets the toeje 7.
	 *
	 * @return the toeje 7
	 */
	public BigDecimal getToeje7() {
		return this.toeje7;
	}

	/**
	 * Sets the toeje 7.
	 *
	 * @param toeje7 the new toeje 7
	 */
	public void setToeje7(BigDecimal toeje7) {
		this.toeje7 = toeje7;
	}

	/**
	 * Gets the toeje 8.
	 *
	 * @return the toeje 8
	 */
	public BigDecimal getToeje8() {
		return this.toeje8;
	}

	/**
	 * Sets the toeje 8.
	 *
	 * @param toeje8 the new toeje 8
	 */
	public void setToeje8(BigDecimal toeje8) {
		this.toeje8 = toeje8;
	}

	/**
	 * Gets the toeje 9.
	 *
	 * @return the toeje 9
	 */
	public BigDecimal getToeje9() {
		return this.toeje9;
	}

	/**
	 * Sets the toeje 9.
	 *
	 * @param toeje9 the new toeje 9
	 */
	public void setToeje9(BigDecimal toeje9) {
		this.toeje9 = toeje9;
	}

	/**
	 * Gets the ubicacion.
	 *
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return this.ubicacion;
	}

	/**
	 * Sets the ubicacion.
	 *
	 * @param ubicacion the new ubicacion
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
	 * Gets the catsector.
	 *
	 * @return the catsector
	 */
	public Catsector getCatsector() {
		return this.catsector;
	}

	/**
	 * Sets the catsector.
	 *
	 * @param catsector the new catsector
	 */
	public void setCatsector(Catsector catsector) {
		this.catsector = catsector;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		return "Obra [id=" + this.getId() + ", fn=" + fn + ", fun=" + fun + ", subfun=" + subfun + ", prog=" + prog + ", subprog="
				+ subprog + ", proy=" + proy + ", ffin=" + ffin + ", ncontrol=" + ncontrol + ", nomobra=" + nomobra
				+ ", tipoejec=" + tipoejec + ", ubicacion=" + ubicacion + ", justificacion=" + justificacion
				+ ", poblacion=" + poblacion + ", tipoasig=" + tipoasig + ", auto1=" + auto1 + ", auto2=" + auto2
				+ ", auto3=" + auto3 + ", auto4=" + auto4 + ", auto5=" + auto5 + ", auto6=" + auto6 + ", auto7=" + auto7
				+ ", auto8=" + auto8 + ", auto9=" + auto9 + ", auto10=" + auto10 + ", auto11=" + auto11 + ", auto12="
				+ auto12 + ", auto13=" + auto13 + ", toeje1=" + toeje1 + ", toeje2=" + toeje2 + ", toeje3=" + toeje3
				+ ", toeje4=" + toeje4 + ", toeje5=" + toeje5 + ", toeje6=" + toeje6 + ", toeje7=" + toeje7
				+ ", toeje8=" + toeje8 + ", toeje9=" + toeje9 + ", toeje10=" + toeje10 + ", toeje11=" + toeje11
				+ ", toeje12=" + toeje12 + ", toeje13=" + toeje13 + ", capturo=" + capturo + ", feccap=" + feccap
				+ ", clvdep=" + clvdep + ", avancef=" + avancef + "]";
	}

}