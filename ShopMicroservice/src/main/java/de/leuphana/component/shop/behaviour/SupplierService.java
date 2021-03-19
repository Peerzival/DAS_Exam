package de.leuphana.component.shop.behaviour;

import org.springframework.web.bind.annotation.RequestParam;

public interface SupplierService {

	// -------------------- ARTICLE -------------------- \\
	// --------------------------------------------------------------------------------

	void addNewArticle(@RequestParam String name,
		@RequestParam String manufactor,
		@RequestParam float price);

	void updateArticle(@RequestParam int articleId,
		@RequestParam String name,
		@RequestParam String manufactor,
		@RequestParam float price);

	void deleteArticleById(@RequestParam int articleId);

	void getArticle(int articleId);

	// -------------------- ORDER -------------------- \\
	// --------------------------------------------------------------------------------
	
	void getOrder(int orderId);

	void getAllOrdersAsString();

}
