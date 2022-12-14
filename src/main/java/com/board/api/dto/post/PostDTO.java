package com.board.api.dto.post;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class PostDTO {

    private long pno;
    @NonNull
    private long boardNo;
    @NonNull
    private String  type;
    @NonNull
    private String  title;
    @NonNull
    private String contents;
    @NonNull
    private String  writer; // 사용자 이름(닉네임)


    public static final Set<String> POST_TYPE = new HashSet<>(Arrays.asList("NORMAL", "NOTICE"));

    private LocalDateTime   regDate;
    private LocalDateTime   updateDate;

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

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}
