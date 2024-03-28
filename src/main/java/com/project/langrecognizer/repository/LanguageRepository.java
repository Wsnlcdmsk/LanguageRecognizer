package com.project.langrecognizer.repository;

import com.project.langrecognizer.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Long> {
    @Query(value = "SELECT * FROM language WHERE name = :name", nativeQuery = true)
    List<Language> findByName(@Param("name") String name);
}
