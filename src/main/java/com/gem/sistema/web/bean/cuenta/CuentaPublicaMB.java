package com.gem.sistema.web.bean.cuenta;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.jmx.export.annotation.ManagedAttribute;

@ManagedBean(name = "cuentaPublicaMB")
@ViewScoped
public class CuentaPublicaMB {

	@ManagedProperty("#{ingresoEgresoCuentaPublicaMB}")
	private IngresoEgresoCuentaPublicaMB ingresoEgreso;

	@ManagedProperty("#{cpEgresoProgramaMB}")
	private CPEgresoProgramaMB cpEgresoProgramaMB;

	@ManagedProperty("#{cpEgresoPilarEjeTransversalMB}")
	private CPEgresoPilarEjeTransversalMB cpEgresoPilarEjeTransversalMB;

	@ManagedProperty("#{cpCategoriaProgamaticaMB}")
	private CPCategoriaProgramaticaMB cpCategoriaProgamaticaMB;

	@ManagedProperty("#{cpAvanceOperativoMB}")
	private CPAvanceOperativoMB cpAvanceOperativoMB;

	@ManagedProperty("#{cpEgresoCapituloMB}")
	private CPEgresoCapituloMB cpEgresoCapituloMB;

	@ManagedProperty("#{cpEgresoObjetoGastoMB}")
	private CPEgresoObjetoGastoMB cpEgresoObjetoGastoMB;

	@ManagedProperty("#{cpClasAdminEntidadesMB}")
	private CPClasAdminEntidadesMB cpClasAdminEntidadesMB;

	@ManagedProperty("#{cpClasAdminDependenciaMB}")
	private CPClasAdminDependenciaMB cpClasAdminDependenciaMB;

	@ManagedProperty("#{cpClasAdminPoderOrgMB}")
	private CPClasAdminPoderOrgMB cpClasAdminPoderOrgMB;

	@ManagedProperty("#{cpIngresosMB}")
	private CPIngresosMB cpIngresosMB;

	@ManagedProperty("#{cPIndicadoresPosturaMB}")
	private CPIndicadoresPosturaMB cPIndicadoresPosturaMB;

	@ManagedProperty("#{endeudamientoNetoCuentaPublicaMB}")
	private EndeudamientoNetoCuentaPublicaMB endeudamientoNetoCuentaPublicaMB;

	@ManagedProperty("#{clasificEcoTipoGastoCeuntaPublicaMB}")
	private ClasificEcoTipoGastoCeuntaPublicaMB clasificEcoTipoGastoCeuntaPublicaMB;

	@ManagedProperty("#{interesDeudaCuentaPublicaMB}")
	private InteresDeudaCuentaPublicaMB interesDeudaCuentaPublicaMB;

	@ManagedProperty("#{estadoSituacionFinancieraDetalladoCuentaPublicaMB}")
	private EstadoSituacionFinancieraDetalladoCuentaPublicaMB estadoSituacionFinancieraDetalladoCuentaPublicaMB;

	@ManagedProperty("#{informeAnaliticoDeudaPublicaCuentaPublicaMB}")
	private InformeAnaliticoDeudaPublicaCuentaPublicaMB informeAnaliticoDeudaPublicaCuentaPublicaMB;
	                    
	@ManagedProperty("#{cPBalancePresupuestarioMB}")
	private CPBalancePresupuestarioMB cpBalancePresupuestarioMB;

	public IngresoEgresoCuentaPublicaMB getIngresoEgreso() {
		return ingresoEgreso;
	}

	public void setIngresoEgreso(IngresoEgresoCuentaPublicaMB ingresoEgreso) {
		this.ingresoEgreso = ingresoEgreso;
	}

	public CPEgresoProgramaMB getCpEgresoProgramaMB() {
		return cpEgresoProgramaMB;
	}

	public void setCpEgresoProgramaMB(CPEgresoProgramaMB cpEgresoProgramaMB) {
		this.cpEgresoProgramaMB = cpEgresoProgramaMB;
	}

	public CPEgresoPilarEjeTransversalMB getCpEgresoPilarEjeTransversalMB() {
		return cpEgresoPilarEjeTransversalMB;
	}

	public void setCpEgresoPilarEjeTransversalMB(CPEgresoPilarEjeTransversalMB cpEgresoPilarEjeTransversalMB) {
		this.cpEgresoPilarEjeTransversalMB = cpEgresoPilarEjeTransversalMB;
	}

	public CPCategoriaProgramaticaMB getCpCategoriaProgamaticaMB() {
		return cpCategoriaProgamaticaMB;
	}

	public void setCpCategoriaProgamaticaMB(CPCategoriaProgramaticaMB cpCategoriaProgamaticaMB) {
		this.cpCategoriaProgamaticaMB = cpCategoriaProgamaticaMB;
	}

	public CPAvanceOperativoMB getCpAvanceOperativoMB() {
		return cpAvanceOperativoMB;
	}

	public void setCpAvanceOperativoMB(CPAvanceOperativoMB cpAvanceOperativoMB) {
		this.cpAvanceOperativoMB = cpAvanceOperativoMB;
	}

	public CPEgresoCapituloMB getCpEgresoCapituloMB() {
		return cpEgresoCapituloMB;
	}

	public void setCpEgresoCapituloMB(CPEgresoCapituloMB cpEgresoCapituloMB) {
		this.cpEgresoCapituloMB = cpEgresoCapituloMB;
	}

	public CPEgresoObjetoGastoMB getCpEgresoObjetoGastoMB() {
		return cpEgresoObjetoGastoMB;
	}

	public void setCpEgresoObjetoGastoMB(CPEgresoObjetoGastoMB cpEgresoObjetoGastoMB) {
		this.cpEgresoObjetoGastoMB = cpEgresoObjetoGastoMB;
	}

	public CPClasAdminEntidadesMB getCpClasAdminEntidadesMB() {
		return cpClasAdminEntidadesMB;
	}

	public void setCpClasAdminEntidadesMB(CPClasAdminEntidadesMB cpClasAdminEntidadesMB) {
		this.cpClasAdminEntidadesMB = cpClasAdminEntidadesMB;
	}

	public CPClasAdminDependenciaMB getCpClasAdminDependenciaMB() {
		return cpClasAdminDependenciaMB;
	}

	public void setCpClasAdminDependenciaMB(CPClasAdminDependenciaMB cpClasAdminDependenciaMB) {
		this.cpClasAdminDependenciaMB = cpClasAdminDependenciaMB;
	}

	public CPClasAdminPoderOrgMB getCpClasAdminPoderOrgMB() {
		return cpClasAdminPoderOrgMB;
	}

	public void setCpClasAdminPoderOrgMB(CPClasAdminPoderOrgMB cpClasAdminPoderOrgMB) {
		this.cpClasAdminPoderOrgMB = cpClasAdminPoderOrgMB;
	}

	public CPIngresosMB getCpIngresosMB() {
		return cpIngresosMB;
	}

	public void setCpIngresosMB(CPIngresosMB cpIngresosMB) {
		this.cpIngresosMB = cpIngresosMB;
	}

	public CPIndicadoresPosturaMB getcPIndicadoresPosturaMB() {
		return cPIndicadoresPosturaMB;
	}

	public void setcPIndicadoresPosturaMB(CPIndicadoresPosturaMB cPIndicadoresPosturaMB) {
		this.cPIndicadoresPosturaMB = cPIndicadoresPosturaMB;
	}

	public EndeudamientoNetoCuentaPublicaMB getEndeudamientoNetoCuentaPublicaMB() {
		return endeudamientoNetoCuentaPublicaMB;
	}

	public void setEndeudamientoNetoCuentaPublicaMB(EndeudamientoNetoCuentaPublicaMB endeudamientoNetoCuentaPublicaMB) {
		this.endeudamientoNetoCuentaPublicaMB = endeudamientoNetoCuentaPublicaMB;
	}

	public ClasificEcoTipoGastoCeuntaPublicaMB getClasificEcoTipoGastoCeuntaPublicaMB() {
		return clasificEcoTipoGastoCeuntaPublicaMB;
	}

	public void setClasificEcoTipoGastoCeuntaPublicaMB(
			ClasificEcoTipoGastoCeuntaPublicaMB clasificEcoTipoGastoCeuntaPublicaMB) {
		this.clasificEcoTipoGastoCeuntaPublicaMB = clasificEcoTipoGastoCeuntaPublicaMB;
	}

	public InteresDeudaCuentaPublicaMB getInteresDeudaCuentaPublicaMB() {
		return interesDeudaCuentaPublicaMB;
	}

	public void setInteresDeudaCuentaPublicaMB(InteresDeudaCuentaPublicaMB interesDeudaCuentaPublicaMB) {
		this.interesDeudaCuentaPublicaMB = interesDeudaCuentaPublicaMB;
	}

	public EstadoSituacionFinancieraDetalladoCuentaPublicaMB getEstadoSituacionFinancieraDetalladoCuentaPublicaMB() {
		return estadoSituacionFinancieraDetalladoCuentaPublicaMB;
	}

	public void setEstadoSituacionFinancieraDetalladoCuentaPublicaMB(
			EstadoSituacionFinancieraDetalladoCuentaPublicaMB estadoSituacionFinancieraDetalladoCuentaPublicaMB) {
		this.estadoSituacionFinancieraDetalladoCuentaPublicaMB = estadoSituacionFinancieraDetalladoCuentaPublicaMB;
	}

	public InformeAnaliticoDeudaPublicaCuentaPublicaMB getInformeAnaliticoDeudaPublicaCuentaPublicaMB() {
		return informeAnaliticoDeudaPublicaCuentaPublicaMB;
	}

	public void setInformeAnaliticoDeudaPublicaCuentaPublicaMB(
			InformeAnaliticoDeudaPublicaCuentaPublicaMB informeAnaliticoDeudaPublicaCuentaPublicaMB) {
		this.informeAnaliticoDeudaPublicaCuentaPublicaMB = informeAnaliticoDeudaPublicaCuentaPublicaMB;
	}

	public CPBalancePresupuestarioMB getCpBalancePresupuestarioMB() {
		return cpBalancePresupuestarioMB;
	}

	public void setCpBalancePresupuestarioMB(CPBalancePresupuestarioMB cpBalancePresupuestarioMB) {
		this.cpBalancePresupuestarioMB = cpBalancePresupuestarioMB;
	}

}
