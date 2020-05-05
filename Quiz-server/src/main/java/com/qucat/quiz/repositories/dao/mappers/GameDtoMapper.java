package com.qucat.quiz.repositories.dao.mappers;

import com.qucat.quiz.repositories.dto.quizplay.GameDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDtoMapper implements RowMapper<GameDto> {
    @Override
    public GameDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return GameDto.builder()
                .gameId(resultSet.getString("id"))
                .quizId(resultSet.getInt("quiz_id"))
                .hostId(resultSet.getInt("host_id"))
                .combo(resultSet.getBoolean("combo"))
                .intermediateResult(resultSet.getBoolean("intermediate_result"))
                .questionAnswerSequence(resultSet.getBoolean("question_answer_sequence"))
                .quickAnswerBonus(resultSet.getBoolean("quick_answer_bonus"))
                .time(resultSet.getInt("time"))
                .build();
    }
}
