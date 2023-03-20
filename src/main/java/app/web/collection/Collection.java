package app.web.collection;

import app.web.book.Book;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
public class Collection {

    @Id
    @SequenceGenerator(
            name="collection_name",
            sequenceName = "collection_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "collection_sequence"
    )
    private Long id;

    @ManyToMany
    @JoinTable(
            name="book_collection",
            joinColumns = @JoinColumn(name="collection_id"),
            inverseJoinColumns = @JoinColumn(name="book_id")
    )
    private Set<Book> bookCollection;
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private LocalDateTime updateDateTime;



    public Collection(Long id, Set<Book> bookCollection,String name) {
        this.id = id;
        this.bookCollection = bookCollection;
        this.name = name;
    }

    public Collection() {

    }

    public Collection(String name, Set<Book> bookCollection) {
        this.name = name;
        this.bookCollection = bookCollection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Book> getBookCollection() {
        return bookCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public void setBookCollection(Set<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", bookCollection=" + bookCollection +
                '}';
    }
}
