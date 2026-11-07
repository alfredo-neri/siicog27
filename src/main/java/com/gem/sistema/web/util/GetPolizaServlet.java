
package com.gem.sistema.web.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;
import java.io.InputStream;

@WebServlet("/pfs")
public class GetPolizaServlet extends HttpServlet{


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
  throws ServletException, IOException {
    try{
      InputStream is = getServletContext().getResourceAsStream("/WEB-INF/classes/conf-properties.xml");
      Properties props = new Properties();
      props.loadFromXML(is);
      File file = getFactura(props.getProperty("consulta.factura.polizas.path"), req.getParameter("name"));
      System.out.println("file: "+file);
      res.setContentType("application/pdf");
      res.setContentLength(new Long(file.length()).intValue());
      Files.copy(file.toPath(), res.getOutputStream());
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public File getFactura(String path, String filename){
    String fullpath = new StringBuffer(path)
    .append(File.separator)
    .append(filename)
    .append(".pdf")
    .toString();
    File file = new File(fullpath);
    return file.exists() ? file : null;
  }
}
