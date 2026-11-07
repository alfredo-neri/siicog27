package com.gem.sistema.business.service.catalogos;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.TwIngresoPropiosDetalle;
import com.gem.sistema.business.dto.IngresoPropioDTO;
import com.gem.sistema.business.dto.IngresosPropiosDetalleDTO;
import com.gem.sistema.business.dto.IngresosPropiosPolizaDTO;

public interface TwIngresoPropioDetalleService {

	IngresoPropioDTO cargaIngresoPropio(File newFile, Polide polide, BigDecimal totalIngresos);

	List<TwIngresoPropiosDetalle> getByPolide(Polide polide);

	void eliminaIngresoByPolide(Polide polide);

	List<IngresosPropiosPolizaDTO> getPolizasWithTotalIngreso(String mes, Integer anopol, Integer idSector);

	List<IngresosPropiosDetalleDTO> getIngresosWithNombre(IngresosPropiosPolizaDTO polide);

	Double getTotalIngresoByMes(Integer mes);

	Double getTotalCuentaByMes(Integer mes, Integer sector);
}
