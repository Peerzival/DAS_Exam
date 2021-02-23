package de.leuphana.connector.order;

import java.util.ArrayList;
import java.util.List;

import de.leuphana.component.order.behaviour.OrderRepository;
import de.leuphana.component.order.structure.Order;

//@Service
//@Transactional()
public class OrderSpringDataConnectorRequester {

//	@Autowired // wenn eine Instanz des Orderservice erzeugt wird, dann wird auch eine Instanz
	// für das mit Autowired gekennzeichnete Objekt erzeugt.
	private OrderRepository orderRepository;

	public OrderSpringDataConnectorRequester() {
	}

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}

	public void addOrder(Order order) {
		orderRepository.save(order);
	}

	public Order getOrder(int orderId) {
		return orderRepository.findById(orderId);
	}

	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	public void deleteOrder(Integer orderId) {
		orderRepository.deleteById(orderId);
	}
}