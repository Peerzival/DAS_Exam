package de.leuphana.component.order.structure;

import java.util.ArrayList;
import java.util.List;

import de.leuphana.component.article.structure.Article;

public class Order {

	private Integer orderId;
	private int customerId;
	private List<OrderPosition> orderPositions;
	
	public Order() {
		orderPositions = new ArrayList<OrderPosition>();
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getNumberOfArticles() {
		
		// TODO has to be implemented later
		return 0;
	}

	public double getTotalPrice() {
		double totalPrice = 0.0;

		Article article;
		for (OrderPosition orderPosition : orderPositions) {
			article = orderPosition.getArticle();

			totalPrice += orderPosition.getArticleQuantity() * article.getPrice();
		}

		return totalPrice;
	}

	public List<OrderPosition> getOrderPositions() {
		return orderPositions;
	}

	public void setOrderPositions(List<OrderPosition> orderPositions) {
		this.orderPositions = orderPositions;
	}
}