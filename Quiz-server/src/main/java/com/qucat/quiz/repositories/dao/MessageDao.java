package com.qucat.quiz.repositories.dao;

import com.qucat.quiz.repositories.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageDao extends GenericDao<Message> {
    String TABLE_NAME = "message";

    List<Message> getMessagesFromChat(int chatId);

    Page<Message> getMessagesFromChat(int chatId, Pageable pageable);

}
