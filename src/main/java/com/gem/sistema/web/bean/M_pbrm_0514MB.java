package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 * @Date 06-04-2021
 * @Changes Se ajusta reporte y componentes a nuevos lineamientos <br>
 *          Se implementa servicios de firmas
 * @Sector Municipal
 */
@ManagedBean
@ViewScoped
public class M_pbrm_0514MB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_TABULADOR_SUELDOS";
	private Integer mes;

	/** The conctb. */
	private Conctb conctb;

	/** The list mes. */
	private List<TcPeriodo> listMes;

	/** The contb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository contbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the contb repository.
	 *
	 * @return the contb repository
	 */
	public ConctbRepository getContbRepository() {
		return contbRepository;
	}

	/**
	 * Sets the contb repository.
	 *
	 * @param contbRepository the new contb repository
	 */
	public void setContbRepository(ConctbRepository contbRepository) {
		this.contbRepository = contbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "m_pbrm_0514";
		endFilename = jasperReporteName + ".pdf";

		listMes = periodoRepositoy.findByTipoPeriodo(3);

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getPeriodo();
		}

	}

	/** The parametros. */
	Map<String, Object> parametros;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		parametros = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		conctb = contbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, (mes * 3));

		endFilename = getNameFileReport(mes, conctb, reporte);

		parametros.put("year", conctb.getAnoemp());
		parametros.put("mes", mes * 3);
		parametros.put("mesName", periodo.getDescripcion().toUpperCase());
		parametros.put("lastDayOfMonth", getLastDayByAnoEmp((mes * 3), conctb.getAnoemp()));
		parametros.put("pathImage1", conctb.getImagePathLeft());
		parametros.put("pathImage2", conctb.getImagePathRigth());
		parametros.put("municipioName", conctb.getNomDep().toUpperCase());
		parametros.put("municipioNo", StringUtils.EMPTY);
		parametros.put("idSector", sector);
		parametros.put("tipoOrganismo", StringUtils.EMPTY);
		parametros.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parametros;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<TcPeriodo> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcPeriodo> listMes) {
		this.listMes = listMes;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public TcEntidadAdministrativaRepository getEntidadAdministrativaRepository() {
		return entidadAdministrativaRepository;
	}

	public void setEntidadAdministrativaRepository(TcEntidadAdministrativaRepository entidadAdministrativaRepository) {
		this.entidadAdministrativaRepository = entidadAdministrativaRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
