package com.walkbook.demo.service;

import com.walkbook.demo.domain.Book;
import com.walkbook.demo.repository.BookRepository;
import com.walkbook.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public void saveBook(Book book){
        bookRepository.save(book);
    }
}
