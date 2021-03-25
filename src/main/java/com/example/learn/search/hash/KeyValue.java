package com.example.learn.search.hash;

/**
 * 映射元素类，K、V分别指定关键字和值的数据类型
 *
 * @param <K>
 * @param <V>
 */
public class KeyValue<K, V> {
    //关键字，最终变量，只能赋值一次
    final K key;
    //值
    V value;

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 返回描述字符串，形式为“(关键字,值)”
     */

    public String toString() {
        return "(" + this.key + "," + this.value + ")";
    }

    /***
     * //返回散列码，覆盖Object类的方法。最终方法，不能被覆盖
     * @return
     */

    public final int hashCode() { //仅以关键字的散列码作为对象的散列码，唯一，正数
        return this.key.hashCode();
    }

    /**
     * 比较对象是否相等，仅比较关键字，覆盖Object类的方法
     */
    public boolean equals(Object obj) {
        return obj == this ||
                obj instanceof KeyValue<?, ?> && this.key.equals(((KeyValue<K, V>) obj).key);
    }
}