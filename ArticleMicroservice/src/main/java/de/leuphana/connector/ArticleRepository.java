package de.leuphana.connector;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
	
	List<Article> findByName(String name);
	
	Article findById(int id);
	
}
