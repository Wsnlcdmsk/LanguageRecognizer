package com.project.langrecognizer.repository;

import com.project.langrecognizer.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TextRepository extends JpaRepository<Text,Long> {
    Optional<Text> findByContent(String content);
}