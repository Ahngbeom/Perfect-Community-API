package org.zerock.domain;

import lombok.*;

import java.security.Principal;
import java.time.LocalDateTime;

enum BoardType {
    NOTICE( "NOTICE" ),
    POPULAR( "POPULAR" ),
    COMMON( "COMMON" );

    private final String type;

    BoardType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}

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

    private String boardPassword;

    private BoardType type;

// Java 8 이전
//    private Date    regDate;
//    private Date    updateDate;

// Java 8 이후
    private LocalDateTime   regDate;
    private LocalDateTime   updateDate;


    private String dateToToday;

    private MemberVO writtenByMember;

    public BoardVO() {    }

    public BoardVO(@NonNull String title, @NonNull String content, @NonNull String writer, String boardPassword) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardPassword = boardPassword;
    }

    public BoardVO(long bno, @NonNull String title, @NonNull String content, @NonNull String writer, String boardPassword, BoardType type, LocalDateTime regDate, LocalDateTime updateDate) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardPassword = boardPassword;
        this.type = type;
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

    public void setBoardPassword(String boardPassword) {
        this.boardPassword = boardPassword;
    }

    public void setType(BoardType type) {
        this.type = type;
    }

    public void setDateToToday(String dateToToday) {
        this.dateToToday = dateToToday;
    }
}
