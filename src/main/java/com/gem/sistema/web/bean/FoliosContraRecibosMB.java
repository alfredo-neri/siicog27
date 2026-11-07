package com.gem.sistema.web.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.FolioContrarecibo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FoliosContrareciboRepository;

@ManagedBean(name = "foliosContraRecibosMB")
@ViewScoped
public class FoliosContraRecibosMB extends AbstractMB {

	private FolioContrarecibo folio;

	@ManagedProperty("#{foliosContrareciboRepository}")
	private FoliosContrareciboRepository foliosContrareciboRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	private Conctb conctb = null;
	private Boolean editing = false;

	@PostConstruct
	public void init() {
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		folio = foliosContrareciboRepository.findTopByAnio(conctb.getAnoemp());
		if (folio == null) {
			folio = new FolioContrarecibo(conctb.getAnoemp(), 0, 0, "");
			foliosContrareciboRepository.save(folio);
		}
	}

	/** Guarda un Recibo */
	public void guardarFolio() {
		List<String> errors = folio.isValid();
		if (errors.isEmpty()) {
			try {

				boolean modificado = folio.getId() != null && folio.getId() > 0;
				folio.setFolioActual(folio.getFolioInicial());
				folio = foliosContrareciboRepository.save(folio);
				displayInfoMessage("Se ha " + (!modificado ? "agregado" : "modificado ") + " correctamente el folio");
				cancelarEdicion();
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrrido un error al guardar el folio");
			}
		} else {
			errors.forEach(this::displayInfoMessage);
		}
	}

	public void editar() {
		this.editing = true;
	}

	public void cancelarEdicion() {
		folio = foliosContrareciboRepository.findOne(folio.getId());
		this.editing = false;

	}

	public FolioContrarecibo getFolio() {
		return folio;
	}

	public void setFolio(FolioContrarecibo folio) {
		this.folio = folio;
	}

	public FoliosContrareciboRepository getFoliosContrareciboRepository() {
		return foliosContrareciboRepository;
	}

	public void setFoliosContrareciboRepository(FoliosContrareciboRepository foliosContrareciboRepository) {
		this.foliosContrareciboRepository = foliosContrareciboRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public Boolean getEditing() {
		return editing;
	}

	public void setEditing(Boolean editing) {
		this.editing = editing;
	}

}
