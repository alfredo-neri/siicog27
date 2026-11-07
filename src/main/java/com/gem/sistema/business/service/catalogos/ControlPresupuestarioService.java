package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.TcAccion;
import com.gem.sistema.business.domain.TcArea;
import com.gem.sistema.business.domain.TcContrato;
import com.gem.sistema.business.domain.TcContratoPad;
import com.gem.sistema.business.domain.TcControlPresupuestario;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcStatusPago;
import com.gem.sistema.business.domain.TrAreaAccion;
import com.gem.sistema.business.domain.TrPresupuestoArea;
import com.gem.sistema.business.domain.TrPresupuestoDependencia;
import com.gem.sistema.business.domain.TwAccionContrato;
import com.gem.sistema.business.domain.TwContratoFactura;
import com.gem.sistema.business.domain.TwObra;
import com.gem.sistema.business.domain.TwObraPago;

public interface ControlPresupuestarioService {

	List<TcControlPresupuestario> getControlPresupuestario();

	List<TrPresupuestoDependencia> getDependenciasWithPresupuesto();

	List<TrPresupuestoArea> getAreasWithPresupuesto();

	TcControlPresupuestario getControlPresupuestario(String claveControl);

	TwAccionContrato saveAccionConytrato(TwAccionContrato accionContrato);

	TcControlPresupuestario saveControlPresupuestario(TcControlPresupuestario presupuestario);

	List<TrPresupuestoArea> savePresupuestoAreas(List<TrPresupuestoArea> presupuestoAreas);
	
	List<TrPresupuestoDependencia> savePresupuestoDependencias(List<TrPresupuestoDependencia> presupuestoDependencias);

	TwContratoFactura saveContratoFactura(TwContratoFactura contratoFactura);

	List<TcPeriodo> getActivePeriodos(Integer sector);

	List<Cattpo> getAllTiposPoliza();

	TcContrato getContratos(String numeroContrato);

	List<TcStatusPago> getStatusPagos();

	List<TcAccion> getAccionsByDireccion(TcArea area);
	
	List<TcAccion> getAllAccions();

	List<TcArea> getAllAreas();
	
	List<Catdep> getAllDependencias();

	Polien getPolien(Integer mespol, Integer anopol, Integer conpol, String tippol, Integer sector);

	TrPresupuestoArea savePresupuestoArea(TrPresupuestoArea presupuestoArea);
	
	TrPresupuestoDependencia savePresupuestoDependencia(TrPresupuestoDependencia presupuestoDependencia);

	TrAreaAccion saveAreaAccion(TrAreaAccion areaAccion);
	
	TwObra saveObra(TwObra obra);
	
	TwObraPago savePagoObra(TwObraPago pago);
	
	TrAreaAccion getAreaAccion(TrPresupuestoArea presupuestoArea, TcAccion accion);
	
	List<TcContratoPad> getAllContratosPad();
	
	TcContratoPad saveContratoPad(TcContratoPad contratoPad);
	
	TcContratoPad getContratoPad(String numeroContrato);
}
