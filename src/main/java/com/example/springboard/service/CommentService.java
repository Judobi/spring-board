package com.example.springboard.service;

import com.example.springboard.dto.request.CommentRequest;
import com.example.springboard.dto.request.CommentListRequest;
import com.example.springboard.dto.response.CommentInsertResponse;
import com.example.springboard.dto.response.CommentListResponse;

public interface CommentService {

    CommentListResponse getCommentList(CommentListRequest request);

    CommentInsertResponse insertComment(CommentRequest request);

    void updateComment(CommentRequest request);

    //void deleteComment();
}
