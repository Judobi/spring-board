package com.example.springboard.service;

import com.example.springboard.dto.PostListRequest;
import com.example.springboard.dto.PostListResponse;
import com.example.springboard.vo.Post;

import java.util.List;

public interface BoardService {

    void checkAuth(String accessToken, int boardId);

    List<PostListResponse> getPostList(PostListRequest request);

    /*
    void insertPost();

    Post getPostDetail();

    void updatePost();

    void deletePost();

    boolean checkPw();
    */
}
