package org.zerock.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

// Java 8 이전
//    private Date    regDate;
//    private Date    updateDate;

// Java 8 이후
    private LocalDateTime   regDate;
    private LocalDateTime   updateDate;

    private String dateToToday;

//    public void setBno(long bno) { // Need when modifying post of board
//        this.bno = bno;
//    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setDateToToday(String dateToToday) {
        this.dateToToday = dateToToday;
    }
}
