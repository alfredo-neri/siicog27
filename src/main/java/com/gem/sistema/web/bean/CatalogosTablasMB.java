package com.gem.sistema.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.gem.sistema.business.domain.TcTabla;
import com.gem.sistema.business.service.catalogos.CatalogoTablasService;
import com.gem.sistema.web.datamodel.DataModelGeneric;

@ManagedBean(name = "catalogosTablasMB")
@ViewScoped
public class CatalogosTablasMB extends AbstractMB {

	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-pencil').eq(";
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:tabs:object:%1$s:nombre');";
	private static final String UPDATE_OBJETS = "jQuery('#form1:tabs\\\\:hiddenUpdate').click();";

	private DataModelGeneric<TcTabla> dataModelTcTabla;
	private List<TcTabla> listTablas;
	private TcTabla tablaSelected;
	private Integer currentIndex;
	private Boolean bEdition;
	private Boolean bModifcar;
	private Boolean bAdicionar;

	@ManagedProperty("#{catalogoTablasService}")
	private CatalogoTablasService catalogoTablasService;

	@PostConstruct
	public void init() {
		this.refreshData();
	}

	public void refreshData() {
		listTablas = catalogoTablasService.findAllTablas();
		dataModelTcTabla = new DataModelGeneric<TcTabla>(listTablas);
		this.bAdicionar = Boolean.FALSE;
	}

	public void addElement() {

		this.bAdicionar = Boolean.TRUE;
		this.setbEdition(Boolean.TRUE);
		listTablas = catalogoTablasService.findAllTablas();
		TcTabla newTabla = new TcTabla();
		newTabla.setDescripcion("");
		listTablas.add(newTabla);

		this.activateRowEdit(listTablas.size() - 1);
		RequestContext.getCurrentInstance()
				.execute("PF('balanceWdg').paginator.setPage(" + (this.getPage() - 1) + ");");

		if (listTablas.size() > 20) {
			Integer indexOf = this.getRowCurrent(listTablas.size());
			this.activateRowEdit(indexOf);

			RequestContext.getCurrentInstance().execute(String.format(FOCUS_BY_ROWID, (listTablas.size() - 1)));
		}

		dataModelTcTabla.setListT(listTablas);

	}

	public void onRowEdit(RowEditEvent event) {
		tablaSelected = new TcTabla();
		tablaSelected = (TcTabla) event.getObject();

		int lastIndex = dataModelTcTabla.getListT().size() - 1;

		if (bModifcar == Boolean.TRUE) {

			// balancepreSelected = balancepreService.modify(balancepreSelected,
			// oldTrimestre, oldConsecutivo);
		} else {
			tablaSelected.setIndex(lastIndex);
			listTablas = catalogoTablasService.saveTabla(tablaSelected);
			tablaSelected = listTablas.get(lastIndex);
		}
		if (CollectionUtils.isEmpty(dataModelTcTabla.getListT())) {
			this.activateRowEdit(dataModelTcTabla.getListT().size());
			dataModelTcTabla.getListT().add(lastIndex, tablaSelected);
		} else {
			if (dataModelTcTabla.getListT().get(lastIndex).getId() != null) {
				listTablas = catalogoTablasService.findAllTablas();
				RequestContext.getCurrentInstance().execute(UPDATE_OBJETS);
				bAdicionar=Boolean.FALSE;
			} else {
				if (bModifcar == Boolean.TRUE && tablaSelected.getId() == null) {
					this.activateRowEdit(currentIndex);
				} else {
					this.activateRowEdit(lastIndex);
				}

				if (dataModelTcTabla.getListT().size() > 20) {
					int index = this.getRowCurrent(dataModelTcTabla.getListT().size());
					this.activateRowEdit(index);
				}
				dataModelTcTabla.getListT().set(lastIndex, tablaSelected);
			}
		}
	}

	public void onInitRowEdit(final RowEditEvent event) {
		this.bAdicionar = Boolean.TRUE;
		tablaSelected = (TcTabla) event.getObject();
		if (null != event.getObject()) {

			if (null != tablaSelected.getId() && tablaSelected.getId() != 0) {
				this.setbModifcar(Boolean.TRUE);

				for (int i = 0; i < dataModelTcTabla.getListT().size(); i++) {

					if (dataModelTcTabla.getListT().get(i).getId().equals(tablaSelected.getId())) {
						currentIndex = i;
						break;
					}
				}
			} else {
				this.setbModifcar(Boolean.FALSE);
			}
		}
	}

	/*
	 * public void delete(Integer index) { tablaSelected =
	 * this.dataModelTcTabla.getListT().get(index);
	 * 
	 * if (null == tablaSelected.getId() || tablaSelected.getId() == 0) {
	 * listBalancepre.remove(balancepreSelected); } else {
	 * listBalancepre.remove(balancepreSelected);
	 * balancepreService.delete(balancepreSelected); } }
	 */
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
		int rows = listTablas.size();
		int de = 1;
		double maxRow = 20;
		double pageActua = (rows * de) / maxRow;
		String page = "" + Math.ceil(pageActua);
		return Integer.parseInt(this.getValue(page)[0]);
	}

	public void goToLastPage() {
		if (this.getbEdition() == Boolean.TRUE)
			if (this.dataModelTcTabla.getListT().size() <= 20) {
				this.activateRowEditOnInsert(this.dataModelTcTabla.getListT().size());

			} else {
				this.activateRowEdit(this.dataModelTcTabla.getListT().size() - 1);

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

	public DataModelGeneric<TcTabla> getDataModelTcTabla() {
		return dataModelTcTabla;
	}

	public void setDataModelTcTabla(DataModelGeneric<TcTabla> dataModelTcTabla) {
		this.dataModelTcTabla = dataModelTcTabla;
	}

	public List<TcTabla> getListTablas() {
		return listTablas;
	}

	public void setListTablas(List<TcTabla> listTablas) {
		this.listTablas = listTablas;
	}

	public TcTabla getTablaSelected() {
		return tablaSelected;
	}

	public void setTablaSelected(TcTabla tablaSelected) {
		this.tablaSelected = tablaSelected;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
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

	public CatalogoTablasService getCatalogoTablasService() {
		return catalogoTablasService;
	}

	public void setCatalogoTablasService(CatalogoTablasService catalogoTablasService) {
		this.catalogoTablasService = catalogoTablasService;
	}

}
