package Stepanov.homework.Bookstore;

import Stepanov.homework.Bookstore.service.AuthorService;
import Stepanov.homework.Bookstore.service.BookService;
import Stepanov.homework.Bookstore.service.BookWarehouseService;
import Stepanov.homework.Bookstore.service.BuyerService;
import Stepanov.homework.Bookstore.entity.*;
import Stepanov.homework.Bookstore.service.OrderService;
import Stepanov.homework.Bookstore.service.OrderingDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);

        AuthorService authorService = context.getBean(AuthorService.class);
        BookService bookService = context.getBean(BookService.class);
        BookWarehouseService bookWarehouseService = context.getBean(BookWarehouseService.class);
        BuyerService buyerService = context.getBean(BuyerService.class);
        OrderService orderService = context.getBean(OrderService.class);
        OrderingDetailsService orderingDetailsService = context.getBean(OrderingDetailsService.class);

        Author author1 = new Author("Petrov", "Ivan", "Ivanovich");
        Author author2 = new Author("Ivanov", "Ivan", "Petrovich");
//        authorService.createAuthor(author1);
//        authorService.createAuthor(author2);

        Book book1 = new Book("Summer", author1, 2002, 220, 600);
        Book book2 = new Book("Spring", author1, 2004, 200, 800);
        Book book3 = new Book("Winter", author2, 2006, 180, 800);
        Book book4 = new Book("Autumn", author2, 2005, 270, 1000);
//        bookService.createBook(book1);
//        bookService.createBook(book2);
//        bookService.createBook(book3);
//        bookService.createBook(book4);

        BookWarehouse bookWarehouse1 = new BookWarehouse(book1, 4);
        BookWarehouse bookWarehouse2 = new BookWarehouse(book2, 3);
        BookWarehouse bookWarehouse3 = new BookWarehouse(book3, 5);
        BookWarehouse bookWarehouse4 = new BookWarehouse(book4, 3);

//        bookWarehouseService.createBookWarehouse(bookWarehouse1);
//        bookWarehouseService.createBookWarehouse(bookWarehouse2);
//        bookWarehouseService.createBookWarehouse(bookWarehouse3);
//        bookWarehouseService.createBookWarehouse(bookWarehouse4);

       Buyer buyer1 = new Buyer("Kozlov", "Vlad", "Ivanovich",
                LocalDate.of(1999, 1, 1));
       Buyer buyer2 = new Buyer("Maslov", "Vova", "Petrovich",
                LocalDate.of(1994, 5, 4));

//        buyerService.createBuyer(buyer1);
//        buyerService.createBuyer(buyer2);

        Ordering ordering1 = new Ordering();
        ordering1.setBuyer(buyerService.getBuyerById(1L));
        OrderingDetails orderingDetails1_1 = new OrderingDetails();
        orderingDetails1_1.setOrdering(ordering1);
        orderingDetails1_1.setBook(bookService.getBookById(1L));
        orderingDetails1_1.setQuantity(2);

        OrderingDetails orderingDetails1_2 = new OrderingDetails();
        orderingDetails1_2.setOrdering(ordering1);
        orderingDetails1_2.setBook(bookService.getBookById(2L));
        orderingDetails1_2.setQuantity(1);
        ordering1.setOrderingDetailsList(List.of(orderingDetails1_1, orderingDetails1_2));
 //       log.info("Order1: {}", ordering1);

   // УДАЛЕНИЕ СТАРЫХ И СОЗДАНИЕ НОВЫХ ОРДЕРОВ
//        orderService.deleteOrders();
//        orderService.createOrdering(ordering1);

//        log.info("Authors: {}", authorService.getAuthors());
//        log.info("Books: {}", bookService.getBooks());
//        log.info("BookWareHouse: {}", bookWarehouseService.getBooks());
//        log.info("Buyers: {}", buyerService.getBuyers());

        log.info("Orders: {}", orderService.getOrderingById(1L));
//                log.info("Orders: {}", orderService.getOrderings());
        log.info("OrderDetailList: {}", orderingDetailsService.getOrderingDetailsById(1L));

//        log.info("OrderDetailList: {}", orderingDetailsService.getOrderingDetails());

//        log.info("Count Books By Authors: {}", bookService.getCountBooksByAuthorsJPQL());
    }
}
