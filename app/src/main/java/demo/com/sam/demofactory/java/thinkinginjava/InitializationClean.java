package demo.com.sam.demofactory.java.thinkinginjava;


/**
 初始化和清理
 * Created by samwang on 2017/8/6.
 */

public class InitializationClean {
    public static void main(String[] args) {
        printaArray(new Integer(1),new Float(1.1));
        printaArray((Object[]) new Integer[]{new Integer(1),new Integer(2)});
        printaArray();

    }

    static void printaArray(Object... objs) {
        for (Object obj : objs) {
            System.out.println(obj);
        }
        System.out.print(" printaArray ");
    }



}
