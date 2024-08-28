package com.appdong.website.dto.answer;

import com.appdong.website.entity.form.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class AnswerResponse {
    @Getter
    @AllArgsConstructor
    public static class Info {
        private Long questionId;
        private String answer;

        public static Info from(Answer answer) {
            return new Info(answer.getQuestion().getId(), answer.getAnswer());
        }
    }
}
