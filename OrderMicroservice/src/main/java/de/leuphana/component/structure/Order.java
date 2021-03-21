package de.leuphana.component.structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DB_ORDER")
public class Order {

	private int orderId;

	private int customerId;

	private List<OrderPosition> orderPositions;

	public Order() {
		orderPositions = new ArrayList<OrderPosition>();
	}

	public Order(int customerId) {
		this.customerId = customerId;
		orderPositions = new ArrayList<OrderPosition>();
	}

//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "customerId")
	public int getCustomerId() {
		return customerId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

//	@ElementCollection(targetClass = OrderPosition.class)
//	@CollectionTable(name = "db_orderposition",
//		joinColumns = @JoinColumn(name="relatedOrderId"))
//	@Column(name="orderId")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	public List<OrderPosition> getOrderPositions() {
		return orderPositions;
	}

	public void setOrderPositions(List<OrderPosition> orderPositions) {
		this.orderPositions = orderPositions;
	}

	public void addOrderPosition(OrderPosition orderPosition) {
		orderPositions.add(orderPosition);
	}

	public OrderPosition deleteOrderPosition(
		int orderPositionId) {
		for (OrderPosition orderPosition : orderPositions) {
			if (orderPosition
					.getOrderPositionId() == orderPositionId) {
				orderPositions.remove(orderPosition);
				return orderPosition;
			}
		}
		return null;
	}
}