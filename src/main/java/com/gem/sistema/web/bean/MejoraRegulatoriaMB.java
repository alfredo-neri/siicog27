package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getDateSystem;
import static com.roonin.utils.UtilDate.getLastDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.dto.CompetenciaLaboralGenericDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.CompetenciasLaboralesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.CreateFileUtils;
import com.google.gson.Gson;

/**
 * @author Alfredo Neri
 *
 */
@ManagedBean(name = "mejoraRegulatoriaMB")
@ViewScoped
public class MejoraRegulatoriaMB extends BaseDirectReport {

	private static final String TABLE_NAME = "MEJORA";
	private static final String JASPER_NAME = "CompetenciaLaboral";
	private String COMPETENCIA_NAME = "Coordinador General Municipal de Mejora Regulatoria";
	private String TITLE_LABEL = "Título Profesional";

	private List<TcPeriodo> listSemestres;
	private List<CompetenciaLaboralGenericDTO> listMejora;
	private Integer semestre;
	private CompetenciaLaboralGenericDTO mejoraSelected;

	private boolean editando = false;
	private boolean bAdicionar = false;
	private boolean bModificar = false;
	private boolean bBorrar;
	private boolean bCancelar;
	private int currentPage = 0;
	private Boolean isNew = null;

	@ManagedProperty("#{competenciasLaboralesService}")
	private CompetenciasLaboralesService competenciasLaboralesService;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@PostConstruct
	public void init() {

		jasperReporteName = JASPER_NAME;
		endFilename = jasperReporteName + ".pdf";

		listMejora = competenciasLaboralesService.findAll(TABLE_NAME);
		listSemestres = periodoRepository.findByTipoPeriodoOrderByPeriodo(6);
		bBorrar = false;

		if (CollectionUtils.isNotEmpty(listSemestres)) {
			semestre = listSemestres.get(0).getPeriodo();
		}

		if (CollectionUtils.isEmpty(listMejora))
			bBorrar = true;
		bModificar = true;
		if (CollectionUtils.isNotEmpty(listMejora)) {
			mejoraSelected = listMejora.get(0);
			semestre = mejoraSelected.getSemestre();
			bModificar = false;
		}
		editando = false;
		currentPage = 0;
		bCancelar = true;

		this.addElement();
		blockBtnAdicionar();
	}

	public void blockBtnAdicionar() {

		if (listMejora.size() >= 2) {
			bAdicionar = false;
		} else {
			bAdicionar = true;
		}
	}

	public void salvar() {
		CompetenciaLaboralGenericDTO nuevo = listMejora.get(currentPage);

		nuevo.setIdanio(0);
		nuevo.setIdref(0);
		nuevo.setIdsector(this.getUserDetails().getIdSector());
		nuevo.setCapturo(this.getUserDetails().getUsername());
		bModificar = true;
		if (isNew) {
			Integer existe = this.competenciasLaboralesService.countSemestres(TABLE_NAME, nuevo.getSemestre());

			if (editando && this.notNull(nuevo) && existe == 0) {
				editando = false;

				this.competenciasLaboralesService.save(TABLE_NAME, nuevo);
				listMejora = this.competenciasLaboralesService.findAll(TABLE_NAME);
				bModificar = false;
				bCancelar = true;
				bBorrar = false;

				blockBtnAdicionar();
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se ha guardado con exito el registro");
			} else {
				nuevo.setFechaing(StringUtils.EMPTY);
				generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!",
						"Ya existe un registro con el semestre " + nuevo.getSemestre());
			}
		} else if (!isNew) {
			this.notNull(nuevo);
			this.competenciasLaboralesService.update(TABLE_NAME, nuevo);
			this.listMejora = this.competenciasLaboralesService.findAll(TABLE_NAME);
			bModificar = false;
			editando = false;
			bCancelar = true;
			bBorrar = false;

			blockBtnAdicionar();
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "!Se ha modificado con exito el registro!");
		}
	}

	public void modificar() {
		mejoraSelected = listMejora.get(currentPage);
		listMejora.set(currentPage, mejoraSelected);

		editando = true;
		bCancelar = false;
		isNew = false;
		bBorrar = true;
	}

	public void reset() {
		listMejora = competenciasLaboralesService.findAll(TABLE_NAME);
		if (CollectionUtils.isEmpty(listMejora)) {
			listMejora.add(new CompetenciaLaboralGenericDTO());
		}
		editando = false;
		bBorrar = false;
		bCancelar = true;
	}

	public void clean() {
		if (listMejora.get(currentPage).getIdrow() == 0) {

			listMejora.get(currentPage).setSemestre(listSemestres.get(0).getPeriodo());
			listMejora.get(currentPage).setExperiencia(StringUtils.EMPTY);
			listMejora.get(currentPage).setFechaing(StringUtils.EMPTY);
			listMejora.get(currentPage).setNombre(StringUtils.EMPTY);
			listMejora.get(currentPage).setCargo(StringUtils.EMPTY);
			listMejora.get(currentPage).setCertificacion(0);
			listMejora.get(currentPage).setAntecedentes(0);
			listMejora.get(currentPage).setConstancia(0);
			listMejora.get(currentPage).setDiplomado(0);
			listMejora.get(currentPage).setIssemym(0);
			listMejora.get(currentPage).setTitulo(0);

		} else {
			listMejora = this.competenciasLaboralesService.findAll(TABLE_NAME);
		}

	}

	public void adicionar() {

		if (StringUtils.EMPTY == listMejora.get(0).getFechaing()) {
			for (int i = 0; i < listMejora.size(); i++) {
				listMejora.remove(i);
			}
		}

		listMejora.add(new CompetenciaLaboralGenericDTO(this.getUserDetails().getUsername(),
				this.getUserDetails().getIdSector()));

		editando = true;
		bCancelar = false;
		bBorrar = true;
		isNew = true;
		if (listMejora.size() == 2) {
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').paginator.setPage(" + (listMejora.size() - 1) + ");");

		}
		if (listMejora.size() > 2) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Solo puede agregar 2 semestres.");
			return;
		}
	}

	public void borrar() {
		if (listMejora.size() == 1) {
			currentPage = 0;
		}

		mejoraSelected = listMejora.get(currentPage);

		this.competenciasLaboralesService.deleteRow(TABLE_NAME, mejoraSelected.getIdrow());
		listMejora = this.competenciasLaboralesService.findAll(TABLE_NAME);

		if (this.listMejora.isEmpty()) {
			this.listMejora.add(new CompetenciaLaboralGenericDTO());
			if (StringUtils.isEmpty(listMejora.get(0).getFechaing()))
				bBorrar = true;
			bModificar = true;
		}

		this.actualizaSeleccionado();
		this.blockBtnAdicionar();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El registro se eliminó de manera correcta");
	}

	public void cambiarPagina(PageEvent event) {
		currentPage = event.getPage();
		actualizaSeleccionado();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		Integer mesFinal = semestre * 6;
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Conctb conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());
		TcPeriodo periodo = periodoRepository.findByTipoPeriodoAndPeriodo(1, mesFinal);

		parameters.put("pNombreMunicipio", firmas.getCampo1());
		parameters.put("pNumMunicipio", conctb.getClave().substring(1));
		parameters.put("pDiaUltimo", getLastDay(mesFinal));
		parameters.put("pNombreMes", periodo.getDescripcion().toUpperCase());
		parameters.put("pAnio", conctb.getAnoemp());
		parameters.put("pLogo", getUserDetails().getPathImgCab1());
		parameters.put("pFirmaLabel", firmas.getL38());
		parameters.put("pFirmaName", firmas.getN38());
		parameters.put("competenciaName", COMPETENCIA_NAME.toUpperCase());
		parameters.put("titleLabel", TITLE_LABEL);

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void generatePDF() {

		CompetenciaLaboralGenericDTO dto = null;

		for (CompetenciaLaboralGenericDTO genericDTO : listMejora) {
			if (genericDTO.getSemestre() == semestre) {
				dto = genericDTO;
				break;
			}
		}

		if (dto != null) {
			Gson gson = new Gson();
			String jsonString = " {\"competencia\":[" + gson.toJson(dto) + "]}";
			String jsonName = TABLE_NAME + UUID.randomUUID() + ".json";
			createFilePdfInlineJson(CreateFileUtils.createJsonFile(jsonName, jsonString));
		} else {
			uuid = null;
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Warning!",
					"No existe información del semestre: " + semestre);
		}

	}

	private boolean notNull(CompetenciaLaboralGenericDTO dto) {
		if (null == dto.getFechaing())
			dto.setFechaing(getDateSystem().toString());

		if (null == dto.getTitulo())
			dto.setTitulo(0);

		if (null == dto.getCertificacion())
			dto.setCertificacion(0);

		if (null == dto.getIssemym())
			dto.setIssemym(0);

		if (null == dto.getConstancia())
			dto.setConstancia(0);

		if (null == dto.getAntecedentes())
			dto.setAntecedentes(0);
		
		if (null == dto.getDiplomado())
			dto.setDiplomado(0);

		if (null == dto.getExperiencia())
			dto.setExperiencia(StringUtils.EMPTY);

		return true;
	}

	private void actualizaSeleccionado() {
		if (CollectionUtils.isNotEmpty(listMejora)) {

			mejoraSelected = listMejora.get(listMejora.size() - 1);
			semestre = mejoraSelected.getSemestre();
		}

	}

	public void addElement() {
		CompetenciaLaboralGenericDTO dto = new CompetenciaLaboralGenericDTO();
		dto.setCapturo(this.getUserDetails().getUsername());
		dto.setIdsector(this.getUserDetails().getIdSector());
		salida: for (CompetenciaLaboralGenericDTO generic : listMejora) {
			if (StringUtils.isEmpty(generic.getFechaing())) {
				listMejora.remove(generic);
				break salida;
			}
		}
		if (CollectionUtils.isEmpty(listMejora)) {

			listMejora = new ArrayList<CompetenciaLaboralGenericDTO>();
			listMejora.add(0, dto);
		}
	}

	public List<TcPeriodo> getListSemestres() {
		return listSemestres;
	}

	public void setListSemestres(List<TcPeriodo> listSemestres) {
		this.listSemestres = listSemestres;
	}

	public List<CompetenciaLaboralGenericDTO> getlistMejora() {
		return listMejora;
	}

	public void setlistMejora(List<CompetenciaLaboralGenericDTO> listMejora) {
		this.listMejora = listMejora;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public CompetenciaLaboralGenericDTO getmejoraSelected() {
		return mejoraSelected;
	}

	public void setmejoraSelected(CompetenciaLaboralGenericDTO mejoraSelected) {
		this.mejoraSelected = mejoraSelected;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public boolean isbAdicionar() {
		return bAdicionar;
	}

	public void setbAdicionar(boolean bAdicionar) {
		this.bAdicionar = bAdicionar;
	}

	public boolean isbModificar() {
		return bModificar;
	}

	public void setbModificar(boolean bModificar) {
		this.bModificar = bModificar;
	}

	public boolean isbBorrar() {
		return bBorrar;
	}

	public void setbBorrar(boolean bBorrar) {
		this.bBorrar = bBorrar;
	}

	public boolean isbCancelar() {
		return bCancelar;
	}

	public void setbCancelar(boolean bCancelar) {
		this.bCancelar = bCancelar;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public CompetenciasLaboralesService getCompetenciasLaboralesService() {
		return competenciasLaboralesService;
	}

	public void setCompetenciasLaboralesService(CompetenciasLaboralesService competenciasLaboralesService) {
		this.competenciasLaboralesService = competenciasLaboralesService;
	}

	public TcPeriodoRepositoy getPeriodoRepository() {
		return periodoRepository;
	}

	public void setPeriodoRepository(TcPeriodoRepositoy periodoRepository) {
		this.periodoRepository = periodoRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	public String getCOMPETENCIA_NAME() {
		return COMPETENCIA_NAME;
	}

	public String getTITLE_LABEL() {
		return TITLE_LABEL;
	}
}
