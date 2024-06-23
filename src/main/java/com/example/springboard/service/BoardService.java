package com.example.springboard.service;

import com.example.springboard.dto.request.PostDeleteRequest;
import com.example.springboard.dto.request.PostListRequest;
import com.example.springboard.dto.request.PostPwCheckRequest;
import com.example.springboard.dto.response.PostInsertResponse;
import com.example.springboard.dto.response.PostResponse;
import com.example.springboard.vo.Post;

import java.util.List;

public interface BoardService {

    Integer checkAuth(String accessToken, int boardId);

    List<PostResponse> getPostList(PostListRequest request);

    PostInsertResponse insertPost(Post post);

    PostResponse getPostDetail(int boardId, int postNo);

    void updatePost(Post post);

    void deletePost(PostDeleteRequest request);

    void checkPw(PostPwCheckRequest request);

}
