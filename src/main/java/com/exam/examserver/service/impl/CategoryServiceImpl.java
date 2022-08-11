package com.exam.examserver.service.impl;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.repo.CategoryRepo;
import com.exam.examserver.repo.QuizRepo;
import com.exam.examserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private QuizRepo quizRepo;

    @Override
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Category updatedCategory) {
        return categoryRepo.save(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.getById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
