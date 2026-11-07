package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the FTECNICASM database table.
 * 
 */
@Entity
@NamedQuery(name="Ftecnicasm.findAll", query="SELECT f FROM Ftecnicasm f")
public class Ftecnicasm extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ALCANE_1")
	private BigDecimal alcane1;

	@Column(name="ALCANE_2")
	private BigDecimal alcane2;

	@Column(name="ALCANE_3")
	private BigDecimal alcane3;

	@Column(name="ALCANE_4")
	private BigDecimal alcane4;

	@Column(name="ALCPORCEN_1")
	private BigDecimal alcporcen1;

	@Column(name="ALCPORCEN_2")
	private BigDecimal alcporcen2;

	@Column(name="ALCPORCEN_3")
	private BigDecimal alcporcen3;

	@Column(name="ALCPORCEN_4")
	private BigDecimal alcporcen4;

	private String ambito1;

	private Integer ambito2;

	private String clvdep;

	private String clvfin;

	private String clvfun;

	private String cobertura;

	private String cveind;

	private String cvetemas;

	private String descfac;

	private String descripcion;

	private String dimension;

	@Column(name="EF_1")
	private BigDecimal ef1;

	@Column(name="EF_2")
	private BigDecimal ef2;

	@Column(name="EF_3")
	private BigDecimal ef3;

	@Column(name="EF_4")
	private BigDecimal ef4;

	private String elaboro;

	private String evaluacion;

	@Temporal(TemporalType.DATE)
	private Date feccap;

	private String formula;

	private String frecuencia;

	@Column(name="ID_REF")
	private Long idRef;

	private Integer idsector;

	private String interpretacion;

	@Column(name="METANUALE_1")
	private BigDecimal metanuale1;

	@Column(name="METANUALE_2")
	private BigDecimal metanuale2;

	@Column(name="METANUALE_3")
	private BigDecimal metanuale3;

	@Column(name="METANUALE_4")
	private BigDecimal metanuale4;

	private String nomind;

	private String nope;

	private String objetivo;

	private String pe;

	@Column(name="PORCALCANE_1")
	private BigDecimal porcalcane1;

	@Column(name="PORCALCANE_2")
	private BigDecimal porcalcane2;

	@Column(name="PORCALCANE_3")
	private BigDecimal porcalcane3;

	@Column(name="PORCALCANE_4")
	private BigDecimal porcalcane4;

	@Column(name="PORCPROGE_1")
	private BigDecimal porcproge1;

	@Column(name="PORCPROGE_2")
	private BigDecimal porcproge2;

	@Column(name="PORCPROGE_3")
	private BigDecimal porcproge3;

	@Column(name="PORCPROGE_4")
	private BigDecimal porcproge4;

	@Column(name="PROGE_1")
	private BigDecimal proge1;

	@Column(name="PROGE_2")
	private BigDecimal proge2;

	@Column(name="PROGE_3")
	private BigDecimal proge3;

	@Column(name="PROGE_4")
	private BigDecimal proge4;

	@Column(name="PROGPORCEN_1")
	private BigDecimal progporcen1;

	@Column(name="PROGPORCEN_2")
	private BigDecimal progporcen2;

	@Column(name="PROGPORCEN_3")
	private BigDecimal progporcen3;

	@Column(name="PROGPORCEN_4")
	private BigDecimal progporcen4;

	@Column(name="SEMAFORO1_1")
	private String semaforo11;

	@Column(name="SEMAFORO1_2")
	private String semaforo12;

	@Column(name="SEMAFORO1_3")
	private String semaforo13;

	@Column(name="SEMAFORO1_4")
	private String semaforo14;

	@Column(name="SEMAFORO2_1")
	private String semaforo21;

	@Column(name="SEMAFORO2_2")
	private String semaforo22;

	@Column(name="SEMAFORO2_3")
	private String semaforo23;

	@Column(name="SEMAFORO2_4")
	private String semaforo24;

	private String tema;

	@Column(name="USERID")
	private String userid;

	private String usuario;

	private String valido;
	
	@Column(name="LINEA_BASE")
	private String lineaBase;

	public Ftecnicasm() {
	}

	public BigDecimal getAlcane1() {
		return this.alcane1;
	}

	public void setAlcane1(BigDecimal alcane1) {
		this.alcane1 = alcane1;
	}

	public BigDecimal getAlcane2() {
		return this.alcane2;
	}

	public void setAlcane2(BigDecimal alcane2) {
		this.alcane2 = alcane2;
	}

	public BigDecimal getAlcane3() {
		return this.alcane3;
	}

	public void setAlcane3(BigDecimal alcane3) {
		this.alcane3 = alcane3;
	}

	public BigDecimal getAlcane4() {
		return this.alcane4;
	}

	public void setAlcane4(BigDecimal alcane4) {
		this.alcane4 = alcane4;
	}

	public BigDecimal getAlcporcen1() {
		return this.alcporcen1;
	}

	public void setAlcporcen1(BigDecimal alcporcen1) {
		this.alcporcen1 = alcporcen1;
	}

	public BigDecimal getAlcporcen2() {
		return this.alcporcen2;
	}

	public void setAlcporcen2(BigDecimal alcporcen2) {
		this.alcporcen2 = alcporcen2;
	}

	public BigDecimal getAlcporcen3() {
		return this.alcporcen3;
	}

	public void setAlcporcen3(BigDecimal alcporcen3) {
		this.alcporcen3 = alcporcen3;
	}

	public BigDecimal getAlcporcen4() {
		return this.alcporcen4;
	}

	public void setAlcporcen4(BigDecimal alcporcen4) {
		this.alcporcen4 = alcporcen4;
	}

	public String getAmbito1() {
		return this.ambito1;
	}

	public void setAmbito1(String ambito1) {
		this.ambito1 = ambito1;
	}

	public Integer getAmbito2() {
		return this.ambito2;
	}

	public void setAmbito2(Integer ambito2) {
		this.ambito2 = ambito2;
	}

	public String getClvdep() {
		return this.clvdep;
	}

	public void setClvdep(String clvdep) {
		this.clvdep = clvdep;
	}

	public String getClvfin() {
		return this.clvfin;
	}

	public void setClvfin(String clvfin) {
		this.clvfin = clvfin;
	}

	public String getClvfun() {
		return this.clvfun;
	}

	public void setClvfun(String clvfun) {
		this.clvfun = clvfun;
	}

	public String getCobertura() {
		return this.cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	public String getCveind() {
		return this.cveind;
	}

	public void setCveind(String cveind) {
		this.cveind = cveind;
	}

	public String getCvetemas() {
		return this.cvetemas;
	}

	public void setCvetemas(String cvetemas) {
		this.cvetemas = cvetemas;
	}

	public String getDescfac() {
		return this.descfac;
	}

	public void setDescfac(String descfac) {
		this.descfac = descfac;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDimension() {
		return this.dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public BigDecimal getEf1() {
		return this.ef1;
	}

	public void setEf1(BigDecimal ef1) {
		this.ef1 = ef1;
	}

	public BigDecimal getEf2() {
		return this.ef2;
	}

	public void setEf2(BigDecimal ef2) {
		this.ef2 = ef2;
	}

	public BigDecimal getEf3() {
		return this.ef3;
	}

	public void setEf3(BigDecimal ef3) {
		this.ef3 = ef3;
	}

	public BigDecimal getEf4() {
		return this.ef4;
	}

	public void setEf4(BigDecimal ef4) {
		this.ef4 = ef4;
	}

	public String getElaboro() {
		return this.elaboro;
	}

	public void setElaboro(String elaboro) {
		this.elaboro = elaboro;
	}

	public String getEvaluacion() {
		return this.evaluacion;
	}

	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}

	public Date getFeccap() {
		return this.feccap;
	}

	public void setFeccap(Date feccap) {
		this.feccap = feccap;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFrecuencia() {
		return this.frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Long getIdRef() {
		return this.idRef;
	}

	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	public Integer getIdsector() {
		return idsector;
	}

	public void setIdsector(Integer idsector) {
		this.idsector = idsector;
	}

	public String getInterpretacion() {
		return this.interpretacion;
	}

	public void setInterpretacion(String interpretacion) {
		this.interpretacion = interpretacion;
	}

	public BigDecimal getMetanuale1() {
		return this.metanuale1;
	}

	public void setMetanuale1(BigDecimal metanuale1) {
		this.metanuale1 = metanuale1;
	}

	public BigDecimal getMetanuale2() {
		return this.metanuale2;
	}

	public void setMetanuale2(BigDecimal metanuale2) {
		this.metanuale2 = metanuale2;
	}

	public BigDecimal getMetanuale3() {
		return this.metanuale3;
	}

	public void setMetanuale3(BigDecimal metanuale3) {
		this.metanuale3 = metanuale3;
	}

	public BigDecimal getMetanuale4() {
		return this.metanuale4;
	}

	public void setMetanuale4(BigDecimal metanuale4) {
		this.metanuale4 = metanuale4;
	}

	public String getNomind() {
		return this.nomind;
	}

	public void setNomind(String nomind) {
		this.nomind = nomind;
	}

	public String getNope() {
		return this.nope;
	}

	public void setNope(String nope) {
		this.nope = nope;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getPe() {
		return this.pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public BigDecimal getPorcalcane1() {
		return this.porcalcane1;
	}

	public void setPorcalcane1(BigDecimal porcalcane1) {
		this.porcalcane1 = porcalcane1;
	}

	public BigDecimal getPorcalcane2() {
		return this.porcalcane2;
	}

	public void setPorcalcane2(BigDecimal porcalcane2) {
		this.porcalcane2 = porcalcane2;
	}

	public BigDecimal getPorcalcane3() {
		return this.porcalcane3;
	}

	public void setPorcalcane3(BigDecimal porcalcane3) {
		this.porcalcane3 = porcalcane3;
	}

	public BigDecimal getPorcalcane4() {
		return this.porcalcane4;
	}

	public void setPorcalcane4(BigDecimal porcalcane4) {
		this.porcalcane4 = porcalcane4;
	}

	public BigDecimal getPorcproge1() {
		return this.porcproge1;
	}

	public void setPorcproge1(BigDecimal porcproge1) {
		this.porcproge1 = porcproge1;
	}

	public BigDecimal getPorcproge2() {
		return this.porcproge2;
	}

	public void setPorcproge2(BigDecimal porcproge2) {
		this.porcproge2 = porcproge2;
	}

	public BigDecimal getPorcproge3() {
		return this.porcproge3;
	}

	public void setPorcproge3(BigDecimal porcproge3) {
		this.porcproge3 = porcproge3;
	}

	public BigDecimal getPorcproge4() {
		return this.porcproge4;
	}

	public void setPorcproge4(BigDecimal porcproge4) {
		this.porcproge4 = porcproge4;
	}

	public BigDecimal getProge1() {
		return this.proge1;
	}

	public void setProge1(BigDecimal proge1) {
		this.proge1 = proge1;
	}

	public BigDecimal getProge2() {
		return this.proge2;
	}

	public void setProge2(BigDecimal proge2) {
		this.proge2 = proge2;
	}

	public BigDecimal getProge3() {
		return this.proge3;
	}

	public void setProge3(BigDecimal proge3) {
		this.proge3 = proge3;
	}

	public BigDecimal getProge4() {
		return this.proge4;
	}

	public void setProge4(BigDecimal proge4) {
		this.proge4 = proge4;
	}

	public BigDecimal getProgporcen1() {
		return this.progporcen1;
	}

	public void setProgporcen1(BigDecimal progporcen1) {
		this.progporcen1 = progporcen1;
	}

	public BigDecimal getProgporcen2() {
		return this.progporcen2;
	}

	public void setProgporcen2(BigDecimal progporcen2) {
		this.progporcen2 = progporcen2;
	}

	public BigDecimal getProgporcen3() {
		return this.progporcen3;
	}

	public void setProgporcen3(BigDecimal progporcen3) {
		this.progporcen3 = progporcen3;
	}

	public BigDecimal getProgporcen4() {
		return this.progporcen4;
	}

	public void setProgporcen4(BigDecimal progporcen4) {
		this.progporcen4 = progporcen4;
	}

	public String getSemaforo11() {
		return this.semaforo11;
	}

	public void setSemaforo11(String semaforo11) {
		this.semaforo11 = semaforo11;
	}

	public String getSemaforo12() {
		return this.semaforo12;
	}

	public void setSemaforo12(String semaforo12) {
		this.semaforo12 = semaforo12;
	}

	public String getSemaforo13() {
		return this.semaforo13;
	}

	public void setSemaforo13(String semaforo13) {
		this.semaforo13 = semaforo13;
	}

	public String getSemaforo14() {
		return this.semaforo14;
	}

	public void setSemaforo14(String semaforo14) {
		this.semaforo14 = semaforo14;
	}

	public String getSemaforo21() {
		return this.semaforo21;
	}

	public void setSemaforo21(String semaforo21) {
		this.semaforo21 = semaforo21;
	}

	public String getSemaforo22() {
		return this.semaforo22;
	}

	public void setSemaforo22(String semaforo22) {
		this.semaforo22 = semaforo22;
	}

	public String getSemaforo23() {
		return this.semaforo23;
	}

	public void setSemaforo23(String semaforo23) {
		this.semaforo23 = semaforo23;
	}

	public String getSemaforo24() {
		return this.semaforo24;
	}

	public void setSemaforo24(String semaforo24) {
		this.semaforo24 = semaforo24;
	}

	public String getTema() {
		return this.tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getValido() {
		return this.valido;
	}

	public void setValido(String valido) {
		this.valido = valido;
	}

	public String getLineaBase() {
		return lineaBase;
	}

	public void setLineaBase(String lineaBase) {
		this.lineaBase = lineaBase;
	}

}