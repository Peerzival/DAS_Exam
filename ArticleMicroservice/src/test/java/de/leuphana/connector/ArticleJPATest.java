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

import de.leuphana.component.article.behaviour.ArticleRepository;
import de.leuphana.component.article.structure.Article;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleJPATest {

	@Autowired private EntityManager entityManager;
	@Autowired private ArticleRepository articleRepository;
	
	private Logger logger;
	
	@BeforeEach
	void setUp() throws Exception {			
		logger = LogManager.getLogger(this.getClass());
	}

	@AfterEach
	void tearDown() throws Exception {
//		Assertions.assertTrue(shopJPAConnector.deleteArticle(createdArticleId));
	}

	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(entityManager);
		Assertions.assertNotNull(articleRepository);
	}
	
	@Test
	void canArticleBePersisted() {
		Article article = new Article("Weihnachtsmann", "Leuphana", 7.77f);
		
		// Persist
		articleRepository.save(article);
		
		// Check persistence
		Assertions.assertNotNull(articleRepository.findByName("Weihnachtsmann"));
		
		// additional logs TODO remove or change to DEBUG instead of INFO
		logger.info(articleRepository.findByName("Weihnachtsmann").get(0).getName());
		logger.info(articleRepository.findByName("Weihnachtsmann").get(0).getManufactor());
		logger.info(articleRepository.findByName("Weihnachtsmann").get(0).getPrice());
	}

}
