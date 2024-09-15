package exercise.daytime;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println("Bean 'Night' is initialized!");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Bean 'Night'. Cleaning up resources or performing final actions!");
    }
    // END
}
