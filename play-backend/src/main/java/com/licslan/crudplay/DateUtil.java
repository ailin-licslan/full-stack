package com.licslan.crudplay;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

public final class DateUtil {
    private DateUtil() {}

    public static LocalDate date2LocalDate(Date date) {
        if(Objects.isNull(date)) {
            return null;
        }
        return localDateTime2LocalDate(date2LocalDateTime(date));
    }

    public static LocalDate localDateTime2LocalDate(LocalDateTime localDateTime) {
        if(Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.toLocalDate();
    }
    public static Instant localDateTime2Instant(LocalDateTime localDateTime) {
        if(Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        if(Objects.isNull(date)) {
            return null;
        }
        return instant2LocalDateTime(date2Instant(date));
    }

    public static Instant date2Instant(Date date) {
        if(Objects.isNull(date)) {
            return null;
        }
        return date.toInstant();
    }

    public static LocalDateTime instant2LocalDateTime(Instant instant) {
        if(Objects.isNull(instant)) {
            return null;
        }
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static String format(LocalDateTime now, String pattern) {
        if(Objects.isNull(now)) {
            return null;
        }
        return now.format(DateTimeFormatter.ofPattern(pattern));
    }
    public static String format(Instant now, String pattern) {
        if(Objects.isNull(now)) {
            return null;
        }
        return DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(now);
    }
    public static String format(LocalDate now, String pattern) {
        if(Objects.isNull(now)) {
            return null;
        }
        return now.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parse2LocalDate(String time, String pattern) {
        if(Objects.isNull(time) || Objects.isNull(pattern)) {
            return null;
        }
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    public static YearMonth parse2YearMonth(String time, String pattern) {
        if(Objects.isNull(time) || Objects.isNull(pattern)) {
            return null;
        }
        return YearMonth.parse(time, DateTimeFormatter.ofPattern(pattern));
    }
    public static LocalDate getLastWorkingDate(YearMonth yearMonth) {
        if(Objects.isNull(yearMonth)) {
            return null;
        }
        LocalDate lastWorkingDate = yearMonth.atEndOfMonth();
        DayOfWeek dayOfWeek = lastWorkingDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            // adjust to previous friday
            lastWorkingDate = lastWorkingDate.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }
        return lastWorkingDate;
    }

    public static void main(String[] args) {
        YearMonth localDate = DateUtil.parse2YearMonth("202307", "yyyyMM");
        LocalDate lastWorkingDate = DateUtil.getLastWorkingDate(localDate);
        String ss = DateUtil.format(lastWorkingDate, "yyyy-MM-dd");
        System.out.println(ss);
    }
}
