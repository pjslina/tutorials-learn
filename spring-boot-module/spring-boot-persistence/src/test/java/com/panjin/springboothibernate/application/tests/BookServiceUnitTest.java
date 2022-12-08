package com.panjin.springboothibernate.application.tests;

import com.panjin.springboothibernate.application.models.Book;
import com.panjin.springboothibernate.application.services.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 因为ExampleApplication类在com.panjin.springboothibernate.application下
 * 所以SpringBootTest注解不需要指定classes=ExampleApplication.class
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceUnitTest {

    @Autowired
    private BookService bookService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Book> books = bookService.list();

        Assert.assertEquals(books.size(), 3);
    }
}
