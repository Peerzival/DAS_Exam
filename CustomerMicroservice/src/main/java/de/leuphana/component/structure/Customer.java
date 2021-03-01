package de.leuphana.component.structure;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.leuphana.component.structure.Order;

@Entity
@Table(name = "DB_CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String name;
	private String address;

	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = de.leuphana.component.structure.Order.class, mappedBy = "customerId")
	@MapKeyColumn(name = "orderId")
	// @JoinColumn(name = "orderId")
	private Map<Integer, Order> orders;

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

//	public void addOrder(Order order) {
//		orders.put(order.getOrderId(), order);
//	}
//	public Map<Integer, Order> getOrders() {
//		return this.orders;
//	}

}