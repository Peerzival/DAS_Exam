package de.leuphana.customer.component.structure;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import de.leuphana.component.article.structure.Article;

public class CartItem {

	private int cartItemId;
	
	@OneToOne(cascade = { CascadeType.ALL })
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