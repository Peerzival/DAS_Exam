package de.leuphana.connector;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
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
@ImportAutoConfiguration({FeignAutoConfiguration.class,
	HttpMessageConvertersAutoConfiguration.class})
class OrderRestConnectorRequesterTest {

	@Autowired
	private EntityManager entityManager;

	
	// FeignContext feignContext;

	private static int customerId;

	@Autowired
	private CustomerRestConnectorProvider customerRestConnectorProvider;

	@BeforeEach
	void setUp() throws Exception {
	//	feignContext.getInstance("order", OrderRestConnectorRequester.class);
		
		customerId = customerRestConnectorProvider.createCustomer("TestName", "TestAddress");
		customerRestConnectorProvider.addCartItem(customerId, 1);
		customerRestConnectorProvider.addCartItem(customerId, 1);
		customerRestConnectorProvider.addCartItem(customerId, 2);
		customerRestConnectorProvider.addCartItem(customerId, 3);
	}

	@AfterEach
	void tearDown() throws Exception {
		entityManager = null;
	//	feignContext = null;

	}

	@Test
	void checkOutCartToOrder() {
		System.out.println(customerRestConnectorProvider.checkOutCartToOrder(customerId));
		System.out.println(customerRestConnectorProvider.getCartItemsFromCustomer(customerId));
	}

}
