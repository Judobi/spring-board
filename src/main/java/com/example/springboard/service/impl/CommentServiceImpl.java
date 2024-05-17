package com.example.springboard.service.impl;

import com.example.springboard.dto.request.CommentListRequest;
import com.example.springboard.dto.response.CommentListResponse;
import com.example.springboard.dto.response.CommentResponse;
import com.example.springboard.global.error.ErrorCode;
import com.example.springboard.global.error.exception.ApiException;
import com.example.springboard.mapper.BoardMapper;
import com.example.springboard.mapper.CommentMapper;
import com.example.springboard.service.CommentService;
import com.example.springboard.vo.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        Post post = boardMapper.getPost(request.getBoardId(), request.getPostNo());
        log.info(post.toString());

        // 삭제된 게시글의 댓글을 요청할 경우
        if(post.getTimeDeleted() != null){
            throw new ApiException(ErrorCode.COMMENT_POST_STATUS_FAIL);
        }

        return new CommentListResponse(post.getCommentsCount(), commentMapper.getCommentList(request));
    }
}
