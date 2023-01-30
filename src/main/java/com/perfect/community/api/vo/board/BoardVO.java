package com.perfect.community.api.vo.board;

import com.perfect.community.api.dto.board.BoardDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
public class BoardVO {
    private long bno;
    private String create_user;
    private String title;
    private String comment;
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    @Builder
    public BoardVO(long bno, String create_user, String title, String comment, LocalDateTime create_date, LocalDateTime update_date) {
        this.bno = bno;
        this.create_user = create_user;
        this.title = title;
        this.comment = comment;
        this.create_date = create_date;
        this.update_date = update_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardVO boardVO = (BoardVO) o;
        return bno == boardVO.bno && Objects.equals(create_user, boardVO.create_user) && Objects.equals(title, boardVO.title) && Objects.equals(comment, boardVO.comment) && Objects.equals(create_date, boardVO.create_date) && Objects.equals(update_date, boardVO.update_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, create_user, title, comment, create_date, update_date);
    }
}
