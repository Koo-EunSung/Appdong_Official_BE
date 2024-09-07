package com.appdong.website.entity.form;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private AnswerSheet answerSheet;

    @ManyToOne
    @JoinColumn
    private Question question;

    @Column(length = 500)
    private String answer;
}
