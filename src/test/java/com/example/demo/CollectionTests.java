package com.example.demo;

import app.repository.BookRepository;
import app.repository.CollectionRepository;
import app.service.CollectionService;
import app.web.book.Book;
import app.web.collection.Collection;
import app.web.collection.CollectionController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.Mockito.when;

public class CollectionTests extends DemoApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private CollectionRepository collectionRepository;

    @Mock
    private CollectionService collectionService;

    @Autowired
    private CollectionController collectionController;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        collectionRepository.deleteAll();
        Set<Book> setBook = new HashSet<>();
        Collection c1 = new Collection(5L, setBook, "Marc");
        Collection c2 = new Collection(6L, setBook, "Dominic");
        Collection c3 = new Collection(7L, setBook, "Bertrand");
        Collection c4 = new Collection(8L, setBook, "Alan");

        collectionRepository.saveAll(List.of(c1,c2,c3,c4));
    }

    @Test
    public void testAddCollection() {
        Set<Book> listBook = new HashSet<>();
        Collection collection = new Collection(5L, listBook, "Test");

        Collection actualCollection = collectionController.addCollection(collection);
        Assert.assertEquals(collection.getId(), actualCollection.getId());
        Assert.assertEquals(collection.getName(), actualCollection.getName());
    }

    @Test
    public void testAddBookToCollection(){
        // Create a book and save it to the repository
        Book bookToAdd = new Book("The Great Gatsby", "Fitzgerald");
        bookRepository.save(bookToAdd);
        // Add the book to the collection using the service
        Collection updatedCollection = collectionController.addBookToCollection(bookToAdd.getISBN(), 7L );
        System.out.println(updatedCollection.getName());
        // Verify that the book was added to the collection
        Assert.assertEquals("The Great Gatsby", updatedCollection.getBookCollection().iterator().next().getTitle());
    }

    @Test
    public void testGetCollectionsSorted() {
        List<Collection> collections = collectionRepository.findAll();
        Collections.sort(collections, Comparator.comparing(Collection::getName));

        LinkedHashSet<Collection> mySortedList = collectionController.getCollections();
        Assert.assertEquals(mySortedList.size(),collections.size());
        Iterator<Collection> listIterator = collections.iterator();
        Iterator<Collection> setIterator = mySortedList.iterator();

        while (listIterator.hasNext() && setIterator.hasNext()) {
            Collection listBook = listIterator.next();
            Collection collection = setIterator.next();
            Assert.assertEquals(collection.getName(), listBook.getName());
        }
    }
}