package com.walkbook.demo.controller;

import com.walkbook.demo.dto.response.ApiResponse;
import com.walkbook.demo.dto.response.CategoryResponseDto;
import com.walkbook.demo.service.CategoryService;
import com.walkbook.demo.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    // 전체 조회
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAllCategories() {
        List<CategoryResponseDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(
                ResponseUtil.success("카테고리 전체 조회 성공", categories)
        );
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryById(@PathVariable Long id) {
        CategoryResponseDto category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(ResponseUtil.success("카테고리 단건 조회 성공", category));
    }

}
