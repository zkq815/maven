package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zkq on 2017/2/21.
 */
public class ReflectDemo {

    public static void main(String[] args) {

//        methodReflect();
        classUtilTest();
    }


    private static void classUtilTest(){
//        Integer in = 1;
//        ClassUtil.printClassMessage(in);
//        System.out.println("-----------------");
//        ClassUtil.getClassMethodInfo(in);
//        System.out.println("-----------------");
//        ClassUtil.getClassFieldInfo(in);
//        System.out.println("-----------------");
//        ClassUtil.getConstructorInfo("hello");
        ClassUtil.findSomething();
    }


    private void clazzTest(){
        Class c4 = int.class;//int的类类型
        Class c5 = String.class;//string类的类类型 string类字节码（自己方便理解的称呼）
        Class c6 = double.class;
        Class c7 = Double.class;
        Class c8 = void.class;
        System.out.println("c8 = " + c8.getName());//类类型的名称
        System.out.println("c8 = " + c8.getSimpleName());//不包含报名的类的名称

        //Foo的实例对象如何表示
        Foo foo = new Foo();//foo就是Foo对象的实例
        //Foo这个类也是一个实例对象，是Class类的实例对象，如何表示？
        //任何一个类都是class类的实例对象，这个实例对象有三种表示方式

        //第一种表示方式,实际在告诉我们任何一个类都有一个隐含的静态成员变量class
        Class c1 = Foo.class;
        //第二种表示方式 已知该类的对象通过getClass方法获取
        Class c2 = foo.getClass();


        /**官网表示：c1 c2 表示了Foo类的   类类型(class type)
         * 万事万物皆对象
         * 类也是对象，是Class类的实例对象
         * 这个对象我们应该成为该类的类类型
         * 不管 c1 还是c2都代表了Foo类的类类型，一个类只可能是Class类的一个实例对象
         * */

        //第三种表示方式
        Class c3 = null;
        try {
            c3 = Class.forName("reflect.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println( (c1==c2)+"----"+(c2 == c3) + "----"+(c1 == c3));

        //我们完全可以通过类的类类型创建该类的对象实例---》通过c1 c2 c3创建foo的实例

        try {
            Foo foo1 = (Foo) c1.newInstance();//需要有无参数的构造方法
            foo1.haha();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void methodReflect(){
        Foo foo = new Foo();
        Class c = foo.getClass();

        try {
            Method m = c.getMethod("methodA",new Class[]{int.class,int.class});
            Method m1 = c.getMethod("methodB",new Class[]{String.class,String.class});
            Method m2 = c.getMethod("methodC");

            Object obj1 = m.invoke(foo,new Object[]{20,30});
            Object obj2 = m.invoke(foo,20,30);
            Object obj3 = m1.invoke(foo,new Object[]{"abc","xyz"});
            Object obj4 = m1.invoke(foo,"abc","xyz");
            Object obj5 = m2.invoke(foo);
            System.out.println(obj1);
            System.out.println(obj2);
            System.out.println(obj3);
            System.out.println(obj4);
            System.out.println(obj5);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


class Foo{

    void haha(){}

    public void methodA(int a,int b){
        System.out.println(a+b);
    }

    public void methodB(String a,String b){
        System.out.println(a.toUpperCase()+","+b.toLowerCase());
    }

    public void methodC(){
        System.out.println("这是c方法");
    }
}