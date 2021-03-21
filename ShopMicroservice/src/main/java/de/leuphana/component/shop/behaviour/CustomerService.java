package de.leuphana.component.shop.behaviour;

public interface CustomerService {

	// -------------------- CUSTOMER -------------------- \\
	// --------------------------------------------------------------------------------

	String createCustomer(String name, String address);

	String getCustomerString(int customerId);

	String getAllCustomersAsString();

	String changeCustomerAddress(int customerId, String address);

	String changeCustomerName(int customerId, String name);

	String deleteCustomer(int customerId);

	String addCartItem(int customerId, int articleId);

	String checkOutCartToOrder(int customerId);

	String getCartItemsFromCustomer(int customerId);

	String decrementArticleFromCartItem(int customerId, int articleId);

	String deleteArticleFromCartItem(int customerId, int articleId);

	// -------------------- ORDER -------------------- \\
	// --------------------------------------------------------------------------------

	String getOrder(int orderId);
	
	String getAllOrders();
	
	String deleteOrder(int orderId);

	
}