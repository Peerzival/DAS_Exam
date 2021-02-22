package de.leuphana.connector.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
//@Table(name="DB_ORDERPOSITION")
public class OrderPositionEntity {

	private Integer positionId;
	private int articleQuantity;
	
	// Association in JPA nicht möglich über Id's !!! nur Referenzen erlaubt
	private ArticleEntity article;

	@Id
	@GeneratedValue
	public Integer getPositionId() {
		return positionId;
	}
	
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public int getArticleQuantity() {
		return articleQuantity;
	}

	public void setArticleQuantity(int articleQuantity) {
		this.articleQuantity = articleQuantity;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	public ArticleEntity getArticle() {
		return article;
	}

	public void setArticle(ArticleEntity article) {
		this.article = article;
	}

}