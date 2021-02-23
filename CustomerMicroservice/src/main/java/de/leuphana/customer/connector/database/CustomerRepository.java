package de.leuphana.customer.connector.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.leuphana.customer.component.structure.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	
	Customer findById(int customerId);
	List<Customer> findByName(String name);

	
}
