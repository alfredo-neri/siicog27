package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010116MB.
 */
@ManagedBean(name = "rF0010116MB")
@ViewScoped
public class RF0010116MB extends BaseDirectReport {

	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
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
		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
		jasperReporteName = "RF0010116_EdoA";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters;
		TcMes descripMes = tcMesRepository.findByMes(mes);
		TrPuestoFirma firma = new TrPuestoFirma();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);

		parameters = new HashMap<String, Object>();
		parameters.put("pImg", sector == 1 ? conctb.getImagePathRigth() : conctb.getImagePathLeft());
		parameters.put("pDescripMun", conctb.getNomDep());
		parameters.put("pLastDay", getLastDay(Integer.valueOf(mes)));
		parameters.put("pDescripMes", descripMes.getDescripcion());
		parameters.put("pId", sector);
		parameters.put("pAnio", conctb.getAnoemp());
		String mes2 = StringUtils.leftPad(mes.toString(), 2, "0");
		TcMes descipMes = tcMesRepository.findByMes(mes2);
		endFilename = "EDO ANA DEU Y OTR PAS " + mes2 + conctb.getAnoemp() + ".pdf";

		if (sector == 1) {
			switch (conctb.getClave().substring(0, 1)) {
			case "0":
				parameters.put("pNoFirmas", 3);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F01.getValue());
				parameters.put("pL1", firma.getPuesto().getPuesto());
				parameters.put("pN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F03.getValue());
				parameters.put("pL2", firma.getPuesto().getPuesto());
				parameters.put("pN2", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F02.getValue());
				parameters.put("pL3", firma.getPuesto().getPuesto());
				parameters.put("pN3", firma.getNombre());
				
				parameters.put("tipo", "Ayuntamiento ");
				parameters.put("noEntity", conctb.getClave());
				break;
			case "2": // ODAS
				parameters.put("pNoFirmas", 3);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F10.getValue());
				parameters.put("pL1", firma.getPuesto().getPuesto());
				parameters.put("pN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F11.getValue());
				parameters.put("pL2", firma.getPuesto().getPuesto());
				parameters.put("pN2", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F12.getValue());
				parameters.put("pL3", firma.getPuesto().getPuesto());
				parameters.put("pN3", firma.getNombre());
				
				parameters.put("tipo", "Organismo de Agua ");
				parameters.put("noEntity", conctb.getClave());
				break;
			case "3":
				parameters.put("pNoFirmas", 3);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F07.getValue());
				parameters.put("pL1", firma.getPuesto().getPuesto());
				parameters.put("pN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F08.getValue());
				parameters.put("pL2", firma.getPuesto().getPuesto());
				parameters.put("pN2", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F09.getValue());
				parameters.put("pL3", firma.getPuesto().getPuesto());
				parameters.put("pN3", firma.getNombre());
				
				parameters.put("tipo", "DIF Municipal ");
				parameters.put("noEntity", conctb.getClave());
				break;
			case "4":
				parameters.put("pNoFirmas", 2);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F20.getValue());
				parameters.put("pL1", firma.getPuesto().getPuesto());
				parameters.put("pN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
						ConstantsClaveEnnum.CLAVE_F21.getValue());
				parameters.put("pL3", firma.getPuesto().getPuesto());
				parameters.put("pN3", firma.getNombre());
				
				parameters.put("tipo", "Organismo del Deporte ");
				parameters.put("noEntity", conctb.getClave());
				break;
			}

		} else {
			parameters.put("pNoFirmas", 3);
			firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("pL2", firma.getPuesto().getPuesto());
			parameters.put("pN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			
			parameters.put("tipo", " ");
			parameters.put("noEntity", conctb.getClave());
		}

//		parameters.put("pSsql2", this.generateQuery(this.getUserDetails().getIdSector(), Integer.valueOf(mes)));
		parameters.put("pSql", this.generatePasivo(sector, Integer.valueOf(mes)));
		return parameters;
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
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generateQuery(Integer idsector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder debe1 = new StringBuilder();
		StringBuilder haber1 = new StringBuilder();

		sSql.append("SELECT ").append("T2.CUENTA,").append("T2.NAT,").append("T2.NOMBRE,").append("T2.SALDOINI,")
				.append("T2.SIHABER,").append("CASE WHEN T2.NAT <>'D'").append(" THEN T2.SIHABER-T2.DEBE2+T2.HABER2")
				.append(" ELSE 0.00 ").append(" END SALDOFINPER ").append("FROM ").append(" (SELECT ")
				.append("T1.CUENTA,").append(" T1.NAT NAT,").append("T1.NOMBRE NOMBRE,").append("T1.SALDOINI SALDOINI,")
				.append(" CASE WHEN T1.NAT <>'D'").append(" THEN  SALDOINI-T1.DEBE1+T1.HABER1").append(" ELSE 0.00 ")
				.append(" END SIHABER,").append("T1.DEBE2,").append("T1.HABER2").append(" FROM ").append("(SELECT ")
				.append(" M.NATCTA NAT,").append("C.NOMCTA NOMBRE,").append("C.CUENTA CUENTA,").append("C.SCTA  SCTA,")
				.append(" C.SSCTA SSCTA,").append("C.SSSCTA SSSCTA,").append("C.SSSSCTA SSSSCTA,")
				.append(" DECODE (C.CUENTA ,'1115',-C.SALINI,C.SALINI,C.CUENTA ,'1161',-C.SALINI, C.SALINI) SALDOINI,")
				.append(" M.NOTCTA,");

		if (mes == 1) {

			sSql.append("SUM (0.00)DEBE1,").append("SUM (0.00)HABER1,");
		} else {

			for (int i = 1; i < mes; i++) {
				debe1.append(" C.CARGOS_" + i + "+");
				haber1.append(" C.ABONOS_" + i + "+");
			}

			sSql.append("SUM(").append(debe1.substring(1, debe1.length() - 1)).append(")DEBE1,").append("SUM( ")
					.append(haber1.substring(1, haber1.length() - 1)).append(")HABER1,");
		}

		sSql.append("C.CARGOS_" + mes).append(" DEBE2,").append("C.ABONOS_" + mes).append(" HABER2")
				.append(" FROM MAYCTA M").append(" JOIN CUENTA C ON C.CUENTA=M.CUENTA")
				.append(" WHERE C.IDSECTOR=" + idsector).append(" AND C.CUENTA >='1001'")
				.append(" AND C.CUENTA <='6039'").append(" AND C.SCTA=''").append("AND C.NOTCTA=0 ")
				.append(" GROUP BY M.NATCTA,").append("C.NOMCTA,").append("C.CUENTA,").append("C.SCTA,")
				.append("C.SSCTA,").append("C.SSSCTA,").append("C.SSSSCTA,").append("C.SALINI,").append("M.NOTCTA,")
				.append("C.CARGOS_" + mes + ",").append(" C.ABONOS_" + mes).append(" ORDER BY C.CUENTA,")
				.append(" C.SCTA,").append("C.SSCTA,").append(" C.SSSCTA,").append("C.SSSSCTA)T1)T2");

		System.out.println(sSql.toString());
		return sSql.toString();
	}

	/**
	 * Generate pasivo.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generatePasivo(Integer idsector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		if (mes > 1) {
			for (int i = 1; i < mes; i++) {
				cargos.append(" + C.CARGOS_" + i);
				abonos.append(" + C.ABONOS_" + i);
			}
		} else {
			cargos.append("  0 ");
			abonos.append("  0 ");
		}

		sSql.append("SELECT CUENTA, NOMCTA, ").append(
				"DECODE(STACTA,'A',(SALINI-CARGOS_ANT+ABONOS_ANT), 'D',(SALINI+CARGOS_ANT-ABONOS_ANT)) SALDO_INICIAL, ")
				.append("DECODE(STACTA,'A',(SALINI-CARGOS+ABONOS), 'D',(SALINI+CARGOS-ABONOS)) SALDO_FINAL")
				.append(" FROM (").append(" SELECT C.CUENTA,  C.STACTA, C.NOMCTA, C.SALINI, ")
				.append(cargos.substring(2)).append(" CARGOS_ANT, ").append(abonos.substring(2)).append(" ABONOS_ANT, ")
				.append(cargos.substring(2)).append(" + C.CARGOS_").append(mes).append(" CARGOS, ")
				.append(abonos.substring(2)).append(" + C.ABONOS_").append(mes).append(" ABONOS ")
				.append(" FROM CUENTA C ").append("	WHERE	C.CUENTA BETWEEN '2000' AND '3000' ")
				.append(" AND C.SCTA = '' ").append(" AND C.NOTCTA = 0 ").append(" AND C.IDSECTOR = ").append(idsector)
				.append(" ) T1");

		System.out.println(sSql.toString());
		return sSql.toString();
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
}
