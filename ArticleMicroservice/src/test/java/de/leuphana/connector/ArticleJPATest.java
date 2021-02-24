package de.leuphana.connector;


//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import de.leuphana.component.article.structure.Article;
//
//class ShopJPAConnectorTest {
//
//	private Integer createdArticleId;
//	private Integer createdOrderId;
//	
//	@BeforeEach
//	void setUp() throws Exception {			
//		Article article = new Article("Weihnachtsmann", "Leuphana", 7.77f);
//		
//		// createdArticleId = shopJPAConnector.createArticle(article);
//	
//		//Article foundArticle = shopJPAConnector.getArticle(createdArticleId);
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
////		Assertions.assertTrue(shopJPAConnector.deleteOrder(createdOrderId));
////		Assertions.assertTrue(shopJPAConnector.deleteArticle(createdArticleId));
//	}
//
//	@Test
//	void canArticleBeFetched() {
//		Assertions.assertNotNull(shopJPAConnector.getArticle(createdArticleId));
//	}
//
//}
