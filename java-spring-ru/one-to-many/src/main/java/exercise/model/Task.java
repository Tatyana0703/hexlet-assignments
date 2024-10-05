package exercise.model;


import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;

@Entity
@Table(name = "assignees")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task implements BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    private long id;

    @NotBlank
    @ToString.Include
    private String title;

    @ToString.Include
    private String description;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    // BEGIN
    // Связь ManyToOne
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)  //по умолчанию FetchType.EAGER
    @JoinColumn(name = "assignee_id", nullable = false)
    private User assignee;
    // END
}
