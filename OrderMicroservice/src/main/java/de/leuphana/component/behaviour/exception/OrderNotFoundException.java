package de.leuphana.component.behaviour.exception;

public class OrderNotFoundException extends RuntimeException{

	public OrderNotFoundException(int orderId) {
		super ("Could not find order " + orderId);
	}
}
