# spring-board
spring-board CRUD

SpringBoot + MyBatis

### 개발환경
openjdk 17  
springboot 3.2.5  
mybatis 3.0.3


### 요구사항
. 회원 가입 기능
. 회원 로그인 기능  
. 회원이 접근 가능한 게시판, 비회원이 접근 가능한 게시판, 회원 + 비회원 접근 가능한 게시판  
. 게시물 읽기 기능  
. 게시물 댓글 작성 기능(대댓글 제외)  
. 게시물 댓글 리스트  
. 게시물 댓글 수정 기능  
. 게시물 댓글 삭제 기능  
. 게시물 수정 기능  
. 게시물 삭제 기능  
. 게시물 읽기 수 표시
. 게시물 작성 시간 노출  
. 게시물에 작성된 댓글 수  
. DB는 MySQL로 작성 

### 제약사항
- lombok - 미사용
- spring sercurity - 미사용

### 진행사항
- [x] 환경세팅
- [x] DB 테이블 세팅
- 로그인
  - [x] 아이디 중복 체크
  - [x] 회원가입
  - [x] 회원정보 수정
  - [x] 로그인 토큰 구현
- 게시판
  - [x] 게시물 목록 조회
  - [ ] 게시물 작성
  - [ ] 게시물 상세
  - [ ] 게시물 수정
  - [ ] 게시물 삭제
  - [ ] 비회원 게시물 비밀번호 검사
- 댓글


### 개발과정
[개발과정 포스팅 1](https://velog.io/@judobi/series/%EC%8A%A4%ED%94%84%EB%A7%81-%EA%B2%8C%EC%8B%9C%ED%8C%90-API)