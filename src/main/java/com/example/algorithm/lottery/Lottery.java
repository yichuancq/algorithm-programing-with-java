package com.example.algorithm.lottery;

import java.io.FileWriter;
import java.util.*;

/**
 * 模拟彩票程序
 *
 * @author yichuan
 */
public class Lottery extends Thread {
    /**
     * 红球
     */
    private List<Integer> redBallList = new ArrayList<>();

    private void fillBall() {
        for (int i = 1; i <= 33; i++) {
            //添加红球
            redBallList.add(i);
        }
    }

    /**
     * 构建红色球
     *
     * @return
     */
    private List<Integer> buildRedBall(int ballSize) {
        this.fillBall();
        List<Integer> resultRedBallList = new ArrayList<>();
        if (ballSize < 6) {
            return resultRedBallList;
        }
        Iterator iterator = redBallList.iterator();
        while (iterator.hasNext()) {
            Random random = new Random();
            //最大边界
            int maxNumberBound = redBallList.size();
            int currentBall = random.ints(1, maxNumberBound).limit(1).findFirst().getAsInt();
            //获取当前号码
            int ballNumber = redBallList.get(currentBall);
            if (!resultRedBallList.contains(ballNumber)) {
                //开出号码加入集合
                resultRedBallList.add(ballNumber);
            }
            //移除掉已经开出的号码
            redBallList.remove(currentBall);
            //开出号码达到6个
            if (resultRedBallList.size() == ballSize) {
                break;
            }
        }
        return resultRedBallList;
    }

    /**
     * @return
     */
    private int buildBlueBall() {
        int redBall = 0;
        Random random = new Random();
        redBall = random.ints(1, 16).limit(1).findFirst().getAsInt();
        return redBall;
    }


    /**
     * 开奖号码和用户号码匹配的个数
     *
     * @param targetBalls
     * @param ballList
     * @return
     */
    private int matchBall(final List<Integer> targetBalls, final List<Integer> ballList) {
        int number = 0;
        for (Integer targetBallNumber : targetBalls) {
            for (Integer userBallNUmber : ballList) {
                //用户球号码与目标中奖号码匹配
                if (targetBallNumber.equals(userBallNUmber)) {
                    number += 1;
                }
            }
        }
        return number;

    }


    /**
     * @param targetRedBalls
     * @param targetBlueBall
     */
    private StringBuffer match(final List<Integer> targetRedBalls, final int targetBlueBall) {
        //生成模拟用户投注的开奖类型的号码组
        //写文件的内容
        StringBuffer stringBufferContext = new StringBuffer();
        int ballSize = 6;
        //生成红色球号码组
        List<Integer> userBallList = this.buildRedBall(ballSize);
        //sort
        int matchBallNumber = this.matchBall(targetRedBalls, userBallList);
        //生成蓝色球
        int blueBallNumber = this.buildBlueBall();
        System.out.println("=========");
        System.out.println("红球：" + userBallList.toString());
        System.out.println("蓝球：" + blueBallNumber);
        //6+1
        if ((ballSize - matchBallNumber) == 0 && (blueBallNumber == targetBlueBall)) {
            System.out.println("=========");
            System.out.println("一等奖");
            System.out.println("红球" + userBallList.toString());
            System.out.println("篮球：" + blueBallNumber);
            stringBufferContext.append("\r\n=========");
            stringBufferContext.append("\r\n一等奖");
            stringBufferContext.append("\r\n红球:" + userBallList.toString());
            stringBufferContext.append("\r\n篮球:" + blueBallNumber);
            return stringBufferContext;
        }

        //6+0
        if ((ballSize - matchBallNumber) == 0 && (blueBallNumber != targetBlueBall)) {
            System.out.println("=========");
            System.out.println("二等奖");
            System.out.println("红球" + userBallList.toString());
            System.out.println("篮球：" + blueBallNumber);
            stringBufferContext.append("\r\n=========");
            stringBufferContext.append("\r\n二等奖");
            stringBufferContext.append("\r\n红球:" + userBallList.toString());
            stringBufferContext.append("\r\n篮球:" + blueBallNumber);
            return stringBufferContext;
        }
        //5+1
        if ((ballSize - matchBallNumber) == 1 && (blueBallNumber == targetBlueBall)) {
            System.out.println("=========");
            System.out.println("三等奖");
            System.out.println("红球" + userBallList.toString());
            System.out.println("篮球：" + blueBallNumber);
            stringBufferContext.append("\r\n=========");
            stringBufferContext.append("\r\n三等奖,单注奖金3000元");
            stringBufferContext.append("\r\n红球:" + userBallList.toString());
            stringBufferContext.append("\r\n篮球:" + blueBallNumber);
            return stringBufferContext;
        }
        //5+0 or 4+1
        if (((ballSize - matchBallNumber) == 1 && (blueBallNumber != targetBlueBall))
                || ((ballSize - matchBallNumber) == 2 && (blueBallNumber == targetBlueBall))) {
            System.out.println("=========");
            System.out.println("四等奖");
            System.out.println("红球" + userBallList.toString());
            System.out.println("篮球：" + blueBallNumber);
            stringBufferContext.append("\r\n=========");
            stringBufferContext.append("\r\n四等奖,单注奖金200元");
            stringBufferContext.append("\r\n红球:" + userBallList.toString());
            stringBufferContext.append("\r\n篮球:" + blueBallNumber);
            return stringBufferContext;
        }
        //4+0 or 3+1
        if (((ballSize - matchBallNumber) == 2 && (blueBallNumber != targetBlueBall))
                || ((ballSize - matchBallNumber) == 3 && (blueBallNumber == targetBlueBall))) {
            System.out.println("=========");
            System.out.println("五等奖");
            System.out.println("红球" + userBallList.toString());
            System.out.println("篮球：" + blueBallNumber);
            stringBufferContext.append("\r\n=========");
            stringBufferContext.append("\r\n五等奖,单注奖金10元");
            stringBufferContext.append("\r\n红球:" + userBallList.toString());
            stringBufferContext.append("\r\n篮球:" + blueBallNumber);
            return stringBufferContext;
        }
        //2+1 or 1+1 or 0+1
        if (((ballSize - matchBallNumber) == 4 && (blueBallNumber == targetBlueBall))
                || ((ballSize - matchBallNumber) == 5 && (blueBallNumber == targetBlueBall))
                || ((ballSize - matchBallNumber) == 6 && (blueBallNumber == targetBlueBall))) {
            System.out.println("=========");
            System.out.println("六等奖");
            System.out.println("红球" + userBallList.toString());
            System.out.println("篮球：" + blueBallNumber);
            stringBufferContext.append("\r\n=========");
            stringBufferContext.append("\r\n六等奖,单注奖金5元");
            stringBufferContext.append("\r\n红球:" + userBallList.toString());
            stringBufferContext.append("\r\n篮球:" + blueBallNumber);
            return stringBufferContext;
        }

        return stringBufferContext;
    }

    /**
     * 主函数入口
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //保存文件的路径
            final String filePath = "src/main/resources/ssq.txt";
            //假定中奖的红色球组
            List<Integer> targetBalls = Arrays.asList(4, 25, 31, 29, 33, 15);
            //假定中奖的蓝色球
            int targetBlueBall = 6;
            Lottery lottery = new Lottery();
            //打印次数
            int times = 1000000;
            // int times = 365029152 / 2;
            //文件记录追加到尾部
            FileWriter fileWriter = new FileWriter(filePath, true);
            do {
                System.out.println("计数器：" + times);
                StringBuffer sbFileContext = lottery.match(targetBalls, targetBlueBall);
                //如果文件内容不为空
                if (sbFileContext.length() > 0) {
                    //写文件
                    fileWriter.write(sbFileContext.toString());
                    fileWriter.flush();
                }
                times--;
            } while (times > 0);
            fileWriter.close();
            //21,107,769
            //一等奖1种可能性，概率为1/17,721,088；
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
