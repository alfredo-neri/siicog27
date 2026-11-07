package com.gem.sistema.business.service.catalogos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.gem.sistema.util.CopyFileUtil;

@Service(value = "convertService")
public class ConvertServiceImpl implements ConvertService {
	private static final String COMILLAS = "\"";
	private static final String PIPE_CAD = "\"|\"";
	private static final String RETURN_CARD = "\r";
	private static Workbook workbook;
	private static final String PATH_OUT = "/gem/municipal/";
	private static final String TXT = ".txt";
	String pathFull = "";

	@Override
	public String readExcel(InputStream file, String fileNameIn, String fileNameOut) {
		StringBuilder cad = new StringBuilder();
		createDirectory();
		try {
			pathFull = CopyFileUtil.copyFile(fileNameIn, file, PATH_OUT);
			FileInputStream inputStream = new FileInputStream(new File(pathFull));
			workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<?> iterator = firstSheet.iterator();
			DataFormatter formatter = new DataFormatter();
			while (iterator.hasNext()) {
				Row nextRow = (Row) iterator.next();
				Iterator<?> cellIterator = nextRow.cellIterator();
				int posX = 0;
				cad.append(COMILLAS);

				while (cellIterator.hasNext()) {
					Cell cell = (Cell) cellIterator.next();
					//String contenidoCelda = formatter.formatCellValue(cell);
					String contenidoCelda = formatter.formatCellValue(cell);
					if(cell.getCellType().equals(CellType.FORMULA)) {
						contenidoCelda = "" + convertToDecimal(cell.getNumericCellValue());
					}	
						

					posX++;
					cad.append(contenidoCelda).append(posX == nextRow.getLastCellNum() ? COMILLAS : PIPE_CAD);
				}
				cad.append(RETURN_CARD);

				convertXlsToTxt(cad.toString(), fileNameOut);

			}
			return PATH_OUT.concat(fileNameOut).concat(TXT);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}
	
	private  BigDecimal convertToDecimal(Object obj) {
		BigDecimal con = new BigDecimal(obj.toString()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return con;
	}
	
	private void createDirectory() {
		File directorio = new File(PATH_OUT);
		if (!directorio.exists()) {
			if (directorio.mkdirs()) {
				System.out.println("Directorio creado");
			} else {
				System.out.println("Error al crear directorio");
			}
		}
	}


	private void convertXlsToTxt(String cad, String fileNameOut) {

		try {

			File archivo = new File(PATH_OUT.concat(fileNameOut).concat(TXT));
			BufferedWriter bw;
			FileOutputStream fos = new FileOutputStream(archivo);
			OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			bw = new BufferedWriter(osw);
			bw.write(cad);
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
