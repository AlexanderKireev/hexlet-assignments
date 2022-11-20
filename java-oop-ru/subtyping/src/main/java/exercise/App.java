package exercise;

import java.util.HashMap;
import java.util.Map;


// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage2)  {
        Map<String, String> clonedStorage = new HashMap<>(storage2.toMap());
        for (String s : clonedStorage.keySet()) { // опустошаем storage2
            storage2.unset(s);
        }
        for (Map.Entry<String, String> x : clonedStorage.entrySet()) {
            storage2.set(x.getValue(), x.getKey());
        }
    }
}
// END
