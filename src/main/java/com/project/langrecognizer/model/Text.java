/**
 * The Text class represents a text entity in the system.
 */
package com.project.langrecognizer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Text {
    /** The id of the text. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The minimum length of a text content. */
    private static final int MIN_CONTENT_LENGHT = 3;

    /** The maximum length of a text content. */
    private static final int MAX_CONTENT_LENGHT = 500;


    /**
     * The content of the text.
     */
    @Column
    @NotBlank
    @Size(min = MIN_CONTENT_LENGHT, max = MAX_CONTENT_LENGHT)
    private String content;

    /**
     * The language of the text.
     */
    @JsonIgnoreProperties("texts")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "language_id")
    private Language language;

    /**
     * The tags associated with this text.
     */
    @JsonIgnoreProperties("texts")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "texts_tags",
            joinColumns =
            @JoinColumn(name = "text_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;
}
