package com.board.api.dto.post;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class PostSearchDTO {

    @NonNull
    private String keyword;
    private String logic_operator;
    @NonNull
    private List<String> targets;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setLogic_operator(String logic_operator) {
        this.logic_operator = logic_operator;
    }

    public void setTargets(List<String> targets) {
        this.targets = targets;
    }

}
