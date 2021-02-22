package de.leuphana.connector;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.leuphana.component.article.structure.Article;
import de.leuphana.component.order.structure.Order;
import de.leuphana.component.order.structure.OrderPosition;

class ShopJPAConnectorTest {

	private OrderSpringDataConnectorRequester shopJPAConnector;
	private Integer createdArticleId;
	private Integer createdOrderId;
	
	@BeforeEach
	void setUp() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext-JPA-Connector.xml");
		
		shopJPAConnector = (OrderSpringDataConnectorRequester)applicationContext.getBean("shopJPAConnector");
				
		Article article = new Article();
		article.setName("Weihnachtsmann");
		article.setManufactor("Leuphana");
		
		createdArticleId = shopJPAConnector.createArticle(article);
		
		// same time later
		
		Article foundArticle = shopJPAConnector.getArticle(createdArticleId);
		
		OrderPosition orderPosition = new OrderPosition();
		orderPosition.setArticle(foundArticle);
		orderPosition.setArticleQuantity(2);
		
		Order order = new Order();
		order.setCustomerId(1);
		
		List<OrderPosition> orderPositions = new ArrayList<OrderPosition>();
		orderPositions.add(orderPosition);
		order.setOrderPositions(orderPositions);
		
		createdOrderId = shopJPAConnector.createOrder(order);
		Assertions.assertNotNull(createdOrderId);
	}

	@AfterEach
	void tearDown() throws Exception {
//		Assertions.assertTrue(shopJPAConnector.deleteOrder(createdOrderId));
//		Assertions.assertTrue(shopJPAConnector.deleteArticle(createdArticleId));
	}

	@Test
	void canArticleBeFetched() {
		Assertions.assertNotNull(shopJPAConnector.getArticle(createdArticleId));
	}

}
