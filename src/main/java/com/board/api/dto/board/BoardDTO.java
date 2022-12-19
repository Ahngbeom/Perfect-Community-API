package com.board.api.dto.board;

import com.google.common.base.Preconditions;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {

    private long bno;

    private String createdUser;

//    @NonNull
    private String title;

    private String comment;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Builder
    public BoardDTO(long bno, String createdUser, String title, String comment, LocalDateTime createDate, LocalDateTime updateDate) {
        this.bno = bno;
        this.createdUser = createdUser;
        Preconditions.checkNotNull(title, "Board title must not be null");
        this.title = title;
        this.comment = comment;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public void setTitle(String title) {
        Preconditions.checkNotNull(title, "Board title must not be null");
        this.title = title;
    }
}
