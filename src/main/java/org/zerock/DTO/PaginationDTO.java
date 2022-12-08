package org.zerock.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PaginationDTO {
    private long postAmount;

    private long pageAmount;

    private long pageMin;
    private long pageMax;

    public void setPostsAmount(long postAmount) {
        this.postAmount = postAmount;
    }

    public void setPageAmount(long pageAmount) {
        this.pageAmount = pageAmount;
    }

    public void setPageMin(long pageMin) {
        this.pageMin = pageMin;
    }

    public void setPageMax(long pageMax) {
        this.pageMax = pageMax;
    }
}
