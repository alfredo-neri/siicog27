package com.gem.sistema.business.service.reportador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.domain.GenericCatalog;
import com.gem.sistema.business.domain.Cuenta;

import java.math.BigDecimal;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneraPartidasPresupuestalesServiceImpl.
 */
@Service("generaPartidasPresupuestalesService")
public class GeneraPartidasPresupuestalesServiceImpl implements GeneraPartidasPresupuestalesService{

  /** The accounts 5100. */
  private static String[] ACCOUNTS_5100 = {"5100", "8211", "8271", "8251", "8241", "8221"};
  
  /** The accounts 5200. */
  private static String[] ACCOUNTS_5200 = {"5200", "8212","8272", "8252", "8242", "8222"};
  
  /** The accounts 5300. */
  private static String[] ACCOUNTS_5300 = {"5300", "8213", "8273", "8253","8243", "8223"};
  
  /** The accounts 5400. */
  private static String[] ACCOUNTS_5400 = {"5400", "8214", "8274", "8254", "8244", "8224"};
  
  /** The accounts 5600. */
  private static String[] ACCOUNTS_5600 = {"5600", "8215", "8275", "8255", "8245", "8225"};
  
  /** The accounts 5700. */
  private static String[] ACCOUNTS_5700 = {"5700", "8216", "8276", "8256", "8246", "8226"};


  /** The catdep repository. */
  @Autowired
  private CatdepRepository catdepRepository;

  /** The xcatpro repository. */
  @Autowired
  private XcatproRepository xcatproRepository;

  /** The natgas repository. */
  @Autowired
  private NatgasRepository natgasRepository;

  /** The muninep repository. */
  @Autowired
  private MuniNepRepository muninepRepository;

  /** The conctb repository. */
  @Autowired
  private ConctbRepository conctbRepository;

  /** The account service. */
  @Autowired
  private AccountService accountService;

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesService#getDependencies(java.lang.String, java.lang.Integer)
   */
  public List<GenericCatalog<String>> getDependencies(String query, Integer idSector){
    List<GenericCatalog<String>> deps =
    catdepRepository.findAllDependencies(idSector, query.toUpperCase()).stream().map(
    (catdep) ->{
      return new GenericCatalog<String>(catdep.getClvdep(), catdep.getNomdep(), catdep);
    }).collect(Collectors.toList());
    return deps;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesService#getPrograms(java.lang.Integer)
   */
  public List<GenericCatalog<String>> getPrograms(Integer idSector){
    List<GenericCatalog<String>> progs =
    xcatproRepository.findBySectorOrderByClvdepClvfunClvfin(idSector).stream().map(
    (xcatpro) -> {
      return new GenericCatalog<String>(xcatpro.getClvdep(), xcatpro.getNompro(), xcatpro);
    }).collect(Collectors.toList());
    return progs;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesService#getParts(java.lang.Integer)
   */
  public List<GenericCatalog<String>> getParts(Integer idSector){
    List<GenericCatalog<String>> parts =
    natgasRepository.findAllWhichNotContainsCeros(idSector).stream().map(
    (natgas) -> {
      return new GenericCatalog<String>(natgas.getClvgas(), natgas.getNomgas(), natgas);
    }).collect(Collectors.toList());
    return parts;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesService#findDependency(java.lang.Integer, java.lang.String)
   */
  public Catdep findDependency(Integer idSector, String clvdep){
    return catdepRepository.findFirstByIdsectorAndClvdepOrderByIdAsc(idSector, clvdep);
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesService#findFirstXcatpro(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
   */
  public Xcatpro findFirstXcatpro(Integer idSector, String clvdep, String clvfun, String clvfin){
    return xcatproRepository.findFirstBySectoridAndClvdepAndClvfunAndClvfinOrderByIdDesc(idSector, clvdep, clvfun, clvfin);
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesService#findFirstNatgas(java.lang.String, java.lang.Integer)
   */
  public Natgas findFirstNatgas(String clvgas, Integer idSector){
    return natgasRepository.findFirstByClvgasAndIdsector(clvgas, idSector);
  }

  /**
   * Gets the dependency.
   *
   * @param dep the dep
   * @param idSector the id sector
   * @return the dependency
   */
  public String getDependency(String dep, Integer idSector){
    Catdep catdep = findDependency(idSector, dep);
    if(catdep == null){
      return null;
    }else{
      return catdep.getNomdep() == null ? "" : catdep.getNomdep();
    }
  }

  /**
   * Gets the program.
   *
   * @param idSector the id sector
   * @param dep the dep
   * @param prog the prog
   * @return the program
   */
  public String getProgram(Integer idSector, String dep, String prog){
    String clvfin = StringUtils.right(prog, 3);
    String clvfun = StringUtils.removeEnd(prog, clvfin);
    Xcatpro xcatpro = findFirstXcatpro(idSector, dep, clvfun, clvfin);
    if(xcatpro == null){
      return null;
    }else{
      return xcatpro.getNompro() == null ? "": xcatpro.getNompro();
    }
  }

  /**
   * Gets the clvfun from prog.
   *
   * @param prog the prog
   * @return the clvfun from prog
   */
  public String getClvfunFromProg(String prog){
    String clvfin = StringUtils.right(prog, 3);
    return StringUtils.removeEnd(prog, clvfin);
  }

  /**
   * Gets the part.
   *
   * @param idSector the id sector
   * @param part the part
   * @return the part
   */
  public String getPart(Integer idSector, String part){
    Natgas natgas = findFirstNatgas(part, idSector);
    if(natgas == null){
      return null;
    }else{
      return natgas.getNomgas() == null ? "" : natgas.getNomgas();
    }
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.reportador.GeneraPartidasPresupuestalesService#process(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.function.Consumer)
   */
  public void process(Long idSector, String dep, String prog, String part, String use, Consumer<String> consumer){
    String depNom, nomPro, nomPart, depC7;
    Conctb conctb =conctbRepository.findByClvempAndIdsector("1", idSector.intValue());

    if((depNom = getDependency(dep, idSector.intValue())) == null){
      consumer.accept("No existe la dependencia");
      return;
    }

    if((nomPro = getProgram(idSector.intValue(), dep, prog)) == null){
      consumer.accept("No existe programa");
      return;
    }

    if((nomPart = getPart(idSector.intValue(), part)) == null){
      consumer.accept("No existe esta partida");
      return;
    }

    Muninep muninep = muninepRepository.findFirstByCampo7AndIdsector(getClvfunFromProg(prog), idSector.intValue());
    if(muninep == null){
      consumer.accept("No existe esta estruc. programatica");
      return;
    }else{
      depC7 = depNom + " " + muninep.getCampo6();
    }

    String depClv =StringUtils.rightPad(dep, conctb.getNivel2() ,"0");
    StringUtils.rightPad(prog, conctb.getNivel3(), "0");
    filterPart(part, (partLabel, accounts) -> {
       if(!createMissingAccounts(accounts, dep, depNom, prog, nomPro, part, nomPart, idSector, use,(msg)->{
         consumer.accept(msg);
       })){
         consumer.accept("No existe cuenta e mayor "+partLabel);
       }
    });
  }

  /**
   * Filter part.
   *
   * @param part the part
   * @param consumer the consumer
   */
  public void filterPart(String part, BiConsumer<String, String[]> consumer){
    if(part.startsWith("1") || part.startsWith("2") || part.startsWith("3")){
      consumer.accept("5100", ACCOUNTS_5100);
    }else if(part.startsWith("4")){
      consumer.accept("5200",ACCOUNTS_5200);
    }else if(part.startsWith("8")){
      consumer.accept("5300",ACCOUNTS_5300);
    }else if(part.startsWith("9")){
      consumer.accept("5400", ACCOUNTS_5400);
    }else if(part.startsWith("5")){
      consumer.accept("5600", ACCOUNTS_5600);
    }else if(part.startsWith("6")){
      consumer.accept("5700", ACCOUNTS_5700);
    }
  }

  /**
   * Creates the missing accounts.
   *
   * @param accounts the accounts
   * @param dep the dep
   * @param depNom the dep nom
   * @param programa the programa
   * @param nomPro the nom pro
   * @param part the part
   * @param nomPart the nom part
   * @param idSector the id sector
   * @param user the user
   * @param consumer the consumer
   * @return the boolean
   */
  public Boolean createMissingAccounts(String[] accounts, String dep,String depNom,
                                    String programa, String nomPro, String part,
                                    String nomPart, Long idSector, String user, Consumer<String> consumer){

    for(String account : accounts){
      if(accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(account,"","","","", idSector)==null){
        return false;
      }else{
        if(accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(account,dep,"","","", idSector)==null){
            accountService.saveAccount(new Cuenta(account, dep, "", "", "", depNom,"N", 2,  user, 0, "D", BigDecimal.ZERO, idSector));
        }
        if(accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(account,dep,programa,"","", idSector)==null){
            accountService.saveAccount(new Cuenta(account, dep, programa, "", "", nomPro,"N", 3,  user, 0, "D",BigDecimal.ZERO, idSector));
        }
        if(accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(account,dep,programa,part,"", idSector)==null){ // para esta hacer validacion para mostrar que ya existe
          accountService.saveAccount(new Cuenta(account, dep, programa, part, "", nomPart,"S", 4 , user, 0, "D", BigDecimal.ZERO, idSector));
        }else{
          consumer.accept("La Cuenta: "+account+", "+dep+", "+programa +" ya existe.");
        }
      }
    }
    return true;
  }

}
