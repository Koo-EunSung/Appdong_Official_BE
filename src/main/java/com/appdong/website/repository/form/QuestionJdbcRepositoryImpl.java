package com.appdong.website.repository.form;

import com.appdong.website.entity.form.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionJdbcRepositoryImpl implements QuestionJdbcRepository{
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    public void saveAllInBulk(List<Question> questions) {
        String sql = "INSERT INTO question (form_id, type, title, description, choice, required)" + "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Question question = questions.get(i);
                ps.setLong(1, question.getForm().getId());
                ps.setString(2, question.getType().name());
                ps.setString(3, question.getTitle());
                ps.setString(4, question.getDescription());
                ps.setBoolean(5, question.isRequired());
                try {
                    ps.setString(5, objectMapper.writeValueAsString(question.getChoice()));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public int getBatchSize() {
                return questions.size();
            }
        });
    }
}
