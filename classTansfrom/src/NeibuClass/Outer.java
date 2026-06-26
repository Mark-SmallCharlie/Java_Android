package NeibuClass;

public class Outer {
    int m=0;
    void test1(){
        System.out.println("Outer.test1成员方法()");
    }
    class Inner{
        int n=1;
        void show1(){
            //在class Inner中访问Outer的成员变量m
            System.out.println("外部类成员方法m="+m);
            //在class Inner中访问Inner的成员变量test1()
            test1();
        }
        void show2() {
           // Inner inner = new Inner();//创建Inner对象
            System.out.println("Inner.show2()内部类成员方法"); //访问Inner对象的成员变量n和方法
           // inner.show2();
        }
    }
    void test2(){
        Inner inner = new Inner(); //创建Inner对象
        System.out.println("Outer.test2内部类成员方法n="+inner.n);
        //inner.show1(); //调用Inner对象的成员方法show1()
        inner.show2(); //调用Inner对象的成员方法show2()
    }
}
