package Stepanov.homework.Bookstore.repository;

import Stepanov.homework.Bookstore.dtoJPQL.DtoClassCountBooksByAuthorsJPQL;
import Stepanov.homework.Bookstore.dtoSQL.DtoInterfaceCountBooksByAuthors;
import Stepanov.homework.Bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor_Id(Long id);

    @Query(value = "FROM Book b WHERE b.author.id=1")
    List<Book> findByAuthor_Id_JPQL(Long id);

    @Query(value = "SELECT * FROM book b WHERE b.author_id=1", nativeQuery = true)
    List<Book> findByAuthor_Id_SQL(Long id);

    //JPQL запрос (Количество книг у каждого автора)
    @Query(value =
            "SELECT new Stepanov.homework.Bookstore.dtoJPQL.DtoClassCountBooksByAuthorsJPQL(b.author, COUNT(b))\n" +
                    "from Book b\n" +
                    "GROUP by b.author\n" +
                    "ORDER by b.author")
    List<DtoClassCountBooksByAuthorsJPQL> getCountBooksByAuthorsJPQL();

    //SQL запрос (Количество книг у каждого автора)
    @Query(value =
            "SELECT author_id, COUNT(*)\n" +
                    "from book\n" +
                    "GROUP by author_id\n" +
                    "ORDER by author_id",
            nativeQuery = true)
    List<DtoInterfaceCountBooksByAuthors> getCountBooksByAuthorsSQL();

}

