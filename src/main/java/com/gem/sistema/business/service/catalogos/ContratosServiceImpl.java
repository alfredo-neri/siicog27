package com.gem.sistema.business.service.catalogos;

import static com.gem.sistema.business.predicates.TcUsuarioPredicates.findByIdSector;

import java.util.ArrayList;
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
import com.gem.sistema.business.repository.catalogs.ProveedoresNuevoRepository;
import com.gem.sistema.business.repository.catalogs.TcAdquisicionGiroSubgiroRepository;
import com.gem.sistema.business.repository.catalogs.TcAdquisicionProgramasRepository;
import com.gem.sistema.business.repository.catalogs.TcAdquisicionRepository;
import com.gem.sistema.business.repository.catalogs.TcArticuloContratoRepository;
import com.gem.sistema.business.repository.catalogs.TcArticulosSARepository;
import com.gem.sistema.business.repository.catalogs.TcContratoRevisionRepository;
import com.gem.sistema.business.repository.catalogs.TcContratosFirmaRepository;
import com.gem.sistema.business.repository.catalogs.TcContratosRepository;
import com.gem.sistema.business.repository.catalogs.TcConvenioRepository;
import com.gem.sistema.business.repository.catalogs.TcEstadosContratoRepository;
import com.gem.sistema.business.repository.catalogs.TcOrigenRecursoRepository;
import com.gem.sistema.business.repository.catalogs.TcProcedimientoAdquisitivoRepository;
import com.gem.sistema.business.repository.catalogs.TcTipoContratoRepository;
import com.gem.sistema.business.repository.catalogs.TcTiposGastoRepository;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;
import com.gem.sistema.business.repository.catalogs.TrContratoDetailRepository;
import com.gem.sistema.business.repository.catalogs.TrContratoSolicitudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "contratosService")
public class ContratosServiceImpl implements ContratosService {

	@Autowired
	private TcUsuarioRepository usuarioRepository;

	@Autowired
	private TcConvenioRepository convenioRepository;

	@Autowired
	private TcContratosRepository contratosRepository;

	@Autowired
	private TcTiposGastoRepository tiposGastoRepository;

	@Autowired
	private TcAdquisicionRepository adquisicionRepository;

	@Autowired
	private ProveedoresNuevoRepository proveedoresRepository;

	@Autowired
	private TcTipoContratoRepository tipoContratoRepository;

	@Autowired
	private TcOrigenRecursoRepository origenRecursoRepository;

	@Autowired
	private TcContratosFirmaRepository contratosFirmaRepository;

	@Autowired
	private TcEstadosContratoRepository estadosContratoRepository;

	@Autowired
	private TrContratoDetailRepository contratoDetailRepository;

	@Autowired
	private TcArticuloContratoRepository articuloContratoRepository;

	@Autowired
	private TcAdquisicionProgramasRepository adquisicionProgramasRepository;

	@Autowired
	private TcArticulosSARepository articulosRepository;

	@Autowired
	private TcContratoRevisionRepository contratoRevisionRepository;

	@Autowired
	private TrContratoSolicitudRepository contratoSolicitudRepository;

	@Autowired
	private TcAdquisicionGiroSubgiroRepository adquisicionGiroSubgiroRepository;

	@Autowired
	private TcProcedimientoAdquisitivoRepository procedimientoAdquisitivoRepository;

	@Override
	public List<TrContratoDetail> getContratos() {

		return contratoDetailRepository.findAllByOrderByContratoNumeroContratoAsc();
	}

	@Override
	public List<TcContratosFirma> getFirmasByContrato(Long idContrato) {
		return contratosFirmaRepository.getFirmasAllByContratoId(idContrato);
	}

	@Override
	public List<TcContratosRevison> getRevisiones(TcContrato contrato) {
		return null; // contratoRevisionRepository.findAllByContrato(contrato);
	}

	@Override
	public List<TrContratoDetail> getContratosByUsuario(String usuario, String estado) {
		return contratoDetailRepository
				.findAllByContratoRevisorUsuarioAndContratoEstadosContratoEstadoOrderByContratoNumeroContratoAsc(
						usuario, estado);
	}

	@Override
	public List<TcProcedimientoAdquisitivo> getProcedimientos() {
		return (List<TcProcedimientoAdquisitivo>) procedimientoAdquisitivoRepository.findAll();
	}

	@Override
	public List<TcTipoContrato> getTipoContratos() {
		// TODO Auto-generated method stub
		return (List<TcTipoContrato>) tipoContratoRepository.findAll();
	}

	@Override
	public List<ProveedorNuevo> getProveedores() {
		return proveedoresRepository.findAll();
	}

	@Override
	public List<TcOrigenRecurso> getOrigenRecursos() {
		return origenRecursoRepository.findAll();
	}

	@Override
	public List<TcAdquisicion> getAdquisicions() {
		return (List<TcAdquisicion>) adquisicionRepository.findAll();
	}

	@Override
	public List<TcAdquisicionGiroSubgiro> getSubgirosByAdquisicion(TcAdquisicion adquisicion) {
		return adquisicionGiroSubgiroRepository.findAllByAdquisicionOrderBySubgiroClaveAsc(adquisicion);
	}

	@Override
	public List<TcArticulosContrato> getArticulosContratoByContrato(TcContrato contrato) {
		return articuloContratoRepository.findAllByContrato(contrato);
	}

	@Override
	public List<TcArticulosSA> getArticulosByAdquisicion(TcAdquisicion adquisicion) {
		return articulosRepository.findAllByAdquisicion(adquisicion);
	}

	@Override
	public List<TcTipoGasto> getTipoGastos() {
		return tiposGastoRepository.findAll();
	}

	@Override
	public List<TcUsuario> getUsuariosList(Integer sector) {
		List<TcUsuario> usuarios = new ArrayList<TcUsuario>();

		usuarioRepository.findAll(findByIdSector(Long.valueOf(sector))).forEach(usuarios::add);
		return usuarios;
	}

	@Override
	public List<TcContratosFirma> saveFirmas(List<TcContratosFirma> firmas) {
		return (List<TcContratosFirma>) contratosFirmaRepository.save(firmas);
	}

	@Override
	public List<TrContratoSolicitud> saveContratoSolicitudes(List<TrContratoSolicitud> contratoSolicituds) {

		return (List<TrContratoSolicitud>) contratoSolicitudRepository.save(contratoSolicituds);
	}

	@Override
	public List<TrContratoSolicitud> getSolicitudesByContrato(TcContrato contrato) {
		return contratoSolicitudRepository.findAllByContrato(contrato);
	}

	@Override
	public List<TcAdquisicionPrograma> getProgramasByAdquisicion(TcAdquisicion adquisicion) {
		return adquisicionProgramasRepository.findAllByAdquisicion(adquisicion);
	}

	@Override
	public TrContratoSolicitud getContratoSolicitud(TcContrato contrato, TcAdquisicion adquisicion) {
		return contratoSolicitudRepository.findByContratoAndSolicitud(contrato, adquisicion);
	}

	@Override
	public TcAdquisicion getAdquisicion(Long idLong) {
		return adquisicionRepository.findOne(idLong);
	}

	@Override
	public TcEstadoContrato getEstadoContrato(String estado) {
		return estadosContratoRepository.findByEstado(estado);
	}

	@Override
	public TcConvenio saveConvenio(TcConvenio convenio) {
		return convenioRepository.save(convenio);
	}

	@Override
	public void saveArticulos(List<TcArticulosContrato> articulosContratos) {
		articuloContratoRepository.save(articulosContratos);
	}

	@Override
	public TcContrato saveContrato(TcContrato contrato) {
		return contratosRepository.save(contrato);
	}

	@Override
	public TrContratoDetail saveContrato(TrContratoDetail contrato) {
		return contratoDetailRepository.save(contrato);
	}

	@Override
	public void saveRevision(TcContratosRevison revison) {
		contratoRevisionRepository.save(revison);
	}

	@Override
	public void deleteContrato(TrContratoDetail contrato) {
		contratoDetailRepository.delete(contrato);
	}

	public TcUsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(TcUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public TcContratosRepository getContratosRepository() {
		return contratosRepository;
	}

	public void setContratosRepository(TcContratosRepository contratosRepository) {
		this.contratosRepository = contratosRepository;
	}

	public TcTipoContratoRepository getTipoContratoRepository() {
		return tipoContratoRepository;
	}

	public void setTipoContratoRepository(TcTipoContratoRepository tipoContratoRepository) {
		this.tipoContratoRepository = tipoContratoRepository;
	}

	public TcTiposGastoRepository getTiposGastoRepository() {
		return tiposGastoRepository;
	}

	public void setTiposGastoRepository(TcTiposGastoRepository tiposGastoRepository) {
		this.tiposGastoRepository = tiposGastoRepository;
	}

	public TcOrigenRecursoRepository getOrigenRecursoRepository() {
		return origenRecursoRepository;
	}

	public void setOrigenRecursoRepository(TcOrigenRecursoRepository origenRecursoRepository) {
		this.origenRecursoRepository = origenRecursoRepository;
	}

	public TcEstadosContratoRepository getEstadosContratoRepository() {
		return estadosContratoRepository;
	}

	public void setEstadosContratoRepository(TcEstadosContratoRepository estadosContratoRepository) {
		this.estadosContratoRepository = estadosContratoRepository;
	}

	public TcAdquisicionRepository getAdquisicionRepository() {
		return adquisicionRepository;
	}

	public void setAdquisicionRepository(TcAdquisicionRepository adquisicionRepository) {
		this.adquisicionRepository = adquisicionRepository;
	}

	public TrContratoDetailRepository getContratoDetailRepository() {
		return contratoDetailRepository;
	}

	public void setContratoDetailRepository(TrContratoDetailRepository contratoDetailRepository) {
		this.contratoDetailRepository = contratoDetailRepository;
	}

	public ProveedoresNuevoRepository getProveedoresRepository() {
		return proveedoresRepository;
	}

	public void setProveedoresRepository(ProveedoresNuevoRepository proveedoresRepository) {
		this.proveedoresRepository = proveedoresRepository;
	}

	public TcArticulosSARepository getArticulosRepository() {
		return articulosRepository;
	}

	public void setArticulosRepository(TcArticulosSARepository articulosRepository) {
		this.articulosRepository = articulosRepository;
	}

	public TcAdquisicionGiroSubgiroRepository getAdquisicionGiroSubgiroRepository() {
		return adquisicionGiroSubgiroRepository;
	}

	public void setAdquisicionGiroSubgiroRepository(
			TcAdquisicionGiroSubgiroRepository adquisicionGiroSubgiroRepository) {
		this.adquisicionGiroSubgiroRepository = adquisicionGiroSubgiroRepository;
	}

	public TcArticuloContratoRepository getArticuloContratoRepository() {
		return articuloContratoRepository;
	}

	public void setArticuloContratoRepository(TcArticuloContratoRepository articuloContratoRepository) {
		this.articuloContratoRepository = articuloContratoRepository;
	}

	public TcContratoRevisionRepository getContratoRevisionRepository() {
		return contratoRevisionRepository;
	}

	public void setContratoRevisionRepository(TcContratoRevisionRepository contratoRevisionRepository) {
		this.contratoRevisionRepository = contratoRevisionRepository;
	}

	public TcContratosFirmaRepository getContratosFirmaRepository() {
		return contratosFirmaRepository;
	}

	public void setContratosFirmaRepository(TcContratosFirmaRepository contratosFirmaRepository) {
		this.contratosFirmaRepository = contratosFirmaRepository;
	}

	public TcProcedimientoAdquisitivoRepository getProcedimientoAdquisitivoRepository() {
		return procedimientoAdquisitivoRepository;
	}

	public void setProcedimientoAdquisitivoRepository(
			TcProcedimientoAdquisitivoRepository procedimientoAdquisitivoRepository) {
		this.procedimientoAdquisitivoRepository = procedimientoAdquisitivoRepository;
	}

	public TcAdquisicionProgramasRepository getAdquisicionProgramasRepository() {
		return adquisicionProgramasRepository;
	}

	public void setAdquisicionProgramasRepository(TcAdquisicionProgramasRepository adquisicionProgramasRepository) {
		this.adquisicionProgramasRepository = adquisicionProgramasRepository;
	}

	public TrContratoSolicitudRepository getContratoSolicitudRepository() {
		return contratoSolicitudRepository;
	}

	public void setContratoSolicitudRepository(TrContratoSolicitudRepository contratoSolicitudRepository) {
		this.contratoSolicitudRepository = contratoSolicitudRepository;
	}

	public TcConvenioRepository getConvenioRepository() {
		return convenioRepository;
	}

	public void setConvenioRepository(TcConvenioRepository convenioRepository) {
		this.convenioRepository = convenioRepository;
	}

}
