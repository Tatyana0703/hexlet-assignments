package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homeList, int n) {
        return homeList.stream().sorted(Home::compareTo).limit(n).map(Object::toString).collect(Collectors.toList());
    }
}
// END
