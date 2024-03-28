package com.project.langrecognizer.repository;

import com.project.langrecognizer.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text,Long> {
    @Query(value = "SELECT * FROM text WHERE content = :content", nativeQuery = true)
    List<Text> findByContent(@Param("content") String content);
}