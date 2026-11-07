package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

public abstract class ReporteComparativo extends BaseDirectReport {

	protected Boolean showMesAnterior = false;
	protected Integer mesFinalAnterior = 12;
	protected Integer mesFinalActual = 1;
	protected List<Integer> listMes = new ArrayList<Integer>();
	protected Conctb conctb = new Conctb();

	@ManagedProperty(value = "#{tcPeriodoRepositoy}")
	protected TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty(value = "#{conctbRepository}")
	protected ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	protected PuestosFirmasService puestosFirmasService;

	protected void initAll() {
		periodoRepositoy.findByTipoPeriodo(1).forEach(p -> {
			listMes.add(p.getPeriodo());
		});
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
	}

	protected Integer getLastDayActual() {
		return getLastDayByAnoEmp(getMesFinalActual(), getAnio());
	}

	protected Integer getLastDayAnterior() {
		return getLastDayByAnoEmp(getMesFinalAnterior(), getAnioAnterior());
	}

	protected Integer getAnio() {
		return conctb.getAnoemp();
	}

	protected Integer getAnioAnterior() {
		return conctb.getAnoemp() - 1;
	}

	/** Optiene el nombre del mes actual a */
	protected String getNombreMesAnterior() {
		return periodoRepositoy.findByTipoPeriodoAndPeriodo(1, getMesFinalAnterior()).getDescripcion();
	}

	/** Optiene el nombre del mes anterior */
	protected String getNombreMesActual() {
		return periodoRepositoy.findByTipoPeriodoAndPeriodo(1, getMesFinalActual()).getDescripcion();
	}

	protected String getFechaForReport() {
		String fecha = "";
		fecha = "DEL " + getLastDayAnterior() + " DE " + getNombreMesAnterior() + " DEL " + getAnioAnterior() + " AL "
				+ getLastDayActual() + " DE " + getNombreMesActual() + " DEL " + getAnio();
		return fecha.toUpperCase();
	}

	protected Map<String, Object> getDefaultParams() {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = new TrPuestoFirma();
		parameters.put("pFecha", getFechaForReport());
		parameters.put("pAnio", getAnio());
		parameters.put("pAnioAnterior", getAnioAnterior());

		parameters.put("pImg1", conctb.getImagePathLeft());
		parameters.put("pImg2", conctb.getImagePathRigth());
		parameters.put("pNombreEntidad", conctb.getNomDep());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F08.getValue());
		parameters.put("pL1", firma.getPuesto().getPuesto());
		parameters.put("pN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F09.getValue());
		parameters.put("pL2", firma.getPuesto().getPuesto());
		parameters.put("pN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F10.getValue());
		parameters.put("pL3", firma.getPuesto().getPuesto());
		parameters.put("pN3", firma.getNombre());
		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean getShowMesAnterior() {
		return showMesAnterior;
	}

	public void setShowMesAnterior(Boolean showMesAnterior) {
		this.showMesAnterior = showMesAnterior;
	}

	public Integer getMesFinalAnterior() {
		return mesFinalAnterior;
	}

	public void setMesFinalAnterior(Integer mesFinalAnterior) {
		this.mesFinalAnterior = mesFinalAnterior;
	}

	public Integer getMesFinalActual() {
		return mesFinalActual;
	}

	public void setMesFinalActual(Integer mesFinalActual) {
		this.mesFinalActual = mesFinalActual;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public List<Integer> getListMes() {
		return listMes;
	}

	public void setListMes(List<Integer> listMes) {
		this.listMes = listMes;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
