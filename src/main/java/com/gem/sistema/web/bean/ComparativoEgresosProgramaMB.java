package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getMonthName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.Constants;
import com.gem.sistema.util.ConstantsClaveEnnum;

/**
 * @author buser
 *
 */
@ManagedBean(name = "comparativoEgresosProgramaMB")
@ViewScoped
public class ComparativoEgresosProgramaMB extends BaseDirectReport {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";
	private static final String CLAVE_REPORT = "RPT_COMPARATIVO_EGRE_GLOBAL_PROG";

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	private List<TcMes> listMes;

	private Conctb conctb;

	private String mes;

	private Integer nfirmas;

	private String programa;

	private String nomPro;

	private List<Integer> listNFirmas;

	private List<Xcatpro> listPrograma;

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public List<TcMes> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getNfirmas() {
		return nfirmas;
	}

	public void setNfirmas(Integer nfirmas) {
		this.nfirmas = nfirmas;
	}

	public List<Integer> getListNFirmas() {
		return listNFirmas;
	}

	public void setListNFirmas(List<Integer> listNFirmas) {
		this.listNFirmas = listNFirmas;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	public List<Xcatpro> getListPrograma() {
		return listPrograma;
	}

	public void setListPrograma(List<Xcatpro> listPrograma) {
		this.listPrograma = listPrograma;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getNomPro() {
		return nomPro;
	}

	public void setNomPro(String nomPro) {
		this.nomPro = nomPro;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	@PostConstruct
	public void init() {

		listMes = tcMesRepository.findAll();
		listPrograma = xcatproRepository.findBySectorid(this.getUserDetails().getIdSector());

		// se inicializa las listas
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

		listNFirmas = new ArrayList<Integer>();
		for (int i = 1; i <= 3; i++) {
			listNFirmas.add(i);
		}
		nfirmas = listNFirmas.get(0);

		if (!CollectionUtils.isEmpty(listPrograma)) {

			programa = listPrograma.get(0).getClvpro();
			nomPro = listPrograma.get(0).getNompro();

		}

		LOGGER.info(":: En postconsruct comparativoEgresosProgramaMB ");
		jasperReporteName = "CompEgrePrograma";
		endFilename = jasperReporteName + ".pdf";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();

		conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		parameters.put("mesLetra", getMonthName(Integer.valueOf(mes)));
		parameters.put("mes", Integer.valueOf(mes));
		parameters.put("year", conctb.getAnoemp());
		parameters.put("sQuery",
				this.generateQuery(Integer.valueOf(mes), this.getUserDetails().getIdSector(), programa));

		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		parameters.put("pImg1", conctb.getImagePathRigth());

		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		endFilename = getNameFileReport(mes, conctb, reporte);
		parameters.put("entidadName", conctb.getNomDep());
		parameters.put("entidadRfc", conctb.getRfc());
		parameters.put("imagePath", this.getUserDetails().getPathImgCab1());
		parameters.put("pPrograma", programa);
		parameters.put("pProgramaNom", nomPro);

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public String generateQuery(Integer mes, Integer sector, String programa) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder sSd = new StringBuilder();
		StringBuilder sSe = new StringBuilder();
		StringBuilder sXampli = new StringBuilder();
		StringBuilder sXredu = new StringBuilder();
		sSd.append(" THEN (P.AUTO1_1");
		sSe.append(" THEN (C.CARGOS_1 - C.ABONOS_1)");
		sXampli.append(" THEN (P.AMPLI1_1");
		sXredu.append(" THEN (P.REDU1_1");
		for (int i = 2; i <= mes; i++) {
			sSd.append(" + P.AUTO1_" + i);
			sSe.append(" + (C.CARGOS_" + i + " - C.ABONOS_" + i + ")");
			sXampli.append(" + P.AMPLI1_" + i);
			sXredu.append(" + P.REDU1_" + i);
		}
		sSd.append(" ) ");
		sSe.append(" ");
		sXampli.append(" ) ");
		sXredu.append(" ) ");

		/*
		 * System.out.println(sSd); System.out.println(sSe);
		 * System.out.println(sXampli); System.out.println(sXredu);
		 */

		sSql.append(" SELECT   ");
		sSql.append(" NOMBRE Z1");
		sSql.append(" , ABS(SA) Z2 ");
		sSql.append(" , ABS(SB) Z3 ");
		sSql.append(" , SC Z4 ");
		sSql.append(" , ABS(SD + XAMPLI - XREDU) Z5 ");
		sSql.append(" , SE Z6 ");
		sSql.append(" , (SE - SD + XAMPLI - XREDU) Z7 ");
		sSql.append(" FROM ( ");
		sSql.append(" SELECT  ");
		sSql.append(" NOMBRE ");
		sSql.append(" , SUM(SA) SA ");
		sSql.append(" , SUM(SB) SB ");
		sSql.append(" , SUM(SC) SC ");
		sSql.append(" , SUM(SD) SD ");
		sSql.append(" , SUM(SE) SE ");
		sSql.append(" , SUM(XAMPLI) XAMPLI ");
		sSql.append(" , SUM(XREDU) XREDU ");
		sSql.append(" FROM ( ");
		sSql.append(" SELECT  ");
		sSql.append(" NOMBRE ");
		sSql.append(" , SA ");
		sSql.append(" , SB ");
		sSql.append(" , SC ");
		sSql.append(" , SD ");
		sSql.append(" , SE ");
		sSql.append(" , XAMPLI ");
		sSql.append(" , XREDU ");
		sSql.append(" FROM ( ");
		sSql.append(" SELECT ");
		sSql.append(" CASE  ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '1001' AND '1999' THEN '1000 SERVICIOS PERSONALES'  ");
		sSql.append(
				"    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '2001' AND '2999' THEN '2000 MATERIALES Y SUMINISTROS'  ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '3001' AND '3999' THEN '3000 SERVICIOS GENERALES'  ");
		sSql.append(
				"    WHEN C.CUENTA=8212 AND C.SSSCTA BETWEEN '4001' AND '4999' THEN '4000 TRANSFERENCIAS, ASIGNACIONES, SUBSIDIOS Y OTRAS'  ");
		sSql.append(
				"    WHEN C.CUENTA=8215 AND C.SSSCTA BETWEEN '5001' AND '5999' THEN '5000 BIENES MUEBLES,INMUEBLES E INTANGIBLES'  ");
		sSql.append("    WHEN C.CUENTA=8216 AND C.SSSCTA BETWEEN '6001' AND '6999' THEN '6000 INVERSION PUBLICA'  ");
		sSql.append(
				"    WHEN C.CUENTA=8217 AND C.SSSCTA BETWEEN '7001' AND '7999' THEN '7000 INVERSIONES FINANCIERAS Y OTRAS PROVISIONES'  ");
		sSql.append(
				"    WHEN C.CUENTA=8213 AND C.SSSCTA BETWEEN '8001' AND '8999' THEN '8000 PARTICIPACIONES Y APORTACIONES'  ");
		sSql.append("    WHEN C.CUENTA=8214 AND C.SSSCTA BETWEEN '9001' AND '9899' THEN '9000 DEUDA PUBLICA'  ");
		sSql.append("    ELSE '' ");
		sSql.append(" END NOMBRE  ");
		sSql.append(" , CASE  ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '1001' AND '1999' THEN C.SALINI ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '2001' AND '2999' THEN C.SALINI ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '3001' AND '3999' THEN C.SALINI ");
		sSql.append("    WHEN C.CUENTA=8212 AND C.SSSCTA BETWEEN '4001' AND '4999' THEN C.SALINI ");
		sSql.append("    WHEN C.CUENTA=8215 AND C.SSSCTA BETWEEN '5001' AND '5999' THEN C.SALINI ");
		sSql.append("    WHEN C.CUENTA=8216 AND C.SSSCTA BETWEEN '6001' AND '6999' THEN C.SALINI ");
		sSql.append("    WHEN C.CUENTA=8217 AND C.SSSCTA BETWEEN '7001' AND '7999' THEN C.SALINI ");
		sSql.append("    WHEN C.CUENTA=8213 AND C.SSSCTA BETWEEN '8001' AND '8999' THEN C.SALINI ");
		sSql.append("    WHEN C.CUENTA=8214 AND C.SSSCTA BETWEEN '9001' AND '9899' THEN C.SALINI   ");
		sSql.append("    ELSE 0.00 ");
		sSql.append(" END SA ");
		sSql.append(" , CASE  ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '1001' AND '1999' THEN (P.AUTO1_" + mes
				+ " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + ") ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '2001' AND '2999' THEN (P.AUTO1_" + mes
				+ " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + ") ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '3001' AND '3999' THEN (P.AUTO1_" + mes
				+ " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + ") ");
		sSql.append("    WHEN C.CUENTA=8212 AND C.SSSCTA BETWEEN '4001' AND '4999' THEN (P.AUTO1_" + mes
				+ " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + ") ");
		sSql.append("    WHEN C.CUENTA=8215 AND C.SSSCTA BETWEEN '5001' AND '5999' THEN (P.AUTO1_" + mes
				+ " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + ") ");
		sSql.append("    WHEN C.CUENTA=8216 AND C.SSSCTA BETWEEN '6001' AND '6999' THEN (P.AUTO1_" + mes
				+ " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + ") ");
		sSql.append("    WHEN C.CUENTA=8217 AND C.SSSCTA BETWEEN '7001' AND '7999' THEN (P.AUTO1_" + mes
				+ " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + ") ");
		sSql.append("    WHEN C.CUENTA=8213 AND C.SSSCTA BETWEEN '8001' AND '8999' THEN (P.AUTO1_" + mes
				+ " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + ") ");
		sSql.append("    WHEN C.CUENTA=8214 AND C.SSSCTA BETWEEN '9001' AND '9899' THEN (P.AUTO1_" + mes
				+ " + P.AMPLI1_" + mes + " - P.REDU1_" + mes + ")   ");
		sSql.append("    ELSE 0.0 ");
		sSql.append(" END SB  ");
		sSql.append(" , CASE ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '1001' AND '1999' ").append(sSd);
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '2001' AND '2999' ").append(sSd);
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '3001' AND '3999' ").append(sSd);
		sSql.append("    WHEN C.CUENTA=8212 AND C.SSSCTA BETWEEN '4001' AND '4999' ").append(sSd);
		sSql.append("    WHEN C.CUENTA=8215 AND C.SSSCTA BETWEEN '5001' AND '5999' ").append(sSd);
		sSql.append("    WHEN C.CUENTA=8216 AND C.SSSCTA BETWEEN '6001' AND '6999' ").append(sSd);
		sSql.append("    WHEN C.CUENTA=8217 AND C.SSSCTA BETWEEN '7001' AND '7999' ").append(sSd);
		sSql.append("    WHEN C.CUENTA=8213 AND C.SSSCTA BETWEEN '8001' AND '8999' ").append(sSd);
		sSql.append("    WHEN C.CUENTA=8214 AND C.SSSCTA BETWEEN '9001' AND '9899' ").append(sSd);
		sSql.append("    ELSE 0.0 ");
		sSql.append(" END SD ");
		sSql.append(" , 0.00 SE ");
		sSql.append(" , CASE ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '1001' AND '1999'  ").append(sXampli);
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '2001' AND '2999'  ").append(sXampli);
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '3001' AND '3999'  ").append(sXampli);
		sSql.append("    WHEN C.CUENTA=8212 AND C.SSSCTA BETWEEN '4001' AND '4999'  ").append(sXampli);
		sSql.append("    WHEN C.CUENTA=8215 AND C.SSSCTA BETWEEN '5001' AND '5999'  ").append(sXampli);
		sSql.append("    WHEN C.CUENTA=8216 AND C.SSSCTA BETWEEN '6001' AND '6999'  ").append(sXampli);
		sSql.append("    WHEN C.CUENTA=8217 AND C.SSSCTA BETWEEN '7001' AND '7999'  ").append(sXampli);
		sSql.append("    WHEN C.CUENTA=8213 AND C.SSSCTA BETWEEN '8001' AND '8999'  ").append(sXampli);
		sSql.append("    WHEN C.CUENTA=8214 AND C.SSSCTA BETWEEN '9001' AND '9899'  ").append(sXampli);
		sSql.append("    ELSE 0.0 ");
		sSql.append(" END XAMPLI ");
		sSql.append(" , CASE ");
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '1001' AND '1999' ").append(sXredu);
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '2001' AND '2999' ").append(sXredu);
		sSql.append("    WHEN C.CUENTA=8211 AND C.SSSCTA BETWEEN '3001' AND '3999' ").append(sXredu);
		sSql.append("    WHEN C.CUENTA=8212 AND C.SSSCTA BETWEEN '4001' AND '4999' ").append(sXredu);
		sSql.append("    WHEN C.CUENTA=8215 AND C.SSSCTA BETWEEN '5001' AND '5999' ").append(sXredu);
		sSql.append("    WHEN C.CUENTA=8216 AND C.SSSCTA BETWEEN '6001' AND '6999' ").append(sXredu);
		sSql.append("    WHEN C.CUENTA=8217 AND C.SSSCTA BETWEEN '7001' AND '7999' ").append(sXredu);
		sSql.append("    WHEN C.CUENTA=8213 AND C.SSSCTA BETWEEN '8001' AND '8999' ").append(sXredu);
		sSql.append("    WHEN C.CUENTA=8214 AND C.SSSCTA BETWEEN '9001' AND '9899' ").append(sXredu);
		sSql.append("    ELSE 0.0 ");
		sSql.append(" END XREDU ");
		sSql.append(" ,0.0 SC ");
		sSql.append(
				" FROM CUENTA C LEFT JOIN PASO P ON C.IDSECTOR=P.IDSECTOR AND C.SCTA=P.CLAVE AND C.SSCTA=P.PROGRAMA AND C.SSSCTA=P.PARTIDA AND C.SSSSCTA=''  ");
		sSql.append(" AND C.CUENTA BETWEEN '8200' AND '8299'  ");
		sSql.append(" WHERE P.IDSECTOR=" + sector + " ");
		sSql.append("  AND (P.CLAVE || P.PROGRAMA)= '" + programa + "'");
		sSql.append(" UNION ALL ");
		sSql.append(" SELECT  ");
		sSql.append(" CASE  ");
		sSql.append(
				"    WHEN C.CUENTA IN ('8241', '8251', '8271') AND C.SSSCTA BETWEEN '1001' AND '1999' THEN '1000 SERVICIOS PERSONALES'  ");
		sSql.append(
				"    WHEN C.CUENTA IN ('8241', '8251', '8271') AND C.SSSCTA BETWEEN '2001' AND '2999' THEN '2000 MATERIALES Y SUMINISTROS'  ");
		sSql.append(
				"    WHEN C.CUENTA IN ('8241', '8251', '8271') AND C.SSSCTA BETWEEN '3001' AND '3999' THEN '3000 SERVICIOS GENERALES'  ");
		sSql.append(
				"    WHEN C.CUENTA IN ('8242', '8252', '8272') AND C.SSSCTA BETWEEN '4001' AND '4999' THEN '4000 TRANSFERENCIAS, ASIGNACIONES, SUBSIDIOS Y OTRAS'  ");
		sSql.append(
				"    WHEN C.CUENTA IN ('8245', '8255', '8275') AND C.SSSCTA BETWEEN '5001' AND '5999' THEN '5000 BIENES MUEBLES,INMUEBLES E INTANGIBLES'  ");
		sSql.append(
				"    WHEN C.CUENTA IN ('8246', '8256', '8276') AND C.SSSCTA BETWEEN '6001' AND '6999' THEN '6000 INVERSION PUBLICA'  ");
		sSql.append(
				"    WHEN C.CUENTA IN ('8247', '8257', '8277') AND C.SSSCTA BETWEEN '7001' AND '7999' THEN '7000 INVERSIONES FINANCIERAS Y OTRAS PROVISIONES'  ");
		sSql.append(
				"    WHEN C.CUENTA IN ('8243', '8253', '8273') AND C.SSSCTA BETWEEN '8001' AND '8999' THEN '8000 PARTICIPACIONES Y APORTACIONES'  ");
		sSql.append(
				"    WHEN C.CUENTA IN ('8244', '8254', '8274') AND C.SSSCTA BETWEEN '9001' AND '9899' THEN '9000 DEUDA PUBLICA'  ");
		sSql.append("    ELSE '' ");
		sSql.append(" END NOMBRE  ");
		sSql.append(" , 0.00 SA ");
		sSql.append(" , 0.00 SB ");
		sSql.append(" , 0.00 SD ");
		sSql.append(" , CASE  ");
		sSql.append("    WHEN C.CUENTA IN ('8241', '8251', '8271') AND C.SSSCTA BETWEEN '1001' AND '1999' ")
				.append(sSe);
		sSql.append("    WHEN C.CUENTA IN ('8241', '8251', '8271') AND C.SSSCTA BETWEEN '2001' AND '2999' ")
				.append(sSe);
		sSql.append("    WHEN C.CUENTA IN ('8241', '8251', '8271') AND C.SSSCTA BETWEEN '3001' AND '3999' ")
				.append(sSe);
		sSql.append("    WHEN C.CUENTA IN ('8242', '8252', '8272') AND C.SSSCTA BETWEEN '4001' AND '4999' ")
				.append(sSe);
		sSql.append("    WHEN C.CUENTA IN ('8245', '8255', '8275') AND C.SSSCTA BETWEEN '5001' AND '5999' ")
				.append(sSe);
		sSql.append("    WHEN C.CUENTA IN ('8246', '8256', '8276') AND C.SSSCTA BETWEEN '6001' AND '6999' ")
				.append(sSe);
		sSql.append("    WHEN C.CUENTA IN ('8247', '8257', '8277') AND C.SSSCTA BETWEEN '7001' AND '7999' ")
				.append(sSe);
		sSql.append("    WHEN C.CUENTA IN ('8243', '8253', '8273') AND C.SSSCTA BETWEEN '8001' AND '8999' ")
				.append(sSe);
		sSql.append("    WHEN C.CUENTA IN ('8244', '8254', '8274') AND C.SSSCTA BETWEEN '9001' AND '9899' ")
				.append(sSe);
		sSql.append("    ELSE 0.0 ");
		sSql.append(" END SE ");
		sSql.append(" , 0.00 XAMPLI ");
		sSql.append(" , 0.00 XREDU ");
		sSql.append("  , CASE  ");
		sSql.append(
				"     WHEN C.CUENTA IN ('8241', '8251', '8271') AND C.SSSCTA BETWEEN '1001' AND '1999' THEN  (C.CARGOS_"
						+ mes + " - C.ABONOS_" + mes + ") ");
		sSql.append(
				"     WHEN C.CUENTA IN ('8241', '8251', '8271') AND C.SSSCTA BETWEEN '2001' AND '2999' THEN  (C.CARGOS_"
						+ mes + " - C.ABONOS_" + mes + ") ");
		sSql.append(
				"     WHEN C.CUENTA IN ('8241', '8251', '8271') AND C.SSSCTA BETWEEN '3001' AND '3999' THEN  (C.CARGOS_"
						+ mes + " - C.ABONOS_" + mes + ") ");
		sSql.append(
				"     WHEN C.CUENTA IN ('8242', '8252', '8272') AND C.SSSCTA BETWEEN '4001' AND '4999' THEN  (C.CARGOS_"
						+ mes + " - C.ABONOS_" + mes + ") ");
		sSql.append(
				"     WHEN C.CUENTA IN ('8245', '8255', '8275') AND C.SSSCTA BETWEEN '5001' AND '5999' THEN  (C.CARGOS_"
						+ mes + " - C.ABONOS_" + mes + ") ");
		sSql.append(
				"     WHEN C.CUENTA IN ('8246', '8256', '8276') AND C.SSSCTA BETWEEN '6001' AND '6999' THEN  (C.CARGOS_"
						+ mes + " - C.ABONOS_" + mes + ") ");
		sSql.append(
				"     WHEN C.CUENTA IN ('8247', '8257', '8277') AND C.SSSCTA BETWEEN '7001' AND '7999' THEN  (C.CARGOS_"
						+ mes + " - C.ABONOS_" + mes + ") ");
		sSql.append(
				"     WHEN C.CUENTA IN ('8243', '8253', '8273') AND C.SSSCTA BETWEEN '8001' AND '8999' THEN  (C.CARGOS_"
						+ mes + " - C.ABONOS_" + mes + ") ");
		sSql.append(
				"     WHEN C.CUENTA IN ('8244', '8254', '8274') AND C.SSSCTA BETWEEN '9001' AND '9899' THEN  (C.CARGOS_"
						+ mes + " - C.ABONOS_" + mes + ") ");
		sSql.append("     ELSE 0.00 ");
		sSql.append(" END SC ");
		sSql.append(
				" FROM CUENTA C LEFT JOIN PASO P ON C.IDSECTOR=P.IDSECTOR AND C.SCTA=P.CLAVE AND C.SSCTA=P.PROGRAMA AND C.SSSCTA=P.PARTIDA AND C.SSSSCTA=''  ");
		sSql.append(" AND C.CUENTA BETWEEN '8200' AND '8299'  ");
		sSql.append(" WHERE P.IDSECTOR=" + sector + " ");
		sSql.append(" AND (P.CLAVE || P.PROGRAMA)='" + programa + "'");
		sSql.append(" )TA WHERE TA.NOMBRE <>''  ");
		sSql.append(" ) GROUP BY NOMBRE  ");
		sSql.append(" ) ");

		return sSql.toString();

	}

	public void updateNom() {
		nomPro = xcatproRepository.getNomproByIdSectorAndClvpro(this.getUserDetails().getIdSector(), programa);
	}

	public void downloadTxt() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
		}
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
