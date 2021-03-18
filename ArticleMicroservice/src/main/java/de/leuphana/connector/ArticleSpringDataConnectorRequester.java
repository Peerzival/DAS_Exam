
package de.leuphana.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Commands for docker deployment
// cd C:\Users\henri\git\DAS_Exam\ArticleMicroservice
// mvn -N io.takari:maven:wrapper
// mvnw package && java -jar target/ArticleMicroService.jar
// docker build -t springio/articlemicroservice .
// docker run -p 8081:8080 springio/articlemicroservice

@SpringBootApplication
@EnableJpaRepositories("de.leuphana.component.behaviour")
@EntityScan("de.leuphana.component.structure") 

//If the class with the annotation RestController is in a different package, this is necessary. 
@ComponentScan("de.leuphana.connector")
@EnableDiscoveryClient
public class ArticleSpringDataConnectorRequester {

	public static final Logger log = 
			LoggerFactory.getLogger(ArticleSpringDataConnectorRequester.class);

	public static void main(String[] args) {
		SpringApplication.run(
				ArticleSpringDataConnectorRequester.class, args);
	}
}
