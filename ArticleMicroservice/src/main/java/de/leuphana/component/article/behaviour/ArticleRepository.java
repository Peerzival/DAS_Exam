package de.leuphana.component.article.behaviour;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.leuphana.component.article.structure.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
	
	List<Article> findByName(String name);
	
	Article findById(int id);
	
}
