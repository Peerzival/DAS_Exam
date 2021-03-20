package de.leuphana.component.shop.behaviour;

import org.apache.hc.client5.http.fluent.Content;
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

	String getArticle(int articleId);

	// -------------------- ORDER -------------------- \\
	// --------------------------------------------------------------------------------
	
	void getOrder(int orderId);

	void getAllOrdersAsString();

}
