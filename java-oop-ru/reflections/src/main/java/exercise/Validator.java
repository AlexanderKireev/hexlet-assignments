package exercise;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

// BEGIN
public class Validator {
    public static List<String> validate(Address adr) {
        List<String> list = new ArrayList<>();
        for (Field field : adr.getClass().getDeclaredFields()) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            if (notNull != null) {
                try {
                    field.setAccessible(true);
                    if (field.get(adr) == null) {
                        list.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e + "Error!!!");
                }
            }
        }
        return list;
    }

    public static Map<String, List<String>> advancedValidate(Address adr) {
        List<String> list = validate(adr);

        Map<String, List<String>> map = list.stream().collect(Collectors.toMap(listVal -> listVal,
                        v -> new ArrayList<>(List.of("can not be null"))));

        for (Field field : adr.getClass().getDeclaredFields()) {
            MinLength minLength = field.getAnnotation(MinLength.class);
            if (minLength != null) { // отсеиваем все поля без аннтотации minLength
                try {
                    field.setAccessible(true);
                    if (list.contains(field.getName())) {
                        map.get(field.getName()).add("length less than " + minLength.minLength());
                    } else if (field.get(adr).toString().length() < minLength.minLength()) {
                        map.put(field.getName(), new ArrayList<>
                                (List.of("length less than " + minLength.minLength())));
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e + "Error!!!");
                }
            }
        }
        return map;
    }
}
// END
