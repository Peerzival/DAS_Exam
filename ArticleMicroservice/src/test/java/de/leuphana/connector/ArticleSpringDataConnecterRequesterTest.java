package de.leuphana.connector;


import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.leuphana.component.behaviour.ArticleComponentService;
import de.leuphana.component.behaviour.ArticleRepository;
import de.leuphana.component.behaviour.exception.ArticleNotFoundException;
import de.leuphana.component.structure.Article;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleSpringDataConnecterRequesterTest {

	@Autowired private EntityManager entityManager;
	@Autowired private ArticleRepository articleRepository;
	
	@BeforeEach
	void setUp() throws Exception {			
		Article article = new Article("Leupht-Craftbeer", "Leupht", 2.5f);
		articleRepository.save(article);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(entityManager);
		Assertions.assertNotNull(articleRepository);
	}
	
	@Test
	void canArticleBePersisted() {
		Article article = new Article("Weihnachtsmann", "Leuphana", 7.77f);
		Assertions.assertNotNull(articleRepository.save(article));
	}
	
	@Test
	void canArticleBeFetched() {
		Article article = articleRepository.findByName("Leupht-Craftbeer").orElseThrow(
				() -> new ArticleNotFoundException("Leupht-Craftbeer"));
		
		System.out.println("\nFetched article: ");
		System.out.println(article.getName() + " by " + article.getManufactor());
		System.out.println("Price: " + article.getPrice() + " Euro\n");
		
		Assertions.assertNotNull(article.getName());
	}

}
