package com.enotes.utils;

import com.enotes.dto.CategoryDto;
import com.enotes.exception.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class Validations {

    public void categoryValidation(CategoryDto categoryDto){

        Map<String, Object> error = new LinkedHashMap<>();

        if (ObjectUtils.isEmpty(categoryDto)){
            throw new IllegalArgumentException("Category object shouldn't be null or an empty");
        } else {
            // Validation Name Field
            if (ObjectUtils.isEmpty(categoryDto.getName())){
                error.put("name", "name field is empty or null");
            } else {
                if (categoryDto.getName().length() < 3){
                    error.put("name", "Name length should not be less than 3");
                }
                if (categoryDto.getName().length() > 50){
                    error.put("name", "Name length should not be greater than 50");
                }
            }

            // Validation Description Field
            if (ObjectUtils.isEmpty(categoryDto.getDescription())){
                error.put("description", "description field is empty or null");
            }

            // Validation isActive Field
            if (ObjectUtils.isEmpty(categoryDto.getIsActive())){
                error.put("isActive", "isActive filed is empty or null");
            } else {
                if (categoryDto.getIsActive() != Boolean.TRUE.booleanValue() && categoryDto.getIsActive() != Boolean.FALSE.booleanValue()){
                    error.put("isActive", "Invalid isActive filed");
                }
            }
        }
        if (!error.isEmpty()){
            throw new ValidationException(error);
        }
    }
}
