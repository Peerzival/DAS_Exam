package de.leuphana.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The API gateway enables all microservices to be addressed via the same port,
 * although they run on different ports.
 * 
 * @author Max Gnewuch
 * @author Henrik Pruess
 * @author Andreas Baechler
 */

@SpringBootApplication
@EnableEurekaClient
public class APIGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(
				APIGatewayApplication.class, args);
	}
}
