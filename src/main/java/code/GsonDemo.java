package code;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Created by zkq on 2017/2/21.
 */
public class GsonDemo {

    public void gsonWithBean(){
        JsonBean jsonBean = new JsonBean();
        jsonBean.setAge(27.2f);
        jsonBean.setHasGF(true);
        jsonBean.setName("ZKQ");
        jsonBean.setSkill(new String[]{"android","java","basketball"});
        jsonBean.setIgnore("haha");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy() {
            public String translateName(Field field) {
                if(field.getName().equals("name")){
                    return "NAMe";
                }
                return field.getName();
            }
        });
        Gson gson = gsonBuilder.create();
        System.out.println("gson.toJson(jsonBean) = " + gson.toJson(jsonBean));
    }

    public void gsonWithFile(){
        File file = new File("test.json");
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str = "";
            while ((str = br.readLine())!=null){
                sb.append(str);
            }
            Gson gson = new Gson();
            JsonBean jsonBean = gson.fromJson(sb.toString(),JsonBean.class);
            System.out.println("jsonBean = " + jsonBean.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gsonWithDate(){
        File file = new File("test.json");
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str = "";
            while ((str = br.readLine())!=null){
                sb.append(str);
            }
            GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd");
            gsonBuilder.setPrettyPrinting();

            Gson gson = gsonBuilder.create();
            JsonBean jsonBean = gson.fromJson(sb.toString(),JsonBean.class);
            System.out.println("jsonBean = " + jsonBean.getMemory().toLocaleString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
