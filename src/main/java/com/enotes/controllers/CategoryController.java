package com.enotes.controllers;

import com.enotes.entities.Category;
import com.enotes.services.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")

public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        Boolean saveCategory = categoryService.saveCategory(category);
        if (saveCategory) return new ResponseEntity<>("Category Saved", HttpStatus.CREATED);
        else return new ResponseEntity<>("Category Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-categories")
    public ResponseEntity<?> getAllCategories(){
        List<Category> categories = categoryService.getCategories();
        if (!CollectionUtils.isEmpty(categories)) return new ResponseEntity<>(categories, HttpStatus.OK);
        else return new ResponseEntity<>("Categories are not available.", HttpStatus.NOT_FOUND);
    }
}
