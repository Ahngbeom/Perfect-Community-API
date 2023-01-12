package com.perfect.community.api.dto.post;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostViewsDTO {
    private long postNo;
    private long views;

    @Builder
    public PostViewsDTO(long postNo, long views) {
        this.postNo = postNo;
        this.views = views;
    }
}
