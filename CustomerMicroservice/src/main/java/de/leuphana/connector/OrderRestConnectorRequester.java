package de.leuphana.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "order", url = "http://192.168.178.121:8280/order")
public interface OrderRestConnectorRequester {
	
	@PostMapping(path = "/createOrder")
	@ResponseBody int createOrder(@RequestParam(name="customerId") int customerId);
	
	@PostMapping(path = "/addArticleToOrder")
	@ResponseBody String addArticleToOrder(
			@RequestParam(name="articleId") int articleId,
			@RequestParam(name="quantity") int quantity,
			@RequestParam(name="orderId") int orderId);
	
}
