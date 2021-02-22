package de.leuphana.connector.mapper;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import de.leuphana.component.order.structure.Order;
import de.leuphana.connector.entity.OrderEntity;

public class OrderMapper {
	// Dozer (JavaBean-Mapper)
	private static Mapper mapper;
	
	static {
		mapper = DozerBeanMapperSingletonWrapper.getInstance();
	}
	
	public static Order mapOrderEntityToOrder(OrderEntity orderEntity) {
		return mapper.map(orderEntity, Order.class);
	}
	
	public static OrderEntity mapOrderToOrderEntity(Order order) {
		return mapper.map(order, OrderEntity.class);
	}
	
	
	
	
	
	
	
	

}
