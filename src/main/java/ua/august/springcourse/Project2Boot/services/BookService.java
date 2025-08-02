package ua.august.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.august.springcourse.Project2Boot.models.Book;
import ua.august.springcourse.Project2Boot.models.Person;
import ua.august.springcourse.Project2Boot.repositories.BookRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageable);
    }
    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }
    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }
    @Transactional
    public void delete (int id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Person> getBookOwner(int id) {
        return bookRepository.findById(id)
                .map(Book::getOwner);
    }
    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            bookRepository.save(book);
        });
    }
    @Transactional
    public void assign(int id, Person selectedPerson) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setOwner(selectedPerson);
            bookRepository.save(book);
        });
    }
    public List<Book> searchBooks(String letter) {
        return bookRepository.findByTitleIgnoreCaseStartingWith(letter);
    }
}
