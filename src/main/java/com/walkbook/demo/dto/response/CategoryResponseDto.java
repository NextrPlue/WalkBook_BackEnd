package com.walkbook.demo.dto.response;

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
}
