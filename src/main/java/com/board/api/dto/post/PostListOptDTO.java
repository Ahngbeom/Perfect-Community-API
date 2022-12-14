package com.board.api.dto.post;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class PostListOptDTO {
    @NonNull
    private long boardNo;
    private int page;
    private String type;

    public PostListOptDTO() {
        this.boardNo = -1;
        this.page = 1;
        this.type = null;
    }

    public PostListOptDTO(long boardNo, int page) {
        this.boardNo = boardNo;
        this.page = page == 0 ? 1 : page;
    }

    public PostListOptDTO(long boardNo, int page, String type) {
        this.boardNo = boardNo;
        this.page = page == 0 ? 1 : page;
        this.type = type;
    }

    public void setBoardNo(long boardNo) {
        this.boardNo = boardNo;
    }

    public void setPage(int page) {
        this.page = page == 0 ? 1 : page;
    }

    public void setType(String type) {
        this.type = type;
    }
}
