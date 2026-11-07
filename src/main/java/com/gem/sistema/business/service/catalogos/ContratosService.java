package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.ProveedorNuevo;
import com.gem.sistema.business.domain.TcAdquisicion;
import com.gem.sistema.business.domain.TcAdquisicionGiroSubgiro;
import com.gem.sistema.business.domain.TcAdquisicionPrograma;
import com.gem.sistema.business.domain.TcArticulosContrato;
import com.gem.sistema.business.domain.TcArticulosSA;
import com.gem.sistema.business.domain.TcContrato;
import com.gem.sistema.business.domain.TcContratosFirma;
import com.gem.sistema.business.domain.TcContratosRevison;
import com.gem.sistema.business.domain.TcConvenio;
import com.gem.sistema.business.domain.TcEstadoContrato;
import com.gem.sistema.business.domain.TcOrigenRecurso;
import com.gem.sistema.business.domain.TcProcedimientoAdquisitivo;
import com.gem.sistema.business.domain.TcTipoContrato;
import com.gem.sistema.business.domain.TcTipoGasto;
import com.gem.sistema.business.domain.TcUsuario;
import com.gem.sistema.business.domain.TrContratoDetail;
import com.gem.sistema.business.domain.TrContratoSolicitud;

public interface ContratosService {

	List<TcProcedimientoAdquisitivo> getProcedimientos();

	List<TcOrigenRecurso> getOrigenRecursos();

	List<TcAdquisicion> getAdquisicions();

	List<ProveedorNuevo> getProveedores();

	List<TcTipoContrato> getTipoContratos();

	List<TcTipoGasto> getTipoGastos();

	List<TrContratoDetail> getContratos();

	List<TcContratosFirma> getFirmasByContrato(Long idContrato);

	List<TcContratosRevison> getRevisiones(TcContrato contrato);

	List<TrContratoDetail> getContratosByUsuario(String usuario, String estado);

	List<TcAdquisicionGiroSubgiro> getSubgirosByAdquisicion(TcAdquisicion adquisicion);

	List<TcArticulosContrato> getArticulosContratoByContrato(TcContrato contrato);

	List<TcArticulosSA> getArticulosByAdquisicion(TcAdquisicion adquisicion);

	List<TcUsuario> getUsuariosList(Integer sector);

	List<TcContratosFirma> saveFirmas(List<TcContratosFirma> firmas);

	List<TcAdquisicionPrograma> getProgramasByAdquisicion(TcAdquisicion adquisicion);

	List<TrContratoSolicitud> saveContratoSolicitudes(List<TrContratoSolicitud> contratoSolicituds);

	List<TrContratoSolicitud> getSolicitudesByContrato(TcContrato contrato);

	TrContratoSolicitud getContratoSolicitud(TcContrato contrato, TcAdquisicion adquisicion);

	TcAdquisicion getAdquisicion(Long idLong);

	TcEstadoContrato getEstadoContrato(String estado);

	TcContrato saveContrato(TcContrato contrato);
	
	TcConvenio saveConvenio(TcConvenio convenio);

	void saveArticulos(List<TcArticulosContrato> articulosContratos);

	void saveRevision(TcContratosRevison revison);

	TrContratoDetail saveContrato(TrContratoDetail contrato);

	void deleteContrato(TrContratoDetail contrato);
}
