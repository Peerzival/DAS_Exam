package de.leuphana.component.behaviour;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.component.structure.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

//	  Order findById(int orderId);
	
}
