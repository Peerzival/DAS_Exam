package de.leuphana.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
  
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class })
public class ShopBootApplication {

	@Autowired
	private ShopRestController apiGatewayRestConnectorRequester;

	private static final Logger log = LoggerFactory
			.getLogger(ShopBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShopBootApplication.class);
	}
}
