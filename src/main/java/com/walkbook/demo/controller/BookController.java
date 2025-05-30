package com.walkbook.demo.controller;

import com.walkbook.demo.domain.Book;
import com.walkbook.demo.domain.Category;
import com.walkbook.demo.dto.request.BookRequestDto;
import com.walkbook.demo.dto.response.ApiResponse;
import com.walkbook.demo.dto.response.BookResponseDto;
import com.walkbook.demo.service.BookService;
import com.walkbook.demo.service.CategoryService;
import com.walkbook.demo.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<BookResponseDto>> createBook(@RequestBody BookRequestDto requestDto){
        Category category = categoryService.getCategory(requestDto.getCategoryId());

        Book book = new Book();
        book.setIsbn(requestDto.getIsbn());
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setPublisher(requestDto.getPublisher());
        book.setDescription(requestDto.getDescription());
        book.setCoverUrl(requestDto.getCoverUrl());
        book.setPublicationTime(requestDto.getPublicationTime());
        book.setCategory(category);

        bookService.saveBook(book);
        return ResponseEntity.ok(ResponseUtil.success("도서 등록 성공", new BookResponseDto(book)));
    }

}
