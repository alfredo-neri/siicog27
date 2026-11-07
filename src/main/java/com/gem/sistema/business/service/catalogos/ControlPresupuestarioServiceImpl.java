package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Conctb;
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
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcAccionRepository;
import com.gem.sistema.business.repository.catalogs.TcAreaRepository;
import com.gem.sistema.business.repository.catalogs.TcContratoPadRepository;
import com.gem.sistema.business.repository.catalogs.TcContratosRepository;
import com.gem.sistema.business.repository.catalogs.TcControlPresupuestarioRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcStatusPagoRepository;
import com.gem.sistema.business.repository.catalogs.TrAreaAccionRepository;
import com.gem.sistema.business.repository.catalogs.TrPresupuestoAreaRepository;
import com.gem.sistema.business.repository.catalogs.TrPresupuestoDependenciaRepository;
import com.gem.sistema.business.repository.catalogs.TwAccionContratoRepository;
import com.gem.sistema.business.repository.catalogs.TwContratoFacturaRepository;
import com.gem.sistema.business.repository.catalogs.TwObraPagoRepository;
import com.gem.sistema.business.repository.catalogs.TwObraRepository;

@Service(value = "controlPresupuestarioService")
public class ControlPresupuestarioServiceImpl implements ControlPresupuestarioService {

	@Autowired
	private TcControlPresupuestarioRepository controlPresupuestarioRepository;

	@Autowired
	private TrPresupuestoDependenciaRepository presupuestoDependenciaRepository;

	@Autowired
	private TrPresupuestoAreaRepository presupuestoAreaRepository;

	@Autowired
	private TwContratoFacturaRepository contratoFacturaRepository;

	@Autowired
	private TwAccionContratoRepository accionContratoRepository;

	@Autowired
	private CattpoRepository cattpoRepository;

	@Autowired
	private ConctbRepository conctbRepository;

	@Autowired
	private PolienRepository polienRepository;

	@Autowired
	private TcPeriodoRepositoy periodoRepositoy;

	@Autowired
	private TwObraPagoRepository obraPagoRepository;

	@Autowired
	private TcContratoPadRepository contratoPadRepository;

	@Autowired
	private TcContratosRepository contratosRepository;

	@Autowired
	private TcStatusPagoRepository statusPagoRepository;

	@Autowired
	private TrAreaAccionRepository areaAccionRepository;

	@Autowired
	private TcAccionRepository accionRepository;

	@Autowired
	private CatdepRepository catdepRepository;

	@Autowired
	private TcAreaRepository areaRepository;

	@Autowired
	private TwObraRepository obraRepository;

	@Override
	public List<TcControlPresupuestario> getControlPresupuestario() {
		return (List<TcControlPresupuestario>) controlPresupuestarioRepository.findAll();
	}

	@Override
	public List<TrPresupuestoDependencia> getDependenciasWithPresupuesto() {
		return presupuestoDependenciaRepository.findAll();
	}

	@Override
	public List<TrPresupuestoArea> getAreasWithPresupuesto() {
		return (List<TrPresupuestoArea>) presupuestoAreaRepository.findAll();
	}

	@Override
	public TcControlPresupuestario getControlPresupuestario(String claveControl) {
		return controlPresupuestarioRepository.findByClave(claveControl);
	}

	@Override
	public TwAccionContrato saveAccionConytrato(TwAccionContrato accionContrato) {
		return accionContratoRepository.save(accionContrato);
	}

	@Override
	public TcControlPresupuestario saveControlPresupuestario(TcControlPresupuestario presupuestario) {
		return controlPresupuestarioRepository.save(presupuestario);
	}

	@Override
	public TwContratoFactura saveContratoFactura(TwContratoFactura contratoFactura) {
		return contratoFacturaRepository.save(contratoFactura);
	}

	@Override
	public List<TrPresupuestoArea> savePresupuestoAreas(List<TrPresupuestoArea> presupuestoAreas) {
		return (List<TrPresupuestoArea>) presupuestoAreaRepository.save(presupuestoAreas);
	}

	@Override
	public List<TrPresupuestoDependencia> savePresupuestoDependencias(
			List<TrPresupuestoDependencia> presupuestoDependencias) {

		return presupuestoDependenciaRepository.save(presupuestoDependencias);
	}

	@Override
	public List<TcPeriodo> getActivePeriodos(Integer sector) {

		Conctb conctb = conctbRepository.findByIdsector(sector);
		List<TcPeriodo> meses = periodoRepositoy.findByTipoPeriodoAndPeriodoLessThanEqualOrderByPeriodo(1,
				conctb.getMesemp());

		return meses;
	}

	@Override
	public List<Cattpo> getAllTiposPoliza() {
		return cattpoRepository.findAll();
	}

	@Override
	public TcContrato getContratos(String numeroContrato) {
		return contratosRepository.findByNumeroContrato(numeroContrato);
	}

	@Override
	public List<TcStatusPago> getStatusPagos() {
		return statusPagoRepository.findAll();
	}

	@Override
	public Polien getPolien(Integer mespol, Integer anopol, Integer conpol, String tippol, Integer sector) {
		return polienRepository.findFirstByMespolAndAnopolAndConpolAndTippolAndIdsectorOrderById(mespol, anopol, conpol,
				tippol, sector);
	}

	@Override
	public TrPresupuestoArea savePresupuestoArea(TrPresupuestoArea presupuestoArea) {
		return presupuestoAreaRepository.save(presupuestoArea);
	}

	@Override
	public TrPresupuestoDependencia savePresupuestoDependencia(TrPresupuestoDependencia presupuestoDependencia) {
		return presupuestoDependenciaRepository.save(presupuestoDependencia);
	}

	@Override
	public List<Catdep> getAllDependencias() {
		return catdepRepository.findAll();
	}

	@Override
	public TrAreaAccion saveAreaAccion(TrAreaAccion areaAccion) {
		return areaAccionRepository.save(areaAccion);
	}

	@Override
	public List<TcAccion> getAccionsByDireccion(TcArea area) {
		return accionRepository.findByDireccionOrderByClave(area);
	}

	@Override
	public List<TcAccion> getAllAccions() {
		return (List<TcAccion>) accionRepository.findAll();
	}

	@Override
	public List<TcArea> getAllAreas() {
		return areaRepository.findAllByOrderByClave();
	}

	@Override
	public TwObra saveObra(TwObra obra) {

		return obraRepository.save(obra);
	}

	@Override
	public TwObraPago savePagoObra(TwObraPago pago) {
		return obraPagoRepository.save(pago);
	}

	@Override
	public TrAreaAccion getAreaAccion(TrPresupuestoArea presupuestoArea, TcAccion accion) {
		return areaAccionRepository.findByPresupuestoAreaAndAccion(presupuestoArea, accion);
	}

	@Override
	public List<TcContratoPad> getAllContratosPad() {
		return contratoPadRepository.findAllByOrderByNumeroContrato() ;
	}

	@Override
	public TcContratoPad saveContratoPad(TcContratoPad contratoPad) {
		return contratoPadRepository.save(contratoPad);
	}

	@Override
	public TcContratoPad getContratoPad(String numeroContrato) {
		return contratoPadRepository.findByNumeroContrato(numeroContrato);
	}

	public TcControlPresupuestarioRepository getControlPresupuestarioRepository() {
		return controlPresupuestarioRepository;
	}

	public void setControlPresupuestarioRepository(TcControlPresupuestarioRepository controlPresupuestarioRepository) {
		this.controlPresupuestarioRepository = controlPresupuestarioRepository;
	}

	public TrPresupuestoAreaRepository getPresupuestoAreaRepository() {
		return presupuestoAreaRepository;
	}

	public void setPresupuestoAreaRepository(TrPresupuestoAreaRepository presupuestoAreaRepository) {
		this.presupuestoAreaRepository = presupuestoAreaRepository;
	}

	public TwContratoFacturaRepository getContratoFacturaRepository() {
		return contratoFacturaRepository;
	}

	public void setContratoFacturaRepository(TwContratoFacturaRepository contratoFacturaRepository) {
		this.contratoFacturaRepository = contratoFacturaRepository;
	}

	public CattpoRepository getCattpoRepository() {
		return cattpoRepository;
	}

	public void setCattpoRepository(CattpoRepository cattpoRepository) {
		this.cattpoRepository = cattpoRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public TwAccionContratoRepository getAccionContratoRepository() {
		return accionContratoRepository;
	}

	public void setAccionContratoRepository(TwAccionContratoRepository accionContratoRepository) {
		this.accionContratoRepository = accionContratoRepository;
	}

	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	public TcContratosRepository getContratosRepository() {
		return contratosRepository;
	}

	public void setContratosRepository(TcContratosRepository contratosRepository) {
		this.contratosRepository = contratosRepository;
	}

	public TcStatusPagoRepository getStatusPagoRepository() {
		return statusPagoRepository;
	}

	public void setStatusPagoRepository(TcStatusPagoRepository statusPagoRepository) {
		this.statusPagoRepository = statusPagoRepository;
	}

	public TrPresupuestoDependenciaRepository getPresupuestoDependenciaRepository() {
		return presupuestoDependenciaRepository;
	}

	public void setPresupuestoDependenciaRepository(
			TrPresupuestoDependenciaRepository presupuestoDependenciaRepository) {
		this.presupuestoDependenciaRepository = presupuestoDependenciaRepository;
	}

	public TcAccionRepository getAccionRepository() {
		return accionRepository;
	}

	public void setAccionRepository(TcAccionRepository accionRepository) {
		this.accionRepository = accionRepository;
	}

	public TcAreaRepository getAreaRepository() {
		return areaRepository;
	}

	public void setAreaRepository(TcAreaRepository areaRepository) {
		this.areaRepository = areaRepository;
	}

	public TrAreaAccionRepository getAreaAccionRepository() {
		return areaAccionRepository;
	}

	public void setAreaAccionRepository(TrAreaAccionRepository areaAccionRepository) {
		this.areaAccionRepository = areaAccionRepository;
	}

	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	public TwObraRepository getObraRepository() {
		return obraRepository;
	}

	public void setObraRepository(TwObraRepository obraRepository) {
		this.obraRepository = obraRepository;
	}

	public TwObraPagoRepository getObraPagoRepository() {
		return obraPagoRepository;
	}

	public void setObraPagoRepository(TwObraPagoRepository obraPagoRepository) {
		this.obraPagoRepository = obraPagoRepository;
	}

	public TcContratoPadRepository getContratoPadRepository() {
		return contratoPadRepository;
	}

	public void setContratoPadRepository(TcContratoPadRepository contratoPadRepository) {
		this.contratoPadRepository = contratoPadRepository;
	}

}
