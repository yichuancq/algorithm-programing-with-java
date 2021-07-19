package com.example.leetcode.sortsentence;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * sortSentence
 * 输入：s = "is2 sentence4 This1 a3"
 * 输出："This is a sentence"
 * 解释：将 s 中的单词按照初始位置排序，得到 "This1 is2 a3 sentence4" ，然后删除数字。
 * <p>
 * 提示：
 * <p>
 * 2 <= s.length <= 200
 * 只包含小写和大写英文字母、空格以及从1到9的数字。
 * 中单词数目为1到9个。
 * s中的单词由单个空格分隔。
 * s不包含任何前导或者后缀空格。
 */
public class Solution {
    /**
     * 排序句子
     *
     * @param s
     * @return
     */
    public String sortSentence(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        String resultString = "";
        if (s == null || s.equals("")) {
            return "";
        }
        //words content
        HashMap<Integer, String> hashMap = new HashMap();
        //split the words
        String words[] = s.split(" ");
        for (String tempWord : words) {
            //截取最后一位数字
            Integer index = Integer.valueOf(tempWord.substring(tempWord.length() - 1));
            hashMap.put(index, tempWord.substring(0, tempWord.length() - 1));
        }
        //sort
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Integer key = (Integer) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + " value: " + value);
            if (value.length() > 0) {
                stringBuilder.append(" " + value);
            }
        }
        resultString = stringBuilder.toString().replaceFirst(" ", "");
        return resultString;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String sortedString = solution.sortSentence("is2 sentence4 This1 a3");
        System.out.println(String.format("排序后输出:%s",sortedString));
    }
}
