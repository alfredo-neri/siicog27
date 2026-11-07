package com.gem.sistema.business.service.polizas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.CopomeRepository;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Copome;
import com.gem.sistema.util.BigDecimalWrapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

// TODO: Auto-generated Javadoc
/**
 * The Class PolizaCierreServiceImpl.
 */
@Service("polizaCierreService")
public class PolizaCierreServiceImpl implements PolizaCierreService{

  /** The polide repository. */
  @Autowired
  private PolideRepository polideRepository;

  /** The polien repository. */
  @Autowired
  private PolienRepository polienRepository;

  /** The conctb repository. */
  @Autowired
  private ConctbRepository conctbRepository;

  /** The cuenta repository. */
  @Autowired
  private CuentaRepository cuentaRepository;

  /** The copome repository. */
  @Autowired
  private CopomeRepository copomeRepository;

  /** The wmesemp. */
  private Integer wmesemp;
  
  /** The wanoemp. */
  private Integer wanoemp;
  
  /** The wmesafe. */
  private Integer wmesafe;

  /** The months. */
  public String months[] = new String[]{"Enero", "Febrero", "Marzo",
                                        "Abril", "Mayo", "Junio",
                                        "Julio", "Agosto",
                                        "Septiembre", "Octubre",
                                        "Noviembre", "Diciembre"};



  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.polizas.PolizaCierreService#hasValidState(java.lang.String, java.lang.Integer, java.util.function.BiConsumer)
   */
  public Boolean hasValidState(String clave, Integer idSector, BiConsumer<String, String> consumer){
    if(basicValidationsValid(clave, idSector, consumer)){
      Integer errors = new Integer(0);
      Conctb conctb = conctbRepository.findFirstByIdsectorOrderByIdAsc(idSector);

      wmesemp = conctb.getMesemp();
      wanoemp = conctb.getAnoemp();
      wmesafe = null;

      Polien polien = polienRepository.findFirstByStaafeAndIdsectorOrderByAnopolDescMespolDesc("A", idSector);

      if(polien != null){
        wmesafe = polien.getMespol();
      }

      if(!wmesemp.equals(wmesafe)){
        consumer.accept("w", "El último mes en proceso: "+ wmesemp+" es diferente al ultimo mes afectado "+wmesafe);
        errors ++;
      }

      if(!new Integer(12).equals(wmesafe)){
        consumer.accept("w", "El último mes afectado: "+wmesafe+ " no corresponde al mes de diciembre");
        errors++;
      }

      Page<Polien> p = polienRepository.findOneByAnioAndMesOrderByIdAsc(wanoemp,wmesemp,idSector, PageRequest.of(0,1));
      if (p != null && p.getContent().size() > 0){
        consumer.accept("w", "El mes "+p.getContent().get(0).getMespol()+" no esta afectado");
      }

      return errors <= 0;
    }
    return null;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.polizas.PolizaCierreService#runSubprocessWithValidations(java.lang.Integer, java.lang.String)
   */
  public void runSubprocessWithValidations(Integer idSector, String username){
    if(idSector == 2){
      polcie1Central(wmesemp, wanoemp,username, idSector);
      polcie2Central(wmesemp, wanoemp,username, idSector);
      polcie3Central(wmesemp, wanoemp,username, idSector);
    }else{
      polcie1Municipal(wmesemp, wanoemp,username, idSector);
      polcie2Municipal(wmesemp, wanoemp,username, idSector);
      polcie3Municipal(wmesemp, wanoemp,username, idSector);
    }
  }

  /**
   *     Proc methods.
   *
   * @param month the month
   * @param year the year
   * @param username the username
   * @param idSector the id sector
   */
  public void polcie1Central(Integer month, Integer year, String username, Integer idSector){
    // 2 central
    Integer xpol = getXpol(month, year, idSector);
    Integer j = 1;
    BigDecimalWrapper left = new BigDecimalWrapper();
    BigDecimalWrapper right = new BigDecimalWrapper();
    try{
      createPolien(month, year, xpol, username, idSector);
    }catch(Exception e){
      e.printStackTrace();
    }
    j = createPolideWhereCuentaBeginsWith("41", month, year, xpol, j, right, idSector, username);
    j = createPolideWhereCuentaBeginsWith("43", month, year, xpol, j, right, idSector, username);
    j = createPolideWhereCuentaBeginsWith("42", month, year, xpol, j, right, idSector, username);
    j = createPolideWhereCuentaIs("5100", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5200", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5300", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5400", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5600", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5700", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5500", month, year, xpol, j, left, "H", null, idSector, username);
    createPolide(new Cuenta("3211", "0000000001", "","", "" ){{
      this.setAbonos1(left.toBigDecimal());
      this.setCargos1(right.toBigDecimal());
      this.setSalini(new BigDecimal(0));
    }},month, year, xpol, j,"D", username, idSector);
    j++;
    sumCtasInPolien(idSector,xpol,month,year, username, true, "8");

  }


  /**
   * Polcie 1 municipal.
   *
   * @param month the month
   * @param year the year
   * @param username the username
   * @param idSector the id sector
   */
  public void polcie1Municipal(Integer month, Integer year, String username, Integer idSector){
    // 2 municipal
    String right1[] = {"4111", "4112", "4113", "4114", "4115", "4116", "4117",
      "4119", "4121", "4122", "4123", "4124", "4129", "4131", "4132", "4141", "4142",
      "4143", "4144", "4151", "4152", "4159", "4161", "4162", "4163", "4164", "4165",
      "4166", "4167", "4168", "4169", "4171", "4172", "4173", "4174", "4191", "4192"};
    String right2[] = { "4211", "4212", "4213", "4221", "4222", "4223", "4224", "4225"};
    String right3 []= { "4311", "4319", "4321", "4322", "4323", "4324", "4325", "4331",
      "4341", "4391", "4392", "4393", "4394", "4395", "4396", "4399"};
    Integer xpol = getXpol(month, year, idSector);
    int j = 1;
    BigDecimalWrapper left = new BigDecimalWrapper();
    BigDecimalWrapper right = new BigDecimalWrapper();
    for(String cuenta: right1){
      j = createPolideWhereCuentaIsAndSssscta(cuenta, month, year, xpol, j, right, "D", null, idSector, username);
    }
    for(String cuenta: right2){
      j = createPolideWhereCuentaIsAndSssscta(cuenta, month, year, xpol, j, right, "D", null, idSector, username);
    }
    for(String cuenta: right3){
      j = createPolideWhereCuentaIsAndSssscta(cuenta, month, year, xpol, j, right, "D", null, idSector, username);
    }
    j = createPolideWhereCuentaIs("5100", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5200", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5300", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5400", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5600", month, year, xpol, j, left, "H", null, idSector, username);
    j = createPolideWhereCuentaIs("5700", month, year, xpol, j, left, "H", null, idSector, username);
    createPolide(new Cuenta("3211", "000000001", "","", "" ){{
      this.setAbonos1(left.toBigDecimal());
      this.setCargos1(right.toBigDecimal());
      this.setSalini(new BigDecimal(0));
    }},month, year, xpol, j,"D", username, idSector);
    j++;
    sumCtasInPolienMunicipal(idSector,xpol,month,year, username, "6", true);

  }


  /**
   * Polcie 2 central.
   *
   * @param month the month
   * @param year the year
   * @param username the username
   * @param idSector the id sector
   */
  public void polcie2Central(Integer month, Integer year, String username, Integer idSector){
    Integer xpol = getXpol(month, year, idSector);
    Integer j=1;
    BigDecimalWrapper left = new BigDecimalWrapper();
    try{
      createPolien(month, year, xpol, username, idSector);
    }catch(Exception e){
      e.printStackTrace();
    }
    j = createPolideWhereCuentaIs("8150", month, year, xpol, j, left, "H", "8110", idSector, username);
    j = createPolideWhereCuentaIs("8120", month, year, xpol, j, left, "H", "8110", idSector, username);
    sumCtasInPolien(idSector,xpol,month,year, username, false, "8");
  }

  /**
   * Polcie 2 municipal.
   *
   * @param month the month
   * @param year the year
   * @param username the username
   * @param idSector the id sector
   */
  public void polcie2Municipal(Integer month, Integer year, String username, Integer idSector){
    Integer xpol = getXpol(month, year, idSector);
    Integer j=1;
    BigDecimalWrapper left = new BigDecimalWrapper();
    j = createPolideWhereCuentaIs("8150", month, year, xpol, j, left, "H", "8110", idSector, username);
    j = createPolideWhereCuentaIs("8120", month, year, xpol, j, left, "H", "8110", idSector, username);
    sumCtasInPolienMunicipal(idSector,xpol,month,year, username,  "8", true);
  }


  /**
   * Polcie 3 central.
   *
   * @param month the month
   * @param year the year
   * @param username the username
   * @param idSector the id sector
   */
  public void polcie3Central(Integer month, Integer year, String username, Integer idSector){
    Integer xpol = getXpol(month, year, idSector);
    Integer j=1;
    BigDecimalWrapper left = new BigDecimalWrapper();
    try{
      createPolien(month, year, xpol, username, idSector);
    }catch(Exception e){
      e.printStackTrace();
    }
    j = createPolideWhereCuentaBetween("8211", "8217", month, year, xpol, j, left, "D", idSector, username);
    j = createPolideWhereCuentaBetween("8271", "8277", month, year, xpol, j, left, "H", idSector, username);
    j = createPolideWhereCuentaBetween("8251", "8257", month, year, xpol, j, left, "H", idSector, username);
    j = createPolideWhereCuentaBetween("8221", "8227", month, year, xpol, j, left, "H", idSector, username);
    j = createPolideWhereCuentaBetweenWithOverride("8241", "8247", month, year, xpol, j, left, "D", idSector, username);
    sumCtasInPolien(idSector,xpol,month,year, username, false, "8");
  }

  /**
   * Polcie 3 municipal.
   *
   * @param month the month
   * @param year the year
   * @param username the username
   * @param idSector the id sector
   */
  public void polcie3Municipal(Integer month, Integer year, String username, Integer idSector){
    Integer xpol = getXpol(month, year, idSector);
    Integer j=1;
    String accounts[] = {"8211", "8212", "8213", "8214", "8215", "8216", "8217"};
    String accounts2[] = {"8271", "8272", "8273", "8274", "8275", "8276", "8277",
        "8251", "8252", "8253", "8254", "8255", "8256", "8257", "8221", "8222", "8223", "8224", "8225", "8226", "8227"};
    BigDecimalWrapper left = new BigDecimalWrapper();
    for(String account: accounts){
      j = createPolideWhereCuentaIs(account, month, year, xpol, j, left, "D", null, idSector, username);
    }
    for(String account: accounts2){
      j = createPolideWhereCuentaIs(account, month, year, xpol, j, left, "H", null, idSector, username);
    }
    sumCtasInPolienMunicipal(idSector,xpol,month,year, username,  "8", false);

  }


  /**
   * Gets the xpol.
   *
   * @param month the month
   * @param year the year
   * @param idSector the id sector
   * @return the xpol
   */
  private Integer getXpol(Integer month, Integer year, Integer idSector){
    Integer xpol = 1;
    Polien polien = polienRepository.findFirstByMespolAndAnopolAndTippolAndIdsectorOrderByIdDesc(month, year, "D", idSector);
    if(polien != null){
      xpol = polien.getConpol()+1;
    }
    return xpol;
  }

/**
 * Sum ctas in polien.
 *
 * @param idSector the id sector
 * @param xpol the xpol
 * @param month the month
 * @param year the year
 * @param username the username
 * @param createLastPolien the create last polien
 * @param beginWith the begin with
 */
private void sumCtasInPolien(Integer idSector, Integer xpol,Integer month, Integer year,  String username, boolean createLastPolien, String beginWith){
  List<Polide> pols = polideRepository.findByIdsectorAndTippolAndConpolAndMespolAndAnopol(idSector,"D",xpol,month,year);
  BigDecimal sumCta = new BigDecimal(0);
  BigDecimal sumSsscta = new BigDecimal(0);
  BigDecimal sumSssscta = new BigDecimal(0);
  BigDecimal cargos = new BigDecimal(0);
  BigDecimal abonos = new BigDecimal(0);
  BigDecimal cargos600 = new BigDecimal(0);
  BigDecimal abonos600 = new BigDecimal(0);
  if(pols != null){
    for(Polide polide: pols){
      sumCta = sumCta.add(new BigDecimal(polide.getCuenta()));
      sumSsscta = sumSsscta.add(new BigDecimal(StringUtils.defaultIfBlank(polide.getSsscta(),"0")));
      sumSssscta = sumSssscta.add(new BigDecimal(StringUtils.defaultIfBlank(polide.getSssscta(),"0")));
      if(polide.getCaopol().equals("D")){
        BigDecimal canpol = (canpol = polide.getCanpol()) == null ? new BigDecimal(0) : canpol;
        cargos = cargos.add(canpol);
        if(polide.getCuenta().startsWith(beginWith))
          cargos600 = cargos600.add(canpol);
      }else{
        BigDecimal canpolh = (canpolh = polide.getCanpolh()) == null ? new BigDecimal(0) : canpolh;
        abonos = abonos.add(canpolh);
        if(polide.getCuenta().startsWith(beginWith))
          abonos600 = abonos600.add(canpolh);
      }
    }
}
  Polien polien = polienRepository.findFirstByMespolAndAnopolAndConpolAndTippolAndIdsectorOrderById(month,year,xpol,"D",idSector);
  if(polien!=null){
    setCtaAbonosYCargosToPolien(polien,sumCta, sumSsscta, sumSssscta, cargos, abonos, cargos600, abonos600);
    polien.setCtssscta(null);
    polienRepository.save(polien);
  }
  Copome copome =copomeRepository.findFirstByTpcpmeAndMecpmeAndAnopmeAndIdsectorOrderByIdAsc("D", month,year,idSector);
  if(copome!=null){
    copome.setCocpme(xpol);
    copomeRepository.save(copome);
  }
  if(createLastPolien){
    try{
      createPolien(month,year, xpol, username,sumCta, sumSsscta, sumSssscta, cargos, abonos, cargos600, abonos600, idSector);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}

/**
 * Sum ctas in polien municipal.
 *
 * @param idSector the id sector
 * @param xpol the xpol
 * @param month the month
 * @param year the year
 * @param username the username
 * @param beginWith the begin with
 * @param allSums the all sums
 */
private void sumCtasInPolienMunicipal(Integer idSector, Integer xpol,Integer month,
                                      Integer year,  String username, String beginWith,
                                      boolean allSums){
  List<Polide> pols = polideRepository.findByIdsectorAndTippolAndConpolAndMespolAndAnopol(idSector,"D",xpol,month,year);
  BigDecimal sumCta = new BigDecimal(0);
  BigDecimal sumSsscta = new BigDecimal(0);
  BigDecimal sumSssscta = new BigDecimal(0);
  BigDecimal cargos = new BigDecimal(0);
  BigDecimal abonos = new BigDecimal(0);
  BigDecimal cargos600 = new BigDecimal(0);
  BigDecimal abonos600 = new BigDecimal(0);
  if(pols != null){
    for(Polide polide: pols){
      sumCta = sumCta.add(new BigDecimal(polide.getCuenta()));
      sumSsscta = sumSsscta.add(new BigDecimal(StringUtils.defaultIfBlank(polide.getSsscta(),"0")));
      sumSssscta = sumSssscta.add(new BigDecimal(StringUtils.defaultIfBlank(polide.getSssscta(),"0")));
      if(polide.getCaopol().equals("D")){
        BigDecimal canpol = (canpol = polide.getCanpol()) == null ? new BigDecimal(0) : canpol;
        cargos = cargos.add(canpol);
        if(polide.getCuenta().startsWith(beginWith))
          cargos600 = cargos600.add(canpol);
      }else{
        BigDecimal canpolh = (canpolh = polide.getCanpolh()) == null ? new BigDecimal(0) : canpolh;
        abonos = abonos.add(canpolh);
        if(polide.getCuenta().startsWith(beginWith))
          abonos600 = abonos600.add(canpolh);
      }
    }
}
try{
  if(allSums){
    createPolien(month,year, xpol, username,sumCta, sumSsscta, sumSssscta, cargos, abonos, cargos600, abonos600, idSector);
  }else{
    createPolien(month,year, xpol, username,sumCta, null, null, cargos, abonos, cargos600, abonos600, idSector);
  }
}catch(Exception e){
  e.printStackTrace();
}
}



/**
 * Sets the cta abonos Y cargos to polien.
 *
 * @param polien the polien
 * @param sumCta the sum cta
 * @param sumSsscta the sum ssscta
 * @param sumSssscta the sum sssscta
 * @param cargos the cargos
 * @param abonos the abonos
 * @param cargos600 the cargos 600
 * @param abonos600 the abonos 600
 */
private void setCtaAbonosYCargosToPolien(Polien polien, BigDecimal sumCta,
                                         BigDecimal sumSsscta, BigDecimal sumSssscta,
                                         BigDecimal cargos, BigDecimal abonos,
                                         BigDecimal cargos600, BigDecimal abonos600){
  polien.setCcuenta(sumCta);
  polien.setCssscta(sumSsscta);
  polien.setCsssscta(sumSssscta);
  if(cargos.equals(abonos))
    polien.setCcontrol(cargos);
  polien.setCdebe(cargos);
  polien.setChaber(abonos);
  polien.setCtcuenta(sumCta);
  polien.setCtssscta(sumSsscta);
  polien.setCtsssscta(sumSssscta);
  polien.setCta600(abonos600);
  polien.setCtc600(cargos600);

}

  /**
   * Creates the base polien.
   *
   * @param month the month
   * @param year the year
   * @param xpol the xpol
   * @param username the username
   * @param idSector the id sector
   * @return the polien
   */
  private Polien createBasePolien(Integer month, Integer year, Integer xpol, String username, Integer idSector){
    Date now = new Date();
    Polien newPolien = new Polien();
    newPolien.setClvpol("D "+StringUtils.leftPad(month+"",2,'0')+ " " +StringUtils.leftPad(xpol+"", 4, '0'));
    newPolien.setFecpol(now);
    newPolien.setFeccap(now);
    newPolien.setStapol("I");
    newPolien.setStaafe("N");
    newPolien.setTippol("D");
    newPolien.setMespol(month);
    newPolien.setConpol(xpol);
    newPolien.setCappol(username);
    newPolien.setAnopol(year);
    newPolien.setIdsector(idSector);
    return newPolien;

  }

  /**
   * Creates the polien.
   *
   * @param month the month
   * @param year the year
   * @param xpol the xpol
   * @param username the username
   * @param idSector the id sector
   */
  private void createPolien(Integer month, Integer year, Integer xpol, String username, Integer idSector){
    Polien newPolien = createBasePolien(month, year, xpol, username, idSector);
    polienRepository.save(newPolien);
  }

  /**
   * Creates the polien.
   *
   * @param month the month
   * @param year the year
   * @param xpol the xpol
   * @param username the username
   * @param sumCta the sum cta
   * @param sumSsscta the sum ssscta
   * @param sumSssscta the sum sssscta
   * @param cargos the cargos
   * @param abonos the abonos
   * @param cargos600 the cargos 600
   * @param abonos600 the abonos 600
   * @param idSector the id sector
   */
  private void createPolien(Integer month, Integer year, Integer xpol, String username,
                            BigDecimal sumCta, BigDecimal sumSsscta, BigDecimal sumSssscta,
                            BigDecimal cargos, BigDecimal abonos, BigDecimal cargos600,
                            BigDecimal abonos600, Integer idSector){
    Polien newPolien =createBasePolien(month, year, xpol, username, idSector);
    setCtaAbonosYCargosToPolien(newPolien,sumCta, sumSsscta, sumSssscta, cargos, abonos, cargos600, abonos600);
    newPolien.setEsppol(""+sumCta);
    polienRepository.save(newPolien);
  }


  /**
   * Creates the polide where cuenta begins with.
   *
   * @param prefix the prefix
   * @param month the month
   * @param year the year
   * @param xpol the xpol
   * @param j the j
   * @param sum the sum
   * @param idSector the id sector
   * @param username the username
   * @return the int
   */
  private int createPolideWhereCuentaBeginsWith(String prefix, Integer month,
                                                 Integer year, Integer xpol,
                                                 int j, BigDecimalWrapper sum, Integer idSector, String username){
    List<Cuenta> cuentas = cuentaRepository.findWhereCtaStartWithAndSssctaNotEmptyAndIdSector(prefix, new Long(idSector.intValue()));

    for (Cuenta cuenta : cuentas){
      Polide polide = createPolide(cuenta, month, year, xpol, j, "D", username, idSector);
      sum.add(polide.getCanpol());
      j++;
    }
    return j;
  }

  /**
   * Creates the polide where cuenta between.
   *
   * @param startAccount the start account
   * @param endAccount the end account
   * @param month the month
   * @param year the year
   * @param xpol the xpol
   * @param j the j
   * @param sum the sum
   * @param caopol the caopol
   * @param idSector the id sector
   * @param username the username
   * @return the int
   */
  private int createPolideWhereCuentaBetween(String startAccount, String endAccount, Integer month,
                                                 Integer year, Integer xpol,
                                                 int j, BigDecimalWrapper sum, String caopol, Integer idSector, String username){
    List<Cuenta> cuentas = cuentaRepository.findWhereCtaBetweenAndSssctaNotEmptyAndIdSector(startAccount, endAccount, new Long(idSector.intValue()));
    for (Cuenta cuenta : cuentas){
      createPolide(cuenta, month, year, xpol, j, caopol, username, idSector);
      j++;
    }
    return j;
  }

  /**
   * Creates the polide where cuenta between.
   *
   * @param startAccount the start account
   * @param endAccount the end account
   * @param month the month
   * @param year the year
   * @param xpol the xpol
   * @param j the j
   * @param sum the sum
   * @param caopol the caopol
   * @param idSector the id sector
   * @param username the username
   * @return the int
   */
  private int createPolideWhereCuentaBetweenWithOverride(String startAccount, String endAccount, Integer month,
                                                 Integer year, Integer xpol,
                                                 int j, BigDecimalWrapper sum, String caopol, Integer idSector, String username){
    List<Cuenta> cuentas = cuentaRepository.findWhereCtaBetweenAndSssctaNotEmptyAndIdSector(startAccount, endAccount, new Long(idSector.intValue()));
    for (Cuenta cuenta : cuentas){
      createPolide(cuenta, month, year, xpol, j, caopol, username, idSector, "H", "H");
      j++;
    }
    return j;
  }

  /**
   * Creates the polide where cuenta is and sssscta.
   *
   * @param account the account
   * @param month the month
   * @param year the year
   * @param xpol the xpol
   * @param j the j
   * @param sum the sum
   * @param caopol the caopol
   * @param secondAccount the second account
   * @param idSector the id sector
   * @param username the username
   * @return the int
   */
  private int createPolideWhereCuentaIsAndSssscta(String account, Integer month,
                                        Integer year, Integer xpol,
                                        int j, BigDecimalWrapper sum, String caopol,
                                        String secondAccount,
                                        Integer idSector, String username){

    List<Cuenta> cuentas = cuentaRepository.findWhereCtaAndSssctaNotEmptyAndSssssctaNotEmptyAndIdSector(account,new Long(idSector.intValue()));
    for(Cuenta cuenta : cuentas){
      Polide polide = createPolide(cuenta, month, year, xpol, j , caopol, username, idSector);
      sum.add(polide.getCanpol());
      j++;
      if(secondAccount != null){
        cuenta.setCuenta(secondAccount);
        polide = createPolide(cuenta, month, year, xpol, j, "D", username, idSector);
        sum.add(polide.getCanpol());
        j++;
      }
    }
    return j;
  }

  /**
   * Creates the polide where cuenta is.
   *
   * @param account the account
   * @param month the month
   * @param year the year
   * @param xpol the xpol
   * @param j the j
   * @param sum the sum
   * @param caopol the caopol
   * @param secondAccount the second account
   * @param idSector the id sector
   * @param username the username
   * @return the int
   */
  private int createPolideWhereCuentaIs(String account, Integer month,
                                        Integer year, Integer xpol,
                                        int j, BigDecimalWrapper sum, String caopol,
                                        String secondAccount,
                                        Integer idSector, String username){

    List<Cuenta> cuentas = secondAccount != null ?
    cuentaRepository.findWhereCtaAndSsssctaNotEmptyAndIdSector(account,new Long(idSector.intValue())) :
    cuentaRepository.findWhereCtaAndSssctaNotEmptyAndIdSector(account,new Long(idSector.intValue()));

    for(Cuenta cuenta : cuentas){                                // H
      Polide polide = createPolide(cuenta, month, year, xpol, j, caopol, username, idSector);
      if(caopol.equals("H"))
        sum.add(polide.getCanpolh());
      else
        sum.add(polide.getCanpol());
      j++;
      if(secondAccount != null){
        cuenta.setCuenta(secondAccount);
        polide = createPolide(cuenta, month, year, xpol, j, "D", username, idSector, "H");
        sum.add(polide.getCanpol());
        j++;
      }
    }
    return j;
  }

  private Polide createPolide(Cuenta cuenta,
                              Integer month, Integer year,
                              Integer xpol, int j, String caopol,
                              String username, Integer idSector){
    return createPolide(cuenta,month, year, xpol, j, caopol,username, idSector, caopol, caopol);
  }

  private Polide createPolide(Cuenta cuenta,
                                Integer month, Integer year,
                                Integer xpol, int j, String caopol,
                                String username, Integer idSector, String override){
      return createPolide(cuenta,month, year, xpol, j, caopol,username, idSector, override, caopol);
    }

  /**
   * Creates the polide.
   *
   * @param cuenta the cuenta
   * @param month the month
   * @param year the year
   * @param xpol the xpol
   * @param j the j
   * @param caopol the caopol
   * @param username the username
   * @param idSector the id sector
   * @return the polide
   */
  private Polide createPolide(Cuenta cuenta,
                              Integer month, Integer year,
                              Integer xpol, int j, String caopol,
                              String username, Integer idSector,
                              String override, String override2){
    List<BigDecimal> abonos = cuenta.getAbonosAsList();
    List<BigDecimal> cargos = cuenta.getCargosAsList();
    BigDecimal total = new BigDecimal(0);


    switch(override){
      case "D":
        total = calculateTotal(month, abonos, cargos);
      break;

      case "H":
        total = calculateTotal(month, cargos, abonos);
      break;
    }


    Polide polide = new Polide();
    polide.setTippol("D");
    polide.setMespol(month);
    polide.setConpol(xpol);
    polide.setRenpol(BigDecimal.valueOf(j));
    polide.setCuenta(cuenta.getCuenta());
    polide.setScta(cuenta.getScuenta());
    polide.setSscta(cuenta.getSscuenta());
    polide.setSsscta(cuenta.getSsscuenta());
    polide.setSssscta(cuenta.getSssscuenta());
    polide.setRefpol("0");
    polide.setConcep("Poliza de cierre");
    polide.setDn("PC");
    polide.setCaopol(caopol);
    polide.setAnopol(year);
    polide.setUserid(username);
    polide.setIdRef(0);
    polide.setIdsector(idSector);

    // polide.setNumdet(); // preguntar por la secuencia de este campo

    switch(override2){
      case "D":
        polide.setCanpol(cuenta.getSalini().add(total));
        polide.setCanpolh(new BigDecimal(0));
        break;

      case "H":
        polide.setCanpolh(cuenta.getSalini().add(total));
        polide.setCanpol(new BigDecimal(0));
        break;
    }
    System.out.println("Creating polide: "+polide);
    polideRepository.save(polide);
    return polide;
  }


  private BigDecimal calculateTotal(Integer max,
                                    List<BigDecimal> min,
                                    List<BigDecimal> sus){
    BigDecimal total = new BigDecimal(0);
    for(int i = 0; i < max; i++){
      if(min.get(i)!=null )
        total =total.add(min.get(i));

      if(sus.get(i)!=null )
        total = total.subtract(sus.get(i));
    }
    return total;
  }

  /**
   *     Utility methods.
   *
   * @param idSector the id sector
   * @return the boolean
   */
  public Boolean isInInvalidState(Integer idSector){
	Integer anopol = conctbRepository.findByIdsector(idSector).getAnoemp();
    Polide polide = polideRepository.findFirstByIdsectorAndConcepAndAnopolOrderByIdAsc(idSector, "Poliza de cierre", anopol);
    return polide != null;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.polizas.PolizaCierreService#validateClave(java.lang.String)
   */
  public Boolean validateClave(String clave){
    return "ypolcie".equals(clave);
  }

  /**
   * Basic validations valid.
   *
   * @param clave the clave
   * @param idSector the id sector
   * @param consumer the consumer
   * @return the boolean
   */
  private Boolean basicValidationsValid(String clave, Integer idSector,
                                       BiConsumer<String, String> consumer){

    if(!validateClave(clave)){
      consumer.accept("w", "Clave incorrecta");
      return false;
    }

    if(isInInvalidState(idSector)){
      consumer.accept("w", "Ya existen las polizas de cierre");
      
      PrimeFaces.current().executeScript("PF(dialogWgt).hide()");
      return false;
    }

    return true;
  }



}
