package demo.com.sam.demofactory.util.py;

/**
 * @author SamWang(199004)
 *         2017/2/23 19:19
 */
public class PinYinTest {
    public static void main(String[] args) {

        System.out.print(HanziToPinyin.getInstance().getPinYin("中说来话长"));
        System.out.print(HanziToPinyin.getInstance().getPinYin("afsd长"));
        System.out.print(HanziToPinyin.getInstance().getPinYin("afs#d x长"));
    }
}