package de.leuphana.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * The Eureka Server holds the information about all client-service
 * applications. Every Micro service will register into the Eureka server and
 * Eureka server knows all the client applications running on each port and IP
 * address.
 * 
 * @author Max Gnewuch
 * @author Henrik Prueß
 * @author Andreas Baechler
 * 
 *
 */

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class })
@EnableEurekaServer
public class EurekaDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				EurekaDiscoveryServiceApplication.class,
				args);
	}
}
