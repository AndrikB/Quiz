package com.qucat.quiz.repositories.dao.implementation;

import com.qucat.quiz.repositories.dao.AnnouncementDao;
import com.qucat.quiz.repositories.dao.mappers.extractors.AnnouncementExtractor;
import com.qucat.quiz.repositories.dao.mappers.AnnouncementMapper;
import com.qucat.quiz.repositories.entities.Announcement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@PropertySource("classpath:announcement.properties")
public class AnnouncementDaoImpl extends GenericDaoImpl<Announcement> implements AnnouncementDao {
    @Value("#{${sql.announcement}}")
    private Map<String, String> announcementQueries;

    protected AnnouncementDaoImpl() {
        super(new AnnouncementMapper(), TABLE_NAME);
    }

    @Override
    protected String getInsertQuery() {
        return announcementQueries.get("insert");
    }

    @Override
    protected PreparedStatement getInsertPreparedStatement(PreparedStatement preparedStatement, Announcement announcement) throws SQLException {
        preparedStatement.setInt(1, announcement.getAuthorId());
        preparedStatement.setBoolean(2, announcement.isPublished());
        preparedStatement.setString(3, announcement.getTitle());
        preparedStatement.setString(4, announcement.getSubtitle());
        preparedStatement.setString(5, announcement.getFullText());
        preparedStatement.setInt(6, announcement.getImageId());
        return preparedStatement;
    }

    @Override
    protected String getUpdateQuery() {
        return announcementQueries.get("update");
    }

    @Override
    protected Object[] getUpdateParameters(Announcement announcement) {
        return new Object[]{announcement.isPublished(), announcement.getTitle(),
                announcement.getSubtitle(), announcement.getFullText(), announcement.getImageId(), announcement.getId()};
    }

    @Override
    public List<Announcement> getByAuthorLogin(String login) {
        return jdbcTemplate.query(
                announcementQueries.get("getAllInfo").replace(";", " WHERE u.login = ?;"),
                new Object[]{login}, new AnnouncementExtractor()
        );
    }

    @Override
    public List<Announcement> getAllInfo() {
        return jdbcTemplate.query(announcementQueries.get("getAllInfo"), new AnnouncementExtractor());
    }

    @Override
    public Announcement getById(int id) {
        List<Announcement> result = jdbcTemplate.query(
                announcementQueries.get("getAllInfo").replace(";", " WHERE a.id = ?;"),
                new Object[]{id}, new AnnouncementExtractor()
        );
        return result.size() == 0 ? null : result.get(0);
    }

    @Override
    public Page<Announcement> getAllInfoForPage(Pageable pageable) {
        int total = jdbcTemplate.queryForObject(announcementQueries.get("rowCount"),
                new Object[]{},
                (resultSet, number) -> resultSet.getInt("row_count"));
        List<Announcement> announcements = jdbcTemplate.query(
                announcementQueries.get("getAllInfo").replace(";", " LIMIT ? OFFSET ?;"),
                new Object[]{pageable.getPageSize(), pageable.getOffset()},
                new AnnouncementExtractor());
        return new PageImpl<>(announcements, pageable, total);
    }

    @Override
    public Page<Announcement> getAllInfoForPage(boolean isPublished, Pageable pageable) {
        int total = jdbcTemplate.queryForObject(announcementQueries.get("rowCount").replace(";", " WHERE is_published = ?;"),
                new Object[]{isPublished},
                (resultSet, number) -> resultSet.getInt("row_count"));

        List<Announcement> announcements = jdbcTemplate.query(
                announcementQueries.get("getAllInfo").replace(";", " WHERE is_published = ? LIMIT ? OFFSET ?;"),
                new Object[]{isPublished, pageable.getPageSize(), pageable.getOffset()},
                new AnnouncementExtractor());
        return new PageImpl<>(announcements, pageable, total);
    }

    @Override
    public Page<Announcement> getPageByAuthorId(int authorId, Pageable pageable) {
        int total = jdbcTemplate.queryForObject(announcementQueries.get("idRowCount"),
                new Object[]{authorId},
                (resultSet, number) -> resultSet.getInt("row_count"));

        List<Announcement> announcements = jdbcTemplate.query(
                announcementQueries.get("getAllInfo").replace(";", " WHERE a.id = ? LIMIT ? OFFSET ?;"),
                new Object[]{authorId, pageable.getPageSize(), pageable.getOffset()},
                new AnnouncementExtractor());
        return new PageImpl<>(announcements, pageable, total);
    }

    @Override
    public Page<Announcement> getPageByAuthorLogin(String login, Pageable pageable) {
        int total = jdbcTemplate.queryForObject(announcementQueries.get("loginRowCount"),
                new Object[]{login},
                (resultSet, number) -> resultSet.getInt("row_count"));

        List<Announcement> announcements = jdbcTemplate.query(
                announcementQueries.get("getAllInfo").replace(";", " WHERE u.login = ? LIMIT ? OFFSET ?;"),
                new Object[]{login, pageable.getPageSize(), pageable.getOffset()},
                new AnnouncementExtractor());
        return new PageImpl<>(announcements, pageable, total);
    }


}
