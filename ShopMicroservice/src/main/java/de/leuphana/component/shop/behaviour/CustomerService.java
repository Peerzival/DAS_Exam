package de.leuphana.component.shop.behaviour;

import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerService {

	// -------------------- CUSTOMER -------------------- \\
	// --------------------------------------------------------------------------------

	String addNewCustomer(@RequestParam String name,
		@RequestParam String address);

	String getCustomerString(@RequestParam int customerId);

	String getAllCustomersAsString();

	String changeCustomerAddress(@RequestParam int customerId,
		@RequestParam String address);

	String changeCustomerName(@RequestParam int customerId,
		@RequestParam String name);

	String deleteCustomer(@RequestParam int customerId);

	String addCartItem(@RequestParam int customerId,
		@RequestParam int articleId);

	String checkOutCartToOrder(@RequestParam int customerId);

	String getCartItemsFromCustomer(
		@RequestParam int customerId);

	String delecrementArticleFromCartitem(
		@RequestParam int customerId,
		@RequestParam int articleId);

	String deleteArticleFromCartitem(
		@RequestParam int customerId,
		@RequestParam int articleId);

	// -------------------- ORDER -------------------- \\
	// --------------------------------------------------------------------------------

	String getOrderString(@RequestParam int orderId);
	
	String getAllOrdersAsString();
	
	String deleteOrder(@RequestParam int orderId);

	
}