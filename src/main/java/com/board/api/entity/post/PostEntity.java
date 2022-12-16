package com.board.api.entity.post;

import com.board.api.dto.post.PostDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    private long pno;
    private long boardNo;
    private String  type;
    private String  title;
    private String contents;
    private String writer;

    private LocalDateTime   regDate;
    private LocalDateTime   updateDate;

    public static PostEntity dtoToEntity(PostDTO postDTO) {
        return PostEntity.builder()
                .pno(postDTO.getPno())
                .boardNo(postDTO.getBoardNo())
                .type(postDTO.getType())
                .writer(postDTO.getWrittenUser().getUserId())
                .title(postDTO.getTitle())
                .contents(postDTO.getContents())
                .regDate(postDTO.getRegDate())
                .updateDate(postDTO.getUpdateDate())
                .build();
    }

}
