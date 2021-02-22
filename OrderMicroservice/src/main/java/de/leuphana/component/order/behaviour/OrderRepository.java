package de.leuphana.component.order.behaviour;

import org.springframework.data.repository.CrudRepository;

import de.leuphana.component.order.structure.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

	Integer createOrder(Order order);
	boolean deleteOrder(Integer orderId);
	Order getOrder(Integer orderId);
	
}
