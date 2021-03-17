package de.leuphana.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.OrderRepository;
import de.leuphana.component.behaviour.exception.OrderNotFoundException;
import de.leuphana.component.structure.Order;
import de.leuphana.component.structure.OrderPosition;

@RestController
@RequestMapping(path = "/order")
public class OrderRestConnectorProvider {

	// Spring Data
	@Autowired
	private OrderRepository orderRepository;

	// Feign
	final ArticleRestConnectorRequester articleRestConnectorRequester;

	@Autowired
	public OrderRestConnectorProvider(
			ArticleRestConnectorRequester articleRestConnectorRequester) {
		this.articleRestConnectorRequester = articleRestConnectorRequester;
	}

	// -------------------- CRUD -------------------- \\
	// -------------------------------------------------------------------------

	// CREATE
	// curl localhost:8280/order/createOrder -d customerId=value
	// -------------------------------------------------------------------------
	@PostMapping(path = "/createOrder")
	public int createOrder(@RequestParam(name = "customerId") int customerId) 
	{
		Order order = new Order(customerId);
		Order savedOrder = orderRepository.save(order);

		return savedOrder.getOrderId();
	}

	// READ
	// curl localhost:8280/order/getOrder/value
	// -------------------------------------------------------------------------

	@GetMapping(path = "/getOrder/{orderId}")
	public Order getOrder(
		@PathVariable(name = "orderId") int orderId) {
		return orderRepository.findById(orderId).orElseThrow(
				() -> new OrderNotFoundException(orderId));
	}

	@GetMapping(path = "/getAllOrders")
	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	// UPDATE
	// curl localhost:8280/order/addArticleToOrder -d articleId=value -d quantity=value -d
	// orderId=value -d customerId=value
	// -------------------------------------------------------------------------

	// TODO later change reponsebody String to int
	@PostMapping(path = "/addArticleToOrder")
	public String addArticleToOrder(
		@RequestParam(name="articleId") int articleId,
		@RequestParam(name="quantity") int quantity,
		@RequestParam(name="orderId") int orderId) {

		// Check if article exists
		int idOfArticle = -1;
		try {
			idOfArticle = articleRestConnectorRequester
					.checkArticleId(articleId);
		} catch (RuntimeException ex) {
			return ex.getClass().getSimpleName()
					+ ex.getMessage();
		}
	
		Order order = orderRepository.findById(orderId).orElseThrow(() -> 
			new OrderNotFoundException(orderId));
		
		OrderPosition orderPosition = new OrderPosition();
		orderPosition.setArticleId(idOfArticle);
		orderPosition.setArticleQuantity(quantity);
		
		order.addOrderPosition(orderPosition);

		orderRepository.save(order);
		
		return "Article with id '" + orderId + "' added to order.\n";
	}

	// DELETE
	// curl localhost:8280/order/deleteOrder -d orderId=value
	// -------------------------------------------------------------------------

	@PostMapping(path = "/deleteOrder")
	public String deleteOrder(@RequestParam int orderId) {
		orderRepository.deleteById(orderId);
		return "order deleted with id: " + orderId;
	}

	// curl localhost:8280/order/deleteOrderPosition -d positionId=value -d orderId=value
	// -------------------------------------------------------------------------
	@PostMapping(path = "/deleteOrderPosition")
	public String deleteOrderPosition(
		@RequestParam int positionId,
		@RequestParam int orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException(
						orderId));

		OrderPosition orderPosition = order
				.deleteOrderPosition(positionId);

		if (orderPosition != null) {
			orderRepository.save(order);
			return "orderPosition with id " + positionId
					+ " in order " + order.getOrderId()
					+ " got deleted.";
		} else
			return "Not a valid order position Id.";
	}
}
