package com.perfect.community.api.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 게시물의 추천수는 클라이언트나 관리자 모두 조작할 수 없음.
 * 오로지 DB의 데이터를 전달만 하는 역할을 수행.
 * 따라서, Setter 메소드는 정의하지 않는다.
 */

@Getter
@ToString
public class PostRecommendDTO {
    private final long postNo;
    private final long recommend;
    private final long notRecommend;

    @Builder
    public PostRecommendDTO(long postNo, long recommend, long notRecommend) {
        this.postNo = postNo;
        this.recommend = recommend;
        this.notRecommend = notRecommend;
    }
}
