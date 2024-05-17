package com.example.springboard.service.impl;

import com.example.springboard.dto.request.PostDeleteRequest;
import com.example.springboard.dto.request.PostListRequest;
import com.example.springboard.dto.request.PostPwCheckRequest;
import com.example.springboard.dto.response.PostInsertResponse;
import com.example.springboard.dto.response.PostResponse;
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
import org.springframework.transaction.annotation.Transactional;

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
     * @return 회원게시판 = 유저 id, 비회원게시판 = null
     */
    public Integer checkAuth(String accessToken, int boardId) {
        log.info("checkAuth : boardId - {}, accessToken - {}", boardId, accessToken);
        int boardAccessId = boardMapper.getBoardAccessId(boardId);
        log.info("board accesId = {}", boardAccessId);

        // 회원 게시판
        if(boardAccessId == AccessType.ACCESS_TYPE_USER){
            // 비회원이 접근하려는 경우
            if (accessToken == null) {
                throw new ApiException(ErrorCode.BOARD_AUTH_USER_FAIL);
            }
            return Integer.valueOf(tokenProvider.checkAccessToken(accessToken)); // access 토큰 유효성 확인
        } else if(boardAccessId == AccessType.ACCESS_TYPE_NON_USER){ // 비회원 게시판
            // 회원이 접근하려는 경우
            if (accessToken != null){
                throw new ApiException(ErrorCode.BOARD_AUTH_NONUSER_FAIL);
            }
        } else{ // 접근 불가 게시판
            throw new ApiException(ErrorCode.BOARD_AUTH_FORBIDDEN);
        }
        return null;
    }

    public List<PostResponse> getPostList(PostListRequest request) {
        log.info(request.toString());
        return boardMapper.getPostList(request);
    }

    @Transactional
    public PostInsertResponse insertPost(Post post){
        log.info(post.toString());
        boardMapper.insertPost(post);
        return new PostInsertResponse(post);
    }

    @Transactional
    public PostResponse getPostDetail(int boardId, int postNo) {
        Post post = boardMapper.getPost(boardId, postNo);

        // post가 null인 경우 잘못된 게시글 번호 요청, 삭제한 게시글 요청
        if(post == null || post.getTimeDeleted() != null){
            throw new ApiException(ErrorCode.POST_GET_FAIL);
        }

        boardMapper.updateViews(postNo); // 조회수 +1 업데이트
        return new PostResponse(post);
    }

    @Transactional
    public void updatePost(Post request) {
        log.info(request.toString());

        //게시글 수정 권한 확인
        checkPostAuth(request.getBoardNo(), request.getPostNo(), request.getUid(), request.getGuestPw());

        boardMapper.updatePost(request);
    }

    @Transactional
    public void deletePost(PostDeleteRequest request) {
        log.info("deletePost : {}", request);

        //게시글 수정 권한 확인
        checkPostAuth(request.getBoardNo(), request.getPostNo(), request.getUid(), request.getGuestPw());

        boardMapper.deletePost(request.getBoardNo(), request.getPostNo());
    }

    public void checkPw(PostPwCheckRequest request) {
        log.info(request.toString());
        if(!boardMapper.checkGuestPw(request.postNo, request.getGuestPw())){
            throw new ApiException(ErrorCode.POST_PWCHECK_FAIL);
        }
    }

    // 게시글 수정/삭제시 권한 확인
    public void checkPostAuth(int boardNo, int postNo, Integer uid, String guestPw){
        //권한 확인을 위한 게시글 정보 불러오기
        Post post =  boardMapper.getPost(boardNo, postNo);
        log.info("수정할 post 정보 : {}", post);

        // post가 null인 경우 잘못된 게시글 번호 요청
        if(post == null){
            throw new ApiException(ErrorCode.POST_GET_FAIL);
        }

        // 삭제된 게시글을 수정하려할 경우
        if(post.getTimeDeleted() != null){
            throw new ApiException(ErrorCode.POST_STATUS_DELETE);
        }

        // uid가 0이 아니면 회원이 작성한 게시글, uid가 미일치하면 다른사람이 작성한 게시글을 수정하려고 접근한 것.
        if((post.getUid() != 0) && (post.uid != uid)){
            throw new ApiException(ErrorCode.POST_CHANGE_AUTH_FAIL);
        } else if ((post.getGuestPw() != null) && (!post.getGuestPw().equals(guestPw))) { //guestPw가 null이 아니면 비회원 게시글, 비밀번호 일치여부 확인
            throw new ApiException(ErrorCode.POST_PWCHECK_FAIL);
        }
    }
}
