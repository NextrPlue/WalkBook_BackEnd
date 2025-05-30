package com.walkbook.demo.service;

import com.walkbook.demo.domain.Book;

public interface BookService {
    void saveBook(Book book);
    Book getBook(Long bookId);
    void deleteBook(Long bookId);
}
