package de.leuphana.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication 
@EnableJpaRepositories("de.leuphana.component.*")
@EntityScan("de.leuphana.component.*")

//If the class with the annotation RestController is in a different package, this is necessary. 
@ComponentScan("de.leuphana.connector") 
@EnableFeignClients
@EnableDiscoveryClient
public class OrderSpringDataConnectorRequester {

	private static final Logger log = LoggerFactory.getLogger(OrderSpringDataConnectorRequester.class);

	public static void main(String[] args) {
		SpringApplication.run(OrderSpringDataConnectorRequester.class);
	}
}