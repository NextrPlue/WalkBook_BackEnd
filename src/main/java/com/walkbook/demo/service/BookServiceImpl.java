package com.walkbook.demo.service;

import com.walkbook.demo.domain.Book;
import com.walkbook.demo.domain.Category;
import com.walkbook.demo.dto.request.BookRequestDto;
import com.walkbook.demo.dto.response.BookResponseDto;
import com.walkbook.demo.error.BusinessException;
import com.walkbook.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.walkbook.demo.error.ExceptionCode.BOOK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    @Override
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BusinessException(BOOK_NOT_FOUND));
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.findById(bookId).orElseThrow(() -> new BusinessException(BOOK_NOT_FOUND));
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookResponseDto convertToDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .description(book.getDescription())
                .coverUrl(book.getCoverUrl())
                .publicationTime(book.getPublicationTime())
                .categoryId(book.getCategory().getCategoryId())
                .build();
    }

    @Override
    public Book updateBook(BookRequestDto requestDto, Book book) {
        if (requestDto.getIsbn() != null) book.setIsbn(requestDto.getIsbn());
        if (requestDto.getTitle() != null) book.setTitle(requestDto.getTitle());
        if (requestDto.getAuthor() != null) book.setAuthor(requestDto.getAuthor());
        if (requestDto.getPublisher() != null) book.setPublisher(requestDto.getPublisher());
        if (requestDto.getCoverUrl() != null) book.setCoverUrl(requestDto.getCoverUrl());
        if (requestDto.getPublicationTime() != null) book.setPublicationTime(requestDto.getPublicationTime());
        if (requestDto.getDescription() != null) book.setDescription(requestDto.getDescription());

        if (requestDto.getCategoryId() != null) {
            Category category = categoryService.getCategory(requestDto.getCategoryId());
            book.setCategory(category);
        }
        return book;
    }



}
