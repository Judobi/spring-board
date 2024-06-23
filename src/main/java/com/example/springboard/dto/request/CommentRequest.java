package com.example.springboard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CommentRequest {
    private Integer commentNo;
    private int postNo;

    private int boardId;
    private Integer uid;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Pattern(regexp = ".{2,10}$",
            message = "게스트 아이디는 2~10 자리를 입력해주세요.")
    private String guestId;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,10}$",
            message = "비밀번호는 4~10 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String guestPw;

    public int getPostNo() {
        return postNo;
    }

    public int getBoardId() {
        return boardId;
    }

    public Integer getUid() {
        return uid;
    }

    public String getContent() {
        return content;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getGuestPw() {
        return guestPw;
    }

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public void setGuestPw(String guestPw) {
        this.guestPw = guestPw;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }
}
