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
public class MainController {

	@Autowired
	private CustomerRepository customerRepository;
	
	// CRUD OPERATIONS: CUSTOMER
	// -------------------------------------------------------------------------
	// CREATE
	
	// curl -X POST -d "name=value&address=value" http://localhost:8281/customer/addcustomer
	@PostMapping(path = "/addcustomer") 
	public @ResponseBody String addNewCustomer(
			@RequestParam String name, 
			@RequestParam String address) {
		
		Cart cart = new Cart();
		Customer customer = new Customer(name, address, cart);
		customer = customerRepository.save(customer);	
		return "new customer created with id: " + customer.getCustomerId();
	}
	
	// -------------------------------------------------------------------------
	// READ
	
	// curl -X POST -d "customerId=value" http://localhost:8281/customer/getCustomer
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

	// curl -X POST -d "name=value&address=value"
	// http://localhost:8281/customer/addcustomer
	@PostMapping(path = "/addcustomer")
	public @ResponseBody String addNewCustomer(
		@RequestParam String name,
		@RequestParam String address) {
		Cart cart = new Cart();
		Customer customer = new Customer(name, address, cart);
		customer = customerRepository.save(customer);
		return "new customer created with id: "
				+ customer.getCustomerId();
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

	// http://localhost:8281/customer/getcustomer/
	@GetMapping(path = "/getcustomer/{customerId}")
	public @ResponseBody Customer getCustomer(
		@PathVariable(value = "customerId") int customerId) {
		return customerRepository.findById(customerId);
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
	// END OF CRUD OPERATIONS: CART ITEM
	// -------------------------------------------------------------------------
	
	// -------------------------------------------------------------------------
	// DEMO METHOD: GET DUMMY CUSTOMERS FOR DATABASE
	
	// http://localhost:8281/customer/democustomers
	@RequestMapping(path = "/democustomers")
	public String dummys() {
		
		customerRepository.save(new Customer(
				"Andreas Michael Baechler", 
				"Am Altenbruecker Ziegelhof 11",
				new Cart()));
		
		customerRepository.save(new Customer(
				"Max Gnewuch und Henrik Pruess", 
				"Feldstrasse 53", 
				new Cart()));
		
		customerRepository.save(new Customer(
				"Levin Schnabel", 
				"Am Sande", 
				new Cart()));
		
		return "Demo Customers Saved";
	}
}
