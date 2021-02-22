package de.leuphana.component.shop.behaviour;

import java.util.Set;

import de.leuphana.component.shop.structure.Article;
import de.leuphana.component.shop.structure.Cart;
import de.leuphana.component.shop.structure.Order;

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