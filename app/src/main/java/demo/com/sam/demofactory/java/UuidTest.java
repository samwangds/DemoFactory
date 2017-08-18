package demo.com.sam.demofactory.java;

import java.util.UUID;

/**
 * @author SamWang(199004)
 *         2017/8/14 15:55
 */
public class UuidTest {
    public static void main(String[] args) {
        UUID test1 = UUID.fromString("631aaf8b-a688-47f6-99b7-00caef9b70b7");
        System.out.println(test1);
        System.out.println(test1.toString());
        System.out.println(UUID.fromString(test1.toString()).toString());
    }
}
