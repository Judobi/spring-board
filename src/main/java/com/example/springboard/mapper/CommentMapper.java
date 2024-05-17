package com.example.springboard.mapper;

import com.example.springboard.dto.request.CommentListRequest;
import com.example.springboard.dto.response.CommentResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentResponse> getCommentList(CommentListRequest request);

}
