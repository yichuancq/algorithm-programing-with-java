package com.example.learn.search.hash;

/**
 *
 */
public class HashMapCharCount {


    /**
     * 统计text中各字符的出现次数，返回Map<String, Integer>映射，从字符串到整数的映射
     *
     * @param text
     * @return
     */
    public static Map<String, Integer> charCount(String text) {
        System.out.println("text=\"" + text + "\"");
        HashMap<String, Integer> map = new HashMap<String, Integer>(10);//设置散列表容量为10
//        Map<String, Integer> map = new TreeMap<String, Integer>();//默认K实现Comparable< T>接口
        for (int i = 0; i < text.length(); i++)                //逐个字符查找计数
        {
            String key = text.substring(i, i + 1);            //获得1个字符，作为关键字
            Integer value = map.get(key);                  //获得关键字key（字符）映射的值
            int count = value == null ? 0 : value.intValue();//转换成int整数
            map.put(key, new Integer(count + 1));            //增加计数，关键字相同时，替换值
        }
        map.printAll();
        return map;
    }

    public static void main(String[] args) {
//        String text="AAAABBBCDDBBAAA";                     //例6.4数据
//        String text="CDAAAABBBDBBAAA";                     //例6.4数据，散列表没差别
//        String text="class Hash";                            //图8.14数据
        String text = "public class";                            //图8.1数据
        System.out.println(charCount(text).toString());      //统计text中各字符的出现次数
    }

}
