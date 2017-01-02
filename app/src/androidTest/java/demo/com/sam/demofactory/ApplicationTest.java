package demo.com.sam.demofactory;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.Calendar;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testWeekTime() {
        //获取本周时间的写法
        final Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        instance.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);



    }

    public void testAnagram() throws Exception {
        assertTrue(anagram("django", "naogdj"));
    }

    public static boolean anagram(String s, String t) {
        // write your code here
        if(s==null||t==null){
            return false;
        }else if(s.equals(t)){
            return true;
        }else if(s.length()!=t.length()){
            return false;
        }else{
            char a ;
            StringBuilder sb = new StringBuilder(s);
            StringBuilder tb = new StringBuilder(t);
            while(sb.length()>0){
                a = sb.charAt(0);
                sb.deleteCharAt(0);
                int index = tb.indexOf(String.valueOf(a));
                if(index <0){
                    return false;
                }
                tb.deleteCharAt(index);

            }
            return tb.length()==0;
        }

    }

}