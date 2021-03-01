package de.leuphana.customer.connector.accessingdatamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.Customer;

@SpringBootApplication()
@EntityScan("de.leuphana.customer.component.structure")
public class AccessingCustomerDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingCustomerDataJpaApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(AccessingCustomerDataJpaApplication.class, args);
	}



}
