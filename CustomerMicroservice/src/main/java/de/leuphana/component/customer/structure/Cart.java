package de.leuphana.component.customer.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.leuphana.component.article.structure.Article;

public class Cart {

	private Map<Integer, CartItem> cartItems;
	private int numberOfArticles;

	public Cart() {
		cartItems = new HashMap<Integer, CartItem>();
		numberOfArticles = 0;
	}

	public void addCartItem(Article article) {
		Integer articleId = article.getArticleId();
		CartItem cartItem;
		if (cartItems.containsKey(articleId)) {
			cartItem = cartItems.get(articleId);
			cartItem.incrementQuantity();
		} else {
			cartItem = new CartItem(article);
			cartItems.put(articleId, cartItem);
		}
		numberOfArticles++;
	}

	public void deleteCartItem(int articleId) {
		for (CartItem cartItem : cartItems.values()) {
			if (cartItem.getArticle().getArticleId() == (articleId)) {
				cartItems.remove(cartItem.getCartItemId());
				break;
			}
		}
	}

	public void decrementArticleQuantity(Integer articleId) {
		if (cartItems.containsKey(articleId)) {
			CartItem cartItem = (CartItem) cartItems.get(articleId);
			cartItem.decrementQuantity();

			if (cartItem.getQuantity() <= 0)
				cartItems.remove(articleId);

			numberOfArticles--;
		}
	}

	public Collection<CartItem> getCartItems() {
		return cartItems.values();
	}

	public int getNumberOfArticles() {
		return numberOfArticles;
	}

	public double getTotalPrice() {
		double totalPrice = 0.0;

		Article article;
		for (CartItem cartItem : getCartItems()) {
			article = cartItem.getArticle();

			totalPrice += cartItem.getQuantity() * article.getPrice();
		}

		return totalPrice;
	}

}