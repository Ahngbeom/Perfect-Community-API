package com.board.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class PostDTO {

    private long pno;

    @NonNull
    private String  type;

    @NonNull
    private String  title;

    @NonNull
    private String contents;

//    @NonNull
    private String  writer; // 사용자 이름(닉네임)

// Java 8 이전
//    private Date    regDate;
//    private Date    updateDate;

// Java 8 이후
    private LocalDateTime   regDate;
    private LocalDateTime   updateDate;

//    private String dateToToday;

    public void setPno(long pno) { // Need when modifying posts of board
        this.pno = pno;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

//    public void setDateToToday(String dateToToday) {
//        this.dateToToday = dateToToday;
//    }
}
