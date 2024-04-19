package Stepanov.homework.Bookstore.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(nullable = false)
    private Author author;

    @Column
    private Integer year_of_publication;

    @Column
    private Integer pages;

    @Column(nullable = false)
    private Integer price;

    public Book(String title, Author author, Integer year_of_publication, Integer pages, Integer price) {
        this.title = title;
        this.author = author;
        this.year_of_publication = year_of_publication;
        this.pages = pages;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author_id=" + author.getId() +
                ", year_of_publication=" + year_of_publication +
                ", pages=" + pages +
                ", price=" + price +
                '}';
    }
}
