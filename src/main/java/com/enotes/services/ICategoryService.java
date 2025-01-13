package com.enotes.services;

import com.enotes.entities.Category;

import java.util.List;

public interface ICategoryService {
    Boolean saveCategory(Category category);
    List<Category> getCategories();
}
