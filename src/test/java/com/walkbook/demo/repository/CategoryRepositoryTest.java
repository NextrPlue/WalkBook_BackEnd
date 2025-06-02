package com.walkbook.demo.repository;

import com.walkbook.demo.domain.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 저장 및 조회")
    void saveAndFindById() {
        // given
        Category saved = categoryRepository.save(
                new Category(null, "문학", "소설, 시, 에세이 등 문학 작품을 포함")
        );

        // when
        Optional<Category> found = categoryRepository.findById(saved.getCategoryId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getCategoryName()).isEqualTo("문학");
    }
}
