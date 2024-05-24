package com.example.todobackend.Controller;


import com.example.todobackend.Entity.Category;
import com.example.todobackend.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(this.categoryService.addCategory(category), HttpStatus.CREATED);
    }
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }
    @GetMapping("/findCategoryById")
    public ResponseEntity<Category> findCategoryById(@RequestParam("CategoryId") Long categoryId) {
        return new ResponseEntity<>(this.categoryService.getCategory(categoryId), HttpStatus.FOUND);
    }
    @PutMapping("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(this.categoryService.editCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<HttpStatus> deleteCategory(@RequestParam("CategoryId") long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}