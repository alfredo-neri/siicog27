package com.gem.sistema.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

@ViewScoped
@ManagedBean(name = "cambioFechaMB")
public class CambioFechaMB extends AbstractMB {

	private List<TcPeriodo> listMeses;
	private Polien polien;
	private String minDate;
	private String maxDate;
	private String tippol;
	private Integer mespol;
	private Integer conpol;
	private Integer anopol;
	private boolean rendered;

	@ManagedProperty("#{polienRepository}")
	private PolienRepository polienRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@PostConstruct
	public void init() {
		rendered = false;
		polien = new Polien();
		anopol = conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp();
		listMeses = periodoRepositoy.findByTipoPeriodo(1);
		if (!listMeses.isEmpty()) {
			mespol = listMeses.get(0).getPeriodo();
		}
	}

	public void obtenerPolien() {

		rendered = true;
		polien = polienRepository.findByMespolAndTippolAndConpolAndIdsector(mespol, tippol, conpol,
				this.getUserDetails().getIdSector());

		if (polien == null) {
			rendered = false;
			polien = new Polien();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"La Póliza no existe");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else if (!polien.getCappol().equals(this.getUserDetails().getUsername())) {
			rendered = false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Usuario no autorizado para modificar póliza");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} else {
			Integer lastDay = getLastDayByAnoEmp(polien.getMespol(), polien.getAnopol());
			String anio = polien.getAnopol().toString().substring(2);
			minDate = "1/" + polien.getMespol() + "/" + anio;
			maxDate = lastDay + "/" + polien.getMespol() + "/" + anio;
		}
		RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
	}

	public void guardarCambios() {

		rendered = false;
		conpol = null;
		RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		polienRepository.save(polien);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
				"Fecha modificada con éxito.");

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public Polien getPolien() {
		return polien;
	}

	public void setPolien(Polien polien) {
		this.polien = polien;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public String getTippol() {
		return tippol;
	}

	public void setTippol(String tippol) {
		this.tippol = tippol;
	}

	public Integer getMespol() {
		return mespol;
	}

	public void setMespol(Integer mespol) {
		this.mespol = mespol;
	}

	public Integer getConpol() {
		return conpol;
	}

	public void setConpol(Integer conpol) {
		this.conpol = conpol;
	}

	public Integer getAnopol() {
		return anopol;
	}

	public void setAnopol(Integer anopol) {
		this.anopol = anopol;
	}

	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

}
