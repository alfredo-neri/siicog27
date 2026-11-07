package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.dto.TotalIngresoEgreso;

public interface CuentaPublicaService {
	
	TotalIngresoEgreso getTotalIngresoEgreso();
	
	Conctb getAllConctb(Integer idSector);
	
	String getQueryGastoCatProgramatica(Integer idSector);
	
	String getQueryEgresosPorPrograma(Integer idSector);
	
	String getQueryPilarEjeTrasversal(Integer idSector);
	
	String getQueryAvanceOperativo(Integer idSector);

	String getQueryEgresosPorCapitulo(Integer idSector);

	String getQueryEgresosObjetoGasto(Integer idSector);

	String getQueryClasAdminEntidades(Integer idSector);

	String getQueryClasAdminDependencia(Integer idSector);

	String getQueryClasAdminPoderOrg(Integer idSector);

	TotalIngresoEgreso getTotalEgreso();
	
	String getQueryIngresos(Integer idSector);

}
