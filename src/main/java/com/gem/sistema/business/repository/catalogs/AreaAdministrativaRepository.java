package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.AreaAdministrativa;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "areaAdministrativaRepository")
public interface AreaAdministrativaRepository
        extends PagingAndSortingRepository<AreaAdministrativa, Long>, QueryDslPredicateExecutor<AreaAdministrativa> {

    List<AreaAdministrativa> findAll();
}
