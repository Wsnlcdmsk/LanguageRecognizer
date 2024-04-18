/**
 * The Tag class represents a tag entity in the system.
 */
package com.project.langrecognizer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Data
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the tag.
     */
    @Column
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    /**
     * The texts associated with this tag.
     */
    @JsonIgnoreProperties({"tags", "ips"})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "texts_tags",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "text_id", referencedColumnName = "id"))
    private List<Text> texts;
}
