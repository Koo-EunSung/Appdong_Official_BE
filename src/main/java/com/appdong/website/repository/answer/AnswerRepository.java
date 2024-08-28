package com.appdong.website.repository.answer;

import com.appdong.website.entity.form.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerJdbcRepository {
}
