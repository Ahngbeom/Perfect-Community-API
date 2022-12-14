package com.board.api.dto.board;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class BoardDTO {

    private long bno;

    private String createUser;

    @NonNull
    private String title;
    @NonNull
    private String comment;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public void setBno(long bno) {
        this.bno = bno;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
