package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();

        for (Field field : address.getClass().getDeclaredFields()) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            if (annotation != null) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> result = new HashMap<>();

        for (Field field : address.getClass().getDeclaredFields()) {
            List<String> annotationErrors = new ArrayList<>();
            NotNull notNullAnnotation = field.getAnnotation(NotNull.class);
            MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
            if (notNullAnnotation != null) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        annotationErrors.add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (minLengthAnnotation != null) {
                int valueAnnotation = minLengthAnnotation.minLength();
                try {
                    field.setAccessible(true);
                    if (String.valueOf(field.get(address)).length() < valueAnnotation) {
                        annotationErrors.add(String.format("length less than %s", valueAnnotation));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (!annotationErrors.isEmpty()) {
                result.put(field.getName(), annotationErrors);
            }
        }

        return result;
    }
}
// END
