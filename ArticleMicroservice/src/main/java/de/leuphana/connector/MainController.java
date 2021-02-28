package de.leuphana.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.leuphana.component.article.behaviour.ArticleRepository;
import de.leuphana.component.article.structure.Article;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

	@Autowired
	private ArticleRepository articleRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody int addNewArticle(@RequestParam String name, @RequestParam String manufactor,
			@RequestParam float price) {

		Article article = new Article(name, manufactor, price);
		articleRepository.save(article);

		// Check persistence
		article = articleRepository.findById((int) article.getArticleId());

		return article.getArticleId();
	}

	@PostMapping(path = "/get")
	public @ResponseBody Article getArticle(@RequestParam int articleId) {
		return articleRepository.findById(articleId);
	}
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Article> getAllArticles() {
		return articleRepository.findAll();
	}

}
