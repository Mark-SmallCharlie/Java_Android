package Demo;
import java.util.*;

public class ScoreManager {
    private List<Student> studentsList;

    public ScoreManager() {
        this.studentsList = new ArrayList<>();

    }

    public boolean addStudent(Student student) {
//        for (Student s : studentsList) {
//            if (s.getID().equals(student.getID())) {
//                return false;
//            }
//        }
        studentsList.add(student);
        return true;
    }

    public Student getStudnetByid(String ID) {
        for (Student s : studentsList) {
            if (s.getID().equals(ID)) {
                return s;
            }
        }
        return null;
    }

    public boolean updateStudentInfo(String ID, String newName, int newAge) {
        Student student = getStudnetByid(ID);
        if (student != null) {
            student.setName(newName);
            student.setAge(newAge);
        }
        return false;
    }

    public boolean deleterStudent(String ID) {
        Student student = getStudnetByid(ID);
        if (student != null) {
            studentsList.remove(student);
            return true;
        }
        return false;
    }

    public boolean inputScore(String ID,String course,double score){
        Student student = getStudnetByid(ID);
        if(student !=null&&score>=0&&score<=100){
            student.addScore(course,score);
            return true;
        }
        return false;
    }
    public void queryCourseScore(String course){
        System.out.println(course);
        for (Student s:studentsList){
            Double score=s.getScore(course);
            System.out.println(s.getID()+s.getName()+":"+(score ==null? "weixuanke":score));
        }

    }
    public Double getCourseAverage(String course){
        double total=0;
        int count =0;
        for (Student s:studentsList){
            s.getScore(course);
            if(score!=null){
                total+=score;
                count++;
            }
        }
        return count ==0?0.0:total/count;
    }
}
