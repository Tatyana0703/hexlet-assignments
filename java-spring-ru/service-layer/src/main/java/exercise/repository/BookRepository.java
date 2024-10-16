package exercise.repository;

import exercise.model.Author;
import exercise.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    //запрос вместо двунаправленной связи
    @Query("SELECT e FROM Book e WHERE e.author = :param")
    List<Book> findAllByAuthor(@Param("param") Author value);
}
