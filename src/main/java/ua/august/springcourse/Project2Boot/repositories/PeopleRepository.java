package ua.august.springcourse.Project2Boot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.august.springcourse.Project2Boot.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
