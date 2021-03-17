package de.leuphana.component.behaviour;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.leuphana.component.structure.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
	
	Optional<Article> findByName(String name);
}
