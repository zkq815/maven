package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by zkq on 2017/2/21.
 */
public class ClassUtil {

    /**
     * 获取类的信息
     * @param object
     */
    public static void printClassMessage(Object object){
        //要获取类的信息，首先要获取类类型
        Class c = object.getClass();//传递的是哪个类的对象，c就是该子类的类类型
        //获取类的名称
        System.out.println("类的名称是" + c.getName());

    }

    /**
     * 获取类的方法信息
     * @param object
     */
    public static void getClassMethodInfo(Object object){
        //要获取类的信息，首先要获取类类型
        Class c = object.getClass();//传递的是哪个类的对象，c就是该子类的类类型

        /**
         * Method类，方法对象
         * 一个成员方法就是一个method对象
         * getMethods()方法获取的是所有public的函数，包含父类继承而来的函数
         * getDeclaredMethods()获取的是该类所有的声明方法，不管是什么访问权限的，不包含父类的方法
         */
        Method[] methods = c.getMethods();
        Method[] methods1 = c.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Class returnType = methods[i].getReturnType();//得到方法的返回值类型的类类型
            System.out.println("returnType.getName() = " + returnType.getName());//获取返回值的类型名称

            System.out.print("方法名称==" +methods[i].getName()+"(");//得到方法的名称
            //获取参数类型---》得到的是参数列表的类型的类类型
            Class[] paramsTYype = methods[i].getParameterTypes();
            for (Class class1:paramsTYype) {
                System.out.print(class1.getName()+",");
            }
            System.out.println(")");
        }
    }

    /**
     * 获取对象的成员变量信息
     * @param object
     */
    public static void getClassFieldInfo(Object object){
        //要获取类的信息，首先要获取类类型
        Class c = object.getClass();//传递的是哪个类的对象，c就是该子类的类类型

        /**
         * 成员变量也是对象
         * java.lang.reflect.field
         * getField()   获取类的所有public成员变量的信息
         * getDeclaredField()   获取类的所有声明的成员变量，不含父类的成员变量
         */

        Field[] fields = c.getFields();
        Field[] fields1 = c.getDeclaredFields();
        for (Field field: fields1) {
            Class fieldType = field.getType();//得到成员变量类型的类类型
            String typeName = fieldType.getName();
            String fieldName = field.getName();
            System.out.println(" 成员变量的类型=" +typeName +"-----成员变量的名字="+fieldName);

        }
    }

    /**
     * 获取对象构造函数的信息
     * @param object
     */
    public static void getConstructorInfo(Object object){
        Class c = object.getClass();
        /**
         * 构造方法也是对象
         * java.lang.Constructor中封装了构造函数的信息
         * getConstructors() 获取所有public的构造函数
         * getDeclaredConstructors() 获取所有自己声明的构造函数
         */
        Constructor[] cs = c.getConstructors();
        Constructor[] cs1 = c.getDeclaredConstructors();
        for (Constructor constructor: cs1) {
            System.out.print(constructor.getName()+"(");
            //获取构造函数的参数列表
            Class[] params = constructor.getParameterTypes();
            for (Class clazz : params) {
                //获取类类型名称
                String typeName = clazz.getName();
                System.out.print(typeName+",");
            }
            System.out.println(")");

        }

    }


    /**
     * java 集合 泛型的本质调研
     */
    public static void findSomething(){
        ArrayList list1 = new ArrayList();
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("zkq");
        Class c1 = list1.getClass();
        Class c2 = list2.getClass();

        System.out.println((c1==c2));

        /**
         * c1 = c2  说明集合的泛型在编译之后是去泛型化的，换而言之泛型是为了编译时方便
         * java 集合中的泛型是防止错误输入的，只在编译阶段有效，绕过编译就无效了
         * 验证：可以通过反射操作，绕过编译
         */
        try {
            Method m1 = c1.getMethod("add",Object.class);
            Object obj = m1.invoke(list2,26);//绕过编译操作，就绕过了泛型
            System.out.println(list2.size()+"----"+list2);
            //如下操作不可取，会爆出异常
//            for (String string : list2) {
//                System.out.println(string);
//            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
