package ua.august.springcourse.Project2Boot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.august.springcourse.Project2Boot.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleIgnoreCaseStartingWith(String prefix);
}
