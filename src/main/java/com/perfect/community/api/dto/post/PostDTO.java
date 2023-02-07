/*
 * Copyright (C) 23. 2. 7. 오후 11:25 Ahngbeom (https://github.com/Ahngbeom)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perfect.community.api.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private LocalDateTime regDate;
    private long regDate;

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long updateDate;

    /**
     * All Arguments Constructor
     *
     * @param postNo:       게시물의 고유 번호.
     * @param boardNo:      게시물의 소속 게시판 고유 번호.
     * @param type:         게시물의 유형. null을 허용하지 않는다.
     * @param title:        게시물의 제목. null을 허용하지 않는다.
     * @param contents:     게시물의 내용. null을 허용하지 않는다.
     * @param writer:       게시물의 작성자. null을 허용하지 않는다.
     * @param views:        게시물의 조회 수.
     * @param recommend:    게시물의 추천 수.
     * @param notRecommend: 게시물의 비추천 수.
     * @param regDate       {@link LocalDateTime}: 게시물의 작성 날짜.
     * @param updateDate    {@link LocalDateTime}: 게시물의 수정 날짜.
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
        this.regDate = ZonedDateTime.of(regDate, ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.updateDate = ZonedDateTime.of(updateDate, ZoneId.systemDefault()).toInstant().toEpochMilli();
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
