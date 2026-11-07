package com.gem.sistema.business.repository.base;


import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract test de integracion para llenar la base de datos dummy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(false)
@SpringApplicationConfiguration( locations = {"file:src/test/resources/applicationContext-jpa.xml"})
public abstract class AbstractIntegrationTest {

}
