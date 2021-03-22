package de.leuphana.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "article", url = "http://192.168.178.121:8180/article")
public interface ArticleRestConnectorRequester {

	@PostMapping(path = "/checkArticleId")
	int checkArticleId(@RequestParam(name="articleId") int articleId);
	
}
