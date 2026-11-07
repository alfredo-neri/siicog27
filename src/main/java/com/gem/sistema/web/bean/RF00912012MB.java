package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcTipoPoder;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcTipoPoderRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

// TODO: Auto-generated Javadoc
/**
 * The Class RF00912012MB.
 *
 * @author Mateo
 */
@Component("rF00912012MB")
@Scope("view")
public class RF00912012MB extends BaseDirectReport {

	/** The mes. */
	private TcMes mes;

	/** The nom mes. */
	private String nomMes;

	/** The poder. */
	private String poder;

	/** The num poder. */
	private String numPoder;

	/** The list poder. */
	private List<TcTipoPoder> listPoder;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The conctb. */
	private Conctb conctb;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The tc tipo poder repository. */
	@Value("#{tcTipoPoderRepository}")
	private TcTipoPoderRepository tcTipoPoderRepository;

	/** The firmas repository. */
	@Value("#{firmasRepository}")
	private FirmasRepository firmasRepository;

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
	public TcMes getMes() {
		return mes;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(TcMes mes) {
		this.mes = mes;
	}

	/**
	 * Gets the nom mes.
	 *
	 * @return the nom mes
	 */
	public String getNomMes() {
		return nomMes;
	}

	/**
	 * Sets the nom mes.
	 *
	 * @param nomMes the new nom mes
	 */
	public void setNomMes(String nomMes) {
		this.nomMes = nomMes;
	}

	/**
	 * Gets the num poder.
	 *
	 * @return the num poder
	 */
	public String getNumPoder() {
		return numPoder;
	}

	/**
	 * Sets the num poder.
	 *
	 * @param numPoder the new num poder
	 */
	public void setNumPoder(String numPoder) {
		this.numPoder = numPoder;
	}

	/**
	 * Gets the list poder.
	 *
	 * @return the list poder
	 */
	public List<TcTipoPoder> getListPoder() {
		return listPoder;
	}

	/**
	 * Sets the list poder.
	 *
	 * @param listPoder the new list poder
	 */
	public void setListPoder(List<TcTipoPoder> listPoder) {
		this.listPoder = listPoder;
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
	 * Gets the tc tipo poder repository.
	 *
	 * @return the tc tipo poder repository
	 */
	public TcTipoPoderRepository getTcTipoPoderRepository() {
		return tcTipoPoderRepository;
	}

	/**
	 * Sets the tc tipo poder repository.
	 *
	 * @param tcTipoPoderRepository the new tc tipo poder repository
	 */
	public void setTcTipoPoderRepository(TcTipoPoderRepository tcTipoPoderRepository) {
		this.tcTipoPoderRepository = tcTipoPoderRepository;
	}

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
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
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

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "rf009_1_20_12";
		endFilename = jasperReporteName + ".pdf";
		listPoder = tcTipoPoderRepository.findAll();
		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0);
		}

		if (!CollectionUtils.isEmpty(listPoder)) {
			numPoder = String.valueOf(listPoder.get(0).getIdPoder());
		}
	}

	/**
	 * Gets the poder.
	 *
	 * @return the poder
	 */
	public String getPoder() {
		return poder;
	}

	/**
	 * Sets the poder.
	 *
	 * @param poder the new poder
	 */
	public void setPoder(String poder) {
		this.poder = poder;
	}

	/** The param reports. */
	Map<String, Object> paramReports;

	/** The firmas. */
	Firmas firmas;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Integer idSector = this.getUserDetails().getIdSector();
		firmas = this.firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		TcMes nomMes = tcMesRepository.findByMes(mes.getMes());
		TrPuestoFirma firma = new TrPuestoFirma();

		paramReports = new HashMap<String, Object>();
		paramReports.put("pNomMes", nomMes.getDescripcion());
		paramReports.put("pAnioIni", conctb.getAnoemp());
		paramReports.put("pAnioFin", conctb.getAnoemp());
		paramReports.put("pL1", firmas.getL1());
		paramReports.put("pL2", firmas.getL2());
		paramReports.put("pL3", firmas.getL3());
		paramReports.put("pN1", firmas.getN1());
		paramReports.put("pN2", firmas.getN2());
		paramReports.put("pN3", firmas.getN3());

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		paramReports.put("pL1", firma.getPuesto().getPuesto());
		paramReports.put("pN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
		paramReports.put("pL2", firma.getPuesto().getPuesto());
		paramReports.put("pN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
		paramReports.put("pL3", firma.getPuesto().getPuesto());
		paramReports.put("pN3", firma.getNombre());

		paramReports.put("pSql", this.generateQuery(Integer.valueOf(mes.getMes()), numPoder.substring(0, 2)));
		paramReports.put("pImg", this.getUserDetails().getPathImgCab1());
		paramReports.put("pNomPoder", numPoder.substring(2));
		paramReports.put("pLastDay", getLastDay(Integer.parseInt(mes.getMes())));

		return paramReports;
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
	 * @param mes      the mes
	 * @param numPoder the num poder
	 * @return the string
	 */
	public String generateQuery(Integer mes, String numPoder) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		StringBuilder c = new StringBuilder();
		StringBuilder d = new StringBuilder();
		StringBuilder f = new StringBuilder();
		StringBuilder g = new StringBuilder();
		StringBuilder cpt = new StringBuilder();
		a.append(" P.AUTO1_1 ");
		b.append(" P.EJPA1_1 ");
		c.append(" P.EJXPA1_1 ");
		d.append(" P.TOEJE1_1 ");
		f.append(" P.AMPLI1_1 ");
		g.append(" P.REDU1_1 ");
		cpt.append(" P.COMPRO1_1 ");

		for (int i = 2; i < 13; i++) {
			a.append("+ P.AUTO1_" + i + " ");
		}
		for (int i = 2; i <= mes; i++) {
			b.append("+ P.EJPA1_" + i + " ");
			c.append("+ P.EJXPA1_" + i + " ");
			d.append("+ P.TOEJE1_" + i + " ");
			f.append("+ P.AMPLI1_" + i + " ");
			g.append("+ P.REDU1_" + i + " ");
			cpt.append("+ P.COMPRO1_" + i + " ");
		}
		sSql.append(" SELECT PARTIDA,  NOMGAS , A , B , C , D , F , G , CPT , ( ( A + F - G ) -D ) E  "
				+ " FROM( SELECT " + "  P.PARTIDA " + "  , NOMGAS " + "  , SUM( " + a + " ) A " + "  , SUM( " + b
				+ " ) B " + "  , SUM( " + c + " ) C " + "  , SUM( " + d + " ) D " + "  , SUM( " + f + " ) F "
				+ "  , SUM( " + g + " ) G " + "  , SUM( " + cpt + " ) CPT " + "    FROM PASO P, NATGAS N "
				+ "    WHERE P.PARTIDA=N.CLVGAS  " + "      AND P.IDSECTOR= N.IDSECTOR  " + "      AND N.IDSECTOR=2 "
				+ "      AND SUBSTR(P.CLAVE,1,1)='" + numPoder + "' " + "      AND SUBSTR(P.PARTIDA,2,3)='000' "
				+ "  GROUP BY PARTIDA , NOMGAS 	 " + "  ) ORDER BY PARTIDA ");

		return sSql.toString();
	}

}
