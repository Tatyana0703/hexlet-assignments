package exercise.model;


import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Table(name = "authors", uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}))
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Author implements BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @CreatedDate
    private LocalDate createdAt;

//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    private List<Book> books = new ArrayList<>();
}
