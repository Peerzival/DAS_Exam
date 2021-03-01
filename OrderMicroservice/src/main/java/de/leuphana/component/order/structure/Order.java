package de.leuphana.component.order.structure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DB_ORDER")
public class Order {

	private Integer orderId;

	private Integer customerId;

	private List<OrderPosition> orderPositions;

	public Order() {
		orderPositions = new ArrayList<OrderPosition>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = de.leuphana.customer.component.structure.Customer.Class()) //TODO entkommentieren, wenn das API Gateway erstellt wurde
//	@JoinColumn(name = "CUSTOMER_ID")
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "positionId") // vielleicht später hinzufügen, weil originär: name = "positionId",
										// referencedColumnName = "orderId"
	public List<OrderPosition> getOrderPositions() {
		return orderPositions;
	}

	public void setOrderPositions(List<OrderPosition> orderPositions) {
		this.orderPositions = orderPositions;
	}

}