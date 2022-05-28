package org.zerock.domain;

import lombok.*;

import java.security.Principal;
import java.time.LocalDateTime;

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

    private String userId;
    private String boardPassword;

// Java 8 이전
//    private Date    regDate;
//    private Date    updateDate;

// Java 8 이후
    private LocalDateTime   regDate;
    private LocalDateTime   updateDate;


    private String dateToToday;

    public BoardVO() {    }

    public BoardVO(@NonNull String title, @NonNull String content, @NonNull String writer, String boardPassword) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardPassword = boardPassword;
    }

    public BoardVO(long bno, @NonNull String title, @NonNull String content, @NonNull String writer, String boardPassword, LocalDateTime regDate, LocalDateTime updateDate) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardPassword = boardPassword;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

    public void setBno(long bno) { // Need when modifying posts of board
        this.bno = bno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBoardPassword(String boardPassword) {
        this.boardPassword = boardPassword;
    }

    public void setDateToToday(String dateToToday) {
        this.dateToToday = dateToToday;
    }
}
