package de.leuphana.customer.component.structure;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.leuphana.component.order.structure.Order;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String name;
	private String address;

	@OneToOne(cascade = { CascadeType.ALL })
	private Cart cart;

	private Map<Integer, Order> orders;
	// List / Map Orders

	protected Customer() {
	}

	public Customer(String name, String address, Cart cart) {
		this.name = name;
		this.address = address;
		this.cart = cart;
		this.orders = new HashMap<>();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer integer) {
		this.customerId = integer;
	}

	public Cart getCart() {
		return cart;
	}

	public void addOrder(Order order) {
		orders.put(order.getOrderId(), order);
	}
	public Map<Integer, Order> getOrders() {
		return this.orders;
	}

}