package de.leuphana.component.structure;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DB_CART")
public class Cart {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	int cartId;

	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
//	@ElementCollection
	private Map<Integer, CartItem> cartItems;

	private int numberOfArticles;

	public Cart() {
		cartItems = new HashMap<Integer, CartItem>();

		numberOfArticles = 0;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public void addCartItem(int articleId) {
		CartItem cartItem;
		if (cartItems.containsKey(articleId)) {
			cartItem = cartItems.get(articleId);
			cartItem.incrementQuantity();
		} else {
			cartItem = new CartItem();
			cartItem.setArticleId(articleId);
			cartItems.put(articleId, cartItem);
		}
		numberOfArticles++;
	}

	public void deleteCartItem(int articleId) {
		for (CartItem cartItem : cartItems.values()) {
			if (cartItem.getArticleId() == (articleId)) {
				cartItems.remove(articleId);
				break;
			}
		}
	}

	public void decrementArticleQuantity(int articleId) {
		if (cartItems.containsKey(articleId)) {
			CartItem cartItem = (CartItem) cartItems.get(articleId);
			cartItem.decrementQuantity();

			if (cartItem.getQuantity() <= 0)
				cartItems.remove(articleId);

			numberOfArticles--;
		}
	}

	public Map<Integer, CartItem> getCartItems() {
		return cartItems;
	}

	public int getNumberOfArticles() {
		return numberOfArticles;
	}

//	public double getTotalPrice() {
//		double totalPrice = 0.0;
//
//		Article article;
//		for (CartItem cartItem : getCartItems()) {
//			article = cartItem.getArticle();
//
//			totalPrice += cartItem.getQuantity() * article.getPrice();
//		}
//
//		return totalPrice;
//	}

}