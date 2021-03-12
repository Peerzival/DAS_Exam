package de.leuphana.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.leuphana.component.structure.Article;

@FeignClient("article-microservice")
public interface ArticleRestConnectorRequester {

//	@PostMapping(path = "/article-maincontroller/getArticleById/{articleId}")
	
	@GetMapping
	@PostMapping(path = "/getArticleById/{articleId}")
	@ResponseBody Article getArticleById(@RequestParam("articleId") int articleId);

}
