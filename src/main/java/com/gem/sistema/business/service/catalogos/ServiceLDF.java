package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.dto.TotalFlujoEfectivo;

public interface ServiceLDF {

	String getQuery(Integer mes);
	
	String getQueryInfoem(Integer mes);
	
	String queryIngresosDetallado(Integer mes);
	
	Conctb getAllConctb(Integer idSector);
	
	String getQueryActivo(Integer trimestre);
	
	String getQueryPasivo(Integer trimestre);
	
	String getQuerySituacion(Integer trimestre);

	String getQueryEstadoSituacionFinancieraConac(Integer trimestre, char tipo, char tipoCuentas);

	String getQueryTotalesConac(Integer trimestre);

	String getQeryEstadoAnaliticoConac(char tipo,Integer trimestre);

	String getQueryEdoCambioConac(Integer trimestre);
	
	String getQueryEdoCambioInfoem(Integer trimestre, Integer sector, boolean mensual);

	String getQueryActividadesConac(Integer trimestre, char tipo );

	String getQueryVariacionHaciendaConac(Integer trimestre);

	String getQueryActivoConac(Integer trimestre);

	String getQueryPasivoConac(Integer trimestre);

	String getQueryHaciendConac(Integer trimestre);

//	String getQeryEstadoAnaliticoConac(Integer trimestre);

//	String getQueryActividadesConac(Integer trimestre);
	
	String getQueryVariacionHaciendaInfoem(Integer trimestre, Integer idSector, boolean mensual);

	String getQueryEstadoSituacionFinancieraActivosConac(Integer trimestre);

	String getQueryEstadoSituacionFinancieraPasivosConac(Integer trimestre);

	String getQueryEstadoSituacionFinancieraHaciendaConac(Integer trimestre);

	String getQueryEstadoAnaliticoIngresoDetalladoConac(Integer trimestre);
	
	String getQueryEstadoAnaliticoIngresoDetalladoInfoem(Integer trimestre, Integer idSector);

	String getQueryEstadoAnaliticoIngresoLDF1(Integer trimestre);

	String getQueryEstadoAnaliticoIngresoLDF2(Integer trimestre);

	String getQueryBalancePresupuestario1(Integer trimestre);

	String getQueryBalancePresupuestario2(Integer trimestre);

	String getQueryBalancePresupuestario3(Integer trimestre);

	String getQueryBalancePresupuestario4(Integer trimestre);

	String getQueryBalancePresupuestario5(Integer trimestre);

	String getQueryBalancePresupuestario1(Integer trimestre, boolean mensual);

	String getQueryBalancePresupuestario2();

	String getQueryBalancePresupuestario3();

	String getQueryBalancePresupuestario4(Integer trimestre, boolean mensual);

	String getQueryBalancePresupuestario5();

	String getQueryEndeudamientoNeto(Integer trimestre);

	String getQueryInteresDeuda(Integer trimestre);

	String getQueryEstadoFlujoEfectivoLDF(char tip, Integer trimestre );

	TotalFlujoEfectivo getQueryTotalFlujoEfectivoLDF(char tip, Integer trimestre);

//	String getQueryEstadoFlujoEfectivoLDF(Integer trimestre);

//	List<TotalFlujoEfectivo> getQueryTotalFlujoEfectivoLDF(Integer trimestre);

	String getQueryEstadoAnaliticoIngresoInfoemRI(Integer periodo, Integer sector, boolean mensual);

	String getQueryEstadoAnaliticoIngresoInfoemPE();

	String getQueryEstadoAnaliticoIngresoInfoemEP(Integer periodo, Integer sector, boolean mensual);

	String getQueryEstadoAnaliticoIngresoInfoemDF(Integer periodo, Integer sector, boolean mensual);

	String getQueryEstadoSituacionFinancieraActivosInfoem(Integer trimestre, Integer idSector);
	
	String getQueryEstadoSituacionFinancieraPasivosInfoem(Integer periodo, Integer idSector);
	
	String getQueryEstadoSituacionFinancieraHaciendaInfoem(Integer periodo, Integer idSector);

}
