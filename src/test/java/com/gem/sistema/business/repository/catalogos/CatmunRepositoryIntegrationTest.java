package com.gem.sistema.business.repository.catalogos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gem.sistema.business.domain.Catmun;
import com.gem.sistema.business.repository.base.AbstractIntegrationTest;
import com.gem.sistema.business.repository.catalogs.CatmunRepository;

public class CatmunRepositoryIntegrationTest extends AbstractIntegrationTest{
	@Autowired CatmunRepository repository;
	
	@Test
	public void saveOriginalRecords() {
	    CSVParser parser=null;
	    CSVFormat formatCatmun = CSVFormat.newFormat(' ')
	        .withIgnoreEmptyLines(true)
	        .withQuote('"')
	        .withIgnoreSurroundingSpaces(true);

	    ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("catmun.txt").getFile());
	    
	    try {
			parser = new CSVParser(
			        new FileReader(file),
			        formatCatmun);

			Catmun catmunVo;
			for (CSVRecord record : parser) {
				catmunVo = new Catmun(record);
            	repository.save(catmunVo);
            }
            parser.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
