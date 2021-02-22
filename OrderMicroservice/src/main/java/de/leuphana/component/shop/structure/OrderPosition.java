package de.leuphana.component.shop.structure;

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