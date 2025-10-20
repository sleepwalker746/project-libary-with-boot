package ua.august.springcourse.Project2Boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.august.springcourse.Project2Boot.entities.Book;
import ua.august.springcourse.Project2Boot.repositories.BookRepository;
import ua.august.springcourse.Project2Boot.services.BookService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @DisplayName("Проверка работы метода findById при наличии книги в БД")
    @Test
    void shouldReturnBookWhenExists() {
        Book book = new Book(1, "Title", "Author", 2020, null, null);
        // arrange
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // act
        Book result = bookService.findOne(1);

        // assert
        verify(bookRepository).findById(1);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("Title", result.getTitle()),
                () -> assertEquals(2020, book.getYear())
        );
    }
    @DisplayName("Проверка работы метода findById при отсутствии книги в БД")
    @Test
    void shouldReturnNullWhenNotExists() {
        // arrange
        when(bookRepository.findById(2)).thenReturn(Optional.empty());

        // act
        Book result = bookService.findOne(2);

        // assert
        verify(bookRepository).findById(2);
        assertNull(result);
    }
}
