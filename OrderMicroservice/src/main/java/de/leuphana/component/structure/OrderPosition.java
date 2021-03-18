package de.leuphana.component.structure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DB_ORDERPOSITION")
public class OrderPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderPositionId;
	private int articleId;
	private int articleQuantity;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public void setOrderPositionId(int orderPositonId) {
		this.orderPositionId = orderPositonId;
	}

	public int getOrderPositionId() {
		return orderPositionId;
	}

	public int getArticleQuantity() {
		return articleQuantity;
	}

	public void setArticleQuantity(int articleQuantity) {
		this.articleQuantity = articleQuantity;
	}

}