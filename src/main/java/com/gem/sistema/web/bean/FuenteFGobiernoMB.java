package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.FuentefGobierno;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FuenteFGobiernoRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "fuenteFGobiernoMB")
@ViewScoped
public class FuenteFGobiernoMB extends BaseDirectReport {

	private List<FuentefGobierno> fuentes = new ArrayList<>();
	private String busqueda = "";
	private Boolean editable = false;
	private FuentefGobierno selected = new FuentefGobierno();

	@ManagedProperty("#{fuenteFGobiernoRepository}")
	private FuenteFGobiernoRepository fuenteFGobiernoRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "fuentesfGobierno";
		endFilename = jasperReporteName + ".pdf";
		buscar();
	}

	public void buscar() {
		if (busqueda == null || busqueda.equals("")) {
			fuentes = fuenteFGobiernoRepository.findAll();
		} else {
			fuentes = fuenteFGobiernoRepository.findAll().stream()
					.filter(p -> p.busquedaSimple().toLowerCase().contains(busqueda.toLowerCase()))
					.collect(Collectors.toList());
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		parameters.put("anio", conctb.getAnoemp());
		parameters.put("pathImage1", conctb.getImagePathLeft());
		parameters.put("pathImage2", conctb.getImagePathRigth());
		parameters.put("entidadName", conctb.getNomDep());

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void use() {
		if (selected != null && selected.getId() > 0) {
			selected.setUsed(!selected.getUsed());
			selected = fuenteFGobiernoRepository.save(selected);
			fuentes = fuentes.stream().map(f -> f.getId().equals(selected.getId()) ? selected : f)
					.collect(Collectors.toList());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					StringUtils.EMPTY, "Se a actualizado la fuente de financiamiento correctamente"));
		}

	}

	public List<FuentefGobierno> getFuentes() {
		return fuentes;
	}

	public void setFuentes(List<FuentefGobierno> fuentes) {
		this.fuentes = fuentes;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public FuenteFGobiernoRepository getFuenteFGobiernoRepository() {
		return fuenteFGobiernoRepository;
	}

	public void setFuenteFGobiernoRepository(FuenteFGobiernoRepository fuenteFGobiernoRepository) {
		this.fuenteFGobiernoRepository = fuenteFGobiernoRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public FuentefGobierno getSelected() {
		return selected;
	}

	public void setSelected(FuentefGobierno selected) {
		this.selected = selected;
	}

}
