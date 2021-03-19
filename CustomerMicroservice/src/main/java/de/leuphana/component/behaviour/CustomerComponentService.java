package de.leuphana.component.behaviour;

public interface CustomerComponentService {

	// curl -X POST -d "name=value&address=value" http://localhost:8281/customer/createCustomer
	int createCustomer(String name, String address);

	// curl -X POST -d "customerId=value" http://localhost:8281/customer/getCustomerString
	String getCustomerString(int customerId);

	// http://localhost:8281/customer/getAllCustomersAsString
	String getAllCustomersAsString();

	// curl -X POST -d "customerId=value&address=value" http://localhost:8281/customer/changeCustomerAddress
	String changeCustomerAddress(int customerId, String address);

	// curl -X POST -d "customerId=value&name=value"  http://localhost:8281/customer/changeCustomerName
	String changeCustomerName(int customerId, String name);

	// curl -X DELETE -d "customerId=value" http://localhost:8281/customer/deleteCustomer
	String deleteCustomer(int customerId);

	// curl -X POST -d "customerId=value&articleId=value" http://localhost:8281/customer/addCartItem
	String addCartItem(int customerId, int articleId);

	// curl localhost:8281/customer/checkOutCartToOrder -d customerId=1
	String checkOutCartToOrder(int customerId);

	// curl -X POST -d "customerId=value" http://localhost:8281/customer/getCartItemsFromCustomer
	String getCartItemsFromCustomer(int customerId);

	// curl -X POST -d "customerId=value&articleId=value" http://localhost:8281/customer/decrementArticleFromCartitem
	String decrementArticleFromCartItem(int customerId, int articleId);

	// curl -X POST -d "customerId=value&articleId=value" http://localhost:8281/customer/deleteArticleFromCartitem
	String deleteArticleFromCartItem(int customerId, int articleId);

}