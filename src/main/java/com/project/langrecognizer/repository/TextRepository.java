package com.project.langrecognizer.repository;

import com.project.langrecognizer.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TextRepository extends JpaRepository<Text,Long> {
    Optional<Text> findByContent(String content);

    @Query(value = "SELECT  DISTINCT t.content FROM Text t "
            + "JOIN texts_tags ttg ON ttg.text_id = t.id "
            + "JOIN tag tg ON tg.id = ttg.tag_id "
            + "WHERE tg.name = :tag ORDER BY t.content ASC",
            nativeQuery = true)
    List<String> findTextsSortedByTag(@Param("tag") String tag);

    @Query(value = "SELECT DISTINCT t.content FROM Text t "
            + "JOIN t.language l "
            + "WHERE l.name = :language ORDER BY t.content ASC")
    List<String> findTextsSortedByLanguage(@Param("language") String language);

}