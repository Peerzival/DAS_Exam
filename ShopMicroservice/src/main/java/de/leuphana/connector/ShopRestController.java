package de.leuphana.connector;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.shop.behaviour.CustomerService;
import de.leuphana.component.shop.behaviour.SupplierService;

/**
 * @author Max Gnewuch
 * @author Henrik Prüß
 * @author Andreas Bächler
 * 
 *         This microservice represents our Httpclient. It sends an Httprequest
 *         to the API Gateway. This distributes the request to the corresponding
 *         microservice order, article or customer.
 * 
 *         The shop microservice provides various functionalities via two interfaces. 
 *         Depending on which interface is used, the shop offers different functionality. 
 *         E.g. only the "SupplierService" can insert new articles.
 */
@RestController
public class ShopRestController
		implements CustomerService, SupplierService {
	// TODO delete
	// IP Max: 192.168.178.22
	// IP Andy:
	// IP Henrik: 192.168.178.121

	private static String ipString = "http://192.168.178.121:8880";

	// TODO uncomment
//	private static String ipString = "http://insertOwnIP:8880";

	// -------------------- ARTICLE -------------------- \\
	// -------------------------------------------------------------------------

	// localhost:8888/article/createArticle?name=Schaufel&manufactor=Hornbach&price=15.99
	@Override
	@GetMapping(path = "/article/createArticle")
	public String createArticle(
		@RequestParam("name") String name,
		@RequestParam("manufactor") String manufactor,
		@RequestParam("price") double price) {

		try {
			return Request
					.post(ipString
							+ "/article/createArticle")
					.bodyForm(Form.form()
							.add("name", name)
							.add("manufactor",
									manufactor)
							.add("price", "" + price)
							.build())
					.execute().returnContent()
					.asString();

		} catch (IOException e) {
			e.printStackTrace();
			return "Creation of new article failed. Please try again.\n";
		}
	};

	// localhost:8888/article/updateArticle?articleId=1&name=Besen&manufactor=Loewe&price=7.99
	@Override
	@GetMapping(path = "/article/updateArticle")
	public String updateArticle(
		@RequestParam("articleId") int articleId,
		@RequestParam("name") String name,
		@RequestParam("manufactor") String manufactor,
		@RequestParam("price") double price) {

		try {
			return Request
					.post(ipString
							+ "/article/updateArticle")
					.bodyForm(Form.form()
							.add("articleId",
									"" + articleId)
							.add("name", name)
							.add("manufactor",
									manufactor)
							.add("price", "" + price)
							.build())
					.execute().returnContent()
					.asString();

		} catch (IOException e) {
			e.printStackTrace();
			return "No article with id '" + articleId + "' found. " + 
			"Update command canceled.\n";
		}
	};

	// localhost:8888/article/deleteArticle?articleId=5
	@Override
	@GetMapping(path = "/article/deleteArticle")
	public String deleteArticleById(
		@RequestParam int articleId) {

		try {
			return Request
					.post(ipString
							+ "/article/deleteArticle")
					.bodyForm(Form.form()
							.add("articleId",
									"" + articleId)
							.build())
					.execute().returnContent()
					.asString();

		} catch (IOException e) {
			e.printStackTrace();
			return "No article with id '" + articleId + "' found. " + 
					"Delete command canceled.\n";
		}
	}

	// localhost:8888/article/getArticle/5
	@Override
	@GetMapping(path = "/article/getArticle/{articleId}")
	public String getArticle(
		@PathVariable("articleId") int articleId) {

		try {
			return Request.post(ipString
					+ "/article/getArticleString")
					.bodyForm(Form.form()
							.add("articleId",
									"" + articleId)
							.build())
					.execute().returnContent()
					.asString();

		} catch (IOException e) {
			e.printStackTrace();
			return "No article with id '" + articleId + "' found.\n";
		}
	}

	// localhost:8888/article/getAllArticles
	@GetMapping(path = "/article/getAllArticles")
	public String getAllArticles() {
		try {
			return Request.get(ipString
					+ "/article/getAllArticlesString")
					.execute().returnContent()
					.asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}	
	}

	// -------------------- CUSTOMER -------------------- \\
	// -------------------------------------------------------------------------

	// localhost:8888/customer/createCustomer?name=Andeas&address=Ziegenhof 13
	@Override
	@GetMapping(path = "/customer/createCustomer")
	public String createCustomer(
		@RequestParam String name,
		@RequestParam String address) {

		try {
			return Request.post(ipString
					+ "/customer/createCustomer")
					.bodyForm(Form.form()
							.add("name", "" + name)
							.add("address",
									"" + address)
							.build())
					.execute().returnContent().asString();

		} catch (IOException e) {
			e.printStackTrace();
			return "Creation of customer failed.";
		}
	};

	// localhost:8888/customer/getCustomer/1
	@Override
	@GetMapping(path = "/customer/getCustomer/{customerId}")
	public String getCustomerString(
		@PathVariable("customerId") int customerId) {

		try {
			return Request.post(ipString
					+ "/customer/getCustomerString")
					.bodyForm(Form.form()
							.add("customerId",
									"" + customerId)
							.build())
					.execute().returnContent().asString();

		} catch (IOException e) {
			e.printStackTrace();
			return "No customer with id '" + customerId + "' found.\n";
		}
	};

	// localhost:8888/customer/getAllCustomers
	@Override
	@GetMapping(path = "/customer/getAllCustomers")
	public String getAllCustomersAsString() {

		try {
			return Request.get(ipString
					+ "/customer/getAllCustomersAsString")
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	};

	// localhost:8888/customer/changeCustomerAddress?customerId=1&address=Strandschuppen
	@Override
	@GetMapping(path = "/customer/changeCustomerAddress")
	public String changeCustomerAddress(
		@RequestParam int customerId,
		@RequestParam String address) {

		try {
			return Request.post(ipString
					+ "/customer/changeCustomerAddress")
					.bodyForm(Form.form()
							.add("customerId",
									"" + customerId)
							.add("address", address)
							.build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "No customer with id '" + customerId + "' found. " + 
			"Update command canceled.\n";
		}
	};

	// localhost:8888/customer/changeCustomerName?customerId=1&name=Shisha
	@Override
	@GetMapping(path = "/customer/changeCustomerName")
	public String changeCustomerName(
		@RequestParam int customerId,
		@RequestParam String name) {

		try {
			return Request.post(ipString
					+ "/customer/changeCustomerName")
					.bodyForm(Form.form()
							.add("customerId",
									"" + customerId)
							.add("name", name).build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "No customer with id '" + customerId + "' found. " + 
					"Update command canceled.\n";
		}
	};

	// localhost:8888/customer/deleteCustomer?customerId=2
	@Override
	@GetMapping(path = "/customer/deleteCustomer")
	public String deleteCustomer(
		@RequestParam int customerId) {

		try {
			return Request.delete(ipString
					+ "/customer/deleteCustomer")
					.bodyForm(Form.form()
							.add("customerId",
									"" + customerId)
							.build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "No customer with id '" + customerId + "' found. " + 
					"Update command canceled.\n";
		}
	};

	// localhost:8888/customer/addCartItem?customerId=1&articleId=1
	@Override
	@GetMapping(path = "/customer/addCartItem")
	public String addCartItem(
		@RequestParam int customerId,
		@RequestParam int articleId) {

		try {
			return Request
					.post(ipString
							+ "/customer/addCartItem")
					.bodyForm(Form.form()
							.add("customerId",
									"" + customerId)
							.add("articleId",
									"" + articleId)
							.build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "No customer with id '" + customerId + "' found.\n";
		}

	};

	// localhost:8888/customer/checkOutCartToOrder?customerId=1
	@Override
	@GetMapping(path = "/customer/checkOutCartToOrder")
	public String checkOutCartToOrder(
		@RequestParam int customerId) {

		try {
			return Request.post(ipString
					+ "/customer/checkOutCartToOrder")
					.bodyForm(Form.form()
							.add("customerId",
									"" + customerId)
							.build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "No customer with id '" + customerId + "' found.\n";
		}
	};

	// localhost:8888/customer/getCartItemsFromCustomer?customerId=1
	@Override
	@GetMapping(path = "/customer/getCartItemsFromCustomer")
	public String getCartItemsFromCustomer(
		@RequestParam int customerId) {

		try {
			return Request.post(ipString
					+ "/customer/getCartItemsFromCustomer")
					.bodyForm(Form.form()
							.add("customerId",
									"" + customerId)
							.build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "No customer with id '" + customerId + "' found.\n";
		}
	};

	// localhost:8888/customer/decrementArticleFromCartItem?customerId=1&articleId=1
	@Override
	@GetMapping(path = "/customer/decrementArticleFromCartItem")
	public String decrementArticleFromCartItem(
		@RequestParam int customerId,
		@RequestParam int articleId) {

		try {
			return Request.post(ipString
					+ "/customer/decrementArticleFromCartItem")
					.bodyForm(Form.form()
							.add("customerId",
									"" + customerId)
							.add("articleId",
									"" + articleId)
							.build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "No customer with id '" + customerId + "' found.\n";
		}
	};

	// localhost:8888/customer/deleteArticleFromCartItem?customerId=1&articleId=2
	@Override
	@GetMapping(path = "/customer/deleteArticleFromCartItem")
	public String deleteArticleFromCartItem(
		@RequestParam int customerId,
		@RequestParam int articleId) {

		try {
			return Request.post(ipString
					+ "/customer/deleteArticleFromCartItem")
					.bodyForm(Form.form()
							.add("customerId",
									"" + customerId)
							.add("articleId",
									"" + articleId)
							.build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "No customer with id '" + customerId + "' found.\n";
		}

	};

	// -------------------- ORDER -------------------- \\
	// -------------------------------------------------------------------------

	// localhost:8888/order/getOrder/1
	@Override
	@GetMapping(path = "/order/getOrder/{orderId}")
	public String getOrder(
		@PathVariable("orderId") int orderId) {

		try {
			return Request
					.post(ipString
							+ "/order/getOrderString")
					.bodyForm(Form.form()
							.add("orderId",
									"" + orderId)
							.build())
					.execute().returnContent()
					.asString();

		} catch (IOException e) {
			e.printStackTrace();
			return "No order with id '" + orderId + "' found.\n";
		}

	}

	// localhost:8888/order/getAllOrders
	@Override
	@GetMapping(path = "/order/getAllOrders")
	public String getAllOrders() {

		try {
			return Request.get(ipString
					+ "/order/getAllOrdersAsString")
					.execute().returnContent()
					.asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	};

	// localhost:8888/order/deleteOrder?orderId=2
	@Override
	@GetMapping(path = "/order/deleteOrder")
	public String deleteOrder(
		@RequestParam int orderId) {

		try {
			return Request
					.post(ipString
							+ "/order/deleteOrder")
					.bodyForm(Form.form()
							.add("orderId",
									"" + orderId)
							.build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
			return "No order with id '" + orderId + "' found.\n";
		}
	};
}
