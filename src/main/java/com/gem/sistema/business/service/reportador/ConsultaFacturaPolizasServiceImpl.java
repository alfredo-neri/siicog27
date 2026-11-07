package com.gem.sistema.business.service.reportador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.TcMes;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaFacturaPolizasServiceImpl.
 */
@Service("consultaFacturaPolizasService")
public class ConsultaFacturaPolizasServiceImpl implements ConsultaFacturaPolizasService{

  /** The conctb repository. */
  @Autowired
  ConctbRepository conctbRepository;

  /** The polide repository. */
  @Autowired
  PolideRepository polideRepository;

  /** The polien repository. */
  @Autowired
  PolienRepository polienRepository;

  /** The tc mes repository. */
  @Autowired
  TcMesRepository tcMesRepository;

  /** The path. */
  @Value("${consulta.factura.polizas.path}")
  private String path;

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.ConsultaFacturaPolizasService#getMonths()
   */
  public List<TcMes> getMonths(){
    return tcMesRepository.findAll();
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.ConsultaFacturaPolizasService#polizaExists(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer)
   */
  public boolean polizaExists(Integer month, Integer conpol, String tippol,
                              Integer idsector){

    Conctb conctb = conctbRepository.findByClvempAndIdsector("1", idsector);
    Polien polien = polienRepository.findFirstByMespolAndAnopolAndConpolAndTippolAndIdsectorOrderById(month, conctb.getAnoemp(), conpol, tippol, idsector);
    return polien != null;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.ConsultaFacturaPolizasService#getFactura(java.lang.String)
   */
  public File getFactura(String filename){
    String fullpath = new StringBuffer(path)
    .append(File.separator)
    .append(filename)
    .append(".pdf")
    .toString();
    System.out.println("path: "+fullpath);
    File file = new File(fullpath);
    return file.exists() ? file : null;
  }
}
