package com.gem.sistema.web.security.providers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Catmun;
import com.gem.sistema.business.domain.TcEntePubCentral;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TcImagenesEntAdmin;
import com.gem.sistema.business.domain.TcImagenesMuni;
import com.gem.sistema.business.domain.TcRole;
import com.gem.sistema.business.domain.TcUsuario;
import com.gem.sistema.business.predicates.TcEntidadAdministrativaPredicate;
import com.gem.sistema.business.predicates.TcImagenesEntAdminPredicate;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CatmunRepository;
import com.gem.sistema.business.repository.catalogs.TcEntePubCentralRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcImagenesEntAdminRepository;
import com.gem.sistema.business.repository.catalogs.TcImagenesMuniRepository;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * Clase de servicio de autenticación y generación de sesión de usuario
 * autenticado.
 *
 */
@Component("authenticationService")
public class AuthenticationService implements UserDetailsService {

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

	/** The tc usuario repository. */
	@Autowired
	@Qualifier("tcUsuarioRepository")
	private TcUsuarioRepository tcUsuarioRepository;

	/** The tc entidad administrativa repository. */
	@Autowired
	@Qualifier("tcEntidadAdministrativaRepository")
	private TcEntidadAdministrativaRepository tcEntidadAdministrativaRepository;

	/** The tc ente pub central repository. */
	@Autowired
	@Qualifier("tcEntePubCentralRepository")
	private TcEntePubCentralRepository tcEntePubCentralRepository;

	/** The catdep repository. */
	@Autowired
	@Qualifier("catdepRepository")
	private CatdepRepository catdepRepository;

	/** The catmun repository. */
	@Autowired
	@Qualifier("catmunRepository")
	private CatmunRepository catmunRepository;

	/** The tc imagenes muni repository. */
	@Autowired
	@Qualifier("tcImagenesMuniRepository")
	private TcImagenesMuniRepository tcImagenesMuniRepository;

	/** The tc imagenes ent admin repository. */
	@Autowired
	@Qualifier("tcImagenesEntAdminRepository")
	private TcImagenesEntAdminRepository tcImagenesEntAdminRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LOGGER.info("Recibiendo {}", userName);
		TcUsuario tcUsuario = tcUsuarioRepository.findByUsuario(userName);

		return buildUserForAuthentication(tcUsuario, buildUserAuthority(tcUsuario.getRole()));
	}

	/**
	 * Builds the user for authentication.
	 *
	 * @param user
	 *            the user
	 * @param authorities
	 *            the authorities
	 * @return the user
	 */
	private User buildUserForAuthentication(TcUsuario user, List<GrantedAuthority> authorities) {
		String entidadDesc = StringUtils.EMPTY;
		String localidadDesc = StringUtils.EMPTY;
		String pathImgCab1 = StringUtils.EMPTY;
		String pathImgCab2 = StringUtils.EMPTY;
		Long idEntidad = 0l;
		Long idLocalidad = 0l;
		if (user.getTipoUsuario().getId() == 1) {
			TcEntidadAdministrativa tcEntidadAdministrativa = this.tcEntidadAdministrativaRepository
					.findOne(TcEntidadAdministrativaPredicate.findByClaveEntidad(user.getIdEntidadAdmini().toString()));
			entidadDesc = tcEntidadAdministrativa.getDescripcion();
			idEntidad = Long.valueOf(tcEntidadAdministrativa.getClave());
			Catmun catmun = this.catmunRepository.findOne(user.getIdLocalidad());
			localidadDesc = catmun.getNommun();
			idLocalidad = catmun.getId();
			String[] paths = this.getPaths(catmun.getClvmun(), idEntidad);
			pathImgCab1 = paths[0];
			pathImgCab2 = paths[1];
		} else {
			TcEntePubCentral tcEntePub = this.tcEntePubCentralRepository.findOne(user.getIdEntidadAdmini());
			entidadDesc = tcEntePub.getDesc();
			idEntidad = tcEntePub.getId();
			Catdep catdep = this.catdepRepository.findOne(user.getIdLocalidad());
			localidadDesc = catdep.getNomdep();
			idLocalidad = catdep.getId();
			String[] paths = this.getPaths(0, 0l);
			pathImgCab1 = paths[0];
			pathImgCab2 = paths[1];
		}

		Calendar cal = Calendar.getInstance();
		return new GemUser(user.getUsuario(), user.getContrasenia(), user.getHabilitado(),
				user.getAccountnonlocked().booleanValue(), true, true, authorities,
				user.getTipoUsuario().getDescripcion(), user.getNombre(), cal.getTime(), entidadDesc,
				user.getRole().getNombre(), idEntidad, localidadDesc, idLocalidad,
				Integer.valueOf(user.getTipoUsuario().getClave()), pathImgCab1, pathImgCab2);
	}

	/**
	 * Gets the paths.
	 *
	 * @param clvmun
	 *            the clvmun
	 * @param idEntidad
	 *            the id entidad
	 * @return the paths
	 */
	private String[] getPaths(int clvmun, Long idEntidad) {
		String[] toReturn = new String[] { StringUtils.EMPTY, StringUtils.EMPTY };
		TcImagenesMuni tcImagenesMuni = this.tcImagenesMuniRepository.findByClvmun(clvmun);
		if (null != tcImagenesMuni) {
			toReturn[0] = tcImagenesMuni.getPathFile();
		}

		TcImagenesEntAdmin tcImagenesEntAdmin = this.tcImagenesEntAdminRepository
				.findOne(TcImagenesEntAdminPredicate.findByClvmunAndEntidad(idEntidad, clvmun == 0 ? 0 : 1));
		if (null != tcImagenesEntAdmin) {
			toReturn[1] = tcImagenesEntAdmin.getPathFile();
		}
		return toReturn;
	}

	/**
	 * Builds the user authority.
	 *
	 * @param role
	 *            the role
	 * @return the list
	 */
	private List<GrantedAuthority> buildUserAuthority(TcRole role) {
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		result.add(new SimpleGrantedAuthority(role.getClave()));
		return result;
	}

}
