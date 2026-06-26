package NeibuClass;

public class Test extends Outer {
    public static void main(String[] args) {
        Outer outer = new Outer();   //实例化外部类
        Outer.Inner inner = outer.new Inner(); //实例化内部类
        inner.show1(); //调用内部类成员方法
        outer.test2(); //调用外部类成员方法
    }
}
