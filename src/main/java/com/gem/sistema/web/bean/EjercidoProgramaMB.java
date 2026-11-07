package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jakarta.activation.MimetypesFileTypeMap;
import jakarta.annotation.PostConstruct;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import com.gem.sistema.business.service.catalogos.GeneraTxtService;
import com.gem.sistema.util.Constants;

@Component("ejercidoProgramaMB")
@Scope("view")
public class EjercidoProgramaMB {

	private static final String FILE_NAME = "ejercidoPorCapitulo.txt";

	@Value("#{generaTxtService}")
	private GeneraTxtService generaTxtService;

	private StreamedContent file;

	private List<Integer> listMeses;

	private Integer programa = 8;

	private Integer mes;

	@PostConstruct
	public void init() {
		listMeses = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			listMeses.add(i);
		}

		mes = listMeses.get(0);

	}

	public GeneraTxtService getGeneraTxtService() {
		return generaTxtService;
	}

	public void setGeneraTxtService(GeneraTxtService generaTxtService) {
		this.generaTxtService = generaTxtService;
	}

	public StreamedContent getFile() {
		InputStream stream = null;
		try {
			stream = new FileInputStream(this.generaTxtService.getData(mes, FILE_NAME, programa));
		} catch (FileNotFoundException ex) {

		}
		file = StreamedContents.of(stream, Constants.APPLICCTION_CSV, FILE_NAME, null);
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public List<Integer> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<Integer> listMeses) {
		this.listMeses = listMeses;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getPrograma() {
		return programa;
	}

	public void setPrograma(Integer programa) {
		this.programa = programa;
	}

}
