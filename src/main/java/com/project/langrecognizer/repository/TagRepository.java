package com.project.langrecognizer.repository;

import com.project.langrecognizer.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    @Query(value = "SELECT * FROM tag WHERE name = :name", nativeQuery = true)
    List<Tag> findByName(@Param("name") String name);
}
