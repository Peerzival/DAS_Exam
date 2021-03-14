package de.leuphana.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.leuphana.component.structure.Article;

@FeignClient(name = "article", url = "localhost:8180/article")
public interface ArticleRestConnectorRequester {

	@GetMapping(path = "/getArticleById/{articleId}")
	Article getArticleById(@PathVariable(name="articleId") int articleId);

}
