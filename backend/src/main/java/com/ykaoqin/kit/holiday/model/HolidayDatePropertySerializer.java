package com.ykaoqin.kit.holiday.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ykaoqin.kit.util.SpringContextUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.util.Locale;

public class HolidayDatePropertySerializer extends JsonSerializer<HolidayDateProperty> {

    @Override
    public void serialize(HolidayDateProperty value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Locale locale = LocaleContextHolder.getLocale();
        String label = "holiday.date.property."+ value.name().toLowerCase();

        //必须通过此种方式获取，无法直接注入
        MessageSource messageSource = (MessageSource) SpringContextUtils.getBean("messageSource");
        gen.writeString(messageSource.getMessage(label, null, locale));
    }
}
