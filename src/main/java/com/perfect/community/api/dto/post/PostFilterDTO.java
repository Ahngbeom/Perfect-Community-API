package com.perfect.community.api.dto.post;

import lombok.*;

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
 *
 * @see PostType
 * </p>
 * <p>
 * <b>keyword</b>를 특정하여 게시물의 제목, 내용에 <b>keyword</b>가 포함되는 게시물만을 추출할 수 있다.
 * <b>keyword</b>가 null일 경우, 무효한 옵션이다.
 * </p>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostFilterDTO {

    private long boardNo;
    private int page;
    private String type;
    private String keyword;

    @Builder
    public PostFilterDTO(long boardNo, int page, String type) {
//            Preconditions.checkState(boardNo >= 0, "Invalid board no.");
        this.boardNo = boardNo;
        this.page = page;
        this.type = type;
    }

}
