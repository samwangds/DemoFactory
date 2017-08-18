package demo.com.sam.demofactory.java;

import java.io.File;

/**
 * @author SamWang(199004)
 *         2017/8/14 16:42
 */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("c:a/b/c.txt");
        String name = file.getName();
        System.out.println(name);
        int index = name.lastIndexOf(".");
        System.out.println(index < 0 ? name : name.substring(0, index));

    }
}
