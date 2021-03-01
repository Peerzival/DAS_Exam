package de.leuphana.component.order.structure;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.leuphana.component.structure.Article;

@Entity
@Table(name = "DB_ORDERPOSITION")
public class OrderPosition {

	private Integer positionId;
	private int articleQuantity;

//	@ManyToOne				//vielleicht entkommentieren, wenn name = "positionId", referencedColumnName = "orderId" 
//							in Joincolumn hinzugefügt wird
//	private Order order; 

	private Article article;

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getPositionId() {
		return positionId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getArticleQuantity() {
		return articleQuantity;
	}

	public void setArticleQuantity(int articleQuantity) {
		this.articleQuantity = articleQuantity;
	}

}