package com.gem.sistema.business.service.indicadoresdiseno;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Ftecnicadd;
import com.gem.sistema.business.domain.Ftecnicadm;
import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.Variables;
import com.gem.sistema.business.dto.FtecnicaddDTO;
import com.gem.sistema.business.dto.FtecnicadmDTO;
import com.gem.sistema.business.predicates.CpdPredicate;
import com.gem.sistema.business.predicates.FtecnicaDmPredicate;
import com.gem.sistema.business.repository.catalogos.VariablesRepository;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CpdRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicaDdRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicaDmRepository;
import com.gem.sistema.business.repository.catalogs.MirRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadoresDisenoServiceImpl.
 */
@Service(value = "indicadoresDisenoService")
public class IndicadoresDisenoServiceImpl implements IndicadoresDisenoService {

	/** The Constant ORDER_BY_ID. */
	private static final Sort ORDER_BY_ID = new Sort(Direction.ASC, "id");

	private static final Sort ORDER_BY_CLVDEP = new Sort(Direction.ASC, "clvdep");

	/** The cpd repository. */
	@Autowired
	CpdRepository cpdRepository;

	/** The xcatpro repository. */
	@Autowired
	XcatproRepository xcatproRepository;

	/** The mir repository. */
	@Autowired
	MirRepository mirRepository;

	/** The ftecnica dd repository. */
	@Autowired
	FtecnicaDdRepository ftecnicaDdRepository;

	/** The ftecnica dm repository. */
	@Autowired
	FtecnicaDmRepository ftecnicaDmRepository;

	/** The catdep repository. */
	@Autowired
	CatdepRepository catdepRepository;

	/** The variables repository. */
	@Autowired
	VariablesRepository variablesRepository;

	/**
	 * metodo que llena la lista de diseño de indicadores.
	 *
	 * @return the list
	 */
	@Override
	public List<FtecnicadmDTO> llenaListaEncabezado(Integer idSector) {
		return this.entities2DisenoDTO((List<Ftecnicadm>) ftecnicaDmRepository.findAllByIdSector(idSector));
	}

	/**
	 * metodo borra un encabezado.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean deleteDiseno(long id) {
		boolean ans = false;
		try {
			ftecnicaDmRepository.delete(id);
			ans = true;
		} catch (Exception e) {
			ans = false;
		}
		return ans;
	}

	/**
	 * metodo que guarda un encabezado.
	 *
	 * @param ftecnicadmDTO the ftecnicadm DTO
	 */
	public boolean salvarDiseno(FtecnicadmDTO ftecnicadmDTO) {

		Ftecnicadm aux = this.disenoDTO2Entity(ftecnicadmDTO);
		Ftecnicadm ftecnicadm = ftecnicaDmRepository.findOne(FtecnicaDmPredicate.validaFichaTecnicaDiseno(aux));
		if (null == ftecnicadm) {
			ftecnicaDmRepository.save(aux);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService#
	 * salvarDisenoModificado(com.gem.sistema.business.dto.FtecnicadmDTO)
	 */
	public void salvarDisenoModificado(FtecnicadmDTO ftecnicadmDTO) {
		ftecnicaDmRepository.save(this.disenoDTO2EntityModificado(ftecnicadmDTO));
	}

	/**
	 * metodo que guarda un detalle.
	 *
	 * @param ftecnicaddDTO the ftecnicadd DTO
	 */
	public void salvarDetalle(FtecnicaddDTO ftecnicaddDTO) {
		ftecnicaDdRepository.save(this.detalleDTO2Entity(ftecnicaddDTO));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService#
	 * salvarDetalleModificado(com.gem.sistema.business.dto.FtecnicaddDTO)
	 */
	public void salvarDetalleModificado(FtecnicaddDTO ftecnicaddDTO) {
		ftecnicaDdRepository.save(this.detalleDTO2EntityModificado(ftecnicaddDTO));
	}

	/**
	 * metodo borra un detalle.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean deleteDetalle(long id) {
		boolean ans = false;
		try {
			ftecnicaDdRepository.delete(id);
			ans = true;
		} catch (Exception e) {
			ans = false;
		}
		return ans;
	}

	/**
	 * metodo que convierte un dto diseno al entity Ftecnicadm.
	 *
	 * @param ftecnicadmDTO the ftecnicadm DTO
	 * @return the ftecnicadm
	 */
	public Ftecnicadm disenoDTO2Entity(FtecnicadmDTO ftecnicadmDTO) {
		Ftecnicadm ftecnicadm = new Ftecnicadm();
//		ftecnicadm.setId(ftecnicadmDTO.getId());
		ftecnicadm.setClvdep(ftecnicadmDTO.getClvdep() != null ? ftecnicadmDTO.getClvdep() : "");
		ftecnicadm.setClvfin(ftecnicadmDTO.getClvfin() != null ? ftecnicadmDTO.getClvfin() : "");
		ftecnicadm.setClvfun(ftecnicadmDTO.getClvfun() != null ? ftecnicadmDTO.getClvfun() : "");
		ftecnicadm.setCveind(ftecnicadmDTO.getCveind() != null ? ftecnicadmDTO.getCveind() : "");
		ftecnicadm.setCvetemas(ftecnicadmDTO.getCvetemas() != null ? ftecnicadmDTO.getCvetemas() : "");
		ftecnicadm.setDescanual(ftecnicadmDTO.getDescanual() != null ? ftecnicadmDTO.getDescanual() : "");
		ftecnicadm.setDescfac(ftecnicadmDTO.getDescfac() != null ? ftecnicadmDTO.getDescfac() : "");
		ftecnicadm.setDimension(ftecnicadmDTO.getDimension() != null ? ftecnicadmDTO.getDimension() : "");
		ftecnicadm.setElaboro(ftecnicadmDTO.getElaboro() != null ? ftecnicadmDTO.getElaboro() : "");
		ftecnicadm.setFactor(ftecnicadmDTO.getFactor());
		ftecnicadm.setFeccap(ftecnicadmDTO.getFeccap());
		ftecnicadm.setFormula(ftecnicadmDTO.getFormula() != null ? ftecnicadmDTO.getFormula() : "");
		ftecnicadm.setFrecuencia(ftecnicadmDTO.getFrecuencia() != null ? ftecnicadmDTO.getFrecuencia() : "");
		ftecnicadm.setIdRef(ftecnicadmDTO.getIdRef());
		ftecnicadm.setIdSector(ftecnicadmDTO.getIdsector());
		ftecnicadm
				.setInterpretacion(ftecnicadmDTO.getInterpretacion() != null ? ftecnicadmDTO.getInterpretacion() : "");
		ftecnicadm.setMedios(ftecnicadmDTO.getMedios() != null ? ftecnicadmDTO.getMedios() : "");
		ftecnicadm.setMetanuale(ftecnicadmDTO.getMetanuale());
		ftecnicadm.setMetasact(ftecnicadmDTO.getMetasact() != null ? ftecnicadmDTO.getMetasact() : "");
		ftecnicadm.setNomind(ftecnicadmDTO.getNomind() != null ? ftecnicadmDTO.getNomind() : "");
		ftecnicadm.setNope(ftecnicadmDTO.getNope() != null ? ftecnicadmDTO.getNope() : "");
		ftecnicadm.setObjetivo(ftecnicadmDTO.getObjetivo() != null ? ftecnicadmDTO.getObjetivo() : "");
		ftecnicadm.setPe(ftecnicadmDTO.getPe() != null ? ftecnicadmDTO.getPe() : "");
		ftecnicadm.setTema(ftecnicadmDTO.getTema() != null ? ftecnicadmDTO.getTema() : "");
		ftecnicadm.setTipo(ftecnicadmDTO.getTipo() != null ? ftecnicadmDTO.getTipo() : "");
		ftecnicadm.setTrim1e(ftecnicadmDTO.getTrim1e());
		ftecnicadm.setTrim2e(ftecnicadmDTO.getTrim2e());
		ftecnicadm.setTrim3e(ftecnicadmDTO.getTrim3e());
		ftecnicadm.setTrim4e(ftecnicadmDTO.getTrim4e());
		ftecnicadm.setUserid(ftecnicadmDTO.getUserid());
		ftecnicadm.setUsuario(ftecnicadmDTO.getUsuario() != null ? ftecnicadmDTO.getUsuario() : "");
		ftecnicadm.setValido(ftecnicadmDTO.getValido() != null ? ftecnicadmDTO.getValido() : "");
		ftecnicadm.setLineaBase(
				StringUtils.isEmpty(ftecnicadmDTO.getLineaBase()) ? StringUtils.EMPTY : ftecnicadmDTO.getLineaBase());

		return ftecnicadm;
	}

	/**
	 * metodo que convierte un dto diseno al entity Ftecnicadm.
	 *
	 * @param ftecnicadmDTO the ftecnicadm DTO
	 * @return the ftecnicadm
	 */
	public Ftecnicadm disenoDTO2EntityModificado(FtecnicadmDTO ftecnicadmDTO) {
		Ftecnicadm ftecnicadm = new Ftecnicadm();
		ftecnicadm.setId(ftecnicadmDTO.getId());
		ftecnicadm.setClvdep(ftecnicadmDTO.getClvdep() != null ? ftecnicadmDTO.getClvdep() : "");
		ftecnicadm.setClvfin(ftecnicadmDTO.getClvfin() != null ? ftecnicadmDTO.getClvfin() : "");
		ftecnicadm.setClvfun(ftecnicadmDTO.getClvfun() != null ? ftecnicadmDTO.getClvfun() : "");
		ftecnicadm.setCveind(ftecnicadmDTO.getCveind() != null ? ftecnicadmDTO.getCveind() : "");
		ftecnicadm.setCvetemas(ftecnicadmDTO.getCvetemas() != null ? ftecnicadmDTO.getCvetemas() : "");
		ftecnicadm.setDescanual(ftecnicadmDTO.getDescanual() != null ? ftecnicadmDTO.getDescanual() : "");
		ftecnicadm.setDescfac(ftecnicadmDTO.getDescfac() != null ? ftecnicadmDTO.getDescfac() : "");
		ftecnicadm.setDimension(ftecnicadmDTO.getDimension() != null ? ftecnicadmDTO.getDimension() : "");
		ftecnicadm.setElaboro(ftecnicadmDTO.getElaboro() != null ? ftecnicadmDTO.getElaboro() : "");
		ftecnicadm.setFactor(ftecnicadmDTO.getFactor());
		ftecnicadm.setFeccap(ftecnicadmDTO.getFeccap());
		ftecnicadm.setFormula(ftecnicadmDTO.getFormula() != null ? ftecnicadmDTO.getFormula() : "");
		ftecnicadm.setFrecuencia(ftecnicadmDTO.getFrecuencia() != null ? ftecnicadmDTO.getFrecuencia() : "");
		ftecnicadm.setIdRef(ftecnicadmDTO.getIdRef());
		ftecnicadm.setIdSector(ftecnicadmDTO.getIdsector());
		ftecnicadm
				.setInterpretacion(ftecnicadmDTO.getInterpretacion() != null ? ftecnicadmDTO.getInterpretacion() : "");
		ftecnicadm.setMedios(ftecnicadmDTO.getMedios() != null ? ftecnicadmDTO.getMedios() : "");
		ftecnicadm.setMetanuale(ftecnicadmDTO.getMetanuale());
		ftecnicadm.setMetasact(ftecnicadmDTO.getMetasact() != null ? ftecnicadmDTO.getMetasact() : "");
		ftecnicadm.setNomind(ftecnicadmDTO.getNomind() != null ? ftecnicadmDTO.getNomind() : "");
		ftecnicadm.setNope(ftecnicadmDTO.getNope() != null ? ftecnicadmDTO.getNope() : "");
		ftecnicadm.setObjetivo(ftecnicadmDTO.getObjetivo() != null ? ftecnicadmDTO.getObjetivo() : "");
		ftecnicadm.setPe(ftecnicadmDTO.getPe() != null ? ftecnicadmDTO.getPe() : "");
		ftecnicadm.setTema(ftecnicadmDTO.getTema() != null ? ftecnicadmDTO.getTema() : "");
		ftecnicadm.setTipo(ftecnicadmDTO.getTipo() != null ? ftecnicadmDTO.getTipo() : "");
		ftecnicadm.setTrim1e(ftecnicadmDTO.getTrim1e());
		ftecnicadm.setTrim2e(ftecnicadmDTO.getTrim2e());
		ftecnicadm.setTrim3e(ftecnicadmDTO.getTrim3e());
		ftecnicadm.setTrim4e(ftecnicadmDTO.getTrim4e());
		ftecnicadm.setUserid(ftecnicadmDTO.getUserid());
		ftecnicadm.setUsuario(ftecnicadmDTO.getUsuario() != null ? ftecnicadmDTO.getUsuario() : "");
		ftecnicadm.setValido(ftecnicadmDTO.getValido() != null ? ftecnicadmDTO.getValido() : "");
		ftecnicadm.setLineaBase(
				StringUtils.isEmpty(ftecnicadmDTO.getLineaBase()) ? StringUtils.EMPTY : ftecnicadmDTO.getLineaBase());

		return ftecnicadm;
	}

	/**
	 * Entities 2 diseno DTO.
	 *
	 * @param listaEncabezadoEntity the lista encabezado entity
	 * @return the list
	 */
	public List<FtecnicadmDTO> entities2DisenoDTO(List<Ftecnicadm> listaEncabezadoEntity) {
		List<FtecnicadmDTO> listaEncabezadoDTO = new ArrayList<FtecnicadmDTO>();
		for (Ftecnicadm ftecnicadm : listaEncabezadoEntity) {
			FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
			ftecnicadmDTO.setId(ftecnicadm.getId());
			ftecnicadmDTO.setClvdep(ftecnicadm.getClvdep());
			ftecnicadmDTO.setClvfin(ftecnicadm.getClvfin());
			ftecnicadmDTO.setClvfun(ftecnicadm.getClvfun());
			ftecnicadmDTO.setCveind(ftecnicadm.getCveind());
			ftecnicadmDTO.setCvetemas(ftecnicadm.getCvetemas());
			ftecnicadmDTO.setDescanual(ftecnicadm.getDescanual());
			ftecnicadmDTO.setDescfac(ftecnicadm.getDescfac());
			ftecnicadmDTO.setDimension(ftecnicadm.getDimension());
			ftecnicadmDTO.setElaboro(ftecnicadm.getElaboro());
			ftecnicadmDTO.setFactor(ftecnicadm.getFactor());
			ftecnicadmDTO.setFeccap(ftecnicadm.getFeccap());
			ftecnicadmDTO.setFormula(ftecnicadm.getFormula());
			ftecnicadmDTO.setFrecuencia(ftecnicadm.getFrecuencia());
			ftecnicadmDTO.setIdRef(ftecnicadm.getIdRef());
			ftecnicadmDTO.setIdsector(ftecnicadm.getIdSector());
			ftecnicadmDTO.setInterpretacion(ftecnicadm.getInterpretacion());
			ftecnicadmDTO.setMedios(ftecnicadm.getMedios());
			ftecnicadmDTO.setMetanuale(ftecnicadm.getMetanuale());
			ftecnicadmDTO.setMetasact(ftecnicadm.getMetasact());
			ftecnicadmDTO.setNomind(ftecnicadm.getNomind());
			ftecnicadmDTO.setNope(ftecnicadm.getNope());
			ftecnicadmDTO.setObjetivo(ftecnicadm.getObjetivo());
			ftecnicadmDTO.setPe(ftecnicadm.getPe());
			ftecnicadmDTO.setTema(ftecnicadm.getTema());
			ftecnicadmDTO.setTipo(ftecnicadm.getTipo());
			ftecnicadmDTO.setTrim1e(ftecnicadm.getTrim1e());
			ftecnicadmDTO.setTrim2e(ftecnicadm.getTrim2e());
			ftecnicadmDTO.setTrim3e(ftecnicadm.getTrim3e());
			ftecnicadmDTO.setTrim4e(ftecnicadm.getTrim4e());
			ftecnicadmDTO.setUserid(ftecnicadm.getUserid());
			ftecnicadmDTO.setUsuario(ftecnicadm.getUsuario());
			ftecnicadmDTO.setValido(ftecnicadm.getValido());
			ftecnicadmDTO.setLineaBase(ftecnicadm.getLineaBase());
			listaEncabezadoDTO.add(ftecnicadmDTO);
		}
		return listaEncabezadoDTO;
	}

	/**
	 * metodo que convierte un dto detalle al entity Ftecnicadd.
	 *
	 * @param ftecnicaddDTO the ftecnicadd DTO
	 * @return the ftecnicadd
	 */
	public Ftecnicadd detalleDTO2Entity(FtecnicaddDTO ftecnicaddDTO) {
		Ftecnicadd ftecnicadd = new Ftecnicadd();
//		ftecnicadd.setId(ftecnicaddDTO.getId());
		ftecnicadd.setClvdep(ftecnicaddDTO.getClvdep());
		ftecnicadd.setClvfin(ftecnicaddDTO.getClvfin());
		ftecnicadd.setClvfun(ftecnicaddDTO.getClvfun());
		ftecnicadd.setCodigo(ftecnicaddDTO.getCodigo());
		ftecnicadd.setCveind(ftecnicaddDTO.getCveind());
		ftecnicadd.setCvetemas(ftecnicaddDTO.getCvetemas());
		ftecnicadd.setNumvar(ftecnicaddDTO.getNumvar());
		ftecnicadd.setCvevar(ftecnicaddDTO.getCvevar());
		ftecnicadd.setFeccap(ftecnicaddDTO.getFeccap());
		ftecnicadd.setIdRef(ftecnicaddDTO.getIdRef());
		ftecnicadd.setIdsector(ftecnicaddDTO.getIdsector());
		ftecnicadd.setMetanual(ftecnicaddDTO.getMetanual());
		ftecnicadd.setNope(ftecnicaddDTO.getNope());
		ftecnicadd.setPe(ftecnicaddDTO.getPe());
		ftecnicadd.setTema(ftecnicaddDTO.getTema());
		ftecnicadd.setTipoper(ftecnicaddDTO.getTipoper());
		ftecnicadd.setTrim1(ftecnicaddDTO.getTrim1());
		ftecnicadd.setTrim2(ftecnicaddDTO.getTrim2());
		ftecnicadd.setTrim3(ftecnicaddDTO.getTrim3());
		ftecnicadd.setTrim4(ftecnicaddDTO.getTrim4());
		ftecnicadd.setUnimed(ftecnicaddDTO.getUnimed());
		ftecnicadd.setUserid(ftecnicaddDTO.getUserid());
		ftecnicadd.setUsuario(ftecnicaddDTO.getUsuario());
		ftecnicadd.setVariables(ftecnicaddDTO.getVariables());
		return ftecnicadd;
	}

	/**
	 * Detalle DTO 2 entity modificado.
	 *
	 * @param ftecnicaddDTO the ftecnicadd DTO
	 * @return the ftecnicadd
	 */
	public Ftecnicadd detalleDTO2EntityModificado(FtecnicaddDTO ftecnicaddDTO) {
		Ftecnicadd ftecnicadd = new Ftecnicadd();
		ftecnicadd.setId(ftecnicaddDTO.getId());
		ftecnicadd.setClvdep(ftecnicaddDTO.getClvdep());
		ftecnicadd.setClvfin(ftecnicaddDTO.getClvfin());
		ftecnicadd.setClvfun(ftecnicaddDTO.getClvfun());
		ftecnicadd.setCodigo(ftecnicaddDTO.getCodigo());
		ftecnicadd.setCveind(ftecnicaddDTO.getCveind());
		ftecnicadd.setCvetemas(ftecnicaddDTO.getCvetemas());
		ftecnicadd.setNumvar(ftecnicaddDTO.getNumvar());
		ftecnicadd.setCvevar(ftecnicaddDTO.getCvevar());
		ftecnicadd.setFeccap(ftecnicaddDTO.getFeccap());
		ftecnicadd.setIdRef(ftecnicaddDTO.getIdRef());
		ftecnicadd.setIdsector(ftecnicaddDTO.getIdsector());
		ftecnicadd.setMetanual(ftecnicaddDTO.getMetanual());
		ftecnicadd.setNope(ftecnicaddDTO.getNope());
		ftecnicadd.setPe(ftecnicaddDTO.getPe());
		ftecnicadd.setTema(ftecnicaddDTO.getTema());
		ftecnicadd.setTipoper(ftecnicaddDTO.getTipoper());
		ftecnicadd.setTrim1(ftecnicaddDTO.getTrim1());
		ftecnicadd.setTrim2(ftecnicaddDTO.getTrim2());
		ftecnicadd.setTrim3(ftecnicaddDTO.getTrim3());
		ftecnicadd.setTrim4(ftecnicaddDTO.getTrim4());
		ftecnicadd.setUnimed(ftecnicaddDTO.getUnimed());
		ftecnicadd.setUserid(ftecnicaddDTO.getUserid());
		ftecnicadd.setUsuario(ftecnicaddDTO.getUsuario());
		ftecnicadd.setVariables(ftecnicaddDTO.getVariables());
		return ftecnicadd;
	}

	/**
	 * Entities detalle 2 DTO.
	 *
	 * @param listadd the listadd
	 * @return the list
	 */
	public List<FtecnicaddDTO> entitiesDetalle2DTO(List<Ftecnicadd> listadd) {
		List<FtecnicaddDTO> listaDetalleDTO = new ArrayList<FtecnicaddDTO>();
		for (Ftecnicadd ftecnicadd : listadd) {
			FtecnicaddDTO dto = new FtecnicaddDTO();
			dto.setId(ftecnicadd.getId());
			dto.setClvdep(ftecnicadd.getClvdep());
			dto.setClvfin(ftecnicadd.getClvfin());
			dto.setClvfun(ftecnicadd.getClvfun());
			dto.setCodigo(ftecnicadd.getCodigo());
			dto.setNumvar(ftecnicadd.getNumvar());
			dto.setCveind(ftecnicadd.getCveind());
			dto.setCvetemas(ftecnicadd.getCvetemas());
			dto.setCvevar(ftecnicadd.getCvevar());
			dto.setFeccap(ftecnicadd.getFeccap());
			dto.setIdRef(ftecnicadd.getIdRef());
			dto.setIdsector(ftecnicadd.getIdsector());
			dto.setMetanual(ftecnicadd.getMetanual());
			dto.setNope(ftecnicadd.getNope());
			dto.setPe(ftecnicadd.getPe());
			dto.setTema(ftecnicadd.getTema());
			dto.setTipoper(ftecnicadd.getTipoper());
			dto.setTrim1(ftecnicadd.getTrim1());
			dto.setTrim2(ftecnicadd.getTrim2());
			dto.setTrim3(ftecnicadd.getTrim3());
			dto.setTrim4(ftecnicadd.getTrim4());
			dto.setUnimed(ftecnicadd.getUnimed());
			dto.setUserid(ftecnicadd.getUserid());
			dto.setUsuario(ftecnicadd.getUsuario());
			dto.setVariables(ftecnicadd.getVariables());
			listaDetalleDTO.add(dto);
		}
		return listaDetalleDTO;
	}

	/**
	 * metodo que llena la lista de diseño de metas de un indicador de diseño
	 * encabezado.
	 *
	 * @param ftecnicadmDTO the ftecnicadm DTO
	 * @return the list
	 */
	@Override
	public List<FtecnicaddDTO> llenaListaDetalle(FtecnicadmDTO ftecnicadmDTO) {
		List<FtecnicaddDTO> listaMetas = new ArrayList<FtecnicaddDTO>();
		listaMetas = this
				.entitiesDetalle2DTO(ftecnicaDdRepository.findByCvetemasAndClvdepAndClvfunAndClvfinAndCveindAndIdsector(
						ftecnicadmDTO.getCvetemas(), ftecnicadmDTO.getClvdep(), ftecnicadmDTO.getClvfun(),
						ftecnicadmDTO.getClvfin(), ftecnicadmDTO.getCveind(), ftecnicadmDTO.getIdsector()));
		return listaMetas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService#
	 * llenaComboTemas()
	 */
	@Override
	/**
	 * llena combo de temas
	 */
	public List<Cpd> llenaComboTemas() {
		return cpdRepository.findAll();
	}

	/**
	 * llena combo de temas.
	 *
	 * @return the list
	 */
	@Override
	public List<Variables> llenaComboVariables() {
		return variablesRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService#
	 * llenaComboDependencias(java.lang.Integer)
	 */
	@Override
	/**
	 * llena combo de dependencias
	 */
	public List<String> llenaComboDependencias(Integer sectorId) {
//		return catdepRepository.findAllByOrderByClvdep();
		return xcatproRepository.findDistinctClvdepBySectoridOrderByClvdep(sectorId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService#
	 * llenaComboProgramas(java.lang.Integer)
	 */
	@Override
	/**
	 * llena combo de programas
	 */
	public List<String> llenaComboProgramas(Integer idSector) {
		return xcatproRepository.llenaListaProgramasComplete(idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService#
	 * llenaComboCodigosIndicador()
	 */
	@Override
	/**
	 * llena combo de codigos de indicador
	 */
	public List<Mir> llenaComboCodigosIndicador() {
		return mirRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService#
	 * tieneDetalle(com.gem.sistema.business.dto.FtecnicadmDTO)
	 */
	@Override
	public boolean tieneDetalle(FtecnicadmDTO ftecnicadmDTO) {
		List<Ftecnicadd> listaDetalle = ftecnicaDdRepository
				.findByCvetemasAndClvdepAndClvfunAndClvfinAndCveindAndIdsector(ftecnicadmDTO.getCvetemas(),
						ftecnicadmDTO.getClvdep(), ftecnicadmDTO.getClvfun(), ftecnicadmDTO.getClvfin(),
						ftecnicadmDTO.getCveind(), ftecnicadmDTO.getIdsector());
		if (!listaDetalle.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	// ----------------GETTERS AND SETTERS----------------
	/**
	 * Gets the cpd repository.
	 *
	 * @return the cpdRepository
	 */
	public CpdRepository getCpdRepository() {
		return cpdRepository;
	}

	/**
	 * Sets the cpd repository.
	 *
	 * @param cpdRepository the cpdRepository to set
	 */
	public void setCpdRepository(CpdRepository cpdRepository) {
		this.cpdRepository = cpdRepository;
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatproRepository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the xcatproRepository to set
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	/**
	 * Gets the mir repository.
	 *
	 * @return the mirRepository
	 */
	public MirRepository getMirRepository() {
		return mirRepository;
	}

	/**
	 * Sets the mir repository.
	 *
	 * @param mirRepository the mirRepository to set
	 */
	public void setMirRepository(MirRepository mirRepository) {
		this.mirRepository = mirRepository;
	}

	/**
	 * Gets the ftecnica dd repository.
	 *
	 * @return the ftecnicaDdRepository
	 */
	public FtecnicaDdRepository getFtecnicaDdRepository() {
		return ftecnicaDdRepository;
	}

	/**
	 * Sets the ftecnica dd repository.
	 *
	 * @param ftecnicaDdRepository the ftecnicaDdRepository to set
	 */
	public void setFtecnicaDdRepository(FtecnicaDdRepository ftecnicaDdRepository) {
		this.ftecnicaDdRepository = ftecnicaDdRepository;
	}

	/**
	 * Gets the ftecnica dm repository.
	 *
	 * @return the ftecnicaDmRepository
	 */
	public FtecnicaDmRepository getFtecnicaDmRepository() {
		return ftecnicaDmRepository;
	}

	/**
	 * Sets the ftecnica dm repository.
	 *
	 * @param ftecnicaDmRepository the ftecnicaDmRepository to set
	 */
	public void setFtecnicaDmRepository(FtecnicaDmRepository ftecnicaDmRepository) {
		this.ftecnicaDmRepository = ftecnicaDmRepository;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdepRepository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the catdepRepository to set
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Gets the variables repository.
	 *
	 * @return the variablesRepository
	 */
	public VariablesRepository getVariablesRepository() {
		return variablesRepository;
	}

	/**
	 * Sets the variables repository.
	 *
	 * @param variablesRepository the variablesRepository to set
	 */
	public void setVariablesRepository(VariablesRepository variablesRepository) {
		this.variablesRepository = variablesRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService#
	 * llenaComboProgramasFiltradoXDep(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<String> llenaComboProgramasFiltradoXDep(String clvDep, Integer sectorId) {
		return xcatproRepository.llenaListaProgramasDistinct(clvDep, sectorId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService#
	 * buscaDescDependencia(java.lang.String, java.lang.Integer)
	 */
	public List<Catdep> buscaDescDependencia(String clvDep, Integer sectorId) {
		return catdepRepository.findByClvdepAndIdsector(clvDep, sectorId);
	}

	@Override
	public List<Cpd> llenaComboVariablesLength() {

		return (List<Cpd>) this.cpdRepository.findAll(CpdPredicate.findByCveTemasLength());
	}
}