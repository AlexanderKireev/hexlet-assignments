package exercise;

import java.util.ArrayList;
import java.util.List;


// BEGIN
class App {
    public static List<String> buildAppartmentsList(List<Home> appartments, int n) {
        List<String> result = new ArrayList<>();
        if (appartments.size() == 0) {
            return result;
        }
        appartments.sort((o1, o2) -> (int) (o1.getArea() - o2.getArea()));
        for (int i = 0; i < n; i++) {
            result.add(appartments.get(i).toString());
        }
        return result;
    }
}
// END
