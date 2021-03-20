package de.leuphana.connector;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.shop.behaviour.CustomerService;
import de.leuphana.component.shop.behaviour.SupplierService;

@RestController
public class ShopRestController implements CustomerService, SupplierService 
{
	// IP Max:
	// IP Andy:
	// IP Henrik: 192.168.178.121
	
	private static String ipString = "http://192.168.178.121:8880";
	

	// -------------------- ARTICLE -------------------- \\
	// -------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/article/createArticle")
	public String createArticle(@RequestParam("name") String name,
		@RequestParam("manufactor") String manufactor,
		@RequestParam("price") double price) {
		
		try {
			return
					Request.post(ipString + "/article/createArticle")
						.bodyForm(Form.form()
						.add("name", name)
						.add("manufactor", manufactor)
						.add("price", "" + price)
						.build())
						.execute()
						.returnContent()
						.asString();
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
		
	};

	@Override
	@PostMapping(path = "/article/updateArticle")
	public String updateArticle(@RequestParam("articleId")int articleId, 
			@RequestParam("name")String name, 
			@RequestParam("manufactor")String manufactor, 
			@RequestParam("price")double price) {
		
		try {
			return
					Request.post(ipString + "/article/updateArticle")
						.bodyForm(Form.form()
						.add("articleId", "" + articleId)
						.add("name", name)
						.add("manufactor", manufactor)
						.add("price", "" + price)
						.build())
						.execute()
						.returnContent()
						.asString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	};
	
	@Override
	@PostMapping(path = "/article/deleteArticle")
	public String deleteArticleById(@RequestParam int articleId) {
		
		try {
			return
					Request.post(ipString + "/article/deleteArticle")
						.bodyForm(Form.form()
						.add("articleId", "" + articleId)
						.build())
						.execute()
						.returnContent()
						.asString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@Override
	@GetMapping(path = "/article/getArticle/{articleId}")
	public String getArticle(@PathVariable("articleId") int articleId) {
		
		try {
			return
					Request
						.post(ipString + "/article/getArticleString")
							.bodyForm(Form.form()
							.add("articleId", "" + articleId)
							.build())
							.execute()
							.returnContent()
							.asString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@GetMapping(path = "/article/getAllArticles")
	public String getAllArticles() {
		try {
			return
					Request
					.get(ipString + "/article/getAllArticlesString")
						.execute()
						.returnContent()
						.asString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	// -------------------- CUSTOMER -------------------- \\
	// -------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/customer/createCustomer")
	public void createCustomer(@RequestParam String name, 
								@RequestParam String address) {
		
		try {
			System.out.println(
					Request
						.post(ipString + "/customer/createCustomer")
							.bodyForm(Form.form()
							.add("name", "" + name)
							.add("address", "" + address)
							.build())
							.execute()
							.returnContent());
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	};

	@Override
	@GetMapping(path = "/customer/getCustomerString")
	public void getCustomerString(@PathVariable("customerId") int customerId) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/getCustomerString")
							.bodyForm(Form.form()
							.add("name", "" + customerId)
							.build())
							.execute()
							.returnContent());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	@Override
	@GetMapping(path = "/customer/getAllCustomersAsString")
	public void getAllCustomersAsString() {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/getAllCustomersAsString")
							.bodyForm(Form.form()
							.build())
							.execute()
							.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	@Override
	@PostMapping(path = "/customer/changeCustomerAddress")
	public void changeCustomerAddress(@RequestParam int customerId,
										@RequestParam String address) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/changeCustomerAddress")
							.bodyForm(Form.form()
							.add("customerId", "" + customerId)
							.add("address", address)
							.build())
							.execute()
							.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	@Override
	@PostMapping(path = "/customer/changeCustomerName")
	public void changeCustomerName(@RequestParam int customerId, 
									@RequestParam String name) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/changeCustomerName")
							.bodyForm(Form.form()
							.add("customerId", "" + customerId)
							.add("name", name)
							.build())
							.execute()
							.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	@Override
	@PostMapping(path = "/customer/deleteCustomer")
	public void deleteCustomer(@RequestParam int customerId) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/deleteCustomer")
							.bodyForm(Form.form()
							.add("customerId", "" + customerId)
							.build())
							.execute()
							.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	@Override
	@PostMapping(path = "/customer/addCartItem")
	public void addCartItem(@RequestParam int customerId, 
							@RequestParam int articleId) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/addCartItem")
							.bodyForm(Form.form()
							.add("customerId", "" + customerId)
							.add("articleId", "" + articleId)
							.build())
							.execute()
							.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	};

	@Override
	@PostMapping(path = "/customer/checkOutCartToOrder")
	public void checkOutCartToOrder(@RequestParam int customerId) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/checkOutCartToOrder")
							.bodyForm(Form.form()
							.add("customerId", "" + customerId)
							.build())
							.execute()
							.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	@Override
	@PostMapping(path = "/customer/getCartItemsFromCustomer")
	public void getCartItemsFromCustomer(@RequestParam int customerId) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/getCartItemsFromCustomer")
							.bodyForm(Form.form()
							.add("customerId", "" + customerId)
							.build())
							.execute()
							.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	@Override
	@PostMapping(path = "/customer/decrementArticleFromCartItem")
	public void decrementArticleFromCartItem(
								@RequestParam int customerId, 
								@RequestParam int articleId) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/decrementArticleFromCartItem")
							.bodyForm(Form.form()
							.add("customerId", "" + customerId)
							.add("articleId", "" + articleId)
							.build())
							.execute()
							.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	};

	@Override
	@PostMapping(path = "/customer/deleteArticleFromCartItem")
	public void deleteArticleFromCartItem(
										@RequestParam int customerId, 
										@RequestParam int articleId) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/customer/deleteArticleFromCartItem")
							.bodyForm(Form.form()
							.add("customerId", "" + customerId)
							.add("articleId", "" + articleId)
							.build())
							.execute()
							.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	};

	// -------------------- ORDER -------------------- \\
	// -------------------------------------------------------------------------

	@Override
	@GetMapping(path = "/order/getOrderString/{orderId}")
	public String getOrder(@PathVariable("orderId") int orderId) {
		
		try {
			return
					Request
						.post(ipString + "/order/getOrderString")
						.bodyForm(Form.form()
						.add("orderId", "" + orderId)
						.build())
						.execute()
						.returnContent()
						.asString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	@Override
	@GetMapping(path = "/order/getAllOrders")
	public String getAllOrders() {
		
		try {
			return
					Request
						.get(ipString + "/order/getAllOrdersAsString")
						.bodyForm(Form.form()
						.build())
						.execute()
						.returnContent()
						.asString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
		
	};

	@Override
	@PostMapping(path = "/order/deleteOrder")
	public void deleteOrder(@RequestParam int orderId) {
		
		try {
			System.out.println(
					Request
						.get(ipString + "/order/deleteOrder")
						.bodyForm(Form.form()
						.add("orderId", "" + orderId)
						.build())
						.execute()
						.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
}
