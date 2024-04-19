package Stepanov.homework.Bookstore;

import Stepanov.homework.Bookstore.entity.*;

import java.time.LocalDate;
import java.util.HashMap;

public class MockDB {
    HashMap<Long, Author> authors = new HashMap<>();
    HashMap<Long, Book> books = new HashMap<>();
    HashMap<Long, BookWarehouse> bookWarehouses = new HashMap<>();
    HashMap<Long, Buyer> buyers = new HashMap<>();
    HashMap<Long, Ordering> orderings = new HashMap<>();
    HashMap<Long, OrderingDetails> orderingDetailsHashMap = new HashMap<>();

    public MockDB() {
        authors.put(1L, new Author(1L, "Petrov", "Ivan", "Ivanovich"));
        authors.put(2L, new Author(2L, "Ivanov", "Ivan", "Petrovich"));

        books.put(1L, new Book(1L, "Summer", authors.get(1L), 2002, 220, 600));
        books.put(2L, new Book(2L, "Spring", authors.get(1L), 2004, 200, 800));
        books.put(3L, new Book(3L, "Winter", authors.get(2L), 2006, 180, 800));
        books.put(4L, new Book(4L, "Autumn", authors.get(2L), 2005, 270, 1000));

        bookWarehouses.put(1L, new BookWarehouse(1L, books.get(1L), 4));
        bookWarehouses.put(2L, new BookWarehouse(2L, books.get(2L), 3));
        bookWarehouses.put(3L, new BookWarehouse(3L, books.get(3L), 5));
        bookWarehouses.put(4L, new BookWarehouse(4L, books.get(4L), 3));

        buyers.put(1L, new Buyer(1L, "Kozlov", "Vlad", "Ivanovich",
                LocalDate.of(1999, 1, 1)));
        buyers.put(2L, new Buyer(2L, "Maslov", "Vova", "Petrovich",
                LocalDate.of(1994, 5, 4)));

        orderings.put(1L, new Ordering(1L, buyers.get(1L), 2000)) ;

        orderingDetailsHashMap.put(1L, new OrderingDetails(1L, orderings.get(1L), books.get(1L), 2));
        orderingDetailsHashMap.put(2L, new OrderingDetails(2L, orderings.get(1L), books.get(2L), 1));
    }
}
