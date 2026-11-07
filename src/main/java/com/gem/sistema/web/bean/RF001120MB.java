package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF001120MB.
 */
@ManagedBean
@ViewScoped
public class RF001120MB extends GenericExecuteProcedure {

	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The file txt 1. */
	private StreamedContent fileTxt1;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/**
	 * Gets the file txt 1.
	 *
	 * @return the file txt 1
	 */
	public StreamedContent getFileTxt1() {
		return fileTxt1;
	}

	/**
	 * Sets the file txt 1.
	 *
	 * @param fileTxt1 the new file txt 1
	 */
	public void setFileTxt1(StreamedContent fileTxt1) {
		this.fileTxt1 = fileTxt1;
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

		procedureName = "SP_GENERA_TXT_RF001120";

		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_usuario", this.getUserDetails().getUsername()).addValue("i_mes", Integer.valueOf(mes));

		return parametros;
	}

	/** The stream. */
	InputStream stream = null;

	/** The out. */
	Map<String, Object> out;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		out = new HashMap<String, Object>();
		out = executePlService.callProcedure(procedureName, getParametersReports());

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			String zipFile = "/gem/downcvs/ExportaInf.zip";

			String[] srcFiles = { out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE1").toString(),
					out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE2").toString(),
					out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE3").toString(),
					out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE4").toString() };
			try {
				// create byte buffer
				byte[] buffer = new byte[1024];
				FileOutputStream fos = new FileOutputStream(zipFile);
				ZipOutputStream zos = new ZipOutputStream(fos);
				FileInputStream fis = null;
				for (int i = 0; i < srcFiles.length; i++) {
					File srcFile = new File(srcFiles[i]);
					fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));
					int length;

					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}

				}
				zos.closeEntry();

				// close the InputStream
				fis.close();
				// close the ZipOutputStream
				zos.close();
				stream = new FileInputStream(new File(zipFile.toString()));
				fileTxt1 = new DefaultStreamedContent(stream, "application/zip", zipFile.toString());
			} catch (IOException ioe) {

				System.out.println("Error creating zip file: " + ioe);

			}

		}

	}

}
