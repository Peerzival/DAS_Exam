package de.leuphana.component.order.structure;

import de.leuphana.component.article.structure.Article;

public class OrderPosition {

	private Integer positionId;
	
	private Article article;
	
	private int articleQuantity;

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getPositionId() {
		return positionId;
	}

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