package com.perfect.community.api.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDTO {

    private long postNo;
    private long boardNo;
    private String type;
    private String title;
    private String contents;
    private String writer;
    @ToString.Exclude private PostViewsDTO views;
    @ToString.Exclude private PostRecommendDTO recommend;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * All Arguments Constructor
     *
     * @param postNo:       게시물의 고유 번호.
     * @param boardNo:      게시물의 소속 게시판 고유 번호.
     * @param type:         게시물의 유형. null을 허용하지 않는다.
     * @param title:        게시물의 제목. null을 허용하지 않는다.
     * @param contents:     게시물의 내용. null을 허용하지 않는다.
     * @param writer:       게시물의 작성자. null을 허용하지 않는다.
     * @param views:        {@link PostViewsDTO} 게시물의 조회 수.
     * @param recommend:    {@link PostRecommendDTO} 게시물의 추천 수.
     * @param regDate       {@link LocalDateTime}:     게시물의 작성 날짜.
     * @param updateDate    {@link LocalDateTime}:  게시물의 수정 날짜.
     */
    @Builder
    public PostDTO(long postNo, long boardNo, String type, String title, String contents, String writer, PostViewsDTO views, PostRecommendDTO recommend, LocalDateTime regDate, LocalDateTime updateDate) {
        this.postNo = postNo;
        this.boardNo = boardNo;
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.views = views;
        this.recommend = recommend;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }
}
