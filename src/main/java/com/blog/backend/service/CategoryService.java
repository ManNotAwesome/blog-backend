package com.blog.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.backend.dto.category.CategoryResponse;
import com.blog.backend.dto.category.CreateCategoryRequest;
import com.blog.backend.entity.Category;
import com.blog.backend.repository.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public CategoryResponse createCategory(CreateCategoryRequest request) {

		String name = request.getName().trim();

		if (categoryRepository.existsByName(name)) {
			throw new RuntimeException("Category already exists!");
		}

		Category category = new Category();
		category.setName(name);

		Category saved = categoryRepository.save(category);

		return new CategoryResponse(saved.getId(), saved.getName());
	}

	public List<CategoryResponse> getAllCategories() {
		return categoryRepository.findAll().stream().map(c -> new CategoryResponse(c.getId(), c.getName())).toList();
	}
}
