package com.walkbook.demo.error;


import lombok.Getter;

@Getter
public enum ExceptionCode {
    CATEGORY_EMPTY(404, "CATEGORY_001", "카테고리 DB가 비어져있습니다."),
    BOOK_NOT_FOUND(404, "BOOK_001", "해당되는 id 의 책을 찾을 수 없습니다."),

    NULL_POINT_ERROR(404, "G010", "NullPointerException 발생"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_ERROR(404, "G011", "Validation Exception 발생");


    // 1. status = 날려줄 상태코드
    // 2. code = 해당 오류가 어느부분과 관련있는지 카테고리화 해주는 코드. 예외 원인 식별하기 편하기에 추가
    // 3. message = 발생한 예외에 대한 설명.

    private final int status;
    private final String code;
    private final String message;

    ExceptionCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}