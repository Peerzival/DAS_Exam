package de.leuphana.component.order.behaviour;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.component.order.structure.Order;

@Service
public class OrderService {

	@Autowired // wenn eine Instanz des Orderservice erzeugt wird, dann wird auch eine Instanz
				// für das mit Autowired gekennzeichnete Objekt erzeugt.
	private OrderRepository orderRepository;

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}

	public void addOrder(Order order) {
		orderRepository.save(order);
	}

	public Order getOrder(Integer orderId) throws RelationNotFoundException {
		return orderRepository.findById(orderId).orElseThrow(() -> new RelationNotFoundException()) ;
	}
	
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}
	
	public void deleteOrder(Integer orderId) {
		orderRepository.deleteById(orderId);
	}

}
