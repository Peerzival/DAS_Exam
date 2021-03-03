package de.leuphana.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.OrderRepository;
import de.leuphana.component.structure.Order;
import de.leuphana.component.structure.exception.OrderNotFoundException;

@RestController
//@RequestMapping(path = "/demo")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@PostMapping(path = "/addOrder") // Map ONLY POST Requests
	public @ResponseBody String addNewOrder() {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Order order = new Order();
		orderRepository.save(order);
		return "Saved\n";
	}

	@GetMapping(path = "/getOrder/{orderId}")
	public @ResponseBody Order getOrder(@PathVariable int orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
	}

	@GetMapping(path = "/getAllOrders")
	public @ResponseBody Iterable<Order> getAllOrders() {
		// This returns a JSON or XML with the users
		return orderRepository.findAll();
	}
	
	@DeleteMapping("/deleteOrder/{orderId}")
	public void deleteOrder(@PathVariable int orderId) {
		orderRepository.deleteById(orderId);
	}
}
