package com.gem.sistema.web.bean.cuenta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.springframework.jmx.export.annotation.ManagedAttribute;

@Component("cuentaPublicaMB")
@Scope("view")
public class CuentaPublicaMB {

	@Value("#{ingresoEgresoCuentaPublicaMB}")
	private IngresoEgresoCuentaPublicaMB ingresoEgreso;

	@Value("#{cpEgresoProgramaMB}")
	private CPEgresoProgramaMB cpEgresoProgramaMB;

	@Value("#{cpEgresoPilarEjeTransversalMB}")
	private CPEgresoPilarEjeTransversalMB cpEgresoPilarEjeTransversalMB;

	@Value("#{cpCategoriaProgamaticaMB}")
	private CPCategoriaProgramaticaMB cpCategoriaProgamaticaMB;

	@Value("#{cpAvanceOperativoMB}")
	private CPAvanceOperativoMB cpAvanceOperativoMB;

	@Value("#{cpEgresoCapituloMB}")
	private CPEgresoCapituloMB cpEgresoCapituloMB;

	@Value("#{cpEgresoObjetoGastoMB}")
	private CPEgresoObjetoGastoMB cpEgresoObjetoGastoMB;

	@Value("#{cpClasAdminEntidadesMB}")
	private CPClasAdminEntidadesMB cpClasAdminEntidadesMB;

	@Value("#{cpClasAdminDependenciaMB}")
	private CPClasAdminDependenciaMB cpClasAdminDependenciaMB;

	@Value("#{cpClasAdminPoderOrgMB}")
	private CPClasAdminPoderOrgMB cpClasAdminPoderOrgMB;

	@Value("#{cpIngresosMB}")
	private CPIngresosMB cpIngresosMB;

	@Value("#{cPIndicadoresPosturaMB}")
	private CPIndicadoresPosturaMB cPIndicadoresPosturaMB;

	@Value("#{endeudamientoNetoCuentaPublicaMB}")
	private EndeudamientoNetoCuentaPublicaMB endeudamientoNetoCuentaPublicaMB;

	@Value("#{clasificEcoTipoGastoCeuntaPublicaMB}")
	private ClasificEcoTipoGastoCeuntaPublicaMB clasificEcoTipoGastoCeuntaPublicaMB;

	@Value("#{interesDeudaCuentaPublicaMB}")
	private InteresDeudaCuentaPublicaMB interesDeudaCuentaPublicaMB;

	@Value("#{estadoSituacionFinancieraDetalladoCuentaPublicaMB}")
	private EstadoSituacionFinancieraDetalladoCuentaPublicaMB estadoSituacionFinancieraDetalladoCuentaPublicaMB;

	@Value("#{informeAnaliticoDeudaPublicaCuentaPublicaMB}")
	private InformeAnaliticoDeudaPublicaCuentaPublicaMB informeAnaliticoDeudaPublicaCuentaPublicaMB;
	                    
	// difem26: "#{cPBalancePresupuestarioMB}" no corresponde a ningun bean; JSF inyectaba null. Pendiente de corregir.
	@Value("#{null}")
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
