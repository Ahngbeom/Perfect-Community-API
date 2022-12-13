package com.board.api.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostListOptDTO {
    private int page;

    private String type;

    public PostListOptDTO() {
        this.page = 1;
        this.type = null;
    }

    public PostListOptDTO(int page) {
        this.page = page == 0 ? 1 : page;
    }

    public PostListOptDTO(int page, String type) {
        this.page = page == 0 ? 1 : page;
        this.type = type;
    }

    public void setPage(int page) {
        this.page = page == 0 ? 1 : page;
    }

    public void setType(String type) {
        this.type = type;
    }
}
