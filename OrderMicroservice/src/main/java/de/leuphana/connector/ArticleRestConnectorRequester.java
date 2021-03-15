package de.leuphana.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "article", url = "localhost:8180/article")
public interface ArticleRestConnectorRequester {

	@GetMapping(path = "/checkArticleId/{articleId}")
	int checkArticleId(@PathVariable(name="articleId") int articleId);
	
}
