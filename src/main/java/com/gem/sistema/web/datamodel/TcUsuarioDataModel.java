/**
 * 
 */
package com.gem.sistema.web.datamodel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.business.predicates.TcUsuarioPredicates.findByIdSector;
import static com.gem.sistema.business.predicates.TcUsuarioPredicates.findByUsuario;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.util.List;
import java.util.Map;


import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Catmun;
import com.gem.sistema.business.domain.TcEntePubCentral;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TcUsuario;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CatmunRepository;
import com.gem.sistema.business.repository.catalogs.TcEntePubCentralRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class TcUsuarioDataModel.
 *
 * @author gauss s
 */
@Component("tcUsuarioDataModel")
@Scope("view")
public class TcUsuarioDataModel extends CustomPagingModel<TcUsuario> {

	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;
	
	/** Componente de servicio. */
	@Value("#{tcUsuarioRepository}")
	private TcUsuarioRepository tcUsuarioRepository;

	/** The tc entidad administrativa repository. */
	@Value("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository tcEntidadAdministrativaRepository;

	/** The tc ente pub central repository. */
	@Value("#{tcEntePubCentralRepository}")
	private TcEntePubCentralRepository tcEntePubCentralRepository;

	/** The catdep repository. */
	@Value("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The catmun repository. */
	@Value("#{catmunRepository}")
	private CatmunRepository catmunRepository;

	/** The id sector. */
	private Long idSector;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#getId(java.lang.Object)
	 */
	@Override
	protected String getId(TcUsuario entity) {
		return Long.valueOf(entity.getId()).toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData(java.util.Map)
	 */
	@Override
	protected int countData(Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		return Long.valueOf(tcUsuarioRepository.count(search)).intValue();

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable, java.util.Map)
	 */
	@Override
	protected List<TcUsuario> fetchData(Pageable pageable, Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		Page<TcUsuario> page = tcUsuarioRepository.findAll(search, pageable);
		return this.setDesc(page.getContent());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#fetchData(org.springframework.data.domain.Pageable)
	 */
	@Override
	protected List<TcUsuario> fetchData(Pageable pageable) {
		Page<TcUsuario> page = tcUsuarioRepository.findAll(pageable);
		return this.setDesc(page.getContent());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#countData()
	 */
	@Override
	protected int countData() {
		return Long.valueOf(tcUsuarioRepository.count()).intValue();
	}

	/**
	 * Construct search query.
	 *
	 * @param filters the filters
	 * @return the predicate
	 */
	private Predicate constructSearchQuery(Map<String, Object> filters) {
		BooleanBuilder mainQuery = new BooleanBuilder();

		String name = MapUtils.getString(filters, "name");
		;
		if (isNotBlank(name)) {
			mainQuery.and(findByUsuario(trimToEmpty(name)));
		}

		if (null != this.idSector) {
			mainQuery.and(findByIdSector(this.idSector));
		}

		return mainQuery;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.datamodel.CustomPagingModel#orderBy()
	 */
	@Override
	protected Sort orderBy() {
		return Sort.by(Sort.Direction.DESC, "id");
	}

	/**
	 * Sets the desc.
	 *
	 * @param tcUsuarios the tc usuarios
	 * @return the list
	 */
	private List<TcUsuario> setDesc(List<TcUsuario> tcUsuarios) {
		for (TcUsuario tcUsuario : tcUsuarios) {
			tcUsuario = this.setDesc(tcUsuario);
		}
		return tcUsuarios;
	}

	/**
	 * Sets the desc.
	 *
	 * @param tcUsuario the tc usuario
	 * @return the tc usuario
	 */
	private TcUsuario setDesc(TcUsuario tcUsuario) {
		String entidadDesc = StringUtils.EMPTY;
		String localidadDesc = StringUtils.EMPTY;
		if (tcUsuario.getTipoUsuario().getId() == 1) {
			TcEntidadAdministrativa tcEntidadAdministrativa = this.tcEntidadAdministrativaRepository
					.findById(tcUsuario.getIdEntidadAdmini()).orElse(null);
			if (tcEntidadAdministrativa != null) {
				entidadDesc = tcEntidadAdministrativa.getDescripcion();
			}
			Catmun catmun = this.catmunRepository.findById(tcUsuario.getIdLocalidad()).orElse(null);
			if (catmun != null) {
				localidadDesc = catmun.getNommun();
			}
		} else {
			TcEntePubCentral tcEntePub = this.tcEntePubCentralRepository.findById(tcUsuario.getIdEntidadAdmini()).orElse(null);
			if (null != tcEntePub) {
				entidadDesc = tcEntePub.getDesc();
			}
			Catdep catdep = this.catdepRepository.findById(tcUsuario.getIdLocalidad()).orElse(null);
			if (catdep != null) {
				localidadDesc = catdep.getNomdep();
			}
		}
		tcUsuario.setDescEntidadAdmin(entidadDesc);
		tcUsuario.setDescLocalidad(localidadDesc);
		return tcUsuario;
	}

	/**
	 * Gets the tc usuario repository.
	 *
	 * @return the tcUsuarioRepository
	 */
	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	/**
	 * Sets the tc usuario repository.
	 *
	 * @param tcUsuarioRepository            the tcUsuarioRepository to set
	 */
	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

	/**
	 * Gets the tc entidad administrativa repository.
	 *
	 * @return the tcEntidadAdministrativaRepository
	 */
	public TcEntidadAdministrativaRepository getTcEntidadAdministrativaRepository() {
		return tcEntidadAdministrativaRepository;
	}

	/**
	 * Sets the tc entidad administrativa repository.
	 *
	 * @param tcEntidadAdministrativaRepository            the tcEntidadAdministrativaRepository to set
	 */
	public void setTcEntidadAdministrativaRepository(
			TcEntidadAdministrativaRepository tcEntidadAdministrativaRepository) {
		this.tcEntidadAdministrativaRepository = tcEntidadAdministrativaRepository;
	}

	/**
	 * Gets the tc ente pub central repository.
	 *
	 * @return the tcEntePubCentralRepository
	 */
	public TcEntePubCentralRepository getTcEntePubCentralRepository() {
		return tcEntePubCentralRepository;
	}

	/**
	 * Sets the tc ente pub central repository.
	 *
	 * @param tcEntePubCentralRepository            the tcEntePubCentralRepository to set
	 */
	public void setTcEntePubCentralRepository(TcEntePubCentralRepository tcEntePubCentralRepository) {
		this.tcEntePubCentralRepository = tcEntePubCentralRepository;
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
	 * @param catdepRepository            the catdepRepository to set
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Gets the catmun repository.
	 *
	 * @return the catmunRepository
	 */
	public CatmunRepository getCatmunRepository() {
		return catmunRepository;
	}

	/**
	 * Sets the catmun repository.
	 *
	 * @param catmunRepository            the catmunRepository to set
	 */
	public void setCatmunRepository(CatmunRepository catmunRepository) {
		this.catmunRepository = catmunRepository;
	}

	/**
	 * Gets the id sector.
	 *
	 * @return the idSector
	 */
	public Long getIdSector() {
		return idSector;
	}

	/**
	 * Sets the id sector.
	 *
	 * @param idSector            the idSector to set
	 */
	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}

}
