package com.nestis.interview.categories.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nestis.interview.categories.entity.Category;
import com.nestis.interview.categories.repository.CategoryRepository;
import com.nestis.interview.categories.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(@Autowired CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> getCategories() {
		return this.categoryRepository.findAll();
	}
	
}
