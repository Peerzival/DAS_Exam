package de.leuphana.connector;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.component.shop.behaviour.CustomerService;
import de.leuphana.component.shop.behaviour.SupplierService;

@RestController
public class ShopRestController implements CustomerService, SupplierService 
{
	

	// -------------------- ARTICLE -------------------- \\
	// -------------------------------------------------------------------------

	@Override
	@PostMapping(path = "/article/createArticle")
	public void addNewArticle(@RequestParam("name") String name,
		@RequestParam("manufactor") String manufactor,
		@RequestParam("price") float price) {
		
		try {
			System.out.println(
					Request.post("http://localhost:8880/article/createArticle")
						.bodyForm(Form.form()
						.add("name", name)
						.add("manufactor", manufactor)
						.add("price", "" + price)
						.build())
						.execute()
						.returnContent());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	};

	@Override
	@PostMapping(path = "/article/updateArticle")
	public void updateArticle(@RequestParam("articleId")int articleId, 
			@RequestParam("name")String name, 
			@RequestParam("manufactor")String manufactor, 
			@RequestParam("price")float price) {
		
		try {
			System.out.println(
					Request.post("http://localhost:8880/article/updateArticle")
						.bodyForm(Form.form()
						.add("articleId", "" + articleId)
						.add("name", name)
						.add("manufactor", manufactor)
						.add("price", "" + price)
						.build())
						.execute()
						.returnContent());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
	
	@Override
	@PostMapping(path = "/article/deleteArticle")
	public void deleteArticleById(@RequestParam int articleId) {
		
		try {
			System.out.println(
					Request.post("http://localhost:8880/article/deleteArticle")
						.bodyForm(Form.form()
						.add("articleId", "" + articleId)
						.build())
						.execute()
						.returnContent());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@GetMapping(path = "/article/getArticle/{articleId}")
	public void getArticle(@PathVariable("articleId") int articleId) {
		
		try {
			System.out.println(
					Request
						.post("http://localhost:8880/article/getArticleString")
							.bodyForm(Form.form()
							.add("articleId", "" + articleId)
							.build())
							.execute()
							.returnContent());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

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
						.post("http://localhost:8880/customer/createCustomer")
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
						.get("http://localhost:8880/customer/getCustomerString")
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
						.get("http://localhost:8880/customer/getAllCustomersAsString")
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
						.get("http://localhost:8880/customer/changeCustomerAddress")
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
						.get("http://localhost:8880/customer/changeCustomerName")
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
						.get("http://localhost:8880/customer/deleteCustomer")
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
						.get("http://localhost:8880/customer/addCartItem")
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
						.get("http://localhost:8880/customer/checkOutCartToOrder")
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
						.get("http://localhost:8880/customer/getCartItemsFromCustomer")
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
						.get("http://localhost:8880/customer/decrementArticleFromCartItem")
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
						.get("http://localhost:8880/customer/deleteArticleFromCartItem")
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
	public void getOrder(@PathVariable("orderId") int orderId) {
		
		try {
			System.out.println(
					Request
						.post("http://localhost:8880/order/getOrderString")
						.bodyForm(Form.form()
						.add("orderId", "" + orderId)
						.build())
						.execute()
						.returnContent());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@GetMapping(path = "/order/getAllOrders")
	public void getAllOrdersAsString() {
		
		try {
			System.out.println(
					Request
						.get("http://localhost:8880/order/getAllOrdersAsString")
						.bodyForm(Form.form()
						.build())
						.execute()
						.returnContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	};

	@Override
	@PostMapping(path = "/order/deleteOrder")
	public void deleteOrder(@RequestParam int orderId) {
		
		try {
			System.out.println(
					Request
						.get("http://localhost:8880/order/deleteOrder")
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
