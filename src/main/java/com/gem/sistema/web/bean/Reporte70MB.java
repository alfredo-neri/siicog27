package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte70MB.
 */
@Component("reporte70MB")
@Scope("view")
public class Reporte70MB extends GenericExecuteProcedure {

	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_RESUMEN_QUINSENA";

	private StreamedContent fileTxt;

	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The numero. */
	private Integer numero;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

		numero = 1;

	}

	/** The out. */
	Map<String, Object> out;

	/** The stream. */
	InputStream stream = null;

	@Override
	public void downLoadFile() throws ReportValidationException {
		addToBitacora(true);
		out = executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			fileTxt = StreamedContents.of(stream, "application/txt", out.get("O_NAME_FILE").toString(), null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		addToBitacora(false);
	}

	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		List<String> querys = this.generateQuerys(conctb.getAnoemp(), this.getUserDetails().getIdSector());

		parameter.addValue("i_mes", mes);
		parameter.addValue("i_numero", numero);
		parameter.addValue("i_query1", querys.get(0));
		parameter.addValue("i_query2", querys.get(1));

		return parameter;
	}

	private List<String> generateQuerys(Integer anio, Integer sector) {

		List<String> list = new ArrayList<String>();
		StringBuilder sql1 = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();

		sql1.append("SELECT SSSCTA, SUM(CANPOL) CARGOS, SUM(CANPOL) SOGRAC, ")
				.append("SUM(CANPOLH) ABONOS, SUM(CANPOLH) SONOBA, ").append("(SUM(CANPOL)-SUM(CANPOLH)) SALDO ")
				.append("FROM POLIDE ").append("WHERE TIPPOL = 'E' " + "AND CONPOL = ").append(numero)
				.append(" AND MESPOL = ").append(mes).append(" AND ANOPOL = ").append(anio)
				.append(" AND CUENTA = '5100' ").append(" AND SUBSTR(SSSCTA,1,1) = '1' ").append(" AND IDSECTOR = ")
				.append(sector).append(" GROUP BY SSSCTA ORDER BY SSSCTA");
		list.add(sql1.toString());

		sql2.append("SELECT CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, SUM(CANPOL) CARGOS, ")
				.append("SUM(CANPOL) SOGRAC, SUM(CANPOLH) ABONOS, SUM(CANPOLH) SONOBA, ")
				.append(" (SUM(CANPOL)-SUM(CANPOLH)) SALDO ").append(" FROM POLIDE WHERE TIPPOL = 'E' AND CONPOL = ")
				.append(numero).append(" AND MESPOL = ").append(mes).append(" AND anopol = ").append(anio)
				.append(" AND cuenta <> '5100' AND IDSECTOR = ").append(sector)
				.append(" AND SUBSTR(CUENTA,1,1) != '8' ").append("GROUP BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA ")
				.append("ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA");
		list.add(sql2.toString());

		return list;
	}

	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
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
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
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

	public Map<String, Object> getOut() {
		return out;
	}

	public void setOut(Map<String, Object> out) {
		this.out = out;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

}
