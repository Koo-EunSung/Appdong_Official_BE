package com.appdong.website.repository.answer;

import com.appdong.website.entity.form.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerJdbcRepositoryImpl implements AnswerJdbcRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveAllInBulk(List<Answer> answers) {
        String sql = "INSERT INTO answer (answer_sheet_id, question_id, answer)" + "VALUES (?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Answer answer = answers.get(i);
                ps.setLong(1, answer.getAnswerSheet().getId());
                ps.setLong(2, answer.getQuestion().getId());
                ps.setString(3, answer.getAnswer());
            }

            @Override
            public int getBatchSize() {
                return answers.size();
            }
        });
    }
}
