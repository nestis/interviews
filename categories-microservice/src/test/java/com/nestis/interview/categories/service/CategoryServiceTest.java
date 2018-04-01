package com.nestis.interview.categories.service;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nestis.interview.categories.entity.Category;
import com.nestis.interview.categories.repository.CategoryRepository;
import com.nestis.interview.categories.service.impl.CategoryServiceImpl;

@RunWith(SpringRunner.class)
public class CategoryServiceTest {

	@MockBean
	private CategoryRepository categoryRepository;
	
	private CategoryService categoryService;
	
	@Before
	public void setup() {
		categoryService = new CategoryServiceImpl(categoryRepository);
	}
	
	@Test
	public void shouldReturnCategories() throws Exception {
		List<Category> categories = new ArrayList<>();
		Category c1 = new Category();
		c1.setCategoryId(1);
		c1.setLabel("Test");
		categories.add(c1);
		
		given(categoryRepository.findAll()).willReturn(categories);
		
		List<Category> result = categoryService.getCategories();
		assertThat(result, is(IsNull.notNullValue()));
		assertTrue(result.size() == 1);
	}
}
