package de.leuphana.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.ArticleRepository;
import de.leuphana.component.structure.Cart;
import de.leuphana.component.structure.Customer;

@RestController
public class MainController {

	@Autowired
	private ArticleRepository customerRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody int addNewCustomer(@RequestParam String name, @RequestParam String address,
			@RequestParam Cart cart) {

		Customer customer = new Customer(name, address, cart);
		customerRepository.save(customer);

		// Check persistence
		customer = customerRepository.findById((int) customer.getCustomerId());

		return customer.getCustomerId();
	}
	@RequestMapping(path = "/demo")
	public String home() {
		return "Henrik Pr��, Andeas B�chler und Max sind die geilsten hier";
	}
	

	@PostMapping(path = "/getcustomer/{customerId}")
	public @ResponseBody Customer getCustomer(@RequestParam int customerId) {
		return customerRepository.findById(customerId);
	}

	@GetMapping(path = "/getAllCustomers")
	public @ResponseBody Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	@DeleteMapping("/deleteCustomer/{customerId}")
	public @ResponseBody String deleteOrder(@PathVariable int customerId) {
		customerRepository.deleteById(customerId);
		return "Deleted\n";
	}


}
