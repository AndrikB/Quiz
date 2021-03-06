package com.qucat.quiz.repositories.dao;


import com.qucat.quiz.repositories.entities.Chat;

import java.util.List;

public interface ChatDao extends GenericDao<Chat> {
    String TABLE_NAME = "chat";

    List<Chat> getAllFullInfo();

    List<Chat> getAllFullInfoForUser(int id);

    Chat getFullInfo(int id);

    List<Chat> getChatsForUser(int userId);

    void addChatMember(int chatId, int userId);

    void removeChatMember(int chatId, int userId);


}
