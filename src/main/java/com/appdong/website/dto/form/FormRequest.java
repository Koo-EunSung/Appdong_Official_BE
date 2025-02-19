package com.appdong.website.dto.form;

import com.appdong.website.dto.question.QuestionRequest;
import com.appdong.website.entity.form.Tag;
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
        @NotNull
        private Tag tag;
        @NotEmpty
        private List<QuestionRequest.Create> questions;
    }

    @Getter
    @AllArgsConstructor
    public static class Update {
        private Long id;
        private String title;
        private String description;
        private Tag tag;
        private boolean updateActive;
    }
}
