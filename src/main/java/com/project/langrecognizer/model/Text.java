package com.project.langrecognizer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
    @ManyToOne
    @JsonIgnoreProperties("texts")
    @JoinColumn(name = "language_id")
    private Language language;
    @JsonIgnoreProperties("texts")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "texts_tags",
    joinColumns = @JoinColumn(name = "text_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

}
