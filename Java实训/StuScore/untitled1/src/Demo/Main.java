package Demo;
import Demo.ScoreManager;
import Demo.Student;

import java.util.*;

public class Main {
    private static ScoreManager manager=new ScoreManager();
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
    initStudent();
        while(true){
            System.out.println("==============");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.添加分数");
            System.out.println("5.查看学生");
            System.out.println("6.查看科目");
            System.out.println("7.求学生平均值");
            System.out.println("8.求课程平均值");
            System.out.println("9.查看所有学生");
            System.out.println("0.退出");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
//                    System.out.println("AddID");
//                    String ID=scanner.nextLine();
//                    System.out.println("Age");
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    inputScore();
                    break;
                case 5:
                    queryStudent();
                    break;
                case 6:
                    queryCourse();
                    break;
                case 7:
                    StudentAvge();
                    break;
                case 8:
                    getCourseAverage();
                    break;
                case 9:
                    manager.displayAllStudent();
                    break;
//                case 10:
//                    Student student = manager.getStudnetById("2025001");
//                    printStudent(student);
//                    break;
                case 0:
                   System.out.println("程序已退出");
                    System.exit(0);
                default:
                    System.out.println("无效");
            }
        }
        //scanner.close();

    }
    private static void addStudent(){
        System.out.println("请输入学号");
        String ID=scanner.next();
        System.out.println("输入姓名");
        String name=scanner.next();
        System.out.println("输入年龄");
        int age=scanner.nextInt();

        if (manager.addStudent(ID, name, age)){
            System.out.println("添加成功");
        }
        else{
            System.out.println("添加失败");
        }
    }

    private static void deleteStudent(){
        System.out.println("要删除的学号");
        String ID=scanner.next();
        if(manager.deleterStudent(ID)){
            System.out.println("成功");
        }
        else{
            System.out.println("失败");
        }
    }
    private static void updateStudent(){
        System.out.println("要修改的学号");
        String ID=scanner.next();
        System.out.println("新的姓名");
        String name =scanner.next();
        System.out.println("新的年龄");
        int age=scanner.nextInt();

        if (manager.updateStudentInfo(ID,name,age)){
            System.out.println("成功");
        }
        else{
            System.out.println("失败");
        }
    }
    private static void  inputScore(){
        System.out.println("要添加的学生学号");
        String ID=scanner.next();
        System.out.println("添加的成绩科目");
        String course =scanner.next();
        System.out.println("添加的成绩");
        double score=scanner.nextInt();

        if (manager.inputScore(ID,course,score)){
            System.out.println("成功");
        }
        else{
            System.out.println("失败");
        }
    }
    private static void queryStudent(){
        System.out.println("====查找学生信息======");
        System.out.println("请输入学号");
        String ID=scanner.next();

        Student student=manager.getStudnetById(ID);
        if(student !=null){
            System.out.println("学号:"+student.getID()+
                    "姓名:"+student.getName()+
                    "年龄:"+student.getAge()+
                    "分数"+student.getScores());
        }
        else{
            System.out.println("错误");
        }

    }
    private static void StudentAvge(){
        System.out.println("查看学生平均分");
        System.out.println("学生学号");
        String ID=scanner.next();

        double avg=manager.studentAverages(ID);
        if(avg>=0){
            System.out.println("学号:"+ID+"平均分"+avg);
        }
        else{
            System.out.println("错误");
        }
    }

//    private static void queryCourse(){
//        System.out.println("查看所有的科目");
//        String course=scanner.next();
//        manager.queryCourseScore(course);
//    }
    private static void queryCourse() {
        System.out.println("请输入科目名称：");
        String course = scanner.next();
        try {
            manager.queryCourseScore(course);

        } catch (Exception e) {
            System.out.println("查询课程成绩时发生错误: " + e.getMessage());
        }
    }



    private static void getCourseAverage(){
        System.out.println("科目名称");
        String course=scanner.next();
        double  avg=manager.getCourseAverage(course);
        if(avg>0){
            System.out.println("科目平均分:"+avg);
        }
        else {
            System.out.println("无效值");
        }
    }

    //创建学生成绩信息
    //初始化学生信息
    private static void initStudent() {
        manager.at("2025001", "张三", 20);
        manager.at("2025002","李四", 21);
        manager.at("2025003", "王五", 22);

        manager.inputScore("2025003", "语文", 70);
        manager.inputScore("2025003", "数学", 80);
        manager.inputScore("2025003", "英语", 85);

        manager.inputScore("2025002", "语文", 70);
        manager.inputScore("2025002", "数学", 80);
        manager.inputScore("2025002", "英语", 85);

        manager.inputScore("2025001", "语文", 70);
        manager.inputScore("2025001", "数学", 80);
        manager.inputScore("2025001", "英语", 85);

    }

}

