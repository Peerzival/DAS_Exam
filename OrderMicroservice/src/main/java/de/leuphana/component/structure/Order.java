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
	
//	It is ensured that the order position we add is always assigned the smallest available ID. 
	public void addOrderPosition(OrderPosition orderPosition) {
		//Set of all awarded id´s
		Set<Integer> ids = new HashSet<Integer>();
		for (OrderPosition orderPositionIterator : orderPositions) {
			ids.add(orderPositionIterator.getOrderPositionId());
		}
		
		if (ids.size() == 0) {
			orderPosition.setOrderPositionId(1);
			orderPositions.add(orderPosition);
			return;
		}
		//The lowest id that is not taken will assigned to the order position that we will add.
		boolean isContained = false;
		for(int i=1; i<=ids.size()+1; i++) {
			for (Integer integer : ids) {
				if(integer.intValue() == i) {
					isContained = true;
					break;
				}  	
			}
			if(!isContained) {
				//add order position with assigned id.
				orderPosition.setOrderPositionId(i);
				orderPositions.add(orderPosition);
				return;
			}
			isContained = false;
		}
	}
}