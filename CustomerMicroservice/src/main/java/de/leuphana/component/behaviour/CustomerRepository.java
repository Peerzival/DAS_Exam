package de.leuphana.component.behaviour;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.leuphana.component.structure.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

//	Customer findById(int customerId);
//	void delete(Customer customer);
	List<Customer> findByName(String name);

}
