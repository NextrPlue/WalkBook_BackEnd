package com.walkbook.demo.service;

import com.walkbook.demo.domain.Book;
import com.walkbook.demo.dto.request.BookRequestDto;
import com.walkbook.demo.dto.response.BookResponseDto;
import com.walkbook.demo.domain.Book;

import java.util.*;

public interface BookService {
    void saveBook(Book book);
    Book getBook(Long bookId);
    void deleteBook(Long bookId);
    List<BookResponseDto> getAllBooks();


}
