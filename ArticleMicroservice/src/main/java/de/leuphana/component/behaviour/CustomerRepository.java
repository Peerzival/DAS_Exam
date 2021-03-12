package de.leuphana.component.behaviour;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.leuphana.component.structure.Article;

public interface CustomerRepository extends CrudRepository<Article, Integer> {
	
	List<Article> findByName(String name);
	
}
