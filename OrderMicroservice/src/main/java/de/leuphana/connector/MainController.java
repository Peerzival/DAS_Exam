package de.leuphana.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.leuphana.component.behaviour.OrderRepository;
import de.leuphana.component.structure.Order;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewOrder() {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Order order = new Order();
		orderRepository.save(order);
		return "Saved\n";
	}

	@PostMapping(path = "/get")
	public @ResponseBody Order getOrder(@RequestParam int orderId) {
		return orderRepository.findById(orderId);
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Order> getAllOrders() {
		// This returns a JSON or XML with the users
		return orderRepository.findAll();
	}
}
