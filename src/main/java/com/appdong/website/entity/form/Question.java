package com.appdong.website.entity.form;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Form form;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private String title;
    private String description;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @ElementCollection
    private List<String> choices;
}
