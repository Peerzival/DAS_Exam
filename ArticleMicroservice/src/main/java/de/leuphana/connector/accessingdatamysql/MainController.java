package de.leuphana.connector.accessingdatamysql;

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
@RequestMapping(path="/demo")
public class MainController {
  @Autowired 
  private ArticleRepository articleRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewArticle (@RequestParam String name
      , @RequestParam String manufactor, @RequestParam float price) {

    Article article = new Article(name, manufactor, price);
    articleRepository.save(article);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Article> getAllArticles() {
    return articleRepository.findAll();
  }
}
