package com.appdong.website.dto.question;

import com.appdong.website.entity.form.QuestionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class QuestionRequest {
    @Getter
    @AllArgsConstructor
    public static class Create {
        @NotNull
        private String title;
        private String description;
        @NotNull
        private QuestionType type;
        private List<String> choices;
    }
}
