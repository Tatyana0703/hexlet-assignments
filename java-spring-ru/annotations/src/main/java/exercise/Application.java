package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Method;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method : Address.class.getDeclaredMethods()) {
            // Проверяем, есть ли у метода аннотация @Inspect
            if (method.isAnnotationPresent(Inspect.class)) {
                LOGGER.info("Method {} returns a value of type {}", method.getName(),
                        method.getReturnType().getSimpleName());
                System.out.printf("Method %s returns a value of type %s%n",
                        method.getName(), method.getReturnType().getSimpleName());
            }
        }
        // END
    }
}
