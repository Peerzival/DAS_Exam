package de.leuphana.component.structure.exception;

public class OrderNotFoundException extends RuntimeException{

	public OrderNotFoundException(int orderId) {
		super ("Could not find order " + orderId);
	}
}
