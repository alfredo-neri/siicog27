package com.gem.sistema.load.fileupload;

import java.io.File;
import java.util.Arrays;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Service;

@Service(value = "poiReadParse")
public class POIReadParseImpl implements POIReadParseService {
	public Object[][] readParseWord(UploadedFile word, String[] out) {
		try {
			XWPFDocument doc = new XWPFDocument(word.getInputstream());

			XWPFWordExtractor wordExtractor = new XWPFWordExtractor(doc);
			String docText = wordExtractor.getText();
			System.out.println(docText);

			long count = Arrays.stream(docText.split("\\s+")).count();
			System.out.println("Total words: " + count);
			wordExtractor.close();
		} catch (Exception e) {

		}

		return null;

	}
}
