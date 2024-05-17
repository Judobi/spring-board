package com.example.springboard.dto.request;

public class PostListRequest {
    public int boardId;
    public int page;
    public int limit;
    public int startPostNo;

    public PostListRequest(int boardId) {
        this.boardId = boardId;
        this.limit = 20;
        this.page = 1;
        this.startPostNo = 0;
    }

    public PostListRequest(int boardId, int page, int limit) {
        this.boardId = boardId;
        this.page = page;
        this.limit = limit;
        this.startPostNo = (this.page - 1) * this.limit;
    }

    public int getBoardId() {
        return boardId;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }

    public int getStartPostNo() {
        return startPostNo;
    }

    @Override
    public String toString() {
        return "PostListRequest{" +
                "boardId=" + boardId +
                ", page=" + page +
                ", limit=" + limit +
                ", startPostNo=" + startPostNo +
                '}';
    }
}
