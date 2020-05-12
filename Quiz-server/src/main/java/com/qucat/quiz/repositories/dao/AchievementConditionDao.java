package com.qucat.quiz.repositories.dao;

import com.qucat.quiz.repositories.entities.AchievementCondition;

import java.util.List;

public interface AchievementConditionDao extends GenericDao<AchievementCondition> {
    String TABLE_NAME = "achievement_condition";

    void delete(List<Integer> achievementConditions);

    void insert(List<AchievementCondition> achievementConditions);
}
