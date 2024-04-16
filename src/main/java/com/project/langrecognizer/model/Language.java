package com.project.langrecognizer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @Column
    @NotBlank
    @Size(min = 3,max = 30)
    private String name;
    @OneToMany(mappedBy = "language", cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    @JsonIgnoreProperties("language")
    private List<Text> texts = new ArrayList<>();
}
