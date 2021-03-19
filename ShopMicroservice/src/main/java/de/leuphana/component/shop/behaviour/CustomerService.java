package de.leuphana.component.shop.behaviour;

public interface CustomerService {

	// -------------------- CUSTOMER -------------------- \\
	// --------------------------------------------------------------------------------

	void createCustomer(String name, String address);

	void getCustomerString(int customerId);

	void getAllCustomersAsString();

	void changeCustomerAddress(int customerId, String address);

	void changeCustomerName(int customerId, String name);

	void deleteCustomer(int customerId);

	void addCartItem(int customerId, int articleId);

	void checkOutCartToOrder(int customerId);

	void getCartItemsFromCustomer(int customerId);

	void decrementArticleFromCartItem(int customerId, int articleId);

	void deleteArticleFromCartItem(int customerId, int articleId);

	// -------------------- ORDER -------------------- \\
	// --------------------------------------------------------------------------------

	void getOrder(int orderId);
	
	void getAllOrdersAsString();
	
	void deleteOrder(int orderId);

	
}