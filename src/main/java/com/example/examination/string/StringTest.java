package com.example.examination.string;

/**
 * 字符串常量池常见问题
 */
public class StringTest {

    static String getStr() {
        return "ing";
    }

    /**
     * 注意 ：比较 String 字符串的值是否相等，可以使用 equals() 方法。 String 中的 equals 方法是被重写过的。
     * Object 的 equals 方法是比较的对象的内存地址，而 String 的 equals 方法比较的是字符串的值是否相等。
     * 如果你使用 == 比较两个字符串是否相等的话，IDEA 还是提示你使用 equals() 方法替换。
     */
    private static void test1() {

        String str1 = "str";
        String str2 = "ing";
        String str3 = "str" + "ing";//常量池中的对象
        String str4 = str1 + str2; //在堆上创建的新的对象
        String str5 = "string";//常量池中的对象
        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//true
        System.out.println(str4 == str5);//false

    }

    /**
     * 对于基本数据类型来说，== 比较的是值。对于引用数据类型来说，==比较的是对象的内存地址
     */
    private static void test2() {
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        System.out.println(aa == bb);// true
        System.out.println(aa.equals(bb));// true

    }

    /**
     * 被final关键字修改之后的String会被编译器当做常量来处理，
     * 编译器在程序编译期就可以确定它的值，其效果就想到于访问常量。
     */
    private static void test3() {
        final String str1 = "str";
        final String str2 = "ing";
        //下面两个表达式其实是等价的
        String c = "str" + "ing";// 常量池中的对象
        String d = str1 + str2; // 常量池中的对象
        System.out.println(c == d);// true

    }

    /**
     * 如果 ，编译器在运行时才能知道其确切值的话，就无法对其优
     *
     * @return
     */
    private static void test4() {
        final String str1 = "str";
        final String str2 = getStr();
        String c = "str" + "ing";// 常量池中的对象
        String d = str1 + str2; // 在堆上创建的新的对象
        System.out.println(c == d);// false

    }

    /**
     *
     */
    private static void test5() {
        // 从字符串常量池中拿对象
        String str1 = "abcd";
        // 直接在堆内存空间创建一个新的对象。
        String str2 = new String("abcd");
        String str3 = new String("abcd");
        //使用 new 的方式创建对象的方式如下，可以简单概括为3 步：
        // 1、在堆中创建一个字符串对象
        // 2、检查字符串常量池中是否有和 new 的字符串值相等的字符串常量
        // 3、如果没有的话需要在字符串常量池中也创建一个值相等的字符串常量，如果有的话，就直接返回堆中的字符串实例对象地址。
        System.out.println(str1 == str2);//false
        System.out.println(str2 == str3);//false

    }

    private static void test6() {
        String s1 = "string";
        String s2 = s1.intern();
        String s3 = new String("string");
        String s4 = s3.intern();
        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
        System.out.println(s1 == s4); // true
        System.out.println(s2 == s3); // false
        System.out.println(s2 == s4); // true
        System.out.println(s3 == s4); // false

    }

    private static void test7() {
        String s1 = new String("abc");// 堆内存的地址值
        String s2 = "abc";
        System.out.println(s1 == s2);// 输出 false,因为一个是堆内存，一个是常量池的内存，故两者是不同的。
        System.out.println(s1.equals(s2));// 输出 true

    }

    /**
     * 有整型包装类对象之间值的比较，全部使用 equals 方法比较
     * Java基本类型的包装类的大部分都实现了常量池技术
     */
    private static void test8() {
        //i1 , i2 , i3 都是常量池中的对象
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        //i4 , i5 , i6 是堆中的对象
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println(i1 == i2);// true
        System.out.println(i1 == i2 + i3);//true
        System.out.println(i1 == i4);// false
        System.out.println(i4 == i5);// false
        //有整型包装类对象之间值的比较，全部使用 equals 方法比较
        System.out.println(i4.equals(i5));//true
        //i4 == i5 + i6 为什么是 true 呢？因为，i5和i6会进行自动拆箱操作,进行数值相加，即i4 == 40 。
        //Integer 对象无法与数值进行直接比较，所以i4自动拆箱转为int值40，最终这条语句转为 40 == 40 进行数值比较
        System.out.println(i4 == i5 + i6);// true
        System.out.println(40 == i5 + i6);// true

    }

    public static void main(String[] args) {
        //基本数据类型(byte、boolean、short、char、int、float、long、double)
        //final 修饰的基本数据类型和字符串变量
        //字符串通过 “+”拼接得到的字符串、基本数据类型之间算数运算（加减乘除）、基本数据类型的位运算（<<、>>、>>> ）
        //StringTest.test1();
        StringTest.test8();

    }

}
