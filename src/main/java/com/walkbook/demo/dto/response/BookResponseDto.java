package com.walkbook.demo.dto.response;

import com.walkbook.demo.domain.Book;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {
    private Long id;
    private String isbn;
    private String title;
    private String publisher;
    private String author;
    private String coverUrl;
    private LocalDate publicationTime;
    private Long categoryId;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.publisher = book.getPublisher();
        this.author = book.getAuthor();
        this.coverUrl = book.getCoverUrl();
        this.publicationTime = book.getPublicationTime();
        this.categoryId = book.getCategory().getCategoryId();
        this.description = book.getDescription();
        this.createdAt = book.getCreatedAt();
        this.updatedAt = book.getUpdatedAt();
    }
}