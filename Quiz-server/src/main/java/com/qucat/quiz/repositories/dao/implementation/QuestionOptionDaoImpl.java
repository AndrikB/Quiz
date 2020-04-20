package com.qucat.quiz.repositories.dao.implementation;

import com.qucat.quiz.repositories.dao.QuestionOptionDao;
import com.qucat.quiz.repositories.dao.mappers.QuestionOptionMapper;
import com.qucat.quiz.repositories.entities.QuestionOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class QuestionOptionDaoImpl extends GenericDaoImpl<QuestionOption> implements QuestionOptionDao {
    @Value("#{${sql.questionOption}}")
    private Map<String, String> questionOptionQueries;

    protected QuestionOptionDaoImpl() {
        super(new QuestionOptionMapper(), TABLE_NAME);
    }


    @Override
    protected String getInsertQuery() {
        return questionOptionQueries.get("insert");
    }

    @Override
    protected PreparedStatement getInsertPreparedStatement(PreparedStatement preparedStatement, QuestionOption questionOption) throws SQLException {
        preparedStatement.setInt(1, questionOption.getQuestionId());
        preparedStatement.setString(2, questionOption.getContent());
        preparedStatement.setBoolean(3, questionOption.isCorrect());
        preparedStatement.setInt(4, questionOption.getSequenceOrder());
        return preparedStatement;
    }

    @Override
    protected String getUpdateQuery() {
        return questionOptionQueries.get("update");
    }

    @Override
    protected Object[] getUpdateParameters(QuestionOption questionOption) {
        return new Object[]{questionOption.getQuestionId(), questionOption.getContent(), questionOption.isCorrect(),
        questionOption.getSequenceOrder(), questionOption.getId()};
    }

    @Override
    public List<QuestionOption> getByQuestionId(int id) {
        return jdbcTemplate.query(questionOptionQueries.get("getByQuestionId"),
                new Object[]{id}, new QuestionOptionMapper());
    }
}
