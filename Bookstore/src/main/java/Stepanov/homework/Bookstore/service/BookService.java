package Stepanov.homework.Bookstore.service;

import Stepanov.homework.Bookstore.dtoJPQL.DtoClassCountBooksByAuthorsJPQL;
import Stepanov.homework.Bookstore.dtoSQL.DtoClassCountBooksByAuthorsSQL;
import Stepanov.homework.Bookstore.dtoSQL.DtoInterfaceCountBooksByAuthors;
import Stepanov.homework.Bookstore.entity.Book;
import Stepanov.homework.Bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Book> findByAuthor_Id(Long id) {
        return bookRepository.findByAuthor_Id(id);
    }

    public List<Book> findByAuthor_Id_JPQL(Long id) {
        return bookRepository.findByAuthor_Id_JPQL(id);
    }

    public List<Book> findByAuthor_Id_SQL(Long id) {
        return bookRepository.findByAuthor_Id_SQL(id);
    }

    public List<DtoClassCountBooksByAuthorsJPQL> getCountBooksByAuthorsJPQL() {
        return bookRepository.getCountBooksByAuthorsJPQL();
    }

    public List<DtoClassCountBooksByAuthorsSQL> getCountBooksByAuthorsSQL() {
        ModelMapper modelMapper = new ModelMapper(); //объект ModelMapper для конвертации
        List<DtoInterfaceCountBooksByAuthors> list1 = bookRepository.getCountBooksByAuthorsSQL();
        List<DtoClassCountBooksByAuthorsSQL> list = list1.stream()
                .map(countBooksByAuthorsSQL -> modelMapper.map(countBooksByAuthorsSQL, DtoClassCountBooksByAuthorsSQL.class))
                .collect(Collectors.toList());
        return list;
    }

}
