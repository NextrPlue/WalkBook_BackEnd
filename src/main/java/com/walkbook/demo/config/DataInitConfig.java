package com.walkbook.demo.config;

import com.walkbook.demo.domain.Book;
import com.walkbook.demo.domain.Category;
import com.walkbook.demo.error.BusinessException;
import com.walkbook.demo.error.ExceptionCode;
import com.walkbook.demo.repository.BookRepository;
import com.walkbook.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitConfig {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Bean
    public ApplicationRunner initCategoryData() {
        return args -> {
            if (categoryRepository.count() == 0) { // 이미 데이터 있으면 생략
                List<Category> categories = List.of(
                        Category.builder().categoryName("문학").categoryDescription("소설, 시, 에세이 등 문학 작품을 포함").build(),
                        Category.builder().categoryName("자기계발").categoryDescription("동기부여, 습관 형성, 시간 관리 등 자기 발전을 위한 도서").build(),
                        Category.builder().categoryName("인문").categoryDescription("철학, 역사, 사회 등 인간과 사회를 다루는 도서").build(),
                        Category.builder().categoryName("과학").categoryDescription("생명과학, 물리, 화학, 수학 등 과학 전반을 다룸").build(),
                        Category.builder().categoryName("기술/IT").categoryDescription("프로그래밍, 인공지능, 데이터베이스 등 기술 관련 도서").build(),
                        Category.builder().categoryName("예술").categoryDescription("미술, 음악, 사진, 디자인 등 예술 관련 도서").build(),
                        Category.builder().categoryName("아동/청소년").categoryDescription("어린이와 청소년을 위한 그림책, 동화, 학습만화 등").build(),
                        Category.builder().categoryName("경제/경영").categoryDescription("경제 이론, 기업 경영, 투자 전략 관련 도서").build(),
                        Category.builder().categoryName("건강/취미").categoryDescription("운동, 요리, 여행, 원예 등 건강과 여가생활 관련 도서").build(),
                        Category.builder().categoryName("종교").categoryDescription("기독교, 불교, 이슬람 등 종교 관련 도서").build()
                );

                categoryRepository.saveAll(categories);
            }
        };
    }

    @Bean
    public ApplicationRunner initBookData(){
        return args -> {
            if (bookRepository.count() == 0) {
                Category literature = categoryRepository.findById(1L).orElse(null);
                Category selfDev = categoryRepository.findById(2L).orElse(null);
                Category science = categoryRepository.findById(4L).orElse(null);

                if (literature == null || selfDev == null || science == null) {
                    throw new BusinessException(ExceptionCode.CATEGORY_EMPTY);
                }

                List<Book> books = List.of(
                        Book.builder()
                                .isbn("9788998139766")
                                .title("나미야 잡화점의 기적")
                                .author("히가시노 게이고")
                                .publisher("현대문학")
                                .description("편지를 통해 고민을 상담하는 신비한 잡화점 이야기")
                                .coverUrl("https://example.com/cover1.jpg")
                                .publicationTime(LocalDate.of(2012, 3, 15))
                                .category(literature)
                                .build(),

                        Book.builder()
                                .isbn("9788901249485")
                                .title("아주 작은 습관의 힘")
                                .author("제임스 클리어")
                                .publisher("비즈니스북스")
                                .description("습관 형성과 변화의 과학을 설명한 자기계발서")
                                .coverUrl("https://example.com/cover2.jpg")
                                .publicationTime(LocalDate.of(2019, 1, 10))
                                .category(selfDev)
                                .build(),

                        Book.builder()
                                .isbn("9788932920194")
                                .title("코스모스")
                                .author("칼 세이건")
                                .publisher("사이언스북스")
                                .description("우주와 생명의 기원을 설명한 과학 고전")
                                .coverUrl("https://example.com/cover3.jpg")
                                .publicationTime(LocalDate.of(2001, 11, 5))
                                .category(science)
                                .build()
                );

                bookRepository.saveAll(books);
            }
        };
    }

}
