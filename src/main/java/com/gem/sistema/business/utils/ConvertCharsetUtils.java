package com.gem.sistema.business.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ConvertCharsetUtils.
 */
public class ConvertCharsetUtils {
	
	/**
	 * Transform.
	 *
	 * @param source the source
	 * @param srcEncoding the src encoding
	 * @param target the target
	 * @param tgtEncoding the tgt encoding
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void transform(File source, String srcEncoding, File target, String tgtEncoding) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), srcEncoding));
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(target), tgtEncoding));) {
			IOUtils.copy(br, bw);
		}
	}

}
