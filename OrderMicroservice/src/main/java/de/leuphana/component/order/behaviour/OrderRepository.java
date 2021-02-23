package de.leuphana.component.order.behaviour;

import org.springframework.data.repository.CrudRepository;

import de.leuphana.component.order.structure.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

	  Order findById(int orderId);
	
}
