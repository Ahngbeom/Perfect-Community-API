package org.zerock.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardSearchVO {

    @NonNull
    private String keyword;

    private boolean checkTitle;
    private boolean checkContent;
    private boolean checkWriter;

    public void setCheckTitle(boolean checkTitle) {
        this.checkTitle = checkTitle;
    }

    public void setCheckContent(boolean checkContent) {
        this.checkContent = checkContent;
    }

    public void setCheckWriter(boolean checkWriter) {
        this.checkWriter = checkWriter;
    }
}
