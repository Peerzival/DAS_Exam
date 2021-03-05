package de.leuphana.connector;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.leuphana.component.behaviour.OrderRepository;
import de.leuphana.component.structure.Order;
import de.leuphana.component.structure.OrderPosition;

@ExtendWith(SpringExtension.class)
@DataJpaTest //Ist ein spezieller Test, mit dem Autowired nutzbar ist
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Damit keine h2 Datenbank benutzt wird
class OrderJPATest {

	@Autowired private EntityManager entityManager;
	@Autowired private OrderRepository orderRepository;
	
	private Logger logger;
	
	@BeforeEach
	void setUp() throws Exception {			
		logger = LogManager.getLogger(this.getClass());
	}

	@AfterEach
	void tearDown() throws Exception {
//		Assertions.assertTrue(shopJPAConnector.deleteArticle(createdArticleId));
	}

	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(entityManager);
		Assertions.assertNotNull(orderRepository);
	}
	
	@Test
	void canOrderBePersisted() {
		Order order = new Order();
		OrderPosition orderPosition = new OrderPosition();
		orderPosition.setArticleId(3);;
		order.getOrderPositions().add(orderPosition);
		// Persist
		orderRepository.save(order);
		
		// Check persistence
		Assertions.assertNotNull(orderRepository.findById(order.getOrderId()));
//		logger.info(orderRepository.findById(order.getOrderId()).getOrderPositions().get(0).getArticle().getName());
//		orderRepository.deleteAll();
//		logger.info(orderRepository.findByName("Weihnachtsmann").get(0).getName());
//		logger.info(orderRepository.findByName("Weihnachtsmann").get(0).getManufactor());
//		logger.info(orderRepository.findByName("Weihnachtsmann").get(0).getPrice());
	}
}
