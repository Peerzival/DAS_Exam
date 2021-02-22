package de.leuphana.connector;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.transaction.annotation.Transactional;

import de.leuphana.component.shop.structure.Article;
import de.leuphana.component.shop.structure.Order;
import de.leuphana.connector.entity.ArticleEntity;
import de.leuphana.connector.entity.OrderEntity;
import de.leuphana.connector.mapper.ArticleMapper;
import de.leuphana.connector.mapper.OrderMapper;

@Transactional()
public class ShopJPAConnector {

	private EntityManager entityManager;

	public ShopJPAConnector() {
	}

	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// DML
	public int createArticle(Article article) {
		int articleId = 0;

		try {
			ArticleEntity articleEntity = ArticleMapper.mapArticleToArticleEntity(article);
			entityManager.persist(articleEntity);
			articleId = articleEntity.getArticleId();
		} catch (EntityExistsException entityExistsException) {
			// Logger
			System.out.println("EntityExistsException" + entityExistsException.toString());
		}

		return articleId;
	}
	
	public int createOrder(Order order) {
		int orderId = 0;
		
		try {
			OrderEntity orderEntity = OrderMapper.mapOrderToOrderEntity(order);
			entityManager.persist(orderEntity);
			orderId = orderEntity.getOrderId();
		} catch (EntityExistsException entityExistsException) {
			// Logger
			System.out.println("EntityExistsException" + entityExistsException.toString());
		}
		
		return orderId;
	}

	public boolean deleteArticle(Integer articleId) {
		boolean isArticleRemoved = false;
		
		try {
			ArticleEntity articleEntity = entityManager.find(ArticleEntity.class, articleId);
			entityManager.remove(articleEntity);
			ArticleEntity articleEntityFound = entityManager.find(ArticleEntity.class, articleId);
			if(articleEntityFound == null) {
				isArticleRemoved = true;
			}
		} catch (EntityExistsException entityExistsException) {
			// Logger
			System.out.println("EntityExistsException" + entityExistsException.toString());
		}
		
		return isArticleRemoved;
	}

	public Article getArticle(Integer articleId) {
		ArticleEntity articleEntity = entityManager.find(ArticleEntity.class, Integer.valueOf(articleId));

		return ArticleMapper.mapArticleEntityToArticle(articleEntity);
	}
	
	public boolean deleteOrder(Integer orderId) {
		boolean isOrderRemoved = false;
		
		try {
			OrderEntity orderEntity = entityManager.find(OrderEntity.class, orderId);
			entityManager.remove(orderEntity);
			OrderEntity orderEntityFound = entityManager.find(OrderEntity.class, orderId);
			if(orderEntityFound == null) {
				isOrderRemoved = true;
			}
		} catch (EntityExistsException entityExistsException) {
			// Logger
			System.out.println("EntityExistsException" + entityExistsException.toString());
		}
		
		return isOrderRemoved;
	}

	public Order getOrder(Integer orderId) {
		OrderEntity orderEntity = entityManager.find(OrderEntity.class, Integer.valueOf(orderId));

		return OrderMapper.mapOrderEntityToOrder(orderEntity);
	}
}
