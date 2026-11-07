package com.gem.sistema.web.bean;

import java.math.BigDecimal;
/*

Object: ReporteIngresosPropiosDelMes.jrxml

Fecha de Creación                    Autor                             Descripcion                      Versión                        Linea de Codigo

---------------       ---------------------------------     -----------------------------          -----------                     -------------------

05/05/2021            Luis Enrique Longino Nicolas. 		Se crea módulo para el reporte de      	1.0
                             								Ingresos Propios del Mes
													
														
       
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcIngresosPropio;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcIngresoPropioRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
// TODO: Auto-generated Javadoc
import com.gem.sistema.util.ConstantsClaveEnnum;

/**
 * The Class ReporteIngresosPropiosDelMesMB.
 */
@ManagedBean(name = "reporteIngresosPropiosDelMesMB")
@ViewScoped
public class ReporteIngresosPropiosDelMesMB extends BaseDirectReport {
	
	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;
	
	private List<TcIngresosPropio> listaIngresosPropios;
	
	private List<TcIngresosPropio> listaIngresoConceptos;
	
	private BigDecimal total;
	
	/** The clave. */
	private Integer clave;
	
	/** The concepto. */
	private String concepto;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The tc ingreso propio repository. */
	@ManagedProperty("#{tcIngresosPropioRepository}")
	private TcIngresoPropioRepository tcIngresoPropioRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "ingresosPropiosDelMes";
		endFilename = jasperReporteName + ".pdf";
		
		listMes = tcMesRepository.findAll();
		
		listaIngresosPropios = tcIngresoPropioRepository.findAllByOrderByClave();
		
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
		
		if(!CollectionUtils.isEmpty(listaIngresosPropios)) {
			clave = listaIngresosPropios.get(0).getClave();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		this.listaIngresoConceptos = tcIngresoPropioRepository.findAllByClave(clave);
		if (Integer.valueOf(mes) ==1 ) {
			this.total = BigDecimal.ZERO;
		} else {
			this.total = tcIngresoPropioRepository.getTotalIngresos(Integer.valueOf(mes) - 1,Integer.valueOf(mes) - 1, clave);
		}
		parameters.put("pNomDep", conctb.getNomDep());
		parameters.put("pMes", Integer.valueOf(this.mes));
		parameters.put("pYear", conctb.getAnoemp());
		parameters.put("pImageLeft", conctb.getImagePathLeft());
		if (!CollectionUtils.isEmpty(listaIngresoConceptos)) {
			this.concepto = listaIngresoConceptos.get(0).getNombre();
		}
		parameters.put("pConcepto" , this.concepto);
		parameters.put("pIdSector", idSector);
		parameters.put("pClave", clave);
		parameters.put("pMesDesc", tcMesRepository.findByMes(mes).getDescripcion());
		parameters.put("pTotalInicial", this.total);
		if (idSector == 2) {
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("pL2", firma.getPuesto().getPuesto());
			parameters.put("pN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
		}
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void viewPdf() {
		this.createFilePdfInline();
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getClave() {
		return this.clave;
	}

	public void setClave(Integer clave) {
		this.clave = clave;
	}

	public List<TcMes> getListMes() {
		return this.listMes;
	}

	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
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

	public TcIngresoPropioRepository getTcIngresoPropioRepository() {
		return tcIngresoPropioRepository;
	}

	public void setTcIngresoPropioRepository(TcIngresoPropioRepository tcIngresoPropioRepository) {
		this.tcIngresoPropioRepository = tcIngresoPropioRepository;
	}

	public List<TcIngresosPropio> getListaIngresosPropios() {
		return listaIngresosPropios;
	}

	public void setListaIngresosPropios(List<TcIngresosPropio> listaIngresosPropios) {
		this.listaIngresosPropios = listaIngresosPropios;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public List<TcIngresosPropio> getListaIngresoConceptos() {
		return listaIngresoConceptos;
	}

	public void setListaIngresoConceptos(List<TcIngresosPropio> listaIngresoConceptos) {
		this.listaIngresoConceptos = listaIngresoConceptos;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
