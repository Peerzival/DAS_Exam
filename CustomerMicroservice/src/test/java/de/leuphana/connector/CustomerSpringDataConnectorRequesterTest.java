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

import de.leuphana.component.behaviour.CustomerRepository;
import de.leuphana.component.structure.Cart;
import de.leuphana.component.structure.Customer;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ImportAutoConfiguration(FeignAutoConfiguration.class)
class CustomerSpringDataConnectorRequesterTest {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private CustomerRepository customerRepository;
	private Cart cart;
	private Customer customer;

	@BeforeEach
	void setUp() throws Exception {
	
		// Persisting Test Customer
		Customer testCustomer = new Customer("name", "address");
		testCustomer = customerRepository.save(testCustomer);

		Cart testCart = new Cart();
		testCart.addCartItem(1);
		testCart.addCartItem(1);
		testCart.addCartItem(2);
		testCart.setCartId(testCustomer.getCustomerId());
		testCustomer.setCart(testCart);

		Assertions.assertNotNull(customerRepository.save(testCustomer));

	}

	@AfterEach
	void tearDown() throws Exception {
		entityManager = null;
		customerRepository = null;
		cart = null;
		customer = null;

	}
	
	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(entityManager);
		Assertions.assertNotNull(customerRepository);
	}

	@Test
	void canCustomerBePersisted() {
		customer = new Customer("Harald Krull", "St. Pauli Reeperbahn");

		cart = new Cart();
		cart.addCartItem(1);
		cart.addCartItem(1);
		cart.addCartItem(2);

		// Persist customer to set the Id
		customer = customerRepository.save(customer);

		// Create a Cart with identical Id
		cart.setCartId(customer.getCustomerId());
		customer.setCart(cart);

		// Persist the new Customer
		customer = customerRepository.save(customer);
		Assertions.assertNotNull(customer);
	}

	@Test
	void isCustomerCorrectlyPersisted() {

		// Check persistence
		Customer customerTest = customerRepository.findByName("name").get(0);

		Assertions.assertEquals(customerTest.getName(), "name");
		Assertions.assertEquals(customerTest.getAddress(), "address");
		Assertions.assertEquals(customerTest.getCart().getCartItems().get(1).getQuantity(), 2);


	}

}
