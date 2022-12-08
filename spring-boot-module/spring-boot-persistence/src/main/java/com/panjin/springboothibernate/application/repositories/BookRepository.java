package com.panjin.springboothibernate.application.repositories;

import com.panjin.springboothibernate.application.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author panjin
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
