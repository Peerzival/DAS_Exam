package de.leuphana.customer.component.structure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer customerId;
	private String name;
	private String address;
	
	// private Cart cart;
	// private List / Map Orders

	protected Customer() {}
	
	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
	
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

	
//	private Cart cart;
//	private Map<Integer, Order> orders;
//

	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer integer) {
		this.customerId = integer;
	}

//	public Cart getCart() {
//		return cart;
//	}
//	
//	public void addOrder(Order order) {
//		orders.put(order.getOrderId(), order);
//	}

}