package de.leuphana.component.behaviour;

import de.leuphana.component.structure.Order;

public interface OrderService {

	int createOrder(int customerId);

	Order getOrder(int orderId);

	String getOrderString(int orderId);

	Iterable<Order> getAllOrders();

	String getAllOrdersAsString();

	String addArticleToOrder(int articleId,
		int quantity,
		int orderId);

	String deleteOrder(int orderId);
}