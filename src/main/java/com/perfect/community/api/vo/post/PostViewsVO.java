package com.perfect.community.api.vo.post;

import com.perfect.community.api.dto.post.PostViewsDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class PostViewsVO {

    private final long post_no;
    private final long views;

    @Builder
    public PostViewsVO(long post_no, long views) {
        this.post_no = post_no;
        this.views = views;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostViewsVO that = (PostViewsVO) o;
        return post_no == that.post_no && views == that.views;
    }

    @Override
    public int hashCode() {
        return Objects.hash(post_no, views);
    }

    public PostViewsDTO toDTO() {
        return PostViewsDTO.builder()
                .postNo(this.post_no)
                .views(this.views)
                .build();
    }
}
