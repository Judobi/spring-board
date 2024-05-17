package com.example.springboard.dto.response;

import java.util.List;

public class CommentListResponse {
    private int commentsCount;
    private List<CommentResponse> data;

    public CommentListResponse(int commentsCount, List<CommentResponse> data) {
        this.commentsCount = commentsCount;
        this.data = data;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public List<CommentResponse> getData() {
        return data;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setData(List<CommentResponse> data) {
        this.data = data;
    }
}
