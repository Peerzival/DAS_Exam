package de.leuphana.connector;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest // Ist ein spezieller Test, mit dem Autowired nutzbar ist
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Damit keine h2 Datenbank benutzt wird
@ImportAutoConfiguration(FeignAutoConfiguration.class)
class ArticleRestConnectorRequesterTest {
	
	@Autowired
	private EntityManager entityManager;
	
//	private static 
	
	@Autowired
	private FeignContext feignContext;
	
	@BeforeEach
	void setUp() throws Exception {
		feignContext.getInstance("article",
				ArticleRestConnectorRequesterTest.class);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
	}

}
