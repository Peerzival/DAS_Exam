package de.leuphana.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import de.leuphana.configuration.OrderConfiguration;

@SpringBootApplication // exclude= {DataSourceAutoConfiguration.class})
@EnableJpaRepositories("de.leuphana.component.*")
@EntityScan("de.leuphana.component.*")
//@ComponentScan(basePackages = {"de.leuphana.component.order.structure"})
@EnableFeignClients
@EnableConfigurationProperties(OrderConfiguration.class)
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}
}