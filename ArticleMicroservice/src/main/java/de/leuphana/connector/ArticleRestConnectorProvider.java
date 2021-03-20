package de.leuphana.connector;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.behaviour.ArticleComponentService;
import de.leuphana.component.behaviour.ArticleRepository;
import de.leuphana.component.behaviour.exception.ArticleNotFoundException;
import de.leuphana.component.structure.Article;

@RestController
@RequestMapping(path = "/article")
public class ArticleRestConnectorProvider implements ArticleComponentService {

	@Autowired
	private ArticleRepository articleRepository;

	// -------------------- CRUD -------------------- \\
	// --------------------------------------------------------------------------------

	// CREATE
	// curl localhost:8180/article/addArticle -d name=Weihnachtsmann -d manufactor=Leuphana -d price=10.5f
	// --------------------------------------------------------------------------------
	@Override
	@PostMapping(path = "/createArticle") // Map ONLY POST Requests
	public int createArticle(@RequestParam String name,
		@RequestParam String manufactor,
		@RequestParam float price) {

		Article article = new Article(name, manufactor, price);
		if (!checkIfArticleNameAlreadyExists(article)) {
			articleRepository.save(article);

			// Check persistence
			int articleId = article.getArticleId();
			article = articleRepository
					.findById(article.getArticleId())
					.orElseThrow(
							() -> new ArticleNotFoundException(
									articleId));

			return articleId;
		}
		return -1;
	}

	// UPDATE
	// curl localhost:8180/article/updateArticle -d articleId=1 -d
	// name=Rumpelstielzchen -d manufactor=TU_Hamburg -d price=56.5f
	// --------------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/updateArticle")
	public String updateArticle(@RequestParam int articleId,
		@RequestParam String name,
		@RequestParam String manufactor,
		@RequestParam float price) {

		Article updateArticle = articleRepository
				.findById(articleId)
				.orElseThrow(() -> new ArticleNotFoundException(
						articleId));

		if (updateArticle != null) {
			updateArticle.setName(name);
			updateArticle.setManufactor(manufactor);
			updateArticle.setPrice(price);

			articleRepository.save(updateArticle);
			return "Article with id '" + articleId
					+ "' updated.\n";
		}
		return "Update of article with id '" + articleId
				+ "' failed. "
				+ "Consider adding a new article.";
	}

	// READ
	// curl localhost:8180/article/getArticleById/1
	// curl localhost:8180/article/getAllArticles
	// curl localhost:8180/article/checkArticleId -d articleId=1
	// --------------------------------------------------------------------------------

	@Override
	@GetMapping(path = "/getArticleById/{articleId}")
	public Article getArticleById(
		@PathVariable(name = "articleId") int articleId) {
		Article article = articleRepository.findById(articleId)
				.orElseThrow(() -> new ArticleNotFoundException(
						articleId));

		System.out.println("GET article successful");
		return article;
	}

	@Override
	@GetMapping(path = "/getArticleByName/{name}")
	public Article getArticleByName(
		@PathVariable(name = "name") String name) {
		return articleRepository.findByName(name).orElseThrow(
				() -> new ArticleNotFoundException(name));
	}

	@Override
	@PostMapping(path = "/getArticleString")
	public @ResponseBody String getArticleString(@RequestParam int articleId) {
		Article article = articleRepository.findById(articleId)
				.orElseThrow(() -> new ArticleNotFoundException(
						articleId));

		return "Articlename: " + article.getName()
				+ "    Articlemanufactor: "
				+ article.getManufactor()
				+ "\nPrice of article: " + article.getPrice()
				+ "€.";
	}

	@Override
	@GetMapping(path = "/getAllArticles")
	public Iterable<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	@PostMapping(path = "/checkArticleId")
	public int checkArticleId(
		@RequestParam(name = "articleId") int articleId) {

		return articleRepository.findById(articleId).orElseThrow(
				() -> new ArticleNotFoundException(articleId))
				.getArticleId();
	}

	// DELETE
	// curl localhost:8180/article/deleteArticle -d articleId=3
	// --------------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/deleteArticle")
	public String deleteArticleById(
		@RequestParam int articleId) {
		try {
			articleRepository.deleteById(articleId);
		} catch (EmptyResultDataAccessException ex) {
			return ("There is no article with that id.\n");
		}
		return "Article with id '" + articleId + "' deleted.\n";
	}

	// Service Methods
	// --------------------------------------------------------------------------------

	private boolean checkIfArticleNameAlreadyExists(
		Article article) {
		List<Article> articles = (List<Article>) articleRepository
				.findAll();

		for (Article listArticle : articles) {
			if (listArticle.getName()
					.equals(article.getName())) {
				return true;
			}
		}

		return false;
	}
}
