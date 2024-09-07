package com.appdong.website.dto.answerSheet;

import com.appdong.website.dto.answer.AnswerResponse;
import com.appdong.website.dto.question.QuestionResponse;
import com.appdong.website.entity.form.Answer;
import com.appdong.website.entity.form.AnswerSheet;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class AnswerSheetResponse {
    @Getter
    @AllArgsConstructor
    public static class AllInfo {
        private List<QuestionResponse.Info> question;
        private List<AnswerSheetResponse.Info> answerSheet;
    }

    @Getter
    @AllArgsConstructor
    public static class Info {
        private LocalDateTime createdAt;
        private String name;
        private String studentId;
        private String phoneNumber;
        private List<AnswerResponse.Info> answers;

        public static Info from(AnswerSheet answerSheet) {
            List<AnswerResponse.Info> answers = answerSheet.getAnswers().stream()
                    .map(AnswerResponse.Info::from)
                    .toList();

            return new Info(answerSheet.getCreatedAt(), answerSheet.getName(), answerSheet.getStudentId(),answerSheet.getPhoneNumber(), answers);
        }
    }
}
