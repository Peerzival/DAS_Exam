package de.leuphana.connector;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	private static Logger log;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public ArticleRestConnectorProvider() {
		log = LogManager.getLogger(this.getClass());
	}

	// -------------------- CRUD -------------------- \\
	// --------------------------------------------------------------------------------

	// CREATE
	// curl localhost:8180/article/addArticle -d name=Weihnachtsmann -d manufactor=Leuphana -d price=10.5f
	// --------------------------------------------------------------------------------
	@Override
	@PostMapping(path = "/createArticle") // Map ONLY POST Requests
	public int createArticle(@RequestParam String name,
		@RequestParam String manufactor,
		@RequestParam double price) {

		Article article = new Article(name, manufactor, price);
		
		if (checkArticleNameExistence(article) == 0) {
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
	// curl localhost:8180/article/updateArticle -d articleId=1 -d name=Rumpelstielzchen -d manufactor=TU_Hamburg -d price=56.5f
	// --------------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/updateArticle")
	public String updateArticle(@RequestParam int articleId,
		@RequestParam String name,
		@RequestParam String manufactor,
		@RequestParam double price) {
		
		Article updateArticle = articleRepository
				.findById(articleId)
				.orElseThrow(() -> new ArticleNotFoundException(
						articleId));
		
		String oldName = updateArticle.getName();
		String oldManufactor = updateArticle.getManufactor();
		double oldPrice = updateArticle.getPrice();
		
		if (updateArticle != null) {
			updateArticle.setName(name);
			updateArticle.setManufactor(manufactor);
			updateArticle.setPrice(price);

			articleRepository.save(updateArticle);
			
			if (checkArticleNameExistence(updateArticle) > 1) {
				return rollbackArticleUpdate(updateArticle, oldName, 
						oldManufactor, oldPrice, articleId);
			}
		}
		
		return createArticleUpdateSuccessMessage(articleId, oldName,
				oldManufactor, oldPrice, updateArticle);
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
		
		log.info("Articlename: " + article.getName()
				+ "\nArticlemanufactor: "
				+ article.getManufactor()
				+ "\nPrice of article: " + article.getPrice()
				+ "€.");
		
		return "Articlename: " + article.getName()
				+ "<br>Articlemanufactor: "
				+ article.getManufactor()
				+ "<br>Price of article: " + article.getPrice()
				+ "€.";
	}

	@Override
	@GetMapping(path = "/getAllArticles")
	public Iterable<Article> getAllArticles() {
		return articleRepository.findAll();
	}
	
	@GetMapping(path = "/getAllArticlesString")
	public String getAllArticlesString() {
		
		List<Article> articles = (List<Article>) getAllArticles();
		String articlesString = "";
		
		for (Article article: articles) {
			articlesString = articlesString +
					"Articlename: " + article.getName()
					+ " - Articlemanufactor: "
					+ article.getManufactor()
					+ " - Price of article: " + article.getPrice()
					+ "€.\n";
		}
		log.info(articlesString);
		
		articlesString="";
		for (Article article: articles) {
			articlesString = articlesString +
					"Articlename: " + article.getName()
					+ " - Articlemanufactor: "
					+ article.getManufactor()
					+ " - Price of article: " + article.getPrice()
					+ "€.<br>";
		}
		
		return articlesString;
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
	
	@GetMapping(path="/")
	public String demo() {
		return "Hello from Article-Microservice";
	}

	private int checkArticleNameExistence(
		Article article) {
		List<Article> articles = (List<Article>) articleRepository
				.findAll();

		int nameCount = 0;
		for (Article listArticle : articles) {
			if (listArticle.getName()
					.equals(article.getName())) {
				nameCount++;
			}
		}
		return nameCount;
	}
	
	private String rollbackArticleUpdate(Article updateArticle, 
			String oldName, String oldManufactor, double oldPrice, 
			int articleId) {
		// Simulation of rollback
		updateArticle.setName(oldName);
		updateArticle.setManufactor(oldManufactor);
		updateArticle.setPrice(oldPrice);
		articleRepository.save(updateArticle);
		
		String failMessage = "Update of article with id '" 
				+ articleId
				+ "' failed. "
				+ "Article name already taken.\n";
		
		String failHTMLMessage = "Update of article with id '" + 
				articleId + "' failed. " + 
				"Article name already taken.<br>";
		
		log.info(failMessage);
		return failHTMLMessage;
	}
	
	private String createArticleUpdateSuccessMessage(int articleId, 
			String oldName, String oldManufactor, double oldPrice, 
			Article updateArticle) {
		
		String successMessage = "Article with id '" 
				+ articleId + "' updated.\n" + 
				"Old article:\n" +
				"Name: " + oldName +
				"\nManufactor: " + oldManufactor + 
				"\nPrice: " + oldPrice + "\n" + 
				"Updated article:\n" + 
				"Name: " + updateArticle.getName() +
				"\nManufactor: " + updateArticle.getManufactor() + 
				"\nPrice: " + updateArticle.getPrice() + "\n";
		
		String successHTMLMessage = "Article with id '" 
				+ articleId + "' updated.<br>" + 
				"Old article:<br>" +
				"Name: " + oldName +
				"<br>Manufactor: " + oldManufactor + 
				"<br>Price: " + oldPrice + "<br>" + 
				"Updated article:<br>" + 
				"Name: " + updateArticle.getName() +
				"<br>Manufactor: " + updateArticle.getManufactor() + 
				"<br>Price: " + updateArticle.getPrice() + "<br>";
		
		log.info(successMessage);
		return successHTMLMessage;
	}
}
