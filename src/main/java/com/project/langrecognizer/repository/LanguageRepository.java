/**
 * The LanguageRepository interface provides CRUD operations for Language entities.
 */
package com.project.langrecognizer.repository;

import com.project.langrecognizer.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    /**
     * Finds a language entity by its name.
     *
     * @param name The name of the language to search for.
     * @return An Optional containing the found Language entity, or empty if not found.
     */
    Optional<Language> findByName(String name);
}
