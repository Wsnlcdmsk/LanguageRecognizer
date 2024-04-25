/**
 * The Tag class represents a tag entity in the system.
 */
package com.project.langrecognizer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@Data
@Entity
public class Tag {
    /** The id of the text. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The minimum length of a text content. */
    private static final int MIN_NAME_LENGHT = 3;

    /** The maximum length of a text content. */
    private static final int MAX_NAME_LENGHT = 30;


    /**
     * The name of the tag.
     */
    @Column
    @NotBlank
    @Size(min = MIN_NAME_LENGHT, max = MAX_NAME_LENGHT)
    private String name;

    /**
     * The texts associated with this tag.
     */
    @JsonIgnoreProperties({"tags", "ips"})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "texts_tags",
            joinColumns =
            @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "text_id", referencedColumnName = "id"))
    private List<Text> texts;
}
