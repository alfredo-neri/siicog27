package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.Constants.ZERO;

import java.math.BigDecimal;
import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Ingreso;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.load.IngresoRepository;

@Component("creaCuentaIngresoMB")
@Scope("view")
public class CreaCuentaIngresoMB extends AbstractMB {
	private static final Integer MAX_LEVEL = 5;

	private String cuenta;
	private String scta;
	private String sscta;
	private String ssscta;
	private String sssscta;
	private String fuente;
	private Long sector;

	@Value("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	@Value("#{ingresoRepository}")
	private IngresoRepository ingresoRepository;

	@Value("#{fuentefRepository}")
	private FuentefRepository fuentefRepository;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@PostConstruct
	public void init() {
		sector = Long.valueOf(this.getUserDetails().getIdSector());
		cuenta = StringUtils.EMPTY;
		scta = StringUtils.EMPTY;
		sscta = StringUtils.EMPTY;
		ssscta = StringUtils.EMPTY;
		sssscta = StringUtils.EMPTY;
		fuente = StringUtils.EMPTY;
	}

	public void creaCuenta() {
		if (sector == 1) {

			if (StringUtils.isNotEmpty(cuenta) && StringUtils.isNotEmpty(scta) && StringUtils.isNotEmpty(sscta)
					&& StringUtils.isNotEmpty(ssscta) && StringUtils.isNotEmpty(sssscta)
					&& StringUtils.isNotEmpty(fuente)) {
				if (cuenta.equals("8110")) {
					if (fuente.length() == 3) {
						if (existeCuentaEnCuenta()) {
							if (existeFunente()) {
								if (!existeCuentaEnIngresoNivel(MAX_LEVEL)) {
									creaNivelesIngreso();
								} else {
									displayInfoMessage(
											"La cuenta ya ha sido creada, no es nesesario capturarla nuevamente");
								}
							} else {
								displayInfoMessage(
										"La fuente de financiamineto no existe en el catalogo de fuentes de financiamiento");
							}
						} else {
							displayInfoMessage("La cuenta no ha sido creada en el catalogo de cuentas");
						}
					} else {
						displayInfoMessage("La fuente debe de tener 3 digitos");
					}
				} else {
					displayInfoMessage("Solo se permiten cuentas 8110");
				}
			} else {
				displayInfoMessage("Todos los campos son nesesarios");
			}
		} else {
			displayInfoMessage("Utileria solo disponible para sector municipal");
		}
	}

	/**
	 * Obtiene el nivel maximo de la cuenta creada en ingreso y crea las cuentas
	 * aprtir del mismo
	 */
	private void creaNivelesIngreso() {
		Integer nivel = 0;
		for (int i = 1; i <= MAX_LEVEL; i++) {
			if (!existeCuentaEnIngresoNivel(i)) {
				nivel = i;
				break;
			}
		}
		try {
			for (int i = nivel; i <= MAX_LEVEL; i++) {
				Ingreso ingreso = ingresoRepository.save(optenerEntidadPorNivel(i));
				displayInfoMessage("Cuenta: " + ingreso.getCuenta() + " " + ingreso.getScta() + " " + ingreso.getSscta()
						+ " " + ingreso.getSsscta() + " " + ingreso.getSssscta() + " creada en ingreso nivel :" + i);
			}
		} catch (Exception e) {
			displayErrorMessage("Ha ocurrido un error al crear las cuentas ");
		}

	}

	/** Valida que la cuenta actual exista en el catalogo cuentas */
	private Boolean existeCuentaEnCuenta() {
		return cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(cuenta, scta,
				sscta, ssscta, sssscta, sector) != null;
	}

	/** Valida que la fuente exista en el catalogo de fuentes */
	private Boolean existeFunente() {
		return !fuentefRepository.findByLigaAndIdsector(fuente, sector.intValue()).isEmpty();
	}

	/** Valida que la cuenta actual exista en ingreso partir de el nivel dado */
	private Boolean existeCuentaEnIngresoNivel(Integer nivel) {
		switch (nivel) {
		case 1:
			return ingresoRepository.findIngreso(sector, cuenta, "", "", "", "") != null;
		case 2:
			return ingresoRepository.findIngreso(sector, cuenta, scta, "", "", "") != null;
		case 3:
			return ingresoRepository.findIngreso(sector, cuenta, scta, sscta, "", "") != null;
		case 4:
			return ingresoRepository.findIngreso(sector, cuenta, scta, sscta, ssscta, "") != null;
		case 5:
			return ingresoRepository.findIngreso(sector, cuenta, scta, sscta, ssscta, sssscta) != null;
		default:
			return false;
		}

	}

	private String leftPadString(String value, Integer length) {
		if (StringUtils.isNotEmpty(value)) {
			value = StringUtils.leftPad(value, length, StringUtils.EMPTY + ZERO);
		}
		return value;
	}

	public void leftPad() {
		scta = leftPadString(scta, 10);
		sscta = leftPadString(sscta, 15);
		ssscta = leftPadString(ssscta, 4);
		sssscta = leftPadString(sssscta, 4);
	}

	private Ingreso optenerEntidadPorNivel(Integer nivel) {
		Ingreso ingreso = new Ingreso();
		ingreso.setCuenta(cuenta);
		ingreso.setAutoi1(BigDecimal.ZERO);
		ingreso.setAutoi2(BigDecimal.ZERO);
		ingreso.setAutoi3(BigDecimal.ZERO);
		ingreso.setAutoi4(BigDecimal.ZERO);
		ingreso.setAutoi5(BigDecimal.ZERO);
		ingreso.setAutoi6(BigDecimal.ZERO);
		ingreso.setAutoi7(BigDecimal.ZERO);
		ingreso.setAutoi8(BigDecimal.ZERO);
		ingreso.setAutoi9(BigDecimal.ZERO);
		ingreso.setAutoi10(BigDecimal.ZERO);
		ingreso.setAutoi11(BigDecimal.ZERO);
		ingreso.setAutoi12(BigDecimal.ZERO);
		ingreso.setAutoi13(BigDecimal.ZERO);
		ingreso.setUserId(this.getUserDetails().getUsername());
		ingreso.setIdsector(sector);
		ingreso.setRef(0);
		ingreso.setCampo1(0);
		ingreso.setClvfuen(fuente);
		ingreso.setCvetxt("1");
		if (nivel > 1) {
			ingreso.setScta(scta);
			if (nivel > 2) {
				ingreso.setSscta(sscta);
				if (nivel > 3) {
					ingreso.setSsscta(ssscta);
					if (nivel > 4) {
						ingreso.setSssscta(sssscta);
					}
				}
			}
		}
		return ingreso;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getScta() {
		return scta;
	}

	public void setScta(String scta) {
		this.scta = scta;
	}

	public String getSscta() {
		return sscta;
	}

	public void setSscta(String sscta) {
		this.sscta = sscta;
	}

	public String getSsscta() {
		return ssscta;
	}

	public void setSsscta(String ssscta) {
		this.ssscta = ssscta;
	}

	public String getSssscta() {
		return sssscta;
	}

	public void setSssscta(String sssscta) {
		this.sssscta = sssscta;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public IngresoRepository getIngresoRepository() {
		return ingresoRepository;
	}

	public void setIngresoRepository(IngresoRepository ingresoRepository) {
		this.ingresoRepository = ingresoRepository;
	}

	public FuentefRepository getFuentefRepository() {
		return fuentefRepository;
	}

	public void setFuentefRepository(FuentefRepository fuentefRepository) {
		this.fuentefRepository = fuentefRepository;
	}

	public Long getSector() {
		return sector;
	}

	public void setSector(Long sector) {
		this.sector = sector;
	}

}
