package com.perfect.community.api.dto.board;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {

    private long bno;

    private String createUser;

    private String title;

    private String comment;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;


    /**
     * All Arguments Constructor
     *
     * @param bno:       게시판의 고유 번호.
     * @param createUser: 게시판의 생성자.
     * @param title:     게시판의 제목.
     * @param comment:   게시판의 내용.
     *
     * @param createDate: 게시판의 작성 날짜. {@link LocalDateTime}
     * @param updateDate}: 게시판의 수정 날짜. {@link LocalDateTime}
     */
    @Builder
    public BoardDTO(long bno, String createUser, String title, String comment, LocalDateTime createDate, LocalDateTime updateDate) {
        this.bno = bno;
        this.createUser = createUser;
        this.title = title;
        this.comment = comment;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
