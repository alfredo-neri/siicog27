package com.gem.sistema.business.service.acumulacion;

import com.gem.sistema.business.domain.Paso;
import com.gem.sistema.business.repository.load.PasoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

// TODO: Auto-generated Javadoc
/**
 * The Class AcumulacionSaldosServiceImpl.
 */
@Service("acumulacionSaldosService")
public class AcumulacionSaldosServiceImpl implements AcumulacionSaldosService{

  /** The paso repository. */
  @Autowired
  PasoRepository pasoRepository;

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.acumulacion.AcumulacionSaldosService#updatePaso()
   */
  public void updatePaso(){
    Date start = new Date();
    System.out.println("executing query ---");
    AtomicInteger i = new AtomicInteger();
    List<Object[]> res = pasoRepository.findWithoutCeros();
    res.forEach((x) -> {
      // System.out.println(x);
      i.getAndIncrement();
    });

    System.out.println("("+i.get()+") lasted: "+(new Date().getTime() - start.getTime())/1000.0);
  }

}
