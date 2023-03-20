package app.service;

import app.web.book.Book;
import app.repository.BookRepository;
import app.web.exceptions.AlreadyExistsException;
import app.web.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book addNewBook(Book newBook) {
        if(bookRepository.findBookByTitle(newBook.getTitle()).isPresent() && bookRepository.findBookByAuthor(newBook.getAuthor()).isPresent()){
            throw new AlreadyExistsException(newBook.getTitle());
        }
        return bookRepository.save(newBook);
    }

    public Optional<Book> deleteBook(Long isbn) {
        Optional<Book> bookToDelete = bookRepository.findById(isbn);
        if(!bookRepository.existsById(isbn)){
            throw new NotFoundException("isbn","book",isbn);
        }
        bookRepository.deleteById(isbn);
        return bookToDelete;
    }

    @Transactional
    public Book updateBook(Long isbn, String title, String author) {
        Book bookToUpdate = bookRepository.findById(isbn).orElseThrow(() -> new NotFoundException("isbn","book",isbn));
        if(title != null && title.length() > 0 && !Objects.equals(title, bookToUpdate.getTitle())){
            bookToUpdate.setTitle(title);
        }
        if(author != null && author.length() > 0 && !Objects.equals(author, bookToUpdate.getAuthor())){
            bookToUpdate.setAuthor(author);
        }
        return bookToUpdate;
    }
}
