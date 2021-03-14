package de.leuphana.component.structure;

import javax.annotation.Generated;
import javax.persistence.Embeddable;

import org.hibernate.annotations.GenerationTime;

//@Entity
//@Table(name = "DB_ORDERPOSITION")
@Embeddable
public class OrderPosition {

	private int orderPositionId;
	private int articleId;
	private int articleQuantity;
	
	public OrderPosition() {}

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