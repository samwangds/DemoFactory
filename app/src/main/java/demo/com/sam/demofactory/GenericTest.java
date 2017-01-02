package demo.com.sam.demofactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型测试
 * @author SamWang(199004)
 *         2016/12/8 18:24
 */
public class GenericTest {

    private void test() {
        List<? extends Base> list1 = new ArrayList<>();//一个类型的list，这个类型？是继承了Base，但是不知道具体是哪个类，所以无法添加

        list1 = new ArrayList<AA>();
//        list1 = new ArrayList<Object>(); //x

//        list1.add(new Base());//不能添加任何类型
//        list1.add(new A());
//        list1.add(new B());
        list1.add(null); // null不属于任何类型
        final Base base = list1.get(0);//可以获取到具体类型


//        ---------------------------------
        List<? super A> list2 = new ArrayList<>();//某种类型的list,这种类型是A的子类，不知道实际是什么，但是一定是A或者A的子类。
//        list2 = new ArrayList<Base>();//ok
//        list2 = new ArrayList<Object>();//ok

        list2.add(new A());
        list2.add(new AA());
//        list2.add(new Base());//  List 添加一个 A 或者其子类型的对象是安全的
//        list2.add(new B());
        final Object object = list2.get(0);//拿不到具体对象，不知道具体类型


    }


    class Base {
    }

    class A extends Base {
    }

    class B extends Base {
    }

    class AA extends A {
    }
}

