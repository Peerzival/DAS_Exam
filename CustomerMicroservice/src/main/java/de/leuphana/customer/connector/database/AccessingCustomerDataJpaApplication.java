package de.leuphana.customer.connector.database;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import de.leuphana.customer.component.structure.Customer;

@SpringBootApplication
@EntityScan("de.leuphana.customer.component.structure")
public class AccessingCustomerDataJpaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(AccessingCustomerDataJpaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AccessingCustomerDataJpaApplication.class, args);			
	}
	
	  @Bean
	  public CommandLineRunner demo(CustomerRepository repository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(new Customer("Lenzelbert", "Drochtersen"));
	      repository.save(new Customer("Don Julio", "Bützfleth"));
	      repository.save(new Customer("Chris Dombowski", "Drochtersen"));
	      repository.save(new Customer("Leon the Rock", "Alpen"));
	      repository.save(new Customer("Levin Marcel Schnabel", "Moorrege"));

	      // fetch all customers
	      log.info("Customers found with findAll():");
	      log.info("-------------------------------");
	      for (Customer customer : repository.findAll()) {
	        log.info(customer.toString());
	      }
	      log.info("");

	      // fetch an individual customer by ID
	      Customer customer = repository.findById(1);
	      log.info("Customer found with findById(1L):");
	      log.info("--------------------------------");
	      log.info(customer.toString());
	      log.info("");

	      // fetch customers by last name
	      log.info("Customer found with findByLastName('Bauer'):");
	      log.info("--------------------------------------------");
	      repository.findByName("Bauer").forEach(bauer -> {
	        log.info(bauer.toString());
	      });
	      // for (Customer bauer : repository.findByLastName("Bauer")) {
	      //  log.info(bauer.toString());
	      // }
	      log.info("");
	    };
	  }

	}
	


