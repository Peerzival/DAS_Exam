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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.leuphana.component.behaviour.OrderService;

@ExtendWith(SpringExtension.class)
@DataJpaTest // Ist ein spezieller Test, mit dem Autowired nutzbar ist
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Damit keine h2 Datenbank benutzt wird
@ImportAutoConfiguration(FeignAutoConfiguration.class)
class OrderRestConnectorProviderTest {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private OrderRestConnectorProvider orderRestConnectorProvider;
	private static Logger logger;
	private static int orderId;
	private static int orderIdForDeleteMethod;

	@BeforeEach
	void setUp() throws Exception {
		orderId = orderRestConnectorProvider
				.createOrder(1);

		orderIdForDeleteMethod = orderRestConnectorProvider
				.createOrder(2);

		logger = LogManager.getLogger(this.getClass());

	}

	@AfterEach
	void tearDown() throws Exception {
		// Delete isn´t necessary because Spring does a rollback after the test
		logger = null;
		orderRestConnectorProvider = null;
		entityManager = null;
	}

	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(entityManager);
		Assertions.assertNotNull(
				orderRestConnectorProvider);
		Assertions.assertNotNull(logger);
	}

	@Test
	void canOrderBeCreated() {
		// Check persistence of order with rest controller
		Assertions.assertNotNull(
				orderRestConnectorProvider
						.createOrder(1));

	}

	@Test
	void canOrderBeFetched() {
		// Check fetching of order with rest controller
		Assertions.assertNotNull(
				orderRestConnectorProvider
						.getOrder(orderId));
	}

	@Test
	void canAllOrdersBeFetched() {
		// Check fetching of all orders with rest controller
		Assertions.assertNotNull(
				orderRestConnectorProvider
						.getAllOrders());
	}

	@Test
	void canOrderBeFetchedAsString() {
		Assertions.assertNotNull(
				orderRestConnectorProvider
						.getOrderString(orderId));

		logger.info(orderRestConnectorProvider
				.getOrderString(orderId));
	}

	@Test
	void canAllOrdersBeFetchedAsString() {
		Assertions.assertNotNull(
				orderRestConnectorProvider
						.getAllOrdersAsString());

		logger.info(orderRestConnectorProvider
				.getAllOrdersAsString());
	}

	@Test
	void canOrderBeDeleted() {
		Assertions.assertNotNull(
				orderRestConnectorProvider.deleteOrder(
						orderIdForDeleteMethod));
	}

}
