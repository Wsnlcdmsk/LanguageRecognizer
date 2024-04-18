/**
 * The Language class represents a language entity in the system.
 */
package com.project.langrecognizer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the language.
     */
    @Column
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    /**
     * The texts associated with this language.
     */
    @JsonIgnoreProperties("texts_language")
    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Text> texts = new ArrayList<>();
}
