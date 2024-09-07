package exercise;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;
import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    private final UserProperties userProperties;

    // Все пользователи
    private final List<User> users = Data.getUsers();

    // BEGIN
//    @Autowired
//    UserProperties userProperties;

    @Autowired
    public Application(UserProperties userProperties) {
        this.userProperties = userProperties;
    }

    @GetMapping("/admins")
    public List<String> showAdmins() {
        return users.stream()
                .filter(u -> userProperties.getAdmins().contains(u.getEmail())).map(User::getName).sorted().toList();
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        return users.stream()
            .filter(u -> Objects.equals(u.getId(), id))
            .findFirst();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
