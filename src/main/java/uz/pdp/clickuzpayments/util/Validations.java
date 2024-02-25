package uz.pdp.clickuzpayments.util;

import lombok.experimental.UtilityClass;
import uz.pdp.clickuzpayments.exception.NullOrEmptyException;

import java.util.List;

@UtilityClass
public class Validations {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static <T> T requireNonNullElse(T obj, T defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        String className = obj.getClass().getSimpleName();
        if (className.equals("String")) {
            if (isNullOrEmpty(obj.toString())) {
                return defaultValue;
            }
        } else if (className.equals("Integer") || className.equals("Double")) {
            Double integer = (Double) obj;
            if (integer < 0) {
                return defaultValue;
            }
        } else if (obj instanceof List<?> list) {
            if (list.isEmpty())
                return defaultValue;
        }
        return obj;
    }
}
