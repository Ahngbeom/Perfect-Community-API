package com.perfect.community.api.vo.post;

import com.perfect.community.api.dto.post.PostRecommendDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class PostRecommendVO {

    private final long post_no;
    private final long recommend;
    private final long not_recommend;

    @Builder
    public PostRecommendVO(long post_no, long recommend, long notRecommend) {
        this.post_no = post_no;
        this.recommend = recommend;
        this.not_recommend = notRecommend;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostRecommendVO that = (PostRecommendVO) o;
        return post_no == that.post_no && recommend == that.recommend && not_recommend == that.not_recommend;
    }
    @Override
    public int hashCode() {
        return Objects.hash(post_no, recommend, not_recommend);
    }

    public PostRecommendDTO toDTO() {
        return PostRecommendDTO.builder()
                .postNo(this.post_no)
                .recommend(this.recommend)
                .build();
    }
}
