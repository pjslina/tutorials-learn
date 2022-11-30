package com.panjin.spring.patterns.proxy;

import org.springframework.stereotype.Repository;

/**
 * @author panjin
 */
@Repository
public class BookRepository {

    public Book create(String author) {
        return new Book(author);
    }
}
