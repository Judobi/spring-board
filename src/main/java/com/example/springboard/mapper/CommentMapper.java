package com.example.springboard.mapper;

import com.example.springboard.dto.request.CommentListRequest;
import com.example.springboard.dto.response.CommentResponse;
import com.example.springboard.vo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentResponse> getCommentList(CommentListRequest request);
    void insertComment(Comment request);

    void updateComment(Comment request);

    Comment getComment(int postNo, int commentNo);

    void deleteComment(Comment comment);

}
