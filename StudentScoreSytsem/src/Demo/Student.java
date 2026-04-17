package Demo;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Student {
    private String name;
    private int ID;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Double>
        socremap;

    public Student(String name, int ID, int age) {
        this.name = name;
        this.age = age;
        this.ID = ID;
        this.socremap = new HashMap<>();
    }

    public void addUpdateScore(String courseName, Double score) {
        if (score < 0 || score > 100) {
            System.out.println("chengjiwuxiao");
        }
        if (score > 0 && score < 100) {
            socremap.put(courseName, score);
        }
    }

    public Double getScore(String coure) {
        return socremap.get(coure);
    }

    public Double getAveageScore() {
        if(socremap.isEmpty())return 0.0;
        double total=0;
        for (Double s:socremap.values())total +=s;
        return total /socremap.size();
    }
}
