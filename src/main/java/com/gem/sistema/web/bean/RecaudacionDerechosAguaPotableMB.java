


package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.roonin.utils.UtilDate.getYear;
import com.gem.sistema.business.domain.Firmas;

import com.gem.sistema.business.domain.TcMes;

import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;

import com.gem.sistema.business.service.reportador.ReportValidationException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import org.primefaces.model.StreamedContent;


// TODO: Auto-generated Javadoc
/**
 * The Class RecaudacionDerechosAguaPotableMB.
 */
@ManagedBean(name="recaudacionDerechosAguaPotableMB")
@ViewScoped
public class RecaudacionDerechosAguaPotableMB extends BaseDirectReport {
	
	/** The mes. */
	private String mes;
	
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	
	/** The tcmes repository. */
	@ManagedProperty("#{tcMesRepository}") 
	private TcMesRepository tcmesRepository;

	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	


	
	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}



	

	

	/**
	 * Gets the tcmes repository.
	 *
	 * @return the tcmes repository
	 */
	public TcMesRepository getTcmesRepository() {
		return tcmesRepository;
	}

	/**
	 * Sets the tcmes repository.
	 *
	 * @param tcmesRepository the new tcmes repository
	 */
	public void setTcmesRepository(TcMesRepository tcmesRepository) {
		this.tcmesRepository = tcmesRepository;
	}



	


	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Firmas firmas= firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		 Map<String, Object> parameters= new HashMap<String, Object>();
		 parameters.put("MES", Integer.valueOf(mes) );
		 parameters.put("SECTOR",this.getUserDetails().getIdSector() );
		 parameters.put("imagen", this.getUserDetails().getPathImgCab1());
		 parameters.put("CAMPO1", this.getUserDetails().getMunicipio());
		 parameters.put("CLAVE", this.getUserDetails().getIdMunicipio());
		 parameters.put("ANO_EMP", firmas.getCampo3());	
		 parameters.put("L3", firmas.getL3() );
		 parameters.put("N3", firmas.getN3() );
		 parameters.put("L4", firmas.getL4() );
		 parameters.put("N4", firmas.getN4() );
		 parameters.put("L5", firmas.getL5());
		 parameters.put("N5", firmas.getN5());
		 
		 
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
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "RecaudacionDerechosAguaPotable" ;
		endFilename = jasperReporteName + ".pdf";
		
		// llenar la lista
		listMes = tcmesRepository.findAll();
		
		
		
		
		// se inicializa las listas
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
		
		
		
		
	}
}


