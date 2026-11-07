package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Localidades;
import com.gem.sistema.business.domain.Programamun;
import com.gem.sistema.business.dto.ProgramamunDTO;
import com.gem.sistema.business.repository.catalogs.LocalidadesRepository;
import com.gem.sistema.business.repository.catalogs.ProgramamunRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgramamunMB.
 *
 * @author Sam
 */
@ManagedBean(name = "programamunMB")
@ViewScoped
public class ProgramamunMB extends AbstractMB implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProgramamunMB.class);
	
	/** The programamun list. */
	private List<ProgramamunDTO> programamunList = new ArrayList<ProgramamunDTO>();
	
	/** The programamun DTO. */
	private ProgramamunDTO programamunDTO = new ProgramamunDTO();
	
	/** The deshabilitado. */
	private boolean deshabilitado;
	
	/** The ver boton salvar. */
	private boolean verBotonSalvar;
	
	/** The ver boton modificar. */
	private boolean verBotonModificar;
	
	/** The ver boton limpiar. */
	private boolean verBotonLimpiar;
	
	/** The ver boton cancelar. */
	private boolean verBotonCancelar;
	
	/** The lista. */
	List<Programamun> lista = new ArrayList<Programamun>();
	
	/** The lista localidades. */
	List<Localidades> listaLocalidades = new ArrayList<Localidades>();
	
	/** The lista localidades selected. */
	List<Localidades> listaLocalidadesSelected = new ArrayList<Localidades>();
	
	/** The lista localidades filtered. */
	List<Localidades> listaLocalidadesFiltered = new ArrayList<Localidades>();

	/** The programamun repository. */
	@ManagedProperty(value = "#{programamunRepository}")
	private ProgramamunRepository programamunRepository;

	/** The localidades repository. */
	@ManagedProperty(value = "#{localidadesRepository}")
	private LocalidadesRepository localidadesRepository;

	/** Mensaje Info. */
	// @Value("${catalog.message.info}")
	protected static final String MESSAGE_INFO = FrontProperty.getPropertyValue("catalog.message.info");

	/** Mensaje Error. */
	// @Value("${catalog.message.error}")
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The rowtable. */
	private int rowtable;

	/**
	 * Salvar.
	 *
	 * @param row the row
	 */
	public void salvar(Integer row) {
		if (null == programamunList.get(row).getLocben())
			programamunList.get(row).setLocben("");

		String errorMsg = this.validate(programamunList.get(row));
		if (StringUtils.isNotEmpty(errorMsg)) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg);
		} else {
			Programamun programamun = new Programamun();
			programamun.setId(programamunList.get(row).getId());
			programamun.setCvedepa(programamunList.get(row).getCvedepa());
			programamun.setCvedepg(programamunList.get(row).getCvedepg());
			programamun.setDescrip(programamunList.get(row).getDescrip());
			programamun.setFecini(programamunList.get(row).getFecini());
			programamun.setFecter(programamunList.get(row).getFecter());
			programamun.setFfin(programamunList.get(row).getFfin());
			programamun.setId(programamunList.get(row).getId());
			programamun.setLocben(programamunList.get(row).getLocben());
			programamun.setPobben(programamunList.get(row).getPobben());
			programamun.setPrograma(programamunList.get(row).getPrograma());
			programamun.setUserid(this.getUserDetails().getUsername());
			programamun.setIdsector(this.getUserDetails().getIdSector());
			programamunRepository.save(programamun);

			findAllProgram();

			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, "Registro guardado correctamente");
			reset(row);
			this.setDeshabilitado(true);
			this.setVerBotonCancelar(true);
			this.setVerBotonLimpiar(true);
			this.setVerBotonModificar(false);
			this.setVerBotonSalvar(true);
		}

	}

	/**
	 * Validate.
	 *
	 * @param programamunDTO the programamun DTO
	 * @return the string
	 */
	private String validate(ProgramamunDTO programamunDTO) {
		StringBuilder toReturn = new StringBuilder();
		StringBuilder localidades = new StringBuilder();

		if (!(programamunDTO.getPobben() >= 0 && programamunDTO.getPobben() <= 100)) {
			toReturn.append("El campo población Beneficiada solo acepta números entre 0 y 100\r\n");
		}

		if (programamunDTO.getFecter() != null && programamunDTO.getFecini() != null) {
			if (programamunDTO.getFecter().before(programamunDTO.getFecini())) {
				toReturn.append("La Fecha de terminación no puede ser menor a la Fecha de inicio\r\n");
			}
		}

		if (programamunDTO.getLocben() != "") {
			String[] cadena = programamunDTO.getLocben().split(",");
			for (int i = 0; i < cadena.length; i++) {
				if (localidadesRepository.numberByCveloc(Integer.parseInt(cadena[i].trim())) < 1) {
					localidades.append(", " + cadena[i].trim());
				}
			}
			if (StringUtils.isNotEmpty(localidades.toString())) {
				toReturn.append("La(s) siguiente(s) localidad(es): " + localidades.toString().substring(1)
						+ " no existe(n), Favor de verficar.");
			}
		}
		return toReturn.toString();
	}

	/**
	 * Modificar.
	 */
	public void modificar() {
		this.setVerBotonSalvar(false);
		this.setVerBotonModificar(true);
		this.setVerBotonCancelar(false);
		this.setVerBotonLimpiar(false);
		this.setDeshabilitado(false);
	}

	/**
	 * Reset.
	 *
	 * @param row the row
	 */
	public void reset(Integer row) {
		programamunList.get(row).setCvedepa(lista.get(row).getCvedepa());
		programamunList.get(row).setCvedepg(lista.get(row).getCvedepg());
		programamunList.get(row).setDescrip(lista.get(row).getDescrip());
		programamunList.get(row).setFecini(lista.get(row).getFecini());
		programamunList.get(row).setFecter(lista.get(row).getFecter());
		programamunList.get(row).setFfin(lista.get(row).getFfin());
		programamunList.get(row).setId(lista.get(row).getId());
		programamunList.get(row).setLocben(lista.get(row).getLocben());
		programamunList.get(row).setPobben(lista.get(row).getPobben());
		programamunList.get(row).setPrograma(lista.get(row).getPrograma());
		// this.setVerBotonSalvar(true);
		// this.setVerBotonModificar(false);
		// this.setDeshabilitado(true);
	}

	/**
	 * Cancelar.
	 *
	 * @param row the row
	 */
	public void cancelar(Integer row) {
		reset(row);
		this.setVerBotonSalvar(true);
		this.setVerBotonLimpiar(true);
		this.setVerBotonCancelar(true);
		this.setVerBotonModificar(false);
		this.setDeshabilitado(true);
	}
	
	/**
	 * Cancelar.
	 */
	public void cancelar() {
		this.setVerBotonSalvar(true);
		this.setVerBotonLimpiar(true);
		this.setVerBotonCancelar(true);
		this.setVerBotonModificar(false);
		this.setDeshabilitado(true);
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct ProgramamunMB " + "Size List: " + programamunList.size());
		findAllProgram();
		this.setVerBotonSalvar(true);
		this.setVerBotonModificar(true);
		this.setVerBotonLimpiar(true);
		this.setVerBotonCancelar(true);
		this.setDeshabilitado(true);
		if (programamunRepository.countProgramamunByIdsector(1) > 0) {
			this.setVerBotonModificar(false);
		}
	}

	/**
	 * Find all program.
	 */
	private void findAllProgram() {

		lista = programamunRepository.findAll();
		programamunList = new ArrayList<ProgramamunDTO>();
		ProgramamunDTO programamunDTO;
		for (Programamun prog : lista) {
			programamunDTO = new ProgramamunDTO();
			programamunDTO.setCvedepa(prog.getCvedepa());
			programamunDTO.setCvedepg(prog.getCvedepg());
			programamunDTO.setDescrip(prog.getDescrip());
			programamunDTO.setFecini(prog.getFecini());
			programamunDTO.setFecter(prog.getFecter());
			programamunDTO.setFfin(prog.getFfin());
			programamunDTO.setId(prog.getId());
			programamunDTO.setLocben(prog.getLocben());
			programamunDTO.setPobben(prog.getPobben() == null ? 0 : prog.getPobben());
			programamunDTO.setPrograma(prog.getPrograma());
			programamunList.add(programamunDTO);
		}
		if (!lista.isEmpty()) {
			this.setDeshabilitado(false);
		} else {
			this.setDeshabilitado(true);
			ProgramamunDTO programamunDTOEmpty = new ProgramamunDTO();
			programamunList.add(programamunDTOEmpty);
		}
	}

	/**
	 * Consult localidades.
	 *
	 * @param row the row
	 */
	public void consultLocalidades(int row) {
		this.setRowtable(row);
		LOGGER.info(":: Consultar Localidades " + row);
		listaLocalidades = localidadesRepository.findAll();
		listaLocalidadesSelected.clear();
		String locben = programamunList.get(this.getRowtable()).getLocben();
		if (locben != null && !locben.isEmpty()) {
			String[] localidades = StringUtils.splitByWholeSeparator(locben, ",");
			for (String i : localidades) {
				for (Localidades locs : listaLocalidades) {
					if (i.trim().equals(locs.getCveLoc().toString())) {
						listaLocalidadesSelected.add(locs);
						break;
					}
				}
			}

		}

	}

	/**
	 * Change localidades.
	 */
	public void changeLocalidades() {
		LOGGER.info(":: Cambiar Localidades Beneficiadas " + listaLocalidadesSelected.toString());
		if (!listaLocalidadesSelected.isEmpty())
			programamunList.get(this.getRowtable()).setLocben(printLocalidades());
	}

	/**
	 * Prints the localidades.
	 *
	 * @return the string
	 */
	private String printLocalidades() {
		StringBuilder locTextArea = new StringBuilder();
		int i = 1;
		for (Localidades locs : listaLocalidadesSelected) {
			locTextArea.append(locs.getCveLoc());
			if (listaLocalidadesSelected.size() != i)
				locTextArea.append(",");
			i++;
		}
		return locTextArea.toString();
	}

	/**
	 * Gets the programamun list.
	 *
	 * @return the programamun list
	 */
	public List<ProgramamunDTO> getProgramamunList() {
		return programamunList;
	}

	/**
	 * Sets the programamun list.
	 *
	 * @param programamunList the new programamun list
	 */
	public void setProgramamunList(List<ProgramamunDTO> programamunList) {
		this.programamunList = programamunList;
	}

	/**
	 * Gets the programamun DTO.
	 *
	 * @return the programamun DTO
	 */
	public ProgramamunDTO getProgramamunDTO() {
		return programamunDTO;
	}

	/**
	 * Sets the programamun DTO.
	 *
	 * @param programamunDTO the new programamun DTO
	 */
	public void setProgramamunDTO(ProgramamunDTO programamunDTO) {
		this.programamunDTO = programamunDTO;
	}

	/**
	 * Checks if is deshabilitado.
	 *
	 * @return true, if is deshabilitado
	 */
	public boolean isDeshabilitado() {
		return deshabilitado;
	}

	/**
	 * Sets the deshabilitado.
	 *
	 * @param deshabilitado the new deshabilitado
	 */
	public void setDeshabilitado(boolean deshabilitado) {
		this.deshabilitado = deshabilitado;
	}

	/**
	 * Checks if is ver boton salvar.
	 *
	 * @return true, if is ver boton salvar
	 */
	public boolean isVerBotonSalvar() {
		return verBotonSalvar;
	}

	/**
	 * Sets the ver boton salvar.
	 *
	 * @param verBotonSalvar the new ver boton salvar
	 */
	public void setVerBotonSalvar(boolean verBotonSalvar) {
		this.verBotonSalvar = verBotonSalvar;
	}

	/**
	 * Checks if is ver boton modificar.
	 *
	 * @return true, if is ver boton modificar
	 */
	public boolean isVerBotonModificar() {
		return verBotonModificar;
	}

	/**
	 * Sets the ver boton modificar.
	 *
	 * @param verBotonModificar the new ver boton modificar
	 */
	public void setVerBotonModificar(boolean verBotonModificar) {
		this.verBotonModificar = verBotonModificar;
	}

	/**
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public List<Programamun> getLista() {
		return lista;
	}

	/**
	 * Sets the lista.
	 *
	 * @param lista the new lista
	 */
	public void setLista(List<Programamun> lista) {
		this.lista = lista;
	}

	/**
	 * Gets the programamun repository.
	 *
	 * @return the programamun repository
	 */
	public ProgramamunRepository getProgramamunRepository() {
		return programamunRepository;
	}

	/**
	 * Sets the programamun repository.
	 *
	 * @param programamunRepository the new programamun repository
	 */
	public void setProgramamunRepository(ProgramamunRepository programamunRepository) {
		this.programamunRepository = programamunRepository;
	}

	/**
	 * Gets the lista localidades.
	 *
	 * @return the lista localidades
	 */
	public List<Localidades> getListaLocalidades() {
		return listaLocalidades;
	}

	/**
	 * Sets the lista localidades.
	 *
	 * @param listaLocalidades the new lista localidades
	 */
	public void setListaLocalidades(List<Localidades> listaLocalidades) {
		this.listaLocalidades = listaLocalidades;
	}

	/**
	 * Gets the localidades repository.
	 *
	 * @return the localidades repository
	 */
	public LocalidadesRepository getLocalidadesRepository() {
		return localidadesRepository;
	}

	/**
	 * Sets the localidades repository.
	 *
	 * @param localidadesRepository the new localidades repository
	 */
	public void setLocalidadesRepository(LocalidadesRepository localidadesRepository) {
		this.localidadesRepository = localidadesRepository;
	}

	/**
	 * Gets the lista localidades selected.
	 *
	 * @return the lista localidades selected
	 */
	public List<Localidades> getListaLocalidadesSelected() {
		return listaLocalidadesSelected;
	}

	/**
	 * Sets the lista localidades selected.
	 *
	 * @param listaLocalidadesSelected the new lista localidades selected
	 */
	public void setListaLocalidadesSelected(List<Localidades> listaLocalidadesSelected) {
		this.listaLocalidadesSelected = listaLocalidadesSelected;
	}

	/**
	 * Gets the lista localidades filtered.
	 *
	 * @return the lista localidades filtered
	 */
	public List<Localidades> getListaLocalidadesFiltered() {
		return listaLocalidadesFiltered;
	}

	/**
	 * Sets the lista localidades filtered.
	 *
	 * @param listaLocalidadesFiltered the new lista localidades filtered
	 */
	public void setListaLocalidadesFiltered(List<Localidades> listaLocalidadesFiltered) {
		this.listaLocalidadesFiltered = listaLocalidadesFiltered;
	}

	/**
	 * Gets the rowtable.
	 *
	 * @return the rowtable
	 */
	public int getRowtable() {
		return rowtable;
	}

	/**
	 * Sets the rowtable.
	 *
	 * @param rowtable the new rowtable
	 */
	public void setRowtable(int rowtable) {
		this.rowtable = rowtable;
	}

	/**
	 * Checks if is ver boton limpiar.
	 *
	 * @return true, if is ver boton limpiar
	 */
	public boolean isVerBotonLimpiar() {
		return verBotonLimpiar;
	}

	/**
	 * Sets the ver boton limpiar.
	 *
	 * @param verBotonLimpiar the new ver boton limpiar
	 */
	public void setVerBotonLimpiar(boolean verBotonLimpiar) {
		this.verBotonLimpiar = verBotonLimpiar;
	}

	/**
	 * Checks if is ver boton cancelar.
	 *
	 * @return true, if is ver boton cancelar
	 */
	public boolean isVerBotonCancelar() {
		return verBotonCancelar;
	}

	/**
	 * Sets the ver boton cancelar.
	 *
	 * @param verBotonCancelar the new ver boton cancelar
	 */
	public void setVerBotonCancelar(boolean verBotonCancelar) {
		this.verBotonCancelar = verBotonCancelar;
	}

}
