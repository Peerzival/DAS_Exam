package de.leuphana.component.behaviour.exception;

public class ArticleNotFoundException extends RuntimeException{

	public ArticleNotFoundException(int articleId) {
		super ("Could not find article with id '" + articleId +"'.");
	}
	
	public ArticleNotFoundException(String name) {
		super ("Could not find article with name '" + name + "'.");
	}
}
