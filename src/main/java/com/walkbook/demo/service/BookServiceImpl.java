package com.walkbook.demo.service;

import com.walkbook.demo.domain.Book;
import com.walkbook.demo.error.BusinessException;
import com.walkbook.demo.repository.BookRepository;
import com.walkbook.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.walkbook.demo.dto.response.BookResponseDto;
import com.walkbook.demo.domain.Book;
import com.walkbook.demo.dto.request.BookRequestDto;
import java.util.stream.Collectors;
import java.util.List;


import static com.walkbook.demo.error.ExceptionCode.BOOK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BookResponseDto convertToDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationTime(book.getPublicationTime())
                .description(book.getDescription())
                .coverUrl(book.getCoverUrl())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .categoryId(book.getCategory() != null ? book.getCategory().getCategoryId() : null)
                .build();
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
}
