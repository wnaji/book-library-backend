package app.repository;

import app.web.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT book FROM Book book WHERE book.title = ?1")
    Optional<Book> findBookByTitle(String title);
    @Query("SELECT book FROM Book book WHERE book.author = ?1")
    Optional<Book> findBookByAuthor(String title);
}
