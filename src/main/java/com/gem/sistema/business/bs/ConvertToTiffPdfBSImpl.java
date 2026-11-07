package com.gem.sistema.business.bs;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;




import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;

// TODO: Auto-generated Javadoc
/**
 * The Class ConvertToTiffPdfBSImpl.
 *
 * @author Mateo
 */
@Repository
public class ConvertToTiffPdfBSImpl implements ConvertToTiffPdfBS {
	
	/** The Constant PDF. */
	private static final String PDF = "pdf";
	
	/** The Constant TMP_PATH. */
	public static final String TMP_PATH = System.getProperty("java.io.tmpdir");
	
	/** The file name. */
	String fileName;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.ConvertToTiffPdfBS#convertFilesToPDF(java.lang.String, java.lang.String)
	 */
	@Override
	public String convertFilesToPDF(String fileName, String uuid) throws Exception {
		String tiff = null;
		String pdf = null;
		String arg[] = {fileName };
		
		if (arg.length < 1) {
			System.out.println("Usage: Tiff2Pdf file1.tif [file2.tif ... fileN.tif]");
			System.exit(1);
		}
		
		
		
		for (int i = 0; i < arg.length; i++) {
			tiff =  arg[i];
			pdf = "/gem/archivoDigital/" + tiff.substring(20, tiff.lastIndexOf('.') + 1) +PDF;
			Document document = new Document(PageSize.A4.rotate());
			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));
				int pages = 0;
				document.open();
				PdfContentByte cb = writer.getDirectContent();
				RandomAccessFileOrArray ra = null;
				int comps = 0;
				try {
					ra = new RandomAccessFileOrArray(tiff);
					comps = TiffImage.getNumberOfPages(ra);
				} catch (Throwable e) {
					System.out.println("Exception in " + tiff + " " + e.getMessage());
					continue;
				}
				System.out.println("Processing: " + tiff);
				for (int c = 0; c < comps; ++c) {
					try {
						Image img = TiffImage.getTiffImage(ra, c + 1);
						if (img != null) {
							System.out.println("page " + (c + 1));
							System.out.println("img.getDpiX() : " + img.getDpiX());
							System.out.println("img.getDpiY() : " + img.getDpiY());
							img.scalePercent(6200f / img.getDpiX(), 6200f / img.getDpiY());
							// img.scalePercent(img.getDpiX(), img.getDpiY());
							document.setPageSize(new Rectangle(img.getScaledWidth(), img.getHeight()));
							img.setAbsolutePosition(0, 0);
							cb.addImage(img);
							document.newPage();
							++pages;
						}
					} catch (Throwable e) {
						System.out.println("Exception " + tiff + " page " + (c + 1) + " " + e.getMessage());
					}
				}
				ra.close();
				document.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return copyFile(pdf.substring(0, 20), pdf.substring(20), uuid, TMP_PATH);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.ConvertToTiffPdfBS#copyFile(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String copyFile(String path, String filename, String uuid, String pathTmp) throws IOException {
		InputStream inputStream = new FileInputStream(new File(path + filename));
		OutputStream output = new FileOutputStream(new File(pathTmp, uuid + filename));

		try {
			IOUtils.copy(inputStream, output);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(output);
		}
		return filename;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.ConvertToTiffPdfBS#removeFile(java.lang.String)
	 */
	@Override
	public void removeFile(String fileName) {
		File removeFile = new File(fileName);
		removeFile.delete();

	}

}
