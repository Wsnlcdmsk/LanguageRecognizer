/**
 * The TagRepository interface provides CRUD operations for Tag entities.
 */
package com.project.langrecognizer.repository;

import com.project.langrecognizer.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * Finds a tag entity by its name.
     *
     * @param name The name of the tag to search for.
     * @return An Optional containing the found Tag entity,
     * or empty if not found.
     */
    Optional<Tag> findByName(String name);
}
