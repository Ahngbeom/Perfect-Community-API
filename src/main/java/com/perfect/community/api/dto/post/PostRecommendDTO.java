package com.perfect.community.api.dto.post;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostRecommendDTO {
    private long postNo;
    private long recommend;
    private long notRecommend;

    @Builder
    public PostRecommendDTO(long postNo, long recommend, long notRecommend) {
        this.postNo = postNo;
        this.recommend = recommend;
        this.notRecommend = notRecommend;
    }
}
