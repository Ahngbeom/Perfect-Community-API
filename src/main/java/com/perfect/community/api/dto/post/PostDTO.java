package com.perfect.community.api.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.dto.user.UserDTO;
import com.google.common.base.Preconditions;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDTO {

    private long pno;
    private long boardNo;
    private String type;
    private String title;
    private String contents;
    private String writer;
    private long views;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * All Arguments Constructor
     *
     * @param pno:       게시물의 고유 번호.
     * @param boardNo:   게시물의 소속 게시판 고유 번호.
     * @param type:      게시물의 유형. null을 허용하지 않는다.
     * @param title:     게시물의 제목. null을 허용하지 않는다.
     * @param contents:  게시물의 내용. null을 허용하지 않는다.
     * @param writer:    게시물의 작성자. null을 허용하지 않는다.
     * @param views:     게시물의 조회수.
     * @param regDate    {@link LocalDateTime}:     게시물의 작성 날짜.
     * @param updateDate {@link LocalDateTime}:  게시물의 수정 날짜.
     */
    @Builder
    public PostDTO(long pno, long boardNo, String type, String title, String contents, String writer, long views, LocalDateTime regDate, LocalDateTime updateDate) {
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

    public static class Fetch {
        private long pno;
        private BoardDTO bindBoard;
        private String type;
        private String title;
        private String contents;
        private UserDTO writtenUser;
        private LocalDateTime regDate;
        private LocalDateTime updateDate;
    }

}
