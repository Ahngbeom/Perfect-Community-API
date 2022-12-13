package com.board.api.dto.board;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class BoardDTO {

    private long bno;
    @NonNull
    private String title;
    @NonNull
    private String comment;

    public void setBno(long bno) {
        this.bno = bno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
