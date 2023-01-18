package com.solvd.farm.dao;

import com.solvd.farm.feed.Feed;

import java.util.List;

public interface IFeedDAO extends IBaseDAO<Feed> {
    List<Feed> getAllFeeds();

    Feed getFeedById(long idFeed);
}
