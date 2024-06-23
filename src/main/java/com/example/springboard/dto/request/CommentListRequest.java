package com.example.springboard.dto.request;

public class CommentListRequest {
    private int boardId;
    private int postNo;
    private int page;
    private int limit;
    private int startCommentNo;

    public CommentListRequest(int boardId, int postNo, int page, int limit) {
        this.boardId = boardId;
        this.postNo = postNo;
        this.page = page;
        this.limit = limit;
        this.startCommentNo = (this.page - 1) * this.limit;
    }

    public int getBoardId() {
        return boardId;
    }

    public int getPostNo() {
        return postNo;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }

    public int getStartCommentNo() {
        return startCommentNo;
    }

    @Override
    public String toString() {
        return "CommentListRequest{" +
                "boardId=" + boardId +
                ", postNo=" + postNo +
                ", page=" + page +
                ", limit=" + limit +
                ", startCommentNo=" + startCommentNo +
                '}';
    }
}
