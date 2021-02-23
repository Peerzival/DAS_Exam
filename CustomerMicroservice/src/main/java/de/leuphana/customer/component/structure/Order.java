package de.leuphana.component.customer.structure;

import java.util.HashMap;
import java.util.Map;

import de.leuphana.component.order.structure.Order;

public class Order {
	private static Integer lastGeneratedCustomerId = 0;

	private Integer customerId;
	private String name;
	private String address;
	private Cart cart;
	private Map<Integer, Order> orders;

	public Order(Cart cart) {
		this.customerId = ++lastGeneratedCustomerId;
		this.cart = cart;
		orders = new HashMap<Integer, Order>();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Cart getCart() {
		return cart;
	}
	
	public void addOrder(Order order) {
		orders.put(order.getOrderId(), order);
	}

}