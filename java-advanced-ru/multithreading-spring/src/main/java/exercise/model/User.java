package exercise.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("users")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Persistable<Long> {

    // Идентификатор будет генерироваться автоматически
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
