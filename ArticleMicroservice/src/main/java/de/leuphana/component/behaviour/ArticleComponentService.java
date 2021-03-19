package de.leuphana.component.behaviour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.leuphana.component.structure.Article;

public interface ArticleComponentService {

	int createArticle(String name, String manufactor, float price);

	String updateArticle(int articleId, String name, String manufactor, float price);

	Article getArticleById(int articleId);

	Article getArticleByName(String name);

	String getArticleString(int articleId);

	Iterable<Article> getAllArticles();

	int checkArticleId(int articleId);

	String deleteArticleById(int articleId);

}