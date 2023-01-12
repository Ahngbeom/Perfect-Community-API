package com.perfect.community.api.vo.post;

import com.perfect.community.api.dto.post.PostDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public class PostVO {

    private final long post_no;
    private final long board_no;
    private final String type;
    private final String title;
    private final String contents;
    private final String writer;
    private final LocalDateTime reg_date;
    private final LocalDateTime update_date;

    @Builder
    public PostVO(long post_no, long board_no, String type, String title, String contents, String writer, LocalDateTime reg_date, LocalDateTime update_date) {
        this.post_no = post_no;
        this.board_no = board_no;
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.reg_date = reg_date;
        this.update_date = update_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostVO postVO = (PostVO) o;
        return post_no == postVO.post_no && board_no == postVO.board_no && Objects.equals(type, postVO.type) && Objects.equals(title, postVO.title) && Objects.equals(contents, postVO.contents) && Objects.equals(writer, postVO.writer) && Objects.equals(reg_date, postVO.reg_date) && Objects.equals(update_date, postVO.update_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post_no, board_no, type, title, contents, writer, reg_date, update_date);
    }

    public PostDTO toDTO() {
        return PostDTO.builder()
                .postNo(this.post_no)
                .boardNo(this.board_no)
                .writer(this.writer)
                .type(this.type)
                .title(this.title)
                .contents(this.contents)
                .regDate(this.reg_date)
                .updateDate(this.update_date)
                .build();
    }
}
