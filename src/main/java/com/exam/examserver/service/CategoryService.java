package com.exam.examserver.service;

import com.exam.examserver.entity.exam.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Category updatedCategory);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
}
