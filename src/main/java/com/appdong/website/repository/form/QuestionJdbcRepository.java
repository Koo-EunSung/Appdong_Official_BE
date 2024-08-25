package com.appdong.website.repository.form;

import com.appdong.website.entity.form.Question;

import java.util.List;

public interface QuestionJdbcRepository {
    void saveAllInBulk(List<Question> questions);
}
