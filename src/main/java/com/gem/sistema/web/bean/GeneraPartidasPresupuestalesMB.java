package com.gem.sistema.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesService;
import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.domain.GenericCatalog;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneraPartidasPresupuestalesMB.
 */
@ManagedBean(name = "genPartPresMB")
@ViewScoped
public class GeneraPartidasPresupuestalesMB extends AbstractMB {

	/** The Constant FOCUS_IN_PROGRAMA. */
	private static final String FOCUS_IN_PROGRAMA = "PrimeFaces.focus('generaPartidasPresup:programa');";
	
	/** The Constant FOCUS_IN_PARTIDA. */
	private static final String FOCUS_IN_PARTIDA = "PrimeFaces.focus('generaPartidasPresup:partida');";
	
	/** The f 1 pressed. */
	private boolean f1Pressed = false;
	
	/** The dep id. */
	private String depId;
	
	/** The dep desc. */
	private String depDesc;
	
	/** The prog id. */
	private String progId;
	
	/** The prog desc. */
	private String progDesc;
	
	/** The part id. */
	private String partId;
	
	/** The part desc. */
	private String partDesc;
	
	/** The current catalog. */
	private String currentCatalog;

	/** The catalog items. */
	private List<GenericCatalog<String>> catalogItems;
	
	/** The selected item. */
	private GenericCatalog<String> selectedItem;

	/** The genera partidas presupuestales service. */
	@ManagedProperty("#{generaPartidasPresupuestalesService}")
	private GeneraPartidasPresupuestalesService generaPartidasPresupuestalesService;

	/**
	 * Sets the catalog items.
	 *
	 * @param catalogItems the new catalog items
	 */
	public void setCatalogItems(List<GenericCatalog<String>> catalogItems) {
		getCatalogItems().clear();
		getCatalogItems().addAll(catalogItems);
	}

	/**
	 * Gets the catalog items.
	 *
	 * @return the catalog items
	 */
	public List<GenericCatalog<String>> getCatalogItems() {
		if (catalogItems == null) {
			catalogItems = new ArrayList<GenericCatalog<String>>();
		}
		return catalogItems;
	}

	/**
	 * Gets the selected item.
	 *
	 * @return the selected item
	 */
	public GenericCatalog<String> getSelectedItem() {
		return this.selectedItem;
	}

	/**
	 * Sets the selected item.
	 *
	 * @param selectedItem the new selected item
	 */
	public void setSelectedItem(GenericCatalog<String> selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * Gets the dep id.
	 *
	 * @return the dep id
	 */
	public String getDepId() {
		return (depId == null ? "" : depId).toUpperCase();
	}

	/**
	 * Sets the dep id.
	 *
	 * @param depId the new dep id
	 */
	public void setDepId(String depId) {
		this.depId = depId;
	}

	/**
	 * Gets the dep desc.
	 *
	 * @return the dep desc
	 */
	public String getDepDesc() {
		return depDesc;
	}

	/**
	 * Sets the dep desc.
	 *
	 * @param depDesc the new dep desc
	 */
	public void setDepDesc(String depDesc) {
		this.depDesc = depDesc;
	}

	/**
	 * Gets the prog id.
	 *
	 * @return the prog id
	 */
	public String getProgId() {
		return progId == null ? "" : progId;
	}

	/**
	 * Sets the prog id.
	 *
	 * @param progId the new prog id
	 */
	public void setProgId(String progId) {
		this.progId = progId;
	}

	/**
	 * Gets the prog desc.
	 *
	 * @return the prog desc
	 */
	public String getProgDesc() {
		return progDesc;
	}

	/**
	 * Sets the prog desc.
	 *
	 * @param progDesc the new prog desc
	 */
	public void setProgDesc(String progDesc) {
		this.progDesc = progDesc;
	}

	/**
	 * Gets the part id.
	 *
	 * @return the part id
	 */
	public String getPartId() {
		return partId == null ? "" : partId;
	}

	/**
	 * Sets the part id.
	 *
	 * @param partId the new part id
	 */
	public void setPartId(String partId) {
		this.partId = partId;
	}

	/**
	 * Gets the part desc.
	 *
	 * @return the part desc
	 */
	public String getPartDesc() {
		return partDesc;
	}

	/**
	 * Sets the part desc.
	 *
	 * @param partDesc the new part desc
	 */
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}

	/**
	 * Gets the show columns.
	 *
	 * @return the show columns
	 */
	public Boolean getShowColumns() {
		if (getCatalogItems().isEmpty())
			return false;
		return getCatalogItems().get(0).getAttrs().containsKey("clvfin");
	}

	/**
	 * Gets the current catalog.
	 *
	 * @return the current catalog
	 */
	public String getCurrentCatalog() {
		return currentCatalog;
	}

	/**
	 * Sets the current catalog.
	 *
	 * @param currentCatalog the new current catalog
	 */
	public void setCurrentCatalog(String currentCatalog) {
		this.currentCatalog = currentCatalog;
	}

	/**
	 * Sets the genera partidas presupuestales service.
	 *
	 * @param generaPartidasPresupuestalesService the new genera partidas presupuestales service
	 */
	public void setGeneraPartidasPresupuestalesService(
			GeneraPartidasPresupuestalesService generaPartidasPresupuestalesService) {
		this.generaPartidasPresupuestalesService = generaPartidasPresupuestalesService;
	}

	/**
	 * Gets the genera partidas presupuestales service.
	 *
	 * @return the genera partidas presupuestales service
	 */
	public GeneraPartidasPresupuestalesService getGeneraPartidasPresupuestalesService() {
		return generaPartidasPresupuestalesService;
	}

	/**
	 * Search dependencies.
	 */
	public void searchDependencies() {
		setSelectedItem(null);
		f1Pressed = true;
		setCatalogItems(generaPartidasPresupuestalesService.getDependencies(getDepId(),
				getIdSectorForCurrentUser().intValue()));
	}

	/**
	 * Gets the programs.
	 *
	 * @return the programs
	 */
	public void getPrograms() {
		setSelectedItem(null);
		f1Pressed = true;
		setCatalogItems(generaPartidasPresupuestalesService.getPrograms(getIdSectorForCurrentUser().intValue()));
	}

	/**
	 * Gets the parts.
	 *
	 * @return the parts
	 */
	public void getParts() {
		setSelectedItem(null);
		f1Pressed = true;
		setCatalogItems(generaPartidasPresupuestalesService.getParts(getIdSectorForCurrentUser().intValue()));
	}

	/**
	 * Validate dep.
	 */
	public void validateDep() {
		if (!StringUtils.isEmpty(getDepId()) && !f1Pressed) {
			Catdep catdep = generaPartidasPresupuestalesService.findDependency(getIdSectorForCurrentUser().intValue(),
					getDepId());
			if (catdep == null) {
				displayErrorMessage("No se encuentra la dependencia");
			} else {
				setDepDesc(catdep.getNomdep());
				RequestContext.getCurrentInstance().execute(FOCUS_IN_PROGRAMA);
			}
		}
		f1Pressed = false;	
	}

	/**
	 * Complete dep.
	 */
	public void completeDep(){
		depId = StringUtils.rightPad(depId, 10, "0");
	}
	
	/**
	 * Validate prog.
	 */
	public void validateProg() {
		if (!StringUtils.isEmpty(getDepId()) && !f1Pressed) {
			String programa = getProgId();
			String clvfin = StringUtils.right(programa, 3);
			String clvfun = StringUtils.removeEnd(programa, clvfin);
			Xcatpro xcatpro = generaPartidasPresupuestalesService
					.findFirstXcatpro(getIdSectorForCurrentUser().intValue(), getDepId(), clvfun, clvfin);
			if (xcatpro == null) {
				displayErrorMessage("No existe el programa");
			} else {
				setProgDesc(xcatpro.getNompro());
				RequestContext.getCurrentInstance().execute(FOCUS_IN_PARTIDA);
			}
		}
		f1Pressed = false;
	}

	/**
	 * Validate part.
	 */
	public void validatePart() {
		if (!StringUtils.isEmpty(getDepId()) && !f1Pressed) {
			// if("000".equals(StringUtils.substring(getPartId(), 1, 4)) ||
			// "00".equals(StringUtils.substring(getPartId(), 2, 4))){ //
			// validar solo que termine en un 0
			if (getPartId().endsWith("0")) {
				displayErrorMessage("Partida no de último nivel");
			}
			Natgas natgas = generaPartidasPresupuestalesService.findFirstNatgas(getPartId(),
					getIdSectorForCurrentUser().intValue());
			if (natgas == null) {
				displayErrorMessage("No existe esta partida");
			} else {
				setPartDesc(natgas.getNomgas());
			}
		}
		f1Pressed = false;
	}

	/**
	 * Guardar item.
	 */
	public void guardarItem() {
		GenericCatalog<String> cat = getSelectedItem();
		if (cat != null) {
			if ("dep".equals(getCurrentCatalog())) {
				setDepId(cat.getId());
				setDepDesc(cat.getName());
				RequestContext.getCurrentInstance().execute(FOCUS_IN_PROGRAMA);
			} else if ("prog".equals(getCurrentCatalog())) {
				setProgId(cat.getAttrs().get("clvfun") + "" + cat.getAttrs().get("clvfin"));
				setProgDesc(cat.getName());
				RequestContext.getCurrentInstance().execute(FOCUS_IN_PARTIDA);
			} else if ("part".equals(getCurrentCatalog())) {
				setPartId(cat.getId());
				setPartDesc(cat.getName());
			}
		}
		f1Pressed = false;
	}

	/**
	 * Process.
	 */
	public void process() {

		if (getPartId().endsWith("0")) {
			displayErrorMessage("Partida no es de último nivel");
		} else {
			generaPartidasPresupuestalesService.process(getIdSectorForCurrentUser(), getDepId(), getProgId(),
					getPartId(),this.getUserDetails().getUsername(), (msg) -> {
						displayErrorMessage(msg);
					});
			displayInfoMessage("Proceso concluido");
			depId = null;
			depDesc = null;
			progId = null;
			progDesc = null;
			partId = null;
			partDesc = null;
			currentCatalog = null;
		}

	}
}
