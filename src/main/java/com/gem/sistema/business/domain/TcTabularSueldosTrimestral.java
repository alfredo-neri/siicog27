package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * The persistent class for the TC_TABULAR_SUELDOS_TRIMESTRAL database table.
 * 
 */
@Entity
@Table(name = "TC_TABULAR_SUELDOS_TRIMESTRAL")
@NamedQuery(name = "TcTabularSueldosTrimestral.findAll", query = "SELECT t FROM TcTabularSueldosTrimestral t")
public class TcTabularSueldosTrimestral extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ACCIDENTE_TRABAJO")
	private BigDecimal accidenteTrabajo;

	private BigDecimal aguinaldo;

	@Column(name = "AGUINALDO_EVENTUAL")
	private BigDecimal aguinaldoEventual;

	@Column(name = "AHORRO_RETIRO")
	private BigDecimal ahorroRetiro;

	@Column(name = "ANIOS_SERVICIO")
	private BigDecimal aniosServicio;

	private BigDecimal antiguedad;

	private BigDecimal becas;

	@Column(name = "CAPITALIZACION_INDIVIDUAL")
	private BigDecimal capitalizacionIndividual;

	@Column(name = "CARRERA_DOCENTE")
	private BigDecimal carreraDocente;

	@Column(name = "CARRERA_MAGISTERIAL")
	private BigDecimal carreraMagisterial;

	@Column(name = "CATEGORIA_CONFIANZA")
	private Integer categoriaConfianza;

	@Column(name = "CATEGORIA_EVENTUAL")
	private Integer categoriaEventual;

	@Column(name = "CATEGORIA_SINDICALIZADO")
	private Integer categoriaSindicalizado;

	@Column(name = "CIVICOS_ECONOMICOS")
	private BigDecimal civicosEconomicos;

	private BigDecimal compensacion;

	@Column(name = "CUSTODIA_VALORES")
	private BigDecimal custodiaValores;

	@Column(name = "DERIVADO_CONVENIO")
	private BigDecimal derivadoConvenio;

	private BigDecimal despensa;

	private BigDecimal dietas;

	@Column(name = "DIFERENCIA_ESCUELAS")
	private BigDecimal diferenciaEscuelas;

	@Column(name = "ESTUDIOS_POSTGRADO")
	private BigDecimal estudiosPostgrado;

	@Column(name = "ESTUDIOS_SUPERIORES")
	private BigDecimal estudiosSuperiores;

	@Column(name = "FECHA_ALTA")
	private Timestamp fechaAlta = new Timestamp(new Date().getTime());

	@Column(name = "FONDO_RETIRO")
	private BigDecimal fondoRetiro;

	private BigDecimal fovissste;

	private BigDecimal gratificacion;

	@Column(name = "GRATIFICACION_CONVENIO")
	private BigDecimal gratificacionConvenio;

	@Column(name = "GRATIFICACION_PRODUCTIVA")
	private BigDecimal gratificacionProductiva;

	private BigDecimal haberes;

	private BigDecimal honorarios;

	@Column(name = "HORARIOS_ESPECIALES")
	private BigDecimal horariosEspeciales;

	@Column(name = "HORAS_CLASE")
	private BigDecimal horasClase;

	@Column(name = "HORAS_EXTRAORDINARIAS")
	private BigDecimal horasExtraordinarias;

	private BigDecimal interinato;

	private BigDecimal issemym;

	private BigDecimal isste;

	private BigDecimal jubilacion;

	@Column(name = "LABORAL_EXTRANJERO")
	private BigDecimal laboralExtranjero;

	@Column(name = "LABORES_DOCENTE")
	private BigDecimal laboresDocente;

	private BigDecimal magisterio;

	@Column(name = "MEDICOS_RESIDENTES")
	private BigDecimal medicosResidentes;

	@Column(name = "NIVEL_SALARIAL")
	private String nivelSalarial;

	@Column(name = "NO_PLAZA")
	private Integer noPlaza;

	private BigDecimal permanencia;

	@Column(name = "PERSONAL_EVENTUAL")
	private BigDecimal personalEventual;

	@Column(name = "PRIMA_DOMINICAL")
	private BigDecimal primaDominical;

	@Column(name = "PRIMA_VACACIONAL")
	private BigDecimal primaVacacional;

	

	private BigDecimal profesionalizacion;

	@Column(name = "PUESTO_FUNCIONAL")
	private String puestoFuncional;

	private BigDecimal puntualidad;

	private BigDecimal recompensas;

	private BigDecimal reconocimiento;

	private BigDecimal representante;

	private BigDecimal retabulacion;

	@Column(name = "RIESGO_TRABAJO")
	private BigDecimal riesgoTrabajo;

	@Column(name = "RIESGO_PROFESIONAL")
	private BigDecimal riesgoProfesional;

	@Column(name = "SEGURIDAD_SOCIAL")
	private BigDecimal seguridadSocial;

	@Column(name = "SEGURO_VIDA")
	private BigDecimal seguroVida;

	@Column(name = "SEGUROS_FIANZAS")
	private BigDecimal segurosFianzas;

	@Column(name = "SEPARACION_INDIVIDUAL")
	private BigDecimal separacionIndividual;

	@Column(name = "SERVICIO_SALUD")
	private BigDecimal servicioSalud;

	@Column(name = "SERVICIO_SOCIAL")
	private BigDecimal servicioSocial;

	@Column(name = "SERVICIOS_ESPECIALES")
	private BigDecimal serviciosEspeciales;

	@Column(name = "SERVIDOR_PUBLICO")
	private BigDecimal servidorPublico;

	private BigDecimal sidicalizados;

	private BigDecimal sobrehaberes;

	@Column(name = "SOLIDARIO_REPARTO")
	private BigDecimal solidarioReparto;

	@Column(name = "SUELDO_BASE")
	private BigDecimal sueldoBase;

	@Column(name = "SUELDO_MAGISTERIO")
	private BigDecimal sueldoMagisterio;

	@Column(name = "SUELDO_SALARIO_CAIDOS")
	private BigDecimal sueldoSalarioCaidos;

	@Column(name = "TECNICO_ESPECIAL")
	private BigDecimal tecnicoEspecial;

	private BigDecimal tesis;

	private BigDecimal total;

	@Column(name = "TRABAJADORES_SINDICALIZADOS")
	private BigDecimal trabajadoresSindicalizados;

	@Column(name = "VACACIONES_FINIQUITO")
	private BigDecimal vacacionesFiniquito;

	private BigDecimal vejez;

	private BigDecimal viaticos;

	public TcTabularSueldosTrimestral() {
	}

	public BigDecimal getAccidenteTrabajo() {
		return this.accidenteTrabajo;
	}

	public void setAccidenteTrabajo(BigDecimal accidenteTrabajo) {
		this.accidenteTrabajo = accidenteTrabajo;
	}

	public BigDecimal getAguinaldo() {
		return this.aguinaldo;
	}

	public void setAguinaldo(BigDecimal aguinaldo) {
		this.aguinaldo = aguinaldo;
	}

	public BigDecimal getAguinaldoEventual() {
		return this.aguinaldoEventual;
	}

	public void setAguinaldoEventual(BigDecimal aguinaldoEventual) {
		this.aguinaldoEventual = aguinaldoEventual;
	}

	public BigDecimal getAhorroRetiro() {
		return this.ahorroRetiro;
	}

	public void setAhorroRetiro(BigDecimal ahorroRetiro) {
		this.ahorroRetiro = ahorroRetiro;
	}

	public BigDecimal getAniosServicio() {
		return this.aniosServicio;
	}

	public void setAniosServicio(BigDecimal aniosServicio) {
		this.aniosServicio = aniosServicio;
	}

	public BigDecimal getAntiguedad() {
		return this.antiguedad;
	}

	public void setAntiguedad(BigDecimal antiguedad) {
		this.antiguedad = antiguedad;
	}

	public BigDecimal getBecas() {
		return this.becas;
	}

	public void setBecas(BigDecimal becas) {
		this.becas = becas;
	}

	public BigDecimal getCapitalizacionIndividual() {
		return this.capitalizacionIndividual;
	}

	public void setCapitalizacionIndividual(BigDecimal capitalizacionIndividual) {
		this.capitalizacionIndividual = capitalizacionIndividual;
	}

	public BigDecimal getCarreraDocente() {
		return this.carreraDocente;
	}

	public void setCarreraDocente(BigDecimal carreraDocente) {
		this.carreraDocente = carreraDocente;
	}

	public BigDecimal getCarreraMagisterial() {
		return this.carreraMagisterial;
	}

	public void setCarreraMagisterial(BigDecimal carreraMagisterial) {
		this.carreraMagisterial = carreraMagisterial;
	}

	public Integer getCategoriaConfianza() {
		return this.categoriaConfianza;
	}

	public void setCategoriaConfianza(Integer categoriaConfianza) {
		this.categoriaConfianza = categoriaConfianza;
	}

	public Integer getCategoriaEventual() {
		return this.categoriaEventual;
	}

	public void setCategoriaEventual(Integer categoriaEventual) {
		this.categoriaEventual = categoriaEventual;
	}

	public Integer getCategoriaSindicalizado() {
		return this.categoriaSindicalizado;
	}

	public void setCategoriaSindicalizado(Integer categoriaSindicalizado) {
		this.categoriaSindicalizado = categoriaSindicalizado;
	}

	public BigDecimal getCivicosEconomicos() {
		return this.civicosEconomicos;
	}

	public void setCivicosEconomicos(BigDecimal civicosEconomicos) {
		this.civicosEconomicos = civicosEconomicos;
	}

	public BigDecimal getCompensacion() {
		return this.compensacion;
	}

	public void setCompensacion(BigDecimal compensacion) {
		this.compensacion = compensacion;
	}

	public BigDecimal getCustodiaValores() {
		return this.custodiaValores;
	}

	public void setCustodiaValores(BigDecimal custodiaValores) {
		this.custodiaValores = custodiaValores;
	}

	public BigDecimal getDerivadoConvenio() {
		return this.derivadoConvenio;
	}

	public void setDerivadoConvenio(BigDecimal derivadoConvenio) {
		this.derivadoConvenio = derivadoConvenio;
	}

	public BigDecimal getDespensa() {
		return this.despensa;
	}

	public void setDespensa(BigDecimal despensa) {
		this.despensa = despensa;
	}

	public BigDecimal getDietas() {
		return this.dietas;
	}

	public void setDietas(BigDecimal dietas) {
		this.dietas = dietas;
	}

	public BigDecimal getDiferenciaEscuelas() {
		return this.diferenciaEscuelas;
	}

	public void setDiferenciaEscuelas(BigDecimal diferenciaEscuelas) {
		this.diferenciaEscuelas = diferenciaEscuelas;
	}

	public BigDecimal getEstudiosPostgrado() {
		return this.estudiosPostgrado;
	}

	public void setEstudiosPostgrado(BigDecimal estudiosPostgrado) {
		this.estudiosPostgrado = estudiosPostgrado;
	}

	public BigDecimal getEstudiosSuperiores() {
		return this.estudiosSuperiores;
	}

	public void setEstudiosSuperiores(BigDecimal estudiosSuperiores) {
		this.estudiosSuperiores = estudiosSuperiores;
	}

	public Timestamp getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public BigDecimal getFondoRetiro() {
		return this.fondoRetiro;
	}

	public void setFondoRetiro(BigDecimal fondoRetiro) {
		this.fondoRetiro = fondoRetiro;
	}

	public BigDecimal getFovissste() {
		return fovissste;
	}

	public void setFovissste(BigDecimal fovissste) {
		this.fovissste = fovissste;
	}

	public BigDecimal getGratificacion() {
		return this.gratificacion;
	}

	public void setGratificacion(BigDecimal gratificacion) {
		this.gratificacion = gratificacion;
	}

	public BigDecimal getGratificacionConvenio() {
		return this.gratificacionConvenio;
	}

	public void setGratificacionConvenio(BigDecimal gratificacionConvenio) {
		this.gratificacionConvenio = gratificacionConvenio;
	}

	public BigDecimal getGratificacionProductiva() {
		return this.gratificacionProductiva;
	}

	public void setGratificacionProductiva(BigDecimal gratificacionProductiva) {
		this.gratificacionProductiva = gratificacionProductiva;
	}

	public BigDecimal getHaberes() {
		return this.haberes;
	}

	public void setHaberes(BigDecimal haberes) {
		this.haberes = haberes;
	}

	public BigDecimal getHonorarios() {
		return this.honorarios;
	}

	public void setHonorarios(BigDecimal honorarios) {
		this.honorarios = honorarios;
	}

	public BigDecimal getHorariosEspeciales() {
		return this.horariosEspeciales;
	}

	public void setHorariosEspeciales(BigDecimal horariosEspeciales) {
		this.horariosEspeciales = horariosEspeciales;
	}

	public BigDecimal getHorasClase() {
		return this.horasClase;
	}

	public void setHorasClase(BigDecimal horasClase) {
		this.horasClase = horasClase;
	}

	public BigDecimal getHorasExtraordinarias() {
		return this.horasExtraordinarias;
	}

	public void setHorasExtraordinarias(BigDecimal horasExtraordinarias) {
		this.horasExtraordinarias = horasExtraordinarias;
	}

	public BigDecimal getInterinato() {
		return this.interinato;
	}

	public void setInterinato(BigDecimal interinato) {
		this.interinato = interinato;
	}

	public BigDecimal getIssemym() {
		return this.issemym;
	}

	public void setIssemym(BigDecimal issemym) {
		this.issemym = issemym;
	}

	public BigDecimal getIsste() {
		return this.isste;
	}

	public void setIsste(BigDecimal isste) {
		this.isste = isste;
	}

	public BigDecimal getJubilacion() {
		return this.jubilacion;
	}

	public void setJubilacion(BigDecimal jubilacion) {
		this.jubilacion = jubilacion;
	}

	public BigDecimal getLaboralExtranjero() {
		return this.laboralExtranjero;
	}

	public void setLaboralExtranjero(BigDecimal laboralExtranjero) {
		this.laboralExtranjero = laboralExtranjero;
	}

	public BigDecimal getLaboresDocente() {
		return this.laboresDocente;
	}

	public void setLaboresDocente(BigDecimal laboresDocente) {
		this.laboresDocente = laboresDocente;
	}

	public BigDecimal getMagisterio() {
		return this.magisterio;
	}

	public void setMagisterio(BigDecimal magisterio) {
		this.magisterio = magisterio;
	}

	public BigDecimal getMedicosResidentes() {
		return this.medicosResidentes;
	}

	public void setMedicosResidentes(BigDecimal medicosResidentes) {
		this.medicosResidentes = medicosResidentes;
	}

	public String getNivelSalarial() {
		return this.nivelSalarial;
	}

	public void setNivelSalarial(String nivelSalarial) {
		this.nivelSalarial = nivelSalarial;
	}

	public Integer getNoPlaza() {
		return this.noPlaza;
	}

	public void setNoPlaza(Integer noPlaza) {
		this.noPlaza = noPlaza;
	}

	public BigDecimal getPermanencia() {
		return this.permanencia;
	}

	public void setPermanencia(BigDecimal permanencia) {
		this.permanencia = permanencia;
	}

	public BigDecimal getPersonalEventual() {
		return this.personalEventual;
	}

	public void setPersonalEventual(BigDecimal personalEventual) {
		this.personalEventual = personalEventual;
	}

	public BigDecimal getPrimaDominical() {
		return this.primaDominical;
	}

	public void setPrimaDominical(BigDecimal primaDominical) {
		this.primaDominical = primaDominical;
	}

	public BigDecimal getPrimaVacacional() {
		return this.primaVacacional;
	}

	public void setPrimaVacacional(BigDecimal primaVacacional) {
		this.primaVacacional = primaVacacional;
	}

	

	public BigDecimal getProfesionalizacion() {
		return this.profesionalizacion;
	}

	public void setProfesionalizacion(BigDecimal profesionalizacion) {
		this.profesionalizacion = profesionalizacion;
	}

	public String getPuestoFuncional() {
		return this.puestoFuncional;
	}

	public void setPuestoFuncional(String puestoFuncional) {
		this.puestoFuncional = puestoFuncional;
	}

	public BigDecimal getPuntualidad() {
		return this.puntualidad;
	}

	public void setPuntualidad(BigDecimal puntualidad) {
		this.puntualidad = puntualidad;
	}

	public BigDecimal getRecompensas() {
		return this.recompensas;
	}

	public void setRecompensas(BigDecimal recompensas) {
		this.recompensas = recompensas;
	}

	public BigDecimal getReconocimiento() {
		return this.reconocimiento;
	}

	public void setReconocimiento(BigDecimal reconocimiento) {
		this.reconocimiento = reconocimiento;
	}

	public BigDecimal getRepresentante() {
		return this.representante;
	}

	public void setRepresentante(BigDecimal representante) {
		this.representante = representante;
	}

	public BigDecimal getRetabulacion() {
		return this.retabulacion;
	}

	public void setRetabulacion(BigDecimal retabulacion) {
		this.retabulacion = retabulacion;
	}

	public BigDecimal getRiesgoTrabajo() {
		return riesgoTrabajo;
	}

	public void setRiesgoTrabajo(BigDecimal riesgoTrabajo) {
		this.riesgoTrabajo = riesgoTrabajo;
	}

	public BigDecimal getRiesgoProfesional() {
		return this.riesgoProfesional;
	}

	public void setRiesgoProfesional(BigDecimal riesgoProfesional) {
		this.riesgoProfesional = riesgoProfesional;
	}

	public BigDecimal getSeguridadSocial() {
		return this.seguridadSocial;
	}

	public void setSeguridadSocial(BigDecimal seguridadSocial) {
		this.seguridadSocial = seguridadSocial;
	}

	public BigDecimal getSeguroVida() {
		return this.seguroVida;
	}

	public void setSeguroVida(BigDecimal seguroVida) {
		this.seguroVida = seguroVida;
	}

	public BigDecimal getSegurosFianzas() {
		return this.segurosFianzas;
	}

	public void setSegurosFianzas(BigDecimal segurosFianzas) {
		this.segurosFianzas = segurosFianzas;
	}

	public BigDecimal getSeparacionIndividual() {
		return this.separacionIndividual;
	}

	public void setSeparacionIndividual(BigDecimal separacionIndividual) {
		this.separacionIndividual = separacionIndividual;
	}

	public BigDecimal getServicioSalud() {
		return this.servicioSalud;
	}

	public void setServicioSalud(BigDecimal servicioSalud) {
		this.servicioSalud = servicioSalud;
	}

	public BigDecimal getServicioSocial() {
		return this.servicioSocial;
	}

	public void setServicioSocial(BigDecimal servicioSocial) {
		this.servicioSocial = servicioSocial;
	}

	public BigDecimal getServiciosEspeciales() {
		return this.serviciosEspeciales;
	}

	public void setServiciosEspeciales(BigDecimal serviciosEspeciales) {
		this.serviciosEspeciales = serviciosEspeciales;
	}

	public BigDecimal getServidorPublico() {
		return this.servidorPublico;
	}

	public void setServidorPublico(BigDecimal servidorPublico) {
		this.servidorPublico = servidorPublico;
	}

	public BigDecimal getSidicalizados() {
		return this.sidicalizados;
	}

	public void setSidicalizados(BigDecimal sidicalizados) {
		this.sidicalizados = sidicalizados;
	}

	public BigDecimal getSobrehaberes() {
		return this.sobrehaberes;
	}

	public void setSobrehaberes(BigDecimal sobrehaberes) {
		this.sobrehaberes = sobrehaberes;
	}

	public BigDecimal getSolidarioReparto() {
		return this.solidarioReparto;
	}

	public void setSolidarioReparto(BigDecimal solidarioReparto) {
		this.solidarioReparto = solidarioReparto;
	}

	public BigDecimal getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(BigDecimal sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	public BigDecimal getSueldoMagisterio() {
		return this.sueldoMagisterio;
	}

	public void setSueldoMagisterio(BigDecimal sueldoMagisterio) {
		this.sueldoMagisterio = sueldoMagisterio;
	}

	public BigDecimal getSueldoSalarioCaidos() {
		return this.sueldoSalarioCaidos;
	}

	public void setSueldoSalarioCaidos(BigDecimal sueldoSalarioCaidos) {
		this.sueldoSalarioCaidos = sueldoSalarioCaidos;
	}

	public BigDecimal getTecnicoEspecial() {
		return this.tecnicoEspecial;
	}

	public void setTecnicoEspecial(BigDecimal tecnicoEspecial) {
		this.tecnicoEspecial = tecnicoEspecial;
	}

	public BigDecimal getTesis() {
		return this.tesis;
	}

	public void setTesis(BigDecimal tesis) {
		this.tesis = tesis;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTrabajadoresSindicalizados() {
		return this.trabajadoresSindicalizados;
	}

	public void setTrabajadoresSindicalizados(BigDecimal trabajadoresSindicalizados) {
		this.trabajadoresSindicalizados = trabajadoresSindicalizados;
	}

	public BigDecimal getVacacionesFiniquito() {
		return this.vacacionesFiniquito;
	}

	public void setVacacionesFiniquito(BigDecimal vacacionesFiniquito) {
		this.vacacionesFiniquito = vacacionesFiniquito;
	}

	public BigDecimal getVejez() {
		return vejez;
	}

	public void setVejez(BigDecimal vejez) {
		this.vejez = vejez;
	}

	public BigDecimal getViaticos() {
		return this.viaticos;
	}

	public void setViaticos(BigDecimal viaticos) {
		this.viaticos = viaticos;
	}

}