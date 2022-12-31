package com.perfect.community.api.dto.post;

import com.google.common.base.Preconditions;
import lombok.*;

public class PostExtractionDTO {

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
