package de.leuphana.component.shop.behaviour;

import org.springframework.web.bind.annotation.RequestParam;

public interface SupplierService {

	// -------------------- ARTICLE -------------------- \\
	// --------------------------------------------------------------------------------

	void addNewArticle(@RequestParam String name,
		@RequestParam String manufactor,
		@RequestParam float price);

	String updateArticle(@RequestParam int articleId,
		@RequestParam String name,
		@RequestParam String manufactor,
		@RequestParam float price);

	String deleteArticleById(@RequestParam int articleId);

	String getArticleString(@RequestParam int articleId);

	// -------------------- ORDER -------------------- \\
	// --------------------------------------------------------------------------------
	
	String getOrderString(@RequestParam int orderId);

	String getAllOrdersAsString();

}
