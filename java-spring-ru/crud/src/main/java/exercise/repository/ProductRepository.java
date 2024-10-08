package exercise.repository;

import exercise.model.Category;
import exercise.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByTitle(String title);

    @Query("SELECT e FROM Product e WHERE e.title LIKE %:param%")
    List<Product> findAllByTitleContaining(@Param("param") String value);

    //запрос вместо двунаправленной связи
    @Query("SELECT e FROM Product e WHERE e.category = :param")
    List<Product> findAllByCategory(@Param("param") Category value);
}
