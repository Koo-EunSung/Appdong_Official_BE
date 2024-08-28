package com.appdong.website.dto.answerSheet;

import com.appdong.website.dto.answer.AnswerRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class AnswerSheetRequest {
    @Getter
    @AllArgsConstructor
    public static class Create {
        @NotNull
        private Long formId;
        @NotNull
        private String name;
        @NotNull
        private String studentId;
        @NotBlank
        private List<AnswerRequest.Create> answer;
    }
}
