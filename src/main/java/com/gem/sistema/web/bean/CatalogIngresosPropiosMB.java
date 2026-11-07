package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import com.gem.sistema.business.domain.TcIngresosPropio;
import com.gem.sistema.business.repository.catalogs.TcIngresoPropioRepository;
import com.gem.sistema.business.repository.catalogs.TwIngresoPropioDetalleRepository;

@ManagedBean(name = "catalogIngresosPropiosMB")
@ViewScoped
public class CatalogIngresosPropiosMB extends CommonCatalogMB<TcIngresosPropio> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean insert;
	private TcIngresosPropio rowSelected;
	private String errorMessage;
	private int selectedIndex;

	@ManagedProperty("#{tcIngresosPropioRepository}")
	private TcIngresoPropioRepository tcIngresosPropioRepository;

	@ManagedProperty("#{twIngresosPropioDetalleRepository}")
	private TwIngresoPropioDetalleRepository detalleRepository;

	@PostConstruct
	public void init() {
		setBeanFind(new TcIngresosPropio());
		setList(new ArrayList<TcIngresosPropio>());
		setListNew(new ArrayList<TcIngresosPropio>());
	}

	public void onPageLoad() {
		getBeanFind().setNombre(null);
		getBeanFind().setClave(null);
		restartData();
	}

	public void onRowEdit(final RowEditEvent event) {
		final TcIngresosPropio catalog = (TcIngresosPropio) event.getObject();
		catalog.setClave(catalog.getId() != null && catalog.getId() != ZERO ? catalog.getClave()
				: tcIngresosPropioRepository.getNewClave());
		executeOperationSaveOrUpdate(catalog, tcIngresosPropioRepository);
		RequestContext.getCurrentInstance().execute("document.getElementById('form1:hideButton2').click();");
		activateRowEdit(catalog.getIndex());

	}

	private Boolean haveIgresos(Integer clave) {
		return !detalleRepository.getAllByClaveIngreso(clave).isEmpty();
	}

	public void delete() {
		if (haveIgresos(getBeanSelected().getClave())) {
			generateNotificationFront(SEVERITY_ERROR, "No es posible eliminar",
					"La clave ha sido ocupada en egresos detallados");
			return;
		}
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			tcIngresosPropioRepository.delete(getBeanSelected());
		}
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		findValuesCatalog();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void consultList() {
		addList((List<TcIngresosPropio>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), "clave", "="));
		restartFilteredList();
	}

	@Override
	protected void consultAll() {
		setList(tcIngresosPropioRepository.findAllByOrderByClave());
		restartFilteredList();
	}

	public List<TcIngresosPropio> completeTcIngresoName(String query) {
		consultAll();
		return getList().stream().filter(a -> a.getNombre() != null && a.getNombre().toLowerCase().startsWith(query))
				.collect(Collectors.toList());
	}

	@Override
	protected boolean isValidRequest() {
		return Boolean.TRUE;
	}

	public void activateRowEdit() {
		super.activateRowEdit(rowSelected.getIndex());
	}

	@Override
	protected void addNewOriginalList() {

	}

	@Override
	public void onRowCancel(RowEditEvent event) {
		restartData();
		super.onRowCancel(event);
	}

	@Override
	public void insertRow() throws InstantiationException, IllegalAccessException {
		super.insertRow();
		this.insert = Boolean.TRUE;
	}

	public void showSuccesMessage() {
		consultList();
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);
	}

	public void showErrorMessage() {
		activateRowEdit(this.selectedIndex);
		generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, this.errorMessage);
	}

	public void showCancelEdition() {
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_CANCELED);
	}

	public Boolean getInsert() {
		return insert;
	}

	public void setInsert(Boolean insert) {
		this.insert = insert;
	}

	public TcIngresosPropio getRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(TcIngresosPropio rowSelected) {
		this.rowSelected = rowSelected;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public TcIngresoPropioRepository getTcIngresosPropioRepository() {
		return tcIngresosPropioRepository;
	}

	public void setTcIngresosPropioRepository(TcIngresoPropioRepository tcIngresosPropioRepository) {
		this.tcIngresosPropioRepository = tcIngresosPropioRepository;
	}

	public TwIngresoPropioDetalleRepository getDetalleRepository() {
		return detalleRepository;
	}

	public void setDetalleRepository(TwIngresoPropioDetalleRepository detalleRepository) {
		this.detalleRepository = detalleRepository;
	}

}
