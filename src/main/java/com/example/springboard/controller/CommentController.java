package com.example.springboard.controller;

import com.example.springboard.dto.request.CommentDeleteRequest;
import com.example.springboard.dto.request.CommentRequest;
import com.example.springboard.dto.request.CommentListRequest;
import com.example.springboard.dto.response.CommentInsertResponse;
import com.example.springboard.dto.response.CommentListResponse;
import com.example.springboard.global.auth.TokenProvider;
import com.example.springboard.global.error.ErrorCode;
import com.example.springboard.global.error.exception.ApiException;
import com.example.springboard.global.response.ResultCode;
import com.example.springboard.global.response.ResultResponse;
import com.example.springboard.service.BoardService;
import com.example.springboard.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;

    public CommentController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }


    @GetMapping("/boards/{board-id}/posts/{post-no}/comments")
    public ResponseEntity<ResultResponse> getCommentList(@PathVariable(value = "board-id") int boardId,
                                                         @PathVariable(value = "post-no") int postNo,
                                                         @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                         @RequestParam(required = false, defaultValue = "1") int page,
                                                         @RequestParam(required = false, defaultValue = "20") int limit){
        boardService.checkAuth(accessToken, boardId);
        CommentListResponse data = commentService.getCommentList(new CommentListRequest(boardId, postNo, page, limit));
        ResultResponse response = ResultResponse.of(ResultCode.GET_COMMENTLIST_SUCCESS, data);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/boards/{board-id}/comments")
    public ResponseEntity<ResultResponse> insertComment(@PathVariable(value = "board-id") int boardId,
                                                        @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                        @Valid @RequestBody CommentRequest request){
        Integer uid = boardService.checkAuth(accessToken, boardId);
        request.setUid(uid);
        CommentInsertResponse data = commentService.insertComment(request);
        ResultResponse response = ResultResponse.of(ResultCode.INSERT_COMMENT_SUCCESS, data);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/boards/{board-id}/comments")
    public ResponseEntity<ResultResponse> updateComment(@PathVariable(value = "board-id") int boardId,
                                                        @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                        @Valid @RequestBody CommentRequest request){
        // url과 요청한 댓글의 게시판 정보가 다를 경우
        if(boardId != request.getBoardId()){
            throw new ApiException(ErrorCode.COMMENT_URL_FAIL);
        }

        Integer uid = boardService.checkAuth(accessToken, boardId);
        request.setUid(uid);
        commentService.updateComment(request);
        ResultResponse response = ResultResponse.of(ResultCode.UPDATE_COMMENT_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/boards/{board-id}/comments")
    public ResponseEntity<ResultResponse> deleteComment(@PathVariable(value = "board-id") int boardId,
                                                        @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                        @Valid @RequestBody CommentDeleteRequest request){
        // url과 요청한 댓글의 게시판 정보가 다를 경우
        if(boardId != request.getBoardId()){
            throw new ApiException(ErrorCode.COMMENT_URL_FAIL);
        }

        Integer uid = boardService.checkAuth(accessToken, boardId);
        request.setUid(uid);
        commentService.deleteComment(request);
        ResultResponse response = ResultResponse.of(ResultCode.DELETE_COMMENT_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
