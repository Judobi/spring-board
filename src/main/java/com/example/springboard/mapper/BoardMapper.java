package com.example.springboard.mapper;

import com.example.springboard.dto.PostListRequest;
import com.example.springboard.dto.PostListResponse;
import com.example.springboard.vo.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<PostListResponse> getPostList(PostListRequest request);

    int getBoardAccessId(int boardId);
}
