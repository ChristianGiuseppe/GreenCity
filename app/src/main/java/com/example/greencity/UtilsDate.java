package com.example.greencity;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class UtilsDate {
    private static String DEFAULT_FORMATTER = "dd/MM/YYYY";
    private Date dateToFormatter;
    private SimpleDateFormat simpleDateFormat;
    private String dateFormatted;

    public UtilsDate(Date dateToFormatter) {
        this.dateToFormatter = dateToFormatter;
        simpleDateFormat = new SimpleDateFormat(DEFAULT_FORMATTER);
        dateFormatted = simpleDateFormat.format(dateToFormatter);
    }

    public Date getDateToFormatter() {
        return dateToFormatter;
    }

    public void setDateToFormatter(Date dateToFormatter) {
        this.dateToFormatter = dateToFormatter;
    }

    @NonNull
    @Override
    public String toString() {
        return dateFormatted;
    }
}
