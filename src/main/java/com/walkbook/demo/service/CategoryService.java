package com.walkbook.demo.service;

import com.walkbook.demo.domain.Category;
import com.walkbook.demo.dto.response.CategoryResponseDto;

import java.util.*;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryById(Long id);
    Category getCategory(Long id);
}
