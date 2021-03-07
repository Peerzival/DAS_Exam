package de.leuphana.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.OrderRepository;
import de.leuphana.component.structure.Article;
import de.leuphana.component.structure.Order;
import de.leuphana.component.structure.OrderPosition;
import de.leuphana.component.structure.exception.OrderNotFoundException;

@RestController
@RequestMapping(path = "/order-maincontroller")
public class MainController {
	
//	@Value("${server.port}")
//	private int port;

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ArticleRestConnectorRequester articleRestConnectorRequester;

	@PostMapping(path = "/addArticleToOrder/{article}") // Map ONLY POST Requests
	public @ResponseBody String addNewOrder(@PathVariable Article article) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		int articleId = articleRestConnectorRequester.getArticleById(article.getArticleId().toString()).getArticleId();
		OrderPosition orderPosition = new OrderPosition();
		orderPosition.setArticleId(articleId);
		orderPosition.setPositionId();
		Order order = new Order();
		order.setOrderId();
		order.addOrderPosition(orderPosition);
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
	public @ResponseBody String deleteOrder(@PathVariable int orderId) {
		orderRepository.deleteById(orderId);
		return "Deleted\n";
	}
}
