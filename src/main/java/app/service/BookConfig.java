package app.service;

import app.repository.CollectionRepository;
import app.model.Book;
import app.repository.BookRepository;
import app.model.Collection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner (BookRepository bookRepository, CollectionRepository collectionRepository){
        return agr -> {
            Book b1 = new Book("Harry Potter", "J.K Rowling");
            Book b2 = new Book("Lord of The Rings", "Tolkien");
            Book b3 = new Book("Pokemon", "Sacha");
            Book b4 = new Book("Zetman", "Unknown");

            Set<Book> c1 = new HashSet<>();
            c1.add(b1);
            Set<Book> c2 = new HashSet<>();
            c1.add(b1);
            Set<Book> c3 = new HashSet<>();
            c1.add(b1);
            Set<Book> c4= new HashSet<>();
            c1.add(b1);

            Collection collection1 = new Collection("Collection",c1);
            Collection collection2 = new Collection("Xavier",c2);
            Collection collection3 = new Collection("Damien",c3);
            Collection collection4 = new Collection("François",c4);

            bookRepository.saveAll(List.of(b1,b3,b4,b2));
            collectionRepository.saveAll(List.of(collection1,collection2,collection3,collection4));
        };


    }
}
