package de.leuphana.connector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerJPAConnectorTest {
//
//	private CustomerJPAConnector customerJPAConnector;
//	private Integer createdCustomerId;
	
	@BeforeEach
	void setUp() throws Exception {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
//				"applicationContext-JPA-Connector-Customer.xml");
//		
//		customerJPAConnector = (CustomerJPAConnector)applicationContext.getBean("shopJPAConnector");
//		
//		
//		Customer customer = new Customer();
//		createdCustomerId = customerJPAConnector.createCustomer(customer);
//		
//		// same time later
//		
//		Customer foundCustomer = customerJPAConnector.getCustomer(createdCustomerId);
//		
	}

	@AfterEach
	void tearDown() throws Exception {
//		Assertions.assertTrue(shopJPAConnector.deleteOrder(createdOrderId));
//		Assertions.assertTrue(shopJPAConnector.deleteArticle(createdArticleId));
	}

	@Test
	void canArticleBeFetched() {
//		Assertions.assertNotNull(customerJPAConnector.getCustomer(Integer.valueOf(0)));
//		Assertions.assertNotNull(customerJPAConnector.deleteCustomer(Integer.valueOf(0)));
//		
	}

}
