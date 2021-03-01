package de.leuphana.component.shop.behaviour;

import java.util.Set;

import de.leuphana.component.customer.structure.Cart;
import de.leuphana.component.order.structure.Order;
import de.leuphana.component.structure.Article;

public interface ConsumerShopService {

	Integer createCustomerWithCart();

	Article getArticle(int articleId);

	Set<Article> getArticles();

	void removeArticleFromCart(Integer customerId, int articleId);

	void addArticleToCart(Integer customerId, Integer articleId);

	void decrementArticleQuantityInCart(Integer customerId, Integer articleId);

	Order checkOutCart(int customerId);

	Cart getCartForCustomer(Integer customerId);

}