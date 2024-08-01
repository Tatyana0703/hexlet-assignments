package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage map) {
        Map<String, String> result = new HashMap<>();
        map.toMap().forEach(
                (key, value) -> {
                    map.unset(key);
                    map.set(value, key);
                }
        );
    }
}
// END
