package com.enotes.services.impl;

import com.enotes.entities.Category;
import com.enotes.repositories.ICategoryRepository;
import com.enotes.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public Boolean saveCategory(Category category) {
        category.setIsDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedDate(new Date());
        Category saveCategory = categoryRepository.save(category);
        return !ObjectUtils.isEmpty(saveCategory);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
