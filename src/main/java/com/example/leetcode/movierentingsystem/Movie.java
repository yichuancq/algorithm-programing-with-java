package com.example.leetcode.movierentingsystem;

import java.util.*;

public class Movie {
    //move id
    public int id;
    //shop list
    public ArrayList<Shop> shopList = new ArrayList<>();
    public Shop[] shops;
    public HashMap<Integer, Integer> priceMap = new HashMap<>();
    public HashSet<Integer> rented = new HashSet<>();

    public class Shop implements Comparable<Shop> {
        int id;
        int price;

        Shop(int v1, int v2) {
            id = v1;
            price = v2;
        }

        @Override
        public int compareTo(Shop s) {
            if (price < s.price) {
                return -1;
            } else if (price > s.price) {
                return 1;
            } else {
                if (id < s.id) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }

    Movie(int i) {
        id = i;
    }

    public void addShop(int shopId, int price) {
        shopList.add(new Shop(shopId, price));
        priceMap.put(shopId, price);
    }

    public void sort() {
        shops = shopList.toArray(new Shop[shopList.size()]);
        Arrays.sort(shops);
    }

    public List<Integer> search() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < shops.length; i++) {
            if (rented.contains(shops[i].id)) {
                continue;
            } else {
                list.add(shops[i].id);
            }
            if (list.size() == 5) {
                return list;
            }
        }
        return list;
    }

    public int rent(int shop) {
        rented.add(shop);
        return priceMap.get(shop);
    }

    public int drop(int shop) {
        rented.remove(shop);
        return priceMap.get(shop);
    }
}