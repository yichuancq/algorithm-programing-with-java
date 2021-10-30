package com.example.leetcode.movierentingsystem;

import java.io.Serializable;

/**
 * 电影租赁实体类
 * entries[i] = [shopi, moviei, pricei]
 */
class MovieRentEntity implements Serializable {
    //店名id
    private Integer shop;
    //电影名称id
    private Integer movie;
    //价格id
    private Integer price;

    public MovieRentEntity(Integer shop, Integer movie, Integer price) {
        this.shop = shop;
        this.movie = movie;
        this.price = price;
    }

    public Integer getShop() {
        return shop;
    }

    public void setShop(Integer shop) {
        this.shop = shop;
    }

    public Integer getMovie() {
        return movie;
    }

    public void setMovie(Integer movie) {
        this.movie = movie;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MovieRentEntity{" +
                "shop=" + shop +
                ", movie=" + movie +
                ", price=" + price +
                '}';
    }

}