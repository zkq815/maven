package code;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by zkq on 2017/2/21.
 */
public class JsonBean {

//    @SerializedName("NamE")
    String name;
    float age;
    String[] skill;
    boolean hasGF;
    transient String ignore;//生成json的时候会忽略这个属性
    Date memory;

    public Date getMemory() {
        return memory;
    }

    public void setMemory(Date memory) {
        this.memory = memory;
    }

    public String getIgnore() {
        return ignore;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public String[] getSkill() {
        return skill;
    }

    public void setSkill(String[] skill) {
        this.skill = skill;
    }

    public boolean isHasGF() {
        return hasGF;
    }

    public void setHasGF(boolean hasGF) {
        this.hasGF = hasGF;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skill=" + Arrays.toString(skill) +
                ", hasGF=" + hasGF +
                ", ignore='" + ignore + '\'' +
                ", memory=" + memory +
                '}';
    }
}
