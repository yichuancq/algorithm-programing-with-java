package com.example.leetcode.sortracker;

/**
 *
 */
public class Tracker {
    private String name;
    private Integer score;
    public Tracker() {
    }
    public Tracker(String name, Integer score) {
        this.name = name;
        this.score = score;
    }
    @Override
    public String toString() {
        return "Tracker{" + "name='" + name + '\'' + ", score=" + score + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
