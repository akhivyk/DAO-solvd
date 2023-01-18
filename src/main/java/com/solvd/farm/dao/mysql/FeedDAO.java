package com.solvd.farm.dao.mysql;

import com.solvd.farm.dao.IFeedDAO;
import com.solvd.farm.feed.Feed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedDAO extends MySqlDAO implements IFeedDAO {
    public static final String SQL_SELECT_ALL_FEED = "SELECT * FROM Feed";
    public static final String SQL_SELECT_FEED_ID =
            "SELECT * FROM Feed WHERE idFeed=?";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Feed getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Feed entity) {

    }

    @Override
    public Feed createEntity(Feed entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Feed> getAllFeeds() {
        List<Feed> allFeed = new ArrayList<>();
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_FEED);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int count = rs.getInt(3);
                allFeed.add(new Feed(id, name, count));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return allFeed;
    }

    @Override
    public Feed getFeedById(long idFeed) {
        Feed feed = null;
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_FEED_ID)) {
            statement.setInt(1, (int) idFeed);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int count = rs.getInt(3);
                feed = new Feed(id, name, count);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return feed;
    }
}
