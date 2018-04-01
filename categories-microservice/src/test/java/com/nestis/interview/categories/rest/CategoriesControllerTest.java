package com.nestis.interview.categories.rest;

import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nestis.interview.categories.CategoriesApplication;
import com.nestis.interview.categories.entity.Category;
import com.nestis.interview.categories.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CategoriesApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "endpoints.categories=categories")
public class CategoriesControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private CategoryService categoryService;
	
	@Test
	public void shouldGetCategories() throws Exception {
		List<Category> categories = new ArrayList<>();
		Category c1 = new Category();
		c1.setCategoryId(1);
		c1.setLabel("Test");
		categories.add(c1);
		
		given(categoryService.getCategories()).willReturn(categories);
		
		webTestClient.get().uri("/categories")
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Category.class)
			.hasSize(1);
	}
}
