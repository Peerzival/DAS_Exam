package de.leuphana.component.customer.structure;

import de.leuphana.connector.Article;

public class CartItem {

	private int cartItemId;
	private Article article;
	private int quantity;

	public CartItem(Article article) {
		this.article = article;
		quantity = 1;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public Article getArticle() {
		return article;
	}

	public int getQuantity() {
		return quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}
	
	public void decrementQuantity() {
		quantity--;
	}

}