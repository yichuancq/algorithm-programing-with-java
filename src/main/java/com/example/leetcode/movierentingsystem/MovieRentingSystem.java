package com.example.leetcode.movierentingsystem;

import java.util.*;

/**
 * 你有一个电影租借公司和 n 个电影商店。你想要实现一个电影租借系统,它支持查询、预订和返还电影的操作。
 * 同时系统还能生成一份当前被借出电影的报告
 */
public class MovieRentingSystem {
    //存放初始数据
    private Map<Integer, MovieRentEntity> hashMap;
    //已经借出的商品信息
    private List<MovieRentEntity> hasRentMovieInfoList;

    /***
     *
     */
    private void showAllInfo() {
        System.out.println("===显示所有信息===");
        System.out.println("size=" + hashMap.size());
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println(entry);
        }
    }

    /***
     *将MovieRentingSystem对象用n个商店和entries表示的电影列表初始化
     * @param entries
     */
    public MovieRentingSystem(int[][] entries) {
        hashMap = new HashMap<>();
        hasRentMovieInfoList = new ArrayList<>();
        this.buildInfo(entries);
    }

    /***
     * 构建初始数据
     * @param entries
     */
    private void buildInfo(int[][] entries) {
        for (int i = 0; i < entries.length; i++) {
            int shop = 0, movie = 0, price = 0;
            int temp = 0;
            for (int j = 0; j < entries[i].length; j++) {
                if (temp == 0) {
                    shop = entries[i][j];
                } else if (temp == 1) {
                    movie = entries[i][j];
                } else if (temp == 2) {
                    price = entries[i][j];
                }
                temp++;
            }
            MovieRentEntity movieRentEntity = new MovieRentEntity(shop, movie, price);
            Integer key = movieRentEntity.hashCode();
            hashMap.put(key, movieRentEntity);
        }
    }

    /**
     * 返回未借出指定movie的商店列表
     **/
    public List<Integer> search(int movie) {
        //没有借出的信息
        List<Integer> resultList = new ArrayList<>();
        if (this.hashMap.isEmpty()) {
            return resultList;
        }
        List<MovieRentEntity> notRentMovieInfoList = new ArrayList<>();
        for (Map.Entry entry : this.hashMap.entrySet()) {
            MovieRentEntity movieRentEntity = (MovieRentEntity) entry.getValue();
            if (movieRentEntity.getMovie() == movie) {
                //加入商店
                notRentMovieInfoList.add(movieRentEntity);
            }
        }
        //sort
        Collections.sort(notRentMovieInfoList, new MyComparator());
        for (MovieRentEntity entity : notRentMovieInfoList) {
            resultList.add(entity.getShop());
        }
        return resultList;
    }

    /***
     *从指定商店shop借出指定电影movie。返回 未借出 指定 movie 的商店列表
     * @param shop
     * @param movie
     */
    public void rent(int shop, int movie) {
        List<Integer> integerKeyList = new ArrayList<>();
        if (this.hashMap.isEmpty()) {
            return;
        }
        for (Map.Entry entry : this.hashMap.entrySet()) {
            MovieRentEntity movieRentEntity = (MovieRentEntity) entry.getValue();
            //shop和movie相同
            if (movieRentEntity.getShop() == shop && movieRentEntity.getMovie() == movie) {
                System.out.println("借出 shop:" + shop + ",movie:" + movie);
                //已经借出的商品信息
                integerKeyList.add(entry.getKey().hashCode());
                //记录已经借出
                hasRentMovieInfoList.add(movieRentEntity);
                break;
            }
        }
        //remove
        for (Integer key : integerKeyList) {
            hashMap.remove(key);
        }

    }

    /**
     * 在指定商店shop返还之前借出的电影movie
     *
     * @param shop
     * @param movie
     */
    public void drop(int shop, int movie) {
        for (MovieRentEntity movieRentEntity : hasRentMovieInfoList) {
            if (movieRentEntity.getShop() == shop && movieRentEntity.getMovie() == movie) {
                //add

                System.out.println("返还 shop:" + shop + ",movie:" + movie);
                hashMap.put(movieRentEntity.hashCode(), movieRentEntity);
                break;
            }
        }
        // 已经借出的列表移除元素
        Iterator iterator = hasRentMovieInfoList.listIterator();
        if (iterator.hasNext()) {
            MovieRentEntity movieRentEntity = (MovieRentEntity) iterator.next();
            if (movieRentEntity.getShop() == shop && movieRentEntity.getMovie() == movie) {
                //remove
                iterator.remove();
            }
        }
    }

    /**
     * 返回最便宜的已借出电影列表
     *
     * @return
     */
    public List<List<Integer>> report() {
        List<List<Integer>> reportList = new ArrayList<>();
        if (hasRentMovieInfoList.isEmpty()) {
            return new ArrayList<>();
        }
        //排序
        Collections.sort(hasRentMovieInfoList, Comparator.comparingInt(MovieRentEntity::getShop));
        for (MovieRentEntity movieRentEntity : hasRentMovieInfoList) {
            // shop id and movie id
            int shop = movieRentEntity.getShop(), movie = movieRentEntity.getMovie();
            List<Integer> entityList = new ArrayList<>();
            entityList.add(shop);
            entityList.add(movie);
            reportList.add(entityList);
        }
        return reportList;

    }

    /**
     * 自定义比较器
     */
    private class MyComparator implements Comparator<MovieRentEntity> {
        //商店需要按照 价格 升序排序，如果价格相同，则 shopi 较小 的商店排在前面。
        @Override
        public int compare(MovieRentEntity o1, MovieRentEntity o2) {
            //price
            int i = o1.getPrice().compareTo(o2.getPrice());
            if (i == 0) {
                //如果价格相同，再比较name
                return o1.getShop().compareTo(o2.getShop());
            } else {
                return i;
            }
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[][] entries = new int[][]{{4, 374, 55}, {1, 6371, 21}, {8, 3660, 24}, {1, 56, 32}, {5, 374, 71}, {3, 4408, 36}, {6, 9322, 73}, {6, 9574, 92}, {8, 7834, 62}, {2, 6084, 27}, {7, 3262, 89}, {2, 8959, 53}, {0, 3323, 41}, {6, 6565, 45}, {0, 4239, 20}};
        //out->[null,null,null,null,null,null,null,[],[[0,4239],[2,6084],[3,4408]],null,[]]
        //0k->[null,null,null,null,null,null,null,[],[[2,6084],[3,4408]],null,[]]
        MovieRentingSystem movieRentingSystem = new MovieRentingSystem(entries);
        //"rent","drop","rent","rent","rent","drop","search","report","rent","search"
        //[0,4239],[0,4239],[3,4408],[2,6084],[0,4239],[0,4239],[9346],[],[6,9322],[8698]]
        movieRentingSystem.showAllInfo();
        movieRentingSystem.rent(0, 4239);
        //drop
        movieRentingSystem.drop(0, 4239);
        System.out.println(movieRentingSystem.search(4239));
        movieRentingSystem.rent(3, 4408);
        movieRentingSystem.rent(2, 6084);
        movieRentingSystem.rent(0, 4239);
        System.out.println(movieRentingSystem.search(4239));
        //
        movieRentingSystem.drop(0, 4239);
        System.out.println(movieRentingSystem.search(4239));
        movieRentingSystem.search(9346);
        List<List<Integer>> reportList = movieRentingSystem.report();
        System.out.println(reportList);
        movieRentingSystem.rent(6, 9322);
        System.out.println(movieRentingSystem.search(8698));
    }
}