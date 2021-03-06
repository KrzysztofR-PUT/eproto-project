package utils;

/**
 * Created by krzysztof on 12/05/2017.
 */
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Provider
public class DateParamConverterProvider implements ParamConverterProvider {

    private final String format;

    public DateParamConverterProvider() {
        format = "YYYY-MM-DD";
    }
    public DateParamConverterProvider(String dateFormat) {
        this.format = dateFormat;
    }

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType,
                                              Type genericType,
                                              Annotation[] annotations) {

        if (rawType != Date.class) {
            return null;
        }

        return (ParamConverter<T>) new ParamConverter<Date>() {

            @Override
            public Date fromString(String value) {
                if (value == null || value.isEmpty())
                    return null;

                SimpleDateFormat formatter = new SimpleDateFormat(format);
                try {
                    return formatter.parse(value);
                } catch (Exception ex) {
                    throw new WebApplicationException("Bad formatted date", 400);
                }
            }

            @Override
            public String toString(Date date) {
                return new SimpleDateFormat(format).format(date);
            }
        };
    }
}