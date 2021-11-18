package com.example.leetcode.aliendictionary;

import java.util.*;

/**
 * 现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。您有一个单词列表（从词典中获得的），该单词列表内的单词已经按这门新语言的字母顺序进行了排序。
 * 需要根据这个输入的列表，还原出此语言中已知的字母顺序。
 * <p>
 * 示例：
 * <p>
 * 输入:
 * [
 * “wrt”,
 * “wrf”,
 * “er”,
 * “ett”,
 * “rftt”
 * ]
 * 输出: “wertf”
 */
public class AlienDictionary {

    String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }
        if (words.length == 1) {
            return words[0];
        }
        Map<Character, List<Character>> adjList = new HashMap<>();
        /**
         * 两两比较字符串对每个出现的字母都创建一个图里的顶点,一旦发现两字母不同，就链接这两个顶点在这里，定义一个found变量，
         * 表明一旦出现不同字母，只需处理好这两个字母，或者顶点的关系，之后的字母不需要考虑
         */
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            int n1 = w1.length(), n2 = w2.length();
            boolean found = false;
            for (int j = 0; j < Math.max(n1, n2); j++) {
                Character c1 = j < n1 ? w1.charAt(j) : null;
                Character c2 = j < n2 ? w2.charAt(j) : null;
                if (c1 != null && !adjList.containsKey(c1)) {
                    adjList.put(c1, new ArrayList<Character>());
                }
                if (c2 != null && !adjList.containsKey(c2)) {
                    adjList.put(c2, new ArrayList<Character>());
                }
                if (c1 != null && c2 != null && c1 != c2 && !found) {
                    adjList.get(c1).add(c2);
                    found = true;
                }
            }
        }

        Set<Character> visited = new HashSet<>();
        Set<Character> loop = new HashSet<>();
        Stack<Character> stack = new Stack<Character>();
        for (Character key : adjList.keySet()) {
            if (!visited.contains(key)) {
                if (!topologicalSort(adjList, key, visited, loop, stack)) {
                    return "";
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * visted集合：用来记录已访问过的顶点； stack堆栈：想将某点加入stack里，必须先把其他跟它有联系的订单都处理完毕；
     * loop集合：有效防止有向图中出现环的情况
     **/
    boolean topologicalSort(Map<Character, List<Character>> adjList, char u, Set<Character> visited,
                            Set<Character> loop, Stack<Character> stack) {
        visited.add(u);
        loop.add(u);
        if (adjList.containsKey(u)) {
            for (int i = 0; i < adjList.get(u).size(); i++) {
                char v = adjList.get(u).get(i);
                if (loop.contains(v)) {
                    return false;
                }
                if (!visited.contains(v)) {
                    if (!topologicalSort(adjList, v, visited, loop, stack)) {
                        return false;
                    }
                }
            }
        }

        loop.remove(u);
        stack.push(u);
        return true;
    }

    public static void main(String[] args) {
        AlienDictionary alienDictionary = new AlienDictionary();
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        String string = alienDictionary.alienOrder(words);
        System.out.println(string);
    }

}
