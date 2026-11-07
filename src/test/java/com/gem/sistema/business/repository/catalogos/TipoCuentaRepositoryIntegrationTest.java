package com.gem.sistema.business.repository.catalogos;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gem.sistema.business.domain.TipoCuenta;
import com.gem.sistema.business.repository.base.AbstractIntegrationTest;
import com.gem.sistema.business.repository.catalogs.TipoCuentaRepository;



/**
 * Integration tests for {@link TipoCuentaRepository}.
 */
public class TipoCuentaRepositoryIntegrationTest extends AbstractIntegrationTest{
	@Autowired TipoCuentaRepository repository;

	@Test
	public void findsAllTipoCuenta() {
		List<TipoCuenta> cuentas = repository.findAll();
		assertThat(cuentas, hasSize(2));
	}
	
	@Test
	public void findsTipoCuentaByIdTipoCuenta() {
		List<TipoCuenta> cuentas = repository.findByIdTipoCuenta(new Integer(1));
		assertThat(cuentas, hasSize(3));
	}

	@Test
	public void findsTipoCuentaByIdTipoCuentaAndNombre() {
		List<TipoCuenta> cuentas = repository.findByIdTipoCuentaAndNombre(new Integer(1),"nombre1");
		assertThat(cuentas, hasSize(1));
	}

        

	
}
