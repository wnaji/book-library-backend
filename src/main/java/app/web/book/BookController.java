package app.web.book;

import app.service.BookService;
import app.web.TrieStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public LinkedHashSet<Book> getBooks(){
        return TrieStructure.sort(bookService.getBooks().stream().collect(Collectors.toSet()), Book::getTitle);
    }

    @PostMapping
    public Book addBook(@RequestBody Book newBook){
        return bookService.addNewBook(newBook);
    }

    @DeleteMapping(path="{bookIsbn}")
    public Optional<Book> deleteBook(@PathVariable("bookIsbn") Long isbn){
        return bookService.deleteBook(isbn);
    }

    @PutMapping(path = "{isbn}")
    public Book updateBook(@PathVariable("isbn") Long isbn, @RequestParam(required=false) String title, @RequestParam(required=false) String author){

        return bookService.updateBook(isbn,title,author);
    }
}
