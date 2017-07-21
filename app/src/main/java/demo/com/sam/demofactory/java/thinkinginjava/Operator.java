package demo.com.sam.demofactory.java.thinkinginjava;

import java.util.Scanner;

/**
 * 操作符
 *
 * @author SamWang(199004)
 *         2017/7/19 19:04
 */
public class Operator {
    public static void main(String[] arg) {
        Operator operator = new Operator();
//        operator.bitwiseOperators();
        operator.practive13();
    }


    /**
     * 按位操作符 ->练习10
     */
    private void bitwiseOperators() {
        /*
        练习（10）编写一个具有两个常量值的程序,一个具有交替的二进制位1和0,其中最低有效位为0,
        另一个也具有交替的二进制位1和0,但其最低有效位为1(提示:使用十六进制常量来表示最简单的方法).
        取两个值,用按位操作符以所有可能方式结合运算它们,然后用Integer.toBinaryString()显示
        */
        int a= 0xaaaaaaaa;//a(10)转二进制 1010
        int b= 0x55555555;//5转二进制 101
        System.out.println("a " +Integer.toBinaryString(a));
        System.out.println("b " +Integer.toBinaryString(b));
        System.out.println("a&b " +Integer.toBinaryString(a&b));
        System.out.println("a|b " +Integer.toBinaryString(a|b));
        System.out.println("a^b " +Integer.toBinaryString(a^b));
        System.out.println("~a " +Integer.toBinaryString(~a));
        System.out.println("~b " +Integer.toBinaryString(~b));
    }

    private void practice11(){
       /*练习11
       Start with a number that has a binary one in the most significant position (hint: Use a hexadecimal constant).
        Using the signed right-shift operator, right shift it all the way through all of its binary positions,
         each time displaying the result using Integer.toBinaryString( ).
       */
        int a = 0x90111111;
        System.out.println(Integer.toBinaryString(a));
        for (int i = 0; i < 31; i++) {
            a >>= 1;
            System.out.println(Integer.toBinaryString(a));
        }
    }

    private void practice12(){
       /*练习12
       练习（12）题目：以一个所有位为1的二进制数字开始，先左移它，然后用无符号右移操作符对其右移，
       直至所有的二进制位都被移除为止，每移一位都要使用Integer.toBinaryString()显示结果。
       */
        int i = 0xffffffff;
        System.out.println("先左移，得到数为：");
        i <<= 1;//先左移一次
        System.out.println(Integer.toBinaryString(i));
        System.out.println("再不断右移，得到的数为：");
        for (int j = 0; j < 32; j++) {
            i >>>= 1;//每次移动一位，并将结果赋给i
            System.out.printf("%32s\n",Integer.toBinaryString(i));
        }
    }

    private void practive13(){
        /*
练习（13）题目：编写一个方法，它以二进制形式显示char类型的值，使用多个不同的字符显示它。
        */
        Scanner scanner = new Scanner(System.in);
        char c = scanner.next().charAt(0);//控制台输入
        System.out.println(Integer.toBinaryString(c));
    }



}
