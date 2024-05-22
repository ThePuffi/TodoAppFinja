package com.example.todobackend.Service;

import com.example.todobackend.Entity.Category;
import com.example.todobackend.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public Category addCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
        if (categoryOptional.isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }
        return categoryRepository.save(category);
    }

    public Category editCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
        if (categoryOptional.isPresent()) {
            return categoryRepository.save(category);
        }
        throw new IllegalArgumentException("Category does not exit");
    }

    public Category getCategory(long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        throw new IllegalArgumentException("Category does not exit");
    }

    public void deleteCategory(long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            categoryRepository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Category does not exit");
    }

    public List<Category> getAllCategories() {
        return  categoryRepository.findAll();
    }
}
