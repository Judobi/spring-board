package com.example.springboard.mapper;

import com.example.springboard.dto.request.PostListRequest;
import com.example.springboard.dto.response.PostResponse;
import com.example.springboard.vo.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<PostResponse> getPostList(PostListRequest request);

    int getBoardAccessId(int boardId);

    void insertPost(Post post);

    Post getPost(int boardId, int postNo);

    void updatePost(Post post);

    void updateViews(int postNo);

    void deletePost(int boardId, int postNo);

    boolean checkGuestPw(int postNo, String guestPw);
}
