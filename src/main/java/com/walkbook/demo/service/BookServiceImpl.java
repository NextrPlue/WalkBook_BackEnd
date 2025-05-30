package com.walkbook.demo.service;

import com.walkbook.demo.domain.Book;
import com.walkbook.demo.error.BusinessException;
import com.walkbook.demo.repository.BookRepository;
import com.walkbook.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.walkbook.demo.error.ExceptionCode.BOOK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    @Override
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BusinessException(BOOK_NOT_FOUND));
    }
}
