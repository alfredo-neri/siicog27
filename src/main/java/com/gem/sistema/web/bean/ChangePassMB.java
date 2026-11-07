package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.TcModulosOperacione;
import com.gem.sistema.business.domain.TcParametro;
import com.gem.sistema.business.service.catalogos.ChangePasswordService;

@ManagedBean(name = "changePassMB")
@ViewScoped
public class ChangePassMB extends AbstractMB {

	private static final Integer INDEX_HOME = 0;

	private String labelPass = "Password";
	private String password;
	private String newPassword;
	private String confirmPassword;
	private Integer tipoPass = 1;
	private Integer idSector;
	private TcParametro tcParametro;
	private TcModulosOperacione tcModulosOperacione;

	private boolean bRender = false;
	private String clvModulo;
	private List<TcModulosOperacione> listModulo;

	@ManagedProperty("#{changePasswordService}")
	private ChangePasswordService changePasswordService;

	@PostConstruct
	public void init() {
		idSector = this.getUserDetails().getIdSector();
		listModulo = this.changePasswordService.findBySector(idSector);
		clvModulo = listModulo.get(INDEX_HOME).getClvProceso();

	}

	public void save() {
		tcParametro = new TcParametro();
		tcModulosOperacione = new TcModulosOperacione();
		if (StringUtils.isNotBlank(password)) {
			tcParametro.setCveParametro(clvModulo);
			tcParametro.setValor(password);

			tcModulosOperacione.setClvProceso(clvModulo);
			tcModulosOperacione.setIdSector(idSector);
			boolean bandera = this.changePasswordService.validatePassword(tcParametro, tcModulosOperacione);
			if (tipoPass == 1) {

				if (!bandera) {
					String value = this.changePasswordService.getValueParameter(tcParametro.getCveParametro());
					if (StringUtils.isBlank(value)) {
						if (password.equals(confirmPassword)) {
							this.changePasswordService.save(tcParametro, tcModulosOperacione);
							confirmPassword = StringUtils.EMPTY;
							generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
									"El password se guardo correctamente");
						} else {
							generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
									"El password no coinside con la confirmación");
						}
					} else {
						generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
								"!El modulo al que deseas cambiarle el password, ya tiene uno asociado!");
					}
				} else {
					generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!", "El password ya existe");
				}
			} else if (tipoPass == 2) {
				if (StringUtils.isNotBlank(newPassword) || StringUtils.isNotBlank(confirmPassword)) {
					if (bandera) {
						if (newPassword.equals(confirmPassword)) {
							tcParametro.setValor(newPassword);
							this.changePasswordService.save(tcParametro, tcModulosOperacione);
							newPassword = StringUtils.EMPTY;
							confirmPassword = StringUtils.EMPTY;
							generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
									"El password se cambio correctamente");
						} else {
							generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
									"El password no coinside con la confirmación");
						}
					} else {
						generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
								"El password no existe en la base");
					}
				} else {
					generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
							"El nuevo password y la confirmacion son obligatorios");
				}
			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!", "El password es obligatorio");
		}
	}

	public void changePass() {
		if (tipoPass == 1) {
			labelPass = "Password";

			bRender = false;
		} else if (tipoPass == 2) {
			labelPass = "Password Anterior";
			bRender = true;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public ChangePasswordService getChangePasswordService() {
		return changePasswordService;
	}

	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

	public String getLabelPass() {
		return labelPass;
	}

	public void setLabelPass(String labelPass) {
		this.labelPass = labelPass;
	}

	public boolean isbRender() {
		return bRender;
	}

	public void setbRender(boolean bRender) {
		this.bRender = bRender;
	}

	public Integer getTipoPass() {
		return tipoPass;
	}

	public void setTipoPass(Integer tipoPass) {
		this.tipoPass = tipoPass;
	}

	public String getClvModulo() {
		return clvModulo;
	}

	public void setClvModulo(String clvModulo) {
		this.clvModulo = clvModulo;
	}

	public List<TcModulosOperacione> getListModulo() {
		return listModulo;
	}

	public void setListModulo(List<TcModulosOperacione> listModulo) {
		this.listModulo = listModulo;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public TcParametro getTcParametro() {
		return tcParametro;
	}

	public void setTcParametro(TcParametro tcParametro) {
		this.tcParametro = tcParametro;
	}

	public TcModulosOperacione getTcModulosOperacione() {
		return tcModulosOperacione;
	}

	public void setTcModulosOperacione(TcModulosOperacione tcModulosOperacione) {
		this.tcModulosOperacione = tcModulosOperacione;
	}

}
