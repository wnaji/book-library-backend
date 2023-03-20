package app.service;

import app.repository.BookRepository;
import app.repository.CollectionRepository;
import app.web.book.Book;
import app.web.collection.Collection;
import app.web.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollectionService {

    @Autowired
    private final CollectionRepository collectionRepository;

    @Autowired
    private final BookRepository bookRepository;

    public CollectionService(CollectionRepository collectionRepository, BookRepository bookRepository) {
        this.collectionRepository = collectionRepository;
        this.bookRepository = bookRepository;
    }

    public List<Collection> getCollections(){
        return collectionRepository.findAll();
    }

    public Collection addNewCollection(Collection collection){
        collectionRepository.save(collection);
        return collection;
    }

    @Transactional
    public Collection addBookToCollection(Long isbn, Long collectionId){
        Book bookToAdd = bookRepository.findById(isbn).orElseThrow(() -> new IllegalArgumentException("Book Not Found"));
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new NotFoundException("collection","collectionId",collectionId));
        collection.getBookCollection()
                .add(bookToAdd);
        return collection;
    }

    @Transactional
    public Collection updateCollectionName(Long collectionId, String name){
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(() -> new NotFoundException("collection Id","collectionId",collectionId));
        if(name != null && name.length() > 0){
            collection.setName(name);
        }
        return collection;
    }


    public Collection bookToRemoveFromCollection(Long collectionId, Long isbnBook){
        Book bookToRemove = bookRepository.findById(isbnBook).orElseThrow(() -> new NotFoundException("book","isbnBook",isbnBook));
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new NotFoundException("collection","collectionId",collectionId));
        collection.getBookCollection().remove(bookToRemove);
        collectionRepository.save(collection);

        return collection;
    }

}
