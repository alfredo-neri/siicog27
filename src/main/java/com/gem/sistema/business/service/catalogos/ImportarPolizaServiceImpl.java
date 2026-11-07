package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ImportarPolizaDTO;
import com.gem.sistema.business.dto.PolizaImportDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ImportarPolizaServiceImpl.
 */
@Service("importarPolizaServiceImpl")
public class ImportarPolizaServiceImpl implements ImportarPolizaService {

	/** The importar poliza DTO. */
	@Autowired
	@Qualifier(value = "importarPolizaDTO")
	private ImportarPolizaDTO importarPolizaDTO;

	/**
	 * Gets the importar poliza DTO.
	 *
	 * @return the importarPolizaDTO
	 */
	public ImportarPolizaDTO getImportarPolizaDTO() {
		return importarPolizaDTO;
	}

	/**
	 * Sets the importar poliza DTO.
	 *
	 * @param importarPolizaDTO            the importarPolizaDTO to set
	 */
	public void setImportarPolizaDTO(ImportarPolizaDTO importarPolizaDTO) {
		this.importarPolizaDTO = importarPolizaDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.ImportarPolizaService#execuet(
	 * com.gem.sistema.business.dto.PolizaImportDTO, java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public PolizaImportDTO execuet(PolizaImportDTO importDTO, Integer idSector, String idUser) {
		// TODO Auto-generated method stub
		return this.importarPolizaDTO.execute(importDTO, idSector, idUser);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ImportarPolizaService#exporReport(com.gem.sistema.business.dto.PolizaImportDTO, java.lang.String)
	 */
	@Override
	public PolizaImportDTO exporReport(PolizaImportDTO polizaDTO, String tipo) {
		// TODO Auto-generated method stub
		return this.importarPolizaDTO.exportReports(polizaDTO, tipo);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ImportarPolizaService#genarteExcel(com.gem.sistema.business.dto.PolizaImportDTO)
	 */
	@Override
	public PolizaImportDTO genarteExcel(PolizaImportDTO polizaDTO) {
		// TODO Auto-generated method stub
		return this.importarPolizaDTO.generateExcel(polizaDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.ImportarPolizaService#
	 * getMesActivo(java.lang.Integer)
	 */
	@Override
	public List<String> getMesActivo(Integer idSector) {
		// TODO Auto-generated method stub
		return this.importarPolizaDTO.getMesActivo(idSector);
	}

}
