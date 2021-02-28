package de.leuphana.connector;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.leuphana.component.article.structure.Article;
import de.leuphana.connector.accessingdatamysql.AccessingDataJpaApplication;
import de.leuphana.connector.accessingdatamysql.MainController;

class ArticleJPATest {

	private int createdArticleId;
	private MainController mainController;
	
	@BeforeEach
	void setUp() throws Exception {			
		Article article = new Article("Weihnachtsmann", "Leuphana", 7.77f);
		
		AccessingDataJpaApplication.main(new String[]{"",""});
		
		mainController = new MainController();
		createdArticleId = mainController.addNewArticle(article.getName(), 
				article.getManufactor(), article.getPrice());
	
		//Article foundArticle = shopJPAConnector.getArticle(createdArticleId);
	}

	@AfterEach
	void tearDown() throws Exception {
//		Assertions.assertTrue(shopJPAConnector.deleteOrder(createdOrderId));
//		Assertions.assertTrue(shopJPAConnector.deleteArticle(createdArticleId));
	}

	@Test
	void canArticleBeFetched() {
		Assertions.assertNotNull(mainController.getArticle(createdArticleId));
	}

}
