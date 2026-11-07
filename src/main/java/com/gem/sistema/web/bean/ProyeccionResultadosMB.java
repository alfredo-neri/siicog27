package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getDateSystem;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TwProyeccionResultado;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.ProyeccionResultadoService;
import com.gem.sistema.web.datamodel.DataModelGeneric;

@ManagedBean(name = "proyeccionResultadosMB")
@ViewScoped
public class ProyeccionResultadosMB extends AbstractMB {

	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-pencil').eq(";

	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";

	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:object:%1$s:trim');";

	private static final String UPDATE_OBJETS = "jQuery('#form1\\\\:hiddenUpdate').click();";

	private DataModelGeneric<TwProyeccionResultado> dataProyRes;

	private List<TwProyeccionResultado> listProyRes;

	private Integer trimestre;

	private TwProyeccionResultado proyResSelected;

	private Conctb conctb;
	private String tipo;
	private Integer year1;
	private Integer year2;
	private Integer year3;
	private Integer year4;
	private Integer year5;
	private Integer currentYear;

	private Integer currentIndex;

	private Integer oldTrimestre;

	private Long oldConsecutivo;

	private Boolean bEdition;

	private Boolean bModifcar;

	private Boolean bAdicionar;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{proyeccionResultadoService}")
	private ProyeccionResultadoService service;

	@PostConstruct
	public void init() {
		conctb = conctbRepository.findByIdsectorAndIdRef(this.getUserDetails().getIdSector(), 0);

		this.tipo = "P";
		this.currentYear = conctb.getAnoemp();
		this.refreshData();
		this.bAdicionar = Boolean.FALSE;
	}

	public void refreshData() {
		
		if(tipo.equals("P")) {
			year1 = currentYear + 1;
			year2 = currentYear + 2;
			year3 = currentYear + 3;
			year4 = currentYear + 4;
			year5 = currentYear + 5;
		}else {
			year1 = currentYear - 1;
			year2 = currentYear - 2;
			year3 = currentYear - 3;
			year4 = currentYear - 4;
			year5 = currentYear - 5;
		}
		
		listProyRes = service.findAllByTipo(tipo);
		dataProyRes = new DataModelGeneric<TwProyeccionResultado>(listProyRes);
		this.bAdicionar = Boolean.FALSE;
	}

//	public void addElement() {
//
//		this.bAdicionar = Boolean.TRUE;
//		this.setbEdition(Boolean.TRUE);
//		listBalancepre = balancepreService.findAllByTrimestre(this.getUserDetails().getIdSector(),
//				this.getUserDetails().getIdMunicipio(), conctb.getId(), trimestre);
//		Balancepre balancepreNew = new Balancepre();
//		balancepreNew.setConcepto("");
//		listBalancepre.add(balancepreNew);
//
//		this.activateRowEdit(listBalancepre.size() - 1);
//		RequestContext.getCurrentInstance()
//				.execute("PF('balanceWdg').paginator.setPage(" + (this.getPage() - 1) + ");");
//
//		if (listBalancepre.size() > 20) {
//			Integer indexOf = this.getRowCurrent(listBalancepre.size());
//			this.activateRowEdit(indexOf);
//
//			RequestContext.getCurrentInstance().execute(String.format(FOCUS_BY_ROWID, (listBalancepre.size() - 1)));
//		}
//
//		dataModelBalancepre.setListT(listBalancepre);
//
//	}

	public void onRowEdit(RowEditEvent event) {
		proyResSelected = new TwProyeccionResultado();
		proyResSelected = (TwProyeccionResultado) event.getObject();
		proyResSelected.setIdSector(this.getUserDetails().getIdSector());
		proyResSelected.setCapturo(this.getUserDetails().getUsername());
		proyResSelected.setFecha(getDateSystem());

		int lastIndex = dataProyRes.getListT().size() - 1;

		if (bModifcar == Boolean.TRUE) {

			proyResSelected = service.save(proyResSelected);
		} else {
			proyResSelected.setIndex(lastIndex);
			proyResSelected = this.service.save(proyResSelected);
		}
		if (CollectionUtils.isEmpty(dataProyRes.getListT())) {
			this.activateRowEdit(dataProyRes.getListT().size());
			dataProyRes.getListT().add(lastIndex, proyResSelected);
		} else {
			if (dataProyRes.getListT().get(lastIndex).isbGuardar() == Boolean.TRUE) {
				listProyRes = service.findAllByTipo(tipo);
				RequestContext.getCurrentInstance().execute(UPDATE_OBJETS);
			} 
//			else {
//				if (bModifcar == Boolean.TRUE ) {
//					this.activateRowEdit(currentIndex);
//				} else {
//					this.activateRowEdit(lastIndex);
//				}
//
//				if (dataProyRes.getListT().size() > 20) {
//					int index = this.getRowCurrent(dataProyRes.getListT().size());
//					this.activateRowEdit(index);
//				}
//				dataProyRes.getListT().set(lastIndex, proyResSelected);
//			}
		}
	}

	public void onInitRowEdit(final RowEditEvent event) {
		this.bAdicionar = Boolean.TRUE;
		proyResSelected = (TwProyeccionResultado) event.getObject();
		if (null != event.getObject()) {

			if (null != proyResSelected.getId() && proyResSelected.getId() != 0) {
				this.setbModifcar(Boolean.TRUE);
//				oldTrimestre = this.balancepreSelected.getTrimestre();
//				oldConsecutivo = this.balancepreSelected.getConsecutivo();

				for (int i = 0; i < dataProyRes.getListT().size(); i++) {

					if (dataProyRes.getListT().get(i).getId().equals(proyResSelected.getId())) {
						currentIndex = i;
						break;
					}
				}
			} else {
				this.setbModifcar(Boolean.FALSE);
			}
		}
	}

	public void delete(Integer index) {
		proyResSelected = this.dataProyRes.getListT().get(index);

		if (null == proyResSelected.getId() || proyResSelected.getId() == 0) {
			listProyRes.remove(proyResSelected);
		} else {
			listProyRes.remove(proyResSelected);
			service.delete(proyResSelected);
		}
	}

	public Integer getRowCurrent(Integer index) {
		Double rows = 20.0;
		Double size = index.doubleValue();
		Double row = (size % rows);
		Integer filas = row.intValue() - 1;
		if (filas < 0) {
			filas = 19;
		}

		return filas;
	}

	public int getPage() {
		int rows = listProyRes.size();
		int de = 1;
		double maxRow = 20;
		double pageActua = (rows * de) / maxRow;
		String page = "" + Math.ceil(pageActua);
		return Integer.parseInt(this.getValue(page)[0]);
	}

	public void goToLastPage() {
		if (this.getbEdition() == Boolean.TRUE)
			if (this.dataProyRes.getListT().size() <= 20) {
				this.activateRowEditOnInsert(this.dataProyRes.getListT().size());

			} else {
				this.activateRowEdit(this.dataProyRes.getListT().size() - 1);

			}
	}

	public void activateRowEditOnInsert(final int index) {

		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	public void activateRowEdit(final int index) {
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	public String[] getValue(String data) {
		return data.split("\\.");
	}

	public DataModelGeneric<TwProyeccionResultado> getDataProyRes() {
		return dataProyRes;
	}

	public void setDataProyRes(DataModelGeneric<TwProyeccionResultado> dataProyRes) {
		this.dataProyRes = dataProyRes;
	}

	public List<TwProyeccionResultado> getListProyRes() {
		return listProyRes;
	}

	public void setListProyRes(List<TwProyeccionResultado> listProyRes) {
		this.listProyRes = listProyRes;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public TwProyeccionResultado getProyResSelected() {
		return proyResSelected;
	}

	public void setProyResSelected(TwProyeccionResultado proyResSelected) {
		this.proyResSelected = proyResSelected;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public Integer getOldTrimestre() {
		return oldTrimestre;
	}

	public void setOldTrimestre(Integer oldTrimestre) {
		this.oldTrimestre = oldTrimestre;
	}

	public Long getOldConsecutivo() {
		return oldConsecutivo;
	}

	public void setOldConsecutivo(Long oldConsecutivo) {
		this.oldConsecutivo = oldConsecutivo;
	}

	public Boolean getbEdition() {
		return bEdition;
	}

	public void setbEdition(Boolean bEdition) {
		this.bEdition = bEdition;
	}

	public Boolean getbModifcar() {
		return bModifcar;
	}

	public void setbModifcar(Boolean bModifcar) {
		this.bModifcar = bModifcar;
	}

	public Boolean getbAdicionar() {
		return bAdicionar;
	}

	public void setbAdicionar(Boolean bAdicionar) {
		this.bAdicionar = bAdicionar;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public ProyeccionResultadoService getService() {
		return service;
	}

	public void setService(ProyeccionResultadoService service) {
		this.service = service;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(Integer currentYear) {
		this.currentYear = currentYear;
	}

	public Integer getYear1() {
		return year1;
	}

	public void setYear1(Integer year1) {
		this.year1 = year1;
	}

	public Integer getYear2() {
		return year2;
	}

	public void setYear2(Integer year2) {
		this.year2 = year2;
	}

	public Integer getYear3() {
		return year3;
	}

	public void setYear3(Integer year3) {
		this.year3 = year3;
	}

	public Integer getYear4() {
		return year4;
	}

	public void setYear4(Integer year4) {
		this.year4 = year4;
	}

	public Integer getYear5() {
		return year5;
	}

	public void setYear5(Integer year5) {
		this.year5 = year5;
	}

}
