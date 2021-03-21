package de.leuphana.component.shop.behaviour;

public interface SupplierService {

	// -------------------- ARTICLE -------------------- \\
	// --------------------------------------------------------------------------------

	String createArticle(String name,
		String manufactor,
		double price);

	String updateArticle(int articleId,
		String name,
		String manufactor,
		double price);

	String deleteArticleById(int articleId);

	String getArticle(int articleId);
	
	String getAllArticles();

	// -------------------- ORDER -------------------- \\
	// --------------------------------------------------------------------------------
	
	String getOrder(int orderId);

	String getAllOrders();

}
