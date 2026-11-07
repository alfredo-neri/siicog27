package com.gem.sistema.business.repository.catalogs;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Balancepre;

@Repository
public interface BalancepreRepository
		extends PagingAndSortingRepository<Balancepre, Long>, QueryDslPredicateExecutor<Balancepre> {

	List<Balancepre> findAllByIdSectorAndIdAnioAndTrimestreOrderByConsecutivoAsc(Integer idSector, Long idAnio,
			Integer trimestre);

	@Query("select count(1) from Balancepre where idSector =:idSector and trimestre =:trimestre and consecutivo =:consecutivo ")
	Integer countByTrimestre(@Param("idSector") Integer idSector, @Param("trimestre") Integer trimestre,
			@Param("consecutivo") Long consecutivo);

	@SuppressWarnings("el-syntax")
	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} u set u.consecutivo = :consecutivo, u.trimestre = :trimestre, u.concepto = :concepto, u.ea = :ea, u.devengado = :devengado, u.rp = :rp, u.capturo = :capturo, u.feccap = :feccap  where u.id = :id")
	int update(@Param("consecutivo") Long consecutivo, @Param("trimestre") Integer trimestre,
			@Param("concepto") String concepto, @Param("ea") BigDecimal ea, @Param("devengado") BigDecimal devengado,
			@Param("rp") BigDecimal rp, @Param("capturo") String capturo, @Param("feccap") Date feccap,
			@Param("id") Long id);

}
