package com.gem.sistema.util;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.imageio.plugins.tiff.TIFFField;
import javax.imageio.stream.ImageInputStream;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

/**
 * Lectura de TIFF multipagina para generar PDF.
 *
 * Sustituye a {@code com.lowagie.text.pdf.codec.TiffImage}, que no existe en el OpenPDF incluido con
 * JasperReports 7. Usa el lector TIFF nativo del JDK y conserva la resolucion (DPI) de cada pagina,
 * que es lo que el codigo original usa para escalar la imagen.
 */
public class TiffPages implements Closeable {

	private final ImageInputStream input;
	private final ImageReader reader;

	public TiffPages(String path) throws IOException {
		input = ImageIO.createImageInputStream(new File(path));
		if (input == null) {
			throw new IOException("No se puede abrir " + path);
		}
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("tiff");
		if (!readers.hasNext()) {
			input.close();
			throw new IOException("El JDK no tiene lector TIFF");
		}
		reader = readers.next();
		reader.setInput(input, false);
	}

	public int getNumberOfPages() throws IOException {
		return reader.getNumImages(true);
	}

	/** Pagina numerada desde 1, como en TiffImage. */
	public Image getPage(int page) throws IOException, BadElementException {
		BufferedImage image = reader.read(page - 1);
		Image img = Image.getInstance(image, null);
		IIOMetadata metadata = reader.getImageMetadata(page - 1);
		TIFFDirectory dir = TIFFDirectory.createFromMetadata(metadata);
		img.setDpi(dpi(dir, BaselineTIFFTagSet.TAG_X_RESOLUTION), dpi(dir, BaselineTIFFTagSet.TAG_Y_RESOLUTION));
		return img;
	}

	private static int dpi(TIFFDirectory dir, int tag) {
		TIFFField field = dir.getTIFFField(tag);
		if (field == null) {
			return 0;
		}
		double value = field.getAsDouble(0);
		TIFFField unit = dir.getTIFFField(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT);
		if (unit != null && unit.getAsInt(0) == BaselineTIFFTagSet.RESOLUTION_UNIT_CENTIMETER) {
			value *= 2.54;
		}
		return (int) value;
	}

	@Override
	public void close() throws IOException {
		reader.dispose();
		input.close();
	}
}
