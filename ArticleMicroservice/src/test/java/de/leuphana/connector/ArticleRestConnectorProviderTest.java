package de.leuphana.connector;

import java.util.List;

import javax.persistence.EntityManager;

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
import de.leuphana.component.structure.Article;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRestConnectorProviderTest {
	
	@Autowired private EntityManager entityManager;
	@Autowired private ArticleComponentService articleService;
	
	private static int articleId;
	
	@BeforeEach
	void setUp() throws Exception {			
		
		// Dummy-Instance
		articleId = articleService.createArticle("Leupht-Craftbeer", "Leupht", 2.5f);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void injectedComponentsAreNotNull() {
		Assertions.assertNotNull(entityManager);
	}
	
	@Test
	void canArticleBeAdded() {
		int articleId = articleService.createArticle("Nikotin", "Malboro", 2.50f);
		Assertions.assertTrue(articleId > 0);
	}
	
	@Test
	void canArticleBeUpdated() {
		String articleAsString = articleService
				.updateArticle(articleId, "Fusspils", "InnovativeDrinks", 0.50f);
		
		System.out.println("\nUpdated article: ");
		System.out.println(articleAsString);
		
		Assertions.assertNotNull(articleAsString);
	}

	@Test
	void canArticleBeFetchedById() {
		Article article = articleService.getArticleById(articleId);
		
		System.out.println("\nArticle with id '" + articleId + "':");
		System.out.println(article.getName() + " by " + article.getManufactor());
		System.out.println("Price: " + article.getPrice() + " Euro\n");
		
		Assertions.assertNotNull(article);
	}

	@Test
	void canArticleBeFetchedByName() {
		Article article = articleService.getArticleByName("Leupht-Craftbeer");
		
		System.out.println("\nArticle with name 'Leupht-Craftbeer':");
		System.out.println(article.getName() + " by " + article.getManufactor());
		System.out.println("Price: " + article.getPrice() + " Euro\n");
		
		Assertions.assertNotNull(article);
	}

	@Test
	void canAllArticlesBeFetched() {
		List<Article> articles = (List<Article>) articleService.getAllArticles();
		
		System.out.println("\nArticles fetched:");
		for (Article article : articles) {
			System.out.println("> " + article.getName()
				+ " by " + article.getManufactor()
				+ " for " + article.getPrice() + " Euro");
		}
		System.out.println("\n");
		Assertions.assertNotNull(articles);
	}
	
	@Test
	void canArticleBeDeleted() {
		String confirmation = articleService.deleteArticleById(articleId);
		
		System.out.println(confirmation);
		
		Assertions.assertNotNull(confirmation);
	}

}
