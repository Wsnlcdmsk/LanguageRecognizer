package com.project.langrecognizer.repository;

import com.project.langrecognizer.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text,Long> {
    Text findByContent(String content);

    @Query(value = "SELECT txt.text FROM Text txt"
            + "JOIN text_tag tt ON tt.text_id = txt.id"
            + "JOIN tag t ON t.id = tt.tag_id"
            + "Where t.name = :tag ORDER BY txt.text ASC", nativeQuery = true)
    List<String> findTagsSortedByText(@Param("tag") String tag);
}