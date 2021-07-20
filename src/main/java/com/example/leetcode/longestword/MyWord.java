package com.example.leetcode.longestword;

import java.util.Objects;

/**
 * 单词实体类
 */
public class MyWord {
    //单词索引
    public Integer index;
    //单词长度
    public Integer wordsLen;
    //内容
    public String content;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getWordsLen() {
        return wordsLen;
    }

    public void setWordsLen(Integer wordsLen) {
        this.wordsLen = wordsLen;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "MyWord{" +
                "index=" + index +
                ", wordsLen=" + wordsLen +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyWord myWord = (MyWord) o;
        return Objects.equals(index, myWord.index) && Objects.equals(wordsLen, myWord.wordsLen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, wordsLen);
    }
}
