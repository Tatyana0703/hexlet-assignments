package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.*;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NonNull
    private String title;

    @NonNull
    private String body;

    @CreatedDate
    private LocalDate createdAt;
}
