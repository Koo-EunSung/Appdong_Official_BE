package com.appdong.website.dto.answer;

import com.appdong.website.entity.form.Answer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class AnswerRequest {
    @Getter
    @AllArgsConstructor
    public static class Create {
        @NotNull
        private Long questionId;
        private String answer;
    }
}
