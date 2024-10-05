package exercise.repository;

import exercise.model.Task;
import exercise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);

    @Query("SELECT e FROM Task e WHERE e.description LIKE %:param%")
    List<Task> findAllByNameContaining(@Param("param") String value);

    //запрос вместо двунаправленной связи
    @Query("SELECT e FROM Task e WHERE e.assignee = :param")
    List<Task> findAllByUser(@Param("param") User user);

}
