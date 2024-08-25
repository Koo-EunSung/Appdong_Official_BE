package com.appdong.website.repository.answerSheet;

import com.appdong.website.entity.form.AnswerSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerSheetRepository extends JpaRepository<AnswerSheet, Long> {
}
