package com.example.leetcode.movierentingsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class MovieRentingSystem2 {
    Movie[] movies = new Movie[10000];
    HashSet<RentRecord> record = new HashSet<>();

    public MovieRentingSystem2(int n, int[][] entries) {
        Movie m;
        for (int[] entry : entries) {
            if (movies[entry[1]] == null) {
                movies[entry[1]] = new Movie(entry[1]);
            }
            m = movies[entry[1]];
            m.addShop(entry[0], entry[2]);
        }
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                movies[i].sort();
            }
        }
    }

    public List<Integer> search(int movie) {
        if (movies[movie] == null) {
            return new ArrayList<>();
        }
        return movies[movie].search();
    }

    public void rent(int shop, int movie) {
        int price = movies[movie].rent(shop);
        record.add(new RentRecord(shop, movie, price));
    }

    public void drop(int shop, int movie) {
        int price = movies[movie].drop(shop);
        RentRecord r = new RentRecord(shop, movie, price);
        record.remove(r);
    }

    public List<List<Integer>> report() {
        RentRecord[] recordList = record.toArray(new RentRecord[record.size()]);
        Arrays.sort(recordList);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < recordList.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(recordList[i].shop);
            list.add(recordList[i].movie);
            ans.add(list);
            if (ans.size() == 5) {
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//
//        ["MovieRentingSystem", "search", "rent", "rent", "report", "drop", "search"]
//[[3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]], [1], [0, 1], [1, 2], [], [1, 2], [2
    }

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */
}
