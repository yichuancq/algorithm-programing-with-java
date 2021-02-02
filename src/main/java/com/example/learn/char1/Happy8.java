package com.example.learn.char1;


import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 快乐8
 */
public class Happy8 {

    private boolean winningFlag = false;
    //写文件的内容
    private StringBuffer stringBufferContext = new StringBuffer();
    //放中奖的号码
    private List<Integer> ballList = new ArrayList<>();
    //假定中奖的球组
    private List<Integer> targetBalls = Arrays.asList(
            3, 6, 9, 10, 12, 17, 19, 20, 28, 32,
            33, 36, 38, 40, 42, 48, 49, 51, 53, 63);

    public Happy8() {
    }

    /**
     *
     */
    private void fillBall() {
        for (int i = 1; i <= 80; i++) {
            //添加号码
            ballList.add(i);
        }
    }

    private List<Integer> buildBall(int ballSize) {
        this.fillBall();
        List<Integer> resultRedBallList = new ArrayList<>();
        Iterator iterator = ballList.iterator();
        while (iterator.hasNext()) {
            Random random = new Random();
            //最大边界
            int maxNumberBound = ballList.size();
//            System.out.println("最大边界:" + maxNumberBound);
            int currentBall = random.ints(1, maxNumberBound).limit(1).findFirst().getAsInt();
            //获取当前号码
            int ballNumber = ballList.get(currentBall);
            if (!resultRedBallList.contains(ballNumber)) {
                //开出号码加入集合
                resultRedBallList.add(ballNumber);
            }
            //移除掉已经开出的号码
            ballList.remove(currentBall);
            //开出号码达到size个
            if (resultRedBallList.size() == ballSize) {
                break;
            }
        }
        return resultRedBallList;
    }

    /**
     * 开奖号码和用户号码匹配的个数
     *
     * @param targetBalls
     * @param ballList
     * @return
     */
    private int matchBall(List<Integer> targetBalls, List<Integer> ballList) {
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
     * 奖项匹配
     *
     * @param n
     */
    private void winningBallMatch(final int n) {
        //生成模拟用户投注的开奖类型的号码组
        List<Integer> userBallList = this.buildBall(n);
        //排序
        Collections.sort(userBallList);
        //计算与开奖号码匹配的个数
        int matchBallNumber = this.matchBall(targetBalls, userBallList);
        //System.out.println("匹配个数：" + matchBallNumber);
        /**
         *选10
         */
        if (n == 10) {
            int lessMatch = n - matchBallNumber;
            //匹配10个号码
            switch (lessMatch) {
                case 0:
                    System.out.println("=========");
                    System.out.println("一等奖,浮动奖金");
                    System.out.println("球：" + userBallList.toString());
                    winningFlag = true;
                    break;
                case 1:
                    System.out.println("=========");
                    System.out.println("二等奖");
                    System.out.println("球：" + userBallList.toString());
                    winningFlag = true;
                    break;
                case 2:
                    System.out.println("=========");
                    System.out.println("三等奖");
                    System.out.println("球：" + userBallList.toString());
                    break;
                case 3:
                    System.out.println("=========");
                    System.out.println("四等奖");
                    System.out.println("球：" + userBallList.toString());
                    break;
                case 4:
                    System.out.println("=========");
                    System.out.println("五等奖");
                    System.out.println("球：" + userBallList.toString());
                    break;
                case 5:
                    System.out.println("=========");
                    System.out.println("六等奖");
                    System.out.println("球：" + userBallList.toString());
                    break;
                default:
                    break;
            }
        }
        /**
         *选9
         */
        if (n == 9) {
            int lessMatch = n - matchBallNumber;
            //匹配9个号码
            switch (lessMatch) {
                case 0:
                    System.out.println("=========");
                    System.out.println("一等奖");
                    System.out.println("球：" + userBallList.toString());
                    stringBufferContext.append("\r\n=========");
                    stringBufferContext.append("\r\n一等奖");
                    stringBufferContext.append("\r\n" + userBallList.toString());
                    winningFlag = true;
                    break;
                case 1:
                    System.out.println("=========");
                    System.out.println("二等奖");
                    System.out.println("球：" + userBallList.toString());
                    stringBufferContext.append("\r\n=========");
                    stringBufferContext.append("\r\n二等奖");
                    stringBufferContext.append("\r\n" + userBallList.toString());
                    // winningFlag = true;
                    break;
                case 2:
                    System.out.println("=========");
                    System.out.println("三等奖");
                    System.out.println("球：" + userBallList.toString());
                    stringBufferContext.append("\r\n=========");
                    stringBufferContext.append("\r\n三等奖");
                    stringBufferContext.append("\r\n" + userBallList.toString());
                    break;
                case 3:
                    System.out.println("=========");
                    System.out.println("四等奖");
                    System.out.println("球：" + userBallList.toString());
                    stringBufferContext.append("\r\n=========");
                    stringBufferContext.append("\r\n四等奖");
                    stringBufferContext.append("\r\n" + userBallList.toString());
                    break;
                case 4:
                    System.out.println("=========");
                    System.out.println("五等奖");
                    System.out.println("球：" + userBallList.toString());
                    stringBufferContext.append("\r\n=========");
                    stringBufferContext.append("\r\n五等奖");
                    stringBufferContext.append("\r\n" + userBallList.toString());
                    break;
                case 5:
                    System.out.println("=========");
                    System.out.println("六等奖");
                    System.out.println("球：" + userBallList.toString());
                    stringBufferContext.append("\r\n=========");
                    stringBufferContext.append("\r\n六等奖");
                    stringBufferContext.append("\r\n" + userBallList.toString());
                    break;
                default:
                    break;
            }
        }
        /**
         * 选8
         */
        if (n == 8) {
            //
            int lessMatch = n - matchBallNumber;
            //匹配9个号码
            switch (lessMatch) {
                case 0:
                    System.out.println("一等奖");
                    winningFlag = true;
                    break;
                case 1:
                    System.out.println("二等奖");
                    break;
                case 2:
                    System.out.println("三等奖");
                    break;
                case 3:
                    System.out.println("四等奖");
                    break;
                case 4:
                    System.out.println("五等奖");
                    break;
                default:
                    break;
            }
        }
        /**
         * 选7
         */
        if (n == 7) {
            int lessMatch = n - matchBallNumber;
            switch (lessMatch) {
                case 0:
                    System.out.println("一等奖");
                    break;
                case 1:
                    System.out.println("二等奖");
                    break;
                case 2:
                    System.out.println("三等奖");
                    break;
                case 3:
                    System.out.println("四等奖");
                    break;
                default:
                    break;
            }

        }
        /**
         * 选6
         */
        if (n == 6) {
            int lessMatch = n - matchBallNumber;
            switch (lessMatch) {
                case 0:
                    System.out.println("一等奖");
                    break;
                case 1:
                    System.out.println("二等奖");
                    break;
                case 2:
                    System.out.println("三等奖");
                    break;
                case 3:
                    System.out.println("四等奖");
                    break;
                default:
                    break;
            }

        }
        /**
         * 选5
         */
        if (n == 5) {
            int lessMatch = n - matchBallNumber;
            switch (lessMatch) {
                case 0:
                    System.out.println("一等奖");
                    break;
                case 1:
                    System.out.println("二等奖");
                    break;
                case 2:
                    System.out.println("三等奖");
                    break;
                default:
                    break;
            }

        }
        /**
         * 选4
         */
        if (n == 4) {
            int lessMatch = n - matchBallNumber;
            switch (lessMatch) {
                case 0:
                    System.out.println("一等奖");
                    break;
                case 1:
                    System.out.println("二等奖");
                    break;
                case 2:
                    System.out.println("三等奖");
                    break;
                default:
                    break;
            }

        }
        /**
         * 选3
         */
        if (n == 3) {
            int lessMatch = n - matchBallNumber;
            switch (lessMatch) {
                case 0:
                    System.out.println("一等奖");
                    break;
                case 1:
                    System.out.println("二等奖");
                    break;
                default:
                    break;
            }

        }
        /**
         * 选2
         */
        if (n == 2) {
            int lessMatch = n - matchBallNumber;
            switch (lessMatch) {
                case 0:
                    System.out.println("一等奖");
                    break;
                default:
                    break;
            }

        }
        /**
         * 选1
         */
        if (n == 1) {
            int lessMatch = n - matchBallNumber;
            switch (lessMatch) {
                case 0:
                    System.out.println("一等奖");
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * @param n
     */
    private void chooseN(final int n) {
        switch (n) {
            case 10:
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:
                winningBallMatch(n);
                break;
            default:
                System.out.println("输入错误！");
                break;

        }
    }

    /**
     * 生成指定规则的随机号码
     */
    @Test
    public void test() {
        Happy8 happy8 = new Happy8();
        System.out.println("选10");
        int n = 5;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> ball = (ArrayList<Integer>) happy8.buildBall(10);
            Collections.sort(ball);
            System.out.println("" + ball.toString());
        }
        System.out.println("选9");
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> ball = (ArrayList<Integer>) happy8.buildBall(9);
            Collections.sort(ball);
            System.out.println("" + ball.toString());
        }
        System.out.println("选8");
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> ball = (ArrayList<Integer>) happy8.buildBall(8);
            Collections.sort(ball);
            System.out.println("" + ball.toString());
        }
        System.out.println("选7");
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> ball = (ArrayList<Integer>) happy8.buildBall(7);
            Collections.sort(ball);
            System.out.println("" + ball.toString());
        }
    }

    @Test
    public void buy() throws IOException {
        Happy8 happy8 = new Happy8();
        //保存文件的路径
        final String filePath = "src/main/resources/happy8.txt";

        for (int i = 0; i < 1000; i++) {
            happy8.chooseN(9);
        }
        System.out.println("开奖号码：" + happy8.targetBalls);
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(happy8.stringBufferContext.toString());
        fileWriter.close();
    }


    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Happy8 happy8 = new Happy8();
        //保存文件的路径
        final String filePath = "src/main/resources/happy8.txt";
        int i = 0;
        do {
            happy8.chooseN(9);
            i++;
            if (happy8.winningFlag) {
                System.out.println("第: " + i + " 组号码");
                System.out.println("开奖号码：" + happy8.targetBalls);
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(happy8.stringBufferContext.toString());
                fileWriter.close();
                break;
            }
        } while (true);
    }
}
