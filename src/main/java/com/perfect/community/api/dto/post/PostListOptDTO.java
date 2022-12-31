package com.perfect.community.api.dto.post;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostListOptDTO {
    private long boardNo;
    private int page;
    private String type;

    @Builder(builderMethodName = "defaultBuilder")
    public PostListOptDTO() {
//        this.boardNo = 0;
//        this.page = 0;
//        this.type = null;
    }

    @Builder(builderMethodName = "byBoardNoBuilder")
    public PostListOptDTO(long boardNo) {
        if (boardNo > 0)
            throw new IllegalStateException("Invalid board no.");
//        Preconditions.checkState(boardNo > 0, "Invalid board no.");
        this.boardNo = boardNo;
    }

    @Builder(builderMethodName = "withPageByBoardNoBuilder")
    public PostListOptDTO(long boardNo, int page) {
        this.boardNo = boardNo;
//        this.page = page == 0 ? 1 : page;
        this.page = page;
    }

    @Builder(builderMethodName = "withTypeAndPageByBoardNoBuilder")
    public PostListOptDTO(long boardNo, int page, String type) {
        this.boardNo = boardNo;
//        this.page = page == 0 ? 1 : page;
        this.page = page;
        this.type = type;
    }

//    public void setPage(int page) {
//        this.page = page == 0 ? 1 : page;
//    }
}
