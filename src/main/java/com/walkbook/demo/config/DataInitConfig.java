package com.walkbook.demo.config;

import com.walkbook.demo.domain.Category;
import com.walkbook.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitConfig {

    private final CategoryRepository categoryRepository;

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
}
