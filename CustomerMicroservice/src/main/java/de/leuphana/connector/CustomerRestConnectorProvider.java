package de.leuphana.connector;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.CustomerComponentService;
import de.leuphana.component.behaviour.CustomerRepository;
import de.leuphana.component.behaviour.exception.CustomerNotFoundException;
import de.leuphana.component.structure.Cart;
import de.leuphana.component.structure.CartItem;
import de.leuphana.component.structure.Customer;

@RestController
@RequestMapping(path = "/customer")
public class CustomerRestConnectorProvider implements CustomerComponentService {

	@Autowired
	private CustomerRepository customerRepository;

	// Feign
	final OrderRestConnectorRequester orderRestConnectorRequester;

	@Autowired
	public CustomerRestConnectorProvider(OrderRestConnectorRequester orderRestConnectorRequester) {

		this.orderRestConnectorRequester = orderRestConnectorRequester;
	}

	// CRUD OPERATIONS: CUSTOMER
	// -------------------------------------------------------------------------
	// CREATE

	// curl -X POST -d "name=value&address=value"
	// http://localhost:8281/customer/createCustomer
	@Override
	@PostMapping(path = "/createCustomer")
	public int createCustomer(@RequestParam String name, @RequestParam String address) {

		Customer customer = new Customer(name, address);
		customer = customerRepository.save(customer);

		Cart cart = new Cart();
		cart.setCartId(customer.getCustomerId());
		customer.setCart(cart);
		customer = customerRepository.save(customer);

		return customer.getCustomerId().intValue();
	}

	// -------------------------------------------------------------------------
	// READ

	// curl -X POST -d "customerId=value" http://localhost:8281/customer/getCustomer
	@PostMapping(path = "/getCustomer")
	public Customer getCustomer(@RequestParam int customerId) {

		return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
	}

	// curl -X POST -d "customerId=value"
	// http://localhost:8281/customer/getCustomerString
	@Override
	@PostMapping(path = "/getCustomerString")
	public String getCustomerString(@RequestParam int customerId) {

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException(customerId));

		return "> CustomerId: " + customer.getCustomerId() + "\n    Customername: " + customer.getName()
				+ "\n    Customeraddress: " + customer.getAddress() + "\n";
	}

	// http://localhost:8281/customer/getAllCustomers
	@GetMapping(path = "/getAllCustomers")
	public Iterable<Customer> getAllCustomers() {

		return customerRepository.findAll();
	}

	// http://localhost:8281/customer/getAllCustomersAsString
	@Override
	@GetMapping(path = "/getAllCustomersAsString")
	public String getAllCustomersAsString() {
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		String s = new String();
		for (Customer customer : customers) {
			s = s + "> CustomerId: " + customer.getCustomerId() + "\n    Customername: " + customer.getName()
					+ "\n    Customeraddress: " + customer.getAddress() + "\n";
		}
		return s;
	}

	// -------------------------------------------------------------------------
	// UPDATE

	// curl -X POST -d "customerId=value&address=value"
	// http://localhost:8281/customer/changeCustomerAddress
	@Override
	@PostMapping(path = "/changeCustomerAddress")
	public String changeCustomerAddress(@RequestParam int customerId, @RequestParam String address) {

		Customer customer = getCustomer(customerId);

		String oldAddress = customer.getAddress();
		customer.setAddress(address);
		customerRepository.save(customer);

		return " > CustomerId: " + customer.getCustomerId() + "\n    Old Address: " + oldAddress + "\n    New Address: "
				+ customer.getAddress();
	}

	// curl -X POST -d "customerId=value&name=value"
	// http://localhost:8281/customer/changeCustomerName
	@Override
	@PostMapping(path = "/changeCustomerName")
	public String changeCustomerName(@RequestParam int customerId, @RequestParam String name) {

		Customer customer = getCustomer(customerId);

		String oldName = customer.getName();
		customer.setName(name);
		customerRepository.save(customer);

		return " > CustomerId: " + customer.getCustomerId() + "\n    Old Name: " + oldName + "\n    New Name: "
				+ customer.getName();
	}

	// -------------------------------------------------------------------------
	// DELETE

	// curl -X DELETE -d "customerId=value"
	// http://localhost:8281/customer/deleteCustomer
	@Override
	@DeleteMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam int customerId) {

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException(customerId));
		customerRepository.delete(customer);

		return " > CustomerId: " + customer.getCustomerId() + ", was successfully deleted.";
	}

	// -------------------------------------------------------------------------
	// END OF CRUD OPERATIONS: CUSTOMER
	// -------------------------------------------------------------------------

	// CRUD OPERATIONS: CART ITEM
	// -------------------------------------------------------------------------
	// CREATE

	// curl -X POST -d "customerId=value&articleId=value"
	// http://localhost:8281/customer/addCartItem
	@Override
	@PostMapping(path = "/addCartItem")
	public String addCartItem(@RequestParam int customerId, @RequestParam int articleId) {

		Customer customer = getCustomer(customerId);

		customer.getCart().addCartItem(articleId);
		customerRepository.save(customer);

		return " > CustomerId: " + customer.getCustomerId() + "\n    CartItem: " + articleId + ", was added";
	}

	// curl localhost:8281/customer/checkOutCartToOrder -d customerId=1
	@Override
	@PostMapping(path = "/checkOutCartToOrder")
	public String checkOutCartToOrder(@RequestParam int customerId) {

		Customer customer = getCustomer(customerId);

		Collection<CartItem> cars = customer.getCart().getCartItems().values();
		int orderId = orderRestConnectorRequester.createOrder(customerId);

		for (CartItem cartItem : cars) {

			orderRestConnectorRequester.addArticleToOrder(cartItem.getArticleId(), cartItem.getQuantity(), orderId);

		}
		Cart cart = new Cart();
		cart.setCartId(customer.getCustomerId());
		customer.setCart(cart);
		customer.setCart(cart);
		customerRepository.save(customer);
	//	deleteArticleFromCartItem(customerId, cartItem.getArticleId());

		return " > CustomerId: " + customer.getCustomerId() + "\n    CartId: " + customer.getCart().getCartId()
				+ " was checked out into order" + "\n    OrderId: " + orderId;

	}

	// -------------------------------------------------------------------------
	// READ

	// curl -X POST -d "customerId=value"
	// http://localhost:8281/customer/getCartItemsFromCustomer
	@Override
	@PostMapping(path = "/getCartItemsFromCustomer")
	public String getCartItemsFromCustomer(@RequestParam int customerId) {

		String cartItemsInCustomer = "";

		Customer customer = getCustomer(customerId);
		Collection<CartItem> cartItems = customer.getCart().getCartItems().values();
		
		for (CartItem cartItem : cartItems) {
			cartItemsInCustomer = cartItemsInCustomer + "\n    ArticleId: " + cartItem.getArticleId()
					+ "\n    Article quantity: " + cartItem.getQuantity();
		}

		return " > CustomerId: " + customer.getCustomerId() + "\n    CartId: " + customer.getCart().getCartId()
				+ cartItemsInCustomer;
	}

	// curl -X POST -d "customerId=value&articleId=value"
	// http://localhost:8281/customer/decrementArticleFromCartItem
	@Override
	@PostMapping(path = "/decrementArticleFromCartItem")
	public String decrementArticleFromCartItem(@RequestParam int customerId, @RequestParam int articleId) {

		Customer customer = getCustomer(customerId);

		customer.getCart().decrementArticleQuantity(articleId);
		customer = customerRepository.save(customer);

		return " > CustomerId: " + customer.getCustomerId() + "\n    ArticleId: " + articleId + " decremented by 1";

	}

	// -------------------------------------------------------------------------
	// DELETE

	// curl -X POST -d "customerId=value&articleId=value"
	// http://localhost:8281/customer/deleteArticleFromCartitem
	@Override
	@PostMapping(path = "/deleteArticleFromCartitem")
	public String deleteArticleFromCartItem(@RequestParam int customerId, @RequestParam int articleId) {

		Customer customer = getCustomer(customerId);
		customer.getCart().deleteCartItem(articleId);
		customerRepository.save(customer);

		return " > CustomerId: " + customer.getCustomerId() + "\n    ArticleId: " + articleId + " deleted";
	}

	// -------------------------------------------------------------------------
	// END OF CRUD OPERATIONS: CART ITEM
	// -------------------------------------------------------------------------

	// -------------------------------------------------------------------------
	// DEMO METHOD: GET DUMMY CUSTOMERS FOR DATABASE

	// http://localhost:8281/customer/demoCustomers
	@RequestMapping(path = "/demoCustomers")
	public String demoCustomers() {
		Customer customer1;
		Cart cart1 = new Cart();
		Customer customer2;
		Cart cart2 = new Cart();
		Customer customer3;
		Cart cart3 = new Cart();

		customer1 = customerRepository.save(new Customer("Andreas Michael Baechler", "Am Altenbruecker Ziegelhof 11"));
		cart1.setCartId(customer1.getCustomerId());
		customer1.setCart(cart1);
		customerRepository.save(customer1);

		customer2 = customerRepository.save(new Customer("Max Gnewuch", "Feldstrasse 53a"));
		cart2.setCartId(customer2.getCustomerId());
		customer2.setCart(cart2);
		customerRepository.save(customer2);

		customer3 = customerRepository.save(new Customer("Henrik Pruess", "Feldstrasse 53b"));
		cart3.setCartId(customer3.getCustomerId());
		customer3.setCart(cart3);
		customerRepository.save(customer3);

		return "Demo Customers Saved";
	}

	// http://localhost:8281/customer/
	@GetMapping(path = "/")
	public String demo() {
		return "Hello from CustomerMicroservice";
	}
}
