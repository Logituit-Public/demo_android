package com.mediacorp.moviedb.data.api;

import static com.mediacorp.moviedb.utils.AppUtilsConstant.SIMPLE_DATE_FORMAT;
import static com.mediacorp.moviedb.utils.AppUtilsConstant.UTC_TIMEZONE;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        String date = element.getAsString();

        SimpleDateFormat formatter = new SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.ROOT);
        formatter.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE));

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            Log.e("Failed", e.getMessage());
            return null;
        }
    }
}