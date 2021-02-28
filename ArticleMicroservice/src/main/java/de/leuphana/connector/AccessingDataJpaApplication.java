
package de.leuphana.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


// Test-commands for Curl
// curl localhost:8080/demo/add -d name=Weihnachtsmann -d manufactor=Leuphana -d price=10.5f
// curl 'localhost:8080/demo/all'
// curl localhost:8080/demo/get -d articleId=9

@SpringBootApplication
@EnableJpaRepositories("de.leuphana.component.article.behaviour")
@EntityScan("de.leuphana.component.article.structure")   
public class AccessingDataJpaApplication {

	public static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(ArticleRepository repository) {
//		return (args) -> {
//			
//			Map<String, String> reviews = new HashMap<String, String>();
//			
//			repository.save(new Article("iPhone X", "Apple Inc.", 999.90f));
//			repository.save(new Article("iPhone XS", "Apple Inc.", 1299.90f));
//			repository.save(new Article("Galaxy S21", "Samsung", 799.90f));
//			repository.save(new Article("Galaxy Note 8", "Samsung", 599.90f));
//			repository.save(new Article("Mate X", "Huawei", 399.90f));
//			
//			log.info("Articles found with findAll():");
//			log.info("-------------------------------");
//			for(Article article: repository.findAll()) {
//				log.info(article.getName() + " manufactured by " + article.getManufactor());
//			}
//			log.info("");
//			
//			Article article = repository.findById(1);
//			log.info("Article found with findById(1L):");
//		    log.info("--------------------------------");
//		    log.info(article.getName() + " manufactured by " + article.getManufactor());
//		    log.info("");
//		    
//		    log.info("Article found with findByName('iPhone X'):");
//		    log.info("--------------------------------------------");
//		    repository.findByName("iPhone X").forEach(iphoneX -> {
//		    	log.info(iphoneX.getName() + " manufactured by " + article.getManufactor());
//		    });
//		    log.info("");
//		};
//	}
	
	
}
