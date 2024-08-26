package com.appdong.website.repository.answerSheet;

import com.appdong.website.entity.form.AnswerSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerSheetRepository extends JpaRepository<AnswerSheet, Long> {
    @Query("select distinct a from AnswerSheet a LEFT join fetch a.answers where a.form.id = :formId")
    List<AnswerSheet> findAllByFormIdFetchJoinAnswers(Long formId);
}
