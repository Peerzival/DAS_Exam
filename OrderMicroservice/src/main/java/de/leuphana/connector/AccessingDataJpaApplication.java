package de.leuphana.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import de.leuphana.component.order.behaviour.OrderRepository;
import de.leuphana.component.order.structure.Order;

@SpringBootApplication // exclude= {DataSourceAutoConfiguration.class})
@EnableJpaRepositories("de.leuphana.component.order.behaviour")
@EntityScan("de.leuphana.component.*.*")
//@ComponentScan(basePackages = {"de.leuphana.component.order.structure"})
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}
}