package com.example.leetcode.movierentingsystem;

import java.util.*;


public class MovieRentingSystem2 {
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

    public class Movie {
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

        public ArrayList<Shop> shopList = new ArrayList<>();
        public Shop[] shops;
        public HashMap<Integer, Integer> priceMap = new HashMap<>();
        public HashSet<Integer> rented = new HashSet<>();
        public int id;

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
            // for(Shop s : shops) {
            //     System.out.println(s.id + " " + s.price);
            // }
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

    Movie[] movies = new Movie[10000];
    HashSet<RentRecord> record = new HashSet<>();

    public MovieRentingSystem2(int n, int[][] entries) {
        Movie m;
        for (int[] entry : entries) {
            //System.out.println(entry[0] + " " + entry[1] + " " + entry[2]);
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
