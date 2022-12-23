package com.board.api.dto.board;

import com.board.api.dto.user.UserDTO;
import com.google.common.base.Preconditions;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {

    private long bno;

    private UserDTO createUser;

    private String title;

    private String comment;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;


    /**
     * All Arguments Constructor
     *
     * @param bno:       게시판의 고유 번호.
     * @param createUser {@link UserDTO}: 게시판의 생성자. null을 허용하지 않는다.
     * @param title:     게시판의 제목. null을 허용하지 않는다.
     * @param comment:   게시판의 내용.
     * @param createDate {@link LocalDateTime}: 게시판의 작성 날짜.
     * @param updateDate {@link LocalDateTime}: 게시판의 수정 날짜.
     */
    @Builder
    public BoardDTO(long bno, UserDTO createUser, String title, String comment, LocalDateTime createDate, LocalDateTime updateDate) {
        this.bno = bno;
        this.createUser = Preconditions.checkNotNull(createUser, "[createUser] must not be null");
        this.title = Preconditions.checkNotNull(title, "[title] must not be null");
        this.comment = comment;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public void setTitle(String title) {
        this.title = Preconditions.checkNotNull(title, "[title] title must not be null");
    }

}
