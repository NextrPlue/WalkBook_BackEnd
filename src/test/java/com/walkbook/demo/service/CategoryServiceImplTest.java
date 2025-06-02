package com.walkbook.demo.service;

import com.walkbook.demo.domain.Category;
import com.walkbook.demo.dto.response.CategoryResponseDto;
import com.walkbook.demo.error.BusinessException;
import com.walkbook.demo.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.walkbook.demo.error.ExceptionCode.CATEGORY_EMPTY;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("카테고리 전체 조회 성공")
    void getAllCategories() {
        // given
        Category c = new Category(1L, "문학", "소설, 시, 에세이 등 문학 작품을 포함");
        given(categoryRepository.findAll()).willReturn(List.of(c));

        // when
        List<CategoryResponseDto> result = categoryService.getAllCategories();

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCategoryName()).isEqualTo("문학");
    }

    @Test
    @DisplayName("카테고리 조회 시 존재하지 않을 경우 예외 발생")
    void getCategoryNotFound() {
        // given
        Long id = 999L;
        given(categoryRepository.findById(id)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> categoryService.getCategory(id))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(CATEGORY_EMPTY.getMessage());
    }
}
