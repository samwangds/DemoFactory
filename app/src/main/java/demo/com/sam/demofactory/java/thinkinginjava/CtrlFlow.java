package demo.com.sam.demofactory.java.thinkinginjava;


/**
 * 控制执行流程
 * Created by samwang on 2017/8/6.
 */

public class CtrlFlow {
    public static void main(String[] args) {
        CtrlFlow ctrlFlow = new CtrlFlow();
        ctrlFlow.pritFibonacci(10);

    }


    /**
     * 测试标签
     */
    private void loopLabel() {

        int i = 0;

        outer:
        while (true) {
            inner:
            for (; i < 10; i++) {
                System.out.println("i = " + i);
                if (i == 2) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("break");
                    i++;
                    break;
                }
                if (i == 7) {
                    System.out.println("continue outer;");
                    i++;
                    continue outer;
                }
                if (i == 8) {
                    System.out.println("break outer;");
                    break outer;
                }

                for (int j = 0; j < 5; j++) {
                    if (j == 3) {
                        System.out.println("cotinue inner");
                        continue inner;
                    }
                }
            }

        }

    }

    /**
     * 练习9
     * 斐波那契数列 1，1，2，3，5
     * 第3个数字 起都是前两个数字之和
     * @param num    数列的个数
     */
    private void pritFibonacci(int num) {
        int n1 = 1, n2 = 1;
        for (int i = 1; i <= num; i++) {
            switch (i) {
                case 1:
                case 2:
                    System.out.print(1);
                    break;
                default:
                    n2 = n1 + n2;//下一个
                    n1 = n2 - n1;//原来的最后一个，即原来n2的值
                    System.out.print(n2);
            }
            if (i < num) {
                System.out.print(",");
            }

        }
    }





}
