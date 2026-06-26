package Demo;
import java.net.IDN;
import java.util.ArrayList;
import java.util.*;

public class ScoreManager  {
    private List<Student> studentsList;

    public ScoreManager() {
        this.studentsList = new ArrayList<>();

    }

    public boolean addStudent(Student student){
        for (Student s : studentsList) {
            if (s.getID().equals(student.getID())) {
                System.out.println( "错误：学生ID" + student.getID() +"学生已存在");
            }
        }
        studentsList.add(student);
        return true;
    }
    public boolean addStudent(String ID,String name,int age) {
        Student student=new Student(ID,name,age);
        return addStudent(student);

    }
    public void at(String ID,String name,int age) {
        Student student=new Student(ID,name,age);
        studentsList.add(student);
    }

    public Student getStudnetById(String ID) {
        for (Student s : studentsList) {
            if (s.getID().equals(ID)) {
                return s;
            }
        }
        return null;
    }

    public boolean updateStudentInfo(String ID, String newName, int newAge) {
        Student student = getStudnetById(ID);
        if (student != null) {
            student.setName(newName);
            student.setAge(newAge);
            return true;
        }
        return false;
    }

    public boolean deleterStudent(String ID) {
        Student student = getStudnetById(ID);
        if (student != null) {
            studentsList.remove(student);
            return true;
        }
        return false;
    }

    public boolean inputScore(String ID, String course, double score) throws IllegalArgumentException {
        try {
            Student student = getStudnetById(ID); // 修正了方法名的拼写错误
            if (student == null) {
                throw new IllegalArgumentException("学生未找到");
            }
            if (score < 0 || score > 100) {
                throw new IllegalArgumentException("分数必须在0到100之间");
            }
            student.addScore(course, score);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

//    public void queryCourseScore(String course){
//        System.out.println(course);
//        for (Student s:studentsList){
//            Double score=s.getScore(course);
//            System.out.println(s.getID()+s.getName()+":"+(score ==null? "未选课":score));
//        }
//
//    }
public void queryCourseScore(String course) {
    if (course == null || course.isEmpty()) {
        System.out.println("错误：课程名称不能为空");
        return;
    } //如果查询的课程名不存在，则返回空字符串
    System.out.println("要查找的课程名是："+course);
    Map<String, Double> result=new HashMap<>();
    for (Student s : studentsList) {
        Double score = s.getScore(course);
        if (score == null) {
            System.out.println(s.getID() + s.getName() + ":未选课");
        }
        else {
            System.out.println("学号："+s.getID() +" 姓名："+ s.getName() +" 成绩：" + score);
        }
    }
}

    public Double getCourseAverage(String course){
        double total=0;
        int count =0;
        for (Student s:studentsList){
            double score=s.getScore(course);
            if(score !=-1){
                total+=score;
                count++;
            }
        }

        return count ==0?0.0:total/count;
    }
    public double studentAverages(String ID){
        Student student=getStudnetById(ID);
        if(student !=null){
            return student.getAveageScore();
        }
        return -1;
    }
    public void displayAllStudent(){
        if(studentsList.isEmpty()){
            System.out.println("无学生信息");
            return;
        }
        System.out.println("=====");
        for(Student student:studentsList){
            System.out.println("学号:"+student.getID()+
                    " 姓名:"+student.getName()+
                    " 年龄:"+student.getAge()+
                    " 分数:"+student.getScores());
        }
    }

}
