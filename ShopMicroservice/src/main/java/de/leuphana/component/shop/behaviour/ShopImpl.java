package de.leuphana.component.shop.behaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.leuphana.component.shop.structure.Article;
import de.leuphana.component.shop.structure.Cart;
import de.leuphana.component.shop.structure.CartItem;
import de.leuphana.component.shop.structure.Catalog;
import de.leuphana.component.shop.structure.Customer;
import de.leuphana.component.shop.structure.Order;
import de.leuphana.component.shop.structure.OrderPosition;

public class ShopImpl implements ConsumerShopService, ProducerShopService {
	private Catalog catalog;
	private Map<Integer, Customer> customers;

	public ShopImpl() {
		customers = new HashMap<Integer, Customer>();
	}

	@Override
	public Integer createCustomerWithCart() {
		Cart cart = new Cart();

		Customer customer = new Customer(cart);

		customers.put(customer.getCustomerId(), customer);

		return customer.getCustomerId();
	}

	@Override
	public Article getArticle(int articleId) {
		// Delegation
		return catalog.getArticle(articleId);
	}

	@Override
	public Set<Article> getArticles() {
		return catalog.getArticles();
	}

	@Override
	public void removeArticleFromCart(Integer customerId, int articleId) {
		// Delegation
		Cart cart = customers.get(customerId).getCart();

		cart.deleteCartItem(articleId);
	}

	@Override
	public void addArticleToCart(Integer customerId, Integer articleId) {
		Article foundArticle = getArticle(articleId);

		Cart cart = customers.get(customerId).getCart();

		cart.addCartItem(foundArticle);
	}

	@Override
	public void decrementArticleQuantityInCart(Integer customerId,
			Integer articleId) {
		Cart cart = customers.get(customerId).getCart();

		cart.decrementArticleQuantity(articleId);
	}

	@Override
	public Order checkOutCart(int customerId) {

		Customer customer = customers.get(customerId);
		Cart cart = customer.getCart();

		Order order = new Order();
		order.setOrderId(1);

		int i = 1;

		for (CartItem cartItem : cart.getCartItems()) {
			OrderPosition orderPosition = new OrderPosition();
			orderPosition.setPositionId(i++);
//			orderPosition.setArticleId(cartItem.getArticle().getArticleId());
			orderPosition.setArticleQuantity(cartItem.getQuantity());
//			order.addOrderPosition(orderPosition);
		}

		customer.addOrder(order);

		return order;
	}

	@Override
	public Cart getCartForCustomer(Integer customerId) {
		return customers.get(customerId).getCart();
	}

	@Override
	public void addNewArticle(Article article) {
		// transiente Speicher
//		catalog.addArticle(article);
		
		// persistente Speicher
//		articleJDBCConnector.createArticle(article);
	}

}