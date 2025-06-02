package com.walkbook.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkbook.demo.domain.Book;
import com.walkbook.demo.domain.Category;
import com.walkbook.demo.dto.request.BookRequestDto;
import com.walkbook.demo.dto.response.BookResponseDto;
import com.walkbook.demo.error.BusinessException;
import com.walkbook.demo.error.ExceptionCode;
import com.walkbook.demo.error.GlobalExceptionHandler;
import com.walkbook.demo.service.BookService;
import com.walkbook.demo.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.sql.Timestamp;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
@Import({
        GlobalExceptionHandler.class,
        BookControllerTest.MockConfig.class
})
class BookControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private BookService bookService;
    @Autowired private CategoryService categoryService;
    @Autowired private ObjectMapper objectMapper;

    // 공통으로 사용하는 테스트 객체
    private BookRequestDto requestTestDto;
    private Category category;
    private Book book;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @BeforeEach
    void setUp() {
        requestTestDto = new BookRequestDto(
                "9788932920194",
                "코스모스",
                "사이언스북스",
                "칼 세이건",
                "https://example.com/cover.png",
                LocalDate.of(2001, 11, 5),
                4L,
                "우주와 생명의 기원을 설명한 과학 고전"
        );

        category = new Category(4L, "과학", "과학 카테고리");
        book = requestTestDto.toEntity(category);
        createdAt = Timestamp.valueOf("2025-05-30 05:48:44");
        updatedAt = Timestamp.valueOf("2025-06-02 01:47:18");
    }

    @Test
    @DisplayName("도서 등록 API 테스트")
    void 도서_등록_API_테스트_성공() throws Exception {
        book.setId(161L);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Mockito.when(categoryService.getCategory(requestTestDto.getCategoryId())).thenReturn(category);
        Mockito.when(bookService.convertToDto(Mockito.any()))
                .thenReturn(buildResponseDto(161L, requestTestDto, now, now));

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestTestDto)))
                .andExpectAll(expectSuccessResponse("도서 등록 성공", requestTestDto, 161));
    }

    @Test
    @DisplayName("도서 수정 API 테스트")
    void 도서_수정_API_테스트_성공() throws Exception {
        book.setId(35L);

        Mockito.when(bookService.getBook(35L)).thenReturn(book);
        Mockito.when(bookService.updateBook(requestTestDto, book)).thenReturn(book);
        Mockito.when(bookService.convertToDto(Mockito.any()))
                .thenReturn(buildResponseDto(35L, requestTestDto, createdAt, updatedAt));

        mockMvc.perform(put("/api/books/35")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestTestDto)))
                .andExpectAll(expectSuccessResponse("도서 수정 완료", requestTestDto, 35));
    }

    @Test
    @DisplayName("도서 수정 API 실패 테스트 - 존재하지 않는 ID")
    void 도서_수정_API_테스트_실패() throws Exception {
        Long notFoundId = 999L;

        Mockito.when(bookService.getBook(notFoundId))
                .thenThrow(new BusinessException(ExceptionCode.BOOK_NOT_FOUND));

        mockMvc.perform(put("/api/books/" + notFoundId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestTestDto)))
                .andExpectAll(expectErrorResponse(
                        404, "BOOK_001", "해당되는 id 의 책을 찾을 수 없습니다."));

    }


    @Test
    @DisplayName("도서 삭제 API 테스트")
    void 도서_삭제_API_테스트_성공() throws Exception {
        Mockito.doNothing().when(bookService).deleteBook(35L);

        mockMvc.perform(delete("/api/books/35"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status").value("success"),
                        jsonPath("$.message").value("도서 삭제 완료"),
                        jsonPath("$.data").doesNotExist()
                );
    }

    @Test
    @DisplayName("도서 삭제 API 실패 테스트 - 존재하지 않는 ID")
    void 도서_삭제_API_테스트_실패() throws Exception {
        Long notFoundId = 999L;

        Mockito.doThrow(new BusinessException(ExceptionCode.BOOK_NOT_FOUND))
                .when(bookService).deleteBook(notFoundId);

        mockMvc.perform(delete("/api/books/" + notFoundId))
                .andExpectAll(expectErrorResponse(
                        404, "BOOK_001", "해당되는 id 의 책을 찾을 수 없습니다."));

    }

    private BookResponseDto buildResponseDto(Long id, BookRequestDto dto, Timestamp createdAt, Timestamp updatedAt) {
        return BookResponseDto.builder()
                .id(id)
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .publisher(dto.getPublisher())
                .author(dto.getAuthor())
                .coverUrl(dto.getCoverUrl())
                .publicationTime(dto.getPublicationTime())
                .categoryId(dto.getCategoryId())
                .description(dto.getDescription())
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    // 등록, 수정 API 테스트 시 성공일때 Response 반환 형태
    private ResultMatcher[] expectSuccessResponse(String message, BookRequestDto dto, long id) {
        return new ResultMatcher[] {
                status().isOk(),
                jsonPath("$.status").value("success"),
                jsonPath("$.message").value(message),
                jsonPath("$.data.id").value(id),
                jsonPath("$.data.title").value(dto.getTitle()),
                jsonPath("$.data.author").value(dto.getAuthor()),
                jsonPath("$.data.coverUrl").value(dto.getCoverUrl()),
                jsonPath("$.data.publicationTime").value(dto.getPublicationTime().toString()),
                jsonPath("$.data.categoryId").value(dto.getCategoryId()),
                jsonPath("$.data.description").value(dto.getDescription())
        };
    }

    // 수정, 삭제 API 테스트 시 실패일때 Response 반환 형태
    private ResultMatcher[] expectErrorResponse(int status, String code, String message) {
        return new ResultMatcher[] {
                status().is(status),
                jsonPath("$.status").value(status),
                jsonPath("$.code").value(code),
                jsonPath("$.message").value(message),
                jsonPath("$.errors").doesNotExist(),
                jsonPath("$.reason").doesNotExist()
        };
    }


    // MockBean -> 직접 주입으로 대체
    @TestConfiguration
    static class MockConfig {
        @Bean public BookService bookService() { return Mockito.mock(BookService.class); }
        @Bean public CategoryService categoryService() { return Mockito.mock(CategoryService.class); }
    }
}
