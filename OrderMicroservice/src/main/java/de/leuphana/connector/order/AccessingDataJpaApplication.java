package de.leuphana.connector.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import de.leuphana.component.order.behaviour.OrderRepository;
import de.leuphana.component.order.structure.Order;

@SpringBootApplication
@EnableJpaRepositories("de.leuphana.component.order.behaviour")
@EntityScan("de.leuphana.component.*.*")
@ComponentScan(basePackages = {"de.leuphana.component.order.structure"})
public class AccessingDataJpaApplication {

  private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class);
  }

  @Bean
  public CommandLineRunner demo(OrderRepository repository) {
    return (args) -> {
      // save a few customers
      repository.save(new Order());
      repository.save(new Order());
      repository.save(new Order());
      repository.save(new Order());
      repository.save(new Order());

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      for (Order order : repository.findAll()) {
        log.info(order.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      Order order = repository.findById(1);
      log.info("Order found with findById(1):");
      log.info("--------------------------------");
      log.info(order.toString());
      log.info("");

      // fetch customers by last name
      log.info("Customer found with findByLastName('Bauer'):");
      log.info("--------------------------------------------");
//      repository.findByLastName("Bauer").forEach(bauer -> {
//        log.info(bauer.toString());
//      });
      // for (Customer bauer : repository.findByLastName("Bauer")) {
      //  log.info(bauer.toString());
      // }
      log.info("");
    };
  }

}