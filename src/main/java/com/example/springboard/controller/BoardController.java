package com.example.springboard.controller;

import com.example.springboard.dto.PostListRequest;
import com.example.springboard.global.auth.TokenProvider;
import com.example.springboard.global.response.ResultCode;
import com.example.springboard.global.response.ResultResponse;
import com.example.springboard.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards/{board-id}")
    public ResponseEntity<ResultResponse> getPostList(@PathVariable(value = "board-id") int boardId,
                                                      @RequestHeader(value = TokenProvider.ACCESS_HEADER_STRING, required = false) String accessToken,
                                                      @RequestParam(required = false, defaultValue = "1") int page,
                                                      @RequestParam(required = false, defaultValue = "20") int limit){
        boardService.checkAuth(accessToken, boardId);
        Object data = boardService.getPostList(new PostListRequest(boardId, page, limit));
        ResultResponse response = ResultResponse.of(ResultCode.GET_POSTLIST_SUCCESS, data);
        return new ResponseEntity<>(response, response.getStatus());
    }


}
