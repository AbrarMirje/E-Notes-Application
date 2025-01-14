package com.enotes.controllers;

import com.enotes.dto.CategoryDto;
import com.enotes.dto.CategoryResponse;
import com.enotes.entities.Category;
import com.enotes.services.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
@Tag(name = "Category APIs")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        Boolean saveCategory = categoryService.saveCategory(categoryDto);
        if (saveCategory) return new ResponseEntity<>("Category Saved", HttpStatus.CREATED);
        else return new ResponseEntity<>("Category Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-categories")
    public ResponseEntity<?> getAllCategories(){
        List<CategoryDto> categories = categoryService.getCategories();
        if (!CollectionUtils.isEmpty(categories)) return new ResponseEntity<>(categories, HttpStatus.OK);
        else return new ResponseEntity<>("Categories are not available.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/active-categories")
    public ResponseEntity<?> getActiveCategories(){
        List<CategoryResponse> categories = categoryService.getActiveCategories();
        if (!CollectionUtils.isEmpty(categories)) return new ResponseEntity<>(categories, HttpStatus.OK);
        else return new ResponseEntity<>("Categories are not available.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        CategoryDto category = categoryService.getCategory(id);
        if (category != null) {
            return new ResponseEntity<>(category,HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Category not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        Boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) return new ResponseEntity<>("Category deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>("Category not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
