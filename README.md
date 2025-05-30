# WalkBook 도서 관리 시스템 - Backend

> 목차
> - [📌 프로젝트 소개](#프로젝트-소개)
> - [👩‍👩‍👧‍👧 팀원 소개](#팀원-소개)
> - [✏️ 주요 기능](#주요-기능)
> - [🔗 링크 모음](#링크-모음)
> - [📡 API 명세](#api-명세)
> - [🚩 시작 가이드](#시작-가이드)
> - [🖥️ 기술 스택](#기술-스택)
> - [📁 프로젝트 구조](#프로젝트-구조)

<br>

## 프로젝트 소개

### 개발 동기 및 목적

WalkBook은 **AI 기반 도서 커버 이미지 생성 기능을 포함한 도서 관리 시스템**입니다.

특히 **AI 기술을 활용한 커버 이미지 자동 생성 기능**을 통해 사용자는 도서의 내용과 분위기에 맞는 맞춤형 커버 이미지를 손쉽게 생성할 수 있습니다.

저희 플랫폼은 **React 기반의 프론트엔드**와 **Spring Boot 기반의 백엔드**, 그리고 **AI 이미지 생성 API**를 통합하여 도서 관리 서비스를 제공하며, **실시간 검색**과 **카테고리별 필터링**, **AI 커버 생성**으로 사용자 경험을 강화했습니다.

<br>

### 서비스 소개

> AI로 나만의 도서 커버를 만드는 스마트 도서 관리 플랫폼 'WalkBook'

1. ✏️ **도서 관리 API**
    - 도서 등록, 조회, 수정, 삭제 기능을 RESTful API로 제공해요.
    - JPA를 통한 안정적인 데이터 영속성 관리를 제공해요.

2. 🗂️ **카테고리 관리 API**
    - 도서 카테고리 목록 조회 및 단건 조회 API를 제공해요.
    - 카테고리별 도서 분류를 위한 연관관계 매핑을 지원해요.
    - 소설, IT 전문서적, 에세이, 자기계발, 경제, 역사 등 다양한 카테고리를 관리해요.

3. 📋 **데이터 검증 및 예외 처리**
    - 체계적인 예외 처리와 에러 응답 구조를 제공해요.
    - 비즈니스 로직 검증을 통한 데이터 무결성을 보장해요.
    - 일관된 API 응답 포맷으로 프론트엔드와의 원활한 통신을 지원해요.

4. 🔄 **초기 데이터 설정**
    - 애플리케이션 시작 시 기본 카테고리 데이터를 자동 생성해요.
    - 개발 및 테스트를 위한 샘플 데이터 제공 기능을 포함해요.

5. 🌐 **CORS 설정**
    - React 프론트엔드와의 통신을 위한 CORS 정책을 설정해요.
    - 개발 환경에서의 원활한 API 호출을 지원해요.

<br>

### 개발 기간

2025.05.29 ~ 2025.06.02

<br>

## 팀원 소개

|        | 이성훈                                                      | 한다현                                                   | 장준혁                                                       | 배소정                                                   |
|--------|----------------------------------------------------------|-------------------------------------------------------|-----------------------------------------------------------|-------------------------------------------------------|
| 역할     | 조장, PM, 풀스택                                              | 서기, 백엔드 개발                                            | 백엔드 개발                                                    | 백엔드 개발                                                |
| E-Mail | p.plue1881@gmail.com                                     | dahyeonhan4529@gmail.com                              | kalina01255@gmail.com                                     | bsj9278@gmail.com                                     |
| GitHub | https://github.com/NextrPlue                             | https://github.com/dahxxn                             | https://github.com/angrynison                             | https://github.com/BaeSJ1                             |
|        | <img src="https://github.com/NextrPlue.png" width=100px> | <img src="https://github.com/dahxxn.png" width=100px> | <img src="https://github.com/angrynison.png" width=100px> | <img src="https://github.com/BaeSJ1.png" width=100px> |

<br>

## 주요 기능

> - 도서 CRUD API
> - 카테고리 관리 API
> - 자동 타임스탬프 관리
> - CORS 설정
> - 초기 데이터 설정

<table>
  <tr>
    <th>기능</th>
    <th>설명</th>
  </tr>
  <tr>
    <td><b>도서 등록 API</b></td>
    <td>새로운 도서 정보를 데이터베이스에 저장합니다.<br>
        ISBN, 제목, 저자, 출판사, 설명, 출간일, 카테고리 정보를 포함합니다.<br>
        AI 생성 커버 이미지 URL도 함께 저장할 수 있습니다.</td>
  </tr>
  <tr>
    <td><b>도서 조회 API</b></td>
    <td>등록된 도서의 상세 정보를 조회합니다.<br>
        도서 ID를 통한 단건 조회 기능을 제공합니다.<br>
        카테고리 정보와 함께 연관된 데이터를 반환합니다.</td>
  </tr>
  <tr>
    <td><b>도서 수정 API</b></td>
    <td>기존 도서 정보를 부분적으로 수정할 수 있습니다.<br>
        null이 아닌 필드만 업데이트하는 부분 수정을 지원합니다.<br>
        카테고리 변경 및 커버 이미지 URL 업데이트가 가능합니다.</td>
  </tr>
  <tr>
    <td><b>도서 삭제 API</b></td>
    <td>도서 ID를 통해 해당 도서를 데이터베이스에서 삭제합니다.<br>
        삭제 전 존재 여부를 확인하여 안전한 삭제를 보장합니다.<br>
        연관된 카테고리는 유지되어 데이터 무결성을 보장합니다.</td>
  </tr>
  <tr>
    <td><b>카테고리 전체 조회 API</b></td>
    <td>시스템에 등록된 모든 카테고리 목록을 반환합니다.<br>
        카테고리 ID, 이름, 설명 정보를 포함합니다.<br>
        프론트엔드의 카테고리 필터링에 사용됩니다.</td>
  </tr>
  <tr>
    <td><b>카테고리 단건 조회 API</b></td>
    <td>특정 카테고리 ID를 통해 해당 카테고리의 상세 정보를 조회합니다.<br>
        카테고리별 도서 등록 시 유효성 검증에 사용됩니다.<br>
        존재하지 않는 카테고리 접근 시 적절한 오류 응답을 제공합니다.</td>
  </tr>
  <tr>
    <td><b>예외 처리 및 에러 응답</b></td>
    <td>비즈니스 로직 오류에 대한 체계적인 예외 처리를 제공합니다.<br>
        일관된 에러 응답 형식으로 프론트엔드의 오류 처리를 지원합니다.<br>
        도서 또는 카테고리 미존재 시 적절한 HTTP 상태 코드를 반환합니다.</td>
  </tr>
  <tr>
    <td><b>자동 타임스탬프 관리</b></td>
    <td>엔티티 생성 및 수정 시간을 자동으로 관리합니다.<br>
        @CreationTimestamp와 @UpdateTimestamp를 활용합니다.<br>
        데이터 변경 이력 추적에 활용할 수 있습니다.</td>
  </tr>
</table>

<br>

## Backend 개발 주안점

### 📌 계층형 아키텍처 구조

> **Controller, Service, Repository** 계층을 명확히 분리하여 관심사의 분리와 유지보수성을 향상시켰습니다.

### 📌 RESTful API 설계

> **HTTP 메서드별 적절한 용도 사용**과 **리소스 중심의 URL 설계**로 직관적인 API를 제공합니다.

### 📌 데이터 검증 및 예외 처리

> **GlobalExceptionHandler**를 통한 전역 예외 처리와 **일관된 응답 형식**으로 안정적인 API를 제공합니다.

### 📌 JPA 연관관계 매핑

> **@ManyToOne** 관계를 통한 도서-카테고리 연관관계 매핑으로 정규화된 데이터 구조를 구현했습니다.

<br>

## 링크 모음

|                                                기획                                                |                                                     디자인                                                     |                             개발                              |
|:------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------:|
|             [회의록](https://www.notion.so/4-22-20238a2a7fbd80aa88c6e9e973582ba5?pvs=4)             | [와이어프레임](https://www.figma.com/design/dttb8USej1RKlHaSpfRAXI/Untitled?node-id=0-1&p=f&t=bbCUR5x0bQlO6t7g-0) | [프론트엔드 깃허브](https://github.com/NextrPlue/WalkBook_FrontEnd) |
| [API 문서](https://vine-drizzle-46a.notion.site/202717c69f8c80138b17fdbcbf9e77e6?source=copy_link) |                                                                                                             |  [백엔드 깃허브](https://github.com/NextrPlue/WalkBook_BackEnd)   |

<br>

## API 명세

### Category API

#### 카테고리 전체 조회
```http
GET /api/categories
```

**Response**
```json
{
  "message": "카테고리 전체 조회 성공",
  "data": [
    {
      "categoryId": 1,
      "categoryName": "소설",
      "categoryDescription": "문학 소설 및 장르 소설"
    }
  ]
}
```

#### 카테고리 단건 조회
```http
GET /api/categories/{categoryId}
```

**Response**
```json
{
  "message": "카테고리 단건 조회 성공",
  "data": {
    "categoryId": 1,
    "categoryName": "소설",
    "categoryDescription": "문학 소설 및 장르 소설"
  }
}
```

### Book API

#### 도서 등록
```http
POST /api/books
```

**Request Body**
```json
{
  "isbn": "978-89-123456-78-9",
  "title": "도서 제목",
  "author": "저자명",
  "publisher": "출판사",
  "description": "도서 설명",
  "coverUrl": "http://example.com/cover.jpg",
  "publicationTime": "2024-01-01",
  "categoryId": 1
}
```

**Response**
```json
{
  "message": "도서 등록 성공",
  "data": {
    "id": 1,
    "isbn": "978-89-123456-78-9",
    "title": "도서 제목",
    "author": "저자명",
    "publisher": "출판사",
    "description": "도서 설명",
    "coverUrl": "http://example.com/cover.jpg",
    "publicationTime": "2024-01-01",
    "categoryId": 1,
    "categoryName": "소설"
  }
}
```

#### 도서 수정
```http
PUT /api/books/{bookId}
```

**Request Body** (부분 수정 지원)
```json
{
  "title": "수정된 도서 제목",
  "coverUrl": "http://example.com/new-cover.jpg"
}
```

**Response**
```json
{
  "message": "도서 수정 완료",
  "data": {
    "id": 1,
    "title": "수정된 도서 제목",
    "coverUrl": "http://example.com/new-cover.jpg"
  }
}
```

#### 도서 삭제
```http
DELETE /api/books/{bookId}
```

**Response**
```json
{
  "message": "도서 삭제 완료",
  "data": null
}
```

<br>

## 시작 가이드

> Requirements: Java 17+, Gradle 8.14+
>
> 프론트엔드와 연동하기 위해 CORS 설정이 포함되어 있습니다.
>
> H2 인메모리 데이터베이스를 사용하므로 별도의 DB 설치가 필요하지 않습니다.

### 1. 프로젝트 클론
```bash
git clone https://github.com/NextrPlue/WalkBook_BackEnd.git
cd WalkBook_BackEnd
```

### 2. 프로젝트 빌드
```bash
./gradlew build
```

### 3. 애플리케이션 실행
```bash
./gradlew bootRun
```

또는

```bash
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

### 4. API 테스트
애플리케이션이 실행되면 다음 URL에서 API를 테스트할 수 있습니다:
- Base URL: `http://localhost:8080`
- H2 콘솔: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:~/books`
  - Username: `sa`
  - Password: `1234`

<br>

## 기술 스택

### Backend Framework
- **Spring Boot 3.5.0**: 애플리케이션 프레임워크
- **Spring Data JPA**: 데이터 액세스 레이어
- **Spring Web**: RESTful API 구현

### Database
- **H2 Database**: 인메모리 데이터베이스
- **JPA/Hibernate**: ORM 프레임워크

### Development Tools
- **Java 17**: 프로그래밍 언어
- **Gradle 8.14**: 빌드 도구
- **Lombok**: 코드 생성 자동화
