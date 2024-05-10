package com.example.springboard.service.impl;

import com.example.springboard.dto.PostListRequest;
import com.example.springboard.dto.PostListResponse;
import com.example.springboard.global.auth.AccessType;
import com.example.springboard.global.auth.TokenProvider;
import com.example.springboard.global.error.ErrorCode;
import com.example.springboard.global.error.exception.ApiException;
import com.example.springboard.mapper.BoardMapper;
import com.example.springboard.service.BoardService;
import com.example.springboard.vo.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final Logger log = LoggerFactory.getLogger(BoardService.class);
    private final BoardMapper boardMapper;
    private final TokenProvider tokenProvider;

    public BoardServiceImpl(BoardMapper boardMapper, TokenProvider tokenProvider) {
        this.boardMapper = boardMapper;
        this.tokenProvider = tokenProvider;
    }

    /**
     * 게시판 접근권한 확인
     * @param accessToken 토큰
     * @param boardId 게시판 번호
     */
    public void checkAuth(String accessToken, int boardId) {
        log.info("checkAuth : boardId - {}, accessToken - {}", boardId, accessToken);
        int boardAccessId = boardMapper.getBoardAccessId(boardId);
        log.info("board accesId = {}", boardAccessId);

        // 회원 게시판
        if(boardAccessId == AccessType.ACCESS_TYPE_USER){
            // 비회원이 접근하려는 경우
            if (accessToken == null) {
                throw new ApiException(ErrorCode.BOARD_AUTH_USER_FAIL);
            }
            tokenProvider.checkAccessToken(accessToken); // access 토큰 유효성 확인

        } else if(boardAccessId == AccessType.ACCESS_TYPE_NON_USER){ // 비회원 게시판
            // 회원이 접근하려는 경우
            if (accessToken != null){
                throw new ApiException(ErrorCode.BOARD_AUTH_NONUSER_FAIL);
            }

        } else{ // 접근 불가 게시판
            throw new ApiException(ErrorCode.BOARD_AUTH_FORBIDDEN);
        }
    }

    public List<PostListResponse> getPostList(PostListRequest request) {
        log.info(request.toString());
        return boardMapper.getPostList(request);
    }
}
