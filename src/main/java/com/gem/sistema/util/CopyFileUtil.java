package com.gem.sistema.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;

import org.apache.commons.io.IOUtils;

public class CopyFileUtil {
	public static void saveFile(String path, String filename, InputStream input) throws IOException {
		// String filename = FilenameUtils.getName(file.getFileName());
		// InputStream input = file.getInputstream();
		OutputStream output = new FileOutputStream(new File(path, filename));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

	public static String copyFile(String filename, InputStream input, String path) throws IOException {
		OutputStream output = new FileOutputStream(new File(path, filename));

		try {
			IOUtils.copy(input, output);
			return path + File.separator + filename;
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);

		}
	}

}
