package Demo;
//import java.*;
import java.util.Map;
import java.util.*;

public class Student {
    private String name;
    private String ID;
    private int age;
    private Map<String, Double> score;

    public Student(String ID, String name, int age) {
        this.name = name;
        this.age = age;
        this.ID = ID;
        this.score = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Map<String, Double> getScores(){
        return score;
    }

    public void addScore(String courseName, Double score) {
        if (score < 0 || score > 100) {
            System.out.println("成绩无效");
        }
        else {
            this.score.put(courseName, score);
        }
    }

    public Double getScore(String course) {
        return score.get(course);
    }

    public Double getAveageScore() {
        if(score.isEmpty())
            return 0.0;
        double total=0;
        for (Double s:score.values())total +=s;
        return total /score.size();
    }


}
