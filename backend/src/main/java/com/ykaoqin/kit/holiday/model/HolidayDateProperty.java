package com.ykaoqin.kit.holiday.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = HolidayDatePropertySerializer.class)
public enum HolidayDateProperty {
    WorkingDay,
    RestDay,
    NationalHoliday,
    CorpHoliday;

    public static HolidayDateProperty valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
