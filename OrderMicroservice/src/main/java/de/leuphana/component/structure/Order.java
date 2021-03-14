package de.leuphana.component.structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "DB_ORDER")
public class Order {

	private int orderId;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "customerId")
	private int customerId;

//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OrderPosition> orderPositions;

	public Order() {
		orderPositions = new ArrayList<OrderPosition>();
	}

	public int getCustomerId() {
		return customerId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@ElementCollection(targetClass = OrderPosition.class)
	@CollectionTable(name = "db_orderposition", joinColumns = @JoinColumn(name="relatedOrderId"))
//	@Column(name="orderId")
	public List<OrderPosition> getOrderPositions() {
		return orderPositions;
	}

	public void setOrderPositions(List<OrderPosition> orderPositions) {
		this.orderPositions = orderPositions;
	}
	
	public void addOrderPosition(OrderPosition orderPosition) {
		Set<Integer> ids = new HashSet<Integer>();
		for (OrderPosition orderPositionIterator : orderPositions) {
			ids.add(orderPositionIterator.getOrderPositionId());
			System.out.println(orderPositionIterator.getOrderPositionId());
		}
		
		boolean isContained = false;
		for(int i=1; i<=ids.size()+1; i++) {
			for (Integer integer : ids) {
				if(integer.intValue() == i) {
					isContained = true;
					break;
				}  	
			}
			if(!isContained) {
				orderPosition.setOrderPositionId(i);
				orderPositions.add(i, orderPosition);
				return;
			}
			isContained = false;
		}
	}
//		int i;
//		for (i = 0; i <= orderPositions.size() - 1; i++) {
//			if(orderPositions.get(i) == null) {
//				orderPosition.setOrderPositionId(i+1);
//				orderPositions.add(i, orderPosition);
//				break;
//			}
//			System.out.println("OrderPosition: " + i);
//		}
//		if(i == orderPositions.re() -1) {
//			System.out.println("In der IF");
//			orderPosition.setOrderPositionId(i+2);
//			orderPositions.add(i+1, orderPosition);
//		}
//	}

}