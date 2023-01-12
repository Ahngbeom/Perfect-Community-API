package com.perfect.community.api.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 게시물의 조회수는 생성, 변경에 대한 요청 불가.
 * 오로지 DB의 데이터를 전달만 하는 역할을 수행.
 * 따라서, Setter 메소드는 정의하지 않는다.
 */


@Getter
@ToString
public class PostViewsDTO {
    private final long postNo;
    private final long views;

    @Builder
    public PostViewsDTO(long postNo, long views) {
        this.postNo = postNo;
        this.views = views;
    }
}
