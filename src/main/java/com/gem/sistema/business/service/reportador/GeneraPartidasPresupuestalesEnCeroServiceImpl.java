package com.gem.sistema.business.service.reportador;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.domain.Paso;
import com.gem.sistema.business.domain.Catsector;
import com.gem.sistema.business.repository.load.PasoRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneraPartidasPresupuestalesEnCeroServiceImpl.
 */
@Service("generaPartidasPresupuestalesEnCeroService")
public class GeneraPartidasPresupuestalesEnCeroServiceImpl implements GeneraPartidasPresupuestalesEnCeroService{

  /** The xcatpro repository. */
  @Autowired
  private XcatproRepository xcatproRepository;

  /** The natgas repository. */
  @Autowired
  private NatgasRepository natgasRepository;

  /** The paso repository. */
  @Autowired
  private PasoRepository pasoRepository;


/* (non-Javadoc)
 * @see com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesEnCeroService#generatePartidasPresPasos(java.lang.Integer, java.io.File, java.lang.String)
 */

  public void generatePartidasPresPasos(Integer idSector, File outputFile, String userName){
    List<Object[]> results= xcatproRepository.getClavesProgramaByIdSector(idSector);
    List<Natgas> resultsNatgas = natgasRepository.findAllWhichNotEndsWithCeros(idSector);

    java.util.Date start = new java.util.Date();
    String filePath="";
      try{
        File egrex = File.createTempFile("egrex.d","info");
        BufferedWriter outEgrex = new BufferedWriter(new FileWriter(egrex));
        File egrey = File.createTempFile("egrey.d","info");
        filePath = egrey.getPath();
        FileWriter outEgrey = new FileWriter(egrey);

        results.stream().forEach((res) -> {
          List <String> row = new ArrayList<String>(Arrays.asList((String)res[0],(String)res[1]));
            int[] i = {0};
            for(Natgas resNatgas:resultsNatgas) {
              i[0]++;
              List <String> rowNatgas = new ArrayList<String>(Arrays.asList((String)res[0],(String)res[1],resNatgas.getClvgas()));

              try{
                if((i[0] % 1000) == 0){
                  outEgrey.flush();
                }
                outEgrey.write(StringUtils.join(rowNatgas, "|")+"\r\n");
              }catch(IOException e){
                e.printStackTrace();
              }
            }
          try{
            outEgrex.write(StringUtils.join(row, "|"));
            outEgrex.newLine();
            outEgrex.flush();
          }catch(IOException e){
            e.printStackTrace();
          }

        });
        outEgrex.close();
        outEgrey.close();
        Map<String, File> files = new <String,File>HashMap();
        files.put("egrey.d.txt", egrey);
        files.put("egrex.d.txt", egrex);
        String notFound = pasoRepository.executeGeneraPartidasPresupuestalesEnCero(idSector,userName).replaceAll("_-_","\r\n");
        char[] buffer = new char[1024];
        File newRows = File.createTempFile("newrows.d","info");
        BufferedWriter outNewrows = new BufferedWriter(new FileWriter(newRows));
        outNewrows.write(notFound);
        outNewrows.close();
        files.put("newrows.d.txt", newRows);
        outputFile = createZipFile(files,outputFile);
    /*}catch(SQLException e){
      e.printStackTrace();*/
    }
    catch(IOException e){
      e.printStackTrace();
    }

    java.util.Date end = new java.util.Date();


  }


  /**
   * Creates the zip file.
   *
   * @param files the files
   * @param fileZip the file zip
   * @return the file
   */
  public File createZipFile(Map<String, File>files, File fileZip){
    try{

    FileOutputStream fos = new FileOutputStream(fileZip);
    final int BUFFER = 2048;
    ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));
     byte data[] = new byte[BUFFER];
    for(Map.Entry<String,File> file: files.entrySet()){
      ZipEntry entry = new ZipEntry(file.getKey());
      zos.putNextEntry(entry);
      FileInputStream fileInputStream = new FileInputStream(file.getValue());
      BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, BUFFER);
      int size = -1;
        while(  (size = bufferedInputStream.read(data, 0, BUFFER)) != -1  )
        {
            zos.write(data, 0, size);
        }
       bufferedInputStream.close();
    }
      zos.close();
      return fileZip;
  }catch(IOException e){
    e.printStackTrace();
  }
  return null;
  }

}
