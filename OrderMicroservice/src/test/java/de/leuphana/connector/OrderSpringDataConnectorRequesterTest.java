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

import de.leuphana.component.behaviour.OrderRepository;
import de.leuphana.component.behaviour.exception.OrderNotFoundException;
import de.leuphana.component.structure.Order;
import de.leuphana.component.structure.OrderPosition;

@ExtendWith(SpringExtension.class)
@DataJpaTest // Ist ein spezieller Test, mit dem Autowired nutzbar ist
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Damit keine h2 Datenbank benutzt wird
@ImportAutoConfiguration(FeignAutoConfiguration.class)
class OrderSpringDataConnectorRequesterTest {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private OrderRepository orderRepository;

	private static Logger logger;
	private static Order order;
	private static OrderPosition orderPosition;

	@BeforeEach
	void setUp() throws Exception {
		order = new Order();
		orderPosition = new OrderPosition();
		logger = LogManager.getLogger(this.getClass());

		orderPosition.setArticleId(3);
		order.addOrderPosition(orderPosition);
		// Persist
		orderRepository.save(order);
	}

	@AfterEach
	void tearDown() throws Exception {
		//Delete order isn´t necessary because Spring does a rollback after the test
		order = null;
		orderPosition = null;
		logger = null;
	}

	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(entityManager);
		Assertions.assertNotNull(orderRepository);
	}
	
	@Test
	void canOrderBePersisted() {
		Order savedOrder = orderRepository.save(order);
		Assertions.assertNotNull(savedOrder);
	}

	@Test
	void hasTheOrderBenSaved() {
		// Check persistence of order
		Assertions.assertNotNull(orderRepository
				.findById(order.getOrderId()).orElseThrow(
						() -> new OrderNotFoundException(
								order.getOrderId())));
	}

	@Test
	void doesOrderPositionInOrderBePersisted() {
		// Check persistence of order position in order
		Assertions.assertNotNull(orderRepository
				.findById(order.getOrderId())
				.orElseThrow(
						() -> new OrderNotFoundException(
								order.getOrderId()))
				.getOrderPositions());
	}
}
