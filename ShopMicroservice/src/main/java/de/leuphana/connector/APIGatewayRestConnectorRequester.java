package de.leuphana.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.leuphana.component.shop.behaviour.CustomerService;
import de.leuphana.component.shop.behaviour.SupplierService;

@FeignClient(name = "gateway-service", url = "localhost:8880")
public interface APIGatewayRestConnectorRequester
		extends SupplierService {

	// -------------------- ARTICLE -------------------- \\
	// --------------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/article/addArticle")
	void addNewArticle(@RequestParam("name") String name,
		@RequestParam("manufactor") String manufactor,
		@RequestParam("price") float price);

	@Override
	@PostMapping(path = "/article/updateArticle")
	String updateArticle(int articleId,
		String name,
		String manufactor,
		float price);

	// -------------------- CUSTOMER -------------------- \\
	// --------------------------------------------------------------------------------

//	@Override
//	@PostMapping(path = "/customer/addNewCustomer")
//	String addNewCustomer(String name, String address);
//
//	@Override
//	@PostMapping(path = "/customer/getCustomerString")
//	String getCustomerString(int customerId);
//
//	@Override
//	@GetMapping(path = "/customer/getAllCustomersAsString")
//	String getAllCustomersAsString();
//
//	@Override
//	@PostMapping(path = "/customer/changeCustomerAddress")
//	String changeCustomerAddress(int customerId, String address);
//
//	@Override
//	@PostMapping(path = "/customer/changeCustomerName")
//	String changeCustomerName(int customerId, String name);
//
//	@Override
//	@PostMapping(path = "/customer/deleteCustomer")
//	String deleteCustomer(int customerId);
//
//	@Override
//	@PostMapping(path = "/customer/addCartItem")
//	String addCartItem(int customerId, int articleId);
//
//	@Override
//	@PostMapping(path = "/customer/checkOutCartToOrder")
//	String checkOutCartToOrder(int customerId);
//
//	@Override
//	@PostMapping(path = "/customer/getCartItemsFromCustomer")
//	String getCartItemsFromCustomer(int customerId);
//
//	@Override
//	@PostMapping(path = "/customer/delecrementArticleFromCartitem")
//	String delecrementArticleFromCartitem(int customerId,
//		int articleId);
//
//	@Override
//	@PostMapping(path = "/customer/deleteArticleFromCartitem")
//	String deleteArticleFromCartitem(int customerId,
//		int articleId);

	// -------------------- ORDER -------------------- \\
	// --------------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/order/getOrderString")
	String getOrderString(int orderId);

	@Override
	@GetMapping(path = "/order/getAllOrdersAsString")
	String getAllOrdersAsString();

//	@Override
//	@PostMapping(path = "/order/deleteOrder")
//	String deleteOrder(int orderId);
}
