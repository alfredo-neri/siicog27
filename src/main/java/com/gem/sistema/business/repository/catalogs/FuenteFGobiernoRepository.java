package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.gem.sistema.business.domain.FuentefGobierno;

@Repository(value = "fuenteFGobiernoRepository")
public interface FuenteFGobiernoRepository extends PagingAndSortingRepository<FuentefGobierno, Long>, CrudRepository<FuentefGobierno, Long> {
	List<FuentefGobierno> findAll();

	@Query("Select f from FuentefGobierno f where f.ssscta<>''")
	List<FuentefGobierno> findAllFourLevel();

	@Query("Select f from FuentefGobierno f where f.sscta<>'' and f.ssscta=''")
	List<FuentefGobierno> findAllThirLevel();

	@Query("Select f from FuentefGobierno f where f.scta<>'' and f.sscta=''")
	List<FuentefGobierno> findAllSecondLevel();

	@Query("Select f from FuentefGobierno f where f.cuenta<>'' and f.scta=''")
	List<FuentefGobierno> findAllFirstLevel();
    
	@Query("Select f from FuentefGobierno f where f.cuenta||f.scta||f.sscta||f.ssscta=?1")
	FuentefGobierno findByFuente(String fuente);
	
}
