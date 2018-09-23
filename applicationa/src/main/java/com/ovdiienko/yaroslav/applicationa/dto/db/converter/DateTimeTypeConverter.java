package com.ovdiienko.yaroslav.applicationa.dto.db.converter;

import android.arch.persistence.room.TypeConverter;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class DateTimeTypeConverter {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @TypeConverter
    public OffsetDateTime toOffsetDateTime(String value) {
        return formatter.parse(value, OffsetDateTime::from);
    }

    @TypeConverter
    public String fromOffsetDateTime(OffsetDateTime date) {
        return date.format(formatter);
    }
}
