package com.perfect.community.api.dto.post;

import com.google.common.base.Preconditions;
import lombok.*;

public class PostExtractionDTO {

    /**
     * <p>
     * <b>boardNo</b>를 지정하여 특정 게시판의 게시물 목록을 추출할 수 있다. (<b>boardNo</b> >= 0)
     * <b>boardNo</b>가 0일 때는 모든 게시판을 대상으로 게시물 목록을 추출한다.
     * </p>
     * <p>
     * <b>page</b>를 지정하여 특정 페이지의 게시물 목록만을 추출할 수 있다. (<b>page</b> >= 0)
     * <b>page</b>가 0일 때는 모든 게시물 목록을 추출한다.
     * </p>
     * <p>
     * <b>type</b>을 지정하여 특정 게시물 유형의 목록만을 추출할 수 있다.
     * <b>type</b>이 null일 경우, 모든 유형의 게시물 목록을 추출한다.
     * @see PostType
     * <p>
     */
    @Getter
    @Setter
    @ToString
    public static class List {
        private long boardNo;
        private int page;
        private String type;

        @Builder
        public List(long boardNo, int page, String type) {
            Preconditions.checkState(boardNo >= 0, "Invalid board no.");
            this.boardNo = boardNo;
            this.page = page;
            this.type = type;
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class Search {
        private String keyword;
        private String logic_operator;
//        private List<String> targets;
    }






}
