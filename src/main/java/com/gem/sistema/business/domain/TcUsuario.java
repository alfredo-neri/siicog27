package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_USUARIOS database table.
 * 
 */
@Entity
@Table(name = "TC_USUARIOS")
@NamedQuery(name = "TcUsuario.findAll", query = "SELECT t FROM TcUsuario t")
public class TcUsuario implements Serializable {

	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name="\"ID\"")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** The contrasenia. */
	@Column(name = "contrasenia", updatable = true)
	private String contrasenia;

	/** The habilitado. */
	@Column(name = "habilitado")
	private Boolean habilitado;

	/** The tipo usuario. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_USUARIO", nullable = false)
	private TcTipoUsuario tipoUsuario;

	/** The nombre. */
	@Column(name = "NOMBRE", updatable = true)
	private String nombre;

	/** The usuario. */
	@Column(name = "USUARIO", unique = true)
	private String usuario;

	/** The menu items. */
	@Transient
	private List<TcMenuItem> menuItems;

//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "TR_USUARIOS2MENUITEMS", joinColumns = {
//			@JoinColumn(name = "ID_USUARIO", nullable = false, updatable = true, insertable = true) }, inverseJoinColumns = {
//					@JoinColumn(name = "ID_MENU_ITEM", nullable = false, updatable = true, insertable = true ) })
//	private List<TcMenuNiveles> menuNiveles;
	
	/** The role. */
@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ROL", nullable = false)
	private TcRole role;

	/** The usuario ctl. */
	@Column(name = "USUARIO_CTL")
	private String usuarioCtl;

	/** The accountnonlocked. */
	private Boolean accountnonlocked;

	/** The credentials non expired. */
	@Column(name = "CREDENTIALSNONEXPIRED")
	private boolean credentialsNonExpired;

	/** The account non expired. */
	@Column(name = "ACCOUNTNONEXPIRED")
	private boolean accountNonExpired;

	/** The id entidad admini. */
	@Column(name = "ID_ENTIDAD_ADMINI")
	private Long idEntidadAdmini;

	/** The id localidad. */
	@Column(name = "ID_LOCALIDAD")
	private Long idLocalidad;

	/** The desc entidad admin. */
	@Transient
	private String descEntidadAdmin;

	/** The desc localidad. */
	@Transient
	private String descLocalidad;

	/** The captured pass. */
	@Transient
	private String capturedPass;

	/**
	 * Instantiates a new tc usuario.
	 */
	public TcUsuario() {
		super();
		// this.entidadAdministrativa = new TcEntidadAdministrativa();
		// this.localidad = new TcLocalidadesUsuario();
		this.tipoUsuario = new TcTipoUsuario();
		this.role = new TcRole();
		this.menuItems = new ArrayList<TcMenuItem>();
	}

	/**
	 * Gets the contrasenia.
	 *
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Sets the contrasenia.
	 *
	 * @param contrasenia            the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Gets the tipo usuario.
	 *
	 * @return the tipoUsuario
	 */
	public TcTipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * Sets the tipo usuario.
	 *
	 * @param tipoUsuario            the tipoUsuario to set
	 */
	public void setTipoUsuario(TcTipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the menu items.
	 *
	 * @return the menuItems
	 */
	public List<TcMenuItem> getMenuItems() {
		return menuItems;
	}

	/**
	 * Sets the menu items.
	 *
	 * @param menuItems            the menuItems to set
	 */
	public void setMenuItems(List<TcMenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public TcRole getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(TcRole role) {
		this.role = role;
	}

	/**
	 * Gets the accountnonlocked.
	 *
	 * @return the accountnonlocked
	 */
	public Boolean getAccountnonlocked() {
		return accountnonlocked;
	}

	/**
	 * Sets the accountnonlocked.
	 *
	 * @param accountnonlocked the new accountnonlocked
	 */
	public void setAccountnonlocked(Boolean accountnonlocked) {
		this.accountnonlocked = accountnonlocked;
	}

	/**
	 * Gets the habilitado.
	 *
	 * @return the habilitado
	 */
	public Boolean getHabilitado() {
		return habilitado;
	}

	/**
	 * Sets the habilitado.
	 *
	 * @param habilitado the new habilitado
	 */
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	/**
	 * Gets the usuario ctl.
	 *
	 * @return the usuario ctl
	 */
	public String getUsuarioCtl() {
		return usuarioCtl;
	}

	/**
	 * Sets the usuario ctl.
	 *
	 * @param usuarioCtl the new usuario ctl
	 */
	public void setUsuarioCtl(String usuarioCtl) {
		this.usuarioCtl = usuarioCtl;
	}

	/**
	 * Checks if is credentials non expired.
	 *
	 * @return true, if is credentials non expired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * Sets the credentials non expired.
	 *
	 * @param credentialsNonExpired the new credentials non expired
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * Checks if is account non expired.
	 *
	 * @return true, if is account non expired
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * Sets the account non expired.
	 *
	 * @param accountNonExpired the new account non expired
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the id entidad admini.
	 *
	 * @return the idEntidadAdmini
	 */
	public Long getIdEntidadAdmini() {
		return idEntidadAdmini;
	}

	/**
	 * Sets the id entidad admini.
	 *
	 * @param idEntidadAdmini            the idEntidadAdmini to set
	 */
	public void setIdEntidadAdmini(Long idEntidadAdmini) {
		this.idEntidadAdmini = idEntidadAdmini;
	}

	/**
	 * Gets the id localidad.
	 *
	 * @return the idLocalidad
	 */
	public Long getIdLocalidad() {
		return idLocalidad;
	}

	/**
	 * Sets the id localidad.
	 *
	 * @param idLocalidad            the idLocalidad to set
	 */
	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	/**
	 * Gets the desc entidad admin.
	 *
	 * @return the descEntidadAdmin
	 */
	public String getDescEntidadAdmin() {
		return descEntidadAdmin;
	}

	/**
	 * Sets the desc entidad admin.
	 *
	 * @param descEntidadAdmin            the descEntidadAdmin to set
	 */
	public void setDescEntidadAdmin(String descEntidadAdmin) {
		this.descEntidadAdmin = descEntidadAdmin;
	}

	/**
	 * Gets the desc localidad.
	 *
	 * @return the descLocalidad
	 */
	public String getDescLocalidad() {
		return descLocalidad;
	}

	/**
	 * Sets the desc localidad.
	 *
	 * @param descLocalidad            the descLocalidad to set
	 */
	public void setDescLocalidad(String descLocalidad) {
		this.descLocalidad = descLocalidad;
	}

	/**
	 * Gets the captured pass.
	 *
	 * @return the captured pass
	 */
	public String getCapturedPass() {
		return capturedPass;
	}

	/**
	 * Sets the captured pass.
	 *
	 * @param capturedPass the new captured pass
	 */
	public void setCapturedPass(String capturedPass) {
		this.capturedPass = capturedPass;
	}

//	public List<TcMenuNiveles> getMenuNiveles() {
//		return menuNiveles;
//	}
//
//	public void setMenuNiveles(List<TcMenuNiveles> menuNiveles) {
//		this.menuNiveles = menuNiveles;
//	}

	/**
 * Gets the id.
 *
 * @return the id
 */
public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

}