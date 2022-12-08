package com.panjin.springboothibernate.application.services;

import com.panjin.springboothibernate.application.models.Book;
import com.panjin.springboothibernate.application.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author panjin
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> list() {
        return bookRepository.findAll();
    }
}
