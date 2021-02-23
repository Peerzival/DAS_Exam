package de.leuphana.component.order.structure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DB_ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer oId;
	private int cId;
	private String useless;
//    private List<OrderPositionEntity> orderPositions;
	
//	public OrderEntity() {
//		orderPositions = new ArrayList<OrderPositionEntity>();
//	}
	
	public Integer getOrderId() {
		return oId;
	}
	
	public void setOrderId(Integer orderId) {
		this.oId = orderId;
	}
	
	public int getCustomerId() {
		return cId;
	}

	public void setCustomerId(int customerId) {
		this.cId = customerId;
	}
	
//	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
//	@JoinColumn(name = "fk_orderId", referencedColumnName = "orderId")
//	public List<OrderPositionEntity> getOrderPositions() {
//		return orderPositions;
//	}
//
//	public void setOrderPositions(List<OrderPositionEntity> orderPositions) {
//		this.orderPositions = orderPositions;
//	}

}