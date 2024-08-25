package com.appdong.website.repository.answer;

import com.appdong.website.entity.form.Answer;

import java.util.List;

public interface AnswerJdbcRepository {
    void saveAllInBulk(List<Answer> answers);
}
