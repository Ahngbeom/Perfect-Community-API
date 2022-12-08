package org.zerock.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PageHeaderDTO {
    private String title;
    private String link;
    private String message;

    public PageHeaderDTO(String title, String link, String message) {
        this.title = title;
        this.link = link;
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
