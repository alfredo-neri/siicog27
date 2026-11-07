package com.gem.sistema.load.fileupload;

import org.primefaces.model.file.UploadedFile;


public interface POIReadParseService {
	Object[][] readParseWord(UploadedFile word, String[] out);
}
