package com.gem.sistema.business.service.utilerias;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Ingreso;
import com.gem.sistema.business.domain.Paso;
import com.gem.sistema.business.domain.MonthlyAbstractEntity;

import com.gem.sistema.business.repository.load.PasoRepository;
import com.gem.sistema.business.repository.load.IngresoRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;

import java.util.List;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.function.Consumer;


// TODO: Auto-generated Javadoc
/**
 * The Class SaldosInicialesServiceImpl.
 */
@Service("saldosInicialesService")
public class SaldosInicialesServiceImpl implements SaldosInicialesService{

  /** The cuenta repository. */
  @Autowired
  private CuentaRepository cuentaRepository;

  /** The ingreso repository. */
  @Autowired
  private IngresoRepository ingresoRepository;

  /** The paso repository. */
  @Autowired
  private PasoRepository pasoRepository;

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.utilerias.SaldosInicialesService#findFirstCuenta(java.lang.String, java.lang.Long)
   */
  public Cuenta findFirstCuenta(String cuenta, Long idSector){
    return cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(cuenta, "", "", "", "", idSector);
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.utilerias.SaldosInicialesService#findCuentasFor(java.lang.String, java.lang.String, java.lang.Long)
   */
  public List<Cuenta> findCuentasFor(String cuenta, String scta, Long idSector){
    return cuentaRepository.findAllWhereCuentaBeginsAndSctaBeginsAndIdSector(cuenta, scta, idSector);
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.utilerias.SaldosInicialesService#findFirstByCuentaAndIdsector(java.lang.String, java.lang.Long)
   */
  public Cuenta findFirstByCuentaAndIdsector(String cuenta, Long idSector){
    return cuentaRepository.findFirstByCuentaAndIdsectorOrderByIdAsc(cuenta, idSector);
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.utilerias.SaldosInicialesService#findPasoForClaveProgramaAndPartida(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.util.function.Consumer)
   */
  public Paso findPasoForClaveProgramaAndPartida(String clave, String programa, String partida, Long idSector, Consumer<Boolean> consumer){
    Paso paso = pasoRepository.findFirstByClaveAndProgramaAndPartidaAndCatsectorOrderByIdAsc(clave, programa,

                                                                                       partida, idSector.intValue());
    if(paso == null){
      consumer.accept(false);
      paso = new Paso();
      paso.setClave(clave);
      paso.setPrograma(programa);
      paso.setPartida(partida);
      // paso.setIdsector(idSector.intValue());
      paso.setAuto11(new BigDecimal(0));
      paso.setAuto12(new BigDecimal(0));
      paso.setAuto13(new BigDecimal(0));
      paso.setAuto14(new BigDecimal(0));
      paso.setAuto15(new BigDecimal(0));
      paso.setAuto16(new BigDecimal(0));
      paso.setAuto17(new BigDecimal(0));
      paso.setAuto18(new BigDecimal(0));
      paso.setAuto19(new BigDecimal(0));
      paso.setAuto110(new BigDecimal(0));
      paso.setAuto111(new BigDecimal(0));
      paso.setAuto112(new BigDecimal(0));
    }

    return paso;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.utilerias.SaldosInicialesService#getPreviousAccountName(com.gem.sistema.business.domain.Cuenta, java.lang.Long)
   */
  public String getPreviousAccountName(Cuenta account, Long idSector){

    Cuenta cuenta = null;
    switch (account.getXnicta()){
      case 5:
      cuenta =
       cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(
       account.getCuenta(),
       account.getScuenta(),
       account.getSscuenta(),
       account.getSsscuenta(),
       "",
       idSector);
      break;
      case 4:
      cuenta =
       cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(
       account.getCuenta(),
       account.getScuenta(),
       account.getSscuenta(),
       "",
       "",
       idSector);
      break;
      case 3:
      cuenta =
       cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(
       account.getCuenta(),
       account.getScuenta(),
       "",
       "",
       "",
       idSector);
      break;
      case 2:
      cuenta =
       cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(
       account.getCuenta(),
       "",
       "",
       "",
       "",
       idSector);
      break;
      default:
      break;
    }
    if(cuenta == null) return "";
    return cuenta.getNomcta();
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.utilerias.SaldosInicialesService#getIngreso(com.gem.sistema.business.domain.Cuenta, java.lang.String, java.lang.Long)
   */
  // la cuenta viene de la pantalla
  public Ingreso getIngreso(Cuenta account, String cuenta, Long idSector) {
// "8110"
    Ingreso ingreso = ingresoRepository.findFirstByCuentaAndSctaAndSsctaAndSssctaAndSsssctaAndIdsector(cuenta,
                       account.getScuenta(), account.getSscuenta(), account.getSsscuenta(), account.getSssscuenta(), idSector);

    if(ingreso == null){
      ingreso = new Ingreso();
      ingreso.setCuenta(cuenta); //"8110"
      ingreso.setScta(account.getScuenta());
      ingreso.setSscta(account.getSscuenta());
      ingreso.setSsscta(account.getSsscuenta());
      ingreso.setSssscta(account.getSssscuenta());
      ingreso.setAutoi1(new BigDecimal(0));
      ingreso.setAutoi2(new BigDecimal(0));
      ingreso.setAutoi3(new BigDecimal(0));
      ingreso.setAutoi4(new BigDecimal(0));
      ingreso.setAutoi5(new BigDecimal(0));
      ingreso.setAutoi6(new BigDecimal(0));
      ingreso.setAutoi7(new BigDecimal(0));
      ingreso.setAutoi8(new BigDecimal(0));
      ingreso.setAutoi9(new BigDecimal(0));
      ingreso.setAutoi10(new BigDecimal(0));
      ingreso.setAutoi11(new BigDecimal(0));
      ingreso.setAutoi12(new BigDecimal(0));
    }
    ingreso.sumAutoiTo13();
    return ingreso;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.utilerias.SaldosInicialesService#saveMuni(com.gem.sistema.business.domain.Cuenta, java.math.BigDecimal, com.gem.sistema.business.domain.MonthlyAbstractEntity, java.lang.Long, java.util.function.Consumer)
   */
  public boolean saveMuni(Cuenta selectedAccount, BigDecimal salIni,
                          MonthlyAbstractEntity mentity, Long idSector,
                          Consumer<String> errorMessage){
    if(selectedAccount == null){
      errorMessage.accept("cuenta no existe");
      return false;
    }

    selectedAccount.setSalini(salIni);
    cuentaRepository.save(selectedAccount);

    if(("8110").equals(selectedAccount.getCuenta())){
      if(selectedAccount.getSalini().equals(mentity.getSuma())){
        Cuenta cuenta = cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector("8120",
         selectedAccount.getScuenta(), selectedAccount.getSscuenta(), selectedAccount.getSsscuenta(),
         selectedAccount.getSssscuenta(),idSector);
         if(cuenta != null){
           cuenta.setSalini(salIni);
           cuentaRepository.save(cuenta);
         }
         Ingreso ingreso = (Ingreso)mentity;
         System.out.println("SAVING INGRESO: "+ingreso);
         ingreso.setIdsector(idSector);
         ingresoRepository.save(ingreso);
      }else{
        errorMessage.accept("Valores diferentes");
        return false;
      }
    }else if("6004".equals(selectedAccount.getCuenta())){
      if(selectedAccount.getSalini().equals(mentity.getSuma())){
        Cuenta cuenta = cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector("6007",
         selectedAccount.getScuenta(), selectedAccount.getSscuenta(), selectedAccount.getSsscuenta(),
         selectedAccount.getSssscuenta(),idSector);
         if(cuenta != null){
           cuenta.setSalini(salIni);
           cuentaRepository.save(cuenta);
         }
         pasoRepository.save((Paso)mentity);
      }else{
        errorMessage.accept("Valores diferentes");
        return false;
      }
    }
    return true;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.utilerias.SaldosInicialesService#saveCentral(com.gem.sistema.business.domain.Cuenta, java.math.BigDecimal, com.gem.sistema.business.domain.MonthlyAbstractEntity, java.lang.Long, java.util.function.Consumer)
   */
  public boolean saveCentral(Cuenta selectedAccount, BigDecimal salIni,
                          MonthlyAbstractEntity mentity, Long idSector,
                          Consumer<String> errorMessage){

    if(selectedAccount == null){
      errorMessage.accept("cuenta no existe");
      return false;
    }
    selectedAccount.setSalini(salIni);
    cuentaRepository.save(selectedAccount);

    if("6001".equals(selectedAccount.getCuenta()) || "6004".equals(selectedAccount.getCuenta())){
      if(!selectedAccount.getSalini().equals(mentity.getSuma())){
        errorMessage.accept("Valores diferentes");
        return false;
      }
    }

    if("6001".equals(selectedAccount.getCuenta())){
      Cuenta cuenta = cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector("6003",
       selectedAccount.getScuenta(), selectedAccount.getSscuenta(), selectedAccount.getSsscuenta(),
       selectedAccount.getSssscuenta(),idSector);

       if(cuenta != null){
         cuenta.setSalini(salIni);
         cuentaRepository.save(cuenta);
       }
       Ingreso ingreso = (Ingreso)mentity;
       ingreso.setIdsector(idSector);
       ingresoRepository.save(ingreso);

    }else if("6004".equals(selectedAccount.getCuenta())){
      Cuenta cuenta = cuentaRepository.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector("6007",
       selectedAccount.getScuenta(), selectedAccount.getSscuenta(), selectedAccount.getSsscuenta(),
       selectedAccount.getSssscuenta(),idSector);

       if(cuenta != null){
         cuenta.setSalini(salIni);
         cuentaRepository.save(cuenta);
       }
       pasoRepository.save((Paso)mentity);
    }
    return true;
  }
}
