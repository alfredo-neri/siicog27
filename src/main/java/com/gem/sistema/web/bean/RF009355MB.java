package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009355MB.
 */
@Component("rF009355MB")
@Scope("view")
public class RF009355MB extends BaseDirectReport {
	
	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	

	/** The tc mes repository. */
	// Indicamos el repositorio de donde se traeran los datos
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

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

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "RF009355";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes))
			mes = listMes.get(0).getMes();

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters;
		TcMes descripMes = tcMesRepository.findByMes(mes);
		parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = null;
		Conctb conctb=conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		
		parameters.put("pImg", conctb.getImagePathLeft());
		parameters.put("pMun", this.getUserDetails().getMunicipio());
		parameters.put("pClveMun", conctb.getClave());
		parameters.put("pAnio", conctb.getAnoemp());
		parameters.put("pLastDay", getLastDayByAnoEmp(Integer.valueOf(mes),conctb.getAnoemp()));
		parameters.put("pMesDescrip", descripMes.getDescripcion());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F01.getValue());
		parameters.put("pL1", firma.getPuesto().getPuesto());
		parameters.put("pN1", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F02.getValue());
		parameters.put("pL2", firma.getPuesto().getPuesto());
		parameters.put("pN2", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("pL3", firma.getPuesto().getPuesto());
		parameters.put("pN3", firma.getNombre());
		parameters.put("pSsql", this.generaQuery(this.getUserDetails().getIdSector(), Integer.valueOf(mes)));
		
		if (sector == 2) {
			parameters.put("pImg1", conctb.getImagePathLeft());
			parameters.put("pImg2", conctb.getImagePathRigth());
		} else {
			parameters.put("pImg1", conctb.getImagePathRigth());
			parameters.put("pImg2", "");
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

	/**
	 * Genera query.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	public String generaQuery(Integer idsector, Integer mes) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder acargos = new StringBuilder();
		StringBuilder aabonos = new StringBuilder();
		StringBuilder agcargos = new StringBuilder();
		StringBuilder agabonos = new StringBuilder();
		StringBuilder dcargos = new StringBuilder();
		StringBuilder dabonos = new StringBuilder();
		StringBuilder depcargos = new StringBuilder();
		StringBuilder depabonos = new StringBuilder();
		StringBuilder mavicigargos = new StringBuilder();
		StringBuilder maviciabonos = new StringBuilder();

		sSql.append("SELECT T2.CUENTA,").append("T2.NOMBRE,").append(" T2.AYUREC,").append("T2.AGUAREC,")
				.append(" T2.DIFREC,").append(" T2.DEPREC,").append("T2.MAVREC,")
				.append(" (T2.AYUREC+T2.AGUAREC+T2.DIFREC+T2.DEPREC+T2.MAVREC)TOTAL").append(" FROM ")
				.append("(SELECT T1.CUENTA,").append("T1.NOMBRE,").append("NVL((T1.AABONOS-T1.ACARGOS),0)AYUREC,")
				.append("NVL((T1.AGABONOS-T1.AGCARGOS),0)AGUAREC,").append("NVL((T1.DABONOS-T1.DCARGOS),0) DIFREC,")
				.append("NVL((T1.DEPABONOS-T1.DEPCARGOS),0)DEPREC,").append("NVL((T1.MAVICIABONOS-T1.MAVICICARGOS),0)MAVREC")
				.append(" FROM ")
				.append("(SELECT C.CUENTA ||' '|| SUBSTR(C.SCTA,7,4)||' '||SUBSTR(C.SSCTA,13,3)||' '||SUBSTR(C.SSSCTA,2,3)||' '||SUBSTR(C.SSSSCTA,1,3) CUENTA, ")
				.append(" C.NOMCTA NOMBRE,");

		for (int i = 1; i <= mes; i++) {

			acargos.append(" I.AYTTOCARGOS_" + i + "+");
			aabonos.append(" I.AYTTOABONOS_" + i + "+");
			agcargos.append(" I.AGUACARGOS_" + i + "+");
			agabonos.append(" I.AGUAABONOS_" + i + "+");
			dcargos.append(" I.DIFCARGOS_" + i + "+");
			dabonos.append(" I.DIFABONOS_" + i + "+");
			depcargos.append(" I.DEPORTECARGOS_" + i + "+");
			depabonos.append(" I.DEPORTEABONOS_" + i + "+");
			mavicigargos.append(" I.MAVICICARGOS_" + i + "+");
			maviciabonos.append(" I.MAVICIABONOS_" + i + "+");

		}

		sSql.append(" (" + acargos.substring(1, acargos.length() - 1)).append(") ACARGOS,")
				.append(" (" + aabonos.substring(1, aabonos.length() - 1)).append(") AABONOS,")
				.append(" (" + agcargos.substring(1, agcargos.length() - 1)).append(")AGCARGOS,")
				.append(" (" + agabonos.substring(1, agabonos.length() - 1)).append(")AGABONOS,")
				.append(" (" + dcargos.substring(1, dcargos.length() - 1)).append(") DCARGOS,")
				.append(" (" + dabonos.substring(1, dabonos.length() - 1)).append(") DABONOS,")
				.append(" (" + depcargos.substring(1, depcargos.length() - 1)).append(") DEPCARGOS,")
				.append(" (" + depabonos.substring(1, depabonos.length() - 1)).append(") DEPABONOS,")
				.append(" (" + mavicigargos.substring(1, mavicigargos.length() - 1)).append(")MAVICICARGOS,")
				.append(" (" + maviciabonos.substring(1, maviciabonos.length() - 1)).append(") MAVICIABONOS");
		sSql.append(" FROM CUENTA C")
				.append(" LEFT JOIN INTEGRADOI I ON C.CUENTA=I.CUENTA AND C.SCTA = I.SCTA AND C.SSCTA=I.SSCTA AND C.SSSCTA = I.SSSCTA AND C.SSSSCTA=I.SSSSCTA AND C.IDSECTOR=I.IDSECTOR")
				.append(" WHERE C.IDSECTOR=" + idsector).append(" AND C.CUENTA = '8150' ORDER BY C.CUENTA,").append("C.SCTA,")
				.append("C.SSCTA,").append("C.SSSCTA,").append(" C.SSSSCTA) T1)T2");

		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();
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
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
