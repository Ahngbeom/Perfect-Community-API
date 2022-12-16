package com.board.api.entity.board;

import com.board.api.dto.board.BoardDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {
    private long bno;
    private String createUser;
    private String title;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static BoardEntity dtoToEntity(BoardDTO boardDTO) {
        return BoardEntity.builder()
                .bno(boardDTO.getBno())
                .createUser(boardDTO.getCreatedUser())
                .title(boardDTO.getTitle())
                .comment(boardDTO.getComment())
                .createDate(boardDTO.getCreateDate())
                .updateDate(boardDTO.getUpdateDate())
                .build();
    }
}
