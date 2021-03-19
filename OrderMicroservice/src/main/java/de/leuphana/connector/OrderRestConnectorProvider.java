package de.leuphana.connector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.OrderRepository;
import de.leuphana.component.behaviour.OrderService;
import de.leuphana.component.behaviour.exception.OrderNotFoundException;
import de.leuphana.component.structure.Order;
import de.leuphana.component.structure.OrderPosition;

@RestController
@RequestMapping(path = "/order")
public class OrderRestConnectorProvider
		implements OrderService {

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
	// curl localhost:8880/order/createOrder -d customerId=value
	// -------------------------------------------------------------------------
	@Override
	@PostMapping(path = "/createOrder")
	public int createOrder(
		@RequestParam(name = "customerId") int customerId) {
		Order order = new Order(customerId);
		Order savedOrder = orderRepository.save(order);

		return savedOrder.getOrderId();
	}

	// READ
	// curl localhost:8280/order/getOrder/value
	// -------------------------------------------------------------------------

	@Override
	@GetMapping(path = "/getOrder/{orderId}")
	public Order getOrder(
		@PathVariable(name = "orderId") int orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(
						() -> new OrderNotFoundException(
								orderId));
	}

	@Override
	@PostMapping(path = "/getOrderString")
	public String getOrderString(
		@RequestParam int orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(
						() -> new OrderNotFoundException(
								orderId));

		List<OrderPosition> orderPositions = order
				.getOrderPositions();

		String orderString = "> Order id: "
				+ order.getOrderId() + "\n";

		for (OrderPosition orderPosition : orderPositions) {
			orderString = orderString
					+ "Order position: "
					+ orderPosition
							.getOrderPositionId()
					+ "    Article id: "
					+ orderPosition.getArticleId()
					+ "    Article quantity: "
					+ orderPosition
							.getArticleQuantity()
					+ "\n";
		}

		return orderString;
	}

	@Override
	@GetMapping(path = "/getAllOrders")
	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	@GetMapping(path = "/getAllOrdersAsString")
	public String getAllOrdersAsString() {
		List<Order> orders = (List<Order>) orderRepository
				.findAll();
		String orderString = new String();

		for (Order order : orders) {
			orderString = orderString + "> Order id: "
					+ order.getOrderId() + "    Customer who owns this order: " + order.getCustomerId() + "\n";
		}
		return orderString;
	}

	// UPDATE
	// curl localhost:8280/order/addArticleToOrder -d articleId=value -d
	// quantity=value -d orderId=value
	// -------------------------------------------------------------------------

	// TODO later change reponsebody String to int
	@Override
	@PostMapping(path = "/addArticleToOrder")
	public String addArticleToOrder(
		@RequestParam(name = "articleId") int articleId,
		@RequestParam(name = "quantity") int quantity,
		@RequestParam(name = "orderId") int orderId) {

		// Check if article exists
		int idOfArticle = -1;
		try {
			idOfArticle = articleRestConnectorRequester
					.checkArticleId(articleId);
		} catch (RuntimeException ex) {
			return ex.getClass().getSimpleName()
					+ ex.getMessage();
		}

		Order order = orderRepository.findById(orderId)
				.orElseThrow(
						() -> new OrderNotFoundException(
								orderId));

		OrderPosition orderPosition = new OrderPosition();
		orderPosition.setArticleId(idOfArticle);
		orderPosition.setArticleQuantity(quantity);

		order.addOrderPosition(orderPosition);

		orderRepository.save(order);

		return "Article with id '" + orderId
				+ "' added to order.\n";
	}

	// DELETE
	// curl localhost:8280/order/deleteOrder -d orderId=value
	// -------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/deleteOrder")
	public String deleteOrder(
		@RequestParam int orderId) {
		orderRepository.deleteById(orderId);
		return "order deleted with id: " + orderId;
	}

	// Demo method for shop microservice
	// curl localhost:8280/order/
	// -------------------------------------------------------------------------

	@Override
	@GetMapping(path = "/")
	public String demoHelloSpring() {
		return "Hello Spring World from order microservice.";
	}

}
