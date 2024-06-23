package com.example.springboard.service;

import com.example.springboard.dto.*;
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
