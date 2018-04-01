package com.nestis.interview.categories.service;

import java.util.List;

import com.nestis.interview.categories.entity.Category;

/**
 * Category Service interface.
 * @author nestis
 *
 */
public interface CategoryService {

	/**
	 * Get all the categories.
	 * @return List containing all the categories.
	 */
	List<Category> getCategories();
}
