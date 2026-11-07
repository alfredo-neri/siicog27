package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 *
 */
@Component("eAPESubFuncionMB")
@Scope("view")
public class EAPESubFuncionMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_AVAN_PRE_EGR_SUB_FUN";
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	
	private String mes;
	private String funcion;
	private String nameFuncion;
	private List<TcMes> listTcMes;
	private List<Muninep> listMuninep;

	@Value("#{tcMesRepository}")
	private TcMesRepository tcmesRepository;

	@Value("#{muniNepRepository}")
	private MuniNepRepository muninepRepository;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;
	
	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	@PostConstruct
	public void init() {
		jasperReporteName = "RF009122_EdoAvancePEfin";
		endFilename = "EAPE_Funcion.pdf";
		listTcMes = tcmesRepository.findAll();
		listMuninep = muninepRepository.getListSubFuncion(this.getUserDetails().getIdSector());
		if (!CollectionUtils.isEmpty(listTcMes)) {
			mes = listTcMes.get(0).getMes();
		}
		if (!CollectionUtils.isEmpty(listMuninep)) {
			funcion = listMuninep.get(0).getCampo7();
			nameFuncion = listMuninep.get(0).getCampo6();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		
		parameters.put("p_IdSector", sector);
		parameters.put("p_Mes", Integer.valueOf(mes));
		parameters.put("p_NombreFin", nameFuncion);
		parameters.put("p_NumFin", funcion);
		parameters.put("imageLeft", conctb.getImagePathLeft());
		parameters.put("imageRigth", conctb.getImagePathRigth());
		parameters.put("p_Query", this.generateQuery(sector, Integer.valueOf(mes), funcion));
		parameters.put("p_An", conctb.getAnoemp());
		parameters.put("p_LastDay", getLastDayByAnoEmp(Integer.valueOf(mes), conctb.getAnoemp()));
		parameters.put("p_DescrpMes", tcmesRepository.findByMes(mes).getDescripcion());
		parameters.put("p_Dependencia", conctb.getNomDep());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}


	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateName() {
		nameFuncion = muninepRepository.findFirstByCampo7AndIdsector(funcion, this.getUserDetails().getIdSector()).getCampo6();
	}
	
	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());

			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
		}
	}
	
	public String generateQuery(Integer idsector, Integer mes, String xdire) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder ejempa = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder toeje = new StringBuilder();
		StringBuilder ampli = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder compro = new StringBuilder();

		sSql.append("SELECT T1.PARTIDA, ").append("T1.DESCRIPCION,").append("T1.A APROBADO,").append("T1.F AMPLIACION,")
				.append("T1.G REDUCCION,").append("(T1.A+T1.F-T1.G) MODIFICADO,").append("(T1.CPT)COMPROMETIDO,")
				.append("T1.C DEVENGADO,").append("T1.B PAGADO,").append("T1.D EJERCIDO,")
				.append("((T1.A +T1.F-T1.G)-D) POR_EJERCER").append(" FROM (SELECT P.PARTIDA PARTIDA,")
				.append("N.NOMGAS DESCRIPCION,")
				.append("SUM(P.AUTO1_1+P.AUTO1_2+P.AUTO1_3+P.AUTO1_4+P.AUTO1_5+P.AUTO1_6+P.AUTO1_7+P.AUTO1_8+P.AUTO1_9+P.AUTO1_10+P.AUTO1_11+P.AUTO1_12)A,");

		for (int i = 1; i <= mes; i++) {

			ejempa.append(" P.EJPA1_" + i + "+");
			ejxpa.append(" P.EJXPA1_" + i + "+");
			toeje.append(" P.TOEJE1_" + i + "+");
			ampli.append(" P.AMPLI1_" + i + "+");
			redu.append(" P.REDU1_" + i + "+");
			compro.append(" P.COMPRO1_" + i + "+");

		}

		sSql.append(" SUM( ").append(ejempa.substring(1, ejempa.length() - 1)).append(")B,").append(" SUM( ")
				.append(ejxpa.substring(1, ejxpa.length() - 1)).append(")C,").append(" SUM( ")
				.append(toeje.substring(1, toeje.length() - 1)).append(")D,").append(" SUM( ")
				.append(ampli.substring(1, ampli.length() - 1)).append(")F,").append(" SUM( ")
				.append(redu.substring(1, redu.length() - 1)).append(")G,").append(" SUM( ")
				.append(compro.substring(1, compro.length() - 1)).append(")CPT ");

		sSql.append(" FROM").append(" PASO P JOIN NATGAS N ").append(" ON N.CLVGAS= P.PARTIDA ")
				.append(" AND N.IDSECTOR=P.IDSECTOR ").append(" WHERE ").append(" P.IDSECTOR = " + idsector)
				.append(" AND SUBSTR(P.PROGRAMA,1,6)='" + xdire + "'").append(" GROUP BY P.PARTIDA,N.NOMGAS)")
				.append("T1");
		return sSql.toString();
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getNameFuncion() {
		return nameFuncion;
	}

	public void setNameFuncion(String nameFuncion) {
		this.nameFuncion = nameFuncion;
	}

	public List<TcMes> getListTcMes() {
		return listTcMes;
	}

	public void setListTcMes(List<TcMes> listTcMes) {
		this.listTcMes = listTcMes;
	}

	public List<Muninep> getListMuninep() {
		return listMuninep;
	}

	public void setListMuninep(List<Muninep> listMuninep) {
		this.listMuninep = listMuninep;
	}

	public TcMesRepository getTcmesRepository() {
		return tcmesRepository;
	}

	public void setTcmesRepository(TcMesRepository tcmesRepository) {
		this.tcmesRepository = tcmesRepository;
	}

	public MuniNepRepository getMuninepRepository() {
		return muninepRepository;
	}

	public void setMuninepRepository(MuniNepRepository muninepRepository) {
		this.muninepRepository = muninepRepository;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
