package com.perfect.community.api.dto.post;

public enum PostType {
    NOTICE ("NOTICE"),
    NORMAL ("NORMAL");

    private final String type;

    PostType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
