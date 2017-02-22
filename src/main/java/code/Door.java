package code;

/**
 * Created by zkq on 2017/2/21.
 */
public class Door {
    public static void main(String[] args) {
//        jsonTest();
        gsonTest();
    }

    private static void jsonTest(){
        JsonDemo jsonDemo = new JsonDemo();
//        jsonDemo.jsonObject();
//        jsonDemo.jsonWithMap();
//        jsonDemo.jsonWithFile();
        jsonDemo.jsonWithBean();
    }

    private static void gsonTest(){
        GsonDemo gsonDemo = new GsonDemo();
//        gsonDemo.gsonWithBean();
//        gsonDemo.gsonWithFile();
        gsonDemo.gsonWithDate();
    }
}
