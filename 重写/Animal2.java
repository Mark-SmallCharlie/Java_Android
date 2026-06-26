package Demo;

public interface Animal2 {
    public static final int Id=1;
    String name="Sigma";
    void shout();
    void eat();
    public abstract void info();
    //静态方法
    public static int getId(){
        System.out.println("Id is "+Id);
        //通过接口调用静态方法
        return Animal2.Id;
    }
    public default void show(){     //默认方法
        System.out.println("This is a default method"+name);
    }
}
