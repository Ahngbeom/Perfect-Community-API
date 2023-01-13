package com.perfect.community.api.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private long postNo;
    private long boardNo;
    private String type;
    private String title;
    private String contents;
    private String writer;
    private Long views;
    private Long recommend;
    private Long notRecommend;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * All Arguments Constructor
     *
     * @param postNo:    게시물의 고유 번호.
     * @param boardNo:   게시물의 소속 게시판 고유 번호.
     * @param type:      게시물의 유형. null을 허용하지 않는다.
     * @param title:     게시물의 제목. null을 허용하지 않는다.
     * @param contents:  게시물의 내용. null을 허용하지 않는다.
     * @param writer:    게시물의 작성자. null을 허용하지 않는다.
     * @param views:     게시물의 조회 수.
     * @param recommend: 게시물의 추천 수.
     * @param notRecommend: 게시물의 비추천 수.
     * @param regDate    {@link LocalDateTime}:     게시물의 작성 날짜.
     * @param updateDate {@link LocalDateTime}:  게시물의 수정 날짜.
     */
    @Builder
    public PostDTO(long postNo, long boardNo, String type, String title, String contents, String writer, Long views, Long recommend, Long notRecommend, LocalDateTime regDate, LocalDateTime updateDate) {
        this.postNo = postNo;
        this.boardNo = boardNo;
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.views = views;
        this.recommend = recommend;
        this.notRecommend = notRecommend;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        String result = "PostDTO{" +
                "postNo=" + postNo +
                ", boardNo=" + boardNo +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate;
        if (views != null) {
            result += ", views=" + views;
        }
        if (recommend != null) {
            result += ", recommend=" + recommend;
        }
        if (notRecommend != null) {
            result += ", notRecommend=" + notRecommend;
        }
        return result + '}';
    }
}
