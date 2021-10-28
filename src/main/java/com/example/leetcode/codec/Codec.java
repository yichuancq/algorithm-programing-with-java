package com.example.leetcode.codec;

import java.util.ArrayList;
import java.util.List;

/***
 * 字符串编码与解码
 */
public class Codec {
    /**
     * 编码
     *
     * @param stringList
     * @return
     */
    public String encode(List<String> stringList) {
        StringBuilder sb = new StringBuilder();
        for (String s : stringList) {
            sb.append(intToString(s));
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 解码
     *
     * @param s
     * @return
     */
    public List<String> decode(String s) {
        int i = 0, n = s.length();
        List<String> output = new ArrayList();
        while (i < n) {
            int length = stringToInt(s.substring(i, i + 4));
            i += 4;
            output.add(s.substring(i, i + length));
            i += length;
        }
        return output;
    }

    public String intToString(String s) {
        int x = s.length();
        char[] bytes = new char[4];
        for (int i = 3; i >= 0; i--) {
            bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
        }
        return new String(bytes);
    }

    public int stringToInt(String bytesStr) {
        int result = 0;
        for (char b : bytesStr.toCharArray())
            result = (result << 8) + (int) b;
        return result;
    }

    public static void main(String[] args) {

        Codec codec = new Codec();
        List<String> stringList = new ArrayList<>();
        stringList.add("test");
        stringList.add("hello");
        String codecString = codec.encode(stringList);
        System.out.println(codecString);
        List<String> decodeSting = codec.decode(codecString);
        System.out.println(decodeSting);
    }
}
