package com.project.langrecognizer.repository;

import com.project.langrecognizer.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    Language findByName(String name);
}
