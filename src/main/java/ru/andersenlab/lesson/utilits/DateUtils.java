package ru.andersenlab.lesson.utilits;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getNextMonday() {
        LocalDate today = LocalDate.now();

        while (today.getDayOfWeek() != DayOfWeek.MONDAY) {
            today = today.plusDays(1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return today.format(formatter);
    }
    public static String getDateInTwoWeeks() {
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksLater = today.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return twoWeeksLater.format(formatter);
    }
}
