package com.gem.sistema.business.repository.catalogos;

import org.springframework.beans.factory.annotation.Autowired;

import com.gem.sistema.business.repository.base.AbstractIntegrationTest;
import com.gem.sistema.business.repository.catalogs.CatfluRepository;



/**
 * Integration tests for {@link CatfluRepository}.
 */
public class CatfluRepositoryIntegrationTest  extends AbstractIntegrationTest{
	@Autowired 
	CatfluRepository repository;
	
	@Autowired
	org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean factory;
	
	/*@Test
	public void findsCatfluByNomflu() {
		List<Catflu> cuentas = repository.findByNomflu("Desde Eclipse 1");
		assertThat(cuentas, hasSize(3));
	}

	@Test
	public void findsCatfluByGruflu() {
		List<Catflu> cuentas = repository.findByGruflu("2");
		assertThat(cuentas, hasSize(1));
	}
	
	@Test
	public void findsCatfluByGrufluAndNomflu() {
		List<Catflu> cuentas = repository.findByNomfluAndGruflu("Desde Eclipse 2","2");
		assertThat(cuentas, hasSize(1));
	}*/
	
}
