package de.leuphana.component.structure;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "DB_CARTITEM")
@EntityScan("de.leuphana.component.structure.Article")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartItemId;

	@OneToOne(cascade = CascadeType.ALL, targetEntity = de.leuphana.component.structure.Article.class)
	@JoinColumn(name = "articleId")
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