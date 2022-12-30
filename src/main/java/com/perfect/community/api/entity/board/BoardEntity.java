package com.perfect.community.api.entity.board;

import com.perfect.community.api.dto.board.BoardDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class BoardEntity {
    private long bno;
    private String createUser;
    private String title;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Builder
    public BoardEntity(long bno, String createUser, String title, String comment, LocalDateTime createDate, LocalDateTime updateDate) {
        this.bno = bno;
        this.createUser = createUser;
        this.title = title;
        this.comment = comment;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static BoardEntity dtoToEntity(BoardDTO boardDTO) {
        return BoardEntity.builder()
                .bno(boardDTO.getBno())
                .createUser(boardDTO.getCreateUser())
                .title(boardDTO.getTitle())
                .comment(boardDTO.getComment())
                .createDate(boardDTO.getCreateDate())
                .updateDate(boardDTO.getUpdateDate())
                .build();
    }
}
