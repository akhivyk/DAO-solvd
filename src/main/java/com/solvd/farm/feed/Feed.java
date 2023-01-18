package com.solvd.farm.feed;

public class Feed {
    private long idFeed;
    private String nameFeed;
    private int countFeed;

    public Feed() {
    }

    public Feed(int id, String nameFeed, int countFeed) {
        this.nameFeed = nameFeed;
        this.countFeed = countFeed;
        this.idFeed = id;
    }

    public int getCountFeed() {
        return countFeed;
    }

    public void setCountFeed(int countFeed) {
        this.countFeed = countFeed;
    }

    public String getNameFeed() {
        return nameFeed;
    }

    public void setNameFeed(String nameFeed) {
        this.nameFeed = nameFeed;
    }

    public long getIdFeed() {
        return idFeed;
    }
}
