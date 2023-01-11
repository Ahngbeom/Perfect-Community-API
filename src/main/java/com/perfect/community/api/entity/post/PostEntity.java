package com.perfect.community.api.entity.post;

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
    private long views;
    private LocalDateTime   regDate;
    private LocalDateTime   updateDate;

    @Builder
    public PostEntity(long pno, long boardNo, String type, String title, String contents, String writer, long views, LocalDateTime regDate, LocalDateTime updateDate) {
        this.pno = pno;
        this.boardNo = boardNo;
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.views = views;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

}
