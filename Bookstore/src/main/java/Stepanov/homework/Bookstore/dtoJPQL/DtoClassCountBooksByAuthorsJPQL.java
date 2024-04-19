package Stepanov.homework.Bookstore.dtoJPQL;

import Stepanov.homework.Bookstore.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode
public class DtoClassCountBooksByAuthorsJPQL {
    private Author author;
    private Long count;
}
