package com.walkbook.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {
    private String isbn;
    private String title;
    private String publisher;
    private String author;
    private String coverUrl;
    private LocalDate publicationTime;
    private Long categoryId;
    private String description;
}
