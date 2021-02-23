package de.leuphana.connector.order.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(name="DB_ORDERPOSITION")
public class OrderPositionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer positionId;
	private int articleQuantity;
	
	// Association in JPA nicht m�glich �ber Id's !!! nur Referenzen erlaubt
//	private ArticleEntity article;

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
	
//	@OneToOne(fetch=FetchType.LAZY)
//	public ArticleEntity getArticle() {
//		return article;
//	}
//
//	public void setArticle(ArticleEntity article) {
//		this.article = article;
//	}

}