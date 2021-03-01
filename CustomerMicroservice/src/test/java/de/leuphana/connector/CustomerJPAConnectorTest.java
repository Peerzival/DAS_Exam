package de.leuphana.connector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.connector.accessingdatamysql.AccessingCustomerDataJpaApplication;
import de.leuphana.customer.connector.accessingdatamysql.MainCustomerController;

class CustomerJPAConnectorTest {
//
//	private CustomerJPAConnector customerJPAConnector;
	Customer customer;
	AccessingCustomerDataJpaApplication accessingCustomerDataJpaApplication;
	
	@BeforeEach
	void setUp() throws Exception {
		MainCustomerController c = new MainCustomerController();
		c.addNewCustomer("name", "address", new Cart());
		
	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void canArticleBeFetched() {
		
	}

}
