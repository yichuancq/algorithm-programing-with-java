package com.example.algorithm.btree;

/**
 * 查找结果
 * @author yichuan
 */
public class FindResult {
    /**
     * 大于前驱关键字小于后继关键字，此时后继的下标
     */
    private int index;
    /**
     * 是否找到
     */
    private boolean found;

    public FindResult() {
    }

    public FindResult(int index, boolean found) {
        this.index = index;
        this.found = found;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean getFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    @Override
    public String toString() {
        return "FindResult{" +
                "index=" + index +
                ", found=" + found +
                '}';
    }
}
