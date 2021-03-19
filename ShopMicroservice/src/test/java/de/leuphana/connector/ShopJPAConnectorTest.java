package de.leuphana.connector;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopJPAConnectorTest {
	
	@BeforeEach
	void setUp() throws Exception {}

	@AfterEach
	void tearDown() throws Exception {}

	@Test
	void canShopAccessArticleMicroService() {
		try {
			String confirmation =
					Request.post("http://localhost:8880/article/")
						.bodyForm(Form.form()
						.build())
						.execute()
						.returnContent().toString();
			
			System.out.println(confirmation);
			Assertions.assertNotNull(confirmation);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void canShopAccessOrderMicroService() {
		try {
			String confirmation =
					Request.post("http://localhost:8880/order/")
						.bodyForm(Form.form()
						.build())
						.execute()
						.returnContent().toString();
			
			System.out.println(confirmation);
			Assertions.assertNotNull(confirmation);
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void canShopAccessCustomerMicroService() {
		try {
			String confirmation =
					Request.post("http://localhost:8880/customer/")
						.bodyForm(Form.form()
						.build())
						.execute()
						.returnContent().toString();
			
			System.out.println(confirmation);
			Assertions.assertNotNull(confirmation);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
