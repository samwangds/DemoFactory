package demo.com.sam.demofactory.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *<img src="/images/small/37.gif" width="19" height="19" />
 * 匹配出37.gif
 * @author SamWang(199004)
 *         2016/12/29 11:13
 */
public class RegularExpression2 {
    public static void main(String[] args) {
        Pattern p=Pattern.compile("(?<=(/images/small/)).*?(?=(\\.gif\"))");
        printResult(p,"<img src=\"/images/small/37.gif\" width=\"19\" height=\"19\" />");
        printResult(p,"<img src=\"/images/small/37aserww23.gif\" width=\"19\" height=\"19\" />");
        printResult(p,"<img src=\"/images/small/3q253aszzsdd7.gif\" width=\"19\" height=\"19\" />");

//        "[\u4E00-\u9FA5]+" 匹配中文

    }



    private static void printResult(Pattern p, String rogin) {


        Matcher m=p.matcher(rogin);
        m.find();
        System.out.println(rogin + " 匹配结果 ");
        System.out.println(m.group());
    }


}

