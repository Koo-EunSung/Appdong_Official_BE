package com.appdong.website.entity.form;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "form")
    private List<AnswerSheet> answerSheets = new ArrayList<>();

    @ColumnDefault("true")
    private boolean active;

    @CreatedDate
    private LocalDateTime createdAt;

    public Form(String title, String description) {
        this.title = title;
        this.description = description;
        this.active = true;
    }

    public void activationToggle() {
        this.active = !this.active;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
