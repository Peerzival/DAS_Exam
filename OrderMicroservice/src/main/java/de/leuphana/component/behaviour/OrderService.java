package de.leuphana.component.behaviour;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.leuphana.component.structure.Order;
import de.leuphana.component.structure.OrderPosition;
import de.leuphana.configuration.OrderConfiguration;

@FeignClient(name = "orderService", configuration = OrderConfiguration.class)
public interface OrderService {

	@RequestMapping(method = RequestMethod.GET, path = "/getOrderById/{orderId}")
	Order getOrderById(@RequestParam("orderId") String orderId);
	
	@RequestMapping(method = RequestMethod.GET, path = "/getAllOrders")
	List<Order> getOrders();
	
	@RequestMapping(method = RequestMethod.GET, path = "/getAllOrderpositions")
	List<OrderPosition> getOrderPositions();
	
	@RequestMapping(method = RequestMethod.GET, path = "/getOrderPositionById/{positionId}")
	OrderPosition getOrderPositionById(@RequestParam("positionId") String positionId);
}
