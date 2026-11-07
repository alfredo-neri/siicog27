/**
 * 
 */
package com.gem.sistema.load.fileupload.validators.impl;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gem.sistema.load.fileupload.exceptions.ValidateFileException;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:validators/file-validators.xml","classpath:external-resource.xml"})
public class CsvFileValidatorIngresosTest {
	
	
	/**
     * <p>
     * <b> commentDao. </b>
     * </p>
     */
    @Autowired
    @Qualifier("ingresosCsvValidator")
    FileContentValidator ingresosCsvValidator;
    
    @Autowired
    @Qualifier("filePresupuestoIngreso")
    Resource resource;
    
    
    @Test
    public void testMenu() throws ValidateFileException, IOException  {

       final FileUpload fileUpload = new FileUpload();

        fileUpload.setFile(resource.getFile());

        fileUpload.setNameOriginal(resource.getFilename());

        fileUpload.setNameReal(resource.getFilename());

        this.ingresosCsvValidator.isValid(fileUpload);


    }

	
}
