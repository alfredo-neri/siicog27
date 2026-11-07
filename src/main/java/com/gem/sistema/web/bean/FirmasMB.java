package com.gem.sistema.web.bean;

import static com.gem.sistema.business.utils.GetValuesClassUtils.getFieldNamesAndValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.dto.FirmasDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.FirmasService;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class FirmasMB.
 */
@ManagedBean(name = "firmasMB")
@ViewScoped
public class FirmasMB extends AbstractMB {

	/** The Constant INIT. */
	private static final int INIT = 1;
	
	/** The Constant POST_INIT. */
	private static final int POST_INIT = 2;
	
	/** The Constant MODIFY. */
	private static final int MODIFY = 3;
	
	/** The Constant FIELDS_NAME. */
	private static final String FIELDS_NAME = FrontProperty.getPropertyValue("firmas.fiels.name");
	
	/** The firmas. */
	private Firmas firmas;

	/** The anio. */
	private Integer anio;

	/** The titulo. */
	private String titulo;

	/** The b lbl. */
	private Boolean bLbl;
	
	/** The btxt. */
	private Boolean btxt;
	
	/** The b add. */
	private Boolean bAdd;
	
	/** The b borrar. */
	private Boolean bBorrar;
	
	/** The b modificar. */
	private Boolean bModificar;
	
	/** The b V modificar. */
	private Boolean bVModificar;
	
	/** The b save. */
	private Boolean bSave;
	
	/** The b limpiar. */
	private Boolean bLimpiar;
	
	/** The b cancel. */
	private Boolean bCancel;

	/** The firmas DTO. */
	private FirmasDTO firmasDTO;

	/** The lis DTO. */
	private List<FirmasDTO> lisDTO;

	/** The firmas service. */
	@ManagedProperty("#{firmasService}")
	private FirmasService firmasService;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio the new anio
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the firmas service.
	 *
	 * @return the firmas service
	 */
	public FirmasService getFirmasService() {
		return firmasService;
	}

	/**
	 * Sets the firmas service.
	 *
	 * @param firmasService the new firmas service
	 */
	public void setFirmasService(FirmasService firmasService) {
		this.firmasService = firmasService;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the b lbl.
	 *
	 * @return the b lbl
	 */
	public Boolean getbLbl() {
		return bLbl;
	}

	/**
	 * Sets the b lbl.
	 *
	 * @param bLbl the new b lbl
	 */
	public void setbLbl(Boolean bLbl) {
		this.bLbl = bLbl;
	}

	/**
	 * Gets the btxt.
	 *
	 * @return the btxt
	 */
	public Boolean getBtxt() {
		return btxt;
	}

	/**
	 * Sets the btxt.
	 *
	 * @param btxt the new btxt
	 */
	public void setBtxt(Boolean btxt) {
		this.btxt = btxt;
	}

	/**
	 * Gets the b add.
	 *
	 * @return the b add
	 */
	public Boolean getbAdd() {
		return bAdd;
	}

	/**
	 * Sets the b add.
	 *
	 * @param bAdd the new b add
	 */
	public void setbAdd(Boolean bAdd) {
		this.bAdd = bAdd;
	}

	/**
	 * Gets the b borrar.
	 *
	 * @return the b borrar
	 */
	public Boolean getbBorrar() {
		return bBorrar;
	}

	/**
	 * Sets the b borrar.
	 *
	 * @param bBorrar the new b borrar
	 */
	public void setbBorrar(Boolean bBorrar) {
		this.bBorrar = bBorrar;
	}

	/**
	 * Gets the b modificar.
	 *
	 * @return the b modificar
	 */
	public Boolean getbModificar() {
		return bModificar;
	}

	/**
	 * Sets the b modificar.
	 *
	 * @param bModificar the new b modificar
	 */
	public void setbModificar(Boolean bModificar) {
		this.bModificar = bModificar;
	}

	/**
	 * Gets the b V modificar.
	 *
	 * @return the b V modificar
	 */
	public Boolean getbVModificar() {
		return bVModificar;
	}

	/**
	 * Sets the b V modificar.
	 *
	 * @param bVModificar the new b V modificar
	 */
	public void setbVModificar(Boolean bVModificar) {
		this.bVModificar = bVModificar;
	}

	/**
	 * Gets the b save.
	 *
	 * @return the b save
	 */
	public Boolean getbSave() {
		return bSave;
	}

	/**
	 * Sets the b save.
	 *
	 * @param bSave the new b save
	 */
	public void setbSave(Boolean bSave) {
		this.bSave = bSave;
	}

	/**
	 * Gets the b limpiar.
	 *
	 * @return the b limpiar
	 */
	public Boolean getbLimpiar() {
		return bLimpiar;
	}

	/**
	 * Sets the b limpiar.
	 *
	 * @param bLimpiar the new b limpiar
	 */
	public void setbLimpiar(Boolean bLimpiar) {
		this.bLimpiar = bLimpiar;
	}

	/**
	 * Gets the b cancel.
	 *
	 * @return the b cancel
	 */
	public Boolean getbCancel() {
		return bCancel;
	}

	/**
	 * Sets the b cancel.
	 *
	 * @param bCancel the new b cancel
	 */
	public void setbCancel(Boolean bCancel) {
		this.bCancel = bCancel;
	}

	/**
	 * Gets the firmas DTO.
	 *
	 * @return the firmas DTO
	 */
	public FirmasDTO getFirmasDTO() {
		return firmasDTO;
	}

	/**
	 * Sets the firmas DTO.
	 *
	 * @param firmasDTO the new firmas DTO
	 */
	public void setFirmasDTO(FirmasDTO firmasDTO) {
		this.firmasDTO = firmasDTO;
	}

	/**
	 * Gets the lis DTO.
	 *
	 * @return the lis DTO
	 */
	public List<FirmasDTO> getLisDTO() {
		return lisDTO;
	}

	/**
	 * Sets the lis DTO.
	 *
	 * @param lisDTO the new lis DTO
	 */
	public void setLisDTO(List<FirmasDTO> lisDTO) {
		this.lisDTO = lisDTO;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		
		this.setbModificar(Boolean.FALSE);
		this.setbVModificar(Boolean.TRUE);
		this.setbBorrar(Boolean.TRUE);
		this.setbSave(Boolean.FALSE);
		this.setbLbl(Boolean.TRUE);
		this.setBtxt(Boolean.FALSE);
		this.setbCancel(Boolean.TRUE);
		this.setbLimpiar(Boolean.TRUE);
		firmas = new Firmas();
		firmas = firmasService.findByIdSector(this.getUserDetails().getIdSector());

//		if (null != firmas) {
//			
//			this.setbModificar(Boolean.FALSE);
//			this.setbVModificar(Boolean.TRUE);
//			this.setbBorrar(Boolean.FALSE);
//			this.setbSave(Boolean.FALSE);
//			this.setbLbl(Boolean.TRUE);
//			this.setBtxt(Boolean.FALSE);
//		} else {
//			this.addElement();
//			
//			this.setbModificar(Boolean.FALSE);
//			this.setbVModificar(Boolean.TRUE);
//			this.setbBorrar(Boolean.TRUE);
//			this.setbSave(Boolean.FALSE);
//			this.setbLbl(Boolean.TRUE);
//			this.setBtxt(Boolean.FALSE);
//		}

		if (this.getUserDetails().getIdSector() == 1) {
			titulo = "Nombre Municipio";
		} else {
			titulo = "Titulo";
		}
		//this.getFielsValue();

	}

	

	/**
	 * Save.
	 */
	public void save() {
		this.getFirmasService().save(firmas);
		
		this.setbModificar(Boolean.FALSE);
		this.setbVModificar(Boolean.TRUE);
		this.setbBorrar(Boolean.FALSE);
		this.setbSave(Boolean.FALSE);
		this.setbLbl(Boolean.TRUE);
		this.setBtxt(Boolean.FALSE);
		this.setbCancel(Boolean.TRUE);
		this.setbLimpiar(Boolean.TRUE);

	}

	/**
	 * Modify.
	 */
	public void modify() {
		
		this.setbModificar(Boolean.FALSE);
		this.setbVModificar(Boolean.FALSE);
		this.setbBorrar(Boolean.FALSE);
		this.setbSave(Boolean.TRUE);
		this.setbLbl(Boolean.FALSE);
		this.setBtxt(Boolean.TRUE);
		this.setbCancel(Boolean.FALSE);
		this.setbLimpiar(Boolean.FALSE);
		this.getFirmasService().modify(firmas);
	}

	/**
	 * Delete.
	 */
	public void delete() {
		this.getFirmasService().delete(firmas);
	}

	/**
	 * Cancel.
	 */
	public void cancel() {
		
		this.setbModificar(Boolean.FALSE);
		this.setbVModificar(Boolean.TRUE);
		this.setbBorrar(Boolean.TRUE);
		this.setbSave(Boolean.FALSE);
		this.setbLbl(Boolean.TRUE);
		this.setBtxt(Boolean.FALSE);
		this.setbCancel(Boolean.TRUE);
		this.setbLimpiar(Boolean.TRUE);
	}

	/**
	 * Clean.
	 */
	public void clean() {
		firmas = firmasService.findByIdSector(this.getUserDetails().getIdSector());
		if(null == firmas) {
			firmas = firmasService.addElements(this.getUserDetails().getIdSector());
		}
	}

	/** The parameters. */
	Map<String, Object> parameters = null;
	
	/** The key name. */
	String keyName;
	
	/** The index. */
	int index = 0;
	
	/** The n. */
	int n = 0;
	
	/** The l. */
	int l = 0;
	
	/** The pos N. */
	int posN = 0;
	
	/** The pos L. */
	int posL = 0;
	
	/** The dto. */
	FirmasDTO dto;

	/**
	 * Gets the fiels value.
	 *
	 * @return the fiels value
	 */
	public void getFielsValue() {
		lisDTO = new ArrayList<FirmasDTO>();
		firmas = firmasService.findByIdSector(this.getUserDetails().getIdSector());
		index = 0;
		dto = new FirmasDTO();
		n = 1;
		l = 1;
		try {
			parameters = getFieldNamesAndValues(firmas, Boolean.FALSE);
			Iterator<String> iterator = parameters.keySet().iterator();
			while (iterator.hasNext()) {
				keyName = iterator.next();
				if (!keyName.matches(FIELDS_NAME)) {

					if (keyName.substring(0, 1). equalsIgnoreCase("N")) {
						dto.setLabel(parameters.get(keyName).toString());

						lisDTO.add(posN, dto);
						n++;
						posN++;
					} else if (keyName.substring(0, 1).equals("L")) {
						dto.setName(parameters.get(keyName).toString());
						lisDTO.set(posL, dto);
						posL++;
						l++;

					}

				}
				index++;

			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
