package com.gem.sistema.business.service.reportador;

import java.util.List;
import java.util.function.Consumer;
import com.gem.sistema.business.domain.TcMes;

// TODO: Auto-generated Javadoc
/**
 * The Interface ArchivoConciliarService.
 */
public interface ArchivoConciliarService{

  /**
   * Gets the months.
   *
   * @return the months
   */
  public List<TcMes> getMonths();

  /**
   * Find records for file.
   *
   * @param month the month
   * @param cta the cta
   * @param sscta the sscta
   * @param ssscta the ssscta
   * @param idsector the idsector
   * @param consumer the consumer
   */
  public void findRecordsForFile(Integer month, String cta,
                                 String sscta, String ssscta,
                                 Long idsector,
                                 Consumer <List<String>> consumer);
}
