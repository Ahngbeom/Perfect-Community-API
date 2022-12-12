package com.board.api.DTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class PostsSearchDTO {

    @NonNull
    private String keyword;

    @NonNull
    private Map<String, Boolean> type;

    public PostsSearchDTO() {
    }

    public PostsSearchDTO(@NonNull String keyword) {
        this.keyword = keyword;
        this.type = new HashMap<>();
        this.type.put("title", false);
        this.type.put("contents", false);
        this.type.put("writer", false);
    }

    public PostsSearchDTO(@NonNull String keyword, @NonNull Map<String, Boolean> type) {
        this.keyword = keyword;
        this.type = type;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setType(Map<String, Boolean> type) {
        this.type = type;
    }
}
