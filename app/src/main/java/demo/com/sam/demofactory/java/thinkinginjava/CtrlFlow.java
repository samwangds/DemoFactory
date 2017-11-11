package demo.com.sam.demofactory.java.thinkinginjava;


/**
 * 控制执行流程
 * Created by samwang on 2017/8/6.
 */

public class CtrlFlow {
    public static void main(String[] args) {
        CtrlFlow ctrlFlow = new CtrlFlow();
        ctrlFlow.vampireNumber();

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

    /**
     * 练习10
     * 吸血鬼数字：偶数位的数字，可以由一对数字相乘而得，这对数字各包含乘积一半位数的数字。数字可以任意排序，以两个0结尾的数字不允许
     * 如：1260=21*60
     * 1827=21*87
     * 找出所有4位吸血鬼数字
     */
    private void vampireNumber() {

        int a[] = new int[4];//4位
        int temp[] = new int[2];//b
        int num, i, j, t;
        for (a[0] = 1; a[0] <= 9; a[0]++) {
            for (a[1] = 0; a[1] <= 9; a[1]++) {
                for (a[2] = 0; a[2] <= 9; a[2]++) {
                    for (a[3] = 0; a[3] <= 9; a[3]++) {
                        if (a[2] == 0 && a[3] == 0) {
                            continue;
                        }
                        num = a[0] * 1000 + a[1] * 100 + a[2] * 10 + a[3];

                        for (i = 1; i < 4; i++) {//4->2*2共 12种情况,考虑到乘法重复
                            t = 0;
                            for (j = 1; j < 4; j++) {
                                if (i != j) {
                                    temp[t] = a[j];
                                    t++;
                                }
                            }
                            boolean isAnswer = permutation(a[0], a[i], temp[0], temp[1], num) ||
                                    permutation(a[i], a[0], temp[0], temp[1], num) ||
                                    permutation(a[0], a[i], temp[1], temp[0], num) ||
                                    permutation(a[i], a[0], temp[1], temp[0], num);

                            if (isAnswer) {
                                break;
                            }
                        }

                    }
                }

            }
        }

    }


    /**
     * @param a1
     * @param a2
     * @param b1
     * @param b2 a1a2*b1b2
     *           a1a2*b2b1
     *           a2a1*b1b2
     *           a2a1*b2b1
     */
    private boolean permutation(int a1, int a2, int b1, int b2, int target) {
        if ((a1 * 10 + a2) * (b1 * 10 + b2) == target) {
            System.out.println(target + " = " + a1 + "" + a2 + " * " + b1 + "" + b2 + "");
            return true;
        }
        return false;
    }


}
