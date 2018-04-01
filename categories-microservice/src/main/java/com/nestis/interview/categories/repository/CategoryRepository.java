package com.nestis.interview.categories.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nestis.interview.categories.entity.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
