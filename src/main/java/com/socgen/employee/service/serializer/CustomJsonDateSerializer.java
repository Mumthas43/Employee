package com.socgen.employee.service.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomJsonDateSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date date,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        String formattedDate = DATE_FORMAT.format(date);
        jsonGenerator.writeString(formattedDate);
    }
}
