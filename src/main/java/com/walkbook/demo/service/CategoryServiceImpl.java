package com.walkbook.demo.service;

import com.walkbook.demo.domain.Category;
import com.walkbook.demo.dto.response.CategoryResponseDto;
import com.walkbook.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = getCategory(id);
        return convertToDto(category);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));
    }

    private CategoryResponseDto convertToDto(Category category) {
        return CategoryResponseDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }
}