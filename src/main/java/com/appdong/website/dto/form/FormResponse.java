package com.appdong.website.dto.form;

import com.appdong.website.dto.question.QuestionResponse;
import com.appdong.website.entity.form.Form;
import com.appdong.website.entity.form.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class FormResponse {
    @Getter
    @AllArgsConstructor
    public static class Introduction {
        private Long id;
        private String title;
        private String description;
        private boolean active;

        public static Introduction from(Form form) {
            return new Introduction(form.getId(), form.getTitle(), form.getDescription(), form.isActive());
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Detail {
        private Long id;
        private String title;
        private String description;
        private List<QuestionResponse.Info> questions;
        private boolean active;

        public static Detail from(Form form) {
            List<QuestionResponse.Info> questions = form.getQuestions().stream()
                    .map(QuestionResponse.Info::from)
                    .toList();

            return new Detail(form.getId(), form.getTitle(), form.getDescription(), questions, form.isActive());
        }
    }
}
