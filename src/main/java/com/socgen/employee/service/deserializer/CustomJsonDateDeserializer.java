package com.socgen.employee.service.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.socgen.employee.exception.EmployeeException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.socgen.employee.exception.ErrorConstant.UNPARSABLE_DATE;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser jsonParser,
                            DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException, EmployeeException {
        String dateString = jsonParser.getText();
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            throw new EmployeeException(UNPARSABLE_DATE);
        }
    }
}
