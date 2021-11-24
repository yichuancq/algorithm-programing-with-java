package com.example.leetcode.arrangewords;

/**
 * 请你重新排列 text 中的单词使所有单词按其长度的升序排列。如果两个单词的长度相同,则保留其在原句子中的相对顺序。
 * 请同样按上述格式返回新的句子。
 * <p>
 * <p>
 * 输入：text = "Leetcode is cool"
 * 输出："Is cool leetcode"
 * 解释：句子中共有3个单词，长度为8的"Leetcode" ，长度为2的"is" 以及长度为4的"cool" 。
 * 输出需要按单词的长度升序排列，新句子中的第一个单词首字母需要大写。
 */
public class Solution {


    /**
     * @param text
     * @return
     */
    public String arrangeWords(String text) {
        if (text == null || text.isEmpty() || text.length() < 0) {
            return "";
        }
        String arrays[] = text.toLowerCase().split(" ");
        String[] news = lenString(arrays);
        return replaceFirst(news);
    }

    /**
     * 替换第一个字符串首字母大写
     *
     * @param strings
     * @return
     */
    private String replaceFirst(String[] strings) {
        String text = "";
        strings[0] = strings[0].substring(0, 1).toUpperCase()
                + strings[0].substring(1, strings[0].length());
        for (String temp : strings) {
            text += " " + temp;
        }
        return text.replaceFirst(" ", "");

    }


    /**
     * @param arrays
     * @return
     */
    private String[] lenString(String arrays[]) {
        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = i; j >= 0; j--) {
                if (arrays[j + 1].length() < arrays[j].length()) {
                    swap(arrays, j, j + 1);
                }
            }
        }
        return arrays;
    }

    /**
     * 交换
     *
     * @param data
     * @param i
     * @param j
     */
    private void swap(String[] data, int i, int j) {
        String temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String text = "tytabc zdn usujd qjhcpm swxxmsh tlvmdbava";
        text = solution.arrangeWords(text);
        System.out.println(text);
    }
}
