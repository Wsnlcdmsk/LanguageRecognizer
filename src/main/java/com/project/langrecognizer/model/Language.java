/**
 * The Language class represents a language entity in the system.
 */
package com.project.langrecognizer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    /** The id of the language. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The minimum length of a language name. */
    private static final int MIN_NAME_LENGHT = 3;

    /** The maximum length of a language name. */
    private static final int MAX_NAME_LENGHT = 30;


    /**
     * The name of the language.
     */
    @Column
    @NotBlank
    @Size(min = MIN_NAME_LENGHT, max = MAX_NAME_LENGHT)
    private String name;

    /**
     * The texts associated with this language.
     */
    @JsonIgnoreProperties("texts_language")
    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Text> texts = new ArrayList<>();
}
