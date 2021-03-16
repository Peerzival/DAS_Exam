package de.leuphana.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.OrderRepository;
import de.leuphana.component.behaviour.exception.OrderNotFoundException;
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

	//TODO later change reponsebody String to int 
	
	// Curl
	// curl localhost:8280/order/addArticleToOrder -d articleId=2 -d quantity=27 -d orderId=2 -d customerId=1
	
	@PostMapping(path = "/addArticleToOrder")
	public String addArticleToOrder(@RequestParam int articleId, @RequestParam int quantity,
			@RequestParam int orderId, @RequestParam int customerId) {

		// TODO Exceptions
		// Check if article exists
		int idOfArticle = -1;
		try {
			idOfArticle = articleRestConnectorRequester.checkArticleId(articleId);
		} catch(RuntimeException ex){
			return ex.getClass().getSimpleName() + ex.getMessage();
		}

		// Get Order
		Order order;
		try {
			order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
		} catch (OrderNotFoundException ex) {
			order = new Order();
			order.setCustomerId(customerId);
		}

		// Create new OrderPosition and add to Order
		OrderPosition orderPosition = new OrderPosition();

		orderPosition.setArticleId(idOfArticle);
		orderPosition.setArticleQuantity(quantity);
		order.addOrderPosition(orderPosition);

		// Persist to DB
		orderRepository.save(order);
		//should later return orderId
		return "Article got added to order with id" + orderId + "\n";
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
		return "order deleted with id: " + orderId;
	}
}
