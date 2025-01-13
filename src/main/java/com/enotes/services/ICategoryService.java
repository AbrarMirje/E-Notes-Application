package com.enotes.services;

import com.enotes.dto.CategoryDto;
import com.enotes.dto.CategoryResponse;
import com.enotes.entities.Category;

import java.util.List;

public interface ICategoryService {
    Boolean saveCategory(CategoryDto categoryDto);
    List<CategoryDto> getCategories();

    List<CategoryResponse> getActiveCategories();
}
