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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ArchivoConciliarServiceImpl.
 */
@Service("archivoConsiliarService")
public class ArchivoConciliarServiceImpl implements ArchivoConciliarService{

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

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.ArchivoConciliarService#getMonths()
   */
  public List<TcMes> getMonths(){
    return tcMesRepository.findAll();
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.ArchivoConciliarService#findRecordsForFile(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.util.function.Consumer)
   */
  public void findRecordsForFile(Integer month, String cta,
                                 String sscta, String ssscta,
                                 Long idsector,
                                 Consumer <List<String>> consumer){

    Conctb conctb = conctbRepository.findFirstByIdsectorOrderByIdAsc(idsector.intValue());
    System.out.println("Searching for year: "+conctb.getAnoemp());

    for(int pagenum = 0; true; pagenum++){
      Page<Polide> page = polideRepository.getByMonthAndLevels(month, conctb.getAnoemp(), cta, sscta, ssscta, idsector.intValue(),
                                                               new PageRequest(pagenum,10));
      page.forEach((p) -> {
        String xcaopol = "";
        BigDecimal xcanpol = null;

        if(BigDecimal.ZERO.compareTo(p.getCanpol()) != 0 && BigDecimal.ZERO.compareTo(p.getCanpolh()) == 0){
          xcaopol = "D";
          xcanpol = p.getCanpol();
        }

        if(BigDecimal.ZERO.compareTo(p.getCanpol()) == 0 && BigDecimal.ZERO.compareTo(p.getCanpolh()) != 0){
          xcaopol = "h";
          xcanpol = p.getCanpolh();
        }

        Polien polien = polienRepository.findFirstByMespolAndAnopolAndConpolAndTippolAndIdsectorOrderById(p.getMespol(), p.getAnopol(),
                                                                                          p.getConpol(), p.getTippol(),
                                                                                          idsector.intValue());
        String xfecha = "";
        if(polien != null){
          xfecha = new SimpleDateFormat("dd/MM/yyyy").format(polien.getFecpol());
        }
        ArrayList<String> result = new ArrayList<String>();
        result.add(StringUtils.leftPad(""+p.getMespol(),2,"0"));
        result.add(""+(StringUtils.isEmpty(p.getRefpol()) ? "" : p.getRefpol()));
        result.add(xcaopol);
        result.add(""+(xcanpol == null ? "" : xcanpol.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()));
        result.add(p.getCuenta());
        result.add(p.getScta());
        result.add(p.getSscta());
        result.add(" ");
        result.add(""+p.getAnopol());
        result.add(p.getConcep());
        result.add(xfecha);
        consumer.accept(result);
      });
      System.out.println("page: "+page);
      if(page.isLast()) break;
    }
  }
}
