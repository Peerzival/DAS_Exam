package de.leuphana.component.structure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DB_CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String name;
	private String address;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	private Cart cart;

	@ElementCollection
	private List<Integer> orderIds;

	public Customer() {
	}

	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
		orderIds = new ArrayList<Integer>();

	}

	public List<Integer> getOrderIds() {
		return orderIds;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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
	
	public void addOrder(int orderId) {
		orderIds.add(orderId);
	}
}