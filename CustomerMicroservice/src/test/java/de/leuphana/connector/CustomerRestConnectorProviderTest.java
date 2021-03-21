package de.leuphana.connector;

import javax.persistence.EntityManager;

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

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ImportAutoConfiguration(FeignAutoConfiguration.class)
class CustomerRestConnectorProviderTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private CustomerRestConnectorProvider customerRestConnectorProvider;

	private static int testCustomerId;
	private static int deleteCustomerId;

	@BeforeEach
	void setUp() throws Exception {

		testCustomerId = customerRestConnectorProvider.createCustomer("", "");
		deleteCustomerId = customerRestConnectorProvider.createCustomer("deleteCustomer", "");

		Assertions.assertNotNull(testCustomerId);
		Assertions.assertNotNull(deleteCustomerId);
	}

	@AfterEach
	void tearDown() throws Exception {
		customerRestConnectorProvider = null;
		entityManager = null;

	}

	@Test
	void createCustomer() {
		System.out.println(customerRestConnectorProvider.createCustomer("CustomerName", "CustomerAddress"));
	}

	@Test
	void getCustomerString() {
		System.out.println(customerRestConnectorProvider.getCustomerString(testCustomerId));
	}

	@Test
	void getAllCustomersAsString() {
		System.out.println(customerRestConnectorProvider.getAllCustomersAsString());
	}

	@Test
	void changeCustomerAddress() {
		System.out.println(customerRestConnectorProvider.changeCustomerAddress(testCustomerId, "new address"));
	}

	@Test
	void changeCustomerName() {
		System.out.println(customerRestConnectorProvider.changeCustomerName(testCustomerId, "new name"));
	}

	@Test
	void deleteCustomer() {
		System.out.println(customerRestConnectorProvider.deleteCustomer(deleteCustomerId));
	}

	@Test
	void addCartItem() {
		System.out.println(customerRestConnectorProvider.addCartItem(testCustomerId, 2));
	}

	@Test
	void getCartItemsFromCustomer() {
		System.out.println(customerRestConnectorProvider.getCartItemsFromCustomer(testCustomerId));
	}

	@Test
	void decrementArticleFromCartItem() {
		System.out.println(customerRestConnectorProvider.decrementArticleFromCartItem(testCustomerId, 2));
	}

	@Test
	void deleteArticleFromCartItem() {
		System.out.println(customerRestConnectorProvider.deleteArticleFromCartItem(testCustomerId, 2));
	}

}
