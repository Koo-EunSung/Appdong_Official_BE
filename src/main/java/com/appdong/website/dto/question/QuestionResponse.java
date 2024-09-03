package com.appdong.website.dto.question;

import com.appdong.website.entity.form.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class QuestionResponse {
    @Getter
    @AllArgsConstructor
    public static class Info {
        private Long id;
        private String title;
        private String description;
        private List<String> choice;

        public static Info from(Question question) {
            return new Info(question.getId(), question.getTitle(), question.getDescription(), question.getChoice());
        }
    }
}
