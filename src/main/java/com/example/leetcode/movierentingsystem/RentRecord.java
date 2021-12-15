package com.example.leetcode.movierentingsystem;

public class RentRecord implements Comparable<RentRecord> {
    int shop;
    int movie;
    int price;

    RentRecord(int v1, int v2, int v3) {
        shop = v1;
        movie = v2;
        price = v3;
    }

    @Override
    public int hashCode() {
        return (shop + 7) * (movie + 7) * (price + 7);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RentRecord) {
            RentRecord r = (RentRecord) o;
            return r.shop == shop && r.movie == movie && r.price == price;
        } else {
            return false;
        }
    }

    public int compareTo(RentRecord r) {
        if (r.price > price) {
            return -1;
        } else if (r.price < price) {
            return 1;
        } else {
            if (r.shop > shop) {
                return -1;
            } else if (r.shop < shop) {
                return 1;
            } else {
                if (r.movie > movie) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }

}

