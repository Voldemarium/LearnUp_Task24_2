package Stepanov.homework.Bookstore.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column
    private String name;

    @Column
    private String middle_name;

    @Column
    private LocalDate birth_date;

    public Buyer(String surname, String name, String middle_name, LocalDate birth_date) {
        this.surname = surname;
        this.name = name;
        this.middle_name = middle_name;
        this.birth_date = birth_date;
    }
}
