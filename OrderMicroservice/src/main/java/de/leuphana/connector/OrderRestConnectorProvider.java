package de.leuphana.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.OrderRepository;
import de.leuphana.component.behaviour.exception.OrderNotFoundException;
import de.leuphana.component.structure.Article;
import de.leuphana.component.structure.Order;
import de.leuphana.component.structure.OrderPosition;

@RestController
@RequestMapping(path = "/order")
public class OrderRestConnectorProvider {

	@Autowired
	private OrderRepository orderRepository;
	final ArticleRestConnectorRequester articleRestConnectorRequester;
	
	@Autowired
	public OrderRestConnectorProvider(ArticleRestConnectorRequester articleRestConnectorRequester) {
		this.articleRestConnectorRequester = articleRestConnectorRequester;
	}
//	public ArticleRestConnectorRequester articleRestConnectorRequester;

	@GetMapping(path = "/addArticleToOrder/{articleId}") // Map ONLY POST Requests
	public @ResponseBody String addNewOrder(@PathVariable(name="articleId") int articleId) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Article article = articleRestConnectorRequester.getArticleById(articleId);
		OrderPosition orderPosition = new OrderPosition();
		orderPosition.setArticleId(article.getArticleId());
		Order order = new Order();
		order.addOrderPosition(orderPosition);
		orderRepository.save(order);
		return "Saved\n";
	}

	@GetMapping(path = "/getOrder")
	public @ResponseBody Order getOrder(@RequestParam int orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
	}

	@GetMapping(path = "/getAllOrders")
	public @ResponseBody Iterable<Order> getAllOrders() {
		// This returns a JSON or XML with the users
		return orderRepository.findAll();
	}

	@DeleteMapping(path = "/deleteOrder")
	public @ResponseBody String deleteOrder(@RequestParam int orderId) {
		orderRepository.deleteById(orderId);
		return "Deleted\n";
	}
}
