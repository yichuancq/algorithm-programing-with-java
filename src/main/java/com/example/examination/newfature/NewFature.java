package com.example.examination.newfature;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewFature {

    @Test
    public void test() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        //返回符合条件的stream
        Stream<String> stringStream = strings.stream().filter(s -> "abc".equals(s));
        //计算流符合条件的流的数量
        long count = stringStream.count();
        //forEach遍历->打印元素
        strings.stream().forEach(System.out::println);

        //limit 获取到1个元素的stream
        Stream<String> limit = strings.stream().limit(1);
        //toArray 比如我们想看这个limitStream里面是什么，比如转换成String[],比如循环
        String[] array = limit.toArray(String[]::new);
        //map 对每个元素进行操作返回新流
        Stream<String> map = strings.stream().map(s -> s + "22");
        //sorted 排序并打印
        strings.stream().sorted().forEach(System.out::println);

        //Collectors collect 把abc放入容器中
        List<String> collect = strings.stream().filter(string -> "abc".equals(string)).collect(Collectors.toList());
        //把list转为string，各元素用，号隔开
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        //对数组的统计，比如用
        List<Integer> number = Arrays.asList(1, 2, 5, 4);
        IntSummaryStatistics statistics = number.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + statistics.getMax());
        System.out.println("列表中最小的数 : " + statistics.getMin());
        System.out.println("平均数 : " + statistics.getAverage());
        System.out.println("所有数之和 : " + statistics.getSum());
        //concat 合并流
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        Stream.concat(strings2.stream(), strings.stream()).count();
        //注意 一个Stream只能操作一次，不能断开，否则会报错。
        Stream stream = strings.stream();
        //第一次使用
        stream.limit(2);
        //第二次使用
        stream.forEach(System.out::println);
        //报错 java.lang.IllegalStateException: stream has already been operated upon or closed

        //但是可以这样, 连续使用
        stream.limit(2).forEach(System.out::println);
    }

    @Test
    public void laziness() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        Stream<Integer> stream = strings.stream().filter(new Predicate() {
            @Override
            public boolean test(Object o) {
                System.out.println("Predicate.test 执行");
                return true;
            }
        });

        System.out.println("count 执行");
        stream.count();
    }

    @Test
    public void newFormat() {
        //format yyyy-MM-dd
        LocalDate date = LocalDate.now();
        System.out.println(String.format("date format : %s", date));

        //format HH:mm:ss
        LocalTime time = LocalTime.now().withNano(0);
        System.out.println(String.format("time format : %s", time));

        //format yyyy-MM-dd HH:mm:ss
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = dateTime.format(dateTimeFormatter);
        System.out.println(String.format("dateTime format : %s", dateTimeStr));
    }

    @Test
    public void testLocalDate() {
        LocalDate date = LocalDate.of(2021, 1, 26);
        LocalDate.parse("2021-01-26");

        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
        LocalDateTime.parse("2021-01-26 12:12:22");

        LocalTime time = LocalTime.of(12, 12, 22);
        LocalTime.parse("12:12:22");

    }


    @Test
    public void pushWeek() {
        //一周后的日期
        LocalDate localDate = LocalDate.now();
        //方法1
        LocalDate after = localDate.plus(1, ChronoUnit.WEEKS);
        //方法2
        LocalDate after2 = localDate.plusWeeks(1);
        System.out.println("一周后日期：" + after);
        //算两个日期间隔多少天，计算间隔多少年，多少月
        LocalDate date1 = LocalDate.parse("2021-02-26");
        LocalDate date2 = LocalDate.parse("2021-12-23");
        Period period = Period.between(date1, date2);
        System.out.println("date1 到 date2 相隔：" + period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "天");
        //打印结果是 “date1 到 date2 相隔：0年9月27天”
        //这里period.getDays()得到的天是抛去年月以外的天数，并不是总天数
        //如果要获取纯粹的总天数应该用下面的方法
        long day = date2.toEpochDay() - date1.toEpochDay();
        System.out.println(date2 + "和" + date2 + "相差" + day + "天");
        //打印结果：2021-12-23和2021-12-23相差300天
    }


    @Test
    public void getDay() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        String first = format.format(c.getTime());
        System.out.println("first day:" + first);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("last day:" + last);

        //当年最后一天
        Calendar currCal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date time = calendar.getTime();
        System.out.println("last day:" + format.format(time));
    }


    @Test
    public void getDayNew() {
        LocalDate today = LocalDate.now();
        //获取当前月第一天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfThisMonth);
        // 取本月最后一天
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        //取下一天：
        LocalDate nextDay = lastDayOfThisMonth.plusDays(1);
        System.out.println(nextDay);
        //当年最后一天
        LocalDate lastday = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println(lastday);
        //2021年最后一个周日，如果用Calendar是不得烦死。
        LocalDate lastMondayOf2021 = LocalDate.parse("2021-12-31").with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println(lastMondayOf2021);

    }

    @Test
    public void zonedDateTime() {
        //当前时区时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("当前时区时间: " + zonedDateTime);
        //东京时间
        ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("JST"));
        ZonedDateTime tokyoTime = zonedDateTime.withZoneSameInstant(zoneId);
        System.out.println("东京时间: " + tokyoTime);
        //ZonedDateTime 转 LocalDateTime
        LocalDateTime localDateTime = tokyoTime.toLocalDateTime();
        System.out.println("东京时间转当地时间: " + localDateTime);
        //LocalDateTime 转 ZonedDateTime
        ZonedDateTime localZoned = localDateTime.atZone(ZoneId.systemDefault());
        System.out.println("本地时区时间: " + localZoned);


    }

}
