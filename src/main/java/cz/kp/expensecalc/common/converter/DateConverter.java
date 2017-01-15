package cz.kp.expensecalc.common.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class DateConverter extends StrutsTypeConverter {
	
    private static final DateFormat INPUT_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Object convertFromString(Map context, String[] values, Class clazz) {
        try {
            java.util.Date udate =  INPUT_FORMAT.parse(values[0]);
            return  new java.sql.Date(udate.getTime());
        } catch (Exception e) {
            throw new TypeConversionException(e.getMessage());
        }
    }
 
    @Override
    public String convertToString(Map context, Object value) {
        try {
            return INPUT_FORMAT.format(value);
        } catch (Exception e) {
            throw new TypeConversionException(e.getMessage());
        }
    }


}
