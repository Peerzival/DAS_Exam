package de.leuphana.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.leuphana.component.behaviour.CustomerRepository;
import de.leuphana.component.structure.Cart;
import de.leuphana.component.structure.Customer;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody int addNewCustomer(@RequestParam String name, @RequestParam String address,
			@RequestParam Cart cart) {

		Customer customer = new Customer(name, address, cart);
		customerRepository.save(customer);

		// Check persistence
		customer = customerRepository.findById((int) customer.getCustomerId());

		return customer.getCustomerId();
	}

	@PostMapping(path = "/get")
	public @ResponseBody Customer getCustomer(@RequestParam int customerId) {
		return customerRepository.findById(customerId);
	}
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

}
