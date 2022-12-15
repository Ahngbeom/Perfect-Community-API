package com.board.api.dto.post;

import com.board.api.dto.UserDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private long pno;
    private long boardNo;
    private String  type;
    private String  title;
    private String contents;
    private UserDTO writtenUser;

    private LocalDateTime   regDate;
    private LocalDateTime   updateDate;

    public void setPno(long pno) { // Need when modifying posts of board
        this.pno = pno;
    }

    public void setBoardNo(long boardNo) {
        this.boardNo = boardNo;
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

    public void setWrittenUser(UserDTO writtenUser) {
        this.writtenUser = writtenUser;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}
