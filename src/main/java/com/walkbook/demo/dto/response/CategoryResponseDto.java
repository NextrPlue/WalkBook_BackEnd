package com.walkbook.demo.dto.response;

import com.walkbook.demo.domain.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

    public CategoryResponseDto(Category category){
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.categoryDescription = category.getCategoryDescription();
    }
}
