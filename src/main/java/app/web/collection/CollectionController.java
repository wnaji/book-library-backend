package app.web.collection;

import app.service.CollectionService;
import app.web.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import app.web.TrieStructure;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path= "api/collection")
public class CollectionController {
    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("")
    public LinkedHashSet<Collection> getCollections(){

        return TrieStructure.sort(collectionService.getCollections().stream().collect(Collectors.toSet()), Collection::getName);
    }

    @PostMapping
    public Collection addCollection(@RequestBody Collection newCollection){
        return collectionService.addNewCollection(newCollection);
    }

    @PutMapping(path = "/addBookToCollection")
    public Collection addBookToCollection(Long isbn, Long collectionId){
        return collectionService.addBookToCollection(isbn,collectionId);
    }


    @DeleteMapping(path="/removeBookFromCollection")
    public Collection deleteBook( Long id, Long isbnBook){
        return collectionService.bookToRemoveFromCollection(id,isbnBook);
    }

    @PutMapping(path ="{collectionId}")
    public Collection updateCollection(@PathVariable("collectionId") Long collectionId, String name){
        return collectionService.updateCollectionName(collectionId,name);
    }
}
