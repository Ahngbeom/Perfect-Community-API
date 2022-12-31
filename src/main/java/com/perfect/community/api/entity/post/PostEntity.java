package com.perfect.community.api.entity.post;

import com.perfect.community.api.dto.post.PostDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class PostEntity {

    private long pno;
    private long boardNo;
    private String  type;
    private String  title;
    private String contents;
    private String writer;
    private LocalDateTime   regDate;
    private LocalDateTime   updateDate;

    @Builder
    public PostEntity(long pno, long boardNo, String type, String title, String contents, String writer, LocalDateTime regDate, LocalDateTime updateDate) {
        this.pno = pno;
        this.boardNo = boardNo;
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

    public static PostEntity dtoToEntity(PostDTO postDTO) {
        return PostEntity.builder()
                .pno(postDTO.getPno())
                .boardNo(postDTO.getBoardNo())
                .type(postDTO.getType())
                .writer(postDTO.getWriter())
                .title(postDTO.getTitle())
                .contents(postDTO.getContents())
                .regDate(postDTO.getRegDate())
                .updateDate(postDTO.getUpdateDate())
                .build();
    }

}
