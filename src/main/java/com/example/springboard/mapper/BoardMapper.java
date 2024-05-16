package com.example.springboard.mapper;

import com.example.springboard.dto.PostListRequest;
import com.example.springboard.dto.PostResponse;
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
}
