package com.walkbook.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkbook.demo.domain.Category;
import com.walkbook.demo.dto.response.CategoryResponseDto;
import com.walkbook.demo.error.BusinessException;
import com.walkbook.demo.error.ExceptionCode;
import com.walkbook.demo.error.GlobalExceptionHandler;
import com.walkbook.demo.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@Import({
        GlobalExceptionHandler.class,
        CategoryControllerTest.MockConfig.class
})
public class CategoryControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private CategoryService categoryService;
    @Autowired private ObjectMapper objectMapper;

    private Category category;
    private CategoryResponseDto responseDto;

    @BeforeEach
    void setUp() {
        category = new Category(1L, "문학", "소설, 시, 에세이 등 문학 작품을 포함");
        responseDto = new CategoryResponseDto(category);
    }

    @Test
    @DisplayName("카테고리 전체 조회 API 테스트")
    void 카테고리_전체_조회_API_테스트() throws Exception {
        Mockito.when(categoryService.getAllCategories()).thenReturn(List.of(responseDto));

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("카테고리 전체 조회 성공"))
                .andExpect(jsonPath("$.data[0].categoryId").value(responseDto.getCategoryId()))
                .andExpect(jsonPath("$.data[0].categoryName").value(responseDto.getCategoryName()))
                .andExpect(jsonPath("$.data[0].categoryDescription").value(responseDto.getCategoryDescription()));
    }

    @Test
    @DisplayName("카테고리 단건 조회 API 테스트")
    void 카테고리_단건_조회_API_테스트() throws Exception {
        Mockito.when(categoryService.getCategoryById(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("카테고리 단건 조회 성공"))
                .andExpect(jsonPath("$.data.categoryId").value(responseDto.getCategoryId()))
                .andExpect(jsonPath("$.data.categoryName").value(responseDto.getCategoryName()));
    }

    @Test
    @DisplayName("카테고리 단건 조회 실패 테스트 - 존재하지 않는 ID")
    void 카테고리_단건_조회_실패_테스트() throws Exception {
        Mockito.when(categoryService.getCategoryById(999L))
                .thenThrow(new BusinessException(ExceptionCode.CATEGORY_EMPTY));

        mockMvc.perform(get("/api/categories/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("CATEGORY_001"))
                .andExpect(jsonPath("$.message").value("카테고리 DB가 비어져있습니다."));
    }

    @TestConfiguration
    static class MockConfig {
        @Bean public CategoryService categoryService() {
            return Mockito.mock(CategoryService.class);
        }
    }
}