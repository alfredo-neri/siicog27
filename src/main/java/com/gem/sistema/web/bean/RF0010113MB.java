package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010113MB.
 */
@Component("rF0010113MB")
@Scope("view")
public class RF0010113MB extends BaseDirectReport {

	/** The mes. */
	private String mes;

	/** The saldo. */
	private String saldo;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;
	
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

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

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	/** Servicio para validacion de mes aprturado, polizas, afectacion */

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

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
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo the new saldo
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
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
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();

		parameters.put("mesName", tcMesRepository.findByMes(mes).getDescripcion());
		parameters.put("lastDay", getLastDayByAnoEmp(Integer.valueOf(mes), conctb.getAnoemp()));
		parameters.put("year", conctb.getAnoemp());
		parameters.put("imagePath", idSector==1? conctb.getImagePathRigth(): conctb.getImagePathLeft());
		parameters.put("query", this.generateQuery(Integer.valueOf(mes), sector, saldo.equals("S") ? 1 : 0));
		parameters.put("municipio", conctb.getNomDep());
		parameters.put("pId", getUserDetails().getIdSector());
		String mes2 = StringUtils.leftPad(mes.toString(), 2, "0");

		endFilename = "EDO ANA ACT " + mes2 + conctb.getAnoemp() + ".pdf";
		
		if (idSector == 1) {
			switch (conctb.getClave().substring(0, 1)) {
		      case "0": 
		    	parameters.put("pNoFirmas",3);
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F01.getValue());
		        parameters.put("firmaP1", firma.getPuesto().getPuesto());
		        parameters.put("firmaN1", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F03.getValue());
		        parameters.put("firmaP2", firma.getPuesto().getPuesto());
		        parameters.put("firmaN2", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F02.getValue());
		        parameters.put("firmaP3", firma.getPuesto().getPuesto());
		        parameters.put("firmaN3", firma.getNombre());
		        
		        parameters.put("tipo", "Ayuntamiento ");
				parameters.put("noEntity", conctb.getClave());
		        break;
		      case "2": // ODAS 
		    	parameters.put("pNoFirmas",3);
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F10.getValue());
		        parameters.put("firmaP1", firma.getPuesto().getPuesto());
		        parameters.put("firmaN1", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F11.getValue());
		        parameters.put("firmaP2", firma.getPuesto().getPuesto());
		        parameters.put("firmaN2", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F12.getValue());
		        parameters.put("firmaP3", firma.getPuesto().getPuesto());
		        parameters.put("firmaN3", firma.getNombre());
		        
		        parameters.put("tipo", "Organismo de Agua ");
				parameters.put("noEntity", conctb.getClave());
		        break;
		      case "3": 
		    	parameters.put("pNoFirmas",3);
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F07.getValue());
		        parameters.put("firmaP1", firma.getPuesto().getPuesto());
		        parameters.put("firmaN1", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F08.getValue());
		        parameters.put("firmaP2", firma.getPuesto().getPuesto());
		        parameters.put("firmaN2", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F09.getValue());
		        parameters.put("firmaP3", firma.getPuesto().getPuesto());
		        parameters.put("firmaN3", firma.getNombre());
		        
		        parameters.put("tipo", "DIF Municipal ");
				parameters.put("noEntity", conctb.getClave());
		        break;
		      case "4":
		    	parameters.put("pNoFirmas",2);
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F20.getValue());
		        parameters.put("firmaP1", firma.getPuesto().getPuesto());
		        parameters.put("firmaN1", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F21.getValue());
		        parameters.put("firmaP3", firma.getPuesto().getPuesto());
		        parameters.put("firmaN3", firma.getNombre());
		        
		        parameters.put("tipo", "Organismo del Deporte ");
				parameters.put("noEntity", conctb.getClave());
		        break;
		      }

		} else {
			parameters.put("pNoFirmas",3);
			firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F04.getValue());
		        parameters.put("firmaP1", firma.getPuesto().getPuesto());
		        parameters.put("firmaN1", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F05.getValue());
		        parameters.put("firmaP2", firma.getPuesto().getPuesto());
		        parameters.put("firmaN2", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
		            ConstantsClaveEnnum.CLAVE_F27.getValue());
		        parameters.put("firmaP3", firma.getPuesto().getPuesto());
		        parameters.put("firmaN3", firma.getNombre());
		        
		        parameters.put("tipo", "");
				parameters.put("noEntity", "");
		}
		
		return parameters;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "RF0010_1_13";
		
		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes))
			mes = listMes.get(0).getMes();

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

	/**
	 * Generate query.
	 *
	 * @param mes        the mes
	 * @param idSector   the id sector
	 * @param saldosCero the saldos cero
	 * @return the string
	 */
	public String generateQuery(Integer mes, Integer idSector, Integer saldosCero) {

		StringBuilder sQuery = new StringBuilder();
		StringBuilder sCargosAnt = new StringBuilder();
		StringBuilder sAbonosAnt = new StringBuilder();

		sQuery.append(
				"SELECT CUENTA, NOMCTA, SALDOINICIAL, CARGOS_MES, ABONOS_MES,(SALDOINICIAL + CARGOS_MES - ABONOS_MES) SALDOFINAL,(SALDOINICIAL - (SALDOINICIAL + CARGOS_MES - ABONOS_MES)) FLUJO FROM ( SELECT CUENTA, NOMCTA, CASE WHEN NATCTA IN('A') THEN CASE WHEN CUENTA IN ('1115','1161') THEN ABS(SALINI + CARGOS_ANT - ABONOS_ANT) ELSE ABS(SALINI - CARGOS_ANT + ABONOS_ANT) END ELSE ABS(SALINI + CARGOS_ANT - ABONOS_ANT) END SALDOINICIAL, CARGOS_MES, ABONOS_MES FROM ( SELECT CU.CUENTA, MA.NATCTA, CU.NOMCTA, CU.SALINI,");

		if (mes > 1) {
			for (int i = 1; i < mes; i++) {
				sCargosAnt.append(" CU.CARGOS_" + i + " +");
				sAbonosAnt.append(" CU.ABONOS_" + i + " +");
			}
		} else {
			sCargosAnt.append("  '0'  ");
			sAbonosAnt.append("  '0'  ");
		}

		sQuery.append(sCargosAnt.substring(0, sCargosAnt.length() - 1) + "CARGOS_ANT,")
				.append(sAbonosAnt.substring(0, sAbonosAnt.length() - 1) + "ABONOS_ANT,")
				.append(" CU.CARGOS_" + mes + " CARGOS_MES, CU.ABONOS_" + mes + " ABONOS_MES ")
				.append(" FROM CUENTA AS CU, MAYCTA AS MA WHERE CU.IDSECTOR=" + idSector
						+ " AND CU.CUENTA = MA.CUENTA AND CU.SCTA = '' AND CU.SSCTA = '' AND CU.SSSCTA = '' AND CU.SSSSCTA = '' AND CU.CUENTA <= 1300 AND SUBSTR(CU.CUENTA,2,3) <> '000'  AND CU.NOTCTA <> 3 ) T1 )T2");
		if (saldosCero == 0) {
			sQuery.append(
					" WHERE SALDOINICIAL > 0 OR CARGOS_MES > 0 OR ABONOS_MES > 0 OR (SALDOINICIAL + CARGOS_MES - ABONOS_MES) > 0 OR (SALDOINICIAL - (SALDOINICIAL + CARGOS_MES - ABONOS_MES)) >0");
		}
		return sQuery.toString();
	}

	/** Metodo de validacion al descargar el PDF */
	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
	}

}
