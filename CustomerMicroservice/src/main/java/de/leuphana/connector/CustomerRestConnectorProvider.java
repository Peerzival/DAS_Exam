package de.leuphana.connector;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.CustomerRepository;
import de.leuphana.component.structure.Cart;
import de.leuphana.component.structure.CartItem;
import de.leuphana.component.structure.Customer;

@RestController
@RequestMapping(path = "/customer")
public class CustomerRestConnectorProvider {
	

	@Autowired
	private CustomerRepository customerRepository;
	
	// Feign
	private OrderRestConnectorRequester orderRestConnectorRequester;
	
	@Autowired
	public CustomerRestConnectorProvider(
			OrderRestConnectorRequester orderRestConnectorRequester) {
		
		this.orderRestConnectorRequester = orderRestConnectorRequester;
	}
	
	
	
	// CRUD OPERATIONS: CUSTOMER
	// -------------------------------------------------------------------------
	// CREATE 
	
	// curl -X POST -d "name=value&address=value" http://localhost:8281/customer/addcustomer
	@PostMapping(path = "/addcustomer") 
	public @ResponseBody String addNewCustomer(
			@RequestParam String name, 
			@RequestParam String address) {
		
		Customer customer = new Customer(name, address);
		customer = customerRepository.save(customer);	
		
		Cart cart = new Cart();
		cart.setCartId(customer.getCustomerId());
		customer.setCart(cart);
		customer = customerRepository.save(customer);
		
		return "new customer created with id: " + customer.getCustomerId();
	}
	
	/* createOrder(customerID)
	 * 
	 * int orderId = orderRepositry.createOrder(customerId)
	 * 
	 * foreach { CartItem cartItem: Customer) orderRepository.addArticleToOrder(...) }
	 * 
	 * 
	 */
	
	// -------------------------------------------------------------------------
	// READ
	
	// curl -X POST -d "customerId=value" http://localhost:8281/customer/getcustomer
	@PostMapping(path = "/getcustomer")
	public @ResponseBody Customer getCustomer(
			@RequestParam int customerId) {

		return customerRepository.findById(customerId);
	}
	
	// http://localhost:8281/customer/getallcustomers
	@GetMapping(path = "/getallcustomers")
	public @ResponseBody Iterable<Customer> getAllCustomers() {
		
		return customerRepository.findAll();
	}
	
	// -------------------------------------------------------------------------
	// UPDATE
	
	// curl -X POST -d "customerId=value&address=value" http://localhost:8281/customer/changecustomer/address
	@PostMapping(path = "/changecustomer/address")
	public @ResponseBody String changeCustomerAddress(
		@RequestParam int customerId,
		@RequestParam String address) {
		Customer customer = getCustomer(customerId);
		customer.setAddress(address);
		customerRepository.save(customer);
		return "changed address of selected customer to: "
				+ address;
	}
	
	// curl -X POST -d "customerId=value&name=value" http://localhost:8281/customer/changecustomer/name
	@PostMapping(path = "/changecustomer/name")
	public @ResponseBody String changeCustomerName(
		@RequestParam int customerId,
		@RequestParam String name) {
		Customer customer = getCustomer(customerId);
		customer.setName(name);
		customerRepository.save(customer);
		return "changed name of selected customer to: " + name;
	}

	// -------------------------------------------------------------------------
	// DELETE
	
	// curl -X DELETE -d "customerId=value" http://localhost:8281/customer/deletecustomer
	@DeleteMapping("/deletecustomer")
	public @ResponseBody String deleteCustomer(
		@RequestParam int customerId) {
		Customer customer = customerRepository
				.findById(customerId);
		customerRepository.delete(customer);
		return "customer deleted with id: " + customerId;
	}
	
	// -------------------------------------------------------------------------
	// END OF CRUD OPERATIONS: CUSTOMER
	// -------------------------------------------------------------------------

	
	// CRUD OPERATIONS: CART ITEM
	// -------------------------------------------------------------------------
	// CREATE	
	
	// curl -X POST -d "customerId=value&articleId=value" http://localhost:8281/customer/addcartitem
		@PostMapping(path = "/addcartitem") 
		public @ResponseBody String addCartItem(
				@RequestParam int customerId, 
				@RequestParam int articleId) {
			
			Customer customer = customerRepository.findById(customerId);
			customer.getCart().addCartItem(articleId);
			customerRepository.save(customer);
			
			return "new cartitem created with id: " + customer.getCustomerId();
		}
		
	// curl localhost:8281/customer/checkoutCartToOrder -d customerId=1
		@PostMapping(path="/checkoutCartToOrder")
		public @ResponseBody String checkOutCartToOrder(@RequestParam int customerId) {
			
			int orderId = orderRestConnectorRequester.createOrder(customerId);
			
			Customer customer = customerRepository.findById(customerId);
			Cart cart = customer.getCart();
			
			for (CartItem cartItem : cart.getCartItems()) {
				orderRestConnectorRequester
					.addArticleToOrder(cartItem.getArticleId(),
							cartItem.getQuantity(),
							orderId);
			}
			
			return "Cart with id '" + cart.getCartId() + "' checked out " +
				"into order with id '" + orderId + "'.\n";
		}
	
	// -------------------------------------------------------------------------
	// READ
	
	// curl -X POST -d "customerId=value" http://localhost:8281/customer/getcartitems
	@PostMapping(path="/getcartitems")
	public @ResponseBody String getCartItemsFromCustomer(
			@RequestParam int customerId) {
		
		String cartItemsInCustomer = "";
		
		Customer customer = getCustomer(customerId);
		Collection<CartItem> cartItems = customer.getCart().getCartItems();
		
		for (CartItem cartItem : cartItems) {
			cartItemsInCustomer = cartItemsInCustomer + 
					"articleId: " + cartItem.getArticleId() + ", " +
					"quantity: " + cartItem.getQuantity() + "\n";
		}
		
		return "cartitems:\n" + cartItemsInCustomer;
	}

	// curl -X POST -d "customerId=value&articleId=value" http://localhost:8281/customer/decrementcartitem
	@PostMapping(path="/decrementcartitem")
	public @ResponseBody String delecrementArticleFromCartitem(
			@RequestParam int customerId,
			@RequestParam int articleId) {
		
		Customer customer = getCustomer(customerId);
		customer.getCart().decrementArticleQuantity(articleId);
		customerRepository.save(customer);
		
		return "cartitem:" + articleId + " decrement";
	}
	
	// -------------------------------------------------------------------------
	// DELETE
	
	// curl -X POST -d "customerId=value&articleId=value" http://localhost:8281/customer/deletecartitem
	@PostMapping(path="/deletecartitem")
	public @ResponseBody String deleteArticleFromCartitem(
			@RequestParam int customerId,
			@RequestParam int articleId) {
		Customer customer = getCustomer(customerId);
		customer.getCart().deleteCartItem(articleId);
		customerRepository.save(customer);
		
		return "cartitem:" + articleId + " deleted";
	}
	
	
	// -------------------------------------------------------------------------
	// END OF CRUD OPERATIONS: CART ITEM
	// -------------------------------------------------------------------------
	
	// -------------------------------------------------------------------------
	// DEMO METHOD: GET DUMMY CUSTOMERS FOR DATABASE
	
	// http://localhost:8281/customer/democustomers
	@RequestMapping(path = "/democustomers")
	public String dummys() {
		Customer customer1;
		Cart cart1 = new Cart();
		Customer customer2;
		Cart cart2 = new Cart();
		Customer customer3;
		Cart cart3 = new Cart();
		
		customer1 = customerRepository.save(new Customer(
				"Andreas Michael Baechler", 
				"Am Altenbruecker Ziegelhof 11"));
		cart1.setCartId(customer1.getCustomerId());
		customer1.setCart(cart1);
		customerRepository.save(customer1);
		
		
		customer2 = customerRepository.save(new Customer(
				"Max Gnewuch und Henrik Pruess", 
				"Feldstrasse 53"));
		cart2.setCartId(customer2.getCustomerId());
		customer2.setCart(cart2);
		customerRepository.save(customer2);
		
		customer3 = customerRepository.save(new Customer(
				"Levin Schnabel", 
				"Am Sande"));
		cart3.setCartId(customer3.getCustomerId());
		customer3.setCart(cart3);
		customerRepository.save(customer3);
		
		return "Demo Customers Saved";
	}
}
