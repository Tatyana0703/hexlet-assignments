package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;

// BEGIN
public class App {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void save(Path filePath, Car car) throws IOException {
        OBJECT_MAPPER.writeValue(filePath.toFile(), car);
    }

    public static Car extract(Path filePath) throws IOException {
        return OBJECT_MAPPER.readValue(filePath.toFile(), Car.class);
    }
}
// END
