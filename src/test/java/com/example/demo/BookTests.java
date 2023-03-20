package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import app.repository.BookRepository;
import app.service.BookService;
import app.web.book.Book;
import app.web.book.BookController;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

public class BookTests extends DemoApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private BookRepository bookRepository;

    @Autowired
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetBook() throws Exception {
        mockMvc.perform(delete("/api/book/2")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.title").value("Pokemon"))
                .andExpect(jsonPath("$.author").value("Sacha"));
    }

    @Test
    public void testAddBook() throws Exception {

        Book book = new Book("HP","HP1");
        when(bookRepository.save(book)).thenReturn(book);
        Book actualBook = bookController.addBook(book);
        Assert.assertEquals(book.getISBN(), actualBook.getISBN());
        Assert.assertEquals(book.getTitle(), actualBook.getTitle());
        Assert.assertEquals(book.getAuthor(), actualBook.getAuthor());
    }

    @Test
    public void testGetBooksSorted() throws Exception {
        Book book1 = new Book("Harry Potter", "J.K Rowling");
        Book book2 = new Book("Lord of The Rings", "Tolkien");
        Book book3 = new Book("Pokemon", "Sacha");
        Book book4 = new Book("Zetman", "Unknown");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        LinkedHashSet<Book> mySortedList = bookController.getBooks();
        // Verify that they have the same size
        Assert.assertEquals(books.size(),mySortedList.size());

        Iterator<Book> listIterator = books.iterator();
        Iterator<Book> setIterator = mySortedList.iterator();
        while (listIterator.hasNext() && setIterator.hasNext()) {
            Book listBook = listIterator.next();
            Book setBook = setIterator.next();
            Assert.assertEquals(listBook.getTitle(), setBook.getTitle());
        }
    }
}