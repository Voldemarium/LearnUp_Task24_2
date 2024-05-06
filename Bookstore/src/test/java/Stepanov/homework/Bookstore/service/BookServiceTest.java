package Stepanov.homework.Bookstore.service;

import Stepanov.homework.Bookstore.MockDB;
import Stepanov.homework.Bookstore.dtoJPQL.DtoClassCountBooksByAuthorsJPQL;
import Stepanov.homework.Bookstore.dtoSQL.DtoClassCountBooksByAuthorsSQL;
import Stepanov.homework.Bookstore.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@ContextConfiguration(initializers = {BookServiceTest.Initializer.class})
@Testcontainers
class BookServiceTest {

    @Autowired
    BookService bookService;

    MockDB mockDB;

    @BeforeEach
    void setUp() {
        //DB с объектами для сравнения
        mockDB = new MockDB();
    }

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("myTestBookStore")
            .withUsername("vladimir")
            .withPassword("postgres")
            .withInitScript("db.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    @Transactional
    void getBookById() {
        Book bookActual = bookService.getBookById(2L);
        Book bookExpected = mockDB.getBooks().get(2L);
        Assertions.assertEquals(bookActual, bookExpected);
    }

    @Test
    @Transactional
    void findBooksByAuthor() {
        List<Book> booksActual = bookService.findByAuthor_Id(1L);
        List<Book> booksExpected = mockDB.getBooks().values()
                .stream()
                .filter(book -> book.getAuthor().getId().equals(1L))
                .collect(Collectors.toList());
        Assertions.assertEquals(booksActual, booksExpected);
    }

    @Test
    @Transactional
    void findBooksByAuthor_JPQL() {
        List<Book> booksActual = bookService.findByAuthor_Id_JPQL(1L);
        List<Book> booksExpected = mockDB.getBooks().values()
                .stream()
                .filter(book -> book.getAuthor().getId().equals(1L))
                .collect(Collectors.toList());
        Assertions.assertEquals(booksActual, booksExpected);
    }

    @Test
    @Transactional
    void findBooksByAuthor_SQL() {
        List<Book> booksActual = bookService.findByAuthor_Id_SQL(1L);
        List<Book> booksExpected = mockDB.getBooks().values()
                .stream()
                .filter(book -> book.getAuthor().getId().equals(1L))
                .collect(Collectors.toList());
        Assertions.assertEquals(booksActual, booksExpected);
    }

    @Test
    @Transactional
    void getCountBooksByAuthorsJPQL() {
        List<DtoClassCountBooksByAuthorsJPQL> countsActual = bookService.getCountBooksByAuthorsJPQL();

        DtoClassCountBooksByAuthorsJPQL countBooksByAuthors1 =
                new DtoClassCountBooksByAuthorsJPQL(mockDB.getAuthors().get(1L), 2L);
        DtoClassCountBooksByAuthorsJPQL countBooksByAuthors2 =
                new DtoClassCountBooksByAuthorsJPQL(mockDB.getAuthors().get(2L), 2L);
        List<DtoClassCountBooksByAuthorsJPQL> countsExpected = List.of(countBooksByAuthors1, countBooksByAuthors2);

        Assertions.assertEquals(countsExpected,countsActual);
    }

    @Test
    @Transactional
    void getCountBooksByAuthorsSQL() {
        List<DtoClassCountBooksByAuthorsSQL> countsActual = bookService.getCountBooksByAuthorsSQL();

        DtoClassCountBooksByAuthorsSQL countBooksByAuthors1 =
                new DtoClassCountBooksByAuthorsSQL(1L, 2L);
        DtoClassCountBooksByAuthorsSQL countBooksByAuthors2 =
                new DtoClassCountBooksByAuthorsSQL(2L, 2L);
        List<DtoClassCountBooksByAuthorsSQL> countsExpected = List.of(countBooksByAuthors1, countBooksByAuthors2);

        Assertions.assertEquals(countsExpected,countsActual);
    }
}