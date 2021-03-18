package de.leuphana.component.behaviour.exception;

public class CustomerNotFoundException extends RuntimeException {
	
	public CustomerNotFoundException(int customerId) {
		super("Could not find customer with id'" + customerId + "'");
	}

}
