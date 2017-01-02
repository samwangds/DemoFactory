package demo.com.sam.demofactory.java;

/**
 * 正则: http://www.jianshu.com/p/27c8b26faa9b
 * java： http://blog.csdn.net/cclovett/article/details/12448843
 *
 * @author SamWang(199004)
 *         2016/12/29 11:13
 */
public class RegularExpression {
    public static void main(String[] args) {
        String[] str = {
                "sales1.xls",
                "sales2.xls",
                "sales3.xls",
                "na1.xls",
                "na2.xls",
                "sa5.xls",
                "sa51.xls",
                "orders3.xls",
                "apac1.xls",
                "europe2.xls",
                "ababcc.xls"
        };
//        Pattern pattern = Pattern.compile("test");
//        Matcher matcher = pattern.matcher("test");
        String regularExpression;

/**
 * +：匹配字符的一次或多次出现
 * ?：匹配字符的0次或一次出现
 * *：匹配字符的0次或多次出现
 *
 * *和+,{n,} 贪婪型元字符，它们在进行匹配时的行为模式是多多益善而不是适可而止的。
 * *?，+?,{n,}? 懒惰型元字符(上面的加?后缀) 适可而止
 * {}：精确地设定重复次数 {3}重复3 ; {3,} 重复>=3 ; {1,4}重复>=1&&<=4
 * */

//        regularExpression = "sales.";//.字符可以匹配任何一个单个的字符。
//        regularExpression = ".a.";

//        regularExpression = ".a1?";//?只能匹配一个字符（或字符集合）的零次或一次出现
//        regularExpression = ".a1*";

//        regularExpression = ".{4,6}";
//        regularExpression = ".{4,}?";//懒惰型元字符

//        regularExpression = "s.*s";// 贪婪型元字符对比 两个s中间包含n个字符
//        regularExpression = "s.*?s";

//        regularExpression = "\\wa\\d+\\.xls";//匹配同一个字符（或字符集合）的多次重复，用一个+字符作为后缀就可以了。至少一个，不匹配零个字符的情况）。
//        regularExpression = "ab+\\.xls";



        /**
         * 元字符 [和]用来定义一个字符集合，必须匹配该集合里的字符之一。定义一个字符集合的具体方法有两种：
         * 1. 把所有的字符都列举出来 , 2.利用元字符-以字符区间的方式给出
        * */
//        regularExpression = "[ns]a.\\.xls";//[] 匹配多个字符中的某一个 ->  na.\.xls和sa.\.xls
//        regularExpression = "[ns]a[2-9]\\.xls";//字符区间 -作为连字符 [A-Za-z0-9]匹配任何一个字母（无论大小写）或数字
//        regularExpression = "[ns]a[^2-9]\\.xls";// 除了那个字符集合里的字符，其他字符都可以匹配，使用字符^在[]里 (shift + 6)

//        regularExpression = "\\Da\\d\\.xls";//\d	任何一个数字字符（等价于[0-9]）  \D	任何一个非数字字符（等价于[^0-9]）
//        regularExpression = "\\wa\\d\\Wxls";//\w	任何一个字母数字字符或下划线字符（[a-zA-Z0-9_]） \W	任何一个非字母数字字符或下划线字符（[^a-zA-Z0-9_]）
//        \s	任何一个空白字符（等价于[\f\n\r\t\v]） \S	任何非一个空白字符（等价于[^\f\n\r\t\v]） 主要用来匹配特定字符（制表符、换行符）和

        /**
         *  \b单词边界，反之\B非边界
         * */
//        regularExpression = "\\bcat\\b";// \b单词边界，反之\B非边界
//        regularExpression = "\\Bcat";// \b单词边界，反之\B非边界
//        System.out.println("The cat scattered his food." + ","+"The cat scattered his food.".replaceAll(regularExpression, "#"));

        /**
         * 字符串边界  ^：定义字符串开头  $：定义字符串结尾
         * */
//         regularExpression = "^s";

        /**
         * 子表达式
        * */
//         regularExpression = "(a.)+";//()里表示一个表达式，后面跟的作用于这个表达式

        /**
         * 回溯引用匹配
         * */
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
         * (?<=)正前后查找
         * (?<!)负向后查找
         * */
//        regularExpression = ".{2}(?=(2.x))";
//        regularExpression = "(?<=al).*(?=xl)";// al------xl
//
//        for (String s : str) {
//            System.out.println(s + ","+s.replaceAll(regularExpression, "#"));
//        }



// 备注--------------------------
        /**
         * 转义符：\
         * \.将匹配.本身
         * \\将匹配\本身
         * */


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


    }



}
