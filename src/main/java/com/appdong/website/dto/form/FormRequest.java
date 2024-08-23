package com.appdong.website.dto.form;

import com.appdong.website.dto.question.QuestionRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class FormRequest {
    @Getter
    @AllArgsConstructor
    public static class Create {
        @NotNull
        private String title;
        private String description;
        @NotEmpty
        private List<QuestionRequest.Create> questions;
    }
}
