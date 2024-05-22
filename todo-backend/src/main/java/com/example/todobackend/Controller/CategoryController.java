package com.example.todobackend.Controller;


import com.example.todobackend.Entity.Category;
import com.example.todobackend.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("api/category")
@CrossOrigin
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(this.categoryService.addCategory(category), HttpStatus.CREATED);
    }
    @GetMapping("/getAllCategorys")
    public ResponseEntity<List<Category>> getAllCategorys() {
        return new ResponseEntity<>(this.categoryService.getAllCategorys(), HttpStatus.FOUND);
    }
    @GetMapping("/findCategoryById")
    public ResponseEntity<Category> findCategoryById(@RequestParam Long categoryId) {
        return new ResponseEntity<>(this.categoryService.getCategory(categoryId), HttpStatus.FOUND);
    }
    @PutMapping("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(this.categoryService.editCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<HttpStatus> deleteCategory(@RequestParam long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}