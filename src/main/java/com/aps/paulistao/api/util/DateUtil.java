package com.aps.paulistao.api.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String formatDate(final LocalDateTime dateTime, final String mask){
        return dateTime.format(DateTimeFormatter.ofPattern(mask));
    }
}