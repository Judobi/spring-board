package com.example.springboard.service.impl;

import com.example.springboard.dto.request.CommentInsertRequest;
import com.example.springboard.dto.request.CommentListRequest;
import com.example.springboard.dto.response.CommentInsertResponse;
import com.example.springboard.dto.response.CommentListResponse;
import com.example.springboard.dto.response.CommentResponse;
import com.example.springboard.global.error.ErrorCode;
import com.example.springboard.global.error.exception.ApiException;
import com.example.springboard.mapper.BoardMapper;
import com.example.springboard.mapper.CommentMapper;
import com.example.springboard.service.CommentService;
import com.example.springboard.vo.Comment;
import com.example.springboard.vo.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final Logger log = LoggerFactory.getLogger(CommentService.class);

    private final CommentMapper commentMapper;
    private final BoardMapper boardMapper;

    public CommentServiceImpl(CommentMapper commentMapper, BoardMapper boardMapper) {
        this.commentMapper = commentMapper;
        this.boardMapper = boardMapper;
    }

    public CommentListResponse getCommentList(CommentListRequest request) {
        log.info(request.toString());

        // 가져올 게시글 상태 확인
        Post post = checkPost(request.getBoardId(), request.getPostNo());

        return new CommentListResponse(post.getCommentsCount(), commentMapper.getCommentList(request));
    }

    @Transactional
    public CommentInsertResponse insertComment(CommentInsertRequest request) {
        checkPost(request.getBoardId(), request.getPostNo());

        // 비회원 댓글 작성시 guest 아이디, 비밀번호 입력여부 확인
        if((request.getUid() == null) && (request.getGuestId() == null || request.getGuestPw() ==null)){
            throw new ApiException(ErrorCode.COMMENT_INSERT_IDPW_BLANK);
        }

        Comment comment = new Comment(request);
        log.info(comment.toString());

        commentMapper.insertComment(comment);
        return new CommentInsertResponse(comment);
    }

    /**
     * 게시글 상태확인
     * @param boardId 게시판 번호
     * @param postNo 게시글 번호
     * @return 게시글 정보
     */
    public Post checkPost(int boardId, int postNo){
        // 가져올 게시글 상태 확인
        Post post = boardMapper.getPost(boardId, postNo);
        log.info("checkPost : {}", post.toString());

        // 삭제된 게시글에 요청할 경우
        if(post.getTimeDeleted() != null){
            throw new ApiException(ErrorCode.COMMENT_POST_STATUS_FAIL);
        }

        return post;
    }
}
