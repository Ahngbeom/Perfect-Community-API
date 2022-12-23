package com.board.api.dto.post;

import com.board.api.dto.board.BoardDTO;
import com.board.api.dto.user.UserDTO;
import com.google.common.base.Preconditions;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDTO {

    private long pno;
    private BoardDTO bindBoard;
    private String type;
    private String title;
    private String contents;
    private UserDTO writtenUser;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    /**
     * All Arguments Constructor
     *
     * @param pno:        게시물의 고유 번호.
     * @param bindBoard     {@link com.board.api.dto.board.BoardDTO}: 게시물의 소속 게시판 고유 번호.
     * @param type:       게시물의 유형. null을 허용하지 않는다.
     * @param title:      게시물의 제목. null을 허용하지 않는다.
     * @param contents:   게시물의 내용. null을 허용하지 않는다.
     * @param writtenUser {@link UserDTO}: 게시물의 작성자. null을 허용하지 않는다.
     * @param regDate     {@link LocalDateTime}:     게시물의 작성 날짜.
     * @param updateDate  {@link LocalDateTime}:  게시물의 수정 날짜.
     */
    @Builder
    public PostDTO(long pno, BoardDTO bindBoard, String type, String title, String contents, UserDTO writtenUser, LocalDateTime regDate, LocalDateTime updateDate) {
        this.pno = pno;
        this.bindBoard = Preconditions.checkNotNull(bindBoard, "[bindBoard] must not be null.");
        this.type = Preconditions.checkNotNull(type, "[type] must not be null.");
        this.title = Preconditions.checkNotNull(title, "[title] must not be null.");
        this.contents = Preconditions.checkNotNull(contents, "[contents] must not be null.");
        this.writtenUser = Preconditions.checkNotNull(writtenUser, "[writtenUser] must not be null.");
        this.regDate = regDate;
        this.updateDate = updateDate;
    }
}
