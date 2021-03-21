package de.leuphana.component.structure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DB_CARTITEM")
//@EntityScan("de.leuphana.component.structure.Article")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartItemId;
	private int articleId;

	@Column(name="ArticleQuantity")
	private int quantity;

	public CartItem() {
		quantity = 1;
	}

	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
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