package de.leuphana.connector;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static Logger log;

	@Autowired
	public OrderRestConnectorProvider(
			ArticleRestConnectorRequester articleRestConnectorRequester) {
		this.articleRestConnectorRequester = articleRestConnectorRequester;
		log = LogManager.getLogger(this.getClass());
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

		//For browser representation
		String orderHtmlString = "> Order id: "
				+ order.getOrderId() + "<br>";
		//For console representation
		String orderString = "> Order id: "
				+ order.getOrderId() + "\n";

		for (OrderPosition orderPosition : orderPositions) {
			orderHtmlString = orderHtmlString
					+ "Order position: "
					+ orderPosition
							.getOrderPositionId()
					+ "<br>Article id: "
					+ orderPosition.getArticleId()
					+ "<br>Article quantity: "
					+ orderPosition
							.getArticleQuantity()
					+ "<br>";

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
		
		log.info(orderString);

		return orderHtmlString;
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
		
		//For browser representation
		String orderHtmlString = new String();
		//For console representation
		String orderString = new String();

		for (Order order : orders) {
			orderHtmlString = orderHtmlString + "> Order id: "
					+ order.getOrderId()
					+ "<br>Customer who owns this order: "
					+ order.getCustomerId() + "<br>";
			
			orderString = orderString + "> Order id: "
					+ order.getOrderId()
					+ "    Customer who owns this order: "
					+ order.getCustomerId() + "\n";
		}
		return orderHtmlString;
	}

	// UPDATE
	// curl localhost:8280/order/addArticleToOrder -d articleId=value -d quantity=value -d orderId=value
	// -------------------------------------------------------------------------

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
		//For console representation
		log.info("Article with id " + idOfArticle
				+ " added to order.\n");
		//For browser representation
		return "Article with id " + idOfArticle
				+ " added to order.<br>";
	}

	// DELETE
	// curl localhost:8280/order/deleteOrder -d orderId=value
	// -------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/deleteOrder")
	public String deleteOrder(
		@RequestParam int orderId) {
		orderRepository.deleteById(orderId);
		//For console representation
		log.info("order deleted with id: " + orderId);
		//For browser representation
		return "order deleted with id: " + orderId;
	}

	// Demo method for shop microservice
	// curl localhost:8280/order/
	// -------------------------------------------------------------------------
	
	@GetMapping(path = "/")
	public String demo() {
		return "Hello from Order-MicroService.";
	}

}
