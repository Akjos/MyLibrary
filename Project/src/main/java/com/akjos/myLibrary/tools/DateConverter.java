package com.akjos.myLibrary.tools;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

    public static Date convertToDate(LocalDate localDate) {
        if (localDate != null)
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return null;
    }

    public static LocalDate convertToLocaldate(Date date) {
        if (date != null)
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return null;
    }
}
