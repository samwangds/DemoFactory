package demo.com.sam.demofactory.java;

//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                        . ' \\| |// ` .
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖保佑             永无BUG
//          佛曰:
//                  写字楼里写字间，写字间里程序员；
//                  程序人员写程序，又拿程序换酒钱。
//                  酒醒只在网上坐，酒醉还来网下眠；
//                  酒醉酒醒日复日，网上网下年复年。
//                  但愿老死电脑间，不愿鞠躬老板前；
//                  奔驰宝马贵者趣，公交自行程序员。
//                  别人笑我忒疯癫，我笑自己命太贱；
//                  不见满街漂亮妹，哪个归得程序员？

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则: http://www.jianshu.com/p/ae353d39f484
 * java： http://blog.csdn.net/cclovett/article/details/12448843
 *
 * @author SamWang(199004)
 *         2016/12/29 11:13
 */
public class RegularExpression {
    public static void main(String[] args) {
        stringMath();

    }

    /**
     * 字符串匹配
     */
    private static void stringMath(){
        Pattern pattern = Pattern.compile("\\d+");//提取其中的数字
        Matcher matcher = pattern.matcher("xx1xx(485894)");
        while(matcher.find()) {
            System.out.println("------------------------------------------");
            String val = matcher.group();
            System.out.println(val);
        }
    }


    private static void boringTest(){
        /**
         * 测试单个字符
         * +：匹配字符的一次或多次出现
         * ?：匹配字符的0次或一次出现
         * *：匹配字符的0次或多次出现
         * .字符可以匹配任何一个单个的字符
         */
        System.out.print("                            ");
        printMathResult("x+", "_oxOoxx_", "o");//_ooOoo_ 一个或多个x被匹配

        System.out.print("\n                           ");
        printMathResult("x1?", "x8888888x1", "o");//o8888888o x或x1被匹配

        System.out.print("\n                           ");
        printMathResult("x1*", "x\" . \"x1111111", "88");//88" . "88  x或x带n个1被匹配

        printMathResult(".", "withoisldkgnd49u8u8319j?>?!@@^**% \" . \"x1111111", "");//nothing 全部匹配

        System.out.print("\n                           ");
        printMathResult("-{2,}", "--(| -_- |)----", "");//(| -_- |) 两个以上的-被匹配

        System.out.print("\n                            ");
        printMathResult("6{3}", "O666 = /O", "\\\\");//O\ = /O 3个6被匹配


        /**
         *
         * *和+,{n,} 贪婪型元字符，它们在进行匹配时的行为模式是多多益善而不是适可而止的。
         * *?，+?,{n,}? 懒惰型元字符(上面的加?后缀) 适可而止
         * {}：精确地设定重复次数 {3}重复3 ; {3,} 重复>=3 ; {1,4}重复>=1&&<=4
         * */
        System.out.print("\n                        ");
        printMathResult("v_+?", "____/`---'v_____", "\\\\");//v_被匹配 懒惰型


        /**
         * 元字符 [和]用来定义一个字符集合，必须匹配该集合里的字符之一。定义一个字符集合的具体方法有两种：
         * 1. 把所有的字符都列举出来 , 2.利用元字符-以字符区间的方式给出
         * */
        System.out.print("\n                        ");
        printMathResult("[ab]", ". ' ab| |// ` .", "\\\\");//a,b分别被匹配

        System.out.print("\n                       ");
        printMathResult("[a-z]", "/ bg||| : |||// m", "\\\\");//字母分别被匹配

        /**
         *  \b单词边界，反之\B非边界
         * */
        System.out.print("\n                    ");
        printMathResult("\\bAAA", "AAA / _||||| -:- AAA|||||- \\", "");//AAA被匹配


        /**
         * 字符串边界  ^：定义字符串开头  $：定义字符串结尾
         * */
        System.out.print("\n                       ");
        printMathResult("^///", "///| | \\\\\\ - /// | |", "");//开头的///被匹配
        System.out.print("\n                     ");
        printMathResult("'$", "| \\_| ''\\---/'' | |'", "");//最后的'被匹配

        /**
         * 子表达式 + 回溯引用匹配
         * */
        System.out.print("\n                      ");
        printMathResult("([AB])\\1", "AA\\ .-\\__ `-` ___/-. /BB", "");//AABB被匹配

//        regularExpression = "a[1-5].*?a[1-5]";
//        regularExpression = "(a[1-5]).*?\\1";
//        System.out.println("a1xxxa1000".replaceAll(regularExpression, "#"));
//        System.out.println("a2xxxa2000".replaceAll(regularExpression, "#"));
//        System.out.println("a2xxxa3000".replaceAll(regularExpression, "#"));

        /**
         * 前后查找 有时候需要正则表达式标记要匹配的文本的位置（而不仅仅是文本本身）
         *
         * 向前查找模式其实就是一个以?=开头的子表达式
         * ?<= 向后查找，查找出现在匹配文本之后的字符
         *
         * (?=)	正向前查找
         * (?!)	负向前查找 //指的是不与给定模式相匹配的文本 不常用
         * (?<=)正向后查找
         * (?<!)负向后查找
         * */
        System.out.print("\n                   ");
        printMathResult(".{2}(?=(__$))", "___`. .' /--.--\\ `. .   __", "");//最后的__的前两个字符


        System.out.print("\n                .\"\" '< `.___\\_<|>_/___.' >'\"\".");
        System.out.print("\n               | | : `- \\`.;`\\ _ /`;.`/ - ` : | |");
        System.out.print("\n                 \\ \\ `-. \\_ __\\ /__ _/ .-` / /");
        System.out.print("\n         ======`-.____`-.___\\_____/___.-`____.-'======");
        System.out.print("\n                            `=---='");
        System.out.print("\n\n\n");
        System.out.print("         .............................................");
    }


    private static void printMathResult(String regularExpression, String original, String replace) {
        System.out.print(original.replaceAll(regularExpression, replace));
    }


}

// 备注--------------------------
/**
 * 转义符：\
 * \.将匹配.本身
 * \\将匹配\本身
 * <p>
 * 元字符
 * [\b]	回退（并删除）一个字符（Backspace键）
 * \f	换页符
 * \n	换行符
 * \r	回车符
 * \t	制表符
 * \v	垂直制表符
 * <p>
 * \r\n匹配一个“回车+换行”组合
 * 匹配文本结束标签
 * Windows：\r\n
 * Linux ： \n
 * <p>
 * 元字符
 * [\b]	回退（并删除）一个字符（Backspace键）
 * \f	换页符
 * \n	换行符
 * \r	回车符
 * \t	制表符
 * \v	垂直制表符
 * <p>
 * \r\n匹配一个“回车+换行”组合
 * 匹配文本结束标签
 * Windows：\r\n
 * Linux ： \n
 */


/**
 * 元字符
 * [\b]	回退（并删除）一个字符（Backspace键）
 * \f	换页符
 * \n	换行符
 * \r	回车符
 * \t	制表符
 * \v	垂直制表符
 *
 *  \r\n匹配一个“回车+换行”组合
 *  匹配文本结束标签
 *  Windows：\r\n
 *  Linux ： \n
 */


