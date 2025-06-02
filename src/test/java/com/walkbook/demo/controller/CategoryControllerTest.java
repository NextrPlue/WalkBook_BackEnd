package com.walkbook.demo.controller;

import com.walkbook.demo.dto.response.CategoryResponseDto;
import com.walkbook.demo.service.CategoryService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
@Import(CategoryControllerTest.MockConfig.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public CategoryService categoryService() {
            return Mockito.mock(CategoryService.class);
        }
    }

    @Test
    @DisplayName("카테고리 전체 조회 성공")
    void getAllCategories() throws Exception {
        List<CategoryResponseDto> list = List.of(
                new CategoryResponseDto(1L, "문학", "문학 설명")
        );

        given(categoryService.getAllCategories()).willReturn(list);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("카테고리 전체 조회 성공"))
                .andExpect(jsonPath("$.data[0].categoryName").value("문학"));
    }

    @Test
    @DisplayName("카테고리 단건 조회 성공")
    void getCategoryById() throws Exception {
        Long id = 1L;
        CategoryResponseDto dto = new CategoryResponseDto(id, "문학", "문학 설명");

        given(categoryService.getCategoryById(id)).willReturn(dto);

        mockMvc.perform(get("/api/categories/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("카테고리 단건 조회 성공"))
                .andExpect(jsonPath("$.data.categoryName").value("문학"));
    }
}