package com.example.leetcode.nextgreatestletter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 给你一个排序后的字符列表letters ，列表中只包含小写英文字母。另给出一个目标字母target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * 在比较时，字母是依序循环出现的。举个例子：如果目标字母 target = 'z' 并且字符列表为letters = ['a', 'b']，则答案返回'a'
 * <p>
 * <p>
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "a"
 * 输出: "c"
 * <p>
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "c"
 * 输出: "f"
 */
public class NextGreatestLetter {

    /**
     * 查找
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int index = 0;
        letters = removeDuplicate(letters);
        index = binarySearch(letters, 0, letters.length, target);
        //index>=0 找到元素
        if (index >= 0) {
            //返回下一个索引的值
            //如果是最后一个元素
            if (index < letters.length - 1) {
                return letters[index + 1];
            } else {
                return letters[(index + 1) - letters.length];
            }

        } else {
            //没有找到元素
            int arrLen = letters.length + 1;
            char newArray[] = Arrays.copyOf(letters, arrLen);
            newArray[arrLen - 1] = target;
            //排序
            Arrays.sort(newArray);
            index = binarySearch(newArray, 0, newArray.length, target);
            //返回下一个索引的值
            if (index < newArray.length - 1) {
                return newArray[index + 1];
            } else {
                return newArray[(index + 1) - newArray.length];
            }
        }
    }

    /**
     * 去掉重复
     *
     * @param letters
     */
    private char[] removeDuplicate(char[] letters) {
        //注意重复后依然保持数组的顺序
        List<Character> characterList = new ArrayList<>();
        char[] newLetters = null;
        for (char ch : letters) {
            if (!characterList.contains(ch)) {
                characterList.add(ch);
            }
        }
        newLetters = new char[characterList.size()];
        Iterator iterator = characterList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            char ch = (char) iterator.next();
            newLetters[i] = ch;
            i++;
        }
        return newLetters;

    }

    /**
     * 二分查找
     *
     * @param array
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    private static int binarySearch(char[] array, int fromIndex, int toIndex, char key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            //int mid = (low + high) >>> 1;
            long midVal = array[mid];
            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                // key found
                return mid;
        }
        // key not found.
        return -(low + 1);
    }


    public static void main(String[] args) {
        NextGreatestLetter nextGreatestLetter = new NextGreatestLetter();
        char[] letters = {'e', 'e', 'e', 'k', 'q', 'q', 'q', 'v', 'v', 'y'}; //return k
        char target = 'e';
        char result = nextGreatestLetter.nextGreatestLetter(letters, target);
        System.out.println(result);
    }
}
