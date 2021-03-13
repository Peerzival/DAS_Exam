package de.leuphana.connector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.ArticleRepository;
import de.leuphana.component.behaviour.exception.ArticleNotFoundException;
import de.leuphana.component.structure.Article;

@RestController
@RequestMapping(path = "/article")
public class ArticleRestConnectorProvider {

	@Autowired
	private ArticleRepository articleRepository;

//	@RequestMapping("/")
//	public String home() {
//		return "Hello Docker World";
//	}

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody int addNewArticle(@RequestParam String name, @RequestParam String manufactor,
			@RequestParam float price) {

		Article article = new Article(name, manufactor, price);
		if (!checkIfArticleNameAlreadyExists(article)) {
			articleRepository.save(article);
			// Check persistence
			int articleId = article.getArticleId();
			article = articleRepository.findById(article.getArticleId()).orElseThrow(() -> new ArticleNotFoundException(articleId));

			return article.getArticleId();
		}

		return -1;

	}

	@GetMapping(path = "/getArticleById/{articleId}")
	public Article getArticleById(@PathVariable(name="articleId") int articleId) {
		return articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@PostMapping(path = "/findArticlesByName")
	public List<Article> findArticlesByName(@RequestParam("name") String name) {
		return articleRepository.findByName(name);
	}

	private boolean checkIfArticleNameAlreadyExists(Article article) {
		List<Article> articles = (List<Article>) articleRepository.findAll();
		for (Article listArticle : articles) {
			if (listArticle.getName().equals(article.getName())) {
				return true;
			}
		}
		return false;
	}

}
