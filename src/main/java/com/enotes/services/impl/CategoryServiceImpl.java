package com.enotes.services.impl;

import com.enotes.dto.CategoryDto;
import com.enotes.dto.CategoryResponse;
import com.enotes.entities.Category;
import com.enotes.exception.ResourceNotFoundException;
import com.enotes.repositories.ICategoryRepository;
import com.enotes.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {
//        Category category = new Category();
//        category.setName(categoryDto.getName());
//        category.setDescription(categoryDto.getDescription());
//        category.setIsActive(categoryDto.getIsActive());

        Category category = mapper.map(categoryDto, Category.class);

        if (ObjectUtils.isEmpty(category.getId())){
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreatedDate(new Date());
        } else {
            updateCategory(category);
        }

        Category saveCategory = categoryRepository.save(category);
        return !ObjectUtils.isEmpty(saveCategory);
    }

    private void updateCategory(Category category) {
        Optional<Category> findByCategory = categoryRepository.findById(category.getId());
        if (findByCategory.isPresent()){
            Category existCategory = findByCategory.get();
            category.setName(existCategory.getName());
            category.setDescription(existCategory.getDescription());
            category.setCreatedBy(existCategory.getCreatedBy());
            category.setCreatedDate(existCategory.getCreatedDate());
            category.setIsDeleted(existCategory.getIsDeleted());
            category.setUpdatedBy(1);
            category.setUpdatedOn(new Date());
        }
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findByIsDeletedFalse();

        return categories.stream()
                .map(category -> mapper.map(category, CategoryDto.class))
                .toList();
    }

    @Override
    public List<CategoryResponse> getActiveCategories() {
        List<Category> categories = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
        return categories.stream()
                .map(category -> mapper.map(category, CategoryResponse.class))
                .toList();
    }

    @Override
    public CategoryDto getCategory(Integer id) throws ResourceNotFoundException {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Category not found with id: " + id));

        if (!ObjectUtils.isEmpty(category)) {

            if (category.getName() == null){
                throw new IllegalArgumentException("Name is null");
            }

            return mapper.map(category, CategoryDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        Optional<Category> findByCategory = categoryRepository.findById(id);

        if (findByCategory.isPresent()){
            Category category = findByCategory.get();
            category.setIsDeleted(true);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }
}
