package de.leuphana.connector.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
//@Table(name="DB_ORDER")
public class OrderEntity {

	@Id
	@GeneratedValue
	private Integer orderId;
	private int customerId;
	
    private List<OrderPositionEntity> orderPositions;
	
	public OrderEntity() {
		orderPositions = new ArrayList<OrderPositionEntity>();
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_orderId", referencedColumnName = "orderId")
	public List<OrderPositionEntity> getOrderPositions() {
		return orderPositions;
	}

	public void setOrderPositions(List<OrderPositionEntity> orderPositions) {
		this.orderPositions = orderPositions;
	}

}