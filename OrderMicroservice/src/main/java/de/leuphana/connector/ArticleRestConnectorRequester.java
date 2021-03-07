package de.leuphana.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import de.leuphana.component.structure.Article;

@FeignClient(contextId = "articleRestConnectorRequester", name = "article-service")
public interface ArticleRestConnectorRequester {

	@GetMapping(path = "/article-maincontroller/getArticleById/{articleId}")
	@ResponseBody Article getArticleById(@PathVariable("articleId") String articleId);

}
