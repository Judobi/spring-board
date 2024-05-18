package com.example.springboard.service;

import com.example.springboard.dto.request.CommentInsertRequest;
import com.example.springboard.dto.request.CommentListRequest;
import com.example.springboard.dto.response.CommentInsertResponse;
import com.example.springboard.dto.response.CommentListResponse;
import com.example.springboard.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {

    CommentListResponse getCommentList(CommentListRequest request);

    CommentInsertResponse insertComment(CommentInsertRequest request);
}
