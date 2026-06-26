package DEMO;

public class Car {
    static String brand="Banz";
    static int price=10000;
    static class Engine{
        public void run() {
            System.out.println("Engine is running");
        }
        //静态方法
        public static void start() {
            System.out.println("Engine is starting");
        }
        public static void method() {
            System.out.println("Engine method");
        }
    }
    Engine engine=new Engine();
    public void start() {
//        Car car=new Car();
//        car.engine.run();
//        car.engine.start();
//        car.engine.method();
        System.out.println("Car is starting");
    }
}
