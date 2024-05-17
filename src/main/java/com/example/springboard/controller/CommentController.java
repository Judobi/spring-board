package com.example.springboard.controller;

import com.example.springboard.dto.request.CommentListRequest;
import com.example.springboard.dto.response.CommentListResponse;
import com.example.springboard.dto.response.CommentResponse;
import com.example.springboard.global.auth.TokenProvider;
import com.example.springboard.global.response.ResultCode;
import com.example.springboard.global.response.ResultResponse;
import com.example.springboard.service.BoardService;
import com.example.springboard.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
