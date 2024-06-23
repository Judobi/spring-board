package com.example.springboard.controller;

import com.example.springboard.dto.request.PostDeleteRequest;
import com.example.springboard.dto.request.PostListRequest;
import com.example.springboard.dto.request.PostPwCheckRequest;
import com.example.springboard.dto.request.PostRequest;
import com.example.springboard.dto.response.PostInsertResponse;
import com.example.springboard.dto.response.PostResponse;
import com.example.springboard.global.auth.TokenProvider;
import com.example.springboard.global.response.ResultCode;
import com.example.springboard.global.response.ResultResponse;
import com.example.springboard.service.BoardService;
import com.example.springboard.vo.Post;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 게시글 리스트 조회
     * @param boardId 게시판 카테고리 번호
     * @param accessToken 로그인 토큰(액세스 토큰)
     * @param page 불러올 페이지
     * @param limit 한 번에 불러올 게시글의 개수
     * @return 게시글 목록, 정렬은 기본 최신순으로
     */
    @GetMapping("/boards/{board-id}")
    public ResponseEntity<ResultResponse> getPostList(@PathVariable(value = "board-id") int boardId,
                                                      @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                      @RequestParam(required = false, defaultValue = "1") int page,
                                                      @RequestParam(required = false, defaultValue = "20") int limit){
        boardService.checkAuth(accessToken, boardId);
        List<PostResponse> data = boardService.getPostList(new PostListRequest(boardId, page, limit));
        ResultResponse response = ResultResponse.of(ResultCode.GET_POSTLIST_SUCCESS, data);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 게시글 등록
     * @param boardId 게시판 번호
     * @param accessToken 액세스 토큰 (회원인 경우)
     * @param request 등록할 게시글
     * @return 등록한 게시글 정보
     */
    @PostMapping("/boards/{board-id}/posts/")
    public ResponseEntity<ResultResponse> insertPost(@PathVariable(value = "board-id") int boardId,
                                                     @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                     @Valid @RequestBody PostRequest request
                                                     ){
        Integer uid = boardService.checkAuth(accessToken, boardId);
        Post post = new Post(uid, boardId, request);
        PostInsertResponse data = boardService.insertPost(post);
        ResultResponse response = ResultResponse.of(ResultCode.INSERT_POST_SUCCESS, data);
        return new ResponseEntity<>(response, response.getStatus());

    }

    /**
     * 게시글 상세조회
     * @param boardId 게시판 번호
     * @param accessToken 로그인 토큰
     * @param postNo 조회할 게시글 번호
     * @return 게시글 내용
     */
    @GetMapping("/boards/{board-id}/posts/{post-no}")
    public ResponseEntity<ResultResponse> getPost(@PathVariable(value = "board-id") int boardId,
                                                  @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                  @PathVariable(value = "post-no") int postNo){
        boardService.checkAuth(accessToken, boardId);
        PostResponse post = boardService.getPostDetail(boardId, postNo);
        ResultResponse response = ResultResponse.of(ResultCode.GET_POST_SUUCCESS, post);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 게시글 수정
     * @param boardId 게시판 카테고리 번호
     * @param postNo 수정할 게시글 번호
     * @param accessToken 로그인 정보 토큰
     * @param request 수정할 게시글 데이터 - (제목, 글내용)
     * @return 성공시 수정완료 응답.
     */
    @PutMapping("/boards/{board-id}/posts/{post-no}")
    public ResponseEntity<ResultResponse> updatePost(@PathVariable(value = "board-id") int boardId,
                                                     @PathVariable(value = "post-no") int postNo,
                                                     @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                     @Valid @RequestBody PostRequest request){
        Integer uid = boardService.checkAuth(accessToken, boardId);
        Post post = new Post(uid, boardId, postNo, request);
        boardService.updatePost(post);
        ResultResponse response = ResultResponse.of(ResultCode.UPDATE_POST_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }


    /**
     * 게시글 삭제 요청.
     * @param boardId 게시판 번호
     * @param accessToken 로그인 토큰
     * @param request 삭제할 게시글 번호, 패스워드(비회원 게시글)
     */
    @DeleteMapping("/boards/{board-id}/posts/{post-no}")
    public ResponseEntity<ResultResponse> deletePost(@PathVariable(value = "board-id") int boardId,
                                                     @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                     @Valid @RequestBody PostDeleteRequest request){
        Integer uid = boardService.checkAuth(accessToken, boardId);
        request.setUid(uid);
        boardService.deletePost(request);
        ResultResponse response = ResultResponse.of(ResultCode.DELETE_POST_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }


    /**
     * 게시글 비밀번호 확인 (비회원 게시글)
     * @param request 게시글 비밀번호
     */
    @PostMapping("/boards/{board-id}/posts/{post-no}/check-pw")
    public ResponseEntity<ResultResponse> checkPW(@Valid @RequestBody PostPwCheckRequest request){
        boardService.checkPw(request);
        ResultResponse response = ResultResponse.of(ResultCode.CHECK_POSTPW_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
