package Stepanov.homework.Bookstore.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Book book;

    @Column
    private Integer balance;

    public BookWarehouse(Book book, Integer balance) {
        this.book = book;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                "book_id=" + book.getId() +
                ", balance=" + balance +
                '}';
    }
}
