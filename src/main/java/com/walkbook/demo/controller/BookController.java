package com.walkbook.demo.controller;

import com.walkbook.demo.domain.Book;
import com.walkbook.demo.domain.Category;
import com.walkbook.demo.dto.request.BookRequestDto;
import com.walkbook.demo.dto.response.ApiResponse;
import com.walkbook.demo.dto.response.BookResponseDto;
import com.walkbook.demo.dto.response.CategoryResponseDto;
import com.walkbook.demo.service.BookService;
import com.walkbook.demo.service.CategoryService;
import com.walkbook.demo.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<BookResponseDto>> createBook(@RequestBody BookRequestDto requestDto){
        Category category = categoryService.getCategory(requestDto.getCategoryId());

        Book book = requestDto.toEntity(category);

        bookService.saveBook(book);
        return ResponseEntity.ok(ResponseUtil.success("도서 등록 성공", bookService.convertToDto(book)));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse<BookResponseDto>> updateBook(@PathVariable Long bookId, @RequestBody BookRequestDto requestDto ){
        Book book = bookService.getBook(bookId);

        book = bookService.updateBook(requestDto, book);

        bookService.saveBook(book);

        return ResponseEntity.ok(ResponseUtil.success("도서 수정 완료", bookService.convertToDto(book)));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok(ResponseUtil.success("도서 삭제 완료", null));
    }



}
