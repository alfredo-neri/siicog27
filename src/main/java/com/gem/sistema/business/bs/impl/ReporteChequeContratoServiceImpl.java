package com.gem.sistema.business.bs.impl;

import com.gem.sistema.business.bs.ReporteChequeContratoService;
import com.gem.sistema.business.domain.TwRegistroPagoCheque;
import com.gem.sistema.business.dto.ReporteChequeContratoDTO;
import com.gem.sistema.business.repository.catalogs.RegistroPagoChequesRepository;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service(value = "reporteChequeContratoService")
public class ReporteChequeContratoServiceImpl implements ReporteChequeContratoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String PATH_OUT = "/gem/reportes/";

	@Override
	public String createReport(Map<String, Object> parameters) {

		List<ReporteChequeContratoDTO> dto = generateData(parameters);
		String nameFile = UUID.randomUUID().toString() + ".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet("Reporte De Cheques");

		String[] titulos = { "Folio", "Nombre", "Concepto Cheque", "Numero Pago", "Monto Cheque", "Estatus Cheque",
				"Mes", "Monto Contrato", "Concepto Contrato", "Numero Contrato" };
		// Creamos un formato para las celdas
		HSSFCellStyle style = workbook.createCellStyle();
		// Especificamente que *siempre* muestre dos digitos enteros y dos decimales
		style.setDataFormat(workbook.createDataFormat().getFormat("00.00"));

		Row encabezado = sheet.createRow(0);

		Cell celda = encabezado.createCell(5);
		celda.setCellValue("Reporte de Cheques Generados del Mes: ");
		Map<Integer, Object[]> map = new HashMap<>();
		Integer key = 0;

		for (ReporteChequeContratoDTO r : dto) {
			Object[] obj = { r.getFolio(), r.getDestCheque(), r.getConceptoCheque(), r.getNumeroPago(),
					r.getMontoCheque(), r.getEstatusCheque(), r.getMesCheque(), r.getMontoContrato(),
					r.getConceptoContrato(), r.getNumeroContrato() };
			map.put(key, obj);
			key++;
		}
		Row row = sheet.createRow(1);
		int index = 2;
		for (int i = 0; i < titulos.length; i++) {
			Cell celdaTitulo = row.createCell(i);
			celdaTitulo.setCellStyle(style);
			celdaTitulo.setCellValue(titulos[i]);

		}
		 Set<Integer> keyset = map.keySet();
		int numeroRenglon = 2;
		for (Integer keyAux : keyset) {
			row = sheet.createRow(numeroRenglon++);
			Object[] arregloObjetos = map.get(keyAux);
			int numeroCelda = 0;
			for (Object obj2 : arregloObjetos) {
				Cell cell = row.createCell(numeroCelda++);
				if (obj2 instanceof String) {
					cell.setCellValue((String) obj2);
				} else if (obj2 instanceof BigDecimal) {
					cell.setCellValue(obj2.toString());
				} else if (obj2 instanceof Integer) {
					cell.setCellValue((Integer) obj2);
				}
			}
		}

		try {
			File archivo = new File(PATH_OUT + nameFile);
			FileOutputStream out = new FileOutputStream(archivo);

			workbook.write(out);
			out.close();

		} catch (IOException e) {
			System.err.println("ERROR AL CREAR EL ARCHIVO!");
			e.printStackTrace();
		}

		return PATH_OUT + nameFile;
	}

	private List<ReporteChequeContratoDTO> generateData(Map<String, Object> parameters) {
		String sSql = "SELECT TR.FOLIO,\n" + "       TR.DESTINARIO,\n" + "       TR.CONCEPTO,\n"
				+ "       TR.NUMERO_PAGO,\n" + "       TR.MONTO MONTO_CHEQUE,\n" + "       TS.NOMBRE ESTATUS,\n"
				+ "       TP.NOMBRE T_CHEQUE," + "       TM.DESCRIPCION MES,\n"
				+ "       NVL(TC.IMPORTE_ADQUISICION, 0.00) MONTO_CONTRATO,\n"
				+ "       NVL(TC.OBJETO_ADQUISICION, '') CONCEPTO_CONTRATO,\n"
				+ "       NVL(TC.NUMERO_CONTRATO, '')NUMERO_CONTRATO\n" + "    FROM TW_REGISTRO_PAGO_CHEQUES TR,\n"
				+ "         TC_TIPO_CHEQUE TP,\n" + "         TC_ESTATUS_CHEQUES TS,\n" + "         TC_PERIODO TM\n"
				+ "         LEFT JOIN TR_CHEQUE_CONTRATO TCH ON TCH.ID_CHEQUE     = TR.ID\n"
				+ "         LEFT JOIN TC_CONTRATOS TC ON TCH.ID_CONTRATO = TC.ID \n" + "         \n" + "\n"
				+ "WHERE TR.ID_TIPO_CHEQUE = TP.ID\n" + "  AND TR.ID_ESTATUS     = TS.ID\n"
				+ "  AND TR.ID_MES         = TM.ID";
		List<Object> obj = new ArrayList<Object>();

		for (Map.Entry<String, Object> map : parameters.entrySet()) {

			sSql = sSql + " AND ";
			if (map.getKey().equals("mes")) {
				sSql = sSql + " TM.DESCRIPCION ";
			}
			sSql = sSql + " = ?";
			obj.add(map.getValue());
		}
		List<ReporteChequeContratoDTO> listReporte = this.jdbcTemplate.query(sSql, obj.toArray(),
				new RowMapper<ReporteChequeContratoDTO>() {

					@Override
					public ReporteChequeContratoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						ReporteChequeContratoDTO reporte = new ReporteChequeContratoDTO();
						reporte.setFolio(rs.getInt("FOLIO"));
						reporte.setDestCheque(rs.getString("DESTINARIO"));
						reporte.setConceptoCheque(rs.getString("CONCEPTO"));
						reporte.setNumeroPago(rs.getString("NUMERO_PAGO"));
						reporte.setMontoCheque(rs.getBigDecimal("MONTO_CHEQUE"));
						reporte.setTipoCheque(rs.getString("T_CHEQUE"));
						reporte.setEstatusCheque(rs.getString("ESTATUS"));
						reporte.setMesCheque(rs.getString("MES"));
						reporte.setMontoContrato(rs.getBigDecimal("MONTO_CONTRATO"));
						reporte.setConceptoContrato(rs.getString("CONCEPTO_CONTRATO"));
						reporte.setNumeroContrato(rs.getString("NUMERO_CONTRATO"));
						return reporte;
					}
				});
		return listReporte;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
