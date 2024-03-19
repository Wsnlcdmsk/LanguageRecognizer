package com.project.langrecognizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LANGUAGE_TBL")
public class Language {
    @Id
    @GeneratedValue
   // private int id;
    private String textOfLanguage;//todo
    private String name;
    //@OneToMany(targetEntity = Language.class,cascade = CascadeType.ALL)
    //@JoinColumn(name ="cp_fk",referencedColumnName = "id")
    //private List<Dictionary> dictionaries;
}
