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
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ImportAutoConfiguration({
		FeignAutoConfiguration.class,
		HttpMessageConvertersAutoConfiguration.class })
/**
 * @author max 
 * Before you start the test you have to start the Eureka-client and
 * then the article microservice.
 */
class ArticleRestConnectorRequesterTest {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private OrderRestConnectorProvider orderRestConnectorProvider;
	private static int orderId;
	private static Logger logger;

	@BeforeEach
	void setUp() throws Exception {

		logger = LogManager.getLogger(this.getClass());
		orderId = orderRestConnectorProvider
				.createOrder(1);
	}

	@AfterEach
	void tearDown() throws Exception {
		orderRestConnectorProvider = null;
		entityManager = null;
		logger = null;
	}

	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(entityManager);
		Assertions.assertNotNull(
				orderRestConnectorProvider);
		Assertions.assertNotNull(logger);
	}

	@Test
	void canArticleBeAddedToOrder() {
		// You have make sure that an article with id 1 already exist in the database
		// before you start the test
		Assertions.assertEquals(
				"Article with id 1 added to order.<br>",
				orderRestConnectorProvider
				.addArticleToOrder(1, 5, orderId));
	}

}
