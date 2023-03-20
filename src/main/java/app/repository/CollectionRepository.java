package app.repository;

import app.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query("SELECT collection FROM Collection collection WHERE collection.name = ?1")
    Optional<Collection> findCollectionByName(String name);

}
