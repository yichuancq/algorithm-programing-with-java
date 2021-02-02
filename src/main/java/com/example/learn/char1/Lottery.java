package com.example.learn.char1;

import java.util.*;

/**
 * 模拟彩票程序
 *
 * @author yichuan
 */
public class Lottery {
    /**
     * 红球
     */
    private List<Integer> redBallList = new ArrayList<>();

    private List<List<Integer>> secReword = new ArrayList<>();

    /**
     * 构建生成号码
     */
    public Lottery() {
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
    List<Integer> buildRedBall(int ballSize) {
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
    int buildBlueBall() {
        int redBall = 0;
        Random random = new Random();
        redBall = random.ints(1, 16).limit(1).findFirst().getAsInt();
        return redBall;
    }

    /**
     * 比较两个集合是否相等
     *
     * @param targetBalls
     * @param b
     * @return
     */
    public boolean isMatchRedBall(final Collection targetBalls, final Collection b) {
        if (targetBalls.size() != b.size()) {
            return false;
        }
        for (Iterator ita = targetBalls.iterator(); ita.hasNext(); ) {
            if (!b.contains(ita.next())) {
                return false;
            }
        }
        for (Iterator itb = b.iterator(); itb.hasNext(); ) {
            if (!targetBalls.contains(itb.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param targetBalls
     * @param targetBlueBall
     */
    private void match(final List<Integer> targetBalls, final int targetBlueBall) {
        //打印次数
        int times = 1;
        do {
            boolean matchBlueFlag = false;
            Lottery lottery = new Lottery();
            List<Integer> userBalls = lottery.buildRedBall(6);
            //红球匹配
            boolean matchRedFlag = lottery.isMatchRedBall(targetBalls, userBalls);
            int blueBallNumber = lottery.buildBlueBall();
            System.out.println("红球" + userBalls);
            System.out.println("篮球：" + blueBallNumber);
            //蓝色球匹配
            if (blueBallNumber == targetBlueBall) {
                matchBlueFlag = true;
            }
            System.out.println("=====\t");
            times += 1;
//            System.out.println("计数器：" + times);
            //如何红色球和蓝色球都匹配则退出循环
            //全部红球匹配，则为二等奖
            if (matchRedFlag) {
                List<Integer> balls = new ArrayList<>();
                balls.addAll(targetBalls);
                balls.add(blueBallNumber);// 蓝球
                if (!matchBlueFlag) {
                    //红匹配，蓝不匹配，则为二等奖
                    secReword.add(balls);
                }
            }
            //红色蓝色全匹配，结束循环
            if (matchRedFlag && matchBlueFlag) {
                //rate=1/17721088
                System.out.println("计数器：" + times);
                for (List<Integer> integerList : secReword) {
                    System.out.println("二等奖：" + integerList.toString());
                }
                break;
            }
        } while (true);
    }

    /**
     * 主函数入口
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //假定中奖的红色球组
            List<Integer> targetBalls = Arrays.asList(2, 3, 7, 8, 17, 22);
            //假定中奖的蓝色球
            int targetBlueBall = 15;
            Lottery lottery = new Lottery();
            //匹配
            lottery.match(targetBalls, targetBlueBall);
            //21,107,769
            //一等奖1种可能性，概率为1/17,721,088；
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
