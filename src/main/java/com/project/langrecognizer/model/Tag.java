package com.project.langrecognizer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NonNull;

@Data
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    @Size(min = 3,max = 30)
    private String name;
    @JsonIgnoreProperties({"tags", "ips"})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "texts_tags",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "text_id", referencedColumnName = "id"))
    private List<Text> texts;
}
