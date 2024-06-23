package com.example.productService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.productService.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Testcontainers
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;

	@Autowired 
	private ObjectMapper objectMapper;


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {

		
			ProductRequest productRequest = getProductRequest();
			String productRequestString = objectMapper.writeValueAsString(productRequest);

			mockMvc.perform(MockMvcRequestBuilders.post("/products")
			.contentType(MediaType.APPLICATION_JSON)
			.content(productRequestString))
			.andExpect(result -> result.getResponse().getContentAsString().contains("test product name"));
	
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
		.name("test product name")
		.description("test product description")
		.price(99.99)
		.build();
	}

}
