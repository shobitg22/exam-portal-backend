package com.exam.examserver.controller;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        return ResponseEntity.ok().body(categoryService.addCategory(category));
    }
    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody Category updatedCategory){
        return ResponseEntity.ok().body(categoryService.updateCategory(updatedCategory));
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }
}
