package com.gem.sistema.web.security.model;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

// TODO: Auto-generated Javadoc
/**
 * The Class GemUser.
 */
public class GemUser extends User {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** CENTRAL / MUNICIPAL. */
	private String userType;

	/** NOMBRE COMPLETO. */
	private String fullName;

	/** The last login date. */
	private Date lastLoginDate;

	/** The management admin. */
	private String managementAdmin;

	/** The role. */
	private String role;

	/** The id management admin. */
	private Long idManagementAdmin;

	/** The municipio. */
	private String municipio;

	/** The id municipio. */
	private Long idMunicipio;

	/** The id sector. */
	private int idSector;
	
	/** The path img cab 1. */
	private String pathImgCab1;
	
	/** The path img cab 2. */
	private String pathImgCab2;

	/**
	 * Instantiates a new gem user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param enabled the enabled
	 * @param accountNonExpired the account non expired
	 * @param credentialsNonExpired the credentials non expired
	 * @param accountNonLocked the account non locked
	 * @param authorities the authorities
	 */
	public GemUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	/**
	 * Instantiates a new gem user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param enabled the enabled
	 * @param accountNonExpired the account non expired
	 * @param credentialsNonExpired the credentials non expired
	 * @param accountNonLocked the account non locked
	 * @param authorities the authorities
	 * @param userType the user type
	 * @param fullName the full name
	 * @param lastLoginDate the last login date
	 * @param managementAdmin the management admin
	 * @param role the role
	 */
	public GemUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			String userType, String fullName, Date lastLoginDate, String managementAdmin, String role) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userType = userType;
		this.fullName = fullName;
		this.lastLoginDate = lastLoginDate;
		this.managementAdmin = managementAdmin;
		this.role = role;
	}

	/**
	 * Instantiates a new gem user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param enabled the enabled
	 * @param accountNonExpired the account non expired
	 * @param credentialsNonExpired the credentials non expired
	 * @param accountNonLocked the account non locked
	 * @param authorities the authorities
	 * @param userType the user type
	 * @param fullName the full name
	 * @param lastLoginDate the last login date
	 * @param managementAdmin the management admin
	 * @param role the role
	 * @param idManagementAdmin the id management admin
	 * @param municipio the municipio
	 * @param idMunicipio the id municipio
	 * @param idSector the id sector
	 * @param pathImgCab1 the path img cab 1
	 * @param pathImgCab2 the path img cab 2
	 */
	public GemUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			String userType, String fullName, Date lastLoginDate, String managementAdmin, String role,
			Long idManagementAdmin, String municipio, Long idMunicipio, int idSector, String pathImgCab1, String pathImgCab2) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userType = userType;
		this.fullName = fullName;
		this.lastLoginDate = lastLoginDate;
		this.managementAdmin = managementAdmin;
		this.role = role;
		this.idManagementAdmin = idManagementAdmin;
		this.municipio = municipio;
		this.idMunicipio = idMunicipio;
		this.idSector = idSector;
		this.pathImgCab1 = pathImgCab1;
		this.pathImgCab2 = pathImgCab2;
	}

	/**
	 * Instantiates a new gem user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param authorities the authorities
	 * @param userType the user type
	 * @param fullName the full name
	 * @param lastLoginDate the last login date
	 */
	public GemUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String userType, String fullName, Date lastLoginDate) {
		super(username, password, authorities);
		this.userType = userType;
		this.fullName = fullName;
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * Instantiates a new gem user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param authorities the authorities
	 * @param userType the user type
	 * @param fullName the full name
	 * @param lastLoginDate the last login date
	 * @param managementAdmin the management admin
	 * @param role the role
	 * @param idSector the id sector
	 */
	public GemUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String userType, String fullName, Date lastLoginDate, String managementAdmin, String role, int idSector) {
		super(username, password, authorities);
		this.userType = userType;
		this.fullName = fullName;
		this.lastLoginDate = lastLoginDate;
		this.managementAdmin = managementAdmin;
		this.role = role;
		this.idSector = idSector;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the last login date.
	 *
	 * @return the last login date
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * Sets the last login date.
	 *
	 * @param lastLoginDate the new last login date
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * Gets the management admin.
	 *
	 * @return the management admin
	 */
	public String getManagementAdmin() {
		return managementAdmin;
	}

	/**
	 * Sets the management admin.
	 *
	 * @param managementAdmin the new management admin
	 */
	public void setManagementAdmin(String managementAdmin) {
		this.managementAdmin = managementAdmin;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the id management admin.
	 *
	 * @return the id management admin
	 */
	public Long getIdManagementAdmin() {
		return idManagementAdmin;
	}

	/**
	 * Sets the id management admin.
	 *
	 * @param idManagementAdmin the new id management admin
	 */
	public void setIdManagementAdmin(Long idManagementAdmin) {
		this.idManagementAdmin = idManagementAdmin;
	}

	/**
	 * Gets the municipio.
	 *
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * Sets the municipio.
	 *
	 * @param municipio the new municipio
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * Gets the id municipio.
	 *
	 * @return the id municipio
	 */
	public Long getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * Sets the id municipio.
	 *
	 * @param idMunicipio the new id municipio
	 */
	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.User#toString()
	 */
	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * Gets the id sector.
	 *
	 * @return the idSector
	 */
	public int getIdSector() {
		return idSector;
	}

	/**
	 * Sets the id sector.
	 *
	 * @param idSector            the idSector to set
	 */
	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}

	/**
	 * Gets the path img cab 1.
	 *
	 * @return the pathImgCab1
	 */
	public String getPathImgCab1() {
		return pathImgCab1;
	}

	/**
	 * Sets the path img cab 1.
	 *
	 * @param pathImgCab1 the pathImgCab1 to set
	 */
	public void setPathImgCab1(String pathImgCab1) {
		this.pathImgCab1 = pathImgCab1;
	}

	/**
	 * Gets the path img cab 2.
	 *
	 * @return the pathImgCab2
	 */
	public String getPathImgCab2() {
		return pathImgCab2;
	}

	/**
	 * Sets the path img cab 2.
	 *
	 * @param pathImgCab2 the pathImgCab2 to set
	 */
	public void setPathImgCab2(String pathImgCab2) {
		this.pathImgCab2 = pathImgCab2;
	}

	
}
