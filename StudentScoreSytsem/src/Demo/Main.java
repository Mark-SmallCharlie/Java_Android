package Demo;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ScoreManager manager=new ScoreManager();
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("====");
            System.out.println("1.AddStudent");
            System.out.println("2.fountStudent");
            System.out.println("3.fixStudent");
            System.out.println("4.DelStudnet");
            System.out.println("5.AddScore");
            System.out.println("6.foundScore");
            System.out.println("7.AvgScore");
            System.out.println("8.AvgStudent");
            System.out.println("0.Exit");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
                    System.out.println("AddID");
                    String ID=scanner.nextLine();
                    System.out.println("Age");

            }
        }
    }
}
