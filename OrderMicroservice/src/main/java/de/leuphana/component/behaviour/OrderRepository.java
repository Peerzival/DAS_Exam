package de.leuphana.component.behaviour;

import org.springframework.data.repository.CrudRepository;

import de.leuphana.component.structure.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

//	  Order findById(int orderId);
	
}
