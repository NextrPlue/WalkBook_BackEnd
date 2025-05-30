package com.walkbook.demo.service;

import com.walkbook.demo.dto.response.CategoryResponseDto;

import java.util.*;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryById(Long id);
}
