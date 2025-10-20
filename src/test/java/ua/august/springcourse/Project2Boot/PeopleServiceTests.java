package ua.august.springcourse.Project2Boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.august.springcourse.Project2Boot.entities.Person;
import ua.august.springcourse.Project2Boot.repositories.PeopleRepository;
import ua.august.springcourse.Project2Boot.services.PeopleService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PeopleServiceTests {

    @Mock
    PeopleRepository peopleRepository;

    @InjectMocks
    PeopleService peopleService;

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person(1,"John",25,null);
    }

    @DisplayName("Проверка работы метода findById при налчичие человека в БД")
    @Test
    void shouldReturnPersonWhenExists() {

        // arrange
        when(peopleRepository.findById(1)).thenReturn(Optional.of(person));

        // act
        Person result = peopleService.findOne(1);

        // assert
        verify(peopleRepository).findById(1);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("John", result.getName()),
                () -> assertEquals(25, person.getAge())
        );

    }

    @DisplayName("Проверка работы метода findById при отсутствии человека в БД")
    @Test
    void shouldReturnNullWhenNotExists() {

        // arrange
        when(peopleRepository.findById(2)).thenReturn(Optional.empty());

        // act
        Person result = peopleService.findOne(2);

        // assert
        verify(peopleRepository).findById(2);
        assertNull(result);

    }


}
