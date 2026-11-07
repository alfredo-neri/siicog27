package com.gem.sistema.business.repository.catalogs;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TbProceso;

@Repository(value = "tbProcesoRepository")
public interface TbProcesoRepository
		extends PagingAndSortingRepository<TbProceso, Long>, CrudRepository<TbProceso, Long>, QuerydslPredicateExecutor<TbProceso> {

	@Query(value = "select p from TbProceso p where p.nombreProceso=:nombre and "
			+ "MONTH(p.fechaInicio)=MONTH(:fecha)  and DAY(p.fechaInicio)=DAY(:fecha) and YEAR(p.fechaInicio)=YEAR(:fecha) "
			+ "order by p.fechaInicio desc")
	List<TbProceso> findByFechaInicioAndNombreProceso(@Param("nombre") String nombre, @Param("fecha") Date fecha);

	@Query(value = "select p from TbProceso p where " + "MONTH(p.fechaInicio)=:mes  and DAY(p.fechaInicio)=:dia "
			+ "order by p.fechaInicio desc")
	List<TbProceso> findByFechaInicio(@Param("dia") Integer dia, @Param("mes") Integer mes);

	@Query(value = "select DISTINCT p.nombreProceso from TbProceso p where p.nombreProceso is not null")
	List<String> getNombresReportes();
}
