package Stepanov.homework.Bookstore.dtoSQL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoClassCountBooksByAuthorsSQL implements DtoInterfaceCountBooksByAuthors {
   private Long author_id;
   private Long count;
}
