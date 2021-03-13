package de.leuphana.connector;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.leuphana.component.behaviour.ArticleRepository;
import de.leuphana.component.structure.Customer;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerJPAConnectorTest {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ArticleRepository customerRepository;
	private Logger logger;

	@BeforeEach
	void setUp() throws Exception {
		logger = LogManager.getLogger(this.getClass());
	}

	@AfterEach
	void tearDown() throws Exception {
		entityManager = null;
		customerRepository = null;
		logger = null;

	}

	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(entityManager);
		Assertions.assertNotNull(customerRepository);
	}

	@Test
	void canCustomerBePersisted() {
		Customer customer = new Customer("Harald Krull", "St. Pauli Reeperbahn", null);

		// Persist
		customerRepository.save(customer);

		// Check persistence
		Assertions.assertNotNull(customerRepository.findByName("Harald Krull"));

		 // additional logs TODO remove or change to DEBUG instead of INFO
		logger.info(customerRepository.findByName("Harald Krull").get(0).getName());
		logger.info(customerRepository.findByName("Harald Krull").get(0).getAddress());
		logger.info(customerRepository.findByName("Harald Krull").get(0).getCart());
	}

}
