package Stepanov.homework.Bookstore.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    private Ordering ordering;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "book_id", referencedColumnName = "id"),
            @JoinColumn(name = "price", referencedColumnName = "price")
    })
    private Book book;

    @Column
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderingDetails{" +
                "id=" + id +
                ", ordering_id=" + ordering.getId() +
                ", book_id=" + book.getId() +
                ", price=" + book.getPrice() +
                ", quantity=" + quantity +
                '}';
    }
}
