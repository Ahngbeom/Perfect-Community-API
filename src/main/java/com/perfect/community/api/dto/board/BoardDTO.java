package com.perfect.community.api.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.perfect.community.api.vo.board.BoardVO;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
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

    public BoardDTO(BoardVO boardVO) {
        this.bno = boardVO.getBno();
        this.createUser = boardVO.getCreate_user();
        this.title = boardVO.getTitle();
        this.comment = boardVO.getComment();
        this.createDate = boardVO.getCreate_date();
        this.updateDate = boardVO.getUpdate_date();
    }

}
