package com.board.api.service.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class DateUtility {

    public String dateToTodayConverter(LocalDateTime toCompare) {
        if (ChronoUnit.YEARS.between(LocalDateTime.now(), toCompare) != 0) {
            return Math.abs(ChronoUnit.YEARS.between(LocalDateTime.now(), toCompare)) + "년 전";
        } else if (ChronoUnit.MONTHS.between(LocalDateTime.now(), toCompare) != 0) {
            return Math.abs(ChronoUnit.MONTHS.between(LocalDateTime.now(), toCompare)) + "개월 전";
        } else if (ChronoUnit.WEEKS.between(LocalDateTime.now(), toCompare) != 0) {
            return Math.abs(ChronoUnit.WEEKS.between(LocalDateTime.now(), toCompare)) + "주 전";
        } else if (ChronoUnit.DAYS.between(LocalDateTime.now(), toCompare) != 0) {
            return Math.abs(ChronoUnit.DAYS.between(LocalDateTime.now(), toCompare)) + "일 전";
        } else if (ChronoUnit.HOURS.between(LocalDateTime.now(), toCompare) != 0) {
            return Math.abs(ChronoUnit.HOURS.between(LocalDateTime.now(), toCompare)) + "시간 전";
        } else if (ChronoUnit.MINUTES.between(LocalDateTime.now(), toCompare) != 0) {
            return Math.abs(ChronoUnit.MINUTES.between(LocalDateTime.now(), toCompare)) + "분 전";
        } else if (ChronoUnit.SECONDS.between(LocalDateTime.now(), toCompare) != 0) {
            return Math.abs(ChronoUnit.SECONDS.between(LocalDateTime.now(), toCompare)) + "초 전";
        } else
            return null;
    }

    public String dateToTodayCalculator(LocalDateTime regDate, LocalDateTime updateDate) {
        String dateToToday = null;
        if (regDate != null && updateDate != null) {
            dateToToday = dateToTodayConverter(regDate);
            if (updateDate.isAfter(regDate)) {
                dateToToday += "(" + dateToTodayConverter(updateDate) + " 수정됨)";
            }
        }
        return dateToToday;
    }
}
