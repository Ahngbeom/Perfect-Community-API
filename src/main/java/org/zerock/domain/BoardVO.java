package org.zerock.domain;

import lombok.*;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardVO {

    private long    bno;

    @NonNull
    private String  title;

    @NonNull
    private String  content;

    @NonNull
    private String  writer;

    private Date    regDate;
    private Date    updateDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
