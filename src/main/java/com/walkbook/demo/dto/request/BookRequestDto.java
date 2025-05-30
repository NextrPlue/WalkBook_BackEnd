package com.walkbook.demo.dto.request;

import com.walkbook.demo.domain.Book;
import com.walkbook.demo.domain.Category;
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

    public Book toEntity(Category category) {
        return Book.builder()
                .isbn(this.isbn)
                .title(this.title)
                .author(this.author)
                .publisher(this.publisher)
                .description(this.description)
                .coverUrl(this.coverUrl)
                .publicationTime(this.publicationTime)
                .category(category)
                .build();
    }
}
