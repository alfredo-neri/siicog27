package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.FirmasBS;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;



// TODO: Auto-generated Javadoc
/**
 * The Class FirmasBSImpl.
 *
 * @author Mateo
 */
@Repository
public class FirmasBSImpl implements FirmasBS{
	
	/** The Constant MUNICIPAL. */
	private static final Integer MUNICIPAL = 1;
	
	/** The Constant CENTRAL. */
	private static final Integer CENTRAL = 2; 

	/** The firmas repository. */
	@Autowired
	private FirmasRepository firmasRepository;
	
	/** The conctb repository. */
	@Autowired
	private ConctbRepository conctbRepository;
	
	
	
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
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.FirmasBS#save(com.gem.sistema.business.domain.Firmas)
	 */
	@Override
	public void save(Firmas entity) {
		firmasRepository.save(entity);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.FirmasBS#modify(com.gem.sistema.business.domain.Firmas)
	 */
	@Override
	public void modify(Firmas entity) {
		firmasRepository.save(entity);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.FirmasBS#delete(com.gem.sistema.business.domain.Firmas)
	 */
	@Override
	public void delete(Firmas entity) {
		firmasRepository.delete(entity);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.FirmasBS#existFirma(com.gem.sistema.business.domain.Firmas)
	 */
	@Override
	public Boolean existFirma(Firmas entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.FirmasBS#findAllbyidSector(java.lang.Integer)
	 */
	@Override
	public Firmas findAllbyidSector(Integer idSector) {
		return firmasRepository.findAllByIdsector(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.FirmasBS#addElement(java.lang.Integer)
	 */
	@Override
	public Firmas addElement(Integer idSector) {
		Firmas firmas = new Firmas();
		firmas.setCampo3(conctbRepository.findByIdsector(idSector).getAnoemp());
		firmas.setCampo4("");
		firmas.setCampo5("");
		firmas.setCampo6("");
		firmas.setCampo7("");
		firmas.setCampo8("");
		firmas.setCampo9("");
		firmas.setCampo10("");
		firmas.setCampo11("");
		firmas.setCampo12("");
		firmas.setCampo13("");
		firmas.setCampo14("");
		firmas.setCampo15("");
		if(MUNICIPAL == idSector) {
			firmas.setCampo1("INTRODUSCA EL MUNICIPIO");
			firmas.setCampo2("INTRODUSCA EL R.F.C.");
			
			firmas.setL1("PRESIDENTE MUNICIPAL");
			firmas.setN1("NOMBRE DEL PRESIDENTE");
			
			firmas.setL2("SECRETARIO");
			firmas.setN2("NOMBRE SECRETARIO");
			
			firmas.setL3("TESORERO");
			firmas.setN3("NOMBRE TESORERO");
			
			firmas.setL4("ELABORO");
			firmas.setN4("NOMBRE DE ELABORO");
			
			firmas.setL5("REVISO");
			firmas.setN5("NOMBRE DE REVISO");
			
			firmas.setL6("DIRECTOR DE OBRAS PUBLICAS");
			firmas.setN6("NOMBRE DIRECTOR DE OBRAS PUBLICAS");
			
			firmas.setL7("PRESIDENTA DIF MUNICIPAL");
			firmas.setN7("NOMBRE DE LA PRESIDENTA DEL DIF MUNICIPAL");
			
			firmas.setL8("DIRECTOR (A) DIF MUNICIPAL");
			firmas.setN8("NOMBRE DEL DIRECTOR (A) DIF MUNICIPAL");
			
			firmas.setL9("TESORERO (A) DIF MUNICIPAL");
			firmas.setN9("NOMBRE DEL TESORERO (A) DIF MUNICIPAL");
			
			firmas.setL10("DIRECTOR GENERAL DEL ODAS");
			firmas.setN10("NOMBRE DEL DIRECTOR GENERAL DEL ODAS");
			
			firmas.setL11("DIRECTOR DE FINANZAS DEL ODAS");
			firmas.setN11("NOMBRDE DEL DIRECTOR DE FINANZAS DEL ODAS");
			
			firmas.setL12("COMISARIO");
			firmas.setN12("NOMBRE DEL COMISARIO");
			
			firmas.setL13("RESPONSABLE DEL PROGRAMA");
			firmas.setN13("NOMBRE DEL RESPONSABLE DEL PROGRAMA");
			
			firmas.setL14("TITULAR DE SERVICIOS PUBLICOS");
			firmas.setN14("NOMBRE DEL TITULAR DE SERVICIOS PUBLICOS");
			
			firmas.setL15("TITULAR DE PROTECCION CIVIL");
			firmas.setN15("NOMBRDE DEL TITULAR DE PROTECCION CIVIL");
			
			firmas.setL16("CONTRALOR INTERNO");
			firmas.setN16("NOMBRE DEL CONTRALOR INTERNO");
			
			firmas.setL17("DIRECTOR DE SEGURIDAD PUBLICA Y TRANSITO");
			firmas.setN17("NOMBRE DEL DIRECTOR DE SEGURIDAD PUBLICA Y TRANSITO");
			
			firmas.setL18("DIRECTOR GENERAL DEL MAVICI");
			firmas.setN18("NOMBRDE DEL DIRECTOR GENERAL DEL MAVICI");
			
			firmas.setL19("DIRECTOR DE FINANZAS DEL MAVICI");
			firmas.setN19("NOMBRE DEL DIRECTOR DE FINANZAS DEL MAVICI");
			
			firmas.setL20("DIRECTOR DEL INSITUTO MUNICIPAL DE CULTURA FISICA Y DEPORTE");
			firmas.setN20("NOMBVRE DEL DIRECTOR DEL INSITUTO MUNICIPAL DE CULTURA FISICA Y DEPORTE");
			
			firmas.setL21("DIRECTOR DE FINANZAS DEL IMCUFIDE");
			firmas.setN21("NOMBRE DEL DIRECTOR DE FINANZAS DEL IMCUFIDE");
			
			firmas.setL22("");
			firmas.setN22("");
			
			firmas.setL23("DIRECTOR DE ADMINISTRACIÓN");
			firmas.setN23("NOMBRE DEL DIRECTOR DE ADMINISTRACIÓN");
			
			firmas.setL24("SERVIDOR PUBLICO HABILITADO");
			firmas.setN24("NOMBRE DEL SERVIDOR PUBLICO HABILITADO");
			
			firmas.setL25("RESPONSABLE DE LA UNIDAD DE PLANEACIÓN");
			firmas.setN25("NOMBRDE DEL RESPONSABLE DE LA UNIDAD DE PLANEACIÓN");
			
			firmas.setL26("DIRECTOR DE DESARROLLO ECONOMICO");
			firmas.setN26("NOMBRE DEL DIRECTOR DE DESARROLLO ECONOMICO");
			
			
			
			firmas.setL27("AUTORIZO");
			firmas.setN27("NOMBRE AUTORIZO");
			
			firmas.setL28("SINDICO");
			firmas.setN28("NOMBRE DEL SINDICO");
			
			firmas.setL29("");
			firmas.setN29("");
			
			firmas.setL30("");
			firmas.setN30("");
			
			
		} else if(CENTRAL == idSector) {
			firmas.setCampo1("INTRODUSCA EL TITULO");
			firmas.setCampo2("INTRODUSCA EL R.F.C.");
			firmas.setCampo3(null);
			firmas.setL1("");
			firmas.setN1("");
			
			firmas.setL2("");
			firmas.setN2("");
			
			firmas.setL3("");
			firmas.setN3("");
			
			firmas.setL4("ELABORO");
			firmas.setN4("NOMBRE DE ELABORO");
			
			firmas.setL5("REVISO");
			firmas.setN5("NOMBRE DE REVISO");
			
			firmas.setL6("");
			firmas.setN6("");
			
			firmas.setL7("");
			firmas.setN7("");
			
			firmas.setL9("");
			firmas.setN9("");
			
			firmas.setL10("");
			firmas.setN10("");
			
			firmas.setL11("");
			firmas.setN11("");
			
			firmas.setL12("");
			firmas.setN12("");
			
			firmas.setL13("");
			firmas.setN13("");
			
			firmas.setL14("");
			firmas.setN14("");
			
			firmas.setL15("");
			firmas.setN15("");
			
			firmas.setL16("");
			firmas.setN16("");
			
			firmas.setL17("");
			firmas.setN17("");
			
			firmas.setL18("");
			firmas.setN18("");
			
			firmas.setL19("");
			firmas.setN19("");
			
			firmas.setL20("");
			firmas.setN20("");
			
			firmas.setL21("");
			firmas.setN21("");
			
			firmas.setL22("");
			firmas.setN22("");
			
			firmas.setL23("");
			firmas.setN23("");
			
			firmas.setL24("");
			firmas.setN24("");
			
			firmas.setL25("");
			firmas.setN25("");
			
			firmas.setL26("");
			firmas.setN26("");
			
			
			
			
			firmas.setL27("AUTORIZO");
			firmas.setN27("NOMBRE AUTORIZO");
			
			firmas.setL28("");
			firmas.setN28("");
			
			firmas.setL29("");
			firmas.setN29("");
			
			firmas.setL30("");
			firmas.setN30("");
			
		}
		firmas.setIdRef(0l);
		firmas.setIdsector(idSector);
		return firmas;
	}

}
