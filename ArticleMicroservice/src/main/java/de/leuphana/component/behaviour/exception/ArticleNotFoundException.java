package de.leuphana.component.behaviour.exception;

public class ArticleNotFoundException extends RuntimeException{

	public ArticleNotFoundException(int articleId) {
		super ("Could not find article " + articleId);
	}
}
