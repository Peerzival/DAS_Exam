package de.leuphana.component.article.structure;

import de.leuphana.connector.Article;

public class CD extends Article {

	private String artist;

	public CD(int articleId) {
		this.setArticleId(articleId);
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}