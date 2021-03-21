package de.leuphana.connector;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopSpringConnectorRequesterTest {
	
	//TODO delete
	// IP Max: 192.168.178.22
	// IP Andy:
	// IP Henrik: 192.168.178.121
		
	private static String ipString;
	
	//TODO uncomment
//	private static String ipString;
	
	@BeforeEach
	void setUp() throws Exception {
		ipString = "http://192.168.178.121:8880";
		//TODO uncomment
//		ipString = "http://insertOwnIP:8880"
	}

	@AfterEach
	void tearDown() throws Exception {
		ipString = null;
	}

	@Test
	void canShopAccessArticleMicroService() {
		try {
			String confirmation =
					Request.post(ipString + "/article/")
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
					Request.post(ipString + "/order/")
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
					Request.post(ipString + "/customer/")
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
