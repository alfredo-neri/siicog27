package com.gem.sistema.web.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.service.catalogos.PolideService;

// TODO: Auto-generated Javadoc
/**
 * The Class CantidadesPolizaCantidadReferenciaBean.
 */
@Component(value = "cantidadesPolizaCantidadReferenciaBean")
@RequestScoped
public class CantidadesPolizaCantidadReferenciaBean extends AbstractMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CantidadesPolizaCantidadReferenciaBean.class);
	
	/** The polide service. */
	@Autowired
	private PolideService polideService;
	
	/** The lista general. */
	private List<Polide> listaGeneral;
	
	/** The cantidad. */
	private BigDecimal cantidad;
	
	/** The referencia. */
	private BigDecimal referencia;
	
	/** The concepto. */
	private String concepto;
	
	/** The row selected. */
	private Polide rowSelected;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct CantidadesPolizaCantidadReferenciaBean ");
		cantidad = BigDecimal.ZERO;
		referencia = null;
		concepto = "";
		listaGeneral = new ArrayList<Polide>();
	}
	
	/**
	 * metodo que se llama cuando se carga la pagina pm1111.
	 */
	public void onPageLoad() {
		cantidad = BigDecimal.ZERO;
		referencia = null;
		concepto = "";
		listaGeneral = new ArrayList<Polide>();
	}
	
	/**
	 * Mostrar tabla actualizada.
	 *
	 * @return the list
	 */
	public List<Polide> mostrarTablaActualizada(){
		concepto = "";
		
		LOGGER.info("CantidadesPolizaCantidadReferenciaBean - actualizar tabla filtrada");
		listaGeneral = new ArrayList<Polide>();
		listaGeneral = polideService.buscarCantidadReferencia(cantidad, referencia, this.getUserDetails().getIdSector());
		
		return listaGeneral;
	}

	/**
	 * On row select.
	 *
	 * @param event the event
	 */
	public void onRowSelect(SelectEvent event) {
		rowSelected = (Polide) event.getObject();
		this.setConcepto(rowSelected.getConcep());
		LOGGER.info("rowSelected: " + rowSelected);
	}
	
	/**
	 * Gets the polide service.
	 *
	 * @return the polide service
	 */
	public PolideService getPolideService() {
		return polideService;
	}

	/**
	 * Sets the polide service.
	 *
	 * @param polideService the new polide service
	 */
	public void setPolideService(PolideService polideService) {
		this.polideService = polideService;
	}

	/**
	 * Gets the lista general.
	 *
	 * @return the lista general
	 */
	public List<Polide> getListaGeneral() {
		return listaGeneral;
	}

	/**
	 * Sets the lista general.
	 *
	 * @param listaGeneral the new lista general
	 */
	public void setListaGeneral(List<Polide> listaGeneral) {
		this.listaGeneral = listaGeneral;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the referencia.
	 *
	 * @return the referencia
	 */
	public BigDecimal getReferencia() {
		return referencia;
	}

	/**
	 * Sets the referencia.
	 *
	 * @param referencia the new referencia
	 */
	public void setReferencia(BigDecimal referencia) {
		this.referencia = referencia;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto the new concepto
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the row selected
	 */
	public Polide getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected the new row selected
	 */
	public void setRowSelected(Polide rowSelected) {
		this.rowSelected = rowSelected;
	}
	
}