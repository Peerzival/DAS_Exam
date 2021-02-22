package de.leuphana.component.shop.structure;

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