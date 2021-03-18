package de.leuphana.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

//@EnableJpaRepositories("de.leuphana.component.*")
//@EntityScan("de.leuphana.component.*")
//
////If the class with the annotation RestController is in a different package, this is necessary. 
//@ComponentScan("de.leuphana.connector") 
@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class })
public class ShopAPIConnectorRequester {

	@Autowired
	private APIGatewayRestConnectorRequester apiGatewayRestConnectorRequester;

	private static final Logger log = LoggerFactory
			.getLogger(ShopAPIConnectorRequester.class);

	public static void main(String[] args) {
		SpringApplication.run(ShopAPIConnectorRequester.class);
	}

	@Bean
	public void demo() {
		apiGatewayRestConnectorRequester.addNewArticle("Müll", "LeLeLe", 35.78f);
	}
}
