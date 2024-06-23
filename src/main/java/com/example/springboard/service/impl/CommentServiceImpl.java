package com.example.springboard.service.impl;

import com.example.springboard.dto.request.CommentAuthRequest;
import com.example.springboard.dto.request.CommentRequest;
import com.example.springboard.dto.request.CommentListRequest;
import com.example.springboard.dto.response.CommentInsertResponse;
import com.example.springboard.dto.response.CommentListResponse;
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
    public CommentInsertResponse insertComment(CommentRequest request) {
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

    @Transactional
    public void updateComment(CommentRequest request) {
        // 게시글 정보 확인
        checkPost(request.getBoardId(), request.getPostNo());

        // 댓글 정보 확인 & 수정 권한 확인
        checkComment(request.getPostNo(), request.getCommentNo(), request.getUid(), request.getGuestPw());

        Comment comment = new Comment(request);
        log.info("updateComment : {}", comment);

        commentMapper.updateComment(comment);
    }

    @Transactional
    public void deleteComment(CommentAuthRequest request) {
        // 게시글 정보 확인
        checkPost(request.getBoardId(), request.getPostNo());

        // 댓글 정보 확인 & 수정 권한 확인
        checkComment(request.getPostNo(), request.getCommentNo(), request.getUid(), request.getGuestPw());

        Comment comment = new Comment(request);
        log.info("updateComment : {}", comment);

        commentMapper.deleteComment(comment);
    }


    public void checkGuestPw(CommentAuthRequest request) {
        // 게시글 정보 확인
        checkPost(request.getBoardId(), request.getPostNo());

        // 댓글 정보 확인 & 수정 권한 확인
        checkComment(request.getPostNo(), request.getCommentNo(), request.getUid(), request.getGuestPw());
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

        // 게시글을 조회할 수 없을 때,
        if(post == null){
            throw new ApiException(ErrorCode.POST_GET_FAIL);
        }

        log.info("checkPost : {}", post);


        // 삭제된 게시글에 요청할 경우
        if(post.getTimeDeleted() != null){
            throw new ApiException(ErrorCode.COMMENT_POST_STATUS_FAIL);
        }

        return post;
    }

    /**
     * 댓글 상태 및 권환 체크
     * @param postNo 게시글 번호
     * @param commentNo 댓글 번호
     * @param uid 회원 - 비회원일 경우 null
     * @param guestPw 비회원 비밀번호 - 회원일 경우 null
     */
    public void checkComment(int postNo, int commentNo, Integer uid, String guestPw){
        // 댓글 상태 확인
        Comment comment = commentMapper.getComment(postNo, commentNo);

        // 댓글을 조회할 수 없을 때
        if(comment ==null){
            throw new ApiException(ErrorCode.COMMENT_INFO_GET_FAIL);
        }
        log.info("checkComment : {}", comment);

        // 댓글이 삭제된 상태인 경우
        if(comment.getTimeDeleted() != null){
            throw new ApiException(ErrorCode.COMMENT_STATUS_DELETED);
        }

        // 비회원 댓글 - uid가 null
        if(uid == null){
            // 비회원 댓글 guest 비밀번호가 다를 경우,
            if(!comment.getGuestPw().equals(guestPw)){
                throw new ApiException(ErrorCode.COMMENT_PWCHECK_FAIL);
            }
        } else{
            // 회원 댓글 - uid 정보가 다를 경우
            if(!comment.getUid().equals(uid)){
                throw new ApiException(ErrorCode.COMMENT_UIDCHECK_FAIL);
            }
        }
    }
}
