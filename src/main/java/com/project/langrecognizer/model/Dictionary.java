package com.project.langrecognizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Dictionary {
    @Id
    private int id;
    private String nameOfDictionary;
    private String nameOfLanguage;
    private int publication;
    private int yearOfPublication;
}
