package com.gem.sistema.business.service.catalogos;

import java.io.File;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dto.OutputDataProcedure;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;

@Service(value = "matrizCargaService")
public class MatrizCargaServiceImpl implements MatrizCargaService {

	@Autowired
	@Qualifier("matricesCsvValidator")
	FileContentValidator matricesCsvValidator;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall jdbcCallEgresos;

	@Autowired
	public void initCallStore() {
		this.jdbcCallEgresos = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_CARGA_MATRICES");
	}

	@Override
	public Boolean validaArchivo(File newFile) {
		final FileUpload fileUpload = new FileUpload();
		fileUpload.setFile(newFile);
		fileUpload.setNameOriginal(newFile.getName());
		fileUpload.setNameReal(newFile.getName());
		try {
			if (this.matricesCsvValidator.isValid(fileUpload)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public OutputDataProcedure cargaArchivo(String path, String nameFile, Integer sector) {

		SqlParameterSource in = new MapSqlParameterSource().addValue("I_ID_SECTOR", sector)
				.addValue("I_FILE_NAME", nameFile).addValue("I_PATH_FILE", path);

		OutputDataProcedure dataProcedure = new OutputDataProcedure();

		try {
			Map<String, Object> out = jdbcCallEgresos.execute(in);

			dataProcedure.setCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
			dataProcedure.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		return dataProcedure;
	}

}
