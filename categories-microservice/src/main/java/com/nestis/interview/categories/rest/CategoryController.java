package com.nestis.interview.categories.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nestis.interview.categories.entity.Category;
import com.nestis.interview.categories.service.CategoryService;

import reactor.core.publisher.Flux;

/**
 * Categories Rest Controller
 * @author nestis
 *
 */
@RestController
@RequestMapping("${endpoints.categories:category}")
public class CategoryController {

	private CategoryService categoryService;
	
	public CategoryController(@Autowired CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/**
	 * Get all the categories.
	 * @return Flux containing all the categories.
	 */
	@GetMapping
	public Flux<Category> getCategories() {
		return Flux.fromIterable(this.categoryService.getCategories());
	}
}
